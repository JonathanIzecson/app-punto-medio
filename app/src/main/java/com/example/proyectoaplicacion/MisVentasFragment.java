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
import com.example.proyectoaplicacion.adapter.MisVentasAdapter;
import com.example.proyectoaplicacion.adapter.ServiciosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MisVentasFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    RecyclerView recyclerMisVentas;
    ArrayList<MisVentas> listaMisVentas;
    JsonObjectRequest jsonObjectRequest;
    View vista;
    ProgressDialog progress;
    public MisVentasFragment() {
    }
    public static MisVentasFragment newInstance(String param1, String param2) {
        MisVentasFragment fragment = new MisVentasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_mis_ventas, container, false);
        listaMisVentas = new ArrayList<>();
        recyclerMisVentas = (RecyclerView) vista.findViewById(R.id.viewMisVentas);
        recyclerMisVentas.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerMisVentas.setHasFixedSize(true);
        cargarWebService();

        return vista;
    }

    private void cargarWebService() {

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        Usuarios users = InstanciaUsuarios.getUsuariosInstance();
        String url = "http://192.168.100.40/WebService/WS_PROYECTO_MIS_VENTAS.php?id=" + users.getId();

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
        MisVentas ventas = null;

        JSONArray json=response.optJSONArray("datos");

        try {

            JSONObject jsonObject=null;
            jsonObject=json.getJSONObject(0);
            if(jsonObject.optString("Resultado").equals("vacio")){
                Toast.makeText(getContext(),"No tienes publicaciones aun",Toast.LENGTH_SHORT).show();
                progress.hide();
            }else if(jsonObject.optString("Resultado").equals("vacio")){
                Toast.makeText(getContext(),"No se envio tu id",Toast.LENGTH_SHORT).show();
                progress.hide();
            }else{
                for (int i=0;i<json.length();i++){
                    ventas=new MisVentas();
                    jsonObject=null;
                    jsonObject=json.getJSONObject(i);

                    ventas.setId(jsonObject.optInt("PR_ID_PRODUCTO"));
                    ventas.setEncabezado(jsonObject.optString("PR_ENCABEZADO"));
                    ventas.setDescripcion(jsonObject.optString("PR_DESCRIPCION"));
                    ventas.setPrecio(jsonObject.optString("PR_PRECIO"));
                    ventas.setStatus(jsonObject.optString("PR_STATUS"));
                    listaMisVentas.add(ventas);
                }
                progress.hide();
                MisVentasAdapter adapter=new MisVentasAdapter(listaMisVentas);
                recyclerMisVentas.setAdapter(adapter);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }
}