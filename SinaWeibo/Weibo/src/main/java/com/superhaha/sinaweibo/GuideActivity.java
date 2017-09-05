package com.superhaha.sinaweibo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    private void initImageList(){
        imageList = new ArrayList<>();
//        添加图片
        imageList.add(R.mipmap.surprise_background_default);
        imageList.add(R.mipmap.surprise_background_grass);
        imageList.add(R.mipmap.surprise_background_roof);
        imageList.add(R.mipmap.surprise_background_window);

        imageViewList = new ArrayList<>();
        for (Integer idRes : imageList){
//            创建显示图片的视图
            ImageView imageView = new ImageView(this);
            imageViewList.add(imageView);
        }
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new GuideAdapter(this,imageViewList,imageList));
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
            imageView.setImageResource(imageList.get(position));
            container.addView(imageView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            return imageView;
        }

//        销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }
    }
}
