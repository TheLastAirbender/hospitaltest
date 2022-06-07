package com.example.diploma_test.recyclers;

import android.view.View;
import android.widget.TextView;

import com.example.diploma_test.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ChatsRecyclerViewHolder extends RecyclerView.ViewHolder{
   private TextView chatName;
   private TextView lastMessage;
   private TextView timeSinceLastMessage;


   public ChatsRecyclerViewHolder(@NonNull View itemView) {
      super(itemView);
      chatName = itemView.findViewById(R.id.chatNameTextView);
      lastMessage = itemView.findViewById(R.id.lastMessageTextView);
      timeSinceLastMessage = itemView.findViewById(R.id.lastMessageTime);
   }
}
