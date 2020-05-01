package com.aygus.kroquiapp.Service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SANTIAGO from AIGUS on 18/01/2019.
 */
public class OkHttpService extends AsyncTask<String, Void, OkHttpService.ResponseResultOkHttp>{
    private String json;
    private String url;
    private OkHttpHelper okHttpHelper;
    private int action;

    public static final int GET = 0;
    public static final int POST = 1;

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpEventListener okHttpEventListener = new OkHttpEventListener(responseOkHttp());
    private final OkHttpClient client = new OkHttpClient.Builder().eventListener(okHttpEventListener).build();

    public interface OkHttpHelper{
        void onPreExecuteHelper();
        void onPosExeculte(ResponseResultOkHttp result);
    }

    public OkHttpService(String json, String url, OkHttpHelper okHttpHelper, int action) {
        this.json = json;
        this.url = url;
        this.okHttpHelper = okHttpHelper;
        this.action = action;
    }

    public OkHttpService(String url, OkHttpHelper okHttpHelper, int action) {
        this.url = url;
        this.okHttpHelper = okHttpHelper;
        this.action = action;
    }

    @Override
    protected void onPreExecute() {
        okHttpHelper.onPreExecuteHelper();
    }

    @Override
    protected ResponseResultOkHttp doInBackground(String... strings) {
        ResponseResultOkHttp result = null;
        switch (action){

            case 0:
                try {
                    result = get(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 1:
                //try {
                    result = null;//post(url, json);
                //} catch (IOException e) {
                    //e.printStackTrace();
                //}
                break;
        }

        return result;
    }

    @Override
    protected void onPostExecute(ResponseResultOkHttp result) {
        okHttpHelper.onPosExeculte(result);

    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        String responseString;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            try {
                assert response.body() != null;
                responseString = response.body().string();

            }catch (NullPointerException ex){
                responseString = "NullPointerException ex";
            }
        }

        return responseString;
    }

    private ResponseResultOkHttp get(String url) throws IOException {
        ResponseResultOkHttp responseResultOkHttp = new ResponseResultOkHttp();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        try {
            assert response.body() != null;
            responseResultOkHttp.result = response.body().string();

        }catch (NullPointerException exc){
            responseResultOkHttp.result = exc.getMessage();
        }

        responseResultOkHttp.mensage = response.message();
        responseResultOkHttp.code = response.code();

        return responseResultOkHttp;
    }

    public static class ResponseResultOkHttp {
        public String mensage;
        public String result;
        public int code;
    }

    private OkHttpEventListener.ResponseOkHttp responseOkHttp(){
        return new OkHttpEventListener.ResponseOkHttp() {

            @Override
            public void callFailed(Call call, IOException ioe) {

            }

            @Override
            public void responseBodyEnd(Call call, long byteCount) {

            }

            @Override
            public void connectionReleased(Call call, Connection connection) {
                Log.d("OkHttp", connection.protocol().name());
            }

            @Override
            public void callEnd(Call call) {

            }

            @Override
            public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException ioe) {
                Log.d("OkHttp", ioe.getMessage());

            }
        };
    }
}
