package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;


/**
 * Created by Intellij IDEA.
 * User: 17129
 * Date: 2023/2/14
 * 用户模块业务层的实现类
 */
//@Service注解：将当前类的对象交给speing来管理，自动创建对象以及对象的维护
@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username=user.getUsername();
        //调用findByUsername（username）判断用户是否被注册
        User result=userMapper.findByUsername(username);
        //判断结果集是否为null，否则抛出用户名被占用的异常
        if (result != null) {
            //抛出异常
            throw  new UsernameDuplicatedException("用户名被占用");
        }
        //密码加密处理的实现：md5算法的形式
        //串+password+串 ---- MD5算法的加密
        //盐值+password+盐值 ---- 盐值就是一个随机的字符串
        String oldPassword=user.getPassword();
        //获取盐值(随机生存一个盐值)
        String salt=UUID.randomUUID().toString().toUpperCase();
        //盐值的记录
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理，忽略原有密码的强度提升了数据的安全性
        String md5Password=getMd5Password(oldPassword,salt);

        //将加密之后的密码重新补全设置到user对象中
        user.setPassword(md5Password);
        //补全数据：is_delete设置成0
        user.setIsDelete(0);
        //补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        //执行注册业务功能的实现(row==1)
        Integer rows=userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("用户在注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        // 根据用户名称来重新用户的数据是否存在，如果不在则抛异常
        User result=userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户的密码是否匹配
        //1. 先获取数据库中的加密之后的密码
        String oldPassword=result.getPassword();
        //2. 和用户的传递过来的密码进行比较
        //2.1 先获取盐值，上一次在注册所自动生存的盐值
        String salt=result.getSalt();
        //2.2 将用户的密码按照相同的md5算法进行加密
        String newMd5Password=getMd5Password(password,salt);
        //3.将密码进行比较
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断is_delete字段的值是否为1表示标记被删除
        if (result.getIsDelete()==1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //这里不返回全部的用户数据是用来提升系统的性能
        User user=new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        //将当前用户的数据返回，返回的数据是为了辅助其他页面做数据展示使用(uid,username,avatar )
        return user;
    }

    //定义一个MD5算法的加密
    private String getMd5Password(String password,String salt){
        //md5加密算法的调用
        for (int i = 0; i < 3; i++) {
            //md5加密算法的调用(进行三次加密)
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        //返回加密后的password
        return password;
    }
}



















































































