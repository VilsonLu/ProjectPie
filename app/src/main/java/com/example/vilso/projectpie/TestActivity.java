package com.example.vilso.projectpie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import bolts.Task;

public class TestActivity extends AppCompatActivity {


    private Button btnLike;
    private TextView lblTitle;
    private TextView lblCount;

    private EditText txtTitle;
    private EditText txtLink;
    private Button btnAdd;
    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btnLike = (Button) findViewById(R.id.btnLike);
        lblTitle = (TextView) findViewById(R.id.lblTitle);
        lblCount = (TextView) findViewById(R.id.lblCount);

        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtLink = (EditText) findViewById(R.id.txtLink);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String title = txtTitle.getText().toString();
                String link = txtLink.getText().toString();
                addIdea(user, title, link);
            }
        });

        user = ParseUser.getCurrentUser();
        lblCount.setText("Count: 0");
    }

    public void addIdea(ParseUser user, String title, String link){
        ParseObject idea = new ParseObject("Idea");
        idea.put("title", title);
        idea.put("youtube", link);
        idea.put("status", "draft");
        idea.put("user", user);
        idea.saveInBackground();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
