package com.diabin.festec.uimake;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.diabin.festec.uimake.topbar.TopbarType;
import com.diabin.festec.uimake.topbar.Topbar_1;
import com.diabin.festec.uimake.topbar.Topbar_2;
import com.diabin.festec.uimake.topbar.Topbar_3;
import com.diabin.festec.uimake.topbar.Topbar_4;

public class MainFragment extends Fragment {
    private RelativeLayout fragmentRoot;
    protected FrameLayout main_top_bar;
    protected FrameLayout frame;
    protected FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TopbarType topbarType;
    private Context context;

    public MainFragment(TopbarType topbarType, Context context) {
        fragmentTransaction = null;
        this.topbarType = topbarType;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        this.fragmentManager = getChildFragmentManager();
        fragmentRoot = view.findViewById(R.id.root);
        main_top_bar = view.findViewById(R.id.main_top_bar);
        frame = view.findViewById(R.id.frame);

        if (topbarType == TopbarType.TOPBAR_1) {
            Topbar_1 topbar_1 = new Topbar_1(this);
            setTopBar(topbar_1, null);
            Fragment fragment = new NewsFragment(this, false);
            replaceFrameLayout(fragment, null);
            commit();
        } else if (topbarType == TopbarType.TOPBAR_2) {
            Topbar_2 topbar_2 = new Topbar_2();
            setTopBar(topbar_2, null);
            Fragment fragment2 = new ExampleFragment(this);
            replaceFrameLayout(fragment2, null);
            commit();
        } else if (topbarType == TopbarType.TOPBAR_3) {
            Topbar_3 topbar_3 = new Topbar_3();
            setTopBar(topbar_3, null);
            Fragment fragment3 = new DecorateFragment(context);
            replaceFrameLayout(fragment3, null);
            commit();
        } else if (topbarType == TopbarType.TOPBAR_4) {
            Topbar_4 topbar_4 = new Topbar_4(context, this);
            setTopBar(topbar_4, null);
            Fragment fragment4 = new MeFragment(this);
            replaceFrameLayout(fragment4, null);
            commit();
        }
        return view;
    }

    //onStart()会在每次画面发生变化时调用
    @Override
    public void onStart() {
        super.onStart();

    }

    public void setTopBar(Fragment fragment, String tag) {
        if (fragmentManager == null)
            fragmentManager = getChildFragmentManager();
        if (fragmentTransaction == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (tag != null)
                fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(R.id.main_top_bar, fragment);
    }

    public void replaceFrameLayout(Fragment fragment, String tag) {
        if (fragmentManager == null)
            fragmentManager = getChildFragmentManager();
        if (fragmentTransaction == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (tag != null)
                fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(R.id.frame, fragment);
    }

    public void commit() {
        if (fragmentTransaction != null)
            fragmentTransaction.commit();
        fragmentTransaction = null;
    }
}
