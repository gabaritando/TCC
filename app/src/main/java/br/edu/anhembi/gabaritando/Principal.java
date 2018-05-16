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
import android.widget.Toast;

import java.io.IOException;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    private Button btRegister, btnLogin;

    String url = "";
    String parametros = "";
    EditText editEmail1, editSenha1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("RAM", "Tela Principal: onCreate");
        setContentView(R.layout.activity_principal);

        btRegister = (Button) findViewById(R.id.btnregister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Principal.this,Register.class);
                startActivity(register);
            }
        });

        editEmail1 = (EditText)findViewById(R.id.editEmail);
        editSenha1 = (EditText)findViewById(R.id.editSenha);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){
                    String email = editEmail1.getText().toString();
                    String senha = editSenha1.getText().toString();

                    if (email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://192.168.0.100:8080/login/logar.php";
                        new SolicitaDados().execute(url);

                        parametros = "email = " + email + "senha = " + senha;
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }

                Intent telaHome = new Intent(Principal.this, Home.class);
                startActivity(telaHome);
            }
        });

    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);

        }

        protected void onPostExecute(String result) {
            //chamando a activity apos login
            if (result.contains("login_ok")) {
                Intent abreinicio = new Intent(Principal.this, Home.class);
                startActivity(abreinicio);
            } else {
                Toast.makeText(getApplicationContext(), "Usuario ou senha estao incorretos", Toast.LENGTH_LONG).show();
            }
        }
    }

    //caso a tela do celuar for desligada ou trocar de tela o app ele fecha
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
