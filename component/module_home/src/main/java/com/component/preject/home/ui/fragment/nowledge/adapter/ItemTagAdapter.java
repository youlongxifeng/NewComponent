package com.component.preject.home.ui.fragment.nowledge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.component.preject.home.R;
import com.component.preject.home.bean.KnowledgeHierarchyData;

import java.util.List;

import mao.com.flexibleflowlayout.TagAdapter;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.nowledge.adapter
 * @ClassName: ItemTagAdapter
 * @Author: xzg
 * @CreateDate: 2019-09-05 14:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 14:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ItemTagAdapter extends TagAdapter {
    List<KnowledgeHierarchyData> children;

    public ItemTagAdapter(List<KnowledgeHierarchyData> item) {
        this.children = item;
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
        return inflater.inflate(R.layout.flow_text_tag_layout, parent, false);
    }

    @Override
    public void bindView(View view, int position) {
        TextView viewTag = view.findViewById(R.id.flow_text_tag);
        viewTag.setText(children.get(position).getName());
    }
}
