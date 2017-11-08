package com.superhaha.sinaweibo.pro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.superhaha.sinaweibo.R;
import com.superhaha.sinaweibo.views.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
    //      存放图片编号列表
    private List<Integer> imageList;
    //      存放具体的视图列表
    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
//        1.准备资源
//        2.编写布局文件,三个控件：ViewPager(滑动分页),自定义分页页码,跳转页面需要的按钮
//        3.代码实现功能
//        3.1 初始化ViewPager分页组件,同时配置Adapter数据
        initImageList();//先加载资源
        initView();//再初始化界面
//        3.2 配置分页页码---采用自定义布局容器(采用高级UI组件开发)
//        3.3 添加跳转功能

    }

    private void initImageList() {
        imageList = new ArrayList<>();
//        添加图片
        imageList.add(R.mipmap.surprise_background_default);
        imageList.add(R.mipmap.surprise_background_grass);
        imageList.add(R.mipmap.surprise_background_roof);
        imageList.add(R.mipmap.surprise_background_window);

        imageViewList = new ArrayList<>();
        for (Integer idRes : imageList) {
//            创建显示图片的视图
            ImageView imageView = new ImageView(this);
            imageViewList.add(imageView);
        }
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new GuideAdapter(this, imageViewList, imageList));
//                3.2 配置分页页码
//      获取页码视图
//        CircleIndicator:第三方组件
        CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);
//        绑定页码
        circleIndicator.setViewPager(viewPager);

//        3.3 添加跳转功能
//        监听是否滑动到最后一页
        final TextView tv_guide = (TextView) findViewById(R.id.tv_guide);
        tv_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
            }
        });

        //设置进入主页显示按钮
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageList.size() - 1) {
                    tv_guide.setVisibility(View.VISIBLE);
                } else {
                    tv_guide.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //    Adapter
//    目的:创建并且显示每一个分页
    public static class GuideAdapter extends PagerAdapter {

        private Context context;
        private List<ImageView> imageViewList;
        private List<Integer> imageList;

        public GuideAdapter(Context context, List<ImageView> imageViewList, List<Integer> imageList) {
            this.context = context;
            this.imageViewList = imageViewList;
            this.imageList = imageList;
        }

        //        页码大小
        @Override
        public int getCount() {
            return imageViewList.size();
        }

        //        当前页
        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        //        当前的分页是不是一个view
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //        创建和绑定每一个分页
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViewList.get(position);
//            绑定图片资源
            imageView.setImageResource(imageList.get(position));
//            填充视图,超过的部分截取掉---保持图片原样不变形
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            绑定视图
            container.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return imageView;
        }

        //        销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }
    }
}
