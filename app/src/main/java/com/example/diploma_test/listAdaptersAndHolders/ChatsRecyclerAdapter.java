package com.example.diploma_test.listAdaptersAndHolders;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diploma_test.R;
import com.example.diploma_test.entity.Channel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatsRecyclerAdapter extends RecyclerView.Adapter<ChatsRecyclerViewHolder> {
    private List<Channel> chats;
    private final ItemClickListener itemClickListener;


    public ChatsRecyclerAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // для обработки нажатия на экране списка чатов
    public Channel getChats(int position){
        return chats.get(position);
    }

    public void setChats(List<Channel> dataset) {
        //this.random = new Random(seed);
        this.chats = dataset;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.chat_recycler_item;
    }

    @NonNull
    @Override
    public ChatsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ChatsRecyclerViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsRecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.setChatName(chats.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
                }
//                itemClickListener.onItemClick(chats.get(position));
//                System.out.println(chats.get(position).getName());
//                ChannelFragment channelFragment = new ChannelFragment();
//                AppCompatActivity activ = (AppCompatActivity)v.getContext();
//                activ.getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.chatListRelativeLayout, channelFragment, "findThisFragment")
//                        .addToBackStack(null)
//                        .commit();
//                onClickListener.onStateClick(state, position);
        });
    }

    @Override
    public int getItemCount() {
        if (this.chats != null) {return chats.size();} else {return 0;}
    }

    public interface ItemClickListener {
        public void onItemClick(int position);
    }
}
