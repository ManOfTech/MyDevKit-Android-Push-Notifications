package com.mydevkit.mydevkitregister;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyDevKit {

    public OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public String post(String url, String json, String public_key) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .header("Authorization", public_key)
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String register(String public_key, String application_key, String device_token, HashMap<String, String> custom_json_hashmap) throws IOException {
        HashMap json = new HashMap<String, String>();
        json.putAll(custom_json_hashmap);
        json.put("application_key", application_key);
        json.put("device_token", device_token);
        String json_string = json.toString();
        return post("https://api.mydevkit.io/api/v1/register/android", json_string, public_key);
    }
}