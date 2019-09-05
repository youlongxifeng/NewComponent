package com.component.preject.home.ui.fragment.homefirst;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.common.utils.ToastUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;
import com.component.preject.home.bean.HomeArticleData;
import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.bean.HomePageBannerModel;
import com.component.preject.home.constant.HomeConstants;
import com.component.preject.home.ui.fragment.homefirst.adapter.HomePageAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homefirst
 * @ClassName: HomeFirstTabFragment
 * @Author: xzg
 * @CreateDate: 2019-08-29 14:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 14:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （首页内容）
 */
public class HomeFirstTabFragment extends BaseMvpFragment<HomeFirstTabPresenter> implements HomePageFirstTabContract.View {
    private final static String TAG = HomeFirstTabFragment.class.getSimpleName();


    @BindView(R2.id.rc_recovery_box)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipe_recovery_box)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Banner mBanner;
    /**
     * 下一页请求页数
     */
    private int mNextRequestPage = 1;

    List<HomeArticleData> datas=new ArrayList<>();

    List<HomePageBannerModel> mBannerModelList=new ArrayList<>();

    private HomePageAdapter mAdapter;

    public static HomeFirstTabFragment newInstance(String tabName) {
        Bundle args = new Bundle();
        args.putString(HomeConstants.TAG_TAB_NAME, tabName);
        HomeFirstTabFragment fragment = new HomeFirstTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomeFirstTabPresenter createPresenter() {
        return new HomeFirstTabPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_first_tab_layout;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void onViewCreated(View view) {
        initRefreshLayout();
        initRecyclerView();
        initAdapter();//初始化适配器
    }

    @Override
    protected void initEventAndData() {
        refresh();
        showBannerData(mBannerModelList);
    }
    private void mHeaderGroup1() {
        //add head banner
        LinearLayout mHeaderGroup = (LinearLayout) getLayoutInflater().inflate(R.layout.home_head_banner_item, null);
        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
        mHeaderGroup.removeView(mBanner);
        mAdapter.setHeaderView(mBanner, 1);
    }
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private List<Integer> bannerIdList;
    private void showBannerData(List<HomePageBannerModel> bannerDataList) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        bannerIdList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (HomePageBannerModel bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
            bannerIdList.add(bannerData.getId());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new BannerGlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setOnBannerListener(new OnBannerListener() {
                                        @Override
                                        public void OnBannerClick(int position) {

                                        }
                                    }

        );
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    boolean oneCount=true;
    @Override
    public void showHomePageBanner(  List<HomePageBannerModel> bannerModelList) {
        mBannerModelList.clear();
        mBannerModelList.addAll(bannerModelList);
        if(oneCount){
            showBannerData(mBannerModelList);
            oneCount=false;
        }else {
            mBanner.update(mBannerModelList);
        }

    }

    @Override
    public void showHomePageBannerFail(String errorMsg) {

    }

    @Override
    public void showTopArticleList(List<HomeArticleData> homeArticleDataList) {

    }

    @Override
    public void showHomeArticleList(  HomeArticleListData homeArticleListData) {
        boolean isRefresh = mNextRequestPage == 1;
        mSwipeRefreshLayout.setRefreshing(false);
        //添加新的数据
        setData(isRefresh, homeArticleListData.getDatas());
    }

    @Override
    public void showAutoLoginSuccess() {

    }

    @Override
    public void showAutoLoginFail(String errorMsg) {

    }

    /**
     * 初始化RefreshLayout
     */
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i(TAG, "initRefreshLayout=============");
                refresh();
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter=new HomePageAdapter(R.layout.item_article_cardview_layout,datas);
        mRecyclerView.setAdapter(mAdapter);
        //加载显示动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //加载更多
                loadMore();
            }
        },mRecyclerView);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //点击子控件
            }
        });
        mHeaderGroup1();
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        mNextRequestPage = 1;
        mSwipeRefreshLayout.setRefreshing(true);
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.getHomePageBannerData();
        mPresenter.getHomeArticleListData(mNextRequestPage);
    }
    /**
     * 加载更多数据
     */
    private void loadMore() {
        mPresenter.getHomeArticleListData(mNextRequestPage);
    }
    /**
     * 更新数据集合
     *
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<HomeArticleData> data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            datas.clear();
            datas.addAll(data);
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                datas.addAll(data);
                mAdapter.addData(data);
            }
        }
        LogUtils.i(TAG,"setData==="+ (!isRefresh && (size < HomeConstants.PAGE_SIZE)));
        if ((size < HomeConstants.PAGE_SIZE)) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            if(!isRefresh){
                ToastUtils.getInstance().show(getActivity(),getString(R.string.no_more_data));
            }
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
