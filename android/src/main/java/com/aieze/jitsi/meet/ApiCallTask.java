package com.aieze.jitsi.meet;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiCallTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        try {
            // Create URL
            URL url = new URL(params[0]);

            // Open connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                // Set up the connection
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000); // milliseconds
                urlConnection.setConnectTimeout(15000); // milliseconds

                // Get the response code
                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    in.close();
                    return response.toString();
                } else {
                    // Handle error
                    return null;
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Handle the result, e.g., update UI or parse JSON
    }
}
