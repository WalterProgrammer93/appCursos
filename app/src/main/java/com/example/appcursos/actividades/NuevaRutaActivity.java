package com.example.appcursos.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Utilidades;
import com.google.android.gms.maps.model.LatLng;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NuevaRutaActivity extends AppCompatActivity {

    EditText txtLatInicio,txtLongInicio,txtLatFinal,txtLongFinal;
    Button btn_obtenerCoordenadas;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_ruta);

        txtLatInicio= findViewById(R.id.txtLatIni);
        txtLongInicio= findViewById(R.id.txtLongIni);
        txtLatFinal= findViewById(R.id.txtLatFin);
        txtLongFinal= findViewById(R.id.txtLongFin);

        btn_obtenerCoordenadas = findViewById(R.id.btn_obtenerCoordenadas);
        btn_obtenerCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilidades.coordenadas.setLatitudInicial(Double.valueOf(txtLatInicio.getText().toString()));
                Utilidades.coordenadas.setLongitudInicial(Double.valueOf(txtLongInicio.getText().toString()));
                Utilidades.coordenadas.setLatitudFinal(Double.valueOf(txtLatFinal.getText().toString()));
                Utilidades.coordenadas.setLongitudFinal(Double.valueOf(txtLongFinal.getText().toString()));

                webServiceObtenerRuta(txtLatInicio.getText().toString(),txtLongInicio.getText().toString(),
                        txtLatFinal.getText().toString(),txtLongFinal.getText().toString());

                Intent miIntent=new Intent(NuevaRutaActivity.this, MapaActivity.class);
                startActivity(miIntent);
            }
        });
        request= Volley.newRequestQueue(getApplicationContext());
    }

    private void webServiceObtenerRuta(String latitudInicial, String longitudInicial, String latitudFinal, String longitudFinal) {
        String url="https://maps.googleapis.com/maps/api/directions/json?origin="+latitudInicial+","+longitudInicial
                +"&destination="+latitudFinal+","+longitudFinal;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jRoutes;
                JSONArray jLegs;
                JSONArray jSteps;

                try {

                    jRoutes = response.getJSONArray("routes");

                    /* Traversing all routes */
                    for(int i=0;i<jRoutes.length();i++){
                        jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                        List<HashMap<String, String>> path = new ArrayList<>();

                        /* Traversing all legs */
                        for(int j=0;j<jLegs.length();j++){
                            jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                            /* Traversing all steps */
                            for(int k=0;k<jSteps.length();k++){
                                String polyline;
                                polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                                List<LatLng> list = decodePoly(polyline);

                                /* Traversing all points */
                                for(int l=0;l<list.size();l++){
                                    HashMap<String, String> hm = new HashMap<>();
                                    hm.put("lat", Double.toString((list.get(l)).latitude) );
                                    hm.put("lng", Double.toString((list.get(l)).longitude) );
                                    path.add(hm);
                                }
                            }
                            Utilidades.routes.add(path);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception ignored){
                }

            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                Log.d("ERROR: ", error.toString());
            }
        });

        request.add(jsonObjectRequest);
    }

    public List<List<HashMap<String,String>>> parse(JSONObject jObject){
        //Este método PARSEA el JSONObject que retorna del API de Rutas de Google devolviendo
        //una lista del lista de HashMap Strings con el listado de Coordenadas de Lat y Long,
        //con la cual se podrá dibujar pollinas que describan la ruta entre 2 puntos.
        JSONArray jRoutes;
        JSONArray jLegs;
        JSONArray jSteps;

        try {

            jRoutes = jObject.getJSONArray("routes");

            /* Traversing all routes */
            for(int i=0;i<jRoutes.length();i++){
                jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                List<HashMap<String, String>> path = new ArrayList<>();

                /* Traversing all legs */
                for(int j=0;j<jLegs.length();j++){
                    jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                    /* Traversing all steps */
                    for(int k=0;k<jSteps.length();k++){
                        String polyline;
                        polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                        List<LatLng> list = decodePoly(polyline);

                        /* Traversing all points */
                        for(int l=0;l<list.size();l++){
                            HashMap<String, String> hm = new HashMap<>();
                            hm.put("lat", Double.toString((list.get(l)).latitude) );
                            hm.put("lng", Double.toString((list.get(l)).longitude) );
                            path.add(hm);
                        }
                    }
                    Utilidades.routes.add(path);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception ignored){
        }
        return Utilidades.routes;
    }

    public void onClick(View view) {

        String latitudA = txtLatInicio.getText().toString();
        String longitudA = txtLongInicio.getText().toString();
        String latitudB = txtLatFinal.getText().toString();
        String longitudB = txtLongFinal.getText().toString();

        if (view.getId()==R.id.btn_obtenerCoordenadas){
            txtLatInicio.setText(latitudA); txtLongInicio.setText(longitudA);
            txtLatFinal.setText(latitudB); txtLongFinal.setText(longitudB);
        }

    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salir) {
            finish();
            Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
            Intent salir = new Intent(NuevaRutaActivity.this, MainActivity.class);
            startActivity(salir);
        }
        return super.onOptionsItemSelected(item);

    }
}
