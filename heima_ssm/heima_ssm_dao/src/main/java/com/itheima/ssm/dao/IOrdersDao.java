package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    /*
           CREATE TABLE orders(
                id varchar2(32) default SYS_GUID() PRIMARY KEY,
                orderNum VARCHAR2(20) NOT NULL UNIQUE,
                orderTime timestamp,
                peopleCount INT,
                orderDesc VARCHAR2(500),
                payType INT,
                orderStatus INT,
                productId varchar2(32),
                memberId varchar2(32),
                FOREIGN KEY (productId) REFERENCES product(id),
                FOREIGN KEY (memberId) REFERENCES member(id)
          )

          productId 外键，指向product表, 通过com.itheima.ssm.dao.IProductDao.findById获取Product对象
     */
    @Select("select * from orders")
    @Results({
              @Result(property = "product",column = "productId", one = @One(select = "com.itheima.ssm.dao.IProductDao.findById"))
             })
    public List<Orders> findAll() throws Exception;


    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "product",column = "productId", one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;




}
