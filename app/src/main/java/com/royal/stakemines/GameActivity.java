package com.royal.stakemines;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageButton imageButton[] = new ImageButton[16];
    String buttonValues[] = new String[16];
    boolean isGameFinished = false;
    int buttonClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // force dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageButton[0] = findViewById(R.id.imgBtn1);
        imageButton[1] = findViewById(R.id.imgBtn2);
        imageButton[2] = findViewById(R.id.imgBtn3);
        imageButton[3] = findViewById(R.id.imgBtn4);
        imageButton[4] = findViewById(R.id.imgBtn5);
        imageButton[5] = findViewById(R.id.imgBtn6);
        imageButton[6] = findViewById(R.id.imgBtn7);
        imageButton[7] = findViewById(R.id.imgBtn8);
        imageButton[8] = findViewById(R.id.imgBtn9);
        imageButton[9] = findViewById(R.id.imgBtn10);
        imageButton[10] = findViewById(R.id.imgBtn11);
        imageButton[11] = findViewById(R.id.imgBtn12);
        imageButton[12] = findViewById(R.id.imgBtn13);
        imageButton[13] = findViewById(R.id.imgBtn14);
        imageButton[14] = findViewById(R.id.imgBtn15);
        imageButton[15] = findViewById(R.id.imgBtn16);

        for (int i = 0; i < 16; i++) {
            buttonValues[i] = "diamond";
            final int index = i;
            imageButton[i].setOnClickListener(view -> playGame(index));

        }

        for (int i = 0; i < 4; i++) {
            assignBomb();
        }
        Log.i("Values", Arrays.toString(buttonValues));
    }

    void playGame(int index) {
        if (isGameFinished) return;

        if (buttonValues[index].equals("bomb")) {
            imageButton[index].setBackground(getDrawable(R.drawable.bomb));
            Toast.makeText(this, "You Lost", Toast.LENGTH_SHORT).show();
            isGameFinished = true;
        } else {
            imageButton[index].setBackground(getDrawable(R.drawable.diamond));
        }
        buttonClicked++;
        if (buttonClicked == 12) {
            Toast.makeText(this, "You Won", Toast.LENGTH_SHORT).show();
            isGameFinished = true;
        }
    }

    void assignBomb() {
        int temp = new Random().nextInt(16);

        if (buttonValues[temp].equals("diamond")) {
            buttonValues[temp] = "bomb";
        } else {
            assignBomb();
        }
    }

}