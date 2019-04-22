package com.example.predator.lab2t1;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadAsyncTask extends AsyncTask<String, Integer, Boolean> {


    interface OnLoadingDone{
        void loadingDone(String stringToPass);
    }

    private OnLoadingDone callBack;
    public String address;
    public String htmlString;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public OnLoadingDone getCallBack() {
        return callBack;
    }

    public void setCallBack(OnLoadingDone callBack) {
        this.callBack = callBack;
    }


    @Override
    protected Boolean doInBackground(String... strings) {
        loadData();
        return new Boolean(true);
    }


    private void loadData() {
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            htmlString = convertStreamToString(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(callBack != null) {
            callBack.loadingDone(htmlString);
        }
    }
}
