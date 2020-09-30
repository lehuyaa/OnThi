package com.ldh.onthidaihoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.model.User;

import java.util.List;

public class RankUserAdapter extends RecyclerView.Adapter<RankUserAdapter.ViewHolder> {
    private Context context;
    private List<User> userList;


    public RankUserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RankUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankUserAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvSTT.setText(position+1+"");
        holder.tvName.setText(user.getName());
        holder.tvScore.setText(user.getScore());
        Glide.with(context).load(user.getImage()).error(R.mipmap.ic_launcher).into(holder.imgAvatar);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSTT,tvName,tvScore;
        private ImageView imgAvatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSTT = itemView.findViewById(R.id.tv_STT);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
