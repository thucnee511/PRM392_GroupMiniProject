package vn.edu.fpt.groupminiproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.fpt.groupminiproject.R;
import vn.edu.fpt.groupminiproject.models.Account;
import vn.edu.fpt.groupminiproject.repositories.AccountRepository;

public class LoginActivity extends AppCompatActivity {
    private final AccountRepository accountRepository = AccountRepository.getInstance();
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                Account account = accountRepository.checkLogin(username, password);
                if (account == null){
                    Toast.makeText(LoginActivity.this, "Wrong login credentials!", Toast.LENGTH_LONG).show();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("account", account);
                    Intent intent = new Intent(LoginActivity.this, RacingActivity.class);
                    intent.putExtras(bundle);
                    Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }
}