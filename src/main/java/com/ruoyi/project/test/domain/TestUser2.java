package com.ruoyi.project.test.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 用户对象 test_user
 *
 * @author ruoyi
 * @date 2022-07-24
 */
@Data
public class TestUser2 extends BaseModel<TestUser2> {


    @Excel(name = "主键")
    private Long id;

    @Excel(name = "名称")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年龄", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birth;

    @Excel(name = "性别")
    private String gender;

}
