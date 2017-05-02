package com.mythsman.dao;

import com.mythsman.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * Created by myths on 5/2/17.
 */
@Service
@Mapper
public interface UserDao {
    String TABLE_NAME="user";
    String INSERT_FIELDS="id,name";

    @Insert({"insert into ",TABLE_NAME," (",INSERT_FIELDS,") values(#{id},#{name})"})
    int addUser(User user);
}
