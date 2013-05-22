package com.example.calculasalariospinner;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemSelectedListener {

	private static DecimalFormat df = new DecimalFormat("0.00");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Spinner spinnerFuncionario = (Spinner)findViewById(R.id.spinnerFuncionario);
		
		spinnerFuncionario.setOnItemSelectedListener(this);
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
			int pos, long id) {
		
		Spinner comboOpcoes = (Spinner)findViewById(R.id.spnopcoes);
		TextView lblSpnOpcoes = (TextView)findViewById(R.id.lblSpnOpcoes);
		
		switch (pos) {
		case 1:
			atualizaItensCombo(comboOpcoes,R.array.itens_funcionario);
			comboOpcoes.setVisibility(Spinner.VISIBLE);
			lblSpnOpcoes.setVisibility(TextView.VISIBLE);
			break;
			
		case 2:
			atualizaItensCombo(comboOpcoes,R.array.itens_gerente);
			comboOpcoes.setVisibility(Spinner.VISIBLE);
			lblSpnOpcoes.setVisibility(TextView.VISIBLE);
			break;
			
		case 3:
			atualizaItensCombo(comboOpcoes,R.array.itens_diretor);
			comboOpcoes.setVisibility(Spinner.VISIBLE);
			lblSpnOpcoes.setVisibility(TextView.VISIBLE);
			break;

		default:
			Spinner spnOpcoes = comboOpcoes;
			spnOpcoes.setVisibility(Spinner.INVISIBLE);
			lblSpnOpcoes.setVisibility(TextView.INVISIBLE);
			spnOpcoes.setSelection(0);
			break;
		}
		
	}

	
	

	private void atualizaItensCombo(Spinner spinner, int arrayItensCombo) {
		String[] valores = getResources().getStringArray(arrayItensCombo);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,valores);
		spinner.setAdapter(arrayAdapter);
		
	}




	@Override
	public void onNothingSelected(AdapterView<?> parent) {

		
	}
	
	
	public void calclularSalario(View view){
		
		
		double salario, novo_sal;
		EditText edsalario = (EditText)findViewById(R.id.edsalario);
		
		if(edsalario.getText() == null ||  edsalario.getText().toString().trim().equals("")){
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Aviso");
			dialogo.setMessage("Digite o valor do salario");
			dialogo.setNeutralButton("OK", null);
			dialogo.show();
			return;
			
		}
		
		salario = Double.parseDouble(edsalario.getText().toString());
		
		Spinner spnopcoes = (Spinner)findViewById(R.id.spnopcoes);
		Spinner spinnerFuncionario = (Spinner)findViewById(R.id.spinnerFuncionario);
		
		if(spinnerFuncionario.getSelectedItemPosition() == 0){
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Aviso");
			dialogo.setMessage("Selecione um cargo");
			dialogo.setNeutralButton("OK", null);
			dialogo.show();
			return;
		}
		
		double porcentagem = retornaPocentagem(spnopcoes.getSelectedItem());
		
		novo_sal = salario + (salario * porcentagem);
		
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage("Novo salario: "+df.format(novo_sal));
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		
		
	}





	private double retornaPocentagem(Object selectedItem) {

		return Double.valueOf(selectedItem.toString().replaceAll("%", "")) / 100;
	}
}
