package com.example.thaouet.memoire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnregistrerScore extends AppCompatActivity {


    EditText txtNom;
    TextView txtScore;
    long score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_score);

        txtNom = (EditText)findViewById(R.id.editTextNom);
        txtScore = (TextView)findViewById(R.id.textView3);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getLong("SCORE");
        }
        txtScore.setText("SCORE: "+score);




    }


    public void buttonEnregistrerClick (View view)
    {
        JoueurSQLiteHelper db = new JoueurSQLiteHelper(this);

        db.ajouterJoueur(new Joueur(txtNom.getText().toString(),(int)score));

        Toast.makeText(EnregistrerScore.this,"Score Enregistré",Toast.LENGTH_LONG).show();
        finish();
    }


}
