package com.example.proyectoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_LONG;

public class Registro extends AppCompatActivity {
    Spinner spinner;
    EditText txtNombre,txtApellido,txtTelefono,txtEmail,txtUsuario,txtPassword;

    JsonObjectRequest jsonObjectRequest;
    Usuarios users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        spinner = (Spinner)findViewById(R.id.spinnerSexo);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_sexo,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

    public void Login(View view){
        Intent cambio = new Intent(Registro.this,MainActivity.class);
        startActivity(cambio);
    }

    public void Registrar(View view){
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String email = txtEmail.getText().toString();
        String usuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();
        String sexo = spinner.getSelectedItem().toString();
        if(!nombre.equals("") || !apellido.equals("") || !telefono.equals("") || email.equals("") || usuario.equals("") || password.equals("")){
            String url = "http://192.168.100.40/WebService/WS_PROYECTO_SIGNIN.php?nombre=" + nombre + "&&apellido="
                    + apellido + "&&sexo=" + sexo + "&&telefono=" + telefono + "&&email=" + email
                    + "&&username=" + usuario + "&&password=" + password;

            jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray json = response.optJSONArray("respuesta");
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = json.getJSONObject(0);
                                if(jsonObject.optString("resultado").equals("exito")){
                                    Toast.makeText(getApplicationContext(),"Se registró satisfactoriamente", LENGTH_LONG).show();

                                    Intent cambio = new Intent(Registro.this,MainActivity.class);
                                    startActivity(cambio);

                                }else if(jsonObject.optString("resultado").equals("error")){
                                    Toast.makeText(getApplicationContext(),"Ocurrio un error al registrar, revisa que los datos sean correctos", LENGTH_LONG).show();
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
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_LONG).show();
        }

    }
}