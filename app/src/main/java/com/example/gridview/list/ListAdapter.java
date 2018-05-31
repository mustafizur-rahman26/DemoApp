package com.example.gridview.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gridview.R;
import com.example.gridview.model.entity.ImageListData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<ImageListData> data;
    private Context context;

    public ListAdapter(Context context, List<ImageListData> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.tvSite.setText(data.get(position).getSite());
        String images = data.get(position).getUrl();

        Glide.with(context)
                .load(images)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image_placeholder))
                .into(holder.imageBackground);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(ImageListData Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.site) TextView tvSite;
        @BindView(R.id.image) ImageView imageBackground;

        private final String TAG = ViewHolder.class.getSimpleName();

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void click(final ImageListData imageListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(imageListData);
                }
            });
        }
    }
}
