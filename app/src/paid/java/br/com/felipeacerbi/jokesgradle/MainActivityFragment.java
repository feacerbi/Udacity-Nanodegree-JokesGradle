package br.com.felipeacerbi.jokesgradle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import br.com.felipeacerbi.jokesdisplaylib.ShowJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokesListener {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeButton = root.findViewById(R.id.bt_tell_joke);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestJoke();
            }
        });
        return root;
    }

    public void requestJoke() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.incrementIdlingResource();
        }

        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void displayJoke(String joke) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.decrementIdlingResource();
        }

        Intent jokeIntent = new Intent(getActivity(), ShowJokeActivity.class);
        jokeIntent.putExtra(ShowJokeActivity.JOKE_EXTRA, joke);

        startActivity(jokeIntent);
    }

    @Override
    public FrameLayout getProgressBar() {
        if(getView() != null) {
            return getView().findViewById(R.id.progress_bar);
        } else {
            return null;
        }
    }
}
