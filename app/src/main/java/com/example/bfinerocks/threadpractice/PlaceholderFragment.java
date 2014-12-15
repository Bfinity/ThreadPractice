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
import com.example.bfinerocks.threadpractice.ThreadPoolThread.ThreadPoolInterface;

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
        final StringBuilder builder = new StringBuilder();
        final String uIThread = "The UI Thread ID is ";
        final String threadID = String.valueOf(Thread.currentThread().getId());
        builder.append(uIThread).append(threadID);
        Log.i("UIThread", threadID);
        AsyncTaskThread asyncTaskThread = new AsyncTaskThread(new AsyncTaskThreadInterface() {
            @Override
            public void updatedText(String stringOfText) {
                String asyncThread = "The Async Thread ID is ";
                builder.append("\n").append(asyncThread).append(stringOfText);
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
                        String executor = "The Executor Implementation Thread ID is ";
                        builder.append("\n").append(executor).append(handlerMessage);
                        text.setText(builder.toString());
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
        ThreadPoolThread threadPoolThread = ThreadPoolThread.getThreadPoolThread(new ThreadPoolInterface() {
            @Override
            public void updateData(String data) {
                String threadpool = "The ThreadPool Thread ID is ";
                builder.append("\n").append(threadpool).append(data);
                text.setText(builder.toString());

            }
        } );
        threadPoolThread.addNewTasksToQueue(new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable", "runnable");
            }
        });
        return rootView;
    }
}
