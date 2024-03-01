package net.thehornyastudio.todoapps.model;

import java.io.Serializable;

public class Tugas implements Serializable {
    private int id;
    private String judul, tugas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTugas() {
        return tugas;
    }

    public void setTugas(String tugas) {
        this.tugas = tugas;
    }
}