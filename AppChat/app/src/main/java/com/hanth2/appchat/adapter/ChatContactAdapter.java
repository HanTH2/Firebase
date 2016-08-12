package com.hanth2.appchat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.hanth2.appchat.ApplicationSingleton;
import com.hanth2.appchat.R;
import com.hanth2.appchat.adapter.base.BaseRecyclerAdapter;
import com.hanth2.appchat.customview.CircleImageView;
import com.hanth2.appchat.datastore.entities.CHUserContact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class ChatContactAdapter extends BaseRecyclerAdapter<CHUserContact, RecyclerView.ViewHolder> implements Filterable {
    private Context mContext;

    public ChatContactAdapter(Context context, int layoutId, ArrayList<CHUserContact> items) {
        super(context, layoutId, items);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contact_item, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        super.onBindViewHolder(viewHolder, position);
        RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
        CHUserContact userContact = mItems.get(position);
        holder.contactName.setText(userContact.getFriend_name());
        Picasso.with(mContext).load(userContact.getAvatar_friend_name()).into(holder.avatar);
        holder.contactName.setTextColor(Color.BLACK);
    }

    @Override
    public Filter getFilter() {
//        ContactRecyclerFilter filterSearch = new ContactRecyclerFilter(this);
//        return filterSearch;
        return null;
    }

    public static class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        TextView contactName;
        CircleImageView avatar;

        public RecyclerItemViewHolder(View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            contactName = (TextView) itemView.findViewById(R.id.tv_username);
        }
    }
}
