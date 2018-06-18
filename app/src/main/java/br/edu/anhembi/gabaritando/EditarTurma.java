package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.anhembi.gabaritando.CRUD.Update;

public class EditarTurma extends AppCompatActivity implements View.OnClickListener {

    EditText editTextNomeTurma, editTextUniversidade, editTextCampus;
    TextView txtDeletarTurma;
    Button btnEditarTurma;
    private int idSelecionado;
    private String TurmaSelecionada, UniversidadeSelecionada,CampusSelecionado;
    private String NovaTurma, NovaUniversidade, NovoCampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_turma);
        txtDeletarTurma = (TextView) findViewById(R.id.txtDeletarTurma);
        editTextNomeTurma = (EditText) findViewById(R.id.editTextNomeTurma);
        editTextUniversidade = (EditText) findViewById(R.id.editTextUniversidade);
        editTextCampus = (EditText) findViewById(R.id.editTextCampus);
        btnEditarTurma = (Button) findViewById(R.id.btnEditarTurma);

        Intent receberIntent = getIntent();
        //pegando os dados da turma selecionada
        TurmaSelecionada = receberIntent.getStringExtra("nome");
        UniversidadeSelecionada = receberIntent.getStringExtra("universidade");
        CampusSelecionado = receberIntent.getStringExtra("campus");

        editTextNomeTurma.setText(TurmaSelecionada);
        editTextUniversidade.setText(UniversidadeSelecionada);
        editTextCampus.setText(CampusSelecionado);

        txtDeletarTurma.setOnClickListener(this);

        btnEditarTurma.setOnClickListener(this);
    }

    public void onClick(View view) {
        NovaTurma = editTextNomeTurma.getText().toString();
        NovaUniversidade = editTextUniversidade.getText().toString();
        NovoCampus = editTextCampus.getText().toString();
        Update u = new Update(getApplicationContext());
        if(view.getId() == R.id.btnEditarTurma) {

            u.alterarTurma(TurmaSelecionada, UniversidadeSelecionada, CampusSelecionado, NovaTurma, NovaUniversidade, NovoCampus);
            Intent telaTurma = new Intent(EditarTurma.this, Turmas.class);
            startActivity(telaTurma);
        }else if (view.getId() == R.id.txtDeletarTurma){

            u.deletarTurma(TurmaSelecionada, UniversidadeSelecionada, CampusSelecionado);
            Intent telaTurma = new Intent(EditarTurma.this, Turmas.class);
            startActivity(telaTurma);

        }
        finish();

    }
}
