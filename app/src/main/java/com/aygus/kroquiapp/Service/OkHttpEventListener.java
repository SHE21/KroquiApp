package com.aygus.kroquiapp.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SANTIAGO from AIGUS on 18/01/2019.
 */
public class OkHttpEventListener extends EventListener {
    long callStartNanos;
    public Call callthis;
    public Request request;
    public Response response;
    public ResponseOkHttp responseOkHttp;

    public interface ResponseOkHttp{
        void callFailed(Call call, IOException ioe);
        void responseBodyEnd(Call call, long byteCount);
        void connectionReleased(Call call, Connection connection);
        void callEnd(Call call);
        void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy,
                           Protocol protocol, IOException ioe);
    }

    public OkHttpEventListener(ResponseOkHttp responseOkHttp) {
        this.responseOkHttp = responseOkHttp;
    }

    private void printEvent(String name) {
        long nowNanos = System.nanoTime();
        if (name.equals("callStart")) {
            callStartNanos = nowNanos;
        }
        long elapsedNanos = nowNanos - callStartNanos;
        System.out.printf("%.3f %s%n", elapsedNanos / 1000000000d, name);
    }

    @Override public void callStart(Call call) {
        this. callthis = call;
        printEvent("callStart");
    }

    @Override public void dnsStart(Call call, String domainName) {
        this. callthis = call;
        printEvent("dnsStart");
    }

    @Override public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        this. callthis = call;
        printEvent("dnsEnd");
    }

    @Override public void connectStart(
            Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        this. callthis = call;
        printEvent("connectStart");
    }

    @Override public void secureConnectStart(Call call) {
        this. callthis = call;
        printEvent("secureConnectStart");
    }

    @Override public void secureConnectEnd(Call call, Handshake handshake) {
        this. callthis = call;
        printEvent("secureConnectEnd");
    }

    @Override public void connectEnd(
            Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        this. callthis = call;
        printEvent("connectEnd");
    }

    @Override public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy,
                                        Protocol protocol, IOException ioe) {

        responseOkHttp.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
        this. callthis = call;
        printEvent("connectFailed");
    }

    @Override public void connectionAcquired(Call call, Connection connection) {
        this. callthis = call;
        printEvent("connectionAcquired - >" + connection.protocol().toString());
    }

    @Override public void connectionReleased(Call call, Connection connection) {
        this. callthis = call;
        printEvent("connectionReleased");
    }

    @Override public void requestHeadersStart(Call call) {
        this. callthis = call;
        printEvent("requestHeadersStart");
    }

    @Override public void requestHeadersEnd(Call call, Request request) {
        this. callthis = call;
        this.request = request;
        printEvent("requestHeadersEnd");
    }

    @Override public void requestBodyStart(Call call) {
        this. callthis = call;
        printEvent("requestBodyStart");
    }

    @Override public void requestBodyEnd(Call call, long byteCount) {
        this. callthis = call;
        printEvent("requestBodyEnd");
    }

    @Override public void responseHeadersStart(Call call) {
        this. callthis = call;
        printEvent("responseHeadersStart");
    }

    @Override public void responseHeadersEnd(Call call, Response response) {
        this.response = response;
        this. callthis = call;
        printEvent("responseHeadersEnd");
    }

    @Override public void responseBodyStart(Call call) {
        this. callthis = call;
        printEvent("responseBodyStart");
    }

    @Override public void responseBodyEnd(Call call, long byteCount) {
        this. callthis = call;
        responseOkHttp.responseBodyEnd(call, byteCount);
        printEvent("responseBodyEnd");
    }

    @Override public void callEnd(Call call) {
        responseOkHttp.callEnd(call);
        this. callthis = call;
        printEvent("callEnd");
    }

    @Override public void callFailed(Call call, IOException ioe) {
        this. callthis = call;
        responseOkHttp.callFailed(call, ioe);
        printEvent("callFailed");
    }
}
