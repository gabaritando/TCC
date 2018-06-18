package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.anhembi.gabaritando.CRUD.Read;


public class Resultado extends AppCompatActivity implements View.OnClickListener {
    Spinner spinnerAlunos;
    ArrayList alunos;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Read readAluno = new Read(getApplicationContext());

        alunos = readAluno.getAlunos();


        ArrayAdapter<Alunos> adapterAluno = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, alunos);
        adapterAluno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        spinnerAlunos = (Spinner) findViewById(R.id.spinnerAluno);
        adapterAluno.toString();
        spinnerAlunos.setAdapter(adapterAluno);

        loadSpinnerData();
        btnEnviar.setOnClickListener(this);
    }
    private void loadSpinnerData() {
    }

    public void onClick(View view){

        Toast.makeText(this, "Nota Atribuida com sucesso!", Toast.LENGTH_SHORT).show();

        Intent telaHome = new Intent(this, Alunos.class);
        startActivity(telaHome);
        finish();
    }


}
