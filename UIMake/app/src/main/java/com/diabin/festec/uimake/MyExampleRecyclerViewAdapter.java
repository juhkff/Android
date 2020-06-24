package com.diabin.festec.uimake;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diabin.festec.uimake.sample.ExampleItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ExampleItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyExampleRecyclerViewAdapter extends RecyclerView.Adapter<MyExampleRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private MainFragment parentFragment;
    private List<ExampleItem> mValues;

    public MyExampleRecyclerViewAdapter(Context context, List<ExampleItem> items, MainFragment mainFragment) {
        this.context = context;
        this.parentFragment = mainFragment;
        mValues = items;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_example, parent, false);
        return new ViewHolder(view/*, context*/);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ExampleItem exampleItem = mValues.get(position);
        holder.textView.setText(exampleItem.itemNumber);
        holder.honorView.setText(exampleItem.itemNumber2);
        holder.imageView.setImageDrawable(exampleItem.image);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        public TextView textView;
        public TextView honorView;
        public ImageView imageView;
        //public ExampleItem mItem;

        public ViewHolder(View view/*, Context context*/) {
            super(view);
            //mView = view;
            textView = (TextView) view.findViewById(R.id.item_number);
            honorView = (TextView) view.findViewById(R.id.item_number2);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}