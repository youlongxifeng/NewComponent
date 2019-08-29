package com.component.preject.home.ui.fragment.homesecond.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.component.preject.common.utils.GlideUtils;
import com.component.preject.home.R;
import com.component.preject.home.bean.HomeArticleData;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homesecond.adapter
 * @ClassName: HomeLatestProjectAdapter
 * @Author: xzg
 * @CreateDate: 2019-08-29 13:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 13:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeLatestProjectAdapter extends BaseQuickAdapter<HomeArticleData, BaseViewHolder> {
    private boolean isShowTag = true;
    public HomeLatestProjectAdapter(int layoutResId, @Nullable List<HomeArticleData> data) {
        super(layoutResId, data);
    }


    public void isShowTag(boolean showTag){
        this.isShowTag = showTag;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeArticleData item) {
        helper.setText(R.id.tv_project_title,item.getTitle())
                .setText(R.id.tv_project_desc,item.getDesc())
                .setText(R.id.tv_project_tag,item.getChapterName())
                .setText(R.id.tv_project_date,item.getNiceDate())
                .setText(R.id.tv_project_author_name,item.getAuthor())
                //项目收藏
                .addOnClickListener(R.id.image_project_collect)
                //项目tag
                .addOnClickListener(R.id.tv_project_tag);
        if(!isShowTag){
            helper.getView(R.id.tv_project_tag).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.tv_project_tag).setVisibility(View.VISIBLE);
        }
        GlideUtils.showBannerImage(mContext,helper.getView(R.id.iv_project_pic),item.getEnvelopePic());
        if(item.isCollect()){
            helper.setImageDrawable(R.id.image_project_collect, ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_collect_24dp));
        }else {
            helper.setImageDrawable(R.id.image_project_collect,ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_gray_24dp));
        }
    }
}
