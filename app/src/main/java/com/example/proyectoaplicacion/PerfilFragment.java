package com.example.proyectoaplicacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilFragment extends Fragment {

    TextView lblNombre,lblSexo,lblTelefono,lblEmail;
    View vista;
    Usuarios users;
    Fragment fragmentPerfil;
    FragmentTransaction transaction;
    Button btnModificar,btnAgregarProducto,btnAgregarServicio,btnEliminarPost;
    public PerfilFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista =inflater.inflate(R.layout.fragment_perfil, container, false);
        lblNombre = (TextView)vista.findViewById(R.id.lblPerfil);
        lblSexo = (TextView)vista.findViewById(R.id.lblSexo);
        lblTelefono = (TextView)vista.findViewById(R.id.lblTelefono);
        lblEmail = (TextView)vista.findViewById(R.id.lblEmail);
        users = InstanciaUsuarios.getUsuariosInstance();
        lblNombre.setText(users.getNombre() + " " + users.getApellido());
        lblSexo.setText(users.getSexo());
        lblTelefono.setText(users.getTelefono());
        lblEmail.setText(users.getEmail());
        return vista;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        btnModificar = getActivity().findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fragmentPerfil = new ModificarDatosFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
            }
        });

        btnAgregarProducto = getActivity().findViewById(R.id.btnAgregarProducto);
        btnAgregarProducto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fragmentPerfil = new RegistrarProductosFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
            }
        });

        btnAgregarServicio = getActivity().findViewById(R.id.btnAgregarServicio);
        btnAgregarServicio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fragmentPerfil = new RegistrarServicioFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
            }
        });

        btnEliminarPost = getActivity().findViewById(R.id.btnEliminarPost);
        btnEliminarPost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fragmentPerfil = new EliminarPostFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
            }
        });

    }
}