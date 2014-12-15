package com.example.bfinerocks.threadpractice;

/**
 * Created by BFineRocks on 12/15/14.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bfinerocks.threadpractice.AsyncTaskThread.AsyncTaskThreadInterface;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView text = (TextView) rootView.findViewById(R.id.text);
        String UIThread = "The UI Thread ID is ";
        String threadID = String.valueOf(Thread.currentThread().getId());
        Log.i("UIThread", threadID);
        text.setText(UIThread + threadID);
        AsyncTaskThread asyncTaskThread = new AsyncTaskThread(new AsyncTaskThreadInterface() {
            @Override
            public void updatedText(String stringOfText) {
                String AsyncThread = "The Async Thread ID is ";

            }
        });
        asyncTaskThread.execute();
        return rootView;
    }
}
