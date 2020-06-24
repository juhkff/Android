package com.diabin.festec.uimake.bottombar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.diabin.festec.uimake.MainFragment;
import com.diabin.festec.uimake.R;
import com.diabin.festec.uimake.topbar.TopbarType;

public class Bottombar extends Fragment implements View.OnClickListener {
    private FragmentActivity fragmentActivity;
    private MainFragment mainFragment;
    private MainFragment mainFragment2;
    private MainFragment mainFragment3;
    private MainFragment mainFragment4;
    private int g_index;

    private TextView bottom_bar_text_1;
    private ImageView bottom_bar_image_1;
    private TextView bottom_bar_text_2;
    private ImageView bottom_bar_image_2;
    private TextView bottom_bar_text_3;
    private ImageView bottom_bar_image_3;
    private TextView bottom_bar_text_4;
    private ImageView bottom_bar_image_4;

    @SuppressWarnings("FieldCanBeLocal")
    private RelativeLayout bottom_bar_1_btn;
    @SuppressWarnings("FieldCanBeLocal")
    private RelativeLayout bottom_bar_2_btn;
    @SuppressWarnings("FieldCanBeLocal")
    private RelativeLayout bottom_bar_3_btn;
    @SuppressWarnings("FieldCanBeLocal")
    private RelativeLayout bottom_bar_4_btn;

    public Bottombar(FragmentActivity fragmentActivity, int g_Index) {
        this.fragmentActivity = fragmentActivity;
        this.g_index = g_Index;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_bar, container, false);
        bottom_bar_1_btn = view.findViewById(R.id.bottom_bar_1_btn);
        //添加点击事件
        bottom_bar_1_btn.setOnClickListener(this);
        bottom_bar_2_btn = view.findViewById(R.id.bottom_bar_2_btn);
        //添加点击事件
        bottom_bar_2_btn.setOnClickListener(this);
        bottom_bar_3_btn = view.findViewById(R.id.bottom_bar_3_btn);
        //添加点击事件
        bottom_bar_3_btn.setOnClickListener(this);
        bottom_bar_4_btn = view.findViewById(R.id.bottom_bar_4_btn);
        //添加点击事件
        bottom_bar_4_btn.setOnClickListener(this);

        bottom_bar_text_1 = view.findViewById(R.id.bottom_bar_text_1);
        bottom_bar_image_1 = view.findViewById(R.id.bottom_bar_image_1);
        bottom_bar_text_2 = view.findViewById(R.id.bottom_bar_text_2);
        bottom_bar_image_2 = view.findViewById(R.id.bottom_bar_image_2);
        bottom_bar_text_3 = view.findViewById(R.id.bottom_bar_text_3);
        bottom_bar_image_3 = view.findViewById(R.id.bottom_bar_image_3);
        bottom_bar_text_4 = view.findViewById(R.id.bottom_bar_text_4);
        bottom_bar_image_4 = view.findViewById(R.id.bottom_bar_image_4);

