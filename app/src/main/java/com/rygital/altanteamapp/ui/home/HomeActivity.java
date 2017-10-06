package com.rygital.altanteamapp.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.rygital.altanteamapp.R;
import com.rygital.altanteamapp.ui.base.BaseApp;
import com.rygital.altanteamapp.ui.home.fragments.ContactsFragment;
import com.rygital.altanteamapp.ui.home.fragments.ContentFragment;


import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseApp implements IHomeView{
    private static String TAG_CONTENT = "content";
    private static String TAG_CONTACTS = "contacts";

    @BindView(R.id.navigation) BottomNavigationView navigation;

    private FragmentManager fragmentManager;
    private HomePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new HomePresenter(this);

        fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (fragmentManager.findFragmentByTag(TAG_CONTENT) == null)
                            presenter.showContentFragment();
                        return true;
                    case R.id.navigation_contacts:
                        if (fragmentManager.findFragmentByTag(TAG_CONTACTS) == null)
                            presenter.showContactsFragment();
                        return true;
                }
                return false;
            }
        });

        Fragment fragment = fragmentManager.findFragmentByTag(TAG_CONTENT);
        if (fragment == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, new ContentFragment(), TAG_CONTENT);
            transaction.commit();
        }
    }

    @Override
    public void setContentFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left,
                R.anim.slide_out_right, R.anim.slide_in_right,
                R.anim.slide_out_left);
        transaction.replace(R.id.container, new ContentFragment(), TAG_CONTENT);
        transaction.commit();
    }

    @Override
    public void setContactsFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,
                R.anim.slide_out_left, R.anim.slide_in_left,
                R.anim.slide_out_right);
        transaction.replace(R.id.container, new ContactsFragment(), TAG_CONTACTS);
        transaction.commit();
    }
}
