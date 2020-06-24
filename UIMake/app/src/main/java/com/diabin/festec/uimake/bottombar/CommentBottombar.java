package com.diabin.festec.uimake.bottombar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.diabin.festec.uimake.MainActivity;
import com.diabin.festec.uimake.R;
import com.google.android.flexbox.FlexboxLayout;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

public class CommentBottombar extends Fragment implements View.OnClickListener {
    private View parentView;
    private View thisView;
    private EasyPopup mCirclePop;
    private Context context;
    private EditText myComment;
    private Button button;
    private String editString;

    private FlexboxLayout flexboxLayout;

    public CommentBottombar(View parentView, Context context) {
        this.parentView = parentView;
        this.context = context;
        WindowManager wm = (WindowManager) ((FragmentActivity) context)
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        mCirclePop = EasyPopup.create()
                .setContentView(context, R.layout.comment_popup)
                .setWidth(width)
                //.setAnimationStyle(R.style.RightPopAnim)
                //是否允许点击PopupWindow之外的地方消失
                .setAnchorView(parentView)
                .setFocusAndOutsideEnable(true)
                .apply();
        myComment = mCirclePop.findViewById(R.id.editText);

        button = mCirclePop.findViewById(R.id.editButton);
        editString = null;
    }

    private void showComment() {
        //mCirclePop.showAtAnchorView(parentView, YGravity.ALIGN_BOTTOM, XGravity.CENTER);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(parentView, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        mCirclePop.showAtAnchorView(thisView, YGravity.ABOVE, XGravity.CENTER);
        myComment.setSelected(true);
        myComment.setFocusable(true);
        myComment.setFocusableInTouchMode(true);
        myComment.requestFocus();
        button.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_bottom_bar, container, false);
        thisView = view;
        flexboxLayout = view.findViewById(R.id.comment);
        flexboxLayout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment:
                showComment();
                break;
            case R.id.editButton:
                Toast.makeText(context, "SubmitTest", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}
