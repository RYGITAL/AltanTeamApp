package com.rygital.altanteamapp.ui.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rygital.altanteamapp.R;
import com.rygital.altanteamapp.deps.ViewComponent;
import com.rygital.altanteamapp.deps.ViewModule;
import com.rygital.altanteamapp.deps.DaggerViewComponent;
import com.rygital.altanteamapp.models.Comment;
import com.rygital.altanteamapp.models.Photo;
import com.rygital.altanteamapp.models.Post;
import com.rygital.altanteamapp.models.Todo;
import com.rygital.altanteamapp.models.User;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentFragment extends Fragment implements IContentView {
    @BindView(R.id.etPostID) EditText etPostID;
    @BindView(R.id.tvPost) TextView tvPost;
    @BindView(R.id.etCommentID) EditText etCommentID;
    @BindView(R.id.tvComment) TextView tvComment;
    @BindView(R.id.tvUser) TextView tvUser;
    @BindView(R.id.ivPhoto) ImageView ivPhoto;
    @BindView(R.id.tvTodo) TextView tvTodo;

    @Inject
    ContentPresenter presenter;
    private ViewComponent viewComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewModule(new ViewModule(this))
                    .build();
        }
        viewComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        presenter.getUsers();
        presenter.getPhoto("3");
        presenter.getTodo(String.valueOf(new Random().nextInt(200) + 1));
        return view;
    }

    @OnClick({R.id.btnConfirmPost, R.id.btnConfirmComment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnConfirmPost:
                presenter.getPost(etPostID.getText().toString());
                break;
            case R.id.btnConfirmComment:
                presenter.getComment(etCommentID.getText().toString());
                break;
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPost(Post post) {
        tvPost.setText("ID: " + post.getId() +
                "\nUser ID: " + post.getUserId() +
                "\nTitle: " + post.getTitle() +
                "\nBody: " + post.getBody());
    }

    @Override
    public void showComment(Comment comment) {
        tvComment.setText("ID: " + comment.getId() +
                "\nComment ID: " + comment.getPostId() +
                "\nName: " + comment.getName() +
                "\nEmail: " + comment.getEmail() +
                "\nBody: " + comment.getBody());
    }

    @Override
    public void showUsers(List<User> list) {
        tvUser.setText("");
        for (int i = 0; i < 5; i++) {
            if(list.get(i) == null) return;
            tvUser.append(list.get(i).getName() + ", " + list.get(i).getEmail());
            if (i != 4) tvUser.append("\n");
        }
    }

    @Override
    public void showPhoto(Photo photo) {
        Glide.with(this)
                .load(photo.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(ivPhoto);
    }

    @Override
    public void showTodo(Todo todo) {
        tvTodo.setText("ID: " + todo.getId() +
                "\nUser ID: " + todo.getUserId() +
                "\nTitle: " + todo.getTitle() +
                "\nCompleted: " + todo.getCompleted());
    }
}