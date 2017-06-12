package com.pediapp.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by windows 8.1 on 27/05/2017.
 */

public class ListaVacunasAdapter extends BaseAdapter {

    private ArrayList<Vacuna> arr_vacunas;
    private Context context;
    private LayoutInflater inflater = null;

    public ListaVacunasAdapter(ArrayList<Vacuna> arr_vacunas, Context context) {
        this.arr_vacunas = arr_vacunas;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arr_vacunas.size();
    }

    @Override
    public Object getItem(int i) {
        return arr_vacunas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_vacunas,null);
            holder=new ViewHolder();
            holder.lblvacuna = (TextView)convertView.findViewById(R.id.txtvacuna);
            holder.lblestado = (TextView)convertView.findViewById(R.id.txtestado);
            holder.lblfechaaplicacion = (TextView)convertView.findViewById(R.id.txtfechaaplicacion);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.lblvacuna.setText("VACUNA :" + arr_vacunas.get(position).getVacuna());
        if(arr_vacunas.get(position).getEstado() == 0){
            holder.lblestado.setText("ESTADO :NO APLICADO");
        }else{
            holder.lblestado.setText("ESTADO :APLICADO");
        }
        holder.lblfechaaplicacion.setText("Fecha Aplicacion :" + arr_vacunas.get(position).getFecha());
        return convertView;
    }

    class ViewHolder{
        TextView lblvacuna;
        TextView lblestado;
        TextView lblfechaaplicacion;
    }
}
