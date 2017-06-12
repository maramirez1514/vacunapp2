package com.pediapp.android;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by windows 8.1 on 27/05/2017.
 */

public class AgregarActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtCedula;
    private Button button;
    private dbAdapter data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        data = new dbAdapter(getApplicationContext());

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtApellido = (EditText)findViewById(R.id.edtApellido);
        edtCedula = (EditText)findViewById(R.id.edtCedula);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("cedula",edtCedula.getText().toString());
                contentValues.put("nombre",edtNombre.getText().toString());
                contentValues.put("apellido",edtApellido.getText().toString());
                data.insertarResgistro("Persona",contentValues);
                contentValues.clear();

                contentValues.put("vacuna","vacuna1");
                contentValues.put("cedula",edtCedula.getText().toString());
                contentValues.put("fechacalendario","2017-06-12");
                data.insertarResgistro("Vacunas",contentValues);
                contentValues.clear();

                contentValues.put("vacuna","vacuna2");
                contentValues.put("cedula",edtCedula.getText().toString());
                contentValues.put("fechacalendario","2017-06-12");
                data.insertarResgistro("Vacunas",contentValues);
                contentValues.clear();

                contentValues.put("vacuna","vacuna3");
                contentValues.put("cedula",edtCedula.getText().toString());
                contentValues.put("fechacalendario","2017-06-12");
                data.insertarResgistro("Vacunas",contentValues);
                contentValues.clear();

                finish();
            }
        });
    }
}
