package com.stu.yqs.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.AddressMapper;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.OrderMapper;
import com.stu.yqs.domain.Address;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.Order;
import com.stu.yqs.domain.search.OrderSearch;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.OutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    final static int INIT_ORDER = 0;
    final static int FINISH_ORDER = 1;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IdentityUtil identityUtil;

    @Autowired
    private GoodUtil goodUtil;
	
    @Autowired
    private OutputUtil outputUtil;

    /*
     * 新增订单，未完成部分：通知卖家
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public JSONObject submitAction(Integer goodId, Integer addressId) throws LogicException {
        int id = identityUtil.isLogin();
        Good good = goodUtil.stateNormal(goodId);
        int sellerId = good.getOwnerId();
        if (id == sellerId) throw new LogicException(501, "不能购买自己的商品");
        Address customerAddress = addressMapper.selectByPrimaryKey(addressId);
        if (customerAddress == null) throw new LogicException(504, "地址id参数异常");
        //获取卖家联系方式
        Address address = addressMapper.selectDefaultByUserId(sellerId);
        if (address == null) throw new LogicException(503, "订单创建失败，卖家暂无默认地址");

        Order order = new Order();
        order.setCustomerId(id);
        order.setSellerId(sellerId);
        order.setGoodId(goodId);
        order.setCustomerAddress(addressId);
        order.setSellerAddress(address.getId());
        orderMapper.insertSelective(order);

        //返回卖家联系方式
        JSONObject json = (JSONObject) JSONObject.toJSON(address);
        json.put("addressId", json.getIntValue("id"));
        json.put("id", order.getId());

        //		outputUtil.addressImfomation(customerAddress.getPhoneNumber(), address.getName(), 
        //				customerAddress.getName(), customerAddress.getPhoneNumber(), customerAddress.getAcademy());
        //		

        //修改订单状态
        good.setState(GoodUtil.FINISH);
        goodMapper.updateByPrimaryKeySelective(good);

        return json;
    }

    /*
     * 订单状态调整
     */
    public JSONObject updateAction(Integer orderId, Short status) throws LogicException {
        Order order = this.hasOrder(orderId);
        if (this.hasStatus(status)) throw new LogicException(501, "状态参数异常");
        order.setStatus(status);
        orderMapper.updateByPrimaryKey(order);
        return new JSONObject();
    }

    //获取订单列表
    public JSONObject getListAction(Integer startId, Integer range, Integer sellerId, Integer customerId, Integer status)
            throws LogicException {
        if (range != null && range < 0) throw new LogicException(501, "参数格式异常");
        int id = identityUtil.isLogin();
        if (!((sellerId != null && id == sellerId) || (customerId != null && id == customerId)))
            throw new LogicException(501, "权限异常");

        OrderSearch search = new OrderSearch();
        search.setStartId(startId);
        search.setCustomerId(customerId);
        search.setRange(range);
        search.setSellerId(sellerId);
        search.setStatus(status);
        JSONArray arr = (JSONArray) JSONArray.toJSON(orderMapper.searchOrder(search));
        arr = goodUtil.addGoodMessageAll(arr, "goodId");
        arr = goodUtil.addOwnerMessageAll(arr);
        return outputUtil.lazyLoading(arr, range);
    }

    //取消订单功能
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public JSONObject deleteAction(Integer orderId) throws LogicException {
        int id = identityUtil.isLogin();
        Order order = this.hasOrder(orderId);
        if (id != order.getCustomerId() && id != order.getSellerId()) throw new LogicException(501, "只能取消自己的订单");
        orderMapper.deleteByPrimaryKey(orderId);

        Good good = new Good();
        good.setState(GoodUtil.ON_SALE);
        good.setId(order.getGoodId());
        goodMapper.updateByPrimaryKeySelective(good);
        return new JSONObject();
    }

    //获取一个订单详情
    public JSON detailAction(Integer orderId) throws LogicException {
        int id = identityUtil.isLogin();
        Order order = this.hasOrder(orderId);
        if (id != order.getCustomerId() && id != order.getSellerId()) throw new LogicException(501, "没有查看权限");
        JSONObject json = (JSONObject) JSONObject.toJSON(order);
        json = goodUtil.addGoodMessage(json, "goodId");
        Address sellerAddress = addressMapper.selectByPrimaryKey(order.getSellerAddress());
        Address customerAddress = addressMapper.selectByPrimaryKey(order.getCustomerAddress());
        json.put("sellerAddress", (JSONObject) JSONObject.toJSON(sellerAddress));
        json.put("customerAddress", (JSONObject) JSONObject.toJSON(customerAddress));
        return json;
    }

    //检测是否存在该订单
    private Order hasOrder(Integer orderId) throws LogicException {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) throw new LogicException(501, "不存在该订单");
        return order;
    }

    //检测订单状态
    private boolean hasStatus(Short status) {
        if (status == INIT_ORDER) return true;
        if (status == FINISH_ORDER) return true;
        return false;
    }

}
