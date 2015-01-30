package com.example.mariangeles.practica4aad;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Adaptador extends CursorAdapter{


    public Adaptador(Context context, Cursor c ) {
        super(context, c, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup vg) {
        LayoutInflater i = LayoutInflater.from(vg.getContext());
        View v = i.inflate(R.layout.elemento, vg, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.direccion)).setText(direccion(cursor));
        ((TextView) view.findViewById(R.id.tipo)).setText("Tipo: "+ cursor.getString(5));
        ((TextView) view.findViewById(R.id.precio)).setText(" Precio: "+ cursor.getString(6));
        ((TextView) view.findViewById(R.id.subido)).setText(" Subido: "+ cursor.getString(1));

    }

    public String direccion(Cursor c){
        return "{ " + c.getString(2) + ", c/ " + c.getString(3) + " nº " + c.getString(4) + " }";
    }



}
