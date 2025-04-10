package org.example;

import java.time.LocalDate;

public class Angajat {
    private String nume;
    private String postul;
    private LocalDate data_angajari;
    private float salar;

    public Angajat() {
    }

    public Angajat(String nume, String postul, LocalDate data_angajari, float salar) {
        this.nume = nume;
        this.postul = postul;
        this.data_angajari = data_angajari;
        this.salar = salar;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPostul() {
        return postul;
    }

    public void setPostul(String postul) {
        this.postul = postul;
    }

    public LocalDate getData_angajari() {
        return data_angajari;
    }

    public void setData_angajari(LocalDate data_angajari) {
        this.data_angajari = data_angajari;
    }

    public float getSalar() {
        return salar;
    }

    public void setSalar(float salar) {
        this.salar = salar;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", postul='" + postul + '\'' +
                ", data_angajari=" + data_angajari +
                ", salar=" + salar +
                '}';
    }
}