package com.component.preject.home.ui.fragment.official.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.home.R;
import com.component.preject.home.bean.KnowledgeHierarchyData;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.official.adapter
 * @ClassName: OfficialAccountsAdapter
 * @Author: xzg
 * @CreateDate: 2019-08-28 17:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 17:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class OfficialAccountsAdapter extends BaseQuickAdapter<KnowledgeHierarchyData, BaseViewHolder> {
    private final static String TAG=OfficialAccountsAdapter.class.getSimpleName();

    public OfficialAccountsAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnowledgeHierarchyData item) {
        helper.setText(R.id.tv_wx_name,item.getName());
    }
}
