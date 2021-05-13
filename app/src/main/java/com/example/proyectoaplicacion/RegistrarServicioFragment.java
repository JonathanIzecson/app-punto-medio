package com.example.proyectoaplicacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class RegistrarServicioFragment extends Fragment {
    View vista;
    Button btnGuardar;
    Spinner spinner;
    EditText txtTitulo,txtDescripcion,txtPrecio;
    JsonObjectRequest jsonObjectRequest;
    Usuarios users;
    public RegistrarServicioFragment() {
    }
    public static RegistrarServicioFragment newInstance(String param1, String param2) {
        RegistrarServicioFragment fragment = new RegistrarServicioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registrar_servicio, container, false);
        txtTitulo = (EditText)vista.findViewById(R.id.txtTitulo);
        txtDescripcion = (EditText)vista.findViewById(R.id.txtDescripcion);
        txtPrecio = (EditText)vista.findViewById(R.id.txtPrecio);

        spinner = (Spinner) vista.findViewById(R.id.spinnerProposito);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.combo_propositoServ,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

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
                if(!txtTitulo.getText().toString().equals("") || !txtDescripcion.getText().toString().equals("")){
                    users = InstanciaUsuarios.getUsuariosInstance();
                    String Titulo = txtTitulo.getText().toString();
                    Titulo = Titulo.replace(" ","%20");
                    String Descripcion = txtDescripcion.getText().toString();
                    Descripcion = Descripcion.replace(" ","%20");
                    String Precio = txtPrecio.getText().toString();
                    Precio = Precio.replace(" ", "%20");
                    String url = "http://192.168.100.40/WebService/WS_PROYECTO_REGISTRAR_SERVICIO.php?encabezado=" + Titulo +
                            "&&descripcion=" + Descripcion + "&&precio=" + Precio +
                            "&&proposito=" + spinner.getSelectedItem().toString() + "&&idUser=" + users.getId();

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    JSONArray json = response.optJSONArray("respuesta");
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = json.getJSONObject(0);
                                        if(jsonObject.optString("resultado").equals("exito")){
                                            Toast.makeText(getContext(),"Se Registró satisfactoriamente", LENGTH_LONG).show();
                                            Fragment fragmentPerfil;
                                            fragmentPerfil = new PerfilFragment();
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();

                                        }else if(jsonObject.optString("resultado").equals("error")){
                                            Toast.makeText(getContext(),"Ocurrio un error al registrar", Toast.LENGTH_LONG).show();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(getContext(),"Ocurrio un error en el catch" + e, LENGTH_LONG).show();
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