package com.mythsman;

import com.mythsman.dao.CommentDao;
import com.mythsman.dao.PostDao;
import com.mythsman.model.Comment;
import com.mythsman.model.Post;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/comment.sql")
public class CommentDaoTests {
    private static final Logger logger = Logger.getLogger(CommentDaoTests.class);
    @Autowired
    CommentDao commentDao;

    @Test
    public void tests(){
        commentDao.addComment(1,1,"233");
        Comment comment=commentDao.selectCommentsByPostId(1).get(0);
        Assert.assertEquals(comment.getContent(),"233");
        commentDao.updateValid(comment.getId(),0);
        Assert.assertEquals(commentDao.selectCommentsByPostId(1).get(0).getValid(),0);
    }
}
