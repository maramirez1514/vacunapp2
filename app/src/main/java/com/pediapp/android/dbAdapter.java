package com.pediapp.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class dbAdapter {
	//Constantes
	private static final String DATABASE_NAME="vacunapp";
	private static final int DATABASE_VERSION=1;
	//Variables
	private  DataBaseMaestro data;
	private SQLiteDatabase db;
	
	//Constructor
	public dbAdapter(Context context){
		data=new DataBaseMaestro(context,dbAdapter.DATABASE_NAME,null,dbAdapter.DATABASE_VERSION);
	}
	
	//Metodo de Insercion de filas
	//DEvuelve el nro de linea insertada -1 si no fue insertada
	public long insertarResgistro(String tabla,ContentValues registros){
		long result=0;
		db=data.getWritableDatabase();
		db.beginTransaction();
		try{
			result=db.insert(tabla,null,registros);
			db.setTransactionSuccessful();
		}catch(SQLiteException ex){
			result=-1;
		}finally{
			db.endTransaction();
		}
		db.close();
		return result;
	}
	
	//Metodo de Actualizacion de Filas
	//Devuelve true si fue actualizada false si no
	public boolean actualizarRegistro(String tabla,ContentValues registros,String clausuraWhere,String[] arg){
		boolean sw=false;
		db=data.getWritableDatabase();
		db.beginTransaction();
		try{
			db.update(tabla, registros, clausuraWhere, arg);
			db.setTransactionSuccessful();
			sw=true;
		}catch(SQLiteException ex){
			
		}finally{
			db.endTransaction();
		}
		db.close();
		return sw;
	}
	
	//Metodo Que realiza un Consulta a la Base de Datos
	//DEvuelve un Cursor con los resultado
	public Cursor consuta(String tabla,String[] campos,String clausuraWhere,String[] arg,String Group,String clausuraHaving,String order){
		db=data.getReadableDatabase();
		Cursor cursor=null;
		try{
			cursor=db.query(tabla,campos,clausuraWhere,arg,Group,clausuraHaving,order);
		}catch(SQLiteException ex){
			
		}
		return cursor;
	}
	
	//Metodo que realiza un Consulta SQL a la Base de Datos
	//Devuelve un cursor con querry del resultado
	public Cursor consultaSql(String sql,String[] args){
		db=data.getReadableDatabase();
		Cursor cursor=null;
		try{
			cursor=db.rawQuery(sql,args);
		}catch(SQLiteException ex){
			
		}
		return cursor;
	}
	
	//Metodo para eliminar un final de la tabla
	//Devuelve True si el borrado fue exitoso false si no
	public boolean eliminarRegistros(String tabla,String clausuraWhere,String[] args ){
		boolean sw=false;
		db=data.getWritableDatabase();
		db.beginTransaction();
		try{
			db.delete(tabla, clausuraWhere, args);
			db.setTransactionSuccessful();
			sw=true;
		}catch(SQLiteException ex){
			
		}finally{
			db.endTransaction();
		}
		db.close();
		return sw;
	}
	
	//Metodo que ejecuta una setencia SQL
	public boolean sqlQuery(String sql){
		db=data.getWritableDatabase();
		db.beginTransaction();
		db.execSQL(sql);
		db.setTransactionSuccessful();
		db.endTransaction();
		db.close();
		return true;
	}
	
	//Metodo que cierra la BD
	public void close(){
		db.close();
	}
}
