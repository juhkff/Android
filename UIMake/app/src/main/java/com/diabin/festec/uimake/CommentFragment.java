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

import com.diabin.festec.uimake.sample.CommentItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.news_infomation, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new CommentAdapter(getActivity(), generateDatas()));
        return view;
    }

    private List<CommentItem> generateDatas() {
        Resources resources = getContext().getResources();
        Drawable user_example = resources.getDrawable(R.drawable.user_example);
        Drawable female = resources.getDrawable(R.drawable.female);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        List<CommentItem> commentItems = new ArrayList<CommentItem>();
        for (int i = 0; i < 10; i++) {
            commentItems.add(new CommentItem(user_example, "name" + i, "contentTestcontentTestcontentTestcontentTestcontentTestcontentTest" + i, simpleDateFormat.format(date)));
        }
        return commentItems;
    }
}
