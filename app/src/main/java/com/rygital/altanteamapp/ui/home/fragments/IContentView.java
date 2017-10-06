package com.rygital.altanteamapp.ui.home.fragments;

import com.rygital.altanteamapp.models.Comment;
import com.rygital.altanteamapp.models.Photo;
import com.rygital.altanteamapp.models.Post;
import com.rygital.altanteamapp.models.Todo;
import com.rygital.altanteamapp.models.User;
import com.rygital.altanteamapp.ui.base.IView;

import java.util.List;

public interface IContentView extends IView {
    void showPost(Post post);
    void showComment(Comment comment);
    void showUsers(List<User> list);
    void showPhoto(Photo photo);
    void showTodo(Todo todo);
}
