package com.emredirican.hbsample.content.display;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.emredirican.hbsample.R;
import com.emredirican.hbsample.content.Second;

public class SecondsViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.sv_item_display_content) SecondView secondView;

  public SecondsViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(Second second) {
    secondView.render(second.value);
  }
}
