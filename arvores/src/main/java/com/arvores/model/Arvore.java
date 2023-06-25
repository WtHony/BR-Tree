package com.arvores.model;

public interface Arvore<T extends Comparable<T>> {

    Arvore<T> inserir(T dados);

    void percorrer();

    T noMax();

    T noMin();

    boolean isVazio();
}
