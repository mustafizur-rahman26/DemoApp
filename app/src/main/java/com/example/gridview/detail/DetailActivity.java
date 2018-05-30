package com.example.gridview.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gridview.R;
import com.example.gridview.model.entity.ImageListData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.image) ImageView ivImage;
    @BindView(R.id.site) TextView tvSite;
    @BindView(R.id.copyright) TextView tvCopyright;
    @BindView(R.id.url) TextView tvUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get data from intent value
        Bundle b = getIntent().getExtras();
        ImageListData data = b.getParcelable("ImageObj");

        renderView(data);
    }

    public void renderView(ImageListData data){
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this)
                .load(data.getUrl())
                .into(ivImage);
        tvSite.setText(data.getSite());
        tvCopyright.setText(data.getCopyright());
        tvUrl.setText(data.getUrl());
    }
}
