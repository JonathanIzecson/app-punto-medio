package com.example.proyectoaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    TextView lblBienvenido;
    Usuarios users;
    Fragment fragmentPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        lblBienvenido = (TextView)findViewById(R.id.lblBienvenido);
        users = InstanciaUsuarios.getUsuariosInstance();

        lblBienvenido.setText("Bienvenido " + users.getNombre() + " " + users.getApellido());
        fragmentPerfil = new PerfilFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,fragmentPerfil).commit();
    }
    public void productos(View view){
        fragmentPerfil = new ProductosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
    }

    public void servicios(View view){
        fragmentPerfil = new ServiciosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
    }
    public void misVentas(View view){
        fragmentPerfil = new MisVentasFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
    }
    public void perfil(View view){
        fragmentPerfil = new PerfilFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentPerfil).commit();
    }

}