package br.edu.anhembi.gabaritando;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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



        final ArrayAdapter<CharSequence> nameAdapter = new ArrayAdapter<CharSequence>
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

        //logica do clique longo na listview
        turmasNomeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Turmas.this);
                alerta.setTitle("Deseja Editar ou Deletar a Turma?");
                //alerta.setMessage("mensagem teste");
                alerta.setCancelable(true);
                alerta.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Nao escolhido",Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String nomeEditar = turmasNomeList.getItemAtPosition(position).toString();
                        String universidadeEditar = universidadeList.getItemAtPosition(position).toString();
                        String campusEditar = campusList.getItemAtPosition(position).toString();

                        //Toast.makeText(getApplicationContext(),nomeEditar + " " +  universidadeEditar + " " + campusEditar,Toast.LENGTH_SHORT).show();
                        Intent EditarTurma = new Intent(Turmas.this, EditarTurma.class);
                        EditarTurma.putExtra("nome",nomeEditar);
                        EditarTurma.putExtra("universidade",universidadeEditar);
                        EditarTurma.putExtra("campus",campusEditar);
                        startActivity(EditarTurma);
                    }
                });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
                return false;
            }
        });
        universidadeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Turmas.this);
                alerta.setTitle("Deseja editar ou deletar a turma?");
                //alerta.setMessage("mensagem teste");
                alerta.setCancelable(true);
                alerta.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Nao escolhido",Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nomeEditar = turmasNomeList.getItemAtPosition(position).toString();
                        String universidadeEditar = universidadeList.getItemAtPosition(position).toString();
                        String campusEditar = campusList.getItemAtPosition(position).toString();

                        //Toast.makeText(getApplicationContext(),nomeEditar + " " +  universidadeEditar + " " + campusEditar,Toast.LENGTH_SHORT).show();
                        Intent EditarTurma = new Intent(Turmas.this, EditarTurma.class);
                        EditarTurma.putExtra("nome",nomeEditar);
                        EditarTurma.putExtra("universidade",universidadeEditar);
                        EditarTurma.putExtra("campus",campusEditar);
                        startActivity(EditarTurma);
                    }
                });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
                return false;
            }
        });
        campusList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Turmas.this);
                alerta.setTitle("Deseja Editar ou Deletar a Turma?");
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

                        String nomeEditar = turmasNomeList.getItemAtPosition(position).toString();
                        String universidadeEditar = universidadeList.getItemAtPosition(position).toString();
                        String campusEditar = campusList.getItemAtPosition(position).toString();

                        //Toast.makeText(getApplicationContext(),nomeEditar + " " +  universidadeEditar + " " + campusEditar,Toast.LENGTH_SHORT).show();
                        Intent EditarTurma = new Intent(Turmas.this, EditarTurma.class);
                        EditarTurma.putExtra("nome",nomeEditar);
                        EditarTurma.putExtra("universidade",universidadeEditar);
                        EditarTurma.putExtra("campus",campusEditar);
                        startActivity(EditarTurma);
                    }
                });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
                return false;
            }
        });



    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        String item = (String) turmasNomeList.getAdapter().getItem(position);
        //Toast.makeText(this, "Nome selecionado: " + item, Toast.LENGTH_SHORT).show();

        String item2 = (String) universidadeList.getAdapter().getItem(position);
       //Toast.makeText(this, "Universidade selecionada: " + item2, Toast.LENGTH_SHORT).show();

        String item3 = (String) campusList.getAdapter().getItem(position);
        //Toast.makeText(this, "Campus selecionado: " + item3, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Intent registrarTurma = new Intent(this, RegistrarTurma.class);
        startActivity(registrarTurma);
        finish();
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

    public String getTurmaCampus() { return turmaCampus; }

    public void setTurmaCampus(String turmaCampus) {
        this.turmaCampus = turmaCampus;
    }

    public String toString() {
        return turmaNome;
    }
}
