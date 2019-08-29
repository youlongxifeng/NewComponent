package com.component.preject.home.ui.fragment.homefirst;

import com.component.preject.common.base.mvp.BaseModel;
import com.component.preject.common.base.mvp.BasePresenter;
import com.component.preject.common.base.mvp.BaseView;
import com.component.preject.home.bean.HomeArticleData;
import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.bean.HomePageBannerModel;
import com.component.preject.home.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homefirst
 * @ClassName: HomePageFirstTabContract
 * @Author: xzg
 * @CreateDate: 2019-08-29 14:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 14:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public interface HomePageFirstTabContract {
    interface View extends BaseView {

        /**
         * 显示首页banner 数据
         *
         * @param bannerModelList
         */
        void showHomePageBanner(  List<HomePageBannerModel> bannerModelList);
        void showHomePageBannerFail(String errorMsg);

        void showTopArticleList(List<HomeArticleData> homeArticleDataList);

        void showHomeArticleList(HomeArticleListData homeArticleListData);

        //void showHomeLatestProjectList(HomeArticleListData homeArticleListData);

        //自动登录成功
        void showAutoLoginSuccess();

        //自动登录失败
        void showAutoLoginFail(String errorMsg);


    }

    interface Model extends BaseModel {

        /**
         * 获取首页Banner数据
         *
         * @return
         */
        Observable<ResponseBean<List<HomePageBannerModel>>> getHomePageBannerData();

        /**
         * 置顶文章
         *
         * @return
         */
        Observable<ResponseBean<List<HomeArticleData>>> homeTopArticleData();

        /**
         * @param pageNum 页码：拼接在链接上，从0开始
         * @return
         */
        Observable<ResponseBean<HomeArticleListData>> getHomeArticleListData(int pageNum );


    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 获取首页第一个 item 数据 (最新博文)
         * @param isRefreshData 是否刷新数据 true 刷新  false 正常加载
         */
        abstract   void getHomeFirstPageData(boolean isRefreshData);
        /**
         * 获取首页Banner数据
         *
         * @return
         */
        abstract void getHomePageBannerData();

        /**
         * 置顶文章
         *
         * @return
         */
         abstract void homeTopArticleData();

        /**
         * @param pageNum 页码：拼接在链接上，从0开始
         * @return
         */
       abstract void getHomeArticleListData(int pageNum);


    }
}
