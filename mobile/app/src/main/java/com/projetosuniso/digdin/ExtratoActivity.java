package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ExtratoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openMenu();
            }
        });

        loadExtrato();
    }

    private void loadExtrato() {

        TableLayout tl = findViewById(R.id.extratoTable);

        for(int i=0; i<=50; i++) {

            TableRow tr = new TableRow(this);

            for(int j=0; j<3; j++) {
                TextView tv = new TextView(this);

                tv.setGravity(Gravity.CENTER);
                tv.setPadding(5, 5,5,5);

                if(j==0) { tv.setText("Data" + i); }
                else if (j==1) { tv.setText("Descricao" + i); }
                else if (j==2) { tv.setText("Valor" + i); }

                tr.addView(tv);
            }


            tr.setBackgroundColor(Color.parseColor("#e8e8e8"));
            tr.setPadding(5,5,5,5);
            tl.addView(tr);
        }

    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}