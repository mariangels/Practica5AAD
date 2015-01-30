package com.example.mariangeles.practica4aad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


public class ActividadLista extends FragmentActivity implements FragmentoLista.Callbacks{

    private boolean dosFragmentos;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.actividad_lista);
        setUsuarioSharedPreferences("mariam");
        if (findViewById(R.id.fragmentoDetalle) != null) {
            dosFragmentos = true;
        }else{
            dosFragmentos = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;
        if (R.id.nuevo == item.getItemId()) {
            result=true;

            Intent i = new Intent(this, Editar.class);
            Bundle b=new Bundle();
            b.putInt("index",-1);
            i.putExtras(b);
            startActivity(i);
        }

        return result;
    }

    @Override
    public void onItemSelected(int id) {
        if(dosFragmentos){
            FragmentoDetalle fragmento= (FragmentoDetalle) getSupportFragmentManager().findFragmentById(R.id.fragmentoDetalle);
            if (fragmento != null && fragmento.isInLayout()) {
                fragmento.mostrar(id);
            }

        }else{
            Intent i = new Intent(this, ActividadDetalle.class);
            i.putExtra("index", id);
            startActivity(i);
        }
    }

    private String getUsuarioSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        return sp.getString("usuario", "");
    }

    private void setUsuarioSharedPreferences(String usuario) {
        SharedPreferences sp = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("usuario", usuario);
        ed.apply();
    }

    public void tostada(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}


