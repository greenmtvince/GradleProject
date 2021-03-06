package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
//import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
//import com.udacity.gradle.builditbigger.backend.MyBean;
//import com.udacity.gradle.builditbigger.backend.MyEndpoint;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Vinnie on 1/11/2018.
 */

public class CallEndpointTask extends AsyncTask<Pair<Context,String>,Void,String> {
    private Context mContext;
    private static MyApi myApiService = null;
    private TaskCompleteListener<String> mListener;

    public CallEndpointTask(TaskCompleteListener listener){
        mListener = listener;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        mContext = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result){
        mListener.onTaskComplete(result);
    }
}
