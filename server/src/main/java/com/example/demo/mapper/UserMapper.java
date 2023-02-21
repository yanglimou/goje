package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.entity.UserQueryParam;
import com.example.demo.entity.UserVo;

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

    void delete(String code);

    User findOne(String code);

    int getPageCount(UserQueryParam userQueryParam);

    List<UserVo> getPage(UserQueryParam userQueryParam);
}
