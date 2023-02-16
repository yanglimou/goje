package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 返回数据的入口controller
public class UserController {

    // 注入访问对象
    @Autowired
    private UserMapper userMapper;

    // 查询总数
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int count() {
        return userMapper.count();
    }

    // 查询全部数据
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return userMapper.findAll();
    }

    // 查询单个
    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public User findOne(@RequestBody User user) {
        return userMapper.findOne(user);
    }

    // 保存用户 0=失败，1=成功
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public int save(@RequestBody User user) {
        try {
            userMapper.save(user);
            return 1;
        } catch (Exception e) {
            // 抛异常代表保存失败
            e.printStackTrace();
            return 0;
        }
    }


    // 保存用户 0=失败，1=成功
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(@RequestBody User user) {
        try {
            userMapper.update(user);
            return 1;
        } catch (Exception e) {
            // 抛异常代表保存失败
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public int delete(@RequestBody User user){
        try {
            userMapper.delete(user);
            return 1;
        } catch (Exception e) {
            // 抛异常代表保存失败
            e.printStackTrace();
            return 0;
        }
    }

}
