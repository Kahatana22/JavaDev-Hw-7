package org.example;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpStatusChecker {
    public String getStatusImage(int code) throws Exception {
        String url = "https://http.cat/" + code + ".jpg";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        connection.disconnect();

        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Image not found for status code: " + code);
        }
        return url;
    }
}
