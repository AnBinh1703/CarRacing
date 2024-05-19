package andb.example.carracing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //khai báo
    final List<Handler> handlerList = new ArrayList<>();
    final List<Runnable> runnableList = new ArrayList<>();

    private SeekBar sbContent1;
    private SeekBar sbContent2;
    private Button btnStart;
    private Button btnRefresh;
    private Button btnReset;
    private SeekBar sbContent3;
    private TextView txtFinish;
    private CheckBox cbCar1;
    private CheckBox cbCar2;
    private CheckBox cbCar3;
    private TextView moneyResult;
    private TextView profitValue;
    private TextView betValue;
    private TextView duck1Speed;
    private TextView duck2Speed;
    private TextView duck3Speed;
    private TextView moneyLeftTotal;
    private EditText bet1;
    private EditText bet2;
    private EditText bet3;

    int process = 0;
    int winner = -1;
    int secondWinner = -1;
    double profitTotal = 0.0;
    double moneyLeft = 0.0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        sbContent1 = (SeekBar) findViewById(R.id.seekBar);
        sbContent2 = (SeekBar) findViewById(R.id.seekBar2);
        sbContent3 = (SeekBar) findViewById(R.id.seekBar3);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        txtFinish = (TextView) findViewById(R.id.finish);
        cbCar1 = (CheckBox) findViewById(R.id.checkBox1);
        cbCar2 = (CheckBox) findViewById(R.id.checkBox2);
        cbCar3 = (CheckBox) findViewById(R.id.checkBox3);
        moneyResult = (TextView) findViewById(R.id.moneyResult);
        profitValue = (TextView) findViewById(R.id.profitValue);
        betValue = (TextView) findViewById(R.id.betValue);
        duck1Speed = (TextView) findViewById(R.id.car1m);
        duck2Speed = (TextView) findViewById(R.id.car2m);
        duck3Speed = (TextView) findViewById(R.id.car3m);
        bet1 = (EditText) findViewById(R.id.betCar1);
        bet2 = (EditText) findViewById(R.id.betCar2);
        bet3 = (EditText) findViewById(R.id.betCar3);
        moneyLeftTotal = (TextView) findViewById(R.id.moneyLeft);
        btnReset = (Button) findViewById(R.id.reset);


        //arraylist store seekbars
        final SeekBar[] seekBars = new SeekBar[]{sbContent1, sbContent2, sbContent3};
        //arraylist store speed
        final TextView[] textSpeed = new TextView[]{duck1Speed, duck2Speed, duck3Speed};
        //add to list boolean to find who is finnish first
        final List<Boolean> finished = new ArrayList<>(Collections.nCopies(seekBars.length, false));
        //run random for seekBar and find the winner
        for (int i = 0; i < seekBars.length; i++) {
            int finalI = i;
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    btnStart.setVisibility(View.INVISIBLE);
                    Random rd = new Random();
                    int rdRace = rd.nextInt((int) (Math.random() * 15) + 1);
                    seekBars[finalI].setProgress(process);
//                    process += (int) (Math.random() + rdRace/10+1);
                    process += (int) (rdRace);
                    if (process <= 100) {
                        handler.postDelayed(this, 1000);
                    } else {
                        btnRefresh.setVisibility(View.VISIBLE);
                        finished.set(finalI, true);
                        if (winner == -1) {
                            winner = finalI;
                        } else if (secondWinner == -1) {
                            secondWinner = finalI;
                        }
                        if (!finished.contains(false)) {
                            //display the winner and the second, can add pop up or sound here
                            double betNum1 = bet1.getText().toString().isEmpty() ? 0 : Double.parseDouble(bet1.getText().toString());
                            double betNum2 = bet2.getText().toString().isEmpty() ? 0 : Double.parseDouble(bet2.getText().toString());
                            double betNum3 = bet3.getText().toString().isEmpty() ? 0 : Double.parseDouble(bet3.getText().toString());
                            double betValueTotal = Double.parseDouble(betValue.getText().toString());
                            double profit = 0.0;
                            if (winner + 1 == 1) {
                                profitTotal = betNum1 * 2;
                                if (secondWinner + 1 == 2) {
                                    profitTotal += betNum2 * 1.5;
                                    txtFinish.setText("The winner is Orange and the second place is Blue");
                                    profit = profitTotal - betValueTotal;
                                }
                                if (secondWinner + 1 == 3) {
                                    profitTotal += betNum3 * 1.5;
                                    txtFinish.setText("The winner is Orange and the second place is White");
                                    profit = profitTotal - betValueTotal;
                                }

                                profitValue.setText(profit + "");

                                double total = Double.parseDouble(moneyLeftTotal.getText().toString()) + profitTotal;
                                moneyResult.setText(total + "");
                            }
                            if (winner + 1 == 2) {
                                profitTotal = betNum2 * 2;
                                if (secondWinner + 1 == 1) {
                                    profitTotal += betNum1 * 1.5;
                                    txtFinish.setText("The winner is Blue and the second place is Orange");
                                    profit = profitTotal - betValueTotal;
                                }
                                if (secondWinner + 1 == 3) {
                                    profitTotal += betNum3 * 1.5;
                                    txtFinish.setText("The winner is Blue and the second place is White");
                                    profit = profitTotal - betValueTotal;
                                }
                                profitValue.setText(profit + "");
//                                double total = moneyLeft + profitTotal;
                                double total = Double.parseDouble(moneyLeftTotal.getText().toString()) + profitTotal;
                                moneyResult.setText(total + "");
                            }
                            if (winner + 1 == 3) {
                                profitTotal = betNum3 * 2;
                                if (secondWinner + 1 == 1) {
                                    profitTotal += betNum1 * 1.5;
                                    txtFinish.setText("The winner is White and the second place is Orange");
                                    profit = profitTotal - betValueTotal;
                                }
                                if (secondWinner + 1 == 2) {
                                    profitTotal += betNum2 * 1.5;
                                    txtFinish.setText("The winner is White and the second place is Blue123");
                                    profit = profitTotal - betValueTotal;
                                }
                                profitValue.setText(profit + "");
//                                double total = moneyLeft+ profitTotal;
                                double total = Double.parseDouble(moneyLeftTotal.getText().toString()) + profitTotal;
                                moneyResult.setText(total + "");
                            }
                        }
                    }
                }
            };
            handlerList.add(handler);
            runnableList.add(runnable);
        }

        //handle random speed
        for (int i = 0; i < seekBars.length; i++) {
            int index = i;
            seekBars[index].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    textSpeed[index].setText(String.valueOf(progress) + " cm/min");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        //handle open class edit text
        cbCar1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bet1.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
                bet1.setText("");
            }
        });
        cbCar2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bet2.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
                bet2.setText("");
            }
        });
        cbCar3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bet3.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
                bet3.setText("");
            }
        });

        //handle start game
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle Bet value
                double betValue1 = 0.0;
                double betValue2 = 0.0;
                double betValue3 = 0.0;
                double totalBet;
                if (!bet1.getText().toString().isEmpty()) {
                    betValue1 = Double.parseDouble(bet1.getText().toString());
                }
                if (!bet2.getText().toString().isEmpty()) {
                    betValue2 = Double.parseDouble(bet2.getText().toString());
                }
                if (!bet3.getText().toString().isEmpty()) {
                    betValue3 = Double.parseDouble(bet3.getText().toString());
                }

                double moneyTotal = Double.parseDouble(moneyResult.getText().toString());


