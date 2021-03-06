package com.invages.orchidrus.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;

public class WebAsyncTask extends AsyncTask<String, String, JSONObject> {

    private Context context;
    String urlStr;
    JSONObject jObj;
    private WebResponseListener webResponseListener;

    ProgressDialog progressDialog;

    String TAG = getClass().getSimpleName();

    public WebAsyncTask(Context context, String url, JSONObject postData, WebResponseListener webResponseListener) {
        this.context = context;
        this.urlStr = url;
        this.jObj = postData;
        this.webResponseListener = webResponseListener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        Log.i(TAG, "doInBackground " + urlStr);
        Log.i(TAG, "doInBackground " + jObj.toString());

        try {


            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setReadTimeout(1500);
            urlConnection.setConnectTimeout(1500);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            bw.write(getPostData(jObj));
            bw.flush();
            bw.close();
            os.close();

            int responseCode = urlConnection.getResponseCode();

            Log.i(TAG, "" + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();

                Log.i(TAG, "" + sb.toString());
                return new JSONObject(sb.toString());

            }


        } catch (Exception e) {
            Log.i(TAG, "doInBackground: " + e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject s) {
        super.onPostExecute(s);
        Log.i(TAG, "onPostExecute: " + s);
        progressDialog.cancel();
        webResponseListener.onResponse(s);
    }


    public String getPostData(JSONObject params) throws Exception {

        Log.i(TAG, "getPostData: " + params.toString());

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}
