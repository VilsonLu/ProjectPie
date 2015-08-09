package com.example.vilso.projectpie;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.example.vilso.projectpie.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import models.IdeaContext;
import models.IdeaItem;
import models.RateAndComment;


public class ViewIdeaActivity extends ActionBarActivity implements CommentAdapter.CommentViewHolder.ClickListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private CommentAdapter itemAdapter;
    private Toolbar toolbar;
    private RelativeLayout layout_question;
    private LayoutInflater layoutInflater;
    private TextView tv_question;
    private TextView tv_analysis;
    private View sliderView;
    private FloatingActionButton btn_fab;
    private Button btn_addcomment;
    private ImageButton btn_yes;
    private ImageButton btn_no;
    private Button btnLike;
    private IdeaItem ideaItem;
    private ParseObject ideaParse;
    private int indexQuestion = 0;
    private String[] questions = new String[]{"Was it a real problem?",
            "Did the idea solve the problem?",
            "Does the idea have a potential for success?",
            "Was the message impacting and convincing?",
            "Was the information simple enough?"};

    private boolean isDoneSurvey = false;

    private final static int DISAPPEAR_DELAY = 1000;
    private final static int APPEAR_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_idea);

        String objectID = getIntent().getStringExtra("ideaID");
        //Log.e("Object ID", objectID);
        IdeaContext ideaContext = new IdeaContext();
        ideaParse = ideaContext.getIdea(objectID);

        ideaItem = new IdeaItem(ideaParse.getString("title"), 0, ideaContext.getLikeCount(ideaParse), ideaParse.getString("youtube"), ideaParse.getString("status"));
        ideaItem.setObjectId(ideaParse.getObjectId());

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(ideaItem.getTitle() + "");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layout_question = (RelativeLayout)findViewById(R.id.layout_question);

        itemAdapter = new CommentAdapter(getData());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        for(int i = 1; i < recyclerView.getChildCount(); i++){
            recyclerView.getChildAt(i).setVisibility(View.GONE);
        }

        layoutInflater = LayoutInflater.from(this);
        sliderView = layoutInflater.inflate(R.layout.view_app_idea_header, recyclerView, false);

        itemAdapter = new CommentAdapter(getData());

        itemAdapter.setContext(this);
        itemAdapter.setClickListener(this);
        itemAdapter.setParallaxHeader(sliderView, recyclerView);
//        itemAdapter.setData(getData());
        itemAdapter.implementMethods();

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tv_question = (TextView)sliderView.findViewById(R.id.tv_question);
        tv_analysis = (TextView)sliderView.findViewById(R.id.tv_analysis);
        tv_analysis.setText("");

        btnLike = (Button) sliderView.findViewById(R.id.btn_like);
        btnLike.setText("Like: " + Integer.toString(ideaContext.getLikeCount(ideaParse)));
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdeaContext ideaContext = new IdeaContext();
                ideaContext.likeIdea(ideaParse, ParseUser.getCurrentUser());
                btnLike.setText("Like: " + Integer.toString(ideaContext.getLikeCount(ideaParse)));
            }
        });

        btn_yes = (ImageButton)sliderView.findViewById(R.id.btn_yes);
        btn_no = (ImageButton)sliderView.findViewById(R.id.btn_no);
        btn_addcomment = (Button)sliderView.findViewById(R.id.btn_comment);
        btn_fab = (FloatingActionButton)findViewById(R.id.btn_fab);

        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        btn_fab.setOnClickListener(this);
        btn_addcomment.setOnClickListener(this);
    }

    private String getQuestion(){
        if(indexQuestion < questions.length){
            YoYo.with(Techniques.FadeIn).duration(500).delay(APPEAR_DELAY).playOn(tv_question);
            YoYo.with(Techniques.ZoomIn).duration(500).delay(APPEAR_DELAY).playOn(btn_no);
            YoYo.with(Techniques.ZoomIn).duration(500).delay(APPEAR_DELAY).playOn(btn_yes);

            return questions[indexQuestion++];
        }else{
            isDoneSurvey = true;
            surveyDone();
        }
        return "";
    }

    private void surveyDone(){
        YoYo.with(Techniques.FadeInUp).duration(1000).playOn(btn_addcomment);
        btn_addcomment.setVisibility(View.VISIBLE);

        for(int i = 1; i < recyclerView.getChildCount(); i++){
            YoYo.with(Techniques.FadeInUp).duration(1000).playOn(recyclerView.getChildAt(i));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btn_yes)){
            YoYo.with(Techniques.ZoomOut).duration(500).playOn(btn_no);

            tv_analysis.setTextColor(getResources().getColor(R.color.answer_correct));
            tv_analysis.setText(tv_question.getText());

            YoYo.YoYoString x = YoYo.with(Techniques.FadeOut).duration(500).delay(DISAPPEAR_DELAY).playOn(tv_analysis);
            YoYo.with(Techniques.FadeOut).duration(1).playOn(tv_question);
            YoYo.with(Techniques.ZoomOut).duration(500).delay(DISAPPEAR_DELAY).playOn(btn_yes);

            tv_question.setText(getQuestion());

        }else if(v.equals(btn_no)){
            YoYo.with(Techniques.ZoomOut).duration(500).playOn(btn_yes);

            tv_analysis.setTextColor(getResources().getColor(R.color.answer_wrong));
            tv_analysis.setText(tv_question.getText());

            YoYo.YoYoString x = YoYo.with(Techniques.FadeOut).duration(500).delay(DISAPPEAR_DELAY).playOn(tv_analysis);
            YoYo.with(Techniques.FadeOut).duration(1).playOn(tv_question);
            YoYo.with(Techniques.ZoomOut).duration(500).delay(DISAPPEAR_DELAY).playOn(btn_no);

            tv_question.setText(getQuestion());
        }else if(v.equals(btn_addcomment)){
            popup();
        }
    }

    private void popup(){
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.et_comment);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Post",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }

    @Override
    public void itemClicked(View view, int position) {
    }

    public List<RateAndComment> getData(){
        List<ParseObject> comments = new IdeaContext().getComments(ideaParse);
        List<RateAndComment> data = new ArrayList();

        for(ParseObject comment: comments){
            try {
                data.add(new RateAndComment(ParseUser.getQuery().get(comment.getString("user")).getUsername(), 3, comment.getString("message")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 20; i++){
            data.add(new RateAndComment("chiko", 3, "Wow! edi"));
        }

        return data;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_idea, menu);
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
