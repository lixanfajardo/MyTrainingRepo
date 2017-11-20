package com.android.f45techdashboard.Services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.f45techdashboard.Models.Constants;
import com.android.f45techdashboard.Models.FreshdeskDataModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
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

        StringBuilder tempData = new StringBuilder();
        String data;
        FreshdeskDataModel freshdeskDataModel = null;

        try {
            url = new URL(strings[0]);
            apiCon = (HttpURLConnection) url.openConnection();

            String apikey = "lmiejps0uF4Z7eCwfyIe";
            String userPass = "michael@bywave.com.au:Welcome@12345";
            byte[] authBytes = Base64.encode(userPass.getBytes(), Base64.DEFAULT);
            String authString = new String(authBytes);
            //apiCon.addRequestProperty("apikey", apikey);
            apiCon.setRequestMethod("GET");
            apiCon.setDoInput(true);
            apiCon.setRequestProperty("Authorization","Basic " +  authString);
            apiCon.connect();

            inputStream = apiCon.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            if (apiCon != null && apiCon.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                while ((data = bufferedReader.readLine()) != null)
                {
                    tempData.append(data);
                    tempData.append("\n");
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

        JSONArray dataArray = null;
        JSONObject dataObject = null;

        try {

            dataArray = new JSONArray(tempData.toString());

            for(int i = 0; dataArray.length() > i; i++)
            {
                dataObject = dataArray.getJSONObject(i);
            }

            freshdeskDataModel = new Gson().fromJson(dataObject.toString(), FreshdeskDataModel.class);
            Constants.freshdeskDataModel = freshdeskDataModel;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Log.e("FreshdeskAPI", freshdeskDataModel.toString());

        if(freshdeskDataModel != null)
        {
            Log.v("Freshdesk", "Created at: " + freshdeskDataModel.created_at + " update at: " + freshdeskDataModel.updated_at);
        }
        else
            {
                Log.e("Freshdesk", "model is null");
            }

        return String.valueOf(freshdeskDataModel);
    }


}

