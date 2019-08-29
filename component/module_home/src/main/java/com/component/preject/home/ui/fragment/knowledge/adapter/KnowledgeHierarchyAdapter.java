package com.component.preject.home.ui.fragment.knowledge.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.home.bean.KnowledgeHierarchyData;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.knowledge.adapter
 * @ClassName: KnowledgeHierarchyAdapter
 * @Author: xzg
 * @CreateDate: 2019-08-29 11:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 11:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class KnowledgeHierarchyAdapter extends BaseQuickAdapter<KnowledgeHierarchyData, BaseViewHolder> {


    public KnowledgeHierarchyAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    public KnowledgeHierarchyAdapter(@Nullable List<KnowledgeHierarchyData> data) {
        super(data);
    }

    public KnowledgeHierarchyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeHierarchyData item) {

    }
}

