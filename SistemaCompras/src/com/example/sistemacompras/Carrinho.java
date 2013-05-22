package com.example.sistemacompras;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private static Carrinho instance = null;
	private static DecimalFormat df = new DecimalFormat("0.00");
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
		
		if(produtos.contains(p)){
			for(Produto prod : produtos){
				if(prod.equals(p)){
					prod.setQuantidade(prod.getQuantidade() + 1);
					prod.setValor(prod.getValor() + p.getValor());
				}
			}
		}else{
			produtos.add(p);
		}
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		double total =0;
		for(Produto p : produtos){
			total += p.getValor();
			if(sb.length() != 0 ) sb.append("\n");
			
			sb.append("Produto: ").append(p.getNome())
			.append(" Quantidade: ").append(p.getQuantidade())
			.append(" Valor: ").append(df.format(p.getValor()));
		}
		
		if(sb.length() != 0 ) sb.append("\n");
		
		sb.append("Total da compra: "+df.format(total));
		
		return sb.toString();
	}
}
