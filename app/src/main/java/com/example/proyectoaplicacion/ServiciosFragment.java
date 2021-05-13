package com.example.proyectoaplicacion;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyectoaplicacion.adapter.ProductosAdapter;
import com.example.proyectoaplicacion.adapter.ServiciosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServiciosFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    RecyclerView recyclerServicios;
    ArrayList<Servicios> listaServicios;
    JsonObjectRequest jsonObjectRequest;
    View vista;
    ProgressDialog progress;

    public ServiciosFragment() {
    }
    public static ServiciosFragment newInstance(String param1, String param2) {
        ServiciosFragment fragment = new ServiciosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        vista =inflater.inflate(R.layout.fragment_servicios, container, false);
        listaServicios = new ArrayList<>();
        recyclerServicios = (RecyclerView) vista.findViewById(R.id.viewServicios);
        recyclerServicios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerServicios.setHasFixedSize(true);
        cargarWebService();
        return vista;
    }

    private void cargarWebService() {

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();


        String url = "http://192.168.100.40/WebService/WS_PROYECTO_SELECT_SERVICIOS.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Servicios servicios = null;

        JSONArray json=response.optJSONArray("datos");

        try {

            for (int i=0;i<json.length();i++){
                servicios=new Servicios();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                servicios.setId(jsonObject.optInt("SR_ID_SERVICIOS"));
                servicios.setSe(jsonObject.optString("SR_PROPOSITO"));
                servicios.setServicio(jsonObject.optString("SR_ENCABEZADO"));
                servicios.setDetalle(jsonObject.optString("SR_DESCRIPCION"));
                servicios.setPrecio(jsonObject.optString("SR_PRECIO"));
                servicios.setNombre(jsonObject.optString("US_NOMBRE") + " " + jsonObject.optString("US_APELLIDO"));
                servicios.setTelefono(jsonObject.optString("US_TELEFONO"));
                servicios.setEmail(jsonObject.optString("US_EMAIL"));
                listaServicios.add(servicios);
            }
            progress.hide();
            ServiciosAdapter adapter=new ServiciosAdapter(listaServicios);
            recyclerServicios.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }
}