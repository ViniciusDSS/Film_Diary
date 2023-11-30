package com.example.af_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.af_mobile.R;

public class VerSeriesActivity extends AppCompatActivity {

    EditText campoTitulo, campoPlataforma, campoDiaSemana, campoEpAssistido, campoAssistir, campoTemporada;

    Button btRemover, btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verserie);
    }

    private void init(){
        campoTitulo = findViewById(R.id.textTitulo);
        campoAssistir = findViewById(R.id.textEpisodio);

    }
}