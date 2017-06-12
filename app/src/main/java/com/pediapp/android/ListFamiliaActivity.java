package com.pediapp.android;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NotificationCompat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by windows 8.1 on 27/05/2017.
 */

public class ListFamiliaActivity extends AppCompatActivity{

    private TextView txtNombre;
    private TextView txtEmail;
    private ListView list_item;
    private FloatingActionButton floatingActionButton;
    private Bundle bundle;
    private dbAdapter data;
    private ArrayList<Persona> arr_personas;
    private NotificationManager notificacion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_familia);

        bundle = getIntent().getExtras();
        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtNombre.setText("Nombre :"+bundle.getString("nombre"));
        txtEmail = (TextView)findViewById(R.id.txtEmail);
        txtEmail.setText("Email :"+bundle.getString("email"));
        list_item=(ListView)findViewById(R.id.list_item);
        cargarLista();
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),VacunasActivity.class);
                intent.putExtra("documento",arr_personas.get(position).getCedula());
                startActivity(intent);
            }
        });

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AgregarActivity.class));
            }
        });
        comprobarCalendario();
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
        Cursor cursor = data.consultaSql("SELECT * FROM Persona",null);
        arr_personas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                arr_personas.add(new Persona(cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        list_item.setAdapter(new ListaFamiliaAdapter(arr_personas,getApplicationContext()));
    }

    private void comprobarCalendario(){
        if(data == null){
            data = new dbAdapter(getApplicationContext());
        }
        Cursor cursor = data.consultaSql("SELECT * FROM Vacunas WHERE fechacalendario='"+getfechaActual()+"'",null);
        if(cursor.moveToFirst()){
            lanzarNotificacion();
        }
    }

    private String getfechaActual(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.format(c.getTime());
    }

    private void lanzarNotificacion(){
        Intent notIntent=new Intent(this,ListFamiliaActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP );
        int requestID = (int) System.currentTimeMillis();
        notIntent.setAction("myString"+ requestID);
        notIntent.setData((Uri.parse("mystring"+requestID)));

        NotificationCompat.Builder mbuilder=new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.ic_launcher))
                .setContentTitle(getResources().getString(R.string.app_name))
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("Hay vacunas que aplicar")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLights(Color.YELLOW,500,500);

        PendingIntent contentIntent=PendingIntent.getActivity(this,requestID, notIntent,0);

        mbuilder.setContentIntent(contentIntent);
        notificacion =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificacion.notify(1, mbuilder.build());
    }
}
