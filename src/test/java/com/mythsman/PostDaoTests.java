package com.mythsman;

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
@Sql("/post.sql")
public class PostDaoTests {
    private static final Logger logger = Logger.getLogger(PostDaoTests.class);
    @Autowired
    PostDao postDao;

    @Test
    public void addPostTests() throws Exception {
        postDao.addPost(2, "https://www.baidu.com","this is title.");
        Post post = postDao.selectPostById(2);
        Assert.assertEquals(post.getSrc(), "https://www.baidu.com");
    }

    @Test
    public void selectPostByUidTests() throws Exception {
        postDao.selectPostById(1).getSrc();
        Assert.assertEquals(postDao.selectPostById(1).getSrc(), "/img/1.jpg");
    }

    @Test
    public void selectPostsByUidTests() {
        List<Post> posts=postDao.selectPostsByUid(1);
        Assert.assertNotNull(posts);
        for(Post post :posts){
            Assert.assertEquals(post.getUid(),1);
        }
    }

    @Test
    public void updateValidTests() throws Exception {
        postDao.updateValid(1, 0);
        Assert.assertEquals(postDao.selectPostById(1).getValid(), 0);
    }

    @Test
    public void updateLikesTests() throws Exception {
        postDao.updateLikes(1, 10);
        Assert.assertEquals(postDao.selectPostById(1).getLikes(), 10);
    }

}
