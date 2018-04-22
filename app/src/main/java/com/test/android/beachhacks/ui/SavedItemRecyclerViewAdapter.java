package com.test.android.beachhacks.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.android.beachhacks.R;
import com.test.android.beachhacks.ui.SavedItemsFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SavedItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class SavedItemRecyclerViewAdapter extends RecyclerView.Adapter<SavedItemRecyclerViewAdapter.ViewHolder> {



    private final List<SavedItem> mSavedItems;
    private final OnListFragmentInteractionListener mListener;

    public SavedItemRecyclerViewAdapter(List<SavedItem> items, OnListFragmentInteractionListener listener) {
        mSavedItems = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_saveditem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mSavedItems.get(position);
        holder.mImageView.setImageBitmap(mSavedItems.get(position).image);
        holder.mTextView.setText(mSavedItems.get(position).name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSavedItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;
        public SavedItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.imageView);
            mTextView = view.findViewById(R.id.text);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText() + "'";
        }
    }
}
