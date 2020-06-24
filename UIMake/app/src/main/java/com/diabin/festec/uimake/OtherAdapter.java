package com.diabin.festec.uimake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diabin.festec.uimake.sample.OtherNewsItem;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {
    private List<OtherNewsItem> newsItemList;
    //    private LayoutInflater inflater;
    private Context context;
    private MainFragment parentFragment;
    private boolean hasFocus;

    public OtherAdapter(Context context, MainFragment mainFragment, List<OtherNewsItem> newsItemList, boolean hasFocus) {
        super();
        this.newsItemList = newsItemList;
        this.hasFocus = hasFocus;
        this.context = context;
        this.parentFragment = mainFragment;
        //this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.other_news, parent, false);
        return new ViewHolder(view/*, context*/);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        OtherNewsItem otherNewsItem = newsItemList.get(position);
        holder.text_time.setText(otherNewsItem.getTime());
        holder.text_content.setText(otherNewsItem.getText_content());
        holder.attached_img.setImageDrawable(otherNewsItem.getAttached_img());
        holder.dis_text.setText(String.valueOf(otherNewsItem.getDistributionNum()));
        holder.comment_text.setText(String.valueOf(otherNewsItem.getCommentNum()));
        holder.like_text.setText(String.valueOf(otherNewsItem.getLikeNum()));
        //Test
        holder.disLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_time;
        public TextView text_content;
        public ImageView attached_img;
        TextView dis_text;
        TextView comment_text;
        TextView like_text;
        FlexboxLayout disLayout;
        FlexboxLayout commentLayout;
        FlexboxLayout likeLayout;

        public ViewHolder(View view/*, Context context*/) {
            super(view);
            text_time = view.findViewById(R.id.time);
            text_content = view.findViewById(R.id.text_content);
            attached_img = view.findViewById(R.id.attached_img);
            dis_text = view.findViewById(R.id.bottom_bar_text_1);
            comment_text = view.findViewById(R.id.bottom_bar_text_2);
            like_text = view.findViewById(R.id.bottom_bar_text_3);
            disLayout = view.findViewById(R.id.bottom_bar_1_btn);
            commentLayout = view.findViewById(R.id.bottom_bar_2_btn);
            likeLayout = view.findViewById(R.id.bottom_bar_3_btn);
        }
    }
}
