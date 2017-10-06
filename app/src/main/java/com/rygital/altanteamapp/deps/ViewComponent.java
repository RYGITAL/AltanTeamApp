package com.rygital.altanteamapp.deps;

import com.rygital.altanteamapp.ui.home.fragments.ContentFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModule.class})
public interface ViewComponent {
    void inject(ContentFragment fragment);
}
