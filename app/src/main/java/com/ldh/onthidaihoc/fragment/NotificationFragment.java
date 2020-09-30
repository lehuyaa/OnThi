package com.ldh.onthidaihoc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.adapter.NotificationAdapter;
import com.ldh.onthidaihoc.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    private static NotificationFragment ISTANCE;

    public static NotificationFragment getInstance() {
        if (ISTANCE == null) {
            ISTANCE = new NotificationFragment();
        }
        return ISTANCE;
    }

    public NotificationFragment() {

    }

    private RecyclerView rvNotification;
    private NotificationAdapter adapter;
    private List<Notification> notificationList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_notification, container, false);
        rvNotification = view.findViewById(R.id.rv_notification);
        notificationList = new ArrayList<>();
        notificationList.add(new Notification("https://upload.wikimedia.org/wikipedia/commons/8/84/C-Seedorf.jpg", "Claudio Seedorf đã gửi lời mời kết bạn.", "54 phút"));
        notificationList.add(new Notification("https://s.hs-data.com/bilder/spieler/gross/1344.jpg", "Nesta đã gửi lời mời kết bạn.", "54 phút"));
        notificationList.add(new Notification("https://cdn.britannica.com/87/139487-050-98D3449D/Kaka-2009.jpg", "Kaka đã gửi lời mời kết bạn.", "54 phút"));
        notificationList.add(new Notification("https://s3.amazonaws.com/charitycdn/cache/resizedcrop-a74c09432b389e112ad1575ed0c0f31e-800x800.jpg", "Maldini đã gửi lời mời kết bạn.", "54 phút"));
        notificationList.add(new Notification("https://s3.amazonaws.com/charitycdn/cache/resizedcrop-a74c09432b389e112ad1575ed0c0f31e-800x800.jpg", "Maldini đã gửi lời mời kết bạn.", "54 phút"));
        notificationList.add(new Notification("https://s3.amazonaws.com/charitycdn/cache/resizedcrop-a74c09432b389e112ad1575ed0c0f31e-800x800.jpg", "Maldini đã gửi lời mời kết bạn.", "54 phút"));
        notificationList.add(new Notification("https://s3.amazonaws.com/charitycdn/cache/resizedcrop-a74c09432b389e112ad1575ed0c0f31e-800x800.jpg", "Maldini đã gửi lời mời kết bạn.", "54 phút"));

        adapter = new NotificationAdapter(getContext(), notificationList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        rvNotification.addItemDecoration(dividerHorizontal);
        rvNotification.setLayoutManager(linearLayoutManager);
        rvNotification.setAdapter(adapter);


        return view;
    }
}
