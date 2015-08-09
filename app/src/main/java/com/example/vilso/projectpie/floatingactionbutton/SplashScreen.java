package com.example.vilso.projectpie.floatingactionbutton;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vilso.projectpie.LoginActivity;
import com.example.vilso.projectpie.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class SplashScreen extends AppIntro {
    @Override
    public void init(Bundle bundle) {
        addSlide(AppIntroFragment.newInstance("Step 1", "Share your ideas", R.drawable.page0, R.color.page1));
        addSlide(AppIntroFragment.newInstance("Step 2", "Get real feedback", R.drawable.page2, R.color.page2));
        addSlide(AppIntroFragment.newInstance("Step 3", "Connect to a community", R.drawable.page1, R.color.page3));
    }

    @Override
    public void onSkipPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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
