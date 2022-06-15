package com.example.diploma_test.listAdaptersAndHolders;

import android.view.View;
import android.widget.TextView;

import com.example.diploma_test.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatsRecyclerViewHolder extends RecyclerView.ViewHolder{
   private TextView chatName;
   private TextView lastMessage;
   private TextView timeSinceLastMessage;


   public ChatsRecyclerViewHolder(@NonNull View itemView, ChatsRecyclerAdapter.ItemClickListener itemClickListener) {
      super(itemView);
      this.chatName = itemView.findViewById(R.id.chatNameTextView);
      this.lastMessage = itemView.findViewById(R.id.lastMessageTextView);
      this.timeSinceLastMessage = itemView.findViewById(R.id.lastMessageTime);

      itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (itemClickListener != null) {
               int pos = getAdapterPosition();
               if (pos != RecyclerView.NO_POSITION) {
                  itemClickListener.onItemClick(pos);
               }
            }
         }
      });
   }

   public void setChatName(String name) {
      chatName.setText(name);
   }

   public void setLastMessage(TextView lastMessage) {
      this.lastMessage = lastMessage;
   }

   public void setTimeSinceLastMessage(TextView timeSinceLastMessage) {
      this.timeSinceLastMessage = timeSinceLastMessage;
   }
}
