package br.edu.anhembi.gabaritando;

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

public class Turmas extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView nameList, universidadeList, campusList;

    private int id, ra;
    private String turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turmas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Em manutencao", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        nameList = (ListView) findViewById(R.id.nameList);
        universidadeList = (ListView) findViewById(R.id.raList);
        campusList = (ListView) findViewById(R.id.turmaList);

        ArrayAdapter<CharSequence> nameAdapter = ArrayAdapter.createFromResource
                (this, R.array.nameList, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> raAdapter = ArrayAdapter.createFromResource
                (this, R.array.raList, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> turmaAdapter = ArrayAdapter.createFromResource
                (this, R.array.turmaList, android.R.layout.simple_list_item_1);

        nameList.setAdapter(nameAdapter);
        universidadeList.setAdapter(raAdapter);
        campusList.setAdapter(turmaAdapter);

        nameList.setOnItemClickListener(this);
        universidadeList.setOnItemClickListener(this);
        campusList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        String item = (String) nameList.getAdapter().getItem(position);
        Toast.makeText(this, "Nome selecionado: " + item, Toast.LENGTH_SHORT).show();

        String item2 = (String) universidadeList.getAdapter().getItem(position);
        Toast.makeText(this, "Universidade selecionada: " + item2, Toast.LENGTH_SHORT).show();

        String item3 = (String) campusList.getAdapter().getItem(position);
        Toast.makeText(this, "Campus selecionado: " + item3, Toast.LENGTH_SHORT).show();
    }

}
