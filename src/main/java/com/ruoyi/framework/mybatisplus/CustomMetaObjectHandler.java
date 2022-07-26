package com.ruoyi.framework.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.enums.Bool;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 自定义元对象处理程序
 *
 * @author dair
 * @date 2022年07月04日 15:58
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("updateTime", now, metaObject);
        LoginUser user = SecurityUtils.getLoginUser();
        if (Objects.nonNull(user)){
            this.setFieldValByName("createBy", user.getUsername(), metaObject);
            this.setFieldValByName("updateBy", user.getUsername(), metaObject);
        }

        this.setFieldValByName("delFlag", Bool.NO.s(), metaObject);

    }

    /**
     * 更新填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        LoginUser user = SecurityUtils.getLoginUser();
        if (Objects.nonNull(user)){
            this.setFieldValByName("updateBy", user.getUsername(), metaObject);
        }
    }
}
