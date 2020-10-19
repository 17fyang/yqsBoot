package com.stu.yqs.service;


import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.ReviewMapper;
import com.stu.yqs.dao.ThumbMapper;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.Review;
import com.stu.yqs.domain.Thumb;
import com.stu.yqs.domain.search.ThumbSearch;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ThumbMapper thumbMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private IdentityUtil identityUtil;

    @Autowired
    private GoodUtil goodUtil;

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public JSONObject review(Integer goodId, String content) throws LogicException {
        int id = identityUtil.isLogin();
        Good good = goodMapper.selectByPrimaryKey(goodId);
        if (good == null) throw new LogicException(503, "该帖子不存在");
        if (!good.getState().equals("1")) throw new LogicException(503, "该帖子不允许评论");

        Good newGood = new Good();
        newGood.setId(good.getId());
        newGood.setReviewNumber(good.getReviewNumber() + 1);
        goodMapper.updateByPrimaryKeySelective(newGood);

        Review review = new Review();
        review.setGoodId(goodId);
        review.setContent(content);
        review.setReviewerId(id);
        reviewMapper.insertSelective(review);
        JSONObject json = (JSONObject) JSONObject.toJSON(review);
        return json;
    }

    public JSONObject deleteReview(Integer id) throws LogicException {
        int reviewerId = identityUtil.isLogin();
        Review review = reviewMapper.selectByPrimaryKey(id);
        if (review == null) throw new LogicException(503, "该评论不存在");
        if (reviewerId != review.getReviewerId()) throw new LogicException(503, "只能操作自己的评论");
        reviewMapper.deleteByPrimaryKey(id);
        return new JSONObject();
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public JSONObject thumb(Integer goodId) throws LogicException {
        int id = identityUtil.isLogin();
        Good good = goodMapper.selectByPrimaryKey(goodId);
        if (good == null) throw new LogicException(503, "该帖子不存在");
        if (!good.getState().equals("1")) throw new LogicException(503, "该帖子不允许点赞");
        if (thumbMapper.selectAppoint(new ThumbSearch(id, goodId)) != null) throw new LogicException(503, "不能重复点赞");
        Good newGood = new Good();
        newGood.setId(good.getId());
        newGood.setThumbNumber(good.getThumbNumber() + 1);
        goodMapper.updateByPrimaryKeySelective(newGood);

        Thumb thumb = new Thumb();
        thumb.setGoodId(goodId);
        thumb.setThumberId(id);
        thumbMapper.insertSelective(thumb);
        JSONObject json = (JSONObject) JSONObject.toJSON(thumb);
        return json;
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public JSONObject deleteThumb(Integer goodId) throws LogicException {
        int thumberId = identityUtil.isLogin();
        Thumb thumb = thumbMapper.selectAppoint(new ThumbSearch(thumberId, goodId));
        if (thumb == null) throw new LogicException(503, "还未点赞");
        thumbMapper.deleteByGoodId(goodId);

        Good good = goodUtil.stateNormal(goodId);
        good.setThumbNumber(good.getThumbNumber() - 1);
        goodMapper.updateByPrimaryKeySelective(good);
        return new JSONObject();
    }

}
