package com.component.preject.home.ui.fragment.project;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.component.preject.common.base.fragment.BaseMvpFragment;
import com.component.preject.common.constants.Constants;
import com.component.preject.home.R;
import com.component.preject.home.R2;
import com.component.preject.home.bean.ProjectClassifyData;
import com.component.preject.home.ui.fragment.homesecond.HomeSecondTabFragment;
import com.component.preject.home.ui.fragment.project.adapter.HomeTabPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.project
 * @ClassName: ProjectFragment
 * @Author: xzg
 * @CreateDate: 2019-08-29 11:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 11:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （项目页面列表）
 */
@Route(path = Constants.ROUTER_PROJECTFRAGMENT)
public class ProjectFragment extends BaseMvpFragment<ProjectPresenter>implements ProjectContract.View {
private final static String TAG=ProjectFragment.class.getSimpleName();

    @BindView(R2.id.project_tab)
    TabLayout mProjectTb;
    @BindView(R2.id.view_pager_project)
    ViewPager mProjectVP;

  /*  @BindView(R2.id.view_error)
    ConstraintLayout mErrorView;
    @BindView(R2.id.tv_reload)
    TextView mTvReload;*/

    List<ProjectClassifyData> mProjectClassifyDataList;
    List<Fragment> mProjectFragmentsList;
    List<String> mTitle;
    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project_layout;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void onViewCreated(View view) {
        mProjectClassifyDataList = new ArrayList<>();
        mProjectFragmentsList = new ArrayList<>();
        mTitle = new ArrayList<>();

    }

    @Override
    protected void initEventAndData() {
        mPresenter.getProjectClassifyData();
    }

    @Override
    public void onProjectClassifyDataSucceed(List<ProjectClassifyData> projectClassifyDataList) {
        mProjectClassifyDataList.clear();
        mProjectClassifyDataList.addAll(projectClassifyDataList);
        for (ProjectClassifyData projectClassifyData:mProjectClassifyDataList) {
            mTitle.add(projectClassifyData.getName());
            mProjectFragmentsList.add(HomeSecondTabFragment.newInstance(projectClassifyData.getName(),projectClassifyData.getId()));
        }
        mProjectVP.setAdapter(new HomeTabPageAdapter(getChildFragmentManager(),mTitle,mProjectFragmentsList));
        mProjectTb.setupWithViewPager(mProjectVP);
    }

    @Override
    public void onProjectClassifyDataFail(String fail) {

    }
}
