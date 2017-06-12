package com.pediapp.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseMaestro extends SQLiteOpenHelper {
	
	private static final String TablaPersona="CREATE TABLE Persona(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			 													 +"cedula INTEGER UNIQUE,"
															     +"nombre TEXT,"
															     +"apellido TEXT);";

	private static final String TablaVacunas="CREATE TABLE Vacunas(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
														         +"vacuna TEXT,"
																 +"cedula INTEGER,"
																 +"fecha NUMERIC," +
																  "fechacalendario TEXT," +
																  "estado INTEGER DEFAULT 0);";
	
	public DataBaseMaestro(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TablaPersona);
		db.execSQL(TablaVacunas);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
