package com.ruoyi.project.test.mapper;

import java.util.List;
import com.ruoyi.project.test.domain.TestUser;

/**
 * 用户Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-24
 */
public interface TestUserMapper 
{
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public TestUser selectTestUserById(Long id);

    /**
     * 查询用户列表
     * 
     * @param testUser 用户
     * @return 用户集合
     */
    public List<TestUser> selectTestUserList(TestUser testUser);

    /**
     * 新增用户
     * 
     * @param testUser 用户
     * @return 结果
     */
    public int insertTestUser(TestUser testUser);

    /**
     * 修改用户
     * 
     * @param testUser 用户
     * @return 结果
     */
    public int updateTestUser(TestUser testUser);

    /**
     * 删除用户
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteTestUserById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestUserByIds(Long[] ids);
}
