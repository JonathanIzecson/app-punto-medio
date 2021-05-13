package com.example.proyectoaplicacion;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyectoaplicacion.adapter.ProductosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductosFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    RecyclerView recyclerProductos;
    ArrayList<Productos> listaProductos;
    JsonObjectRequest jsonObjectRequest;
    View vista;
    ProgressDialog progress;
    RequestQueue queue;

    public ProductosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_productos, container, false);
        listaProductos = new ArrayList<>();

        recyclerProductos = (RecyclerView) vista.findViewById(R.id.viewProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerProductos.setHasFixedSize(true);
        cargarWebService();
        return vista;
    }

    private void cargarWebService() {

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();


        String url = "http://192.168.100.40/WebService/WS_PROYECTO_SELECT_PRODUCTOS.php";

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
        Productos productos = null;

        JSONArray json=response.optJSONArray("datos");

        try {

            for (int i=0;i<json.length();i++){
                productos=new Productos();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                productos.setId(jsonObject.optInt("PR_ID_PRODUCTO"));
                productos.setSe(jsonObject.optString("PR_PROPOSITO"));
                productos.setProducto(jsonObject.optString("PR_ENCABEZADO"));
                productos.setDetalle(jsonObject.optString("PR_DESCRIPCION"));
                productos.setPrecio(jsonObject.optString("PR_PRECIO"));
                productos.setNombre(jsonObject.optString("US_NOMBRE") + " " + jsonObject.optString("US_APELLIDO"));
                productos.setTelefono(jsonObject.optString("US_TELEFONO"));
                productos.setEmail(jsonObject.optString("US_EMAIL"));
                listaProductos.add(productos);
            }
            progress.hide();
            ProductosAdapter adapter=new ProductosAdapter(listaProductos);
            recyclerProductos.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }
}
   /* private void listarProductos() {
        String url = "http://192.168.100.40/WebService/WS_PROYECTO_SELECT_PRODUCTOS.php";
        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Productos productos = null;
                        JSONArray json = response.optJSONArray("datos");

                        try {
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject jsonObject = null;
                                productos = new Productos();
                                jsonObject = json.getJSONObject(i);
                                productos.setId(jsonObject.optInt("PR_ID_PRODUCTO"));
                                productos.setSe(jsonObject.optString("PR_PROPOSITO"));
                                productos.setProducto(jsonObject.optString("PR_ENCABEZADO"));
                                productos.setDetalle(jsonObject.optString("PR_DESCRIPCION"));
                                productos.setPrecio(jsonObject.optString("PR_PRECIO"));
                                productos.setNombre(jsonObject.optString("US_NOMBRE") + " " + jsonObject.optString("US_APELLIDO"));
                                productos.setTelefono(jsonObject.optString("US_TELEFONO"));
                                productos.setEmail(jsonObject.optString("US_EMAIL"));
                                listaProductos.add(productos);
                            }
                            ProductosAdapter productosAdapter = new ProductosAdapter(listaProductos);
                            recyclerProductos.setAdapter(productosAdapter);

                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Ocurrio un error en el try", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getContext(), "Ocurrio un error en el volley", Toast.LENGTH_SHORT).show();
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

    }*/
