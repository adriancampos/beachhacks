package com.test.android.beachhacks.ui;

import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.android.beachhacks.R;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SavedItem}
 */
public class SavedItemRecyclerViewAdapter extends RecyclerView.Adapter<SavedItemRecyclerViewAdapter.ViewHolder> {



    private final List<SavedItem> mSavedItems;

    public SavedItemRecyclerViewAdapter(List<SavedItem> items) {
        mSavedItems = items;
    }
    public SavedItemRecyclerViewAdapter() {
        mSavedItems = new ArrayList<>();
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


        // Set color of bar
        Palette p = Palette.from(mSavedItems.get(position).image).generate(); // TODO Should NOT do this on the main thread or more than once
        holder.mLinearLayout.setBackgroundColor(p.getDominantColor(Color.BLACK));

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    public void addItem(SavedItem savedItem) {
        mSavedItems.add(savedItem);
        notifyDataSetChanged();
    }
    public void addItems(List<SavedItem> savedItems) {
        mSavedItems.addAll(savedItems);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mSavedItems.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mSavedItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;
        public final LinearLayout mLinearLayout;
        public SavedItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.imageView);
            mTextView = view.findViewById(R.id.text);
            mLinearLayout = view.findViewById(R.id.linearLayout);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText() + "'";
        }
    }
}
