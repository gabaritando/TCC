package br.edu.anhembi.gabaritando;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.anhembi.gabaritando.CRUD.Update;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText editNome, editEmail, editSenha, editSenha2;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNome = (EditText) findViewById(R.id.editNome);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editSenha2 = (EditText) findViewById(R.id.editSenha2);
        btnRegister = (Button) findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (!editSenha.getText().toString().equals(editSenha2.getText().toString())) {
            Toast.makeText(this, "Senhas não correspondem!", Toast.LENGTH_SHORT).show();
        } else if (editSenha.getText().length() < 6) {
            editSenha.setError("Para a senha é necessário conter mais de 6 caracteres!");
            editSenha.requestFocus();
            editSenha2.requestFocus();
        } else {
            Docente d = new Docente();

            d.setNome(editNome.getText().toString());
            d.setEmail(editEmail.getText().toString());
            d.setSenha(editSenha.getText().toString());

            Update u = new Update(getApplicationContext());

            if (u.insertDocente(d)) {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                Intent telaPrincipal = new Intent(this, Principal.class);
                startActivity(telaPrincipal);
            } else {
                Toast.makeText(this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
            }


        }
    }

}