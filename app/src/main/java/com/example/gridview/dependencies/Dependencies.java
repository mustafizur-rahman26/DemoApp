package com.example.gridview.dependencies;


import com.example.gridview.list.ListActivity;
import com.example.gridview.model.network.NetworkClient;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mustafizur Rahman on 5/27/18.
 */
@Singleton
@Component(modules = {NetworkClient.class,})
public interface Dependencies {
    void inject(ListActivity listActivity);
}
