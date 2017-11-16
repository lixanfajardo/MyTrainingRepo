package com.android.f45techdashboard.Services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by LeakSun on 11/09/2017.
 */

public class FreshdeskAPIService extends Service {



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast toast = Toast.makeText(this, "Started :)", Toast.LENGTH_LONG);
        toast.show();
        Log.e("LIXAN", getJSONData());
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private String getJSONData()
 {
     String freshdeskURL = "https://f45training.freshdesk.com/api/v2/tickets";
     return String.valueOf(new APITask().execute(freshdeskURL));
 }

 private void authenticateAPI(String username, String password)
 {

 }

}

class APITask extends AsyncTask<String, String, String>
{

    private URL url;
    private HttpURLConnection apiCon;
    private InputStream inputStream;
    private BufferedReader bufferedReader;

    @Override
    protected String doInBackground(String... strings) {

        StringBuffer tempData = new StringBuffer();

        String data;
        try {

            url = new URL(strings[0]);
            apiCon = (HttpURLConnection) url.openConnection();
            apiCon.connect();

            inputStream = apiCon.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            if (apiCon != null)
            {
                while ((data = bufferedReader.readLine()) != null)
                {
                    tempData.append(data);
                }

                apiCon.disconnect();
                bufferedReader.close();
            }
            else {
                Log.e("FreshDeskAPI", "apiCon and is NULL");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(tempData);
    }


}

