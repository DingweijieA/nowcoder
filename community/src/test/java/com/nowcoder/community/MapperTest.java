package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);
         user = userMapper.selectByName("liubei");
        System.out.println(user);
    }

    @Test
    public void testInsetUser(){
        User user = new User();
        user.setUsername("ding");
        user.setPassword("1234");
        user.setEmail("sadsah@qq.com");
        user.setCreateTime(new Date());

        int rows =userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser(){
        int rows =userMapper.updateStatus(150,1);
        User user = userMapper.selectById(150);
        System.out.println(rows);
        System.out.println(user.getStatus());
    }
    @Test
    public void testSelevtPosts(){

        List<DiscussPost>  list =discussPostMapper.selectDiscussPosts(101,0,10);
        for (DiscussPost post :
                list) {
            System.out.println(post);

        }
        int rows =discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);

    }






}
