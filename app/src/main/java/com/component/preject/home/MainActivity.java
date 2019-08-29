package com.component.preject.home;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.component.preject.common.base.activity.BaseActivity;
import com.component.preject.common.constants.Constants;
import com.component.preject.common.utils.ARouterUtils;
import com.component.preject.common.utils.LogUtils;
import com.component.preject.common.utils.StatusBarUtil;
import com.component.preject.home.fragment.ProjectFragment;
import com.component.preject.home.utils.NavHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;

/**
 * @author Administrator
 */
public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener,
        NavHelper.OnTabChangeListener<String> {
    private final static String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.main_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.tv_page_title)
    TextView pageTitle;
    @BindView(R.id.iv_search)
    ImageView mSearch;


    //用户头像
    //private CircleImageView userImageIcon;

    private NavHelper mNavHelper;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {

        Log.i("YYY", "toolbar:" + (toolbar != null));
        Log.i("YYY", "pageTitle:" + (pageTitle != null) + " pageTitle:" + pageTitle);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        Log.i("YYY", "" + (supportActionBar != null));
        if (supportActionBar != null) {
            assert supportActionBar != null;
            //除去toolbar 默认显示的标题
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        pageTitle.setText(getString(R.string.page_home));
        //沉浸式状态栏
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, drawer, ContextCompat.getColor(this, R.color.colorPrimary));
        initFragment();
        initView();
    }
    Fragment HomePageFragment = ARouterUtils.getFragment(Constants.ROUTER_HOME);
    Fragment OfficialAccountsPageFragment = ARouterUtils.getFragment(Constants.ROUTER_OFFICIALACCOUNTSPAGE);
   // Fragment KnowledgeHierarchyPageFragment=ARouterUtils.getFragment(Constants.ROUTER_KNOWLEDGEHIERARCHYPAGE);
    Fragment ProjectFragment=ARouterUtils.getFragment(Constants.ROUTER_PROJECTFRAGMENT);
    Fragment fragment = ARouterUtils.getFragment(Constants.ROUTER_HOME);
    private void initFragment() {
         LogUtils.i(TAG, "HomePageFragment:" + HomePageFragment);
        LogUtils.i(TAG, "fragment:" + fragment);

        mNavHelper = new NavHelper<String>(this, R.id.page_fragment_container, getSupportFragmentManager(), this)
                .add(R.id.tab_main, new NavHelper.Tab<String>(HomePageFragment.getClass(), getString(R.string.page_home), Constants.TAG_HOME))
                .add(R.id.nav_home, new NavHelper.Tab<String>(HomePageFragment.getClass(), getString(R.string.page_home), Constants.TAG_HOME))
                .add(R.id.tab_knowledge_hierarchy, new NavHelper.Tab<String>(HomePageFragment.getClass(), getString(R.string.knowledge_hierarchy), Constants.TAG_KNOWLEGER))
                .add(R.id.tab_official_accounts, new NavHelper.Tab<String>(OfficialAccountsPageFragment.getClass(), getString(R.string.official_accounts), Constants.TAG_OFFICIAL))
                .add(R.id.tab_navigation, new NavHelper.Tab<String>(ProjectFragment.class, getString(R.string.navigation), Constants.TAG_NAVIGATION))
                .add(R.id.tab_project, new NavHelper.Tab<String>(ProjectFragment.getClass(), getString(R.string.project), Constants.TAG_PROJECT))
                .add(R.id.collect_page, new NavHelper.Tab<String>(ProjectFragment.class, getString(R.string.nav_collect), Constants.TAG_COLLECTION));

    }

    private void initView() {
        fab.setOnClickListener(this);
        mSearch.setVisibility(View.VISIBLE);
        mSearch.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //默认选择 首页
        navigationView.setCheckedItem(R.id.nav_home);
        //获取侧边栏头部属性
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        /*userImageIcon = headerView.findViewById(R.id.imageView_user_icon);
        userImageIcon.setOnClickListener(this);*/
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Menu menu = bottomNavigationView.getMenu();
        menu.performIdentifierAction(R.id.tab_main, 0);
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initEventAndData() {

    }


    @Override
    public void onTabChange(NavHelper.Tab<String> newTab, NavHelper.Tab<String> oldTab) {

    }

    int id;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        id = menuItem.getItemId();
        LogUtils.i(TAG, "onBackPressed:" + (id != R.id.nav_home) + "   id:" + id);
        switch (id) {
            case R.id.collect_page:
            /*    //收藏
                if(!mPresenter.getLoginStatus()){
                    //是否已经登录
                    StartDetailPage.start(this,null, HomeConstants.PAGE_LOGIN,HomeConstants.ACTION_LOGIN_ACTIVITY);
                    return false;
                }*/
                initPageTitle(getString(R.string.nav_collect));
                bottomNavigationView.setVisibility(View.GONE);
                break;
            case R.id.nav_settings:
                //设置
              /*  if (mSettingsFragment == null) {
                    mSettingsFragment = SettingsFragment.newInstance();
                }
                if (!isDestroyed() && mSettingsFragment.isAdded()) {
                    mSettingsFragment.dismiss();
                }
                mSettingsFragment.show(getSupportFragmentManager(),"showSettings");*/
                break;
            case R.id.nav_todo:
                //TODO
                Toast.makeText(this, "暂未实现", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home:
            case R.id.tab_main:
                //主页
                initPageTitle(getString(R.string.page_home));
                bottomNavigationView.setVisibility(View.VISIBLE);
                break;
            case R.id.tab_knowledge_hierarchy:
                //知识体系
                initPageTitle(getString(R.string.knowledge_hierarchy));
                break;
            case R.id.tab_official_accounts:
                //公众号
                initPageTitle(getString(R.string.official_accounts));
                break;
            case R.id.tab_navigation:
                //导航
                initPageTitle(getString(R.string.navigation));
                break;
            case R.id.tab_project:
                //项目
                initPageTitle(getString(R.string.project));
                break;
            case R.id.common_website:
                //常用网站
              /*  if (mCommonWebFragment == null) {
                    mCommonWebFragment = CommonWebFragment.newInstance(getString(R.string.common_web));
                }
                if (!isDestroyed() && mCommonWebFragment.isAdded()) {
                    mCommonWebFragment.dismiss();
                }
                mCommonWebFragment.show(getSupportFragmentManager(),"showCommonWeb");*/
                break;
            default:
                break;
        }
       /* if (id == R.id.nav_collect) {
            Toast.makeText(this,"点击了收藏",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this,"点击了设置",Toast.LENGTH_SHORT).show();
        }*/
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //点击之后关闭DrawerLayout
        if (R.id.common_website != id) {
            //如果是 常用网站则不改变选中状态
            //navigationView 选中
            navigationView.setCheckedItem(id);
        }
        /*if(R.id.nav_settings == id){
            //如果是设置 让其选中首页状态
            navigationView.setCheckedItem(R.id.nav_home);
        }*/
        drawer.closeDrawer(GravityCompat.START);
        return mNavHelper.performClickMenu(id);
    }

    /**
     * 加载对应的页面
     */
    private void initPageTitle(String pagetitle) {
        pageTitle.setText(pagetitle);

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        LogUtils.i(TAG, "onBackPressed:" + (id != R.id.nav_home));
        MenuItem menu = bottomNavigationView.getMenu().getItem(0);
        if (id != menu.getItemId()) {
            id = menu.getItemId();
            menu.setChecked(true);

            LogUtils.i(TAG, "onBackPressed");
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Fragment fragment = (Fragment) ARouter.getInstance().build(Constants.ROUTER_HOME).navigation();
                Log.i(TAG, "fragment:" + fragment);
                //Toast.makeText(this, "找到Fragment:" + fragment.toString(), Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(Constants.ROUTER_PROJECT).navigation(this, new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d("ARouter", "找到了");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d("ARouter", "找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d("ARouter", "跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d("ARouter", "被拦截了");
                    }
                });
                break;
            default:
                break;
        }

    }
}
