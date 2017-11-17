package br.com.felipeacerbi.jokesdisplaylib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowJokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke";

    private String jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);

        handleIntent();
        setUpUI();
    }

    private void setUpUI() {
        TextView jokeTextView = findViewById(R.id.tv_joke_text);
        jokeTextView.setText(jokeText);
    }

    private void handleIntent() {
        Intent intent = getIntent();

        if(intent.hasExtra(JOKE_EXTRA)) {
            jokeText = intent.getStringExtra(JOKE_EXTRA);
        }
    }
}