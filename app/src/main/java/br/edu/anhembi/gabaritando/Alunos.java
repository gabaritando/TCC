package br.edu.anhembi.gabaritando;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class Alunos extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView nameList, raList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        nameList = (ListView) findViewById(R.id.nameList);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.nameList, android.R.layout.simple_expandable_list_item_1);
        nameList.setAdapter(adapter);
        nameList.setOnItemClickListener(this);

        raList = (ListView) findViewById(R.id.raList);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource
                (this, R.array.raList, android.R.layout.simple_expandable_list_item_1);
        raList.setAdapter(adapter1);
        raList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) nameList.getAdapter().getItem(position);
        Toast.makeText(this, "Nome selecionado: " + item, Toast.LENGTH_SHORT).show();

        String item2 = (String) raList.getAdapter().getItem(position);
        Toast.makeText(this, "RA selecionado: " + item2, Toast.LENGTH_SHORT).show();

    }
}
