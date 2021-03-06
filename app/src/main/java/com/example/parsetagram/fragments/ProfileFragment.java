package com.example.parsetagram.fragments;

import android.util.Log;

import com.example.parsetagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import static com.example.parsetagram.Post.KEY_CREATED_AT;

public class ProfileFragment extends PostsFragment{
    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
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
