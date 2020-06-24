package com.diabin.festec.uimake;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.diabin.festec.uimake.bottombar.Bottombar;

public class MainActivity extends AppCompatActivity {
    private Bottombar bottombar;
    public static boolean isHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        FragmentManager fm = getSupportFragmentManager();
        bottombar = new Bottombar(this, -1);
        fm.beginTransaction().add(R.id.main_bottom_bar, bottombar).commit();
    }

    @Override
    public void onBackPressed() {
        MainFragment mainFragment = null;
        int g_index = bottombar.getG_index();
        switch (g_index) {
            case 0:
                mainFragment = bottombar.getMainFragment();
                break;
            case 1:
                mainFragment = bottombar.getMainFragment2();
                break;
            case 2:
                mainFragment = bottombar.getMainFragment3();
                break;
            case 3:
                mainFragment = bottombar.getMainFragment4();
                break;
        }
        // 获取当前回退栈中的Fragment个数
        FragmentManager fragmentManager = mainFragment.getChildFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        // 回退栈中至少有多个fragment,栈底部是首页
        Log.i("数量", String.valueOf(backStackEntryCount));
        if (backStackEntryCount > 0) {
            // 回退一步
            Log.i("数量", String.valueOf(backStackEntryCount));
            fragmentManager.popBackStackImmediate();
            if (isHidden) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
                fragmentTransaction.replace(R.id.main_bottom_bar, bottombar);
                fragmentTransaction.commit();
                isHidden = false;
            }
        } else {
            super.onBackPressed();
        }
    }
}