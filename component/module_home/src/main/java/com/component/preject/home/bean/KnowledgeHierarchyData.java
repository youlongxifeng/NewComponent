package com.component.preject.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.bean
 * @ClassName: KnowledgeHierarchyData
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class KnowledgeHierarchyData implements Serializable {
    /**
     * children : []
     * courseId : 13
     * id : 60
     * name : Android Studio相关
     * order : 1000
     * parentChapterId : 150
     * userControlSetTop : false
     * visible : 1
     */

    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;
    private List<KnowledgeHierarchyData> children;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<KnowledgeHierarchyData> getChildren() {
        return children;
    }

    public void setChildren(List<KnowledgeHierarchyData> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "KnowledgeHierarchyData{" +
                "courseId=" + courseId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", parentChapterId=" + parentChapterId +
                ", userControlSetTop=" + userControlSetTop +
                ", visible=" + visible +
                ", children=" + children +
                '}';
    }
}
