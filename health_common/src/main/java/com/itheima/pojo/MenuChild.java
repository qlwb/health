package com.itheima.pojo;


import java.io.Serializable;
import java.util.List;



/**
 * @Author: dxw
 * @Date: 2019/11/25 11:39
 */
//封装的菜单子类
public class MenuChild implements Serializable {
    private String path;
    private String title;
    private String icon;
    private String linkUrl;
    private List<MenuChild> children;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public List<MenuChild> getChildren() {
        return children;
    }

    public void setChildren(List<MenuChild> children) {
        this.children = children;
    }
}
