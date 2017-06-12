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

public class ListaFamiliaAdapter extends BaseAdapter {
    private ArrayList<Persona> arr_personas;
    private Context context;
    private LayoutInflater inflater = null;

    public ListaFamiliaAdapter(ArrayList<Persona> arr_personas, Context context) {
        this.arr_personas = arr_personas;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arr_personas.size();
    }

    @Override
    public Object getItem(int i) {
        return arr_personas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_familia,null);
            holder=new ViewHolder();
            holder.lblNombre = (TextView)convertView.findViewById(R.id.txt_item_nombre);
            holder.lblDocumento = (TextView)convertView.findViewById(R.id.txt_item_documento);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.lblNombre.setText("Nombre :"+arr_personas.get(position).getNombre()+" "+arr_personas.get(position).getApellido());
        holder.lblDocumento.setText("Documento :"+arr_personas.get(position).getCedula());
        return convertView;
    }

    class ViewHolder{
        TextView lblNombre;
        TextView lblDocumento;
    }
}
