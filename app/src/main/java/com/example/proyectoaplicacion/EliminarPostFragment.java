package com.example.proyectoaplicacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyectoaplicacion.adapter.MisVentasAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_LONG;

public class EliminarPostFragment extends Fragment {
    Button btnBuscar,btnEliminar;
    EditText txtTitulo,txtDescripcion,txtPrecio;
    View vista;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog progreso;

    public EliminarPostFragment() {
    }

    public static EliminarPostFragment newInstance(String param1, String param2) {
        EliminarPostFragment fragment = new EliminarPostFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_eliminar_post, container, false);
        txtTitulo = (EditText) vista.findViewById(R.id.txtTitulo);
        txtDescripcion = (EditText)vista.findViewById(R.id.txtDescripcion);
        txtPrecio = (EditText)vista.findViewById(R.id.txtPrecio);
        return vista;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        btnBuscar = getActivity().findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtTitulo.getText().toString().equals("")){
                    Usuarios users = InstanciaUsuarios.getUsuariosInstance();
                    String Titulo = txtTitulo.getText().toString();
                    Titulo = Titulo.replace(" ","%20");
                    String url = "http://192.168.100.40/WebService/WS_PROYECTO_BUSCAR.php?titulo=" + Titulo + "&&id=" + users.getId();

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    JSONArray json = response.optJSONArray("datos");
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = json.getJSONObject(0);
                                        if(jsonObject.optString("resultado").equals("vacio")){
                                            Toast.makeText(getContext(),"No existe post con ese titulo", LENGTH_LONG).show();
                                        }else{
                                            txtDescripcion.setText(jsonObject.optString("PR_DESCRIPCION"));
                                            txtPrecio.setText(jsonObject.optString("PR_PRECIO"));
                                            Toast.makeText(getContext(),"Post existente", LENGTH_LONG).show();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(getContext(),e.toString(), LENGTH_LONG).show();
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
                    Toast.makeText(getContext(),"Debes buscar por titulo",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar = getActivity().findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(!txtTitulo.getText().toString().equals("")){
                    Usuarios users = InstanciaUsuarios.getUsuariosInstance();
                    String Titulo = txtTitulo.getText().toString();
                    Titulo = Titulo.replace(" ","%20");
                    String url = "http://192.168.100.40/WebService/WS_PROYECTO_ELIMINAR_POST.php?titulo=" + Titulo + "&&id=" + users.getId();

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    JSONArray json = response.optJSONArray("respuesta");
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = json.getJSONObject(0);
                                        if(jsonObject.optString("resultado").equals("exito")){
                                            Toast.makeText(getContext(),"Se eliminó satisfactoriamente", LENGTH_LONG).show();
                                            Fragment fragmentPerfil;
                                            fragmentPerfil = new PerfilFragment();
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();

                                        }else if(jsonObject.optString("resultado").equals("error")){
                                            Toast.makeText(getContext(),"Ocurrio un error al eliminar, revisa que los datos sean correctos", LENGTH_LONG).show();
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