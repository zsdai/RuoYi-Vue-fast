package com.ruoyi.project.test.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.test.mapper.TestUserMapper;
import com.ruoyi.project.test.domain.TestUser;
import com.ruoyi.project.test.service.ITestUserService;

/**
 * 用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-24
 */
@Service
public class TestUserServiceImpl implements ITestUserService 
{
    @Autowired
    private TestUserMapper testUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public TestUser selectTestUserById(Long id)
    {
        return testUserMapper.selectTestUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param testUser 用户
     * @return 用户
     */
    @Override
    public List<TestUser> selectTestUserList(TestUser testUser)
    {
        return testUserMapper.selectTestUserList(testUser);
    }

    /**
     * 新增用户
     * 
     * @param testUser 用户
     * @return 结果
     */
    @Override
    public int insertTestUser(TestUser testUser)
    {
        testUser.setCreateTime(DateUtils.getNowDate());
        return testUserMapper.insertTestUser(testUser);
    }

    /**
     * 修改用户
     * 
     * @param testUser 用户
     * @return 结果
     */
    @Override
    public int updateTestUser(TestUser testUser)
    {
        testUser.setUpdateTime(DateUtils.getNowDate());
        return testUserMapper.updateTestUser(testUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteTestUserByIds(Long[] ids)
    {
        return testUserMapper.deleteTestUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteTestUserById(Long id)
    {
        return testUserMapper.deleteTestUserById(id);
    }
}
