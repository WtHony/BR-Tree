package com.arvores.controller;

import com.arvores.model.Arvore;
import com.arvores.model.No;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public class BRT<T extends Comparable<T>> implements Arvore<T> {

    private No<T> raiz;

    @Override
    public Arvore<T> inserir(T dados) {
        No<T> no = new No<>(dados);
        raiz = inserir(raiz, no);
        reCorRotacao(no);
        return this;
    }

    private No<T> inserir(No<T> no, No<T> noInserido) {
        if (no == null) {
            return noInserido;
        }
        if (noInserido.getDados().compareTo(no.getDados()) < 0) {
            no.setEsquerda(inserir(no.getEsquerda(), noInserido));
            no.getEsquerda().setParametro(no);
        } else if (noInserido.getDados().compareTo(no.getDados()) > 0) {
            no.setDireita(inserir(no.getDireita(), noInserido));
            no.getDireita().setParametro(no);
        }
        return no;
    }

    private void reCorRotacao(No<T> no) {
        No<T> parametro = no.getParametro();
        if (no != raiz && parametro.getCor() == RED) {
            No<T> avo = no.getParametro().getParametro();
            No<T> tio = parametro.iSesquerda() ? avo.getDireita() : avo.getEsquerda();
            if (tio != null && tio.getCor() == RED) {
                recoloracao(parametro, tio, avo);
            } else if (parametro.iSesquerda()) {
                situacaoEsquerda(no, parametro, avo);
            } else if (!parametro.iSesquerda()) {
                situacaoDireita(no, parametro, avo);
            }
        }
        raiz.setCor(BLACK);
    }

    private void recoloracao(No<T> parametro, No<T> tio, No<T> avo) {
        tio.trocarCor();
        parametro.trocarCor();
        avo.trocarCor();
        reCorRotacao(avo);
    }

    private void situacaoDireita(No<T> no, No<T> parametro, No<T> avo) {
        if (no.iSesquerda()) {
            giroDireita(parametro);
        }
        parametro.trocarCor();
        avo.trocarCor();
        giroEsquerda(avo);
        reCorRotacao(no.iSesquerda() ? avo : parametro);
    }

    private void situacaoEsquerda(No<T> no, No<T> parametro, No<T> avo) {
        if (!no.iSesquerda()) {
            giroEsquerda(parametro);
        }
        parametro.trocarCor();
        avo.trocarCor();
        giroDireita(avo);
        reCorRotacao(no.iSesquerda() ? parametro : avo);
    }

    private void giroDireita(No<T> no) {
        No<T> noEsquerda = no.getEsquerda();
        no.setEsquerda(noEsquerda.getDireita());
        if (no.getEsquerda() != null) {
            no.getEsquerda().setParametro(no);
        }
        noEsquerda.setDireita(no);
        noEsquerda.setParametro(no.getParametro());
        atualizarFilhosNoPai(no, noEsquerda);
        no.setParametro(noEsquerda);
    }

    private void giroEsquerda(No<T> no) {
        No<T> noDireita = no.getDireita();
        no.setDireita(noDireita.getEsquerda());
        if (no.getDireita() != null) {
            no.getDireita().setParametro(no);
        }
        noDireita.setEsquerda(no);
        noDireita.setParametro(no.getParametro());
        atualizarFilhosNoPai(no, noDireita);
        no.setParametro(noDireita);

    }

    private void atualizarFilhosNoPai(No<T> no, No<T> auxNo) {
        if (no.getParametro() == null) {
            raiz = auxNo;
        } else if (no.iSesquerda()) {
            no.getParametro().setEsquerda(auxNo);
        } else {
            no.getParametro().setDireita(auxNo);
        }
    }

    @Override
    public void percorrer() {
        PreOrdem(raiz);
    }

    private void PreOrdem(No<T> no) {
        if (no != null) {
            System.out.println(no);
            PreOrdem(no.getEsquerda());
            PreOrdem(no.getDireita());
        }
    }

    @Override
    public T noMax() {
        if (isVazio()) {
            return null;
        }
        return noMax(raiz);
    }

    private T noMax(No<T> no) {
        if (no.getDireita() != null) {
            return noMax(no.getDireita());
        }
        return no.getDados();
    }

    @Override
    public T noMin() {
        if (isVazio()) {
            return null;
        }
        return noMin(raiz);
    }

    private T noMin(No<T> no) {
        if (no.getEsquerda() != null) {
            return noMin(no.getEsquerda());
        }
        return no.getDados();
    }

    @Override
    public boolean isVazio() {
        return raiz == null;
    }

}
