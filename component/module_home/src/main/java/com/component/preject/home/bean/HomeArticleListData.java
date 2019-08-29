package com.component.preject.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.bean
 * @ClassName: HomeArticleListData
 * @Author: xzg
 * @CreateDate: 2019-08-29 14:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 14:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeArticleListData implements Serializable {

    private int curPage;
    private List<HomeArticleData> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<HomeArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<HomeArticleData> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "HomeArticleListData{" +
                "curPage=" + curPage +
                ", datas=" + datas +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}
