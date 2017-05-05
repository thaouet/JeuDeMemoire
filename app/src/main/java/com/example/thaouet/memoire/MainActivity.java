package com.example.thaouet.memoire;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    GridView gridView;
    Chronometer stopWatch;
    //TextView textGoesHere;
    boolean tour = false;
    boolean start = false;
    int cpt = 0;
    int imgrx;
    ImageView imgx;
    long startTime;
    long countUp;
    ImageAdapter adapter;
    public Integer[] ImagesId = {R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img1,
            R.drawable.img2, R.drawable.img3, R.drawable.img5,
            R.drawable.img6, R.drawable.img4, R.drawable.img6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridview1);


        adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);


        stopWatch = (Chronometer) findViewById(R.id.chrono);
        startTime = SystemClock.elapsedRealtime();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if (start == true) {


                    final  ImageView img = (ImageView) v;


                    Runnable r = new Runnable(){
                        public void run(){
                            img.setImageResource(R.drawable.img0);

                        }
                    };

                    Runnable r1 = new Runnable(){
                        public void run(){
                            imgx.setImageResource(R.drawable.img0);

                        }
                    };


                    img.setImageResource(ImagesId[position]);

                    if (tour == true) {




                        if (ImagesId[position] == imgrx) {
                            tour = false;
                            cpt++;
                            img.setImageResource(ImagesId[position]);
                        } else {



                            img.setImageResource(R.drawable.img0);
                            imgx.setImageResource(R.drawable.img0);



                            tour = false;

                        }
                    } else {
                        img.setImageResource(ImagesId[position]);
                        imgx = img;
                        imgrx = ImagesId[position];

                        tour = true;
                    }

                    if (cpt == 6) {
                        Toast.makeText(MainActivity.this, "Bravo! vous avez gagné .\n Votre temps est: " + stopWatch.getText().toString(), Toast.LENGTH_LONG).show();


                        stopWatch.stop();

                       Intent intent = new Intent(MainActivity.this,EnregistrerScore.class);

                        long timeElapsed = SystemClock.elapsedRealtime() - stopWatch.getBase();


                       intent.putExtra("SCORE",timeElapsed);
                       startActivity(intent);



                        start = false;
                    }
                }




            }
        });




    }

    public void demarrerButtonClick(View view) {

        //reinitialiser les boutons
        gridView.setAdapter(adapter);
        tour = false;
        start = true;
        cpt = 0;
        stopWatch.setBase(SystemClock.elapsedRealtime());
        stopWatch.start();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu, menu);


        return true;
    }

    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.aide:
                Toast.makeText(MainActivity.this, "Aide...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.heightscores:

                Intent intent = new Intent(MainActivity.this,ScoreActivity.class);
                startActivity(intent);
                return true;

        }
        return false;}



public class ImageAdapter extends BaseAdapter
{
    private Context context;

    public ImageAdapter(Context c)
    {
        context = c;
    }

    //---returns the number of images---
    public int getCount() {
        return ImagesId.length;
    }

    //---returns the ID of an item---
    public Object getItem(int position) {
        return ImagesId[position];
    }

    public long getItemId(int position) {
        return position;
    }

    //---returns an ImageView view---
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        /*   if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(185, 185));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(5, 5, 5, 5);
            } else {
    imageView = (ImageView) convertView;
}*/
        imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(5, 5, 5, 5);
        imageView.setImageResource(R.drawable.img0);

        return imageView;
    }
}

}








