package com.example.amm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Event> eventList;

    public DataAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateAndTimeTextView;
        private TextView informationTextView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateAndTimeTextView = itemView.findViewById(R.id.dateTextView);
            informationTextView = itemView.findViewById(R.id.contentTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(Event event) {
            titleTextView.setText(event.getTitle());
            dateAndTimeTextView.setText(event.getDate());
            informationTextView.setText(event.getContent());

            if (event.getImageUrl() != null) {
                // Load and display the image using Glide
                Glide.with(itemView.getContext())
                        .load(event.getImageUrl())
                        .into(imageView);
            } else {
                // Hide the ImageView if there is no image
                imageView.setVisibility(View.GONE);
            }
        }
    }
}
