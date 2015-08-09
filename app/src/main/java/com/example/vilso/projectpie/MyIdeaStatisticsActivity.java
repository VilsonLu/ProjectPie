package com.example.vilso.projectpie;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MyIdeaStatisticsActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private TextView[] tvs;
    private TextView[] descs;
    private TextView[] pgs;
    private ProgressBar[] pgbars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_idea_statistics);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvs = new TextView[5];
        descs = new TextView[5];
        pgbars = new ProgressBar[5];
        pgs = new TextView[5];

        tvs[0] = (TextView)findViewById(R.id.tv_label1);
        tvs[1] = (TextView)findViewById(R.id.tv_label2);
        tvs[2] = (TextView)findViewById(R.id.tv_label3);
        tvs[3] = (TextView)findViewById(R.id.tv_label4);
        tvs[4] = (TextView)findViewById(R.id.tv_label5);

        pgs[0] = (TextView)findViewById(R.id.tv_percentage1);
        pgs[1] = (TextView)findViewById(R.id.tv_percentage2);
        pgs[2] = (TextView)findViewById(R.id.tv_percentage3);
        pgs[3] = (TextView)findViewById(R.id.tv_percentage4);
        pgs[4] = (TextView)findViewById(R.id.tv_percentage5);

        descs[0] = (TextView)findViewById(R.id.tv_message1);
        descs[1] = (TextView)findViewById(R.id.tv_message2);
        descs[2] = (TextView)findViewById(R.id.tv_message3);
        descs[3] = (TextView)findViewById(R.id.tv_message4);
        descs[4] = (TextView)findViewById(R.id.tv_message5);

        pgbars[0] = (ProgressBar)findViewById(R.id.progressBar1);
        pgbars[1] = (ProgressBar)findViewById(R.id.progressBar2);
        pgbars[2] = (ProgressBar)findViewById(R.id.progressBar3);
        pgbars[3] = (ProgressBar)findViewById(R.id.progressBar4);
        pgbars[4] = (ProgressBar)findViewById(R.id.progressBar5);

        pgbars[0].setIndeterminate(true);
        pgbars[1].setIndeterminate(true);
        pgbars[2].setIndeterminate(true);
        pgbars[3].setIndeterminate(true);
        pgbars[4].setIndeterminate(true);

        tvs[0].setText("Emphatize");
        pgbars[0].setProgress(70);
        pgs[0].setText("70");
        descs[0].setText("Was a 'real' problem or opportunity identified? Yep! Alot of people are experiencing your problem! Get to know more of them and know what pains them more.");

        tvs[1].setText("Idea");
        pgbars[1].setProgress(23);
        pgs[1].setText("23");
        descs[1].setText("Did the idea solve or address the problem or opportunity? Unfortunately, you would need to pivot.");

        tvs[2].setText("Scaleable");
        pgbars[2].setProgress(13);
        pgs[2].setText("13");
        descs[2].setText("Did the idea have potential for scaleable or profitable? Right now it doesn't. Get to know more marketing strategies and revise your business plan.");

        tvs[3].setText("Presentation");
        pgbars[3].setProgress(83);
        pgs[3].setText("83");
        descs[3].setText("Was the message memorable and engaging? You have captured an audience! Now try to practice in a live crowd.");

        tvs[4].setText("Simplicity");
        pgbars[4].setProgress(50);
        pgs[4].setText("50");
        descs[4].setText("Was the information presented in the simplest form possible? Some people didn't get the gist a lot.");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_idea_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
