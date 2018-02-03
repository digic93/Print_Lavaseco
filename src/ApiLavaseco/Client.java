/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiLavaseco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dell
 */
public class Client {

    private int branchOfficeId;
    private int[] salePointIds;
    private String stringUrl;

    public Client(int branchOfficeId, int[] salePointIds, String url) {
        this.branchOfficeId = branchOfficeId;
        this.salePointIds = salePointIds;
        this.stringUrl = url;
    }

    public JSONObject getBills() throws MalformedURLException, IOException, ParseException {
        URL url = new URL(stringUrl + "/" + branchOfficeId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        String input = getData();
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse((Reader)br);
        conn.disconnect();
        return json;
    }

    private String getData() {
        JSONArray salePoints = new JSONArray();
        for (int salePointId : salePointIds) {
            salePoints.add(salePointId);
        }
        JSONObject data = new JSONObject();
        data.put("salePoints", salePoints);
        
        return data.toJSONString();
    }
}
