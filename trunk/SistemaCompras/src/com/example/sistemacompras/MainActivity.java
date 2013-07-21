package com.example.sistemacompras;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.json.JSONException;

import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {
	
	private static Carrinho carrinho = Carrinho.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		 Intent intent = new Intent(this, PayPalService.class);

	     // live: don't put any environment extra
	     // sandbox: use PaymentActivity.ENVIRONMENT_SANDBOX
	     intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, PaymentActivity.ENVIRONMENT_SANDBOX);

	     intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, "APP-80W284485P519543T");//Sandbox

	     startService(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void verCarrinho(View view){
		
//		
//		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
//		dialogo.setTitle("Aviso");
//		dialogo.setMessage("Compras no Carrinho:\n" + carrinho.toString());
//		dialogo.setNeutralButton("OK", null);
//		dialogo.show();
		
		
		 PayPalPayment payment = new PayPalPayment(new BigDecimal("8.75"), "USD", "hipster jeans");

	     Intent intent = new Intent(this, PaymentActivity.class);

	     // comment this line out for live or set to PaymentActivity.ENVIRONMENT_SANDBOX for sandbox
	     intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, PaymentActivity.ENVIRONMENT_SANDBOX);

	     // it's important to repeat the clientId here so that the SDK has it if Android restarts your
	     // app midway through the payment UI flow.
	     intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, "APP-80W284485P519543T");

	     // Provide a payerId that uniquely identifies a user within the scope of your system,
	     // such as an email address or user ID.
	     intent.putExtra(PaymentActivity.EXTRA_PAYER_ID, "hernand.azevedo@gmail.com");

	     intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL, "hernand.azevedo@gmail.com");
	     intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

	     startActivityForResult(intent, 0);
		
	}
	
	@Override
	 protected void onActivityResult (int requestCode, int resultCode, Intent data) {
	     if (resultCode == Activity.RESULT_OK) {
	         PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
	         if (confirm != null) {
	             try {
	                 Log.i("paymentExample", confirm.toJSONObject().toString(4));

	                 // TODO: send 'confirm' to your server for verification.
	                 // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
	                 // for more details.

	             } catch (JSONException e) {
	                 Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
	             }
	         }
	     }
	     else if (resultCode == Activity.RESULT_CANCELED) {
	         Log.i("paymentExample", "The user canceled.");
	     }
	     else if (resultCode == PaymentActivity.RESULT_PAYMENT_INVALID) {
	         Log.i("paymentExample", "An invalid payment was submitted. Please see the docs.");
	     }
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
	
	 @Override
	 public void onDestroy() {
	     stopService(new Intent(this, PayPalService.class));
	     super.onDestroy();
	 }

}
