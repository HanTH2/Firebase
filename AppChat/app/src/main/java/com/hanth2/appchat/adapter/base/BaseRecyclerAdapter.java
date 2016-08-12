package com.hanth2.appchat.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public abstract class BaseRecyclerAdapter<M, VH extends ViewHolder> extends Adapter<VH> implements Filterable {
    private BaseRecyclerAdapter.OnItemClickListener<M> itemClickListener;
    protected ArrayList<M> mItems = new ArrayList();
    protected ArrayList<M> filteredItems = new ArrayList();
    protected ArrayList<M> mBackupItems = new ArrayList<>();
    protected String filter = "";
    private View mEmptyView;
    protected Context mContext;
    protected int mLayoutId;
    protected int mCurrentPosition;

    public BaseRecyclerAdapter(Context context, int layoutId, ArrayList<M> items) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        mBackupItems = new ArrayList<>();
        if (items != null) {
            mBackupItems.addAll(items);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public ArrayList<M> getBackupItems() {
        return mBackupItems;
    }

    private AdapterDataObserver mEmptyObserver = new AdapterDataObserver() {
        public void onChanged() {
            super.onChanged();
            BaseRecyclerAdapter.this.checkIfEmpty();
        }
    };

    public BaseRecyclerAdapter() {
        this.setHasStableIds(true);
    }

    protected void onItemClick(View view, int position) {
        if(this.itemClickListener != null) {
            this.itemClickListener.onItemClick(view, this.getItem(position), position);
        }

    }

    protected boolean onQuery(M item, String query) {
        return true;
    }

    protected void onSort(List<M> items) {
    }

    protected void onFiltered() {
    }

    public void setEmptyView(View emptyView) {
        if(this.mEmptyView != null) {
            this.unregisterAdapterDataObserver(this.mEmptyObserver);
        }

        this.mEmptyView = emptyView;
        this.registerAdapterDataObserver(this.mEmptyObserver);
    }

    private void checkIfEmpty() {
        if(this.mEmptyView != null) {
            this.mEmptyView.setVisibility(this.getItemCount() > 0 ? 8 : 0);
        }

    }

    public void setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener<M> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void query(String query) {
        this.filter = query;
        this.filter();
        this.notifyDataSetChanged();
    }

    public void clearQuery() {
        this.filter = "";
        this.filter();
    }

    private void filter() {
        if(this.filter != null && !this.filter.isEmpty()) {
            if(this.filter == null) {
                this.filter = "";
            }

            this.filteredItems.clear();
            Iterator var1 = this.mItems.iterator();

            while(var1.hasNext()) {
                Object item = var1.next();
                if(this.onQuery((M) item, this.filter)) {
                    this.filteredItems.add((M) item);
                }
            }
        } else {
            this.filteredItems.clear();
            this.filteredItems.addAll(this.mItems);
        }

        this.onFiltered();
    }

    public void add(M object) {
        this.mItems.add(object);
        this.filter();
    }

    public void add(int index, M object) {
        this.mItems.add(index, object);
        this.filter();
    }

    public void addAll(Collection<? extends M> collection) {
        if(collection != null) {
            this.mItems.addAll(collection);
            this.filter();
        }

    }

    public void clear() {
        this.clearQuery();
        this.mItems.clear();
        this.filteredItems.clear();
        this.filter = null;
    }

    public void remove(M object) {
        this.mItems.remove(object);
        this.filter();
    }

    public M remove(int index) {
        Object item = this.mItems.remove(index);
        this.filter();
        return (M) item;
    }

    public void moveItem(int start, int end) {
        int max = Math.max(start, end);
        int min = Math.min(start, end);
        if(min >= 0 && max < this.mItems.size()) {
            Object item = this.mItems.remove(min);
            this.mItems.add(max, (M) item);
        }

        this.filteredItems.clear();
        this.filteredItems.addAll(this.mItems);
        this.notifyItemMoved(min, max);
    }

    public void sort(Comparator<M> comparator) {
        Collections.sort(this.mItems, comparator);
        this.filter();
    }

    public M getItem(int position) {
        return this.filteredItems.get(position);
    }

    public ArrayList<M> getmItems() {
        return this.filteredItems;
    }

    public ArrayList<M> getUnfilteredItems() {
        return this.mItems;
    }

    public int getItemCount() {
        return this.filteredItems != null ? this.filteredItems.size() : 0;
    }

    public void onBindViewHolder(VH vh, final int i) {
        mCurrentPosition = i;
        if(this.itemClickListener != null) {
            vh.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    BaseRecyclerAdapter.this.itemClickListener.onItemClick(v, BaseRecyclerAdapter.this.getItem(i), i);
                }
            });
        }

    }

    public interface OnItemClickListener<T> {
        void onItemClick(View var1, T var2, int var3);
    }
}
