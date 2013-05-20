package com.example.sistemacompras;

public class Carrinho {

	private static Carrinho instance = null;
	
	private Carrinho() {
	}

	
	public static Carrinho getInstance() {
		if(instance == null){
			instance = new Carrinho();
		}
		return instance;
	}
	
	public void adicionar(Produto p){
		
	}
	
	
	@Override
	public String toString() {
	
		
		return super.toString();
	}
}
