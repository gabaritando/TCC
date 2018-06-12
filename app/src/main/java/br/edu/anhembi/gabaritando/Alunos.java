package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.edu.anhembi.gabaritando.CRUD.Read;

public class Alunos extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView alunosNomeList, alunosRaList, alunosTurmaList;

    private int id, ra;
    private String nome, turma;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        alunosNomeList = (ListView) findViewById(R.id.alunosNomeList);
        alunosRaList = (ListView) findViewById(R.id.alunosRaList);
        alunosTurmaList = (ListView) findViewById(R.id.alunosTurmaList);

        ArrayList<CharSequence> alunosArray = new ArrayList();
        ArrayList<CharSequence> raArray = new ArrayList();
        ArrayList<CharSequence> turmasArray = new ArrayList();

        alunosNomeList.setOnItemClickListener(this);
        alunosRaList.setOnItemClickListener(this);
        alunosTurmaList.setOnItemClickListener(this);

        Read r = new Read(getApplicationContext());
        r.selectNomeTurmas(alunosArray, raArray, turmasArray);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*String item = (String) alunosNomeList.getAdapter().getItem(position);
        Toast.makeText(this, "Nome selecionado: " + item, Toast.LENGTH_SHORT).show();

        String item2 = (String) alunosRaList.getAdapter().getItem(position);
        Toast.makeText(this, "RA selecionado: " + item2, Toast.LENGTH_SHORT).show();

        String item3 = (String) alunosTurmaList.getAdapter().getItem(position);
        Toast.makeText(this, "Turma selecionada: " + item3, Toast.LENGTH_SHORT).show();*/

    }

    @Override
    public void onClick(View view) {
        Intent registrarAluno = new Intent(this, RegistrarAluno.class);
        startActivity(registrarAluno);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getRa() { return ra; }

    public void setRa(int ra) { this.ra = ra; }

    public String getTurma() { return turma; }

    public void setTurma(String turma) { this.turma = turma; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }
}
