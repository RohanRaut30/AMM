package com.example.amm.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amm.DataClass;
import com.example.amm.FullScreenImageActivity;
import com.example.amm.R;

import java.util.ArrayList;

public class MyImageAdapter extends BaseAdapter {

    private ArrayList<DataClass> dataList;
    private Context context;
    LayoutInflater layoutInflater;

    public MyImageAdapter(Context context, ArrayList<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.gridImage = view.findViewById(R.id.gridImage);
            viewHolder.gridCaption = view.findViewById(R.id.gridCaption);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        DataClass data = dataList.get(i);

        Glide.with(context).load(data.getImageURL()).into(viewHolder.gridImage);
        viewHolder.gridCaption.setText(data.getCaption());

        viewHolder.gridImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imageUrlsList = new ArrayList<>();
                for (DataClass data : dataList) {
                    imageUrlsList.add(data.getImageURL());
                }

                Intent intent = new Intent(context, FullScreenImageActivity.class);
                intent.putStringArrayListExtra("image_urls", imageUrlsList);
                intent.putExtra("current_index", i);
                context.startActivity(intent);
            }
        });

        return view;
    }

    private static class ViewHolder {
        ImageView gridImage;
        TextView gridCaption;
    }
}
