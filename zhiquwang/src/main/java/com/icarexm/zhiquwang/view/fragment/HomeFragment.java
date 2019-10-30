package com.icarexm.zhiquwang.view.fragment;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.HomeFmAdapter;
import com.icarexm.zhiquwang.adapter.TodayHeatAdapter;
import com.icarexm.zhiquwang.custview.GlideImageLoader;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.view.actvity.RecruitDetailActivity;
import com.yyydjk.library.BannerLayout;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private BannerLayout bannerLayout;
    //轮播图
    final List<String> urls = new ArrayList<>();
    //轮播图
    final List<String> gridurls = new ArrayList<>();
    private String[] name;
    private RadioGroup radioGroup;
    private RadioButton radiobutton_area;
    private RadioButton radiobutton_salary;
    private RadioButton radiobutton_age;
    private RadioButton radiobutton_trade;
    private RadioButton radiobutton_work;
    private RecyclerView recyclerView;
    private Context mContext;
    private TodayHeatAdapter todayHeatAdapter;
    private XRecyclerView mRecyclerView;
    private HomeFmAdapter homeFmAdapter;
    private List<String> list=new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getContext();
        String s = imageTranslateUri(R.mipmap.ic_home_banner);
        urls.clear();
        for (int a=0;a<4;a++){
            urls.add(s);
        }
        //图标下的文字
        name = new String[]{"名企招聘","高返费专区","高价小时工","高级蓝领"};
        //图标
        gridurls.clear();
        int icno[] = {R.mipmap.ic_grid_one,R.mipmap.ic_grid_two,R.mipmap.ic_grid_three,R.mipmap.ic_grid_four};
         for(int b=0;b<icno.length;b++){
             gridurls.add(imageTranslateUri(icno[b]));
         }
        InitUI(inflate);
        return inflate;
    }

    private void InitUI(View inflate) {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        GridView home_gridview = inflate.findViewById(R.id.fm_home_gridview);
        home_gridview.setAdapter(new GridAdapter());
        bannerLayout= inflate.findViewById(R.id.fm_home_banner);
        bannerLayout.setAutoPlay(true);
        bannerLayout.setImageLoader(new GlideImageLoader());
        bannerLayout.setViewUrls(urls);
        //添加点击监听
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        recyclerView = inflate.findViewById(R.id.fm_home_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        todayHeatAdapter = new TodayHeatAdapter(mContext);
        recyclerView.setAdapter(todayHeatAdapter);
        todayHeatAdapter.refreshData(5);

        mRecyclerView = inflate.findViewById(R.id.fm_home_content_xRecyclerView);

        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        homeFmAdapter = new HomeFmAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }
        });
        mRecyclerView.setAdapter(homeFmAdapter);
        homeFmAdapter.setListAll(list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_10))
                        , 0
                        , 0);
            }
        });
        homeFmAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                startActivity(new Intent(mContext, RecruitDetailActivity.class));
            }
        });
        homeFmAdapter.notifyDataSetChanged();
        radioGroup = inflate.findViewById(R.id.fm_home_radioGroup);
        radiobutton_area = inflate.findViewById(R.id.fm_home_radiobutton_area);
        radiobutton_salary = inflate.findViewById(R.id.fm_home_radiobutton_salary);
        radiobutton_age = inflate.findViewById(R.id.fm_home_radiobutton_age);
        radiobutton_trade = inflate.findViewById(R.id.fm_home_radiobutton_trade);
        radiobutton_work = inflate.findViewById(R.id.fm_home_radiobutton_work);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.fm_home_radiobutton_area:
                        radiobutton_area.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.fm_home_radiobutton_salary:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.fm_home_radiobutton_age:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.fm_home_radiobutton_trade:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.fm_home_radiobutton_work:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        break;
                }
            }
        });
    }

    private String imageTranslateUri(int resId) {

        Resources r = getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri.toString();
    }

   public class GridAdapter extends BaseAdapter{
       @Override
       public int getCount() {
           return gridurls.size();
       }

       @Override
       public Object getItem(int i) {
           return null;
       }

       @Override
       public long getItemId(int i) {
           return 0;
       }

       @Override
       public View getView(int i, View view, ViewGroup viewGroup) {
           View inflate = getLayoutInflater().inflate(R.layout.home_griditem, null);
           ImageView grid_img= inflate.findViewById(R.id.grid_img);
           TextView guid_text = inflate.findViewById(R.id.guid_text);
           Glide.with(getContext()).load(gridurls.get(i)).into(grid_img);
           guid_text.setText(name[i]);
           return inflate;
       }
   }

}
