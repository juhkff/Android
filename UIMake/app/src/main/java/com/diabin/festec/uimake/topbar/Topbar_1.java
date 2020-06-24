package com.diabin.festec.uimake.topbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.diabin.festec.uimake.MainFragment;
import com.diabin.festec.uimake.NewsFragment;
import com.diabin.festec.uimake.R;

public class Topbar_1 extends Fragment implements View.OnClickListener {
    private MainFragment mainFragment;

    public Topbar_1(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_bar_1, container, false);
        Button button_1 = view.findViewById(R.id.top_button1);
        Button button_2 = view.findViewById(R.id.top_button2);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_button1:
                setLayout("focus");
                break;
            case R.id.top_button2:
                setLayout("recommend");
                break;
        }
    }

    private void setLayout(String type) {
        FragmentManager fm;
        Fragment fragment;
        switch (type) {
            case "focus":
                //fragment跳转
//                fm = fragmentActivity.getSupportFragmentManager();
                //注意v4包的配套使用
                fragment = new NewsFragment(mainFragment,false);
//                fm.beginTransaction().replace(R.id.frame, fragment).commit();
                mainFragment.replaceFrameLayout(fragment,null);
                mainFragment.commit();
                //fragment_id=fragment.getId();
                //new Thread(new Threadx()).start();
                break;
            case "recommend":
                //fragment跳转
//                fm = fragmentActivity.getSupportFragmentManager();
                //注意v4包的配套使用
                fragment = new NewsFragment(mainFragment,true);
//                fm.beginTransaction().replace(R.id.frame, fragment).commit();
                mainFragment.replaceFrameLayout(fragment,null);
                mainFragment.commit();
                //fragment_id=fragment.getId();
                //new Thread(new Threadx()).start();
                break;
            default:
                break;
        }
    }
}