//
                if (betValue1 != 0.0 && betValue2 == 0.0 && betValue3 == 0) {
//                    Log.println(Log.INFO,"check", "1 "+ moneyTotal +" "+ betValue1 + " ");
                    handleRun(moneyTotal, betValue1);
                }
                if (betValue2 != 0.0 && betValue1 == 0.0 && betValue3 == 0) {
//                    Log.println(Log.INFO,"check", "2 "+ moneyTotal +" "+ betValue2 + " ");
                    handleRun(moneyTotal, betValue2);
                }
                if (betValue3 != 0.0 && betValue1 == 0.0 && betValue2 == 0) {
//                    Log.println(Log.INFO,"check", "3 "+ moneyTotal +" "+ betValue3 + " ");
                    handleRun(moneyTotal, betValue3);
                }
                //
                if (betValue1 != 0.0 && betValue2 != 0.0 && betValue3 == 0.0) {
                    totalBet = betValue1 + betValue2;
//                    Log.println(Log.INFO,"check", "1 2 "+ moneyTotal +" "+ totalBet+ " ");
                    handleRun(moneyTotal, totalBet);
                    return;
                }

                if ((betValue1 != 0.0 && betValue3 != 0.0 && betValue2 == 0.0)) {
                    totalBet = betValue1 + betValue3;
//                    Log.println(Log.INFO,"check", "1 3 "+ moneyTotal +" "+ totalBet+ " ");
                    handleRun(moneyTotal, totalBet);
                    return;
                }

                if ((betValue2 != 0.0 && betValue3 != 0.0 && betValue1 == 0.0)) {
                    totalBet = betValue2 + betValue3;
//                    Log.println(Log.INFO,"check", "3 2 "+ moneyTotal +" "+ totalBet+ " ");
                    handleRun(moneyTotal, totalBet);
                    return;
                }

                if (betValue1 != 0 && betValue2 != 0 && betValue3 != 0) {
                    totalBet = betValue1 + betValue2 + betValue3;
//                    Log.println(Log.INFO,"check", "1 2 3 "+ moneyTotal +" "+ totalBet+ " ");
                    handleRun(moneyTotal, totalBet);
                    return;
                }
            }
        });

        //handle refresh the game
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setVisibility(View.VISIBLE);
                btnRefresh.setVisibility(View.INVISIBLE);
                profitValue.setText(0.0 + "");
                process = 0;
                winner = -1;
                secondWinner = -1;
                sbContent1.setProgress(0);
                sbContent2.setProgress(0);
                sbContent3.setProgress(0);
                bet1.setText("");
                bet2.setText("");
                bet3.setText("");
                betValue.setText(0.0 + "");
                cbCar1.setChecked(false);
                cbCar2.setChecked(false);
                cbCar3.setChecked(false);
                txtFinish.setText("");
                double allMoney = Double.parseDouble(moneyResult.getText().toString());
                if (allMoney <= 1.0) {
                    btnReset.setVisibility(View.VISIBLE);
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReset.setVisibility(View.INVISIBLE);
                btnStart.setVisibility(View.VISIBLE);
                profitValue.setText(0.0 + "");
                process = 0;
                winner = -1;
                secondWinner = -1;
                sbContent1.setProgress(0);
                sbContent2.setProgress(0);
                sbContent3.setProgress(0);
                bet1.setText("");
                bet2.setText("");
                bet3.setText("");
                betValue.setText(0.0 + "");
                cbCar1.setChecked(false);
                cbCar2.setChecked(false);
                cbCar3.setChecked(false);
                txtFinish.setText("");
                moneyResult.setText(100.0 + "");
            }
        });


    }

    // handle random
    public void handleRun(double moneyTotal, double totalBet) {
        moneyLeft = moneyTotal - totalBet;
        if (moneyLeft < 0) {
            Log.println(Log.INFO, "check", "less " + moneyTotal + " " + totalBet + " " + moneyLeft);
            Toast.makeText(MainActivity.this, "Your money is not enough to bet", Toast.LENGTH_LONG).show();
        } else {
            Log.println(Log.INFO, "check error ", "height" + moneyTotal + " " + totalBet + " " + moneyLeft);
            for (int i = 0; i < handlerList.size(); i++) {
                handlerList.get(i).post(runnableList.get(i));
            }
            betValue.setText(totalBet + "");
            moneyResult.setText(moneyLeft + "");
            moneyLeftTotal.setText(moneyLeft + "");
        }

    }


    @Override
    public void onClick(View v) {
    }
}