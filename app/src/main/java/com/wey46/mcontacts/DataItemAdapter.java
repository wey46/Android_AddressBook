package com.wey46.mcontacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wey46.mcontacts.model.ContactItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    public static final String ITEM_KEY = "item_key";
    private List<ContactItem> mItems;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public ImageView imageView;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.itemNameText);
            imageView = itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }

    public DataItemAdapter(Context mContext, List<ContactItem> mItems) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {
        final ContactItem item = mItems.get(position);
        String displayName = item.getLName() + ", " + item.getFName();
        try {
            holder.tvName.setText(displayName);
            String imageFile = "defaultimg.png";
            InputStream inputStream = mContext.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.mView.setOnClickListener(view -> {
            //Toast.makeText(mContext,"You selected " + item.getLName()+" "+item.getFName(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(ITEM_KEY, item);
            mContext.startActivity(intent);
        });

        holder.mView.setOnLongClickListener(view -> {
            //Toast.makeText(mContext,"You have long clicked " + item.getLName()+" "+item.getFName(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, EditActivity.class);
            intent.putExtra(ITEM_KEY, item);
            mContext.startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
