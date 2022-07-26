package com.ruoyi.project.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.MybatisPlusUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.test.domain.TestUser2;
import com.ruoyi.project.test.service.ITestUser2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 用户Controller
 *
 * @author ruoyi
 * @date 2022-07-24
 */
@RestController
@RequestMapping("/test/user")
public class TestUser2Controller extends BaseController {
    @Autowired
    private ITestUser2Service testUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('test:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestUser2 testUser2) {
        startPage();
        QueryWrapper<TestUser2> qr = MybatisPlusUtil.entity2Wrapper(testUser2);
        List<TestUser2> list = testUserService.getBaseMapper().selectList(null);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('test:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TestUser2 testUser2) {
        List<TestUser2> list = testUserService.getBaseMapper().selectList(MybatisPlusUtil.entity2Wrapper(testUser2));
        ExcelUtil<TestUser2> util = new ExcelUtil<TestUser2>(TestUser2.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('test:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(testUserService.getById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('test:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestUser2 testUser2) {
        //选择性插入，忽略null值
        return toAjax(testUser2.insert());
        //return toAjax(testUserService.saveOrUpdate(testUser2));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('test:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestUser2 testUser2) {
        // updateById 是选择性更新；会忽略null值
        return toAjax(testUser2.updateById());
        //return toAjax(testUserService.saveOrUpdate(testUser2));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('test:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(testUserService.removeBatchByIds(Arrays.asList(ids), 500));
    }
}
