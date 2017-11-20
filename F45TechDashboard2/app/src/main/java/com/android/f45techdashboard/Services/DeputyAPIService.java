package com.android.f45techdashboard.Services;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobScheduler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.f45techdashboard.Models.Constants;
import com.android.f45techdashboard.Models.DeputyDataModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by LeakSun on 11/09/2017.
 */

public class DeputyAPIService extends Service{


    DeputyAPITask deputyAPITask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String deputyAPIURL = "https://a3c3f816065445.as.deputy.com/api/v1/resource/Timesheet/";
        deputyAPITask = (DeputyAPITask) new DeputyAPITask().execute(deputyAPIURL);
        return START_STICKY;
    }
}


class DeputyAPITask extends AsyncTask<String, String, String>
{

     private HttpURLConnection apiCon;
     private URL url;
     private InputStream inputStream;
     private BufferedReader bufferedReader;

    @Override
    protected String doInBackground(String... strings) {

        StringBuilder tempData = new StringBuilder();
        String data;
        String client_id = "bc9809e6ee1a36bd20501f1aace2f480a2029192";
        String client_secret = "77a81fa35c0ea495393676d58cc7c70fabfd92a2";
        String oauthToken = "ffc0b18fb4ffd88c70dd523cb38259e5";
        DeputyDataModel deputyModel = null;

        try
        {
            url = new URL(strings[0]);
            apiCon = (HttpURLConnection) url.openConnection();
            apiCon.addRequestProperty("client_id", client_id);
            apiCon.addRequestProperty("client_secret", client_secret);
            apiCon.setRequestMethod("GET");
            apiCon.setRequestProperty("Authorization", "OAuth " + oauthToken);
            apiCon.connect();

            inputStream = apiCon.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            if (apiCon != null){

                while((data = bufferedReader.readLine()) != null)
                {
                    tempData.append(data);
                    tempData.append("\n");
                }

                apiCon.disconnect();
                bufferedReader.close();

            }
            else {
                Log.e("LIXAN", "NO CONNECTION DUMBASS CAPSLOCK PARA INTENSE MO*FU*");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        JSONArray dataArray = null;
        JSONObject dataObject = null;
        StringBuilder formattedData = new StringBuilder();

        try {
               //convert JSONArray to JSON Object then put to the model
               dataArray = new JSONArray(tempData.toString());
            Log.v("LIXAN", "dataArray Length: " + dataArray.length());
                for(int i = 0; i < dataArray.length(); i++)
                {
                    dataObject = dataArray.getJSONObject(i);
                    formattedData.append(dataObject + ",");
                }

               //dataObject = dataArray.getJSONObject(0);
                deputyModel = new Gson().fromJson(dataObject.toString(), DeputyDataModel.class);
                Constants.deputyDataModel = deputyModel;

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.e("LIXAN", "TASK: " + dataObject);
            Log.e("LIXAN", "TASK: " + formattedData.toString());
            if(deputyModel != null)
            {
                Log.e("LIXAN", "MODEL: NAME: " + deputyModel._DPMetaData.EmployeeInfo.DisplayName + " ID: " + deputyModel.Id);
                Log.e("LIXAN", "MODEL: " + deputyModel.toString());
            }
            else
                {
                    Log.e("LIXAN", "MODEL: " + "is null ,,/,,");
                }

        return String.valueOf(deputyModel);
    }
}
