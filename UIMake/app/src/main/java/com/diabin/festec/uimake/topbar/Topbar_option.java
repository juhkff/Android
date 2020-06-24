package com.diabin.festec.uimake.topbar;

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

import com.diabin.festec.uimake.MeFragment;
import com.diabin.festec.uimake.OptionFragment;
import com.diabin.festec.uimake.R;

public class Topbar_option extends Fragment implements View.OnClickListener {
//    private FragmentActivity fragmentActivity;
//    private ImageView backImage;

    public Topbar_option(/*FragmentActivity fragmentActivity*/) {
//        this.fragmentActivity = fragmentActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_bar_option, container, false);
//        backImage = view.findViewById(R.id.back);
//        backImage.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.back:
//                backToUser();
//                break;
//            default:
//                break;
//        }
    }

    /*private void backToUser() {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        fragment = new Topbar_4(fragmentActivity);
        fragmentTransaction.replace(R.id.main_top_bar, fragment);
        fragment = new MeFragment();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }*/
}
