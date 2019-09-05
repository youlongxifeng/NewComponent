package com.component.preject.home.bean;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.bean
 * @ClassName: NaviBean
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NaviBean extends BaseBean {
    private int cid;
    private String name;
    private List<ArticleBean> articles;

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

    public List<ArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBean> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "NaviBean{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                '}';
    }
}
