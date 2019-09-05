package com.component.preject.home.ui.fragment.nowledge.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.home.bean.KnowledgeBean;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.nowledge.adapter
 * @ClassName: KnowledgeAdapter
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean, BaseViewHolder> {
    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnowledgeBean item) {

    }
}
