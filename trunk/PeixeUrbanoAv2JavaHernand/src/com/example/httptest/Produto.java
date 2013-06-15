package com.example.httptest;

import java.io.Serializable;

public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int imageID;
	private String imageUrl;
	private String nome;
	private String preco;
	private String precoAntigo;
	private String numOfertaVend;
	private String percentDisconto;
	
	public Produto() {
	}
	
	public Produto(int imageID, String nome, String preco ) {
		this.imageID = imageID;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto(String imageUrl, String nome, String preco ) {
		this.setImageUrl(imageUrl);
		this.nome = nome;
		this.preco = preco;
	}
	
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPrecoAntigo() {
		return precoAntigo;
	}

	public void setPrecoAntigo(String precoAntigo) {
		this.precoAntigo = precoAntigo;
	}

	public String getNumOfertaVend() {
		return numOfertaVend;
	}

	public void setNumOfertaVend(String numOfertaVend) {
		this.numOfertaVend = numOfertaVend;
	}

	public String getPercentDisconto() {
		return percentDisconto;
	}

	public void setPercentDisconto(String percentDisconto) {
		this.percentDisconto = percentDisconto;
	}
	
	

}
