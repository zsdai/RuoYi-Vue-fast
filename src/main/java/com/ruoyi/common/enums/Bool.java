package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;

/**
 * 是否-枚举
 *
 * @author dairdai
 * @date 2022/07/04
 */
@AllArgsConstructor
public enum Bool {
    /**
     * YES or NO
     */
    YES("1", 1, true, "是"),
    NO("0", 0, false,"否");

    private String s;
    private Integer i;
    private Boolean b;
    private String desc;

    public String s() {
        return s;
    }
    public Integer i() {
        return i;
    }
    public Boolean b() {
        return b;
    }
    public String desc() {
        return desc;
    }
}
