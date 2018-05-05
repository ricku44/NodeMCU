package com.sample.shivam;

import android.annotation.SuppressLint;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Shashank on 25-02-2018.
 */

class MySingleton {

    @SuppressLint("StaticFieldLeak")
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;

    private MySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    static synchronized MySingleton getInstance(Context context){
        if(mInstance == null)
            mInstance = new MySingleton(context);
        return mInstance;
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        return requestQueue;
    }

    <T>void addToReqQ(Request<T> request){
        requestQueue.add(request);
    }
}
