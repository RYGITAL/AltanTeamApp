package com.rygital.altanteamapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rygital.altanteamapp.deps.AppComponent;
import com.rygital.altanteamapp.deps.DaggerAppComponent;
import com.rygital.altanteamapp.network.NetworkModule;

import java.io.File;

public class BaseApp extends AppCompatActivity {
    private static AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        appComponent = DaggerAppComponent.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}