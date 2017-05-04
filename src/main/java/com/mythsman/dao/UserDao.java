package com.mythsman.dao;

import com.mythsman.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by myths on 5/2/17.
 */
@Mapper
public interface UserDao {

    @Insert({"insert into user ( name,salt,password ) values(#{name},#{salt},#{password})"})
    int addUser(User user);

    @Select({"select * from user where id=#{id}"})
    User getUserById(int id);

    @Select({"select name,salt,password from user where id=#{id}"})
    User getLoginInfoById(int id);

}
