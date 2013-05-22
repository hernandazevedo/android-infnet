package com.example.sistemacompras;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {
	
	private static Carrinho carrinho = Carrinho.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void verCarrinho(View view){
		
		
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage("Compras no Carrinho:\n" + carrinho.toString());
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		
	}
	
	public void adicionarProdutoCarrinho(View view){
		CheckBox chkarroz = (CheckBox) findViewById(R.id.chkArroz);
		CheckBox chkleite = (CheckBox) findViewById(R.id.chkLeite);
		CheckBox chkcarne = (CheckBox) findViewById(R.id.chkCarne);
		CheckBox chkfeijao = (CheckBox) findViewById(R.id.chkFeijao);
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		
		
		double total =0;
		if(chkarroz.isChecked()){
			Produto p = new Produto("Arroz",1,2.69);
			carrinho.adicionar(p);
			total += 2.69;
		}
		if(chkleite.isChecked()){
			Produto p = new Produto("Leite",1,5.00);
			carrinho.adicionar(p);
			total += 5.00;
		}
		if(chkcarne.isChecked()){
			Produto p = new Produto("Carne",1,9.7);
			carrinho.adicionar(p);
			total += 9.7;
		}
		if(chkfeijao.isChecked()){
			Produto p = new Produto("Feijão",1,2.30);
			carrinho.adicionar(p);
			total += 2.30;
		}

		String msg = null;
		if(total > 0){
			msg = "Compra adicionada:\nValor total:" + df.format(total);
		}else{
			msg = "Por favor selecione um produto.";
		}
		
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage(msg);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}

}
