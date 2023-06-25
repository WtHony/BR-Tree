package com.arvores.model;

import java.awt.Color;
import static java.awt.Color.RED;
import static java.awt.Color.BLACK;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
public class No<T extends Comparable<T>> {

    @NonNull
    private T dados;

    private Color cor = RED;

    private No<T> esquerda;
    private No<T> direita;

    @ToString.Exclude
    private No<T> parametro;

    public boolean iSesquerda() {
        return this == parametro.getEsquerda();
    }

    public void trocarCor() {
        setCor(cor == RED ? BLACK : RED);
    }

}
