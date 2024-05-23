package andb.example.carracing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView winnerText;
    private TextView secondWinnerText;
    private TextView profitText;
    private TextView moneyLeftText;
    private SharedPreferences sharedPreferences;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winnerText = findViewById(R.id.winnerText);
        secondWinnerText = findViewById(R.id.secondWinnerText);
        profitText = findViewById(R.id.profitText);
        moneyLeftText = findViewById(R.id.moneyLeftText);

        sharedPreferences = getSharedPreferences("ResultData", Context.MODE_PRIVATE);

        // Load result data from SharedPreferences
        loadResultData();
    }

    private void loadResultData() {
        String winner = sharedPreferences.getString("winner", "");
        String secondWinner = sharedPreferences.getString("secondWinner", "");
        double profit = sharedPreferences.getFloat("profit", 0.0f);
        double moneyLeft = sharedPreferences.getFloat("moneyLeft", 0.0f);

        // Display loaded result data
        winnerText.setText(winner);
        secondWinnerText.setText(secondWinner);
        profitText.setText(String.valueOf(profit));
        moneyLeftText.setText(String.valueOf(moneyLeft));

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload result data when activity is resumed
        loadResultData();
    }

/*
    private TextView winnerText;
    private TextView secondWinnerText;
    private TextView profitText;
    private TextView moneyLeftText;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize Views
        winnerText = findViewById(R.id.winnerText);
        secondWinnerText = findViewById(R.id.secondWinnerText);
        profitText = findViewById(R.id.profitText);
        moneyLeftText = findViewById(R.id.moneyLeftText);
        btnBack = findViewById(R.id.btnBack);

        // Get Data from Intent
        Intent intent = getIntent();
        String winner = intent.getStringExtra("winner");
        String secondWinner = intent.getStringExtra("secondWinner");
        double profit = intent.getDoubleExtra("profit", 0.0);
        double moneyLeft = intent.getDoubleExtra("moneyLeft", 0.0);

        // Set Data to Views
        winnerText.setText(winner);
        secondWinnerText.setText(secondWinner);
        profitText.setText(String.valueOf(profit));
        moneyLeftText.setText(String.valueOf(moneyLeft));

        // Handle Back Button Click
        btnBack.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("moneyLeft", moneyLeft);
            setResult(RESULT_OK, resultIntent);
            finish();
        });*/
    }
