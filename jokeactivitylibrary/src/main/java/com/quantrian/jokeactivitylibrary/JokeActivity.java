package com.quantrian.jokeactivitylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    private static final String JOKE = "joke";
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        mTextView = findViewById(R.id.tv_jokeDisplay);
        String joke = getIntent().getStringExtra(JOKE);
        mTextView.setText(joke);
    }
}
