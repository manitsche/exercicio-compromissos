package com.manitsche.exerciciocompromissos.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.manitsche.exerciciocompromissos.R;
import com.manitsche.exerciciocompromissos.modelo.Compromisso;

public class Detalhes extends AppCompatActivity {

    TextView txtDetalhes;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtDetalhes = findViewById(R.id.txt_detalhes);
        btnVoltar = findViewById(R.id.btn_voltar);

        Compromisso compromisso = (Compromisso) getIntent().getSerializableExtra("compromisso");

        if (compromisso != null) {
            String detalhes = "Título: " + compromisso.getTitulo() +
                    "\nData: " + compromisso.getData() +
                    "\nHora: " + compromisso.getHora() +
                    "\nLocal: " + compromisso.getLocal() +
                    "\nDia da Semana: " + compromisso.getDiaSemana() +
                    "\nDescrição: " + compromisso.getDescricao();
            txtDetalhes.setText(detalhes);
        }

        btnVoltar.setOnClickListener(v -> finish());
    }
}