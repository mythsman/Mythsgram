package com.mythsman.service;

import com.mythsman.dao.CommentDao;
import com.mythsman.dao.LoginTicketDao;
import com.mythsman.dao.PostDao;
import com.mythsman.dao.UserDao;
import com.mythsman.model.Comment;
import com.mythsman.model.LoginTicket;
import com.mythsman.model.Post;
import com.mythsman.model.User;
import com.mythsman.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by myths on 17-5-4.
 */
@Service
public class PostService {
    private static final Logger logger = Logger.getLogger(PostService.class);

    @Autowired
    UserComponent userComponent;

    @Autowired
    PostDao postDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    UserDao userDao;

    public Map<String,List<Map<String ,Object>>> getPostViews(){
        List<Map<String,Object>> posts=new ArrayList<>();

        for(Post post:postDao.selectPostsByUid(userComponent.getUser().getId())){
            Map<String,Object>postItem=new HashMap<>();
            postItem.put("user",userDao.selectById(post.getUid()));
            List<Map<String,Object>> comments=new ArrayList<>() ;
            for(Comment comment:commentDao.selectCommentsByPostId(post.getId())){
                Map<String ,Object> commentItem=new HashMap<>();
                commentItem.put("user",userDao.selectById(comment.getUid()));
                commentItem.put("comment",comment);
                comments.add(commentItem);
            }
            postItem.put("comments",comments);
            postItem.put("post",post);
            postItem.put("user",userComponent.getUser());
            posts.add(postItem);
        }

        Map<String,List<Map<String ,Object>>> result=new HashMap<>();
        result.put("posts",posts);
        return result;
    }


}
