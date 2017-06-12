package com.pediapp.android;

/**
 * Created by windows 8.1 on 12/06/2017.
 */

public class Vacuna {
    private String vacuna;
    private String fecha;
    private int estado;

    public Vacuna(String vacuna, String fecha, int estado) {
        this.vacuna = vacuna;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getVacuna() {
        return vacuna;
    }

    public String getFecha() {
        return fecha;
    }

    public int getEstado() {
        return estado;
    }
}
