package com.component.preject.home.ui.fragment.navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.home.R;
import com.component.preject.home.bean.NavigationListData;

import java.util.List;

import mao.com.flexibleflowlayout.TagAdapter;
import mao.com.flexibleflowlayout.TagFlowLayout;

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
public class NaviAdapter extends BaseQuickAdapter<NavigationListData, BaseViewHolder> {
    public NaviAdapter(int layoutResId, @Nullable List<NavigationListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NavigationListData item) {
        helper.setText(R.id.tv_nav_article_title,item.getName());
        TagFlowLayout flowLayout = helper.getView(R.id.nav_flow_layout);
        //设置不可选择
        flowLayout.setMaxSelectCount(0);
        flowLayout.setAdapter(new TagAdapter() {
            @Override
            public int getItemCount() {
                return item.getArticles().size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.flow_text_tag_layout,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView viewTag = view.findViewById(R.id.flow_text_tag);
                viewTag.setText(item.getArticles().get(position).getTitle());
            }
            //每个 tag 点击事件
            @Override
            public void onTagItemViewClick(View v, int position) {
                super.onTagItemViewClick(v, position);
                //Toast.makeText(mContext,flowLayout.getSelectPositionItemList().toString(),Toast.LENGTH_SHORT).show();
               // StartDetailPage.start(mContext,item.getArticles().get(position), Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
            }
        });
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
