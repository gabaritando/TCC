package br.edu.anhembi.gabaritando;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

    private int id, ra, turma;
    private String nome;



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

        ArrayAdapter<CharSequence> alunosAdapter = new ArrayAdapter<CharSequence>
                (this, android.R.layout.simple_list_item_1, alunosArray );

        ArrayAdapter<CharSequence> raArrayAdapter = new ArrayAdapter<CharSequence>
                (this, android.R.layout.simple_list_item_1, raArray );

        ArrayAdapter<CharSequence> turmasAdapter = new ArrayAdapter<CharSequence>
                (this, android.R.layout.simple_list_item_1, turmasArray );


        alunosNomeList.setAdapter(alunosAdapter);
        alunosRaList.setAdapter(raArrayAdapter);
        alunosTurmaList.setAdapter(turmasAdapter);

        alunosNomeList.setOnItemClickListener(this);
        alunosRaList.setOnItemClickListener(this);
        alunosTurmaList.setOnItemClickListener(this);



        Read r = new Read(getApplicationContext());
        r.selectNomeAlunos(alunosArray, raArray, turmasArray);
        alunosNomeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Alunos.this);
                alerta.setTitle("Deseja editar ou deletar aluno?");
                //alerta.setMessage("mensagem teste");
                alerta.setCancelable(true);
                alerta.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"Nao escolhido",Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String alunoNomeEditar = alunosNomeList.getItemAtPosition(position).toString();
                        String alunoRaEditar = alunosRaList.getItemAtPosition(position).toString();


                        Intent EditarAluno = new Intent(Alunos.this, EditarAluno.class);
                        int ra = Integer.parseInt(alunoRaEditar);
                        Read read = new Read(getApplicationContext());
                        int alunoTurmaEditar = read.selectTurma(alunoNomeEditar, ra);
                        EditarAluno.putExtra("nome", alunoNomeEditar );
                        EditarAluno.putExtra("ra", ra);
                        EditarAluno.putExtra("turma", alunoTurmaEditar );
                        startActivity(EditarAluno);
                    }
                });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
                return false;
            }
        });
        alunosRaList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Alunos.this);
                alerta.setTitle("Deseja editar ou deletar aluno?");
                //alerta.setMessage("mensagem teste");
                alerta.setCancelable(true);
                alerta.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"Nao escolhido",Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String alunoNomeEditar = alunosNomeList.getItemAtPosition(position).toString();
                        String alunoRaEditar = alunosRaList.getItemAtPosition(position).toString();


                        Intent EditarAluno = new Intent(Alunos.this, EditarAluno.class);
                        int ra = Integer.parseInt(alunoRaEditar);
                        Read read = new Read(getApplicationContext());
                        int alunoTurmaEditar = read.selectTurma(alunoNomeEditar, ra);
                        EditarAluno.putExtra("nome", alunoNomeEditar );
                        EditarAluno.putExtra("ra", ra);
                        EditarAluno.putExtra("turma", alunoTurmaEditar );
                        startActivity(EditarAluno);
                    }
                });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
                return false;
            }
        });
        alunosTurmaList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Alunos.this);
                alerta.setTitle("Deseja editar ou deletar aluno?");
                //alerta.setMessage("mensagem teste");
                alerta.setCancelable(true);
                alerta.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"Nao escolhido",Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String alunoNomeEditar = alunosNomeList.getItemAtPosition(position).toString();
                        String alunoRaEditar = alunosRaList.getItemAtPosition(position).toString();


                        Intent EditarAluno = new Intent(Alunos.this, EditarAluno.class);
                        int ra = Integer.parseInt(alunoRaEditar);
                        Read read = new Read(getApplicationContext());
                        int alunoTurmaEditar = read.selectTurma(alunoNomeEditar, ra);
                        EditarAluno.putExtra("nome", alunoNomeEditar );
                        EditarAluno.putExtra("ra", ra);
                        EditarAluno.putExtra("turma", alunoTurmaEditar );
                        startActivity(EditarAluno);
                    }
                });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
                return false;
            }
        });
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

    public int getId() {
        return id; }

    public void setId(int id) {
        this.id = id; }

    public int getRa() {
        return ra; }

    public void setRa(int ra) {
        this.ra = ra; }

    public int getTurma() {
        return turma; }

    public void setTurma(int turma) {
        this.turma = turma; }

    public String getNome() {
        return nome; }

    public void setNome(String nome) {
        this.nome = nome; }

    public String toString(){
        return nome;
    }
}
