package com.ruoyi.framework.web.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author dair
 * @date 2022年07月25日 19:44
 */
@Getter
@Setter
public class BaseModel<T extends Model<T>> extends Model<T> {

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected Date createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected String createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    protected Date updateTime;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改者")
    protected String updateBy;

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }

    @Override
    public boolean insert() {
        this.updateTime = new Date();
        this.createTime = this.updateTime;
        this.updateBy = SecurityUtils.getLoginUser().getUsername();
        this.createBy = this.updateBy;
        return super.insert();
    }

    @Override
    public boolean updateById() {
        this.updateTime = new Date();
        this.updateBy = SecurityUtils.getLoginUser().getUsername();
        return super.updateById();
    }

}
