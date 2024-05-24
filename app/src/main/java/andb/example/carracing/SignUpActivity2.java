package andb.example.carracing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity2 extends AppCompatActivity implements View.OnClickListener{

    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView tvAlreadyAccount;
    private Button btnSignUp;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount);
        btnSignUp = findViewById(R.id.btnSignUp);

        tvAlreadyAccount.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }
    private boolean checkInput(){
        if (TextUtils.isEmpty(etUsername.getText().toString())){
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())){
            etPassword.setError(REQUIRE);
            return false;
        }
        if (!TextUtils.equals(etPassword.getText().toString(),
                etConfirmPassword.getText().toString())){
            Toast.makeText(this, "Password are not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void signUp(){
        if (!checkInput())
            return;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void signInForm(){
        Intent intent = new Intent(this, SignInActivity2.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View v){
        if (v.getId() == R.id.btnSignUp)
            signUp();
        else if (v.getId() == R.id.tvAlreadyAccount)
            signInForm();
    }
}