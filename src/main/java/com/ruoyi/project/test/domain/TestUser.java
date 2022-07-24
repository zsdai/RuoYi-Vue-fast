package com.ruoyi.project.test.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户对象 test_user
 * 
 * @author ruoyi
 * @date 2022-07-24
 */
public class TestUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 年龄 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年龄", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birth;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setBirth(Date birth) 
    {
        this.birth = birth;
    }

    public Date getBirth() 
    {
        return birth;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("birth", getBirth())
            .append("gender", getGender())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
