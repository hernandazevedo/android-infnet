package com.example.calculasalario;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {
	
	private static DecimalFormat df = new DecimalFormat("0.00");

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
	
	
	public void calclularSalario(View view){
		
		EditText edtSenha = (EditText)findViewById(R.id.txtSenha);
		if(edtSenha.getText() == null ||  edtSenha.getText().toString().trim().equals("")){
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Aviso");
			dialogo.setMessage("Digite a senha");
			dialogo.setNeutralButton("OK", null);
			dialogo.show();
			return;
			
		}else if(!isSenhaValida( edtSenha.getText().toString())){
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Aviso");
			dialogo.setMessage("Senha invalida!");
			dialogo.setNeutralButton("OK", null);
			dialogo.show();
			return;
		}
		
		
		double salario, novo_sal;
		EditText edsalario = (EditText)findViewById(R.id.edsalario);
		salario = Double.parseDouble(edsalario.getText().toString());
		RadioGroup rg = (RadioGroup) findViewById(R.id.rgopcoes);
		int op = rg.getCheckedRadioButtonId();
		if(op==R.id.rb40)
		novo_sal = salario + (salario * 0.4);
		else if(op==R.id.rb45)
		novo_sal = salario + (salario * 0.45);
		else
		novo_sal = salario + (salario * 0.5);
		
		//limpar os campos
		edtSenha.setText("");
		edsalario.setText("");
		RadioButton rb40 = (RadioButton)findViewById(R.id.rb40);
		rb40.setChecked(true);
		
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage("Novo salario: "+df.format(novo_sal));
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		
		
	}

	private boolean isSenhaValida(String senha) {

		return "12345".equals(senha);
	}

}
