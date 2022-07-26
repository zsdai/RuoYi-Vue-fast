package com.ruoyi.project.test.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户对象 test_user
 *
 * @author ruoyi
 * @date 2022-07-24
 */

@ApiModel("用户对象 test_user")
@TableName("test_user2")
@Getter
@Setter
public class TestUser2 extends BaseModel<TestUser2> {

    @Override
    public Serializable pkVal() {
        return id;
    }

    @ApiModelProperty("主键")
    @Excel(name = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("名称")
    @Excel(name = "名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("年龄")
    @Excel(name = "年龄", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "birth")
    private Date birth;

    @ApiModelProperty("性别")
    @Excel(name = "性别")
    @TableField(value = "gender")
    private String gender;

}
