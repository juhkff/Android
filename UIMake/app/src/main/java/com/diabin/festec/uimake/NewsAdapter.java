package com.diabin.festec.uimake;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.diabin.festec.uimake.bottombar.CommentBottombar;
import com.diabin.festec.uimake.sample.NewsItem;
import com.diabin.festec.uimake.topbar.Topbar_news;
import com.diabin.festec.uimake.topbar.Topbar_other;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class NewsAdapter extends BaseAdapter implements View.OnClickListener {
    private View parent;
    private List<NewsItem> newsItemList;
    private LayoutInflater inflater;
    private Context context;
    private boolean hasFocus;
//    private FragmentActivity activity;
    private MainFragment parentFragment;

    public NewsAdapter(View view, List<NewsItem> newsItemList, Context context,MainFragment parentFragment, boolean hasFocus) {
        super();
        this.parent = view;
        this.newsItemList = newsItemList;
        this.hasFocus = hasFocus;
        this.context = context;
//        this.activity = (FragmentActivity) context;
//        this.fragmentManager=fragmentManager;
        this.parentFragment =parentFragment;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //创建ViewHolder的对象。
        ViewHolder viewHolder = null;
        //获得Item位置上的数据。
        NewsItem newsItem = newsItemList.get(position);
        //convertview 优化
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_item, null);
            viewHolder = new ViewHolder();
            viewHolder.user_img = (ImageView) convertView.findViewById(R.id.user_img);
            viewHolder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            viewHolder.text_honor = (TextView) convertView.findViewById(R.id.text_honor);
            viewHolder.text_content = (TextView) convertView.findViewById(R.id.text_content);
            viewHolder.attached_img = (ImageView) convertView.findViewById(R.id.attached_img);
            viewHolder.button = convertView.findViewById(R.id.button);
            //加载操作区
            viewHolder.disLayout = convertView.findViewById(R.id.dis);
            viewHolder.commentLayout = convertView.findViewById(R.id.comment);
            viewHolder.likeLayout = convertView.findViewById(R.id.like);
            viewHolder.dis_text = convertView.findViewById(R.id.dis_text);
            viewHolder.comment_text = convertView.findViewById(R.id.comment_text);
            viewHolder.like_text = convertView.findViewById(R.id.like_text);

            viewHolder.linearLayout = convertView.findViewById(R.id.layout);
            //convertView为空时，ViewHolder将显示在ListView中的数据通过findViewById获取到。
            convertView.setTag(viewHolder);
        } else {
            //convertView不为空时，直接获取ViewHolder的Tag即可。
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.user_img.setImageDrawable(newsItem.getUser_img());
        viewHolder.text_name.setText(newsItem.getText_name());
        viewHolder.text_honor.setText(newsItem.getText_honor());
        viewHolder.text_content.setText(newsItem.getText_content());
        viewHolder.attached_img.setImageDrawable(newsItem.getAttached_img());
        //Test
        viewHolder.disLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.dis_text.setText(String.valueOf(newsItem.getDistributionNum()));
        viewHolder.comment_text.setText(String.valueOf(newsItem.getCommentNum()));
        viewHolder.like_text.setText(String.valueOf(newsItem.getLikeNum()));

        viewHolder.user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                //fragment跳转
                //注意v4包的配套使用
                fragment = new OtherFragment(parentFragment);
                Topbar_other topbar_other = new Topbar_other();
//                FragmentTransaction fragmentTransaction=parentFragment.fragmentManager.beginTransaction();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.addToBackStack("userInfo");
                parentFragment.setTopBar(topbar_other,"userInfo");
                //fragmentTransaction.replace(R.id.frame, fragment);
                parentFragment.replaceFrameLayout(fragment,"userInfo");
                parentFragment.commit();
                //MainActivity.hideBottomBar();

//                fragmentTransaction.commit();
            }
        });
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
            }
        });
        if (hasFocus) {
            viewHolder.button.setVisibility(View.VISIBLE);
        } else {
            viewHolder.button.setVisibility(View.INVISIBLE);
        }

        viewHolder.linearLayout.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout:
//                FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                Fragment fragment;
                fragment = new CommentFragment();
                //fm.beginTransaction().replace(R.id.main_top_bar, commentFragment).commit();
//                FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.addToBackStack("news");
//                fragmentTransaction.replace(R.id.frame, fragment);

                parentFragment.replaceFrameLayout(fragment,"news");
                Topbar_news topbar_news = new Topbar_news();
//                fragmentTransaction.replace(R.id.main_top_bar, fragment);
                parentFragment.setTopBar(topbar_news,"news");
                parentFragment.commit();
                CommentBottombar commentBottombar = new CommentBottombar(parent, context);
                FragmentManager fragmentManager=((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_activity=fragmentManager.beginTransaction();
                fragmentTransaction_activity.replace(R.id.main_bottom_bar, commentBottombar);
                fragmentTransaction_activity.commit();
                MainActivity.isHidden=true;
                break;
            default:
                break;
        }
    }

    static class ViewHolder {
        ImageView user_img;
        TextView text_name;
        TextView text_honor;
        TextView text_content;
        ImageView attached_img;
        Button button;
        TextView dis_text;
        TextView comment_text;
        TextView like_text;
        FlexboxLayout disLayout;
        FlexboxLayout commentLayout;
        FlexboxLayout likeLayout;

        LinearLayout linearLayout;
    }
}
