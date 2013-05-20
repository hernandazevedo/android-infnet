package com.example.sistemacompras;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {

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
	
	
	public void calcularTotal(View view){
		
		CheckBox chkarroz = (CheckBox) findViewById(R.id.chkArroz);
		CheckBox chkleite = (CheckBox) findViewById(R.id.chkLeite);
		CheckBox chkcarne = (CheckBox) findViewById(R.id.chkCarne);
		CheckBox chkfeijao = (CheckBox) findViewById(R.id.chkFeijao);
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		double total =0;
		if(chkarroz.isChecked())
		total += 2.69;
		if(chkleite.isChecked())
		total += 5.00;
		if(chkcarne.isChecked())
		total += 9.7;
		if(chkfeijao.isChecked())
		total += 2.30;

		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage("Valor total da compra:" + df.format(total));
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		
	}

}
