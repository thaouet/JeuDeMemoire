package com.example.thaouet.memoire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class JoueurSQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "JoueurDB";


    // Nom de la table
    private static final String TABLE_JOUEUR = "Joueur";

    // Les colonnes de la table joueur
    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nom";
    private static final String KEY_SCORE = "score";

    private static final String[] COLUMNS = {KEY_ID,KEY_NOM,KEY_SCORE};



    public JoueurSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create joueur table
        String CREATE_JOUEUR_TABLE = "CREATE TABLE Joueur ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, "+
                "score INTEGER )";

        // créer joueur table
        db.execSQL(CREATE_JOUEUR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older joueur table if existed
        db.execSQL("DROP TABLE IF EXISTS joueur");

        // create fresh books table
        this.onCreate(db);
    }

    //Ajouter un Joueur
    public void ajouterJoueur(Joueur joueur){


        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. créer un objet  ContentValues pour stocker objets de type cle "column"/valeur
        ContentValues values = new ContentValues();
        values.put(KEY_NOM, joueur.getNom()); // get Nom
        values.put(KEY_SCORE, joueur.getScore()); // get Score

        // 3. insert
        db.insert(TABLE_JOUEUR, // table
                null, //nullColumnHack
                values); // cle/valeur -> cle = column names/ valeurs = column values

        // 4. close
        db.close();
    }


    public Joueur getJoueur(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_JOUEUR, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Joueur joueur = new Joueur();
        joueur.setId(Integer.parseInt(cursor.getString(0)));
        joueur.setNom(cursor.getString(1));
        joueur.setScore(Integer.parseInt(cursor.getString(2)));


        // 5. return joueur
        return joueur;
    }

    public List<Joueur> getListJoueurs() {
        List<Joueur> joueurs = new LinkedList<Joueur>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_JOUEUR + " ORDER BY " + KEY_SCORE  +  " DESC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Joueur joueur = null;
        if (cursor.moveToFirst()) {
            do {
                joueur = new Joueur();
                joueur.setId(Integer.parseInt(cursor.getString(0)));
                joueur.setNom(cursor.getString(1));
                joueur.setScore(Integer.parseInt(cursor.getString(2)));

                // Add book to books
                joueurs.add(joueur);
            } while (cursor.moveToNext());
        }

        // return books
        return joueurs;
    }

    public int updateJoueur(Joueur joueur) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("nom", joueur.getNom()); // get nom
        values.put("score", joueur.getScore()); // get score

        // 3. updating row
        int i = db.update(TABLE_JOUEUR, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(joueur.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }


    public void supprimerJoueur(Joueur joueur) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_JOUEUR, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(joueur.getId())}); //selections args

        // 3. close
        db.close();



    }
}
