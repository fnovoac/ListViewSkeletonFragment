package rh.listViewSkeleton;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rh.listViewSkeleton.events.DataReceivedEvent;


public class BackgroundWebRunner extends AsyncTask<String, Void, String> {
    private static final String TAG = "BackgroundWebRunner";

    @Override
    protected String doInBackground(String... ulr) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ulr[0])
                .build();

        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(String result) {
        BaseApplication.getEventBus().post(new DataReceivedEvent(result));
    }
}