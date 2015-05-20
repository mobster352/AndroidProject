package com.tetramaster;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends ActionBarActivity implements GameFragment.OnFragmentInteractionListener,
        InstructionsFragment.OnFragmentInteractionListener, DeckFragment.OnFragmentInteractionListener,
        ManageDeckFragment.OnFragmentInteractionListener, ManageTrunkFragment.OnFragmentInteractionListener,
        MoveFragment.OnFragmentInteractionListener, EndGameFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements Button.OnClickListener {

        private FragmentActivity myContext;
        private MediaPlayer mediaPlayer;

        public PlaceholderFragment() {
        }

        @Override
        public void onAttach(Activity activity) {
            myContext=(FragmentActivity) activity;
            super.onAttach(activity);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            ImageView image = (ImageView) getView().findViewById(R.id.imageView);
            Drawable drawable = getResources().getDrawable(R.drawable.mainmenu);
            image.setImageDrawable(drawable);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            ImageButton startButton = (ImageButton) getView().findViewById(R.id.startButton);
            drawable = getResources().getDrawable(R.drawable.start);
            startButton.setImageDrawable(drawable);
            startButton.setOnClickListener(this);
            startButton.setScaleType(ImageView.ScaleType.FIT_XY);

            ImageButton instructionsButton = (ImageButton)getView().findViewById(R.id.instructionsButton);
            drawable = getResources().getDrawable(R.drawable.howto);
            instructionsButton.setImageDrawable(drawable);
            instructionsButton.setOnClickListener(this);
            instructionsButton.setScaleType(ImageView.ScaleType.FIT_XY);

            ImageButton deckButton = (ImageButton)getView().findViewById(R.id.deckButton);
            drawable = getResources().getDrawable(R.drawable.deck);
            deckButton.setImageDrawable(drawable);
            deckButton.setOnClickListener(this);
            deckButton.setScaleType(ImageView.ScaleType.FIT_XY);

            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            mediaPlayer = MediaPlayer.create(getView().getContext(),R.raw.menu_music);

            mediaPlayer.start();

            mediaPlayer.setLooping(true);
        }



        @Override
        public void onPause() {
            super.onPause();
            mediaPlayer.pause();
        }

        @Override
        public void onResume() {
            super.onResume();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.startButton)
            {
                Fragment f = GameFragment.newInstance("Steve");
                android.support.v4.app.FragmentTransaction ft = myContext.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,f);
                ft.addToBackStack(null);
                ft.commit();
            }
            else if(view.getId()==R.id.instructionsButton)
            {
                Fragment f = new InstructionsFragment();
                android.support.v4.app.FragmentTransaction ft = myContext.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,f);
                ft.addToBackStack(null);
                ft.commit();
            }
            else if(view.getId() == R.id.deckButton)
            {
                Fragment f = new DeckFragment();
                android.support.v4.app.FragmentTransaction ft = myContext.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,f);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
    }
}
