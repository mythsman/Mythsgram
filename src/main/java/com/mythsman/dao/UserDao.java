package com.mythsman.dao;

import com.mythsman.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * Created by myths on 5/2/17.
 */
@Mapper
@Service
public interface UserDao {

    @Insert({"insert into `user` ( name,salt,password ) values(#{name},#{salt},#{password})"})
    int addUser(@Param("name") String name, @Param("salt") String salt, @Param("password") String password);

    @Select({"select * from `user` where id=#{id}"})
    User selectById(@Param("id") int id);

    @Select({"select * from `user` where name=#{name}"})
    User selectByName(@Param("name") String name);

}
