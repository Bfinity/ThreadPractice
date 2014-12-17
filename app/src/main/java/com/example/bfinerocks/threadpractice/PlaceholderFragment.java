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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        Log.i("UIThread", threadID + Thread.currentThread().getName());
        AsyncTaskThread asyncTaskThread = new AsyncTaskThread(new AsyncTaskThreadInterface() {
            @Override
            public void updatedText(String stringOfText) {
                String asyncThread = "The Async Thread ID is ";
                builder.append("\n").append(asyncThread).append(stringOfText);
            }
        });
        asyncTaskThread.execute();
        final Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                switch (msg.what){
                    case 1:
                        final String handlerMessage1 = bundle.getString("executor");
                        Log.i("message", handlerMessage1);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String executor = "The Executor Implementation Thread ID is ";
                                builder.append("\n").append(executor).append(handlerMessage1);
                                text.setText(builder.toString());
                            }
                        });
                        break;
                    case 2:
                        final String handlerMessage2 = bundle.getString("executorService");
                        Log.i("message", handlerMessage2);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String executor = "The ExecutorService Implementation Thread ID is ";
                                builder.append("\n").append(executor).append(handlerMessage2);
                                text.setText(builder.toString());
                            }
                        });
                        break;
                    case 3:
                        final String handlerMessage3 = bundle.getString("threadpool");
                        Log.i("message", handlerMessage3);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String executor = "The Threadpool Implementation Thread ID is ";
                                builder.append("\n").append(executor).append(handlerMessage3);
                                text.setText(builder.toString());
                            }
                        });
                }

            }
        };
        ThreadExecutorThread threadExecutorThread = new ThreadExecutorThread(handler);
        threadExecutorThread.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable", "runnable");
            }
        });

        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("runnable", "runnable");
                String threadId = String.valueOf(Thread.currentThread().getId());
                Log.i("newSingle", threadId);
                Message msg = new Message();
                msg.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("executorService", threadId);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });

        ThreadPoolThread threadPoolThread = ThreadPoolThread.getThreadPoolThread();
        threadPoolThread.addNewTasksToQueue(new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable", "runnable");
                String threadId = String.valueOf(Thread.currentThread().getId());
                Log.i("newSingle", threadId);
                Message msg = new Message();
                msg.what = 3;
                Bundle bundle = new Bundle();
                bundle.putString("threadpool", threadId);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        return rootView;
    }
}
