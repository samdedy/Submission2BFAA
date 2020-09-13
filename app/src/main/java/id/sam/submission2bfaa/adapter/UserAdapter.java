package id.sam.submission2bfaa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.sam.submission2bfaa.DetailActivity;
import id.sam.submission2bfaa.R;
import id.sam.submission2bfaa.model.search.Item;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public static final String DATA_SEARCH = "dataSearch";
    public static final String DATA_EXTRA = "dataExtra";

    private Context context;
    private List<Item> data = new ArrayList<>();

    public UserAdapter(Context context, List<Item> data) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtUsername.setText(data.get(position).getLogin());
        holder.txtUrl.setText(data.get(position).getHtmlUrl());
        Picasso.get().load(data.get(position).getAvatarUrl()).resize(100,100).into(holder.imgAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_SEARCH, Parcels.wrap(data.get(position)));
                intent.putExtra(DATA_EXTRA, bundle);
                context.startActivity(intent);
            }
        });
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
