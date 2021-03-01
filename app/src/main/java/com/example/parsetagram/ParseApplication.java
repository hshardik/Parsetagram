package com.example.parsetagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MIh40FlmLQcA1IEKNzAlXyOyclY2d9EgXPXLmrDK")
                .clientKey("YVgRtew1O8ZqOmFQGxkmwfGT2ZojLzPdKdBcOHJQ")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
