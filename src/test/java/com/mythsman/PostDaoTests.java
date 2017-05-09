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
    public void tests() throws Exception {
        postDao.addPost(1, "https://www.baidu.com","this is title.");
        Post post = postDao.selectPostById(1);
        Assert.assertEquals(post.getSrc(), "https://www.baidu.com");
        postDao.updateValid(1, 0);
        Assert.assertEquals(postDao.selectPostById(1).getValid(), 0);
        List<Post> posts=postDao.selectPostsByUid(1);
        Assert.assertNotNull(posts);
        for(Post postx :posts){
            Assert.assertEquals(postx.getUid(),1);
        }
    }


}
