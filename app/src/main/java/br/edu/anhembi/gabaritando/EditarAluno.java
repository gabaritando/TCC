package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;

import br.edu.anhembi.gabaritando.CRUD.Read;
import br.edu.anhembi.gabaritando.CRUD.Update;

public class EditarAluno extends AppCompatActivity implements View.OnClickListener {

    EditText editTextNomeAluno, editTextRA;
    Spinner spinnerAlunoTurma;
    Button btnEditarAluno;
    TextView txtDeletarAluno;
    ArrayList alunoTurmas;
    private String NomeSelecionado, NovoNome;
    private int TurmaSelecionada, NovaTurma, NovoRA, RASelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_aluno);
        editTextNomeAluno = (EditText) findViewById(R.id.editTextNomeAluno);
        editTextRA = (EditText) findViewById(R.id.editTextRA);
        spinnerAlunoTurma = (Spinner) findViewById(R.id.spinnerAlunoTurma);
        btnEditarAluno = (Button) findViewById(R.id.btnEditarAluno);
        txtDeletarAluno = (TextView) findViewById(R.id.txtDeletarAluno);



        Intent receberIntent = getIntent();
        //pegando dados do aluno

        RASelecionado = receberIntent.getExtras().getInt("ra");
        NomeSelecionado = receberIntent.getStringExtra("nome");
        TurmaSelecionada = receberIntent.getExtras().getInt("turma");
        String raAuxiliar = Integer.toString(RASelecionado);
        editTextRA.setText(raAuxiliar);
        editTextNomeAluno.setText(NomeSelecionado);

        Read r = new Read(getApplicationContext());




        alunoTurmas = r.getTurmas();

        ArrayAdapter<Turmas> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alunoTurmas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAlunoTurma.setAdapter(adapter);
        spinnerAlunoTurma.setSelection(TurmaSelecionada - 1);
        //spinnerAlunoTurma.setOnItemSelectedListener(this);
        loadSpinnerData();


        btnEditarAluno.setOnClickListener(this);
        txtDeletarAluno.setOnClickListener(this);


    }
    public void onClick(View view) {
        NovoNome = editTextNomeAluno.getText().toString();
        String auxra = editTextRA.getText().toString();//deve ser int
        NovoRA = Integer.parseInt(auxra);
        NovaTurma = ((int) spinnerAlunoTurma.getSelectedItemId());

        Update u = new Update(getApplicationContext());

        if(view.getId() == R.id.btnEditarAluno) {
            u.alterarAluno(RASelecionado, NomeSelecionado, TurmaSelecionada, NovoRA, NovoNome, (NovaTurma + 1));
            Intent telaAlunos = new Intent(EditarAluno.this, Alunos.class);
            startActivity(telaAlunos);
        }else if (view.getId() == R.id.txtDeletarAluno){

            u.deletarAluno(RASelecionado, NomeSelecionado, TurmaSelecionada);
            Intent telaAlunos = new Intent(EditarAluno.this, Alunos.class);
            startActivity(telaAlunos);

        }
        finish();

    }



    private void loadSpinnerData(){

    }
}
