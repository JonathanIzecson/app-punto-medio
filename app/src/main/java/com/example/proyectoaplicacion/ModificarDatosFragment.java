package com.example.proyectoaplicacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_LONG;

public class ModificarDatosFragment extends Fragment {
    View vista;
    EditText txtTelefono,txtEmail,txtNombre,txtSexo;
    Button btnGuardar;
    Usuarios users;
    JsonObjectRequest jsonObjectRequest;
    Spinner spinner;
    public ModificarDatosFragment() {
    }
    public static ModificarDatosFragment newInstance(String param1, String param2) {
        ModificarDatosFragment fragment = new ModificarDatosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_modificar_datos, container, false);
        txtNombre = (EditText) vista.findViewById(R.id.txtNombre);
        txtSexo = (EditText) vista.findViewById(R.id.txtSexo);
        txtTelefono = (EditText) vista.findViewById(R.id.txtTelefono);
        txtEmail = (EditText) vista.findViewById(R.id.txtEmail);
        users = InstanciaUsuarios.getUsuariosInstance();
        txtNombre.setText(users.getNombre() + " " + users.getApellido());
        txtSexo.setText(users.getSexo());
        txtTelefono.setText(users.getTelefono());
        txtEmail.setText(users.getEmail());
        spinner = (Spinner)vista.findViewById(R.id.spinnerProposito);


        return vista;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        btnGuardar = getActivity().findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(!txtTelefono.getText().toString().equals("") || !txtEmail.getText().toString().equals("")){
                    String url = "http://192.168.100.40/WebService/WS_PROYECTO_MODIFICAR_DATOS.php?id=" + users.getId() +
                            "&&telefono=" + txtTelefono.getText().toString() + "&&email=" + txtEmail.getText().toString();

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    JSONArray json = response.optJSONArray("respuesta");
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = json.getJSONObject(0);
                                        if(jsonObject.optString("resultado").equals("exito")){
                                            Toast.makeText(getContext(),"Se actualizó satisfactoriamente", LENGTH_LONG).show();
                                            Fragment fragmentPerfil;
                                            fragmentPerfil = new PerfilFragment();
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();

                                        }else if(jsonObject.optString("resultado").equals("error")){
                                            Toast.makeText(getContext(),"Ocurrio un error al actualizar, revisa que los datos sean correctos", LENGTH_LONG).show();
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
                    MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

                }else{
                    Toast.makeText(getContext(),"No puedes dejar campos vacios",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}