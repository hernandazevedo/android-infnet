package com.example.httptest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends Activity implements OnItemClickListener{

	List<Produto> produtos;
	ListView listView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = (ListView) findViewById(R.id.listView_produtos);
        
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://infnet.aws.af.cm/ofertas.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
//              TextView tx = (TextView) findViewById(R.id.textViewHello);
              try {
            	  produtos = parseJsonToListProduto(new String(response.getBytes(),"UTF-8"));
            	  listView.setAdapter(new ProdutoAdapter());
            	  
//				tx.setText(new String(response.getBytes(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
            private List<Produto> parseJsonToListProduto(String string) {
            	JSONObject obj = null;
            	List<Produto> produtos = new ArrayList<Produto>();
            	try {
        			obj = new JSONObject(string);
        			JSONObject objResult = obj.getJSONObject("Result");
        			JSONArray discountListArray = objResult.getJSONArray("DiscountList");
        			for(int i=0;i<discountListArray.length();i++){
        				String imageUrl = discountListArray.getJSONObject(i).getString("SmallImageUrlOrDefault");
        				String preco = discountListArray.getJSONObject(i).getString("DiscountedValue");
        				String nome = discountListArray.getJSONObject(i).getString("MobileName");
        				String precoAntigo = discountListArray.getJSONObject(i).getString("OriginalValue");
        				String numOfertaVend = discountListArray.getJSONObject(i).getString("NumSold");
        				String percentDisconto = discountListArray.getJSONObject(i).getString("PercentageDiscount");
        				
        				Produto p = new Produto();
        				p.setImageUrl(imageUrl);
        				p.setPreco(preco);
        				p.setNome(nome);
        				p.setPrecoAntigo(precoAntigo);
        				p.setNumOfertaVend(numOfertaVend);
        				p.setPercentDisconto(percentDisconto);
        				
        				produtos.add(p);
        			}
        			
        		} catch (JSONException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            	
            	return produtos;
        	}
			
        });
        
        listView.setOnItemClickListener(this);
    }
    
    
    class ProdutoAdapter extends BaseAdapter{

		public int getCount() {
			return produtos.size();
		}

		public Produto getItem(int position) {
			return produtos.get(position);
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = convertView;
			if(view == null){
				view = getLayoutInflater().inflate(R.layout.linha_produto, null);
			}
			
			ImageView image = (ImageView) view.findViewById(R.id.imageView1);
			TextView txNome = (TextView) view.findViewById(R.id.textView1);
			TextView txPreco = (TextView) view.findViewById(R.id.textView2);
			
			Produto p = getItem(position);
			
			image.setImageResource(p.getImageID());
			UrlImageViewHelper.setUrlDrawable(image, p.getImageUrl());
			
			txNome.setText(p.getNome());
			txPreco.setText(p.getPreco());
			
			return view;
		}
    	
    }


	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Produto p = produtos.get(position);
		
		Intent it = new Intent(this, DetalheProdutoActivity.class);
		it.putExtra("produto", p);
		startActivity(it);
		
	}

    
}
