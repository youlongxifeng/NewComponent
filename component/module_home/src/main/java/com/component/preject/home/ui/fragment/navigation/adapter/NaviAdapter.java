package com.component.preject.home.ui.fragment.navigation.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.home.bean.NaviBean;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.navigation.adapter
 * @ClassName: NaviAdapter
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NaviAdapter extends BaseQuickAdapter<NaviBean, BaseViewHolder> {
    public NaviAdapter(int layoutResId, @Nullable List<NaviBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NaviBean item) {

    }
}