        //初始选中第一栏
        setSelectStatus(0);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_bar_1_btn:
                setSelectStatus(0);
                break;
            case R.id.bottom_bar_2_btn:
                setSelectStatus(1);
                break;
            case R.id.bottom_bar_3_btn:
                setSelectStatus(2);
                break;
            case R.id.bottom_bar_4_btn:
                setSelectStatus(3);
                break;
            default:
                break;
        }
    }

    private void setSelectStatus(int index) {
        if (g_index == index)
            return;
        else {
            g_index = index;
            FragmentManager fm;
            switch (index) {
                case 0:
                    //图片点击选择变换图片，颜色的改变，其他变为原来的颜色，并保持原有的图片
                    bottom_bar_image_1.setImageResource(R.drawable.home_selected);
                    bottom_bar_text_1.setTextColor(Color.parseColor("#0097F7"));
                    //其他的文本颜色不变
                    bottom_bar_text_2.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_3.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_4.setTextColor(Color.parseColor("#666666"));
                    //fragment跳转
                    fm = fragmentActivity.getSupportFragmentManager();
                    mainFragment = new MainFragment(TopbarType.TOPBAR_1, (Context) fragmentActivity);
                    fm.beginTransaction().replace(R.id.frame, mainFragment).commit();
                    //图片也不变
                    bottom_bar_image_2.setImageResource(R.drawable.flag);
                    bottom_bar_image_3.setImageResource(R.drawable.decorate);
                    bottom_bar_image_4.setImageResource(R.drawable.user);
                    break;
                case 1://同理如上
                    //图片点击选择变换图片，颜色的改变，其他变为原来的颜色，并保持原有的图片
                    bottom_bar_image_2.setImageResource(R.drawable.flag_selected);
                    bottom_bar_text_2.setTextColor(Color.parseColor("#0097F7"));
                    //其他的文本颜色不变
                    bottom_bar_text_1.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_3.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_4.setTextColor(Color.parseColor("#666666"));
                    //fragment跳转
                    fm = fragmentActivity.getSupportFragmentManager();
                    mainFragment2 = new MainFragment(TopbarType.TOPBAR_2, (Context) fragmentActivity);
                    fm.beginTransaction().replace(R.id.frame, mainFragment2).commit();
                    //图片也不变
                    bottom_bar_image_1.setImageResource(R.drawable.home);
                    bottom_bar_image_3.setImageResource(R.drawable.decorate);
                    bottom_bar_image_4.setImageResource(R.drawable.user);
                    break;
                case 2://同理如上
                    //图片点击选择变换图片，颜色的改变，其他变为原来的颜色，并保持原有的图片
                    bottom_bar_image_3.setImageResource(R.drawable.decorate_selected);
                    bottom_bar_text_3.setTextColor(Color.parseColor("#0097F7"));
                    //其他的文本颜色不变
                    bottom_bar_text_1.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_2.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_4.setTextColor(Color.parseColor("#666666"));
                    //fragment跳转
                    fm = fragmentActivity.getSupportFragmentManager();
                    mainFragment3 = new MainFragment(TopbarType.TOPBAR_3, (Context) fragmentActivity);
                    fm.beginTransaction().replace(R.id.frame, mainFragment3).commit();
                    //图片也不变
                    bottom_bar_image_1.setImageResource(R.drawable.home);
                    bottom_bar_image_2.setImageResource(R.drawable.flag);
                    bottom_bar_image_4.setImageResource(R.drawable.user);
                    break;
                case 3://同理如上
                    //图片点击选择变换图片，颜色的改变，其他变为原来的颜色，并保持原有的图片
                    bottom_bar_image_4.setImageResource(R.drawable.user_selected);
                    bottom_bar_text_4.setTextColor(Color.parseColor("#0097F7"));
                    //其他的文本颜色不变
                    bottom_bar_text_1.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_2.setTextColor(Color.parseColor("#666666"));
                    bottom_bar_text_3.setTextColor(Color.parseColor("#666666"));
                    //fragment跳转
                    fm = fragmentActivity.getSupportFragmentManager();
                    mainFragment4 = new MainFragment(TopbarType.TOPBAR_4, (Context) fragmentActivity);
                    fm.beginTransaction().replace(R.id.frame, mainFragment4).commit();
                    //图片也不变
                    bottom_bar_image_1.setImageResource(R.drawable.home);
                    bottom_bar_image_2.setImageResource(R.drawable.flag);
                    bottom_bar_image_3.setImageResource(R.drawable.decorate);
                    break;
            }
        }
    }

    public MainFragment getMainFragment() {
        return mainFragment;
    }

    public MainFragment getMainFragment2() {
        return mainFragment2;
    }

    public MainFragment getMainFragment3() {
        return mainFragment3;
    }

    public MainFragment getMainFragment4() {
        return mainFragment4;
    }

    public int getG_index() {
        return g_index;
    }
}
