package com.example.ventadearticulos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Facturas extends Activity {

	TextView t1,t2,t3,t4,t5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facturas);
		
		t1= (TextView)findViewById(R.id.textView3NameFactura);
		t2= (TextView)findViewById(R.id.textViewantidadFactur);
		t3= (TextView)findViewById(R.id.textViewPrecioVtafactura);
		t4= (TextView)findViewById(R.id.textViewEnvio);
		t5= (TextView)findViewById(R.id.textViewTotalImporte);	
		
		Bundle b= getIntent().getExtras();
		String nombre= b.getString("namePersona");
		String cantidad= b.getString("cantidad");
		String precio= b.getString("precio");
		String envio= b.getString("envio");
		String importeTotal= b.getString("importeTotal");
		
		t1.setText(nombre);
		t2.setText(cantidad);
		t3.setText(precio);
		t4.setText(envio);
		t5.setText(importeTotal);
	}
	
	
	
	
	
	
	
	
	
	
	public void salir(View v)
	{
		finish();
		
	}

}
