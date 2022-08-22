package com.example.bdoperaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarLista extends AppCompatActivity {
    private ArrayList<String> arrayListTexto;
    private String DBname="DB_Alumnos";
    private SQLiteDatabase dbalumnos;
    private Button btnOk,btnSalir;
    private ListView lvAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista);
        inicializardatos();
        arrayListTexto = new ArrayList<String>();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListTexto);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteOpenHelper auxiliar=new SQLiteOpenHelper(MostrarLista.this,DBname,null,1);
                //invoco el método de apertura correspondiente (getReadableDatabase o getWritableDatabase)
                dbalumnos=auxiliar.getWritableDatabase();
                String[] datosARecuperar={"codigo", "nombre"};
                Cursor c = dbalumnos.query("alumnos", datosARecuperar, null,null,null,null,null,null);
                if (c.moveToFirst()) { //significa que se ha recuperado algo en la consulta
                    //recorremos el cursor hasta que no haya más registros
                    do {
                        int codigo = c.getInt(0);
                        String nombre = c.getString(1);
                        arrayListTexto.add(codigo+" --> "+nombre);
                        lvAlumnos.setAdapter(adaptador);
                    }while (c.moveToNext());
                }
                else
                    Toast.makeText(MostrarLista.this, "No hay alumnos en la base de datos.", Toast.LENGTH_LONG).show();
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
        lvAlumnos=findViewById(R.id.lvAlumnos);
    }
}