package com.diabin.festec.uimake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diabin.festec.uimake.sample.CommentItem;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<CommentItem> commentItems;

    public CommentAdapter(Context context, List<CommentItem> commentItems) {
        super();
        this.context = context;
        this.commentItems = commentItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentItem commentItem=commentItems.get(position);
        holder.user_img.setImageDrawable(commentItem.getUser_img());
        holder.name.setText(String.valueOf(commentItem.getName()));
        holder.content.setText(String.valueOf(commentItem.getContent()));
        holder.time.setText(String.valueOf(commentItem.getTime()));
    }

    @Override
    public int getItemCount() {
        return commentItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView user_img;
        public TextView name;
        public TextView content;
        public TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_img=itemView.findViewById(R.id.user_img);
            name=itemView.findViewById(R.id.name);
            content=itemView.findViewById(R.id.content);
            time=itemView.findViewById(R.id.time);
        }
    }
}
