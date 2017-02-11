package com.emredirican.hbsample.content.display;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class SecondView extends TextView {

  public SecondView(Context context) {
    super(context);
  }

  public SecondView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SecondView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void render(int value) {
    setText(String.valueOf(value));
  }
}
