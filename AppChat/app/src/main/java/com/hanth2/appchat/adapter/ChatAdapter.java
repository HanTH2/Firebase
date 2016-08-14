package com.hanth2.appchat.adapter;

import android.content.Context;
import android.net.Uri;
import android.renderscript.Sampler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanth2.appchat.R;
import com.hanth2.appchat.adapter.base.BaseRecyclerAdapter;
import com.hanth2.appchat.customview.CircleImageView;
import com.hanth2.appchat.datastore.entities.CHChatMessage;
import com.hanth2.appchat.utils.ConvertTime;
import com.squareup.picasso.Picasso;

import java.io.File;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by zero on 14/08/2016.
 */
public class ChatAdapter extends BaseRecyclerAdapter<CHChatMessage, RecyclerView.ViewHolder> {
    private static final String TAG = ChatAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private Context context;
    private static final int TYPE_MESSAGE = 0;
    private static final int TYPE_IMAGE = 1;

    public ChatAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, String.valueOf(viewType));
        View view = inflater.inflate(R.layout.recycler_message_item, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CHChatMessage chChatMessage = getItem(position);
            Log.d(TAG, "send text");
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            holder.name_sender.setText(chChatMessage.getSender());
            holder.time_sender.setText(ConvertTime.convertTimestamp(chChatMessage.getTime()));
            holder.message_sender.setText(chChatMessage.body);
            Picasso.with(context).load(chChatMessage.getAvatar_friend_name()).into(holder.avatar_sender);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        TextView name_sender;
        TextView time_sender;
        CircleImageView avatar_sender;
        TextView message_sender;

        public RecyclerItemViewHolder(View itemView) {
            super(itemView);
            avatar_sender = (CircleImageView) itemView.findViewById(R.id.avatar_sender);
            name_sender = (TextView) itemView.findViewById(R.id.name_sender);
            time_sender = (TextView) itemView.findViewById(R.id.time_sender);
            message_sender = (TextView) itemView.findViewById(R.id.msg_sender);
        }

    }

    @Override
    public int getItemViewType(int position) {
        CHChatMessage chChatMessage = getItem(position);
        Log.d(TAG, "get Item send text type : " + chChatMessage.getType());
        return TYPE_MESSAGE;
    }

//    @Override
//    public int getItemCount() {
//        return super.getItemCount() + 1;
//    }

}
