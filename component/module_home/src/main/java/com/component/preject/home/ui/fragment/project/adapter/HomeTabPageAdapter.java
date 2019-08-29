package com.component.preject.home.ui.fragment.project.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.project.adapter
 * @ClassName: HomeTabPageAdapter
 * @Author: xzg
 * @CreateDate: 2019-08-29 13:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 13:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （首页 tablayout fragment 适配器）
 */
public class HomeTabPageAdapter  extends FragmentStatePagerAdapter {

    List<String> mTitle;
    List<Fragment> mFragments;

    public HomeTabPageAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.mTitle = titles;
        this.mFragments = fragments;

    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }

    //注释父类实现，避免出现fragment 空白
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container,position,object);
    }
}
