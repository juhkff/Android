package com.diabin.festec.uimake.topbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.diabin.festec.uimake.MainFragment;
import com.diabin.festec.uimake.NewsFragment;
import com.diabin.festec.uimake.OptionFragment;
import com.diabin.festec.uimake.R;
import com.diabin.festec.uimake.bottombar.Bottombar;

public class Topbar_4 extends Fragment implements View.OnClickListener {
    //    private FragmentActivity fragmentActivity;
    private Context context;
    private ImageView optionImage;
    //    private FragmentManager fragmentManager;
    private MainFragment mainFragment;

    public Topbar_4(Context context, MainFragment mainFragment) {
        this.context = context;
        this.mainFragment = mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_bar_4, container, false);
        optionImage = view.findViewById(R.id.option);
        optionImage.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option:
                changeToOption();
                break;
            default:
                break;
        }
    }

    private void changeToOption() {
//        fragmentManager = fragmentActivity.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.addToBackStack("option");
        Fragment fragment;
        fragment = new OptionFragment(mainFragment, context);
//        fragmentTransaction.replace(R.id.frame, fragment);
        mainFragment.replaceFrameLayout(fragment, "option");
        Topbar_option topbar_option = new Topbar_option(/*fragmentActivity*/);
//        fragmentTransaction.replace(R.id.main_top_bar, fragment);
        mainFragment.setTopBar(topbar_option, "option");
//        fragmentTransaction.commit();
        mainFragment.commit();
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for(int i = 0; i < backStackCount; i++){
//            int backId = fm.getBackStackEntryAt(i).getId();
//            fm.popBackStack(backId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.popBackStack();
        }
    }*/
}
