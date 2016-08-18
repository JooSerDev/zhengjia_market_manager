package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class CoreItemType implements Serializable {
    /**
     * item_item_type.typeId
     * @ibatorgenerated 2016-08-16 17:16:44
     */
    private Integer typeid;

    /**
     * item_item_type.name
     * @ibatorgenerated 2016-08-16 17:16:44
     */
    private String name;

    /**
     * item_item_type.sort
     * @ibatorgenerated 2016-08-16 17:16:44
     */
    private Integer sort;

    /**
     * item_item_type.createTime
     * @ibatorgenerated 2016-08-16 17:16:44
     */
    private Date createtime;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}