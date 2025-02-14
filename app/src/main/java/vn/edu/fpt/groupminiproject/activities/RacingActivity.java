package vn.edu.fpt.groupminiproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.fpt.groupminiproject.R;
import vn.edu.fpt.groupminiproject.models.Account;

public class RacingActivity extends AppCompatActivity {

    private TextView txtDisplayName;
    private TextView txtDisplayBalance;
    private Button btnStartGame;
    private Button btnReset;
    private Button btnBet;

    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_racing);
        Mapping();

    }

    private void Mapping() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.account = (Account) bundle.getSerializable("account");
        } else {
            Intent goToLogin = new Intent(this, LoginActivity.class);
            startActivity(goToLogin);
        }
        txtDisplayName = (TextView) findViewById(R.id.txtDisplayName);
        txtDisplayBalance = (TextView) findViewById(R.id.txtDisplayBalance);
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnBet = (Button) findViewById(R.id.btnBet);
        Display();
    }
    private void Display() {
        txtDisplayName.setText(account.getDisplayName());
        txtDisplayBalance.setText(account.getBalanceString() + " $");
    }
}