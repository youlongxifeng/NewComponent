package com.component.preject.home.bean;

import java.io.Serializable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.bean
 * @ClassName: HomePageBannerModel
 * @Author: xzg
 * @CreateDate: 2019-08-29 14:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 14:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomePageBannerModel implements Serializable {

    /**
     * desc :
     * id : 6
     * imagePath : http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png
     * isVisible : 1
     * order : 1
     * title : 我们新增了一个常用导航Tab~
     * type : 0
     * url : http://www.wanandroid.com/navi
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HomePageBannerModel{" +
                "desc='" + desc + '\'' +
                ", id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", isVisible=" + isVisible +
                ", order=" + order +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
