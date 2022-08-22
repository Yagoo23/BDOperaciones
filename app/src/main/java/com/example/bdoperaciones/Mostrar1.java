package com.example.bdoperaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mostrar1 extends AppCompatActivity {
    private String DBname="DB_Alumnos";
    private SQLiteDatabase dbalumnos;
    private Button btnOk,btnSalir;
    private EditText etCodigo,etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar1);
        inicializardatos();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo=etCodigo.getText().toString();
                if(codigo.isEmpty()){
                    Toast.makeText(Mostrar1.this, "Debe introducir datos", Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteOpenHelper auxiliar=new SQLiteOpenHelper(Mostrar1.this,DBname,null,1);
                    //invoco el método de apertura correspondiente (getReadableDatabase o getWritableDatabase)
                    dbalumnos=auxiliar.getWritableDatabase();
                    Cursor cursor=dbalumnos.rawQuery("SELECT nombre FROM alumnos WHERE codigo="+codigo,null);
                    if(cursor.moveToFirst()){ //Si hay algo en la primera posición del resultado
                        String strNombre=cursor.getString(0); //Posición 0 porque solo recuperamos el nombre
                        Toast.makeText(Mostrar1.this, "Nombre: "+strNombre, Toast.LENGTH_SHORT).show();
                        //Recordar que varios Toast en api 27 se pisan
                    }else{ //Resultado vacío
                        Toast.makeText(Mostrar1.this, "No encontrado", Toast.LENGTH_SHORT).show();
                    }
                    //HAY que cerrar el cursor
                    cursor.close();
                }
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
    public void inicializardatos(){
        btnOk=findViewById(R.id.btnOk);
        btnSalir=findViewById(R.id.btnSalir);
        etCodigo=findViewById(R.id.etCodigo);
    }
    }
