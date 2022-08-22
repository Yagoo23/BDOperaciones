package com.example.bdoperaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminar extends AppCompatActivity {
    private String DBname="DB_Alumnos";
    private SQLiteDatabase dbalumnos;
    private Button btnOk,btnSalir;
    private EditText etCodigo,etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        inicializardatos();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo=etCodigo.getText().toString();
                if(codigo.isEmpty()){
                    Toast.makeText(Eliminar.this, "Debe introducir datos", Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteOpenHelper auxiliar=new SQLiteOpenHelper(Eliminar.this,DBname,null,1);
                    //invoco el método de apertura correspondiente (getReadableDatabase o getWritableDatabase)
                    dbalumnos=auxiliar.getWritableDatabase();
                    dbalumnos.delete("alumnos","codigo="+codigo,null);
                    Toast.makeText(Eliminar.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
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