package com.hanth2.appchat.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hanth2.appchat.R;
import com.hanth2.appchat.customview.CircleImageView;

/**
 * Created by HanTH2 on 8/15/2016.
 */
public class MessageViewHolder extends RecyclerView.ViewHolder {
    public TextView name_sender;
    public TextView time_sender;
    public CircleImageView avatar_sender;
    public TextView message_sender;

    public MessageViewHolder(View itemView) {
        super(itemView);
        avatar_sender = (CircleImageView) itemView.findViewById(R.id.avatar_sender);
        name_sender = (TextView) itemView.findViewById(R.id.name_sender);
        time_sender = (TextView) itemView.findViewById(R.id.time_sender);
        message_sender = (TextView) itemView.findViewById(R.id.msg_sender);
    }
}
