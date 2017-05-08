package com.mythsman.dao;

import com.mythsman.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by myths on 5/2/17.
 */
@Mapper
@Service
public interface CommentDao {

    @Insert({"insert into comment(uid,post_id, content) values(#{uid} ,#{post_id},#{content}) "})
    void addComment(@Param("uid")int uid,@Param("post_id") int post_id,@Param("content")String content);

    @Select({"select * from comment where post_id=#{post_id} order by date"})
    List<Comment> selectCommentsByPostId(@Param("post_id")int post_id);

    @Update({"update comment set valid=#{valid} where id=#{id}"})
    void updateValid(@Param("id") int id,@Param("valid") int valid);

}
