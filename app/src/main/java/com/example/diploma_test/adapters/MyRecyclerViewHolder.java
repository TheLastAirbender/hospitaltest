package com.example.diploma_test.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;

import org.w3c.dom.Text;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    private CardView view;
    private TextView header;
    private TextView newstext;

    public MyRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.recycleCardView);
        header = itemView.findViewById(R.id.recycleHeader);
        newstext = itemView.findViewById(R.id.recycleTextInfo);
    }

//    public CardView getView(){
//        return view;
//    }

    public void setText(String st){
        newstext.setText(st);
    }

    public void setHeader(String st) {
        header.setText(st);
    }
}
