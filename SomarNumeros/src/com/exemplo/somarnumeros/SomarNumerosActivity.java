package com.exemplo.somarnumeros;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SomarNumerosActivity extends Activity {
	private String descricaoOperacao;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void somar(View view) {

		EditText ednumero1 = (EditText) findViewById(R.id.numero1);
		EditText ednumero2 = (EditText) findViewById(R.id.numero2);

		double num1 = Double.parseDouble(ednumero1.getText().toString());
		double num2 = Double.parseDouble(ednumero2.getText().toString());

		Spinner spinner_op = (Spinner) findViewById(R.id.spinner_op);
		double res = doOperation(num1, num2, spinner_op.getSelectedItem()
				.toString());

		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle(descricaoOperacao);
		dialogo.setMessage("Resultado:" + res);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();

	}

	public double doOperation(double arg1, double arg2, String operador) {
		if (operador != null) {
			double res = 0.0;
			switch (operador.charAt(0)) {
			case '+':
				descricaoOperacao = "Soma";
				res = arg1 + arg2;
				break;
			case '-':
				descricaoOperacao = "Diferença";
				res = arg1 - arg2;
				break;

			case '/':
				descricaoOperacao = "Divisão";
				res = arg1 / arg2;
				break;

			case '*':
				descricaoOperacao = "Multiplicação";
				res = arg1 * arg2;
				break;

			default:
				break;
			}

			return res;
		} else {
			return 0.0;
		}
	}

}