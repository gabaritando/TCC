package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.anhembi.gabaritando.CRUD.Read;
import br.edu.anhembi.gabaritando.CRUD.Update;

public class RegistrarAluno extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText editTextNomeAluno, editTextRA;
    Button btnRegisterAluno;
    ArrayList alunoTurmas;
    Spinner spTurmas;
    Alunos aluno = new Alunos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aluno);

        editTextNomeAluno = (EditText) findViewById(R.id.editTextNomeAluno);
        editTextRA = (EditText) findViewById(R.id.editTextRA);
        btnRegisterAluno = (Button) findViewById(R.id.btnEditarAluno);

        Read r = new Read(getApplicationContext());


        alunoTurmas = r.getTurmas();

        ArrayAdapter<Turmas> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alunoTurmas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTurmas = (Spinner) findViewById(R.id.spinnerAlunoTurma);


        spTurmas.setAdapter(adapter);
        spTurmas.setOnItemSelectedListener(this);


        loadSpinnerData();


        btnRegisterAluno.setOnClickListener(this);
    }

    private void loadSpinnerData() {
    }

    @Override
    public void onClick(View view) {

        aluno.setNome(editTextNomeAluno.getText().toString());
        aluno.setRa(Integer.parseInt(editTextRA.getText().toString()));

        Update u = new Update(getApplicationContext());

        if (u.insertAluno(aluno)) {
            Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            Intent telaAlunos = new Intent(this, Alunos.class);
            startActivity(telaAlunos);
        } else {
            Toast.makeText(this, "Erro ao cadastrar aluno!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        aluno.setTurma((int) spTurmas.getSelectedItemId() + 1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}