package com.example.android.moviemaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public String movieTitleString = "";
    public String movieYearString = "";




    //public String URL_STRING = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        final EditText movieTitle = (EditText) findViewById(R.id.movieTitle);
        final EditText movieYear = (EditText) findViewById(R.id.movieYear);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieTitleString = movieTitle.getText().toString();
                movieYearString = movieYear.getText().toString();
                String URL_String = "http://www.omdbapi.com/?i=tt3896198&apikey=ac89c56e&t=" + movieTitleString + "&y="+ movieYearString;

                Intent movieActivityIntent = new Intent(MainActivity.this , MovieActivity.class);
                movieActivityIntent.putExtra("URL_STRING" , URL_String);
                startActivity(movieActivityIntent);

                Log.i("open intent" , "reached");
            }
        });


    }

    /*public  String getURL_STRING()
    {
        return URL_STRING;
    }*/
}
