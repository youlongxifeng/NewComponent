package com.component.preject.home.ui.fragment.official;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.constants.Constants;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.common.utils.ToastUtils;
import com.component.preject.home.R;
import com.component.preject.home.R2;
import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.constant.HomeConstants;
import com.component.preject.home.ui.fragment.official.adapter.OfficialAccountsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.official
 * @ClassName: OfficialAccountsPageFragment
 * @Author: xzg
 * @CreateDate: 2019-08-28 17:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 17:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （公众号页面）
 */
@Route(path = Constants.ROUTER_OFFICIALACCOUNTSPAGE)
public class OfficialAccountsPageFragment extends BaseMvpFragment<OfficialAccountsPagePresenter> implements OfficialAccountsPageContract.View {
    private final static String TAG = OfficialAccountsPageFragment.class.getSimpleName();

    /**
     * 下一页请求页数
     */
    private int mNextRequestPage = 1;

    @BindView(R2.id.rc_recovery_box)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipe_recovery_box)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private List<KnowledgeHierarchyData> datas = new ArrayList<>();
    private OfficialAccountsAdapter mAdapter;
    @Override
    protected OfficialAccountsPagePresenter createPresenter() {
        return new OfficialAccountsPagePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_official_layout;
    }

    @Override
    protected void initToolbar() {


    }

    @Override
    protected void onViewCreated(View view) {

    }

    @Override
    protected void initEventAndData() {
        refresh();
        initRefreshLayout();
        initRecyclerView();
        initAdapter();//初始化适配器
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
        mAdapter=new OfficialAccountsAdapter(R.layout.official_accounts_item_cardview_layout,datas);
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
        mPresenter.getOfficialAccountsListData( );
    }
    /**
     * 加载更多数据
     */
    private void loadMore() {
        mPresenter.getOfficialAccountsListData( );
    }



    @Override
    public void showOfficialAccountsListSuccess(List<KnowledgeHierarchyData> officialAccountsListData) {
        boolean isRefresh = mNextRequestPage == 1;
        mSwipeRefreshLayout.setRefreshing(false);
        //添加新的数据
        setData(isRefresh, officialAccountsListData);
    }

    @Override
    public void showOfficialAccountsListFail(String msg) {

    }

    /**
     * 更新数据集合
     *
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List<KnowledgeHierarchyData> data) {
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
