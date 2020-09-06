package com.stcode.bootstrap.service;

import com.stcode.bootstrap.domain.User;
import com.stcode.bootstrap.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private UserMapper userMapper;

    public List<User> list (){
        return userMapper.selectByExample(null);
    }

}
