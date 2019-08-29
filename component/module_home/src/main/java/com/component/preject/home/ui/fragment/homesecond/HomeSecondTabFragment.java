package com.component.preject.home.ui.fragment.homesecond;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
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
import com.component.preject.home.constant.HomeConstants;
import com.component.preject.home.ui.fragment.homesecond.adapter.HomeLatestProjectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homesecond
 * @ClassName: HomeSecondTabFragment
 * @Author: xzg
 * @CreateDate: 2019-08-29 13:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 13:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeSecondTabFragment extends BaseMvpFragment<HomeSecondTabPresenter> implements HomeSecondTabContracte.View {
    private final static String TAG=HomeSecondTabFragment.class.getSimpleName();

    @BindView(R2.id.rc_recovery_box)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipe_recovery_box)
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 下一页请求页数
     */
    private int mNextRequestPage = 1;
    HomeLatestProjectAdapter mAdapter;
    private List<HomeArticleData> datas = new ArrayList<>();
    /**
     *
     * @param tabName tab name
     * @param id 该id在获取该分类下项目时需要用到
     * @return
     */
    public static Fragment newInstance(String tabName, int id) {
        Bundle args = new Bundle();
        args.putString(HomeConstants.TAG_TAB_NAME,tabName);
        args.putInt(HomeConstants.BUNDLE_PROJECT_ID,id);
        HomeSecondTabFragment fragment = new HomeSecondTabFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected HomeSecondTabPresenter createPresenter() {
        return new HomeSecondTabPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_tab_layout;
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
        mAdapter=new HomeLatestProjectAdapter(R.layout.item_project_layout,datas);
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
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        LogUtils.i(TAG, "refresh============mNextRequestPage=" + mNextRequestPage);
        mNextRequestPage = 1;
        mSwipeRefreshLayout.setRefreshing(true);
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.getHomeArticleListProjectData(mNextRequestPage);
    }
    /**
     * 加载更多数据
     */
    private void loadMore() {
        mPresenter.getHomeArticleListProjectData(mNextRequestPage);;
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

    @Override
    public void onHomeArticleListSucceed(HomeArticleListData officialAccountsListData) {
        boolean isRefresh = mNextRequestPage == 1;
        mSwipeRefreshLayout.setRefreshing(false);
        //添加新的数据
        setData(isRefresh, officialAccountsListData.getDatas());
    }

    @Override
    public void onHomeArticleListFail(String fail) {

    }
}
