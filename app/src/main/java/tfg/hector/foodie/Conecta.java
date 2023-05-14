package tfg.hector.foodie;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Conecta extends AsyncTask<Void, Void, String> {
    public String doInBackground(Void... params) {
        // Realizar la operación de red en segundo plano
        String resultado = "";
        try {
            URLConnection url = new URL("http://localhost/pruebas/recetas.json").openConnection();
            url.connect();
            InputStream in = new BufferedInputStream(url.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            resultado = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    protected void onPostExecute(String resultado) {
        // Actualizar la interfaz de usuario con los datos obtenidos
        // por la operación de red
        //TextView textView = (TextView) findViewById(R.id.text_view);
        //textView.setText(resultado);
    }
}
