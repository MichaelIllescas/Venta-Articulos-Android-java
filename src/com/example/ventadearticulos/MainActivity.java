package com.example.ventadearticulos;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText name, valor, cant;
	TextView incr, total;
	RadioButton contado, unPago, tresCuotas;
	CheckBox envio;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name= (EditText)findViewById(R.id.editTextNombre);
		cant= (EditText)findViewById(R.id.editTextCantidad);
		valor= (EditText)findViewById(R.id.editTextPrecioVta);
		
		incr= (TextView)findViewById(R.id.textViewDescuento);
		total= (TextView)findViewById(R.id.textViewImporteTotal);
		
		contado= (RadioButton)findViewById(R.id.radioContado);
		unPago= (RadioButton)findViewById(R.id.radioUnPago);
		tresCuotas= (RadioButton)findViewById(R.id.radioTresCtas);
	
		envio=(CheckBox)findViewById(R.id.checkBox1);
		String nombre = name.getText().toString();
		
		String cantidad= cant.getText().toString();
		
		String precio= valor.getText().toString();
		
	}
	
	 public boolean esEntero(String dato) {
	        try {
	            Integer.parseInt(dato);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    
	 public boolean esFloat(String dato) {
	        try {
	            Float.parseFloat(dato);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    
	 
	 public String nombre(View v){
		 String nombre = name.getText().toString();
		 return nombre;
	 } 
	 
	 public String cantidad (View v){
		 String cantidad= cant.getText().toString();
		 return cantidad;
	 } 
	 
	 public String precio (View v){
		 String precio= valor.getText().toString();
		 return precio;
	 } 
	 
	 public String envio(View v){
		 if(envio.isChecked()){
			 return "Si";
		 }else {
			 return "No";
		 }
	 }
	 
	 
	public String calcular(View v){
		
		
		 String nombre = name.getText().toString();
		
		String cantidad= cant.getText().toString();
		
		String precio= valor.getText().toString();

			DecimalFormat f= new DecimalFormat("0.00");
			float importeTotal=0;
			float porcentaje=0;
			float aumento=0;
			
			
		
		if (nombre.equalsIgnoreCase("")|| cantidad.equalsIgnoreCase("") || precio.equalsIgnoreCase(""))
		{
			Toast t= Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_LONG);
			t.show();
		}else
			
			
			if (!esEntero(cantidad) || (!esFloat(precio) ||  !esEntero(precio) )  )
			{
				Toast t= Toast.makeText(this, "Valor Ingresado Invalido", Toast.LENGTH_SHORT);
 					t.show();
 				
			
 			} else
 				{
 				
 				int cantidadProductos= Integer.parseInt(cantidad);
 				float precioUnitario= Float.parseFloat(precio);
 				
		
		
		
			if( contado.isChecked() )
			{
				if(!envio.isChecked() )
				{	
					porcentaje= -precioUnitario*10/100;
					aumento= porcentaje * cantidadProductos;
					incr.setText(String.valueOf(aumento));
					importeTotal=precioUnitario*cantidadProductos+aumento;
					total.setText("$ "+f.format(importeTotal));
				}	else{
					porcentaje= -precioUnitario*10/100;
					aumento= porcentaje * cantidadProductos+100;
					incr.setText(String.valueOf(aumento));
					importeTotal=precioUnitario*cantidadProductos+aumento;
					total.setText("$ "+ f.format(importeTotal));
					
				}

				
			}else if(unPago.isChecked() )
					{
						if(!envio.isChecked())	
						{
							importeTotal=precioUnitario;
							incr.setText("0.00");
							importeTotal=importeTotal*cantidadProductos;
							total.setText("$ "+f.format(importeTotal));

						}else
						{
							importeTotal=precioUnitario;
							incr.setText("+100");
							importeTotal=importeTotal*cantidadProductos +100;
							total.setText("$ "+f.format(importeTotal));
							
						}
					
					
					
					}
			else if(tresCuotas.isChecked() ) 
				{
				if(!envio.isChecked())
						{
							porcentaje= precioUnitario*10/100;
							aumento= porcentaje * cantidadProductos;
							incr.setText("+ "+String.valueOf(aumento) );
							importeTotal= (precioUnitario + porcentaje);
							importeTotal=importeTotal*cantidadProductos;
							total.setText("$ "+f.format(importeTotal));
						}else
						{
							porcentaje= precioUnitario*10/100;
							aumento= porcentaje * cantidadProductos+100;
							incr.setText("+ "+String.valueOf(aumento) );
							importeTotal= (precioUnitario + porcentaje);
							importeTotal=importeTotal*cantidadProductos+100;
							total.setText("$ "+f.format(importeTotal));
						}
					}return String.valueOf(f.format(importeTotal));
			
 			}
		return String.valueOf(f.format(importeTotal));
	}
	
	
	public void factura (View v){
		Intent i= new Intent(this, Facturas.class);
		
		i.putExtra("namePersona", nombre(v));
		i.putExtra("cantidad", cantidad(v));
		i.putExtra("precio", precio(v));
		i.putExtra("envio", envio(v));
		i.putExtra("importeTotal", calcular(v));
		
		
		
		
		startActivity(i);
		
		
		
	}
		
		
}
	
	
	



