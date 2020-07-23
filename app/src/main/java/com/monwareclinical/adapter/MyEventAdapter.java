package com.monwareclinical.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.monwareclinical.R;
import com.monwareclinical.model.Event;

import java.util.List;

public class MyEventAdapter extends PagerAdapter {

    List<Event> events;
    LayoutInflater layoutInflater;
    Context context;


    public MyEventAdapter(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_my_event, container, false);

        ImageView imgBanner;
        TextView txtTitle;
        TextView txtDescription;

        imgBanner = view.findViewById(R.id.imgBanner);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);


        txtTitle.setText(events.get(position).getTitle());

        String description = "Fecha: " + events.get(position).getDate() + "\n" +
                events.get(position).getPlace().getName() + "\n" +
                events.get(position).getPlace().getState() + ", " + events.get(position).getPlace().getCity() + "\n" +
                events.get(position).getPlace().getStreetAddress() + " #" + events.get(position).getPlace().getExtNumber();

        txtDescription.setText(description);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
