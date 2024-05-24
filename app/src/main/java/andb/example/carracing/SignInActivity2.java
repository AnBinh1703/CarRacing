package andb.example.carracing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignInActivity2 extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvNoAccount;
    private Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in2);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvNoAccount = findViewById(R.id.tvNotAccountYet);
        btnSignin = findViewById(R.id.btnSignIn);

        tvNoAccount.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
    }
    private boolean checkInput(){
        String REQUIRE = "Require";
        if (TextUtils.isEmpty(etUsername.getText().toString())){
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError(REQUIRE);
            return false;
        }
        return true;
    }
    private void signIn(){
        if(!checkInput()){
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void signUpForm() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSignIn)
            signIn();
        else if (v.getId() == R.id.tvNotAccountYet)
            signUpForm();
    }
}