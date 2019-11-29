package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.pojo.Setmeal;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/22 15:18
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;

    //体检预约
    public Result order(Map map) throws Exception {
        //获取预约时间
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);//字符串日期转换成yyyy-MM-dd的日期类型
        //查询所选日期能不能进行体检预约
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if (orderSetting == null) {
            //所选日期不能进行体检预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //检查预约日期是否预约已满
        int number = orderSetting.getNumber();
        //可预约人数
        int reservations = orderSetting.getReservations();
        //已预约人数
        if (reservations >= number) {
            //预约已满，不能预约
            return new Result(false, MessageConstant.ORDER_FULL);
        }
        //检查当前用户是否为会员，根据手机号判断
        String telephone = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(telephone);
        //防止重复预约
        if (member != null) {
            Integer memberId = member.getId();
            int setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(memberId, date, null, null, setmealId);
            //查询某日期是否存在某会员预约了某套餐
            List<Order> list = orderDao.findByCondition(order);
            if (list != null && list.size() > 0) {
                //已经完成了预约，不能重复预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }
        //可以预约，设置预约人数加一
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);
        if (member == null) {
            //当前用户不是会员，需要添加到会员表
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }
        //保存预约信息到预约表
        Order order = new Order(member.getId(), date, (String) map.get("orderType"), Order.ORDERSTATUS_NO, Integer.parseInt((String) map.get("setmealId")));
        orderDao.add(order);
        return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
    }

    //根据id查询预约信息，包括套餐信息和会员信息
    public Map findById(Integer id) throws Exception {
        Map map = orderDao.findById4Detail(id);
        if (map != null) {
            //处理日期格式
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate", DateUtils.parseDate2String(orderDate));//日期转换 Date -> String
        }
        return map;
    }

    //分页
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage, pageSize);
        Page<Map<String,Object>> page= orderDao.selectByCondition(queryString);
        for (Map<String, Object> map : page) {
            String orderstatus = (String) map.get("orderstatus");
            Date orderdate = (Date) map.get("orderdate");
            try {
                String s = DateUtils.parseDate2String(orderdate);
                map.put("orderdate",s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(orderstatus.equals("已到诊")){
                map.put("isTrue",true);
            }else{
                map.put("isTrue",false);
            }
        }
        //封装返回的结果对象
        return new PageResult(page.getTotal(), page.getResult());
    }

    //确认到诊
    public void confirmOrder(Integer id) {
        orderDao.confirmOrderStatus(id);
    }

    //电话预约
    public Result orderByAdmin(Map map, Integer[] setmealIds) throws Exception {
        //获取预约时间
        String orderDate = (String) map.get("orderDate");
        System.out.println(orderDate);
        Date date = DateUtils.parseString2Date(orderDate);//字符串日期转换成yyyy-MM-dd的日期类型
        //查询所选日期能不能进行体检预约
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if (orderSetting == null) {
            //所选日期不能进行体检预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //检查预约日期是否预约已满
        int number = orderSetting.getNumber();
        //可预约人数
        int reservations = orderSetting.getReservations();
        //已预约人数
        if (reservations >= number) {
            //预约已满，不能预约
            return new Result(false, MessageConstant.ORDER_FULL);
        }
        //检查当前用户是否为会员，根据手机号判断
        String telephone = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(telephone);
        //防止重复预约
        if (member != null) {
            Integer memberId = member.getId();
            int setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(memberId, date, null, null, setmealId);
            //查询某日期是否存在某会员预约了某套餐
            List<Order> list = orderDao.findByCondition(order);
            if (list != null && list.size() > 0) {
                //已经完成了预约，不能重复预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }
        //可以预约，设置预约人数加一
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);
        if (member == null) {
            //当前用户不是会员，需要添加到会员表
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }
        if(setmealIds!=null&&setmealIds.length>0){
            for (Integer setmealId : setmealIds) {
                //保存预约信息到预约表
                Order order = new Order(member.getId(), date, (String) map.get("orderType"), Order.ORDERSTATUS_NO, setmealId);
                orderDao.add(order);
            }
        }

        return new Result(true, MessageConstant.ORDER_SUCCESS);
    }

    //后台查找订单
    public Map findByIdAdmin(Integer id) {
        return orderDao.findByIdAdmin(id);
    }

    //根据订单id查询套餐id
    public List<Integer> setMealIdByOrderId(Integer id) {
        return orderDao.findMealIdByOrderId(id);
    }

    //后台修改预约订单
    public void update(Map map, Integer[] setmealIds) throws Exception {
        Integer id = (Integer) map.get("id");
        map.put("orderType", Order.ORDERTYPE_TELEPHONE);
        orderDao.deleteById(id);
        orderByAdmin(map,setmealIds);
    }

    @Override
    public void delete(Integer id) {
        orderDao.deleteById(id);
    }

}

