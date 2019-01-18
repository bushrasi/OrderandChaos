/**
 * Developer: Bushra Al Sibai
 * App: Order and Chaos
 * application tested on 4.7,768 x 1280, xhdpi(Nexus4)
 */
package com.orderandchaosgame.orderandchaos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GameActivity extends Activity implements View.OnClickListener {

    int[] imageView_ids = {
            R.id.imageView_0_0, R.id.imageView_0_1, R.id.imageView_0_2, R.id.imageView_0_3, R.id.imageView_0_4, R.id.imageView_0_5,
            R.id.imageView_1_0, R.id.imageView_1_1, R.id.imageView_1_2, R.id.imageView_1_3, R.id.imageView_1_4, R.id.imageView_1_5,
            R.id.imageView_2_0, R.id.imageView_2_1, R.id.imageView_2_2, R.id.imageView_2_3, R.id.imageView_2_4, R.id.imageView_2_5,
            R.id.imageView_3_0, R.id.imageView_3_1, R.id.imageView_3_2, R.id.imageView_3_3, R.id.imageView_3_4, R.id.imageView_3_5,
            R.id.imageView_4_0, R.id.imageView_4_1, R.id.imageView_4_2, R.id.imageView_4_3, R.id.imageView_4_4, R.id.imageView_4_5,
            R.id.imageView_5_0, R.id.imageView_5_1, R.id.imageView_5_2, R.id.imageView_5_3, R.id.imageView_5_4, R.id.imageView_5_5

    };
    int[][] ids = new int[][]{{R.id.imageView_0_0, R.id.imageView_0_1, R.id.imageView_0_2, R.id.imageView_0_3, R.id.imageView_0_4, R.id.imageView_0_5},
            {R.id.imageView_1_0, R.id.imageView_1_1, R.id.imageView_1_2, R.id.imageView_1_3, R.id.imageView_1_4, R.id.imageView_1_5},
            {R.id.imageView_2_0, R.id.imageView_2_1, R.id.imageView_2_2, R.id.imageView_2_3, R.id.imageView_2_4, R.id.imageView_2_5},
            {R.id.imageView_3_0, R.id.imageView_3_1, R.id.imageView_3_2, R.id.imageView_3_3, R.id.imageView_3_4, R.id.imageView_3_5},
            {R.id.imageView_4_0, R.id.imageView_4_1, R.id.imageView_4_2, R.id.imageView_4_3, R.id.imageView_4_4, R.id.imageView_4_5},
            {R.id.imageView_5_0, R.id.imageView_5_1, R.id.imageView_5_2, R.id.imageView_5_3, R.id.imageView_5_4, R.id.imageView_5_5}};

    int piecesPlayed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        for (int id : imageView_ids) {
            ImageView imageView = findViewById(id);
            imageView.setTag("default");
            imageView.setOnClickListener(this);


        }

    }

    @Override
    public void onClick(View view) {
        TextView winner_text = (TextView) findViewById(R.id.winner_textView);
        TextView turn_text = (TextView) findViewById(R.id.textView_turn);
        turn_text.setText("ORDER turn");
        RadioGroup selectedPeace = (RadioGroup) findViewById(R.id.radioGroup);
        int selected_Peace = selectedPeace.getCheckedRadioButtonId();
        for (int id : imageView_ids) {
            ImageView imageView = (ImageView) view;
            //if (view.getId() == id) {
            // Button button = findViewById(id);
            if (selected_Peace == R.id.x_radioButton) {
                imageView.setImageResource(R.drawable.x2);
                imageView.setTag("x");

            } else if (selected_Peace == R.id.o_radioButton) {
                imageView.setImageResource(R.drawable.o2);
                imageView.setTag("o");
            }
            if (imageView.isClickable() == true) {
                piecesPlayed++;
                imageView.setClickable(false);
            }
            if(piecesPlayed % 2 == 0){
                turn_text.setText("ORDER turn");
            }else{
                turn_text.setText("CHAOS turn");
            }


            boolean isWin = isHorizonatalWin();
            if (isWin) {
                winner_text.setText("The Winner is ORDER!");
                setEnabled();
            } else {
                isWin = isVerticalWin();
                if (isWin) {
                    winner_text.setText("The Winner is ORDER!");
                    setEnabled();
                } else {
                    isWin = isDiagonalRightWin();
                    if (isWin) {
                        winner_text.setText("The Winner is ORDER!");
                        setEnabled();
                    } else {
                        isWin = isDiagonalLeftWin();
                        if (isWin) {
                            winner_text.setText("The Winner is ORDER!");
                            setEnabled();
                        }
                        if (piecesPlayed == 36) {
                            winner_text.setText("The Winner is CHAOS!");
                            setEnabled();
                        }
                    }
                }
            }
        }

    }

    private void setEnabled() {
        for (int i = 0; i < imageView_ids.length; i++) {
            ImageView image = (ImageView) findViewById(imageView_ids[i]);
            image.setClickable(false);
        }
        TextView turn_text = (TextView) findViewById(R.id.textView_turn);
        turn_text.setText("GAME OVER");
    }

    private boolean isHorizonatalWin() {
        boolean win = false;
        int numOfMatchElements = 0;
        ImageView imageView;
        for (int row = 0; row < ids.length; row++) {
            numOfMatchElements = 0;
            for (int col = 1; col < (ids[row].length); col++) {
                ImageView image1 = findViewById(ids[row][col]);
                ImageView image2 = findViewById(ids[row][col - 1]);
                //Log.i("message", "row is " + row + " column is " + col + image1.getBackground());
                if ((!image1.getTag().equals("default")) && (!image2.getTag().equals("default"))) {
                    if (image1.getTag().equals(image2.getTag())) {
                        //Log.i("message", "TRUE ==== here " + row + " " + col);
                        numOfMatchElements += 1;
                    }
                }
                if (numOfMatchElements == 5) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return win;
    }

    private boolean isVerticalWin() {
        boolean win = false;
        int numOfMatchElements;
        for (int col = 0; col < ids.length; col++) {
            numOfMatchElements = 0;
            for (int row = 1; row < (ids[col].length); row++) {
                ImageView image1 = findViewById(ids[row][col]);
                ImageView image2 = findViewById(ids[row - 1][col]);
                if ((!image1.getTag().equals("default")) && (!image2.getTag().equals("default"))) {
                    if (image1.getTag().equals(image2.getTag())) {
                        //Log.i("message", "TRUE ==== here " + row + " " + col);
                        numOfMatchElements += 1;
                    }
                }
                if (numOfMatchElements == 5) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return win;
    }


    private boolean isDiagonalRightWin() {
        boolean win = false;
        int numOfMatchElements = 0;
        for (int row = 1, col = 1; row < ids.length; row++, col++) {
            ImageView image1 = findViewById(ids[row][col]);
            ImageView image2 = findViewById(ids[row - 1][col - 1]);
            if ((!image1.getTag().equals("default")) && (!image2.getTag().equals("default"))) {
                if (image1.getTag().equals(image2.getTag())) {
                    //Log.i("message", "TRUE ==== here " + row + " " + col);
                    numOfMatchElements += 1;
                }
            }
        }
        if (numOfMatchElements == 5) {
            win = true;
        } else {
            win = false;
        }
        return win;
    }

    private boolean isDiagonalLeftWin() {
        boolean win = false;
        int numOfMatchElements = 0;
        for (int row = 1, col = 4; row < ids.length; row++, col--) {
            ImageView image1 = findViewById(ids[row][col]);
            ImageView image2 = findViewById(ids[row - 1][col + 1]);
            if ((!image1.getTag().equals("default")) && (!image2.getTag().equals("default"))) {
                if (image1.getTag().equals(image2.getTag())) {
                    //Log.i("message", "TRUE ==== here " + row + " " + col);
                    numOfMatchElements += 1;
                }
            }
        }
        if (numOfMatchElements == 5) {
            win = true;
        } else {
            win = false;
        }
        return win;
    }

}




