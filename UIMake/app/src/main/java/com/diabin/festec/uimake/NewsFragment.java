package com.diabin.festec.uimake;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.diabin.festec.uimake.sample.NewsItem;
import com.diabin.festec.uimake.topbar.Topbar_1;

import java.util.ArrayList;

public class NewsFragment extends Fragment{
    private ArrayList<NewsItem> newsList;
    private ListView listView;
    private NewsAdapter newsAdapter;
    private boolean hasFocus;
    private MainFragment mainFragment;

    public NewsFragment(MainFragment mainFragment,boolean hasFocus) {
        this.mainFragment=mainFragment;
        this.hasFocus = hasFocus;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        newsList = new ArrayList<NewsItem>();
//        getListFunction();
        //newsAdapter = new NewsAdapter(newsList, getActivity(), hasFocus,getActivity());
        //listView.setAdapter(newsAdapter);
//        if (newsAdapter != null)
//            setListAdapter(newsAdapter);
//        else
//            Log.e("初始化失败", "newsAdapter/NewsFragment");
    }

    private void getListFunction() {
        for (int i = 0; i < 2; i++) {
            Resources resources = getContext().getResources();
            Drawable user_example = resources.getDrawable(R.drawable.user_example);
            Drawable female = resources.getDrawable(R.drawable.female);
            newsList.add(new NewsItem("1",
                    user_example,
                    "text_name",
                    "text_honor",
                    "text_content",
                    female,
                    20, 21, 22));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        //Context context = view.getContext();
        listView = (ListView) view.findViewById(R.id.list);
        //LayoutInflater layoutInflater=getLayoutInflater();
        newsList = new ArrayList<NewsItem>();
        getListFunction();
        newsAdapter = new NewsAdapter(view,newsList, getActivity(),mainFragment, hasFocus);
        listView.setAdapter(newsAdapter);
        return view;
    }
}
