package com.manitsche.exerciciocompromissos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.manitsche.exerciciocompromissos.R;
import com.manitsche.exerciciocompromissos.modelo.Compromisso;

public class Cadastro extends AppCompatActivity {

    EditText edtTitulo, edtData, edtHora, edtLocal, edtDiaSemana, edtDescricao;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtTitulo = findViewById(R.id.edt_titulo);
        edtData = findViewById(R.id.edt_data);
        edtHora = findViewById(R.id.edt_hora);
        edtLocal = findViewById(R.id.edt_local);
        edtDiaSemana = findViewById(R.id.edt_dia_semana);
        edtDescricao = findViewById(R.id.edt_descricao);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v -> {
            String titulo = edtTitulo.getText().toString();
            String data = edtData.getText().toString();
            String hora = edtHora.getText().toString();
            String local = edtLocal.getText().toString();
            String diaSemana = edtDiaSemana.getText().toString();
            String descricao = edtDescricao.getText().toString();

            Compromisso compromisso = new Compromisso(titulo, data, hora, local, diaSemana, descricao);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("compromisso", compromisso);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}