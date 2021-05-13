package com.example.proyectoaplicacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoaplicacion.Productos;
import com.example.proyectoaplicacion.R;

import java.util.List;
public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosHolder>{

    List<Productos> listaProductos;

    public ProductosAdapter(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public ProductosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_productos,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new ProductosHolder(vista);
    }

    @Override
    public void onBindViewHolder(ProductosHolder holder, int position) {
        holder.lblSe.setText(listaProductos.get(position).getSe().toString());
        holder.lblProducto.setText(listaProductos.get(position).getProducto().toString());
        holder.lblDetalle.setText(listaProductos.get(position).getDetalle().toString());
        holder.lblPrecio.setText(listaProductos.get(position).getPrecio().toString());
        holder.lblNombre.setText(listaProductos.get(position).getNombre().toString());
        holder.lblTelefono.setText(listaProductos.get(position).getTelefono().toString());
        holder.lblEmail.setText(listaProductos.get(position).getEmail().toString());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosHolder extends RecyclerView.ViewHolder{
        TextView lblSe,lblProducto,lblDetalle,lblPrecio,lblNombre,lblTelefono,lblEmail;


        public ProductosHolder(View itemView) {
            super(itemView);
            lblSe = (TextView) itemView.findViewById(R.id.lblSe);
            lblProducto = (TextView) itemView.findViewById(R.id.lblProducto);
            lblDetalle = (TextView) itemView.findViewById(R.id.lblDescripcion);
            lblPrecio = (TextView) itemView.findViewById(R.id.lblPrecio);
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblTelefono = (TextView) itemView.findViewById(R.id.lblTelefono);
            lblEmail = (TextView) itemView.findViewById(R.id.lblEmail);

        }
    }
}