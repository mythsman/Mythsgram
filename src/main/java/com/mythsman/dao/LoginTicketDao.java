package com.mythsman.dao;

import com.mythsman.model.LoginTicket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * Created by myths on 5/2/17.
 */
@Mapper
@Service
public interface LoginTicketDao {

    @Insert({"insert into `login_ticket` (uid,ticket,expire,valid) values(#{uid},#{ticket},#{expire},#{valid})"})
    int addTicket(LoginTicket loginTicket);

    @Select({"select * from `login_ticket` where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    @Update({"update `login_ticket` set valid=#{valid} where ticket=#{ticket}"})
    void updateValid(@Param("ticket")String ticket,@Param("valid") int valid);
}
