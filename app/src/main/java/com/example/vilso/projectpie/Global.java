package com.example.vilso.projectpie;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by vilso on 08/08/2015.
 */
public class Global extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Enable parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "dSS6DiCMBiQds2CTEbfRHjvohIHngLGfa3HskXMH", "BjIx9eVo4t36Kqttz7Mh7w55EDdfS0ybA4unnBeo");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

    }
}
