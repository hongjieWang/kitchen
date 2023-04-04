package com.kitchen.system.domain;


import com.kitchen.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 埋点配置对象 key_config
 *
 * @author wanghongjie
 * @date 2023-04-04
 */
public class KeyConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * key
     */
    private String keyValues;

    /**
     * Key1
     */
    private String keyOne;

    /**
     * key2
     */
    private String keyTwo;

    /**
     * key3
     */
    private String keyThird;

    /**
     * 名称
     */
    private String name;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 部门状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setKeyValues(String keyValues) {
        this.keyValues = keyValues;
    }

    public String getKeyValues() {
        return keyValues;
    }

    public void setKeyOne(String keyOne) {
        this.keyOne = keyOne;
    }

    public String getKeyOne() {
        return keyOne;
    }

    public void setKeyTwo(String keyTwo) {
        this.keyTwo = keyTwo;
    }

    public String getKeyTwo() {
        return keyTwo;
    }

    public void setKeyThird(String keyThird) {
        this.keyThird = keyThird;
    }

    public String getKeyThird() {
        return keyThird;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("keyValues", getKeyValues())
                .append("keyOne", getKeyOne())
                .append("keyTwo", getKeyTwo())
                .append("keyThird", getKeyThird())
                .append("name", getName())
                .append("appName", getAppName())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
