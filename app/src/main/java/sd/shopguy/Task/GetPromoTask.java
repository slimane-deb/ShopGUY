package sd.shopguy.Task;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ProUser on 26/06/2016.
 */
public class GetPromoTask extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        String data;
        try {
            URL url = new URL("http://192.168.173.1:8080/getpromos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Attendre 5 secondes max pour établir la connexion
            conn.setConnectTimeout(5000);
            // Attendre 1 minute max pour lire les données
            conn.setReadTimeout(60000);
            if (conn.getResponseCode()==200) {
               /* pd = new ProgressDialog(context);
                pd.setTitle("Please Wait..");
                pd.setMessage("Loading...");
                pd.show();*/
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((data = reader.readLine()) != null) {
                    result.append(data);
                }

            }
            else {
                int code = conn.getResponseCode();
                System.out.println(code);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
