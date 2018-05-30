package com.example.gridview.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.gridview.BaseApp;
import com.example.gridview.R;
import com.example.gridview.detail.DetailActivity;
import com.example.gridview.model.entity.ImageListData;
import com.example.gridview.model.entity.ImageListResponse;
import com.example.gridview.model.network.NetworkService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends BaseApp implements ListView {

    @BindView(R.id.recycler_view_list) RecyclerView gridList;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.retry) Button btnRetry;

    private Context context;
    private ListPresenter presenter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Inject
    public NetworkService networkService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDependencies().inject(this);
        renderView();

        presenter = new ListPresenter(networkService, this);
        presenter.getImageList();
    }

    public  void renderView(){
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        //Custom Action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_custom);

        //Set custom recycleview

        int numOfColumn = this.getResources().getConfiguration().orientation == 1 ? 3 : 5;
        recyclerViewLayoutManager = new GridLayoutManager(context, numOfColumn);
        gridList.setLayoutManager(recyclerViewLayoutManager);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Snackbar.make(findViewById(android.R.id.content), appErrorMessage, Snackbar.LENGTH_LONG).show();
        btnRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void getImageListSuccess(ImageListResponse imageListResponse) {

        ListAdapter adapter = new ListAdapter(getApplicationContext(), imageListResponse.getData(),
                new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(ImageListData Item) {
                        Bundle b = new Bundle();
                        b.putParcelable("ImageObj", Item);
                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });

        gridList.setAdapter(adapter);
    }

    @OnClick(R.id.retry)
    public void onClick(View v) {
        presenter.getImageList();
        progressBar.setVisibility(View.VISIBLE);
        btnRetry.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }
}
