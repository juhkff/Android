package tools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.portmappingchat.R;

import java.util.List;

public class ChatAdapter extends BaseAdapter {
    private List<ChatMessage> chatMessages = null;
    private Context context = null;

    public ChatAdapter(List<ChatMessage> chatMessageList, Context context) {
        super();
        this.chatMessages = chatMessageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return chatMessages.get(position);
    }

    /**
     * 未使用
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.chatmessage_item,null);
            convertView.getTag();
        }
        */
        View view = LayoutInflater.from(context).inflate(R.layout.chatmessage_item, null);
        TextView left = view.findViewById(R.id.left);
        TextView right = view.findViewById(R.id.right);
        if (chatMessages.get(position).isMe()) {
            //是我发送的
            right.setText(chatMessages.get(position).getMessage());
        } else {
            //是对方发送的
            left.setText(chatMessages.get(position).getMessage());
        }
        return view;
    }
}
