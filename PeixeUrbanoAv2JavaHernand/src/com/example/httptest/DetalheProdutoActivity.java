package com.example.httptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DetalheProdutoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe_produto);
		
		 Produto p = (Produto) getIntent().getExtras().get("produto");
	        
	        ImageView image = (ImageView) findViewById(R.id.detalheImagemProduto);
	        TextView txNomeProduto = (TextView) findViewById(R.id.detalheNomeProduto);
	        TextView txPrecoProduto = (TextView) findViewById(R.id.detalhePrecoProduto);
	        TextView txPrecoAntigo = (TextView) findViewById(R.id.detalhePrecoAntigo);
	        TextView txNumSold = (TextView) findViewById(R.id.detalheNumSold);
	        TextView txPerncentDisc = (TextView) findViewById(R.id.detalhePerncentDisc);
	        
	        
	        
	        txNomeProduto.setText(p.getNome());
	        txPrecoProduto.setText(p.getPreco());
	        txPrecoAntigo.setText(p.getPrecoAntigo());
	        txNumSold.setText(p.getNumOfertaVend());
	        txPerncentDisc.setText(p.getPercentDisconto());
	        image.setImageResource(p.getImageID());
			UrlImageViewHelper.setUrlDrawable(image, p.getImageUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhe_produto, menu);
		return true;
	}

}
