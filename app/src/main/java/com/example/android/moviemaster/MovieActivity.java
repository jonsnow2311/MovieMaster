package com.example.android.moviemaster;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 03-06-2019.
 */

public class MovieActivity extends AppCompatActivity{

    List<String> responses = new ArrayList<>();
    String URL_STRING = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);




        //Bundle bundle = getIntent().getExtras();
        Intent previousIntent = getIntent();
        URL_STRING = previousIntent.getStringExtra("URL_STRING");

        //EditText movieTitle = (EditText) findViewById(R.id.movieTitle);
        //EditText movieYear = (EditText) findViewById(R.id.movieYear);

        MovieAsync movieAsync = new MovieAsync();
        movieAsync.execute(URL_STRING);
    }

    private void updateUi()
    {
        TextView movieName = (TextView) findViewById(R.id.movieName);
        TextView runTime = (TextView) findViewById(R.id.runtime);
        TextView releaseDate = (TextView) findViewById(R.id.releaseDate);
        TextView director = (TextView) findViewById(R.id.director);
        TextView genre = (TextView) findViewById(R.id.genre);
        TextView cast = (TextView) findViewById(R.id.cast);
        TextView plot = (TextView) findViewById(R.id.plot);
        TextView imdbRating = (TextView) findViewById(R.id.imdbRating);
        TextView boxOffice = (TextView) findViewById(R.id.boxOffice);
        TextView production = (TextView) findViewById(R.id.production);

        movieName.setText(responses.get(0));
        runTime.setText(responses.get(1));
        releaseDate.setText(responses.get(2));
        director.setText(responses.get(3));
        genre.setText(responses.get(4));
        cast.setText(responses.get(5));
        plot.setText(responses.get(6));
        imdbRating.setText(responses.get(7));
        boxOffice.setText(responses.get(8));
        production.setText(responses.get(9));

    }




    private class MovieAsync extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            String response = QueryUtils.fetchMovieData(urls[0]);

            return response;
        }

        @Override
        protected void onPostExecute(String jsonResponse)
        {
            super.onPostExecute(jsonResponse);

             responses = QueryUtils.extractFeatureFromJson(jsonResponse);
             Log.i(responses.get(0) , "SUCCESSFULLY");

            updateUi();

            /*if(responses != null)
            {
                String a = "Title: " + responses.get(0);
                movieName.setText(a);

                Log.i("RESPONSES" , "NOT NULL REACHED");
                //runTime.setText("Runtime: " + responses.get(1));

                Log.i("open responses" , "reached");


            }*/
        }
    }
}

