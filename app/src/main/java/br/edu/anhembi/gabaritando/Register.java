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

public class Register extends AppCompatActivity {

    EditText editNome, editMail, editSenha, editSenha2;
    TextView textTermos;
    Button btnRegister;


    String url = "";
    String parametros = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNome = (EditText)findViewById(R.id.editNome);
        editMail = (EditText)findViewById(R.id.editMail);
        editSenha = (EditText)findViewById(R.id.editSenha);
        editSenha2 = (EditText)findViewById(R.id.editSenha2);
        btnRegister = (Button)findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){
                    String nome = editNome.getText().toString();
                    String email = editMail.getText().toString();
                    String senha = editSenha2.getText().toString();


                    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {

                        url = "http://192.168.0.100:8080/login/registrar.php";

                        parametros =  "nome=" + nome +"&email=" + email + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);

        }

        protected void onPostExecute(String result) {

            if(result.contains("email_erro")){
                Toast.makeText(getApplicationContext(), "Este emaiol ja esta registrado", Toast.LENGTH_LONG).show();

            }else if (result.contains("registro_ok")) {
                Intent abreinicio = new Intent(Register.this, Principal.class);
                Toast.makeText(getApplicationContext(), "Registro concluido com sucesso!", Toast.LENGTH_LONG).show();
                startActivity(abreinicio);
            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro!", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
