package com.diabin.festec.uimake;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diabin.festec.uimake.sample.OtherNewsItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OtherFragment extends Fragment {
    private MainFragment parentFragment;

    public OtherFragment(MainFragment mainFragment) {
        this.parentFragment = mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_news_list, container, false);

//        if (view instanceof RecyclerView) {
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rec);
//            if (mColumnCount <= 1) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
        recyclerView.setAdapter(new OtherAdapter(getActivity(),parentFragment, generateDatas(), false));
//        }
        return view;
    }

    private List<OtherNewsItem> generateDatas() {
        Resources resources = getContext().getResources();
        Drawable user_example = resources.getDrawable(R.drawable.user_example);
        Drawable female = resources.getDrawable(R.drawable.female);

        List<OtherNewsItem> otherNewsItems = new ArrayList<OtherNewsItem>();
        for (int i = 0; i < 4; i++) {
            otherNewsItems.add(new OtherNewsItem(String.valueOf(i),
                    String.valueOf(new Date().getTime()),
                    user_example,
                    "content" + i,
                    female,
                    20, 21, 22));
        }
        return otherNewsItems;
    }
}
