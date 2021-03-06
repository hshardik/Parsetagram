package com.example.parsetagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parsetagram.Post;
import com.example.parsetagram.PostsAdapter;
import com.example.parsetagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.example.parsetagram.Post.KEY_CREATED_AT;


public class PostsFragment extends Fragment {
private RecyclerView rvPosts;
public static final String TAG="PostsFragment";
protected PostsAdapter adapter;
protected List<Post> allPosts;
//SwipeRefreshLayout swipeContainer;
    public PostsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Steps to create RecyclerView
        //0 create a layout for one row in the list
        //1 create the adapter
        rvPosts=view.findViewById(R.id.rvPosts);
        //2 create the data source
        allPosts = new ArrayList<>();
        // create the adapter
        adapter = new PostsAdapter(getContext(), allPosts);
        //3 set the adapter in the recycler view
        rvPosts.setAdapter(adapter);
        //4 set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));


        queryPosts();
    }
    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e!= null)
                {
                    Log.e(TAG,"Issue with getting Posts");
                    return;

                }
                for(Post post: posts){
                    Log.i(TAG,"Post"+post.getDescription()+"username:"+post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}