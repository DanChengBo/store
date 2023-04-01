package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Intellij IDEA.
 * User: 17129
 * Date: 2023/2/13
 */
//@SpringBootTest:表示标准当前的类是一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith：表示启动这个单元测试类(单元测试类是不能够运行的),需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTest {
    //idea有检测的功能，接口是不能够直接创建Bean的(动态代理技术类解决)
    @Autowired(required = false)
    private UserMapper userMapper;
    /*
    * 单元测试方法：就可以单独独立运行，不用启动整个项目，可以做单元测试，提升了代码的测试效率
    * 1.必须被@Test注解修饰
    * 2.返回值类型必须是void
    * 3.方法的参数列表不指定任何类型
    * 4.方法的访问修饰福必须是public*/
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows=userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void findByUsername(){
        System.out.println(userMapper.findByUsername("tim"));
    }

}














































