package com.hencoder.a25_mvc_mvp_mvvm.mvc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hencoder.a25_mvc_mvp_mvvm.R;

import androidx.annotation.Nullable;
import com.hencoder.a25_mvc_mvp_mvvm.data.DataCenter;

public class DataView extends LinearLayout implements MvcActivity.IView {
    EditText data1View;
    EditText data2View;

    public DataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        data1View = findViewById(R.id.data1View);
        data2View = findViewById(R.id.data2View);
    }

    @Override
    public void showViewWithData() {
        String[] data = DataCenter.getData();
        data1View.setText(data[0]);
        data2View.setText(data[1]);
    }
}
