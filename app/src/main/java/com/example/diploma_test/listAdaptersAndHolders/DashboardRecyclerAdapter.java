package com.example.diploma_test.listAdaptersAndHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.utility_pojos.NewsInNewsfeed;

import java.util.List;
import java.util.Random;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerViewHolder>{
    private Random random;
//    private List<String> dataset;
    private List<NewsInNewsfeed> news;

    // ัะตัั
    public NewsInNewsfeed getNews(int position){
      return news.get(position);
    }

    public void setNews(List<NewsInNewsfeed> dataset) {
        //this.random = new Random(seed);
        this.news = dataset;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.feed_recycle_item;
    }

    @NonNull
    @Override
    public DashboardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new DashboardRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerViewHolder holder, int position) {
        //Integer newInt = dataset.get(position);
        System.out.println(position);
        System.out.println(news.get(position).getAuthor());
        //try{ holder.setHeader(dataset.get(position).getHeader());} catch (Exception e) {System.out.println(e);};
        holder.setPostAuthor(news.get(position).getAuthor());
        holder.setPostInfoAndDate(news.get(position).getDatetimeposted().toString());
        holder.setPostText(news.get(position).getMessage());
//        holder.setText(dataset.get(position));
//        holder.flagView.setImageResource(state.getFlagResource());
//        holder.nameView.setText(state.getName());
//        holder.capitalView.setText(state.getCapital());
    }

    @Override
    public int getItemCount() {
        // ััะดะฐ ัะบะพัะตะต ะฒัะตะณะพ ะธะท ViewModel ะฒะฟะธัะฐัั ะบะพะปะธัะตััะฒะพ
        if (this.news != null) {return news.size();} else {return 0;}
    }
}