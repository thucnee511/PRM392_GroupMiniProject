package vn.edu.fpt.groupminiproject.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.fpt.groupminiproject.R;
import vn.edu.fpt.groupminiproject.models.Account;
import vn.edu.fpt.groupminiproject.models.Bet;

public class BetActivity extends AppCompatActivity {
    TextView txtBalance;
    TextView txtInGiraffe;
    TextView txtInLion;
    TextView txtInSquirrel;
    EditText edtGiraffeBet;
    EditText edtLionBet;
    EditText edtSquirrelBet;
    Button btnInGiraffe;
    Button btnInLion;
    Button btnInSquirrel;
    Button btnRemoveGiraffe;
    Button btnRemoveLion;
    Button btnRemoveSquirrel;
    Button btnBackToGame;

    private Account account;
    private Bet bet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bet);
        Mapping();
    }

    private void Mapping() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.account = (Account) bundle.getSerializable("account");
            this.bet = (Bet) bundle.getSerializable("bet");
        }
        txtBalance = (TextView) findViewById(R.id.txtBalance);
        txtInGiraffe = (TextView) findViewById(R.id.txtInGiraffe);
        txtInLion = (TextView) findViewById(R.id.txtInLion);
        txtInSquirrel = (TextView) findViewById(R.id.txtInSquirrel);
        edtGiraffeBet = (EditText) findViewById(R.id.edtGiraffeBet);
        edtLionBet = (EditText) findViewById(R.id.edtLionBet);
        edtSquirrelBet = (EditText) findViewById(R.id.edtSquirrelBet);
        btnInGiraffe = (Button) findViewById(R.id.btnInGiraffe);
        btnInLion = (Button) findViewById(R.id.btnInLion);
        btnInSquirrel = (Button) findViewById(R.id.btnInSquirrel);
        btnBackToGame = (Button) findViewById(R.id.btnBackToGame);
        btnRemoveGiraffe = (Button) findViewById(R.id.btnRemoveGiraffe);
        btnRemoveLion = (Button) findViewById(R.id.btnRemoveLion);
        btnRemoveSquirrel = (Button) findViewById(R.id.btnRemoveSquirrel);

        display();

        btnBackToGame.setOnClickListener(v -> backToGame());
        btnInGiraffe.setOnClickListener(v -> betInGiraffe());
        btnInLion.setOnClickListener(v -> betInLion());
        btnInSquirrel.setOnClickListener(v -> betInSquirrel());
        btnRemoveGiraffe.setOnClickListener(v -> removeBetInGiraffe());
        btnRemoveLion.setOnClickListener(v -> removeBetInLion());
        btnRemoveSquirrel.setOnClickListener(v -> removeBetInSquirrel());
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void display() {
        txtBalance.setText(account.getBalanceString() + " $");
        txtInGiraffe.setText(String.format("%,d", bet.getGiraffeBetAmount()) + " $");
        txtInLion.setText(String.format("%,d", bet.getLionBetAmount()) + " $");
        txtInSquirrel.setText(String.format("%,d", bet.getSquirrelBetAmount()) + " $");
    }

    private void backToGame() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, RacingActivity.class);
        if (bundle != null) {
            bundle.putSerializable("account", account);
            bundle.putSerializable("bet", bet);
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    private void betInGiraffe() {
        String betAmount = edtGiraffeBet.getText().toString();
        edtGiraffeBet.setText("");
        if (betAmount.isEmpty() || betAmount.isBlank()) {
            betAmount = "0";
        }
        int betAmountInt = Integer.parseInt(betAmount);
        if (betAmountInt > account.getBalance()) {
            Toast.makeText(this, "Not enough balance", Toast.LENGTH_SHORT).show();
            return;
        }
        int currentBet = bet.getGiraffeBetAmount();
        bet.setGiraffeBetAmount(betAmountInt);
        account.setBalance(account.getBalance() - betAmountInt + currentBet);
        display();
    }

    private void betInLion() {
        String betAmount = edtLionBet.getText().toString();
        edtLionBet.setText("");
        if (betAmount.isEmpty() || betAmount.isBlank()) {
            betAmount = "0";
        }
        int betAmountInt = Integer.parseInt(betAmount);
        if (betAmountInt > account.getBalance()) {
            Toast.makeText(this, "Not enough balance", Toast.LENGTH_SHORT).show();
            return;
        }
        int currentBet = bet.getLionBetAmount();
        bet.setLionBetAmount(betAmountInt);
        account.setBalance(account.getBalance() - betAmountInt + currentBet);
        display();
    }

    private void betInSquirrel() {
        String betAmount = edtSquirrelBet.getText().toString();
        edtSquirrelBet.setText("");
        if (betAmount.isEmpty() || betAmount.isBlank()) {
            betAmount = "0";
        }
        int betAmountInt = Integer.parseInt(betAmount);
        if (betAmountInt > account.getBalance()) {
            Toast.makeText(this, "Not enough balance", Toast.LENGTH_SHORT).show();
            return;
        }
        int currentBet = bet.getSquirrelBetAmount();
        bet.setSquirrelBetAmount(betAmountInt);
        account.setBalance(account.getBalance() - betAmountInt + currentBet);
        display();
    }

    private void removeBetInGiraffe() {
        int currentBet = bet.getGiraffeBetAmount();
        bet.setGiraffeBetAmount(0);
        account.setBalance(account.getBalance() + currentBet);
        display();
    }

    private void removeBetInLion() {
        int currentBet = bet.getLionBetAmount();
        bet.setLionBetAmount(0);
        account.setBalance(account.getBalance() + currentBet);
        display();
    }

    private void removeBetInSquirrel() {
        int currentBet = bet.getSquirrelBetAmount();
        bet.setSquirrelBetAmount(0);
        account.setBalance(account.getBalance() + currentBet);
        display();
    }

}