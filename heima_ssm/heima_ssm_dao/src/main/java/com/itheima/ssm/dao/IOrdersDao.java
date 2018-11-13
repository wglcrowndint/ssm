package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
}
