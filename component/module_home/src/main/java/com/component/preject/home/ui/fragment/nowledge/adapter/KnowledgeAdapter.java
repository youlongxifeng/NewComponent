package com.component.preject.home.ui.fragment.nowledge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.home.R;
import com.component.preject.home.bean.KnowledgeHierarchyData;

import java.util.List;

import mao.com.flexibleflowlayout.TagAdapter;
import mao.com.flexibleflowlayout.TagFlowLayout;

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
public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeHierarchyData, BaseViewHolder> {
    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnowledgeHierarchyData item) {
        helper.setText(R.id.tv_knowledge_title,item.getName());
        TagFlowLayout flowLayout = helper.getView(R.id.flow_layout);
        flowLayout.setMaxSelectCount(0);
        List<KnowledgeHierarchyData> children = item.getChildren();
        flowLayout.setAdapter(new TagAdapter() {
            @Override
            public int getItemCount() {
                return children.size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.flow_text_tag_layout,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView viewTag = view.findViewById(R.id.flow_text_tag);
                viewTag.setText(children.get(position).getName());
            }
        });
    }
}
