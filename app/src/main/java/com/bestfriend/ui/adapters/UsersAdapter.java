package com.bestfriend.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bestfriend.OnItemClickListener;
import com.bestfriend.R;
import com.bestfriend.model.User;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Avishay on 22/02/2018.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {


    private Context mContext;
    private List<User> mList;
    private  OnItemClickListener mOnItemClickListener;

    private int rowLayout_REGULAR = R.layout.user_list_item;


    // Provide a suitable constructor (depends on the kind of dataset)
    public UsersAdapter(Context context, List<User> list, OnItemClickListener listener) {
        this.mContext = context;
        this.mList = list;
        this.mOnItemClickListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // createContactsObservable a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout_REGULAR, parent, false);
        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final User item = mList.get(position);

        holder.tvUserName.setText(item.getFullName());

        Glide.with(mContext)
                .load(item.getImageProfile())
                .into(holder.ivUserImg);
    }

    public void addItem(User user) {
        mList.add(user);
        notifyItemInserted(getItemCount() - 1);
    }


    public User getItem(int position) {
        if (mList != null)
            return mList.get(position);
        return null;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_user_image)
        ImageView ivUserImg;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(ViewHolder.this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickListener.onItemClick(view,  mList.get(getAdapterPosition()));
        }
    }

}