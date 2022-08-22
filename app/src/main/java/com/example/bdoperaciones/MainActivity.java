package com.example.bdoperaciones;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int CODIGO_LLAMADA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        Intent i =new Intent();
        switch (view.getId()){
            case R.id.btnInsertar:
                i.setClass(this,Insertar.class);
                break;
            case R.id.btnModificar:
                i.setClass(this,Modificar.class);
                break;
            case R.id.btnEliminar:
                i.setClass(this,Eliminar.class);
                break;
            case R.id.btnMostrar1:
                i.setClass(this,Mostrar1.class);
                break;
            case R.id.btnMostrarVarios:
                i.setClass(this,MostrarVarios.class);
                break;
            case R.id.btnMostrarLista:
                i.setClass(this,MostrarLista.class);
                break;
        }
        startActivityForResult(i,CODIGO_LLAMADA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODIGO_LLAMADA) {
            if (resultCode == RESULT_OK) {
            }
        }
    }
}