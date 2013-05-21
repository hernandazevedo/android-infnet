package com.example.sistemacompras;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private static Carrinho instance = null; 
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	private Carrinho() {
	}

	
	public static Carrinho getInstance() {
		if(instance == null){
			instance = new Carrinho();
		}
		return instance;
	}
	
	public void adicionar(Produto p){
		produtos.add(p);
	}
	
	
	@Override
	public String toString() {
	
		
		return super.toString();
	}
}
