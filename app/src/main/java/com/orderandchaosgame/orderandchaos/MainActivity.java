/**
 * Developer: Bushra Al Sibai
 * App: Order and Chaos
 * application tested on 4.7,768 x 1280, xhdpi(Nexus4)
 */

package com.orderandchaosgame.orderandchaos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.aboutGame_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutGame();
            }
        });
        Button aboutButton = findViewById(R.id.aboutApp_button);
        MyListener myListener = new MyListener(aboutButton);
        aboutButton.setOnClickListener(myListener);

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(this);

    }


    private void aboutGame() {
        Uri uri = Uri.parse("https://en.wikipedia.org/wiki/Order_and_Chaos");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void aboutApp(){
        Intent intent = new Intent(getApplicationContext(), aboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }

    private class MyListener implements View.OnClickListener {
        private Button button;

        public MyListener(Button button) {
            this.button = button;
        }

        @Override
        public void onClick(View view) {
            aboutApp();
        }
    }
}