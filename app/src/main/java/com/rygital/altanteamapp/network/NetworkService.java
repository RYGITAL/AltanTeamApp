package com.rygital.altanteamapp.network;

import com.rygital.altanteamapp.models.Comment;
import com.rygital.altanteamapp.models.Photo;
import com.rygital.altanteamapp.models.Post;
import com.rygital.altanteamapp.models.Todo;
import com.rygital.altanteamapp.models.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface NetworkService {
    @GET("/posts/{number}")
    Observable<Post> getPost(@Path("number") String number);
    @GET("/comments/{number}")
    Observable<Comment> getComment(@Path("number") String number);
    @GET("/users/{number}")
    Observable<List<User>> getUsers(@Path("number") String number);
    @GET("/photos/{number}")
    Observable<Photo> getPhoto(@Path("number") String number);
    @GET("/todos/{number}")
    Observable<Todo> getTodo(@Path("number") String number);
}
