package com.ruoyi.project.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.test.domain.TestUser2;
import com.ruoyi.project.test.mapper.TestUser2Mapper;
import com.ruoyi.project.test.service.ITestUser2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-24
 */
@Service
public class TestUser2ServiceImpl extends ServiceImpl<TestUser2Mapper, TestUser2> implements ITestUser2Service {

    @Autowired
    private TestUser2Mapper testUser2Mapper;
}
