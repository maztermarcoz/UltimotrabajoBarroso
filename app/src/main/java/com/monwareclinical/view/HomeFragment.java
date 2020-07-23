package com.monwareclinical.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.monwareclinical.R;
import com.monwareclinical.adapter.MyEventAdapter;
import com.monwareclinical.model.Event;
import com.monwareclinical.util.Constants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    View root;
    Context context;

    ViewPager myEventsViewPager;
    MyEventAdapter eventAdapter;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_fragment, container, false);
        context = root.getContext();

        myEventsViewPager = root.findViewById(R.id.viewPagerMyEvents);

//        eventAdapter = new MyEventAdapter(null, context);
//        myEventsViewPager.setAdapter(eventAdapter);

        CircleImageView imgProfile = root.findViewById(R.id.imgProfile);

        Uri uri = Uri.parse("https://cdn.pixabay.com/photo/2020/05/15/11/49/pet-5173354_960_720.jpg");
        Glide.with(root).load(uri).placeholder(R.drawable.blank_user).dontAnimate().into(imgProfile);

        return root;
    }
}