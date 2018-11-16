package com.itheima.ssm.dao;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
public interface IUserDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "roles",column = "id",
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    @Select("select * from users where username=#{username}")
    public UserInfo findByUsername(String username) throws Exception;
}




