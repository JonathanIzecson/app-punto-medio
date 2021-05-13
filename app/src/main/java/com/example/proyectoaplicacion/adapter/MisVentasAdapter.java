package com.example.proyectoaplicacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoaplicacion.MisVentas;
import com.example.proyectoaplicacion.R;
import com.example.proyectoaplicacion.Servicios;

import java.util.List;

public class MisVentasAdapter extends RecyclerView.Adapter<MisVentasAdapter.MisVentasHolder>{

    List<MisVentas> listaMisVentas;

    public MisVentasAdapter(List<MisVentas> listaMisVentas) {
        this.listaMisVentas = listaMisVentas;
    }

    @Override
    public MisVentasAdapter.MisVentasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mis_ventas,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new MisVentasAdapter.MisVentasHolder(vista);
    }

    @Override
    public void onBindViewHolder(MisVentasAdapter.MisVentasHolder holder, int position) {
        holder.lblSe.setText(listaMisVentas.get(position).getProposito());
        holder.lblEncabezado.setText(listaMisVentas.get(position).getEncabezado());
        holder.lblDetalle.setText(listaMisVentas.get(position).getDescripcion());
        holder.lblPrecio.setText(listaMisVentas.get(position).getPrecio());
        //holder.lblDisponibilidad.setText(listaMisVentas.get(position).getDisponibilidad());
    }

    @Override
    public int getItemCount() {
        return listaMisVentas.size();
    }

    public class MisVentasHolder extends RecyclerView.ViewHolder{
        TextView lblSe,lblEncabezado,lblDetalle,lblPrecio,lblDisponibilidad;


        public MisVentasHolder(View itemView) {
            super(itemView);
            lblSe = (TextView) itemView.findViewById(R.id.lblSe);
            lblEncabezado = (TextView) itemView.findViewById(R.id.lblProducto_servicio);
            lblDetalle = (TextView) itemView.findViewById(R.id.lblDescripcion);
            lblPrecio = (TextView) itemView.findViewById(R.id.lblPrecio);
            //lblDisponibilidad = (TextView) itemView.findViewById(R.id.lblDisponibilidad);

        }
    }
}
