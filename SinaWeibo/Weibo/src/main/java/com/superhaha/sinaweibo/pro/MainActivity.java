package com.superhaha.sinaweibo.pro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.superhaha.sinaweibo.R;
import com.superhaha.sinaweibo.pro.discover.view.DiscoverFragment;
import com.superhaha.sinaweibo.pro.home.view.HomeFragment;
import com.superhaha.sinaweibo.pro.message.view.MessageFragment;
import com.superhaha.sinaweibo.pro.publish.view.PublishFragment;
import com.superhaha.sinaweibo.pro.user.view.UserFragment;

import java.util.ArrayList;
import java.util.List;
/*
* Tab页
* */

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    //    保存Tab页基本信息
    private List<TabItem> tabItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabItemList();
        initTabView();
    }

    //    初始化Tab页基本信息
    private void initTabItemList() {
        this.tabItemList = new ArrayList<>();
        this.tabItemList.add(new TabItem(R.mipmap.tabbar_home, R.mipmap.tabbar_home_highlighted, R.string.tabbar_home_text, HomeFragment.class));
        this.tabItemList.add(new TabItem(R.mipmap.tabbar_message_center, R.mipmap.tabbar_message_center_highlighted, R.string.tabbar_message_text, MessageFragment.class));
        this.tabItemList.add(new TabItem(R.drawable.tabbar_compose_button, R.drawable.tabbar_compose_button_highlighted, 0, PublishFragment.class));
        this.tabItemList.add(new TabItem(R.mipmap.tabbar_discover, R.mipmap.tabbar_discover_highlighted, R.string.tabbar_discover_text, DiscoverFragment.class));
        this.tabItemList.add(new TabItem(R.mipmap.tabbar_profile, R.mipmap.tabbar_profile_highlighted, R.string.tabbar_profile_text, UserFragment.class));
    }

    //初始化Tab,同时绑定Tab
    private void initTabView() {
        //绑定和加载替换我们的Fragment
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        指定Fragment绑定的布局
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        //删除分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < tabItemList.size(); i++) {
            TabItem tabItem = tabItemList.get(i);
            //创建Tab
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTabText()).setIndicator(tabItem.getTabindicator());
//                    添加Fragment
            fragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), tabItem.getBundle());
//            给Tab设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.tabbar_bottom_bg));
            fragmentTabHost.setOnTabChangedListener(this);
            //设置默认选中Tab
            if (i == 0) {
                tabItem.setCheckek(true);
            }
        }
    }

    @Override
    public void onTabChanged(String tabId) {
//        通过循环重置样式
    for (int i = 0;i<tabItemList.size();i++) {
        TabItem tabItem = tabItemList.get(i);
        if (tabItem.getTabText().equals(tabId)) {
            tabItem.setCheckek(true);
        } else {
            tabItem.setCheckek(false);
        }
    }
    }

    //    采用OO设计思想
    public class TabItem {
        private int imageNormal;//正常显示的图片
        private int imagePress;//选中的图片
        private int tabTextRes;//tab名字
        private Class<? extends Fragment> fragmentClass;//tab对应的Fragment
        private Bundle bundle;//Fragment对应的数据(例如：下标)

        private View viewIndicator;
        private ImageView iv_tab;
        private TextView tv_tab;

        public TabItem(int imageNormal, int imagePress, int tabTextRes, Class<? extends Fragment> fragmentClass) {
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.tabTextRes = tabTextRes;
            this.fragmentClass = fragmentClass;
        }

        public Bundle getBundle() {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putInt("tabTextRes", tabTextRes);
            }
            return bundle;
        }

        public String getTabText() {
            if (tabTextRes == 0) {
                return "";
            }
            return getResources().getString(tabTextRes);
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTabTextRes() {
            return tabTextRes;
        }

        //重置tab样式
        public void setCheckek(boolean isChecked) {
            if (iv_tab != null) {
                if (isChecked) {
                    iv_tab.setImageResource(imagePress);
                } else {
                    iv_tab.setImageResource(imageNormal);
                }
            }

            if (tv_tab != null) {
                if (isChecked) {
                    tv_tab.setTextColor(getResources().getColor(R.color.tabbar_text_press_color));
                } else {
                    tv_tab.setTextColor(getResources().getColor(R.color.tabbar_text_normal_color));

                }
            }
        }

        public View getTabindicator() {
            //创建Tab视图
            if (viewIndicator == null) {
                int layoutId = 0;
                //判断加载哪一个布局文件
                if (this.tabTextRes <= 0) {
                    layoutId = R.layout.tabbar_publish_indicator;
                } else {
                    layoutId = R.layout.tabbar_indicator;
                }
                viewIndicator = getLayoutInflater().inflate(layoutId, null);
                iv_tab = (ImageView) viewIndicator.findViewById(R.id.iv_tab);
                if (this.tabTextRes > 0) {
                    tv_tab = (TextView) viewIndicator.findViewById(R.id.tv_tab);
                    tv_tab.setText(getTabText());
                }
//                绑定默认的资源
                iv_tab.setImageResource(imageNormal);

            }
            return viewIndicator;
        }

    }
}
