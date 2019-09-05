package com.component.preject.home.ui.fragment.navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;
import com.component.preject.home.bean.NavigationListData;
import com.component.preject.home.constant.HomeConstants;
import com.component.preject.home.ui.fragment.navigation.adapter.NaviAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.navigation
 * @ClassName: NavigationFragment
 * @Author: xzg
 * @CreateDate: 2019-08-30 11:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-30 11:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NavigationFragment extends BaseMvpFragment<NavigationPresenter> implements NavigationContract.View{
    private final static String TAG = NavigationFragment.class.getSimpleName();
    @BindView(R2.id.vertical_tab)
    VerticalTabLayout verticalTabLayout;
    @BindView(R2.id.rc_recovery_box)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipe_recovery_box)
    SwipeRefreshLayout mSwipeRefreshLayout;
    /**用于recyclerView滑动到指定的位置*/
    private boolean canScroll;
    /**
     * 水平对齐
     */
    LinearLayoutManager linearLayoutManager;
    /**
     *  //是否点击了 Tab
     */
    private boolean isClickTab;
    /**
     *  //指向位置
     */
    private int indexPosition;
    /**
     *  //VerticalTabLayout 点击获取位置让 RecycleView滑动到相应位置
     */
    private int scrollToPosition;
    private VerticalTabLayout.OnTabSelectedListener tabSelectedListener;
    private RecyclerView.OnScrollListener onScrollListener;
    List<NavigationListData> datas = new ArrayList<>();

    private NaviAdapter mAdapter;

    public static Fragment newInstance(String tabName) {
        Bundle args = new Bundle();
        args.putString(HomeConstants.TAG_TAB_NAME, tabName);
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_navigation_layout;
    }

    @Override
    protected void initToolbar() {
//item_nav_cardview_layout
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
    private void VerticalTabLayoutWithRecyclerView() {
        onScrollListener =new RecyclerView.OnScrollListener() {
            // RecyclerView 滚动状态变化时回调
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (canScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    canScroll = false;
                    //moveToPosition(layoutManager, recyclerView, scrollToPosition);
                    RecyclerViewSmoothScroll();
                }
                //RecyclerView 滑动关联 VerticalTabLayout
                RecyclerViewWithTabLayout(newState);
            }
            // RecyclerView 滚动时回调
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(canScroll){
                    canScroll = false;
                    RecyclerViewSmoothScroll();
                }
            }
        };
        tabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isClickTab = false;
                //点击标签，使recyclerView滑动
                moveToPosition(linearLayoutManager, mRecyclerView, position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        };
        mRecyclerView.addOnScrollListener(onScrollListener);
        verticalTabLayout.addOnTabSelectedListener(tabSelectedListener);
    }

    /**
     * 滑动到对应位置
     */
    private void RecyclerViewSmoothScroll() {
        int indexPositionDistance = scrollToPosition - linearLayoutManager.findFirstVisibleItemPosition();
        if (indexPositionDistance >= 0 && indexPositionDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexPositionDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    private void RecyclerViewWithTabLayout(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                //点击tab 不执行以下操作
                isClickTab = false;
                return;
            }
            int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (indexPosition != firstPosition) {
                indexPosition = firstPosition;
                //使 TabLayout 选择正确的item
                setTabLayoutChecked(indexPosition);
            }
        }
    }
    private void setTabLayoutChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (verticalTabLayout == null) {
                return;
            }
            verticalTabLayout.setTabSelected(indexPosition);
        }
        indexPosition = position;
    }
    /**
     * VerticalTabLayout 点击使 RecyclerView 滑动到对应位置
     * @param layoutManager
     * @param recyclerView
     * @param position
     */
    private void moveToPosition(LinearLayoutManager layoutManager, RecyclerView recyclerView, int position) {
        // 第一个可见的view的位置
        int firstItem = layoutManager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        int lastItem = layoutManager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            recyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            int top = recyclerView.getChildAt(position - firstItem).getTop();
            recyclerView.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            recyclerView.smoothScrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
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
          linearLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
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
        mAdapter = new NaviAdapter(R.layout.item_nav_cardview_layout, datas);
        mRecyclerView.setAdapter(mAdapter);
        //加载显示动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //加载更多
                loadMore();
            }
        }, mRecyclerView);
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
        mSwipeRefreshLayout.setRefreshing(true);
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.getNavigationData();

    }

    /**
     * 加载更多数据
     */
    private void loadMore() {
        mPresenter.getNavigationData();
    }

    /**
     * 更新数据集合
     *
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<NavigationListData> data) {
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
        LogUtils.i(TAG, "setData===" + (!isRefresh && (size < HomeConstants.PAGE_SIZE)));
        mAdapter.loadMoreEnd(isRefresh);
    /*    if ((size < HomeConstants.PAGE_SIZE)) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            if (!isRefresh) {
                ToastUtils.getInstance().show(getActivity(), getString(R.string.no_more_data));
            }
        } else {
            mAdapter.loadMoreComplete();
        }*/

    }


    @Override
    public void getNavigationDataSuccess(List<NavigationListData> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        setData(true,data);
        verticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(data.get(position).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity,R.color.textColorPress), ContextCompat.getColor(_mActivity,R.color.textColorPrimary))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        VerticalTabLayoutWithRecyclerView();
    }

    @Override
    public void getNavigationDataFail(String msg) {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
