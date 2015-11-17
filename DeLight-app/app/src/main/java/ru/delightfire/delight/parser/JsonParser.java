package ru.delightfire.delight.parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergei on 04.11.2015.
 */
public class JsonParser {
    URL url = null;
    int responseCode;
    StringBuilder response = new StringBuilder();
    JSONObject jsonObject;
    public JSONObject makeRequestHttp(String requestUrl, String method, HashMap<String, String> params)
            throws IOException {

        url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod(method);

        OutputStream out = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.write(buildRequestParams(params));
        writer.flush();
        writer.close();
        out.close();
        responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            String line;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null){
                response.append(line + "\n");
            }
            reader.close();
                try {
                    jsonObject = new JSONObject(response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
            }
        }
        return jsonObject;
    }

    private String buildRequestParams(HashMap<String, String> param) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : param.entrySet()) {
            if(first){
                first = false;
            }else{
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
