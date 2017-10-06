package com.rygital.altanteamapp.ui.home.fragments;

import android.os.Bundle;
import android.util.Log;

import com.rygital.altanteamapp.models.Comment;
import com.rygital.altanteamapp.models.Photo;
import com.rygital.altanteamapp.models.Post;
import com.rygital.altanteamapp.models.Todo;
import com.rygital.altanteamapp.models.User;
import com.rygital.altanteamapp.network.NetworkError;
import com.rygital.altanteamapp.network.Service;
import com.rygital.altanteamapp.ui.base.BaseApp;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class ContentPresenter {
    @Inject
    Service service;
    private IContentView contentView;
    private CompositeSubscription subscriptions;

    @Inject
    public ContentPresenter(IContentView contentView) {
        this.contentView = contentView;
        this.subscriptions = new CompositeSubscription();

        BaseApp.getAppComponent().inject(this);
    }

    void getPost(String id) {
        Log.d("LOG_TAG", "getPost");
        if (id.equals("") || Integer.parseInt(id) <= 0) {
            contentView.showError("ID is not entered or <= 0");
            return;
        }
        if (Integer.parseInt(id) > 100) {
            contentView.showError("Post ID > 100");
            return;
        }

        Subscription subscription = service.getPostItem(id, new Service.GetProductListCallback<Post>() {
            @Override
            public void onSuccess(Post item) {
                if(item != null) contentView.showPost(item);
            }

            @Override
            public void onError(NetworkError networkError) {
                contentView.showError(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    void getComment(String id) {
        Log.d("LOG_TAG", "getComment");

        if (id.equals("") || Integer.parseInt(id) <= 0) {
            contentView.showError("ID is not entered or <= 0");
            return;
        }
        if (Integer.parseInt(id) > 500) {
            contentView.showError("Comment ID > 500");
            return;
        }

        Subscription subscription = service.getCommentItem(id, new Service.GetProductListCallback<Comment>() {
            @Override
            public void onSuccess(Comment item) {
                if(item != null) contentView.showComment(item);
            }

            @Override
            public void onError(NetworkError networkError) {
                contentView.showError(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    void getUsers() {
        Log.d("LOG_TAG", "getUsers");

        Subscription subscription = service.getUsersList("", new Service.GetProductListCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> list) {
                if(!list.isEmpty()) contentView.showUsers(list);
            }

            @Override
            public void onError(NetworkError networkError) {
                contentView.showError(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    void getPhoto(String id) {
        if (id.equals("") || Integer.parseInt(id) <= 0) {
            contentView.showError("ID is not entered or <= 0");
            return;
        }
        if (Integer.parseInt(id) > 5000) {
            contentView.showError("Photo ID > 5000");
            return;
        }

        Subscription subscription = service.getPhotoItem(id, new Service.GetProductListCallback<Photo>() {
            @Override
            public void onSuccess(Photo item) {
                if(item != null) contentView.showPhoto(item);
            }

            @Override
            public void onError(NetworkError networkError) {
                contentView.showError(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    void getTodo(String id) {
        if (id.equals("") || Integer.parseInt(id) <= 0) {
            contentView.showError("ID is not entered or <= 0");
            return;
        }
        if (Integer.parseInt(id) > 200) {
            contentView.showError("Todo ID > 200");
            return;
        }

        Subscription subscription = service.getTodoItem(id, new Service.GetProductListCallback<Todo>() {
            @Override
            public void onSuccess(Todo item) {
                if(item != null) contentView.showTodo(item);
            }

            @Override
            public void onError(NetworkError networkError) {
                contentView.showError(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }
}
