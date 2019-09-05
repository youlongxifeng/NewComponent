package com.component.preject.home.ui.fragment.nowledge;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;
import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.constant.HomeConstants;
import com.component.preject.home.ui.fragment.nowledge.adapter.KnowledgeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment
 * @ClassName: KnowledgeFragment
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （体系）
 */
public class KnowledgeFragment extends BaseMvpFragment<KnowledgePresenter> implements KnowledgeContract.View {
    private final static String TAG = KnowledgeFragment.class.getSimpleName();

    @BindView(R2.id.rc_recovery_box)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipe_recovery_box)
    SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * 下一页请求页数
     */
    private int mNextRequestPage = 1;

    private KnowledgeAdapter mAdapter;
    List<KnowledgeHierarchyData> datas = new ArrayList<>();
    StaggeredGridLayoutManager layoutManager;
    public static Fragment newInstance(String tabName) {
        Bundle args = new Bundle();
        args.putString(HomeConstants.TAG_TAB_NAME, tabName);
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge_layout;
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
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
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
        mAdapter = new KnowledgeAdapter(R.layout.item_knowledge_layout, datas);
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
        mNextRequestPage = 1;
        mSwipeRefreshLayout.setRefreshing(true);
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.getKnowledgeList();

    }

    /**
     * 加载更多数据
     */
    private void loadMore() {
        mPresenter.getKnowledgeList();
    }

    /**
     * 更新数据集合
     *
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<KnowledgeHierarchyData> data) {
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
    public void getKnowledgeListSuccess(List<KnowledgeHierarchyData> data) {
        setData(true, data);
    }

    @Override
    public void getKnowledgeListFail(String msg) {

    }
}
