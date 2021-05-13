package com.example.proyectoaplicacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoaplicacion.Productos;
import com.example.proyectoaplicacion.R;
import com.example.proyectoaplicacion.Servicios;

import java.util.List;

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServiciosHolder>{

    List<Servicios> listaServicios;

    public ServiciosAdapter(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }

    @Override
    public ServiciosAdapter.ServiciosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_servicios,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new ServiciosAdapter.ServiciosHolder(vista);
    }

    @Override
    public void onBindViewHolder(ServiciosAdapter.ServiciosHolder holder, int position) {
        holder.lblSe.setText(listaServicios.get(position).getSe().toString());
        holder.lblServicio.setText(listaServicios.get(position).getServicio().toString());
        holder.lblDetalle.setText(listaServicios.get(position).getDetalle().toString());
        holder.lblPrecio.setText(listaServicios.get(position).getPrecio().toString());
        holder.lblNombre.setText(listaServicios.get(position).getNombre().toString());
        holder.lblTelefono.setText(listaServicios.get(position).getTelefono().toString());
        holder.lblEmail.setText(listaServicios.get(position).getEmail().toString());
    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public class ServiciosHolder extends RecyclerView.ViewHolder{
        TextView lblSe,lblServicio,lblDetalle,lblPrecio,lblNombre,lblTelefono,lblEmail;


        public ServiciosHolder(View itemView) {
            super(itemView);
            lblSe = (TextView) itemView.findViewById(R.id.lblSe);
            lblServicio = (TextView) itemView.findViewById(R.id.lblProducto);
            lblDetalle = (TextView) itemView.findViewById(R.id.lblDescripcion);
            lblPrecio = (TextView) itemView.findViewById(R.id.lblPrecio);
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblTelefono = (TextView) itemView.findViewById(R.id.lblTelefono);
            lblEmail = (TextView) itemView.findViewById(R.id.lblEmail);

        }
    }
}
