package com.manitsche.exerciciocompromissos.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.manitsche.exerciciocompromissos.R;
import com.manitsche.exerciciocompromissos.modelo.Compromisso;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "COMPROMISSOS_PREFS";
    private static final String COMPROMISSOS_KEY = "COMPROMISSOS";
    ListView listViewCompromissos;
    Button botaoAdicionarCompromissos;
    private ArrayList<Compromisso> compromissos;
    private ArrayAdapter<Compromisso> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewCompromissos = findViewById(R.id.listViewCompromissos);
        botaoAdicionarCompromissos = findViewById(R.id.botaoAdicionarCompromisso);

        compromissos = new ArrayList<>();
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Carregar compromissos salvos
        loadCompromissos();

        // Configurar o adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, compromissos);
        listViewCompromissos.setAdapter(adapter);

        // Adicionar novo compromisso
        botaoAdicionarCompromissos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Cadastro.class);
            startActivityForResult(intent, 1);
        });

        // Exibir detalhes ao clicar em um item da lista
        listViewCompromissos.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, Detalhes.class);
            intent.putExtra("compromisso", compromissos.get(position));
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Adicionar o novo compromisso à lista
            Compromisso compromisso = (Compromisso) data.getSerializableExtra("compromisso");
            compromissos.add(compromisso);
            adapter.notifyDataSetChanged();
            saveCompromissos();
        }
    }

    private void saveCompromissos() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> compromissoSet = new HashSet<>();

        for (Compromisso compromisso : compromissos) {
            String compromissoString = compromisso.getTitulo() + "," + compromisso.getData() + "," + compromisso.getHora() +
                    "," + compromisso.getLocal() + "," + compromisso.getDiaSemana() + "," + compromisso.getDescricao();
            compromissoSet.add(compromissoString);
        }

        editor.putStringSet(COMPROMISSOS_KEY, compromissoSet);
        editor.apply();
    }

    private void loadCompromissos() {
        Set<String> compromissoSet = sharedPreferences.getStringSet(COMPROMISSOS_KEY, new HashSet<>());

        for (String compromissoString : compromissoSet) {
            String[] parts = compromissoString.split(",");
            Compromisso compromisso = new Compromisso(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            compromissos.add(compromisso);
        }
    }
}