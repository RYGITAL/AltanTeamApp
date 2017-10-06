package com.rygital.altanteamapp.ui.home;

class HomePresenter {
    private final IHomeView homeView;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }

    void showContentFragment() {
        homeView.setContentFragment();
    }

    void showContactsFragment() {
        homeView.setContactsFragment();
    }
}
