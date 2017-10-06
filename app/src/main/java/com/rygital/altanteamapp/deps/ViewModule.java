package com.rygital.altanteamapp.deps;

import com.rygital.altanteamapp.ui.home.fragments.ContentPresenter;
import com.rygital.altanteamapp.ui.home.fragments.IContentView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    private IContentView contentView;

    public ViewModule(IContentView contentView) {
        this.contentView = contentView;
    }

    @Provides ContentPresenter provideContentPresenter() {
        return new ContentPresenter(contentView);
    }
}
