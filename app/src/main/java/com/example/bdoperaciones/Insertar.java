package com.example.bdoperaciones;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends AppCompatActivity {
    private String DBname="DB_Alumnos";
    private SQLiteDatabase dbalumnos;
    private Button btnOk,btnSalir;
    private EditText etCodigo,etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        inicializardatos();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo=etCodigo.getText().toString();
                String nombre=etNombre.getText().toString();
                if(codigo.isEmpty() || nombre.isEmpty()){
                    Toast.makeText(Insertar.this, "Debe introducir datos", Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteOpenHelper auxiliar=new SQLiteOpenHelper(Insertar.this,DBname,null,1);
                    //invoco el método de apertura correspondiente (getReadableDatabase o getWritableDatabase)
                    dbalumnos=auxiliar.getWritableDatabase();
                    ContentValues nuevaTupla=new ContentValues();
                    nuevaTupla.put("codigo",codigo);
                    nuevaTupla.put("nombre",nombre);
                    long l=dbalumnos.insert("alumnos",null,nuevaTupla);
                    if (l!=0){
                        Toast.makeText(Insertar.this, "Inserción OK!", Toast.LENGTH_SHORT).show();
                        etCodigo.setText("");
                        etNombre.setText("");
                    }else{
                        Toast.makeText(Insertar.this, "Inserción... o más bien no.", Toast.LENGTH_SHORT).show();
                    }
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
        etNombre=findViewById(R.id.etNombre);

    }
}