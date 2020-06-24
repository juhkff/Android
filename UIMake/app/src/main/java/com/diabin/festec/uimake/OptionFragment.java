package com.diabin.festec.uimake;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OptionFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private MainFragment parentFragment;

    private TextView name;
    private TextView passWord;
    private TextView honor;
    private TextView about;

    public OptionFragment(MainFragment mainFragment, Context context) {
        this.parentFragment = mainFragment;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.option_list, container, false);
        name = view.findViewById(R.id.name);
        passWord = view.findViewById(R.id.passWord);
        honor = view.findViewById(R.id.honor);
        about = view.findViewById(R.id.about);
        listenerRegister();
        return view;
    }

    private void listenerRegister() {
        name.setOnClickListener(this);
        passWord.setOnClickListener(this);
        honor.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name:
                Toast.makeText(context, "name", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
