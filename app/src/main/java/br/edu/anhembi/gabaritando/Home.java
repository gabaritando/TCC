package br.edu.anhembi.gabaritando;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.anhembi.gabaritando.CRUD.Read;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button btnGabaritos, btnTurmas, btnAlunos;
    TextView nomeHome, navNomeHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nomeHome = (TextView) findViewById(R.id.txtNomeHome);



        String emailHome = getIntent().getExtras().getString("exibirNome"); //recebendo o nome do usuario
        Read r = new Read(getApplicationContext());

        //esse trecho de codigo seleciona apenas o primeiro nome do usuario
        String exibirNome = r.selectNome(emailHome);
        String pattern = "\\S+";

        Pattern pt = Pattern.compile(pattern);
        Matcher m = pt.matcher(exibirNome);

        if (m.find( )) {
            nomeHome.setText(m.group(0));
        }


        //fim da selecao do primeiro nome


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hview = navigationView.getHeaderView(0);

        btnGabaritos = (Button) findViewById(R.id.btnGabaritos);
        btnTurmas = (Button) findViewById(R.id.btnTurmas);
        btnAlunos = (Button) findViewById(R.id.btnAlunos);

        navNomeHome = (TextView) hview.findViewById(R.id.txtNavNomeHome);

        btnGabaritos.setOnClickListener(this);
        btnTurmas.setOnClickListener(this);
        btnAlunos.setOnClickListener(this);

        navNomeHome.setText(nomeHome.getText().toString());



    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnGabaritos){
            Intent telaGabaritos = new Intent(Home.this, Gabaritos.class);
            startActivity(telaGabaritos);
        } else if(view.getId() == R.id.btnTurmas) {
            Intent telaTurmas = new Intent(Home.this, Turmas.class);
            startActivity(telaTurmas);
        } else if(view.getId() == R.id.btnAlunos) {
            Intent telaAlunos = new Intent(Home.this, Alunos.class);
            startActivity(telaAlunos);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slide_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent telaHome = new Intent(Home.this, Home.class);
                startActivity(telaHome);
                break;
            case R.id.nav_turma:
                Intent telaTurma = new Intent(Home.this, Turmas.class);
                startActivity(telaTurma);
                return true;
            case R.id.nav_gabarito:
                Intent telaGabarito = new Intent(Home.this, Gabaritos.class);
                startActivity(telaGabarito);
                return true;
            /*case R.id.nav_perfil:
                Intent telaPerfil = new Intent(Home.this, Perfil.class);
                startActivity(telaPerfil);
                return true;*/
            /*case R.id.nav_share:
                Intent telaShare = new Intent(Home.this, Share.class);
                startActivity(telaShare);
                return true;*/
            case R.id.nav_logout:
                Intent telaSair = new Intent(Home.this, Principal.class);
                startActivity(telaSair);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
