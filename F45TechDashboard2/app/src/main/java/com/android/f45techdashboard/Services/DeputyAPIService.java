package com.android.f45techdashboard.Services;

import android.os.AsyncTask;
import android.util.Log;

import com.android.f45techdashboard.Managers.ShiftTableManager;
import com.android.f45techdashboard.Models.DataModelLists;
import com.android.f45techdashboard.Models.DeputyDataModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LeakSun on 11/09/2017.
 */

public class DeputyAPIService extends AsyncTask<String, String, String>
{

    ShiftTableManager shiftTableManager;

    public void getDeputyAPIdata(String url)
    {
        new DeputyAPIService().execute(url);
    }


    @Override
    protected String doInBackground(String... strings) {

         HttpURLConnection apiCon;
         URL url;
         InputStream inputStream;
         BufferedReader bufferedReader;

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

        DataModelLists deputyDataModelList = null;

        try {
            /*JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", tempData.toString());*/

            String toJson = "{\"data\":" + new Gson().toJson(tempData) + "}";
            JSONObject jsonObject = new JSONObject(toJson);


            deputyModel  = new Gson().fromJson(jsonObject.toString(), DeputyDataModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(deputyModel != null)
        {
//            Log.e("LIXAN", "MODEL: NAME: " + deputyDataModelList.deputyDataModelList.get(1).data._DPMetaData.EmployeeInfo.DisplayName  + " ID: " + deputyDataModelList.deputyDataModelList.get(1).data.Id);
            Log.e("LIXAN", "MODEL: " + deputyModel.toString());
            shiftTableManager.notifyObserver(deputyModel);
        }
        else
        {
            Log.e("LIXAN", "MODEL: " + "is null ,,/,,");
        }

        return String.valueOf(deputyModel);


    }

}

