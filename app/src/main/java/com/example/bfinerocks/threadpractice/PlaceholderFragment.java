package com.example.bfinerocks.threadpractice;

/**
 * Created by BFineRocks on 12/15/14.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
        final TextView text = (TextView) rootView.findViewById(R.id.text);
        final String uIThread = "The UI Thread ID is ";
        final String threadID = String.valueOf(Thread.currentThread().getId());
        Log.i("UIThread", threadID);
        text.setText(uIThread + threadID);
        AsyncTaskThread asyncTaskThread = new AsyncTaskThread(new AsyncTaskThreadInterface() {
            @Override
            public void updatedText(String stringOfText) {
                String asyncThread = "The Async Thread ID is ";
                text.setText(uIThread + threadID + "\n" + asyncThread + stringOfText);
            }
        });
        asyncTaskThread.execute();
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                final String handlerMessage = bundle.getString("executor");
                Log.i("message", handlerMessage);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(handlerMessage);
                    }
                });
            }
        };
        ThreadExecutorThread threadExecutorThread = new ThreadExecutorThread(handler);
        threadExecutorThread.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable", "runnable");
            }
        });
        return rootView;
    }
}
