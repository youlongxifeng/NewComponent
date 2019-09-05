package com.component.preject.home.bean;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.bean
 * @ClassName: NavigationListData
 * @Author: xzg
 * @CreateDate: 2019-09-05 14:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 14:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NavigationListData extends BaseBean {
    private List<HomeArticleData> articles;
    private int cid;
    private String name;

    public List<HomeArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<HomeArticleData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NavigationListData{" +
                "articles=" + articles +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
