package com.khalid.projectaandroid.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.khalid.projectaandroid.R;

public class ClassesViewHolder extends RecyclerView.ViewHolder{
    public TextView nom;
    public CardView mycard;
    public ImageView delete;
    public ClassesViewHolder(@NonNull View itemView) {
        super(itemView);
        nom = itemView.findViewById(R.id.nom);
        mycard = itemView.findViewById(R.id.mycard);
        delete = itemView.findViewById(R.id.delete);
    }
}