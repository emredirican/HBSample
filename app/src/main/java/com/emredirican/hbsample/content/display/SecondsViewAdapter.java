package com.emredirican.hbsample.content.display;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.emredirican.hbsample.R;
import com.emredirican.hbsample.content.Second;
import java.util.ArrayList;
import java.util.List;

public class SecondsViewAdapter extends RecyclerView.Adapter<SecondsViewHolder> {

  private List<Second> secondList;

  public SecondsViewAdapter(List<Second> secondList) {
    this.secondList = secondList;
  }

  @Override public SecondsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_display_content, parent, false);
    return new SecondsViewHolder(view);
  }

  @Override public void onBindViewHolder(SecondsViewHolder holder, int position) {
    holder.bind(secondList.get(position));
  }

  @Override public int getItemCount() {
    return secondList.size();
  }

  public void update(List<Second> secondList) {
    this.secondList = new ArrayList<>(secondList);
    notifyDataSetChanged();
  }

  public List<Second> dataListSnapshot() {
    return new ArrayList<>(this.secondList);
  }
}
