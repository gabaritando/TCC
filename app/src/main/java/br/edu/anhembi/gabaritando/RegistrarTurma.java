package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.anhembi.gabaritando.CRUD.Read;
import br.edu.anhembi.gabaritando.CRUD.Update;

public class RegistrarTurma extends AppCompatActivity implements View.OnClickListener {

    EditText editTextNomeTurma, editTextUniversidade, editTextCampus;
    Button btnRegisterTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_turma);

        editTextNomeTurma = (EditText) findViewById(R.id.editTextNomeTurma);
        editTextUniversidade = (EditText) findViewById(R.id.editTextUniversidade);
        editTextCampus = (EditText) findViewById(R.id.editTextCampus);
        btnRegisterTurma = (Button) findViewById(R.id.btnRegisterTurma);


        btnRegisterTurma.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Turmas t = new Turmas();

        t.setTurmaNome(editTextNomeTurma.getText().toString());
        t.setTurmaUniversidade(editTextUniversidade.getText().toString());
        t.setTurmaCampus(editTextCampus.getText().toString());

        Update u = new Update(getApplicationContext());

        if (u.insertTurma(t)) {
            Toast.makeText(this, "Turma cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

            Intent telaTurmas = new Intent(this, Turmas.class);
            startActivity(telaTurmas);
        } else {
            Toast.makeText(this, "Erro ao cadastrar turma!", Toast.LENGTH_SHORT).show();
        }
        finish();

    }
}
