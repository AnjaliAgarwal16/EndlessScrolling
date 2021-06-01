package com.example.endlessscrolling;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private List<String> mItemList;
    RecyclerViewAdapter(List<String> itemList) {
        mItemList = itemList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        }
        else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }
    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }
    @Override
    public int getItemViewType(int position) {
        int VIEW_TYPE_LOADING = 1;
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING :
                VIEW_TYPE_ITEM;
    }
    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.textViewItem);
        }
    }
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
    }
    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        String item = mItemList.get(position);
        viewHolder.tvItem.setText(item);
    }
}