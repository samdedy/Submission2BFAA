package id.sam.submission2bfaa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.sam.submission2bfaa.R;
import id.sam.submission2bfaa.model.FollowersModel;
import id.sam.submission2bfaa.model.FollowingModel;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {

    Context context;
    List<FollowingModel> data = new ArrayList<>();

    public FollowingAdapter(Context context, List<FollowingModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_users,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUsername.setText(data.get(position).getLogin());
        holder.txtUrl.setText(data.get(position).getHtmlUrl());
        Picasso.get().load(data.get(position).getAvatarUrl()).resize(100,100).into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsername, txtUrl;
        CircleImageView imgAvatar;
        public ViewHolder(@NonNull View v) {
            super(v);
            txtUsername = v.findViewById(R.id.txtUsername);
            txtUrl = v.findViewById(R.id.txtUrl);
            imgAvatar = v.findViewById(R.id.imgAvatar);
        }
    }
}
