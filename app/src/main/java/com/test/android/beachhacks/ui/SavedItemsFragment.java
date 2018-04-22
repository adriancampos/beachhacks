package com.test.android.beachhacks.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.android.beachhacks.R;
import com.test.android.beachhacks.ui.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class SavedItemsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private SavedItemRecyclerViewAdapter recyclerViewAdapter = new SavedItemRecyclerViewAdapter();
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SavedItemsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SavedItemsFragment newInstance(int columnCount) {
        SavedItemsFragment fragment = new SavedItemsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        // Load items from database
        databaseHelper = new DatabaseHelper();
        databaseHelper.loadFromDBAsync(new DatabaseHelper.ItemsLoadedCallback() {
            @Override
            public void onItemsLoaded(final ArrayList<SavedItem> items) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerViewAdapter.addItems(items);
                    }
                });
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saveditem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(recyclerViewAdapter);

        }
        return view;
    }


    /**
     * Adds savedItem to the recyclerview, scrolls the recyclerview, and saves to database.
     * @param savedItem
     */
    public void addSavedItem(SavedItem savedItem) {
        recyclerViewAdapter.addItem(savedItem);

        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(recyclerViewAdapter.getItemCount());
        }

        databaseHelper.addItemToDBAsync(savedItem);
    }


}
