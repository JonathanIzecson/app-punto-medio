package com.example.proyectoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity{

    EditText txtUsername, txtPassword;
    JsonObjectRequest jsonObjectRequest;
    private static Usuarios users = new Usuarios();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
    }
    public void IniciarSesion(View view){
        if(!txtUsername.getText().toString().equals("") || !txtPassword.getText().toString().equals("")){


            String url = "http://192.168.100.40/WebService/WS_PROYECTO_LOGIN.php?username=" + txtUsername.getText().toString() +
                    "&&password=" + txtPassword.getText().toString();
            jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray json = response.optJSONArray("respuesta");
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = json.getJSONObject(0);
                                if(jsonObject.optString("acceso").equals("permitido")){
                                    Toast.makeText(getApplicationContext(),"Acceso permitido", LENGTH_LONG).show();

                                    extraterDatos();

                                    Intent cambio = new Intent(MainActivity.this,Principal.class);
                                    //cambio.putExtra("nombre",users.getNombre());
                                    //cambio.putExtra("apellido",users.getApellido());
                                    startActivity(cambio);

                                }else if(jsonObject.optString("acceso").equals("denegado")){
                                    Toast.makeText(getApplicationContext(),"Usuario y/o contraseña incorrectos", LENGTH_LONG).show();
                                }
                            }catch (Exception e){

                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });

            // Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        }else{
            Toast.makeText(this,"Debes llenar los campos", LENGTH_LONG).show();
        }
    }

    private void extraterDatos() {

        String url = "http://192.168.100.40/WebService/WS_PROYECTO_DATOS_USUARIO.php?username=" + txtUsername.getText().toString();
        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray json = response.optJSONArray("datos");
                        JSONObject jsonObject = null;
                        try {
                            users = InstanciaUsuarios.getUsuariosInstance();
                            jsonObject = json.getJSONObject(0);
                                users.setId(jsonObject.optInt("US_ID"));
                                users.setNombre(jsonObject.optString("US_NOMBRE"));
                                users.setApellido(jsonObject.optString("US_APELLIDO"));
                                users.setSexo(jsonObject.optString("US_SEXO"));
                                users.setTelefono(jsonObject.optString("US_TELEFONO"));
                                users.setEmail(jsonObject.optString("US_EMAIL"));
                                users.setUsername(jsonObject.optString("US_USERNAME"));
                                users.setPassword(jsonObject.optString("US_PASSWORD"));

                        }catch (Exception e){

                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

    public void Registrar(View view){
        Intent cambio = new Intent(MainActivity.this,Registro.class);
        startActivity(cambio);
    }
}