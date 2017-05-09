package com.mythsman.dao;

import com.mythsman.model.LoginTicket;
import com.mythsman.model.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by myths on 5/2/17.
 */
@Mapper
@Service
public interface PostDao {

    @Insert({"insert into post(uid,src,title) values (#{uid} ,#{src},#{title})"})
    void addPost(@Param("uid") int uid,@Param("src") String src,@Param("title")String title);

    @Update({"update post set valid=#{valid} where id=#{id}"})
    void updateValid(@Param("id") int id,@Param("valid") int valid);

    @Select({"select * from post where uid=#{uid} order by date desc"})
    List<Post> selectPostsByUid(@Param("uid") int uid);

    @Select({"select * from post where id=#{id} order by date desc"})
    Post selectPostById(@Param("id") int id);

    @Update({"update post set likes=#{likes} where id=#{id}"})
    void updateLikes(@Param("id") int id,@Param("likes") int likes);

}
