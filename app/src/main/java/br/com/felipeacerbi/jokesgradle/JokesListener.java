package br.com.felipeacerbi.jokesgradle;

import android.widget.FrameLayout;

interface JokesListener {
    void displayJoke(String joke);
    FrameLayout getProgressBar();
}
