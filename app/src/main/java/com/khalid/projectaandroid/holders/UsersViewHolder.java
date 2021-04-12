package com.khalid.projectaandroid.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.khalid.projectaandroid.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    public TextView email;
    public CardView mycard;
    public ImageView delete;
    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);
        email = itemView.findViewById(R.id.nom);
        mycard = itemView.findViewById(R.id.mycard);
        delete = itemView.findViewById(R.id.delete);

    }
}
