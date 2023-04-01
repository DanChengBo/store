package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Intellij IDEA.
 * User: 17129
 * Date: 2023/2/14
 */
//@Controller
@RestController     //@RestController=@ResponseBody+@Controller 组合注解
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired(required = false)
    private IUserService userService;

    /**
     * 约定大于配置：开发理念来完成，省略大量的配甚至注解的编写
     *1.接受数据方式：请求处理的参数列表设置为pojo类型来接受前端的数据，
     *  SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较，
     *  如果这两个名称相同，则将值注入到pojo类中对应的绍属性上
     */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        //创建相应结果的对象
        return new JsonResult<>(OK);
    }

    /**
     *2.接受数据方式：请求处理方法的参数列表设置为非pojo类型
     * SpringBoot会将请求的参数名和方法的参数名直接进行比较，如果名称相同则自动完成值的依赖注入
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username,String password){
        User data=userService.login(username,password);
        return new JsonResult<User>(OK,data);
    }
}
    /*  没有异常基类的原始代码
    //@ResponseBody           //表示此方法的相应结果一json格式进行数据的响应给发到前端
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        //创建相应结果的对象
        JsonResult<Void> result=new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("在注册时产生未知的异常");
        }
        return result;
    }*/

