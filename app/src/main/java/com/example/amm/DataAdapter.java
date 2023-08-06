package com.example.amm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
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
            dateAndTimeTextView = itemView.findViewById(R.id.dateEditText);
            informationTextView = itemView.findViewById(R.id.contentEditText);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(Event event) {
            titleTextView.setText(event.getTitle());
            dateAndTimeTextView.setText(event.getDate());
            informationTextView.setText(event.getContent());

            // Load and display the image using Picasso (or your preferred image-loading library)
            if (event.getImageUrl() != null) {
                Picasso.get().load(event.getImageUrl()).into(imageView);
            } else {
                // If there is no image, you can set a placeholder or hide the ImageView
                imageView.setVisibility(View.GONE);
            }
        }
    }
}
