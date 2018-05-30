package com.example.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gridview.dependencies.DaggerDependencies;
import com.example.gridview.dependencies.Dependencies;
import com.example.gridview.model.network.NetworkClient;

import java.io.File;

/**
 * Created by Mustafizur Rahman on 5/26/18.
 */
public class BaseApp  extends AppCompatActivity{
    Dependencies dependencies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        dependencies = DaggerDependencies.builder().networkClient(new NetworkClient(cacheFile)).build();
    }

    public Dependencies getDependencies() {
        return dependencies;
    }
}
