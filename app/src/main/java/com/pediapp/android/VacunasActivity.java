package com.pediapp.android;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by windows 8.1 on 27/05/2017.
 */

public class VacunasActivity extends AppCompatActivity {

    private ListView lst_vacunas;
    private String documento;
    private dbAdapter data;
    private ArrayList<Vacuna> arr_vacunas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);

        documento = getIntent().getExtras().getString("documento");
        lst_vacunas = (ListView)findViewById(R.id.lst_vacunas);
        cargarLista();
        lst_vacunas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogFragment newFragment = MyAlertDialogFragment.newInstance(documento,arr_vacunas.get(i).getVacuna());
                newFragment.show(getSupportFragmentManager(),"");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    private void cargarLista(){
        if(data == null){
            data = new dbAdapter(getApplicationContext());
        }
        Cursor cursor = data.consultaSql("SELECT * FROM Vacunas WHERE cedula="+documento,null);
        if (cursor.moveToFirst()){
            arr_vacunas = new ArrayList<>();
            do{
                arr_vacunas.add(new Vacuna(cursor.getString(1),cursor.getString(3),cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        lst_vacunas.setAdapter(new ListaVacunasAdapter(arr_vacunas,getApplicationContext()));
    }

    public static class MyAlertDialogFragment extends DialogFragment {
        private String documento;
        private String vacuna;

        public static MyAlertDialogFragment newInstance(String documento,String vacuna) {
            MyAlertDialogFragment frag = new MyAlertDialogFragment();
            Bundle args = new Bundle();
            args.putString("documento", documento);
            args.putString("vacuna", vacuna);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final String documento = getArguments().getString("documento");
            final String vacuna = getArguments().getString("vacuna");
            return new AlertDialog.Builder(getActivity())
                    .setTitle("")
                    .setMessage("Marcar como Aplicado")
                    .setPositiveButton("ACEPTAR",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("estado",1);
                                    contentValues.put("fecha",getfechaActual());
                                    dbAdapter data = new dbAdapter(getContext());
                                    data.actualizarRegistro("Vacunas",contentValues,"cedula="+documento+" AND vacuna='"+vacuna+"'",null);
                                    dialog.dismiss();
                                }
                            }
                    )
                    .setNegativeButton("CANCELAR",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            }
                    )
                    .create();
        }

        private String getfechaActual(){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return date.format(c.getTime());
        }
    }
}
