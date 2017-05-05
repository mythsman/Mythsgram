package com.mythsman.dao;

import com.mythsman.model.User;
import org.apache.ibatis.annotations.*;
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

    @Update({"update `user` set website=#{website} , email=#{email} ,phone=#{phone} ,gender=#{gender},biography=#{biography} where id=#{id}"})
    void updateProfile(User user);

    @Insert({"update `user` set salt=#{salt},password=#{password} where id=#{id}"})
    void updatePassword(@Param("id")int id, @Param("salt") String salt, @Param("password") String password);

}
