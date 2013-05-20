package com.exemplo.somarnumeros;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SomarNumerosActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        Button btsomar = (Button) findViewById(R.id.btsomar);
//        btsomar.setOnClickListener(new View.OnClickListener(){
//        	public void onClick(View arg0) {
//        		EditText ednumero1 = (EditText) findViewById(R.id.numero1);
//                EditText ednumero2 = (EditText) findViewById(R.id.numero2);
//                
//        		double num1 = Double.parseDouble(
//        		ednumero1.getText().toString());
//        		double num2 = Double.parseDouble(
//        		ednumero2.getText().toString());
//        		double res = num1 + num2;
//        		}
//        		});
       
    }
    
    public void somar(View view){
    	
    	EditText ednumero1 = (EditText) findViewById(R.id.numero1);
    	EditText ednumero2 = (EditText) findViewById(R.id.numero2);
      
		double num1 = Double.parseDouble(
		ednumero1.getText().toString());
		double num2 = Double.parseDouble(
		ednumero2.getText().toString());
		double res = num1 + num2;
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage("Soma:" + res);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		
	
    }
}