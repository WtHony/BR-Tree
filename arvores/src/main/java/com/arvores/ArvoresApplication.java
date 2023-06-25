package com.arvores;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arvores.controller.BRT;
import com.arvores.model.Arvore;

@SpringBootApplication
public class ArvoresApplication {

	public static void main(String[] args) {
		Arvore<Integer> brt = new BRT<>();

		brt.inserir(10).inserir(20).inserir(5).inserir(40).inserir(50)
				.inserir(25).inserir(60).inserir(80).inserir(85).inserir(90)
				.inserir(30).inserir(15).inserir(100).inserir(55).inserir(45)
				.inserir(0).inserir(26);

		brt.percorrer();
		System.out.println("maior no: " + brt.noMax());
		System.out.println("menor no: " + brt.noMin());
	}

}
