package com.example.thaouet.memoire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ScoreActivity extends AppCompatActivity {


    ListView listeScores;
    List<Joueur> listeJoueur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        listeScores = (ListView) findViewById(R.id.list);
        JoueurSQLiteHelper db = new JoueurSQLiteHelper(this);
        listeJoueur = db.getListJoueurs();


        String[] tab = new String[listeJoueur.size()];
        for (int i=0; i<listeJoueur.size();i++)
        {
            tab[i] = listeJoueur.get(i).toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tab );
        listeScores.setAdapter(adapter);
    }
}
