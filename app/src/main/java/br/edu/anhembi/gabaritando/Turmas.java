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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.anhembi.gabaritando.CRUD.Read;
import br.edu.anhembi.gabaritando.CRUD.Update;

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
        ArrayList<CharSequence> turmas = new ArrayList();
        ArrayList<CharSequence> universidades = new ArrayList();
        ArrayList<CharSequence> campus = new ArrayList();



        ArrayAdapter<CharSequence> nameAdapter = new ArrayAdapter<CharSequence>
                (this, android.R.layout.simple_list_item_1, turmas );

        ArrayAdapter<CharSequence> universidadeAdapter = new ArrayAdapter<CharSequence>
                (this, android.R.layout.simple_list_item_1, universidades );

        ArrayAdapter<CharSequence> campusAdapter = new ArrayAdapter<CharSequence>
                (this, android.R.layout.simple_list_item_1, campus );


        turmasNomeList.setAdapter(nameAdapter);
        universidadeList.setAdapter(universidadeAdapter);
        campusList.setAdapter(campusAdapter);

        turmasNomeList.setOnItemClickListener(this);
        universidadeList.setOnItemClickListener(this);
        campusList.setOnItemClickListener(this);

        Read r = new Read(getApplicationContext());
        r.selectNomeTurmas(turmas, universidades, campus);




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
