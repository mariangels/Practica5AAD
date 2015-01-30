package com.example.mariangeles.practica4aad;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mariangeles.practica4aad.Datos.TablaInmueble;

public class FragmentoLista extends Fragment {

    private Adaptador ad;
    private ListView lvLista;
    private Cursor c;

    public FragmentoLista( ) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View v= inflater.inflate(R.layout.fragmento_lista, container, false);
        lvLista = (ListView) v.findViewById(R.id.lvLista);

        registerForContextMenu(lvLista);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallbacks.onItemSelected(((int) l));
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle e) {
        super.onActivityCreated(e);
        c = getActivity().getContentResolver().query(TablaInmueble.CONTENT_URI,
                TablaInmueble.COLUMNAS,
                null, null, null);
        ad=new Adaptador(getActivity(), c);
        lvLista.setAdapter(ad);
    }

        /********  LongClick  *********/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_long_click, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id=item.getItemId();
        /*
        c.moveToPosition(info.position);
        int idBaseDatos= c.getInt(0);
        */
        Cursor r= (Cursor)lvLista.getItemAtPosition(info.position);
        int idBaseDatos=r.getInt(0);

        switch (id){
            case R.id.borrar:
                act_borrar(idBaseDatos);
                break;
            case R.id.editar:
                act_editar(idBaseDatos);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void act_editar(int id){
        Intent i = new Intent(getActivity(),Editar.class);
        Bundle b=new Bundle();
        b.putInt("index", id);
        i.putExtras(b);
        startActivityForResult(i, 1);
    }


    public void act_borrar(final int id){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("¿Estas seguro?");
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View vista = inflater.inflate(R.layout.dialogo_borrar, null);
        alert.setView(vista);
        alert.setPositiveButton("borrar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        borrar(id);
                        ad.notifyDataSetChanged();
                    }
                });
        alert.setNegativeButton("cancelar", null);
        alert.show();
    }

    public void borrar(int id){
        //delete(Uri uri, String cond, String[] param)
        int delete = getActivity().getContentResolver().delete(TablaInmueble.CONTENT_URI,
                TablaInmueble._ID + " = ?",
                new String[] { String.valueOf(id)});
        tostada("Elemento eliminado");
    }

    public void tostada(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    public String toString(Cursor c){
        // Inmueble nº __ situado en Las Gabias, c/Flor nº 18
        return "Inmueble nº "+ c.getInt(0)+" situado en " + c.getString(2) + ", c/" + c.getString(3) + " nº " + c.getString(4) + " }";
    }


    /**************** CALLBACK ****************/

    private Callbacks mCallbacks = CallbacksVacios;

    public interface Callbacks {
        public void onItemSelected(int id);
    }

    private static Callbacks CallbacksVacios = new Callbacks() {
        @Override
        public void onItemSelected(int id) {
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Error: La actividad debe implementar el callback del fragmento");
        }
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = CallbacksVacios;
    }

}
