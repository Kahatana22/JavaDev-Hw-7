package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusImageDownloader {
    public void downloadStatusImage(int code) throws Exception {
        HttpStatusChecker checker = new HttpStatusChecker();
        String imageUrl = checker.getStatusImage(code);
        URL url = new URL(imageUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String fileName = url.getFile();
        String[] pathSegments = fileName.split("/");
        String imageName = pathSegments[pathSegments.length - 1];
        File outputFile = new File("src/main/resources/" + imageName);

        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        connection.disconnect();
        System.out.println("Image downloaded for status code " + code + " and saved as: " + imageName);
    }
}
