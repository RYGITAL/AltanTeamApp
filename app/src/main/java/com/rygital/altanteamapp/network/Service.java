package com.rygital.altanteamapp.network;

import com.rygital.altanteamapp.models.Comment;
import com.rygital.altanteamapp.models.Photo;
import com.rygital.altanteamapp.models.Post;
import com.rygital.altanteamapp.models.Todo;
import com.rygital.altanteamapp.models.User;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getPostItem(String id, final GetProductListCallback<Post> callback) {
        return networkService.getPost(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Post>>() {
                    @Override
                    public Observable<? extends Post> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Post>(){
                    @Override
                    public void onCompleted(){

                    }

                    @Override
                    public void onError(Throwable e){
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(Post item){
                        callback.onSuccess(item);
                    }
                });
    }
    public Subscription getCommentItem(String id, final GetProductListCallback<Comment> callback) {
        return networkService.getComment(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Comment>>() {
                    @Override
                    public Observable<? extends Comment> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Comment>(){
                    @Override
                    public void onCompleted(){

                    }

                    @Override
                    public void onError(Throwable e){
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(Comment item){
                        callback.onSuccess(item);
                    }
                });
    }
    public Subscription getUsersList(String id, final GetProductListCallback<List<User>> callback) {
        return networkService.getUsers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<User>>>() {
                    @Override
                    public Observable<? extends List<User>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<User>>(){
                    @Override
                    public void onCompleted(){

                    }

                    @Override
                    public void onError(Throwable e){
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(List<User> list){
                        callback.onSuccess(list);
                    }
                });
    }
    public Subscription getPhotoItem(String id, final GetProductListCallback<Photo> callback) {
        return networkService.getPhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Photo>>() {
                    @Override
                    public Observable<? extends Photo> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Photo>(){
                    @Override
                    public void onCompleted(){

                    }

                    @Override
                    public void onError(Throwable e){
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(Photo item){
                        callback.onSuccess(item);
                    }
                });
    }
    public Subscription getTodoItem(String id, final GetProductListCallback<Todo> callback) {
        return networkService.getTodo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Todo>>() {
                    @Override
                    public Observable<? extends Todo> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Todo>(){
                    @Override
                    public void onCompleted(){

                    }

                    @Override
                    public void onError(Throwable e){
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(Todo item){
                        callback.onSuccess(item);
                    }
                });
    }

    public interface GetProductListCallback<T> {
        void onSuccess(T list);
        void onError(NetworkError networkError);
    }

}
