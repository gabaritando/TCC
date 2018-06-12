package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.anhembi.gabaritando.CRUD.Update;

public class RegistrarAluno extends AppCompatActivity implements View.OnClickListener {

    EditText editTextNomeAluno, editTextRA, editTextAlunoTurma;
    Button btnRegisterAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aluno);

        editTextNomeAluno = (EditText) findViewById(R.id.editTextNomeAluno);
        editTextRA = (EditText) findViewById(R.id.editTextRA);
        editTextAlunoTurma = (EditText) findViewById(R.id.editTextAlunoTurma);
        btnRegisterAluno = (Button) findViewById(R.id.btnRegisterAluno);


        btnRegisterAluno.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Alunos a = new Alunos();

        a.setNome(editTextNomeAluno.getText().toString());
        a.setRa(Integer.parseInt(editTextRA.getText().toString()));
        a.setTurma(editTextAlunoTurma.getText().toString());

        Update u = new Update(getApplicationContext());

        if (u.insertAluno(a)) {
            Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            Intent telaAlunos = new Intent(this, Alunos.class);
            startActivity(telaAlunos);
        } else {
            Toast.makeText(this, "Erro ao cadastrar aluno!", Toast.LENGTH_SHORT).show();
        }
    }
}
