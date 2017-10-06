package com.rygital.altanteamapp.deps;

import com.rygital.altanteamapp.network.NetworkModule;
import com.rygital.altanteamapp.ui.home.fragments.ContentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {
    void inject(ContentPresenter presenter);
}
