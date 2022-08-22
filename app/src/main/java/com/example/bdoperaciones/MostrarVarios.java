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

public class MostrarVarios extends AppCompatActivity {
    private String DBname="DB_Alumnos";
    private SQLiteDatabase dbalumnos;
    private Button btnOk,btnSalir;
    private TextView tvAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_varios);
        inicializardatos();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SQLiteOpenHelper auxiliar=new SQLiteOpenHelper(MostrarVarios.this,DBname,null,1);
                    //invoco el método de apertura correspondiente (getReadableDatabase o getWritableDatabase)
                    dbalumnos=auxiliar.getWritableDatabase();
                    String[] datosARecuperar={"codigo", "nombre"};
                    Cursor c = dbalumnos.query("alumnos", datosARecuperar, null,null,null,null,null,null);
                    if (c.moveToFirst()) { //significa que se ha recuperado algo en la consulta
                        //recorremos el cursor hasta que no haya más registros
                        do {
                            int codigo = c.getInt(0);
                            String nombre = c.getString(1);
                            tvAlumnos.append("Nombre: "+nombre+". Código: "+codigo+"\n");
                        }while (c.moveToNext());
                    }
                    else
                        Toast.makeText(MostrarVarios.this, "No hay alumnos en la base de datos.", Toast.LENGTH_LONG).show();
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
        tvAlumnos=findViewById(R.id.tvAlumnos);
    }
}