package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Turmas extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView turmasNomeList, universidadeList, campusList;

    private int id;
    private String turmaNome, turmaUniversidade, turmaCampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turmas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        turmasNomeList = (ListView) findViewById(R.id.turmasNomeList);
        universidadeList = (ListView) findViewById(R.id.universidadeList);
        campusList = (ListView) findViewById(R.id.campusList);

        ArrayAdapter<CharSequence> nameAdapter = ArrayAdapter.createFromResource
                (this, R.array.turmasNomeList, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> universidadeAdapter = ArrayAdapter.createFromResource
                (this, R.array.universidadeList, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> campusAdapter = ArrayAdapter.createFromResource
                (this, R.array.campusList, android.R.layout.simple_list_item_1);

        turmasNomeList.setAdapter(nameAdapter);
        universidadeList.setAdapter(universidadeAdapter);
        campusList.setAdapter(campusAdapter);

        turmasNomeList.setOnItemClickListener(this);
        universidadeList.setOnItemClickListener(this);
        campusList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        String item = (String) turmasNomeList.getAdapter().getItem(position);
        Toast.makeText(this, "Nome selecionado: " + item, Toast.LENGTH_SHORT).show();

        String item2 = (String) universidadeList.getAdapter().getItem(position);
        Toast.makeText(this, "Universidade selecionada: " + item2, Toast.LENGTH_SHORT).show();

        String item3 = (String) campusList.getAdapter().getItem(position);
        Toast.makeText(this, "Campus selecionado: " + item3, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Intent registrarTurma = new Intent(this, RegistrarTurma.class);
        startActivity(registrarTurma);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurmaNome() {
        return turmaNome;
    }

    public void setTurmaNome(String turmaNome) {
        this.turmaNome = turmaNome;
    }

    public String getTurmaUniversidade() {
        return turmaUniversidade;
    }

    public void setTurmaUniversidade(String turmaUniversidade) {
        this.turmaUniversidade = turmaUniversidade;
    }

    public String getTurmaCampus() {
        return turmaCampus;
    }

    public void setTurmaCampus(String turmaCampus) {
        this.turmaCampus = turmaCampus;
    }
}
