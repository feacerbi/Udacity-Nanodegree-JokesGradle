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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.felipeacerbi.jokesdisplaylib.ShowJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragmentFree extends Fragment implements JokesListener {

    public MainActivityFragmentFree() {
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

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
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
