package com.example.diploma_test.recyclers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;

public class DashboardRecyclerViewHolder extends RecyclerView.ViewHolder {
    private CardView view;
    private TextView author;
    private TextView postInfoAndDate;
    private TextView postText;

    public DashboardRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.recycleCardView);
        author = itemView.findViewById(R.id.recycleHeader);
        postInfoAndDate = itemView.findViewById(R.id.recyclePostInfoAndDate);
        postText = itemView.findViewById(R.id.recyclePostText);
    }

//    public CardView getView(){
//        return view;
//    }

    public void setPostInfoAndDate (String st) {postInfoAndDate.setText(st);}

    public void setPostText(String st){
        postText.setText(st);
    }

    public void setPostAuthor(String st) {
        author.setText(st);
    }
}
