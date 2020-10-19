package com.stu.yqs.aspect.SearchController;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.SearchMapper;
import com.stu.yqs.domain.Search;
import com.stu.yqs.utils.IdentityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * date:2020.4.19
 * author:yf
 * detail:在一次搜索之后添加搜索记录
 */
@Aspect
@Component
public class AddSearchHistory {
    @Autowired
    private IdentityUtil identityUtil;
    @Autowired
    private SearchMapper searchMapper;

    @After("execution(* com.stu.yqs.service.GoodService.getTransaction(..))")
    public void exceptionDeal(JoinPoint join) {
        Object[] arr = join.getArgs();
        if (arr[3] == null) return;
        String input = (String) arr[3];
        int userId;
        try {
            userId = identityUtil.isLogin();
        } catch (LogicException e) {
            return;
        }
        Search search = new Search();
        search.setUserId(userId);
        search.setSearchContent(input);
        searchMapper.insertSelective(search);
    }
}
