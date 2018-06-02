package br.edu.anhembi.gabaritando;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.edu.anhembi.gabaritando.CRUD.Create;
import br.edu.anhembi.gabaritando.CRUD.Read;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister, btnLogin;
    TextView editEmail, editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Log.i("RAM", "Tela Principal: onCreate");
        setContentView(R.layout.activity_principal);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        Create c = new Create(getApplicationContext());
        c.createTable();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegister){
            Intent register = new Intent(this, Register.class);
            startActivity(register);
        } else if (view.getId() == R.id.btnLogin){
            Read r = new Read(getApplicationContext());
            ArrayList<Docente> dArray = r.getDocentes();

            for (int i = 0; i < dArray.size(); i++) {
                Docente d = dArray.get(i);
                System.out.println("Nome: " + d.getNome() + " Email: " + d.getEmail() + " Senha: " + d.getSenha());
            }

            if (r.validaSenha(editEmail.getText().toString(), editSenha.getText().toString())){
                Intent telaHome = new Intent(this, Home.class);
                startActivity(telaHome);
                Toast.makeText(this, "Usuário logado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
