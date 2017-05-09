package com.mythsman.service;

import com.mythsman.dao.CommentDao;
import com.mythsman.dao.PostDao;
import com.mythsman.dao.UserDao;
import com.mythsman.model.Comment;
import com.mythsman.model.Post;
import com.mythsman.model.User;
import com.mythsman.util.JedisAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

/**
 * Created by myths on 17-5-4.
 */
@Service
public class PostService implements InitializingBean{
    private static final Logger logger = Logger.getLogger(PostService.class);
    private String classPath=null;

    @Autowired
    UserComponent userComponent;

    @Autowired
    PostDao postDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    UserDao userDao;

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    JedisAdapter jedisAdapter;

    public Map<String,List<Map<String ,Object>>> getPostsAndComments(){

        List<Integer>followList= userService.getAllFollows();
        followList.add(userComponent.getUser().getId());

        List<Map<String,Object>> posts=new ArrayList<>();
        for(int uid:followList){
            for(Post post:postDao.selectPostsByUid(uid)){
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
                postItem.put("user",userDao.selectById(uid));
                if(likeService.isLike(post.getId(),userComponent.getUser().getId())){
                    postItem.put("star",true);
                }else{
                    postItem.put("star",false);
                }
                posts.add(postItem);
            }
        }


        Map<String,List<Map<String ,Object>>> result=new HashMap<>();
        result.put("posts",posts);
        return result;
    }

    public Map<String,Object> getPosts(){
        User user=userComponent.getUser();
        List<Post>list=postDao.selectPostsByUid(user.getId());
        Map<String,Object> map=new HashMap<>();
        map.put("posts",list);
        return map;

    }


    public Map<String,Object> publish(MultipartFile file, String title){
        Map<String,Object> map=new HashMap<>();
        if(title.trim().length()==0){
            map.put("code","0");
            map.put("msg","Please input your title .");
            return map;
        }
        if(file==null){
            map.put("code","0");
            map.put("msg","Input file doesn't exist !");
            return map;
        }

        Calendar cal = Calendar.getInstance();

        File dir= new File(classPath+"uploads/"+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1));
        if(!dir.exists()){
            dir.mkdirs();
        }


        BufferedImage im=null;
        String name=UUID.randomUUID().toString();
        try {
            im = ImageIO.read(file.getInputStream());
            ImageIO.write(im,"png",new File(dir.getPath()+File.separator+name+".png"));
        }catch (Exception e) {
            e.printStackTrace();
            map.put("code","0");
            map.put("msg","Format Error !");
            return map;
        }finally {
            if(im!=null){
                im.flush();
            }
        }
        String src="uploads/"+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+File.separator+name+".png";
        User user=userComponent.getUser();
        user.setPosts(user.getPosts()+1);
        userDao.updateProfile(user);
        postDao.addPost(user.getId(),src,title);
        map.put("code","1");
        map.put("msg","Upload successfully !");
        return map;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Resource resource=new ClassPathResource("");
        try{
            classPath=resource.getURL().getPath();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
