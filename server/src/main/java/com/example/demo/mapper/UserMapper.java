package com.example.demo.mapper;

import com.example.demo.entity.User;

import java.util.List;

/**
 * 访问user表的mapper
 */
public interface UserMapper {
    //  查询表总数
    int count();

    List<User> findAll();

    void save(User user);

    void update(User user);

    void delete(User user);

    User findOne(User user);
}
