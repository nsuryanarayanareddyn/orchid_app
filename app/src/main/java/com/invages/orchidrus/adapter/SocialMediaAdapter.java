package com.invages.orchidrus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.invages.orchidrus.R;

import java.util.List;

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.MyViewAdapter> {

    List<SocialMediaItem> list;

    public SocialMediaAdapter(List<SocialMediaItem> list) {
        this.list = list;
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public MyViewAdapter(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_media_list_item, parent, false);
        return new MyViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {
        SocialMediaItem item = list.get(position);
        holder.title.setText(item.getName());
        holder.image.setBackgroundResource(item.getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
