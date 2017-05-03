package com.mythsman.dao;

import com.mythsman.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by myths on 5/2/17.
 */
@Mapper
public interface UserDao {
    String TABLE_NAME="user";
    String INSERT_FIELDS="name,salt,password";

    @Insert({"insert into ",TABLE_NAME," (",INSERT_FIELDS,") values(#{name},#{salt},#{password})"})
    int addUser(User user);
}
