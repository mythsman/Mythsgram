package com.mythsman;

import com.mythsman.dao.CommentDao;
import com.mythsman.dao.PostDao;
import com.mythsman.model.Post;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
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
    public void addCommentTests() throws Exception {
        commentDao.addComment(2,2,"hahaha");
        Assert.assertEquals(commentDao.selectCommentsByPostId(2).get(0).getContent(),"hahaha");
    }

    @Test
    public void selectCommentsByPidTests() throws Exception {
        Assert.assertEquals(commentDao.selectCommentsByPostId(1).get(0).getContent(),"hahahaha");
    }

    @Test
    public void updateValidTests() throws Exception {
        commentDao.updateValid(1,0);
        Assert.assertEquals(commentDao.selectCommentsByPostId(1).get(0).getValid(),0);
    }
}
