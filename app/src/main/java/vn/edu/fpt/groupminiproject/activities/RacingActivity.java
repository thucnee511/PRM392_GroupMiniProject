package vn.edu.fpt.groupminiproject.activities;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
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
import vn.edu.fpt.groupminiproject.repositories.AnimalRepository;

public class RacingActivity extends AppCompatActivity {
    private AnimalRepository animalRepository;
    private TextView txtDisplayName;
    private TextView txtDisplayBalance;
    private Button btnStartGame;
    private Button btnReset;
    private Button btnBet;
    private SeekBar sbAnimalGiraffe;
    private SeekBar sbAnimalLion;
    private SeekBar sbAnimalSquirrel;
    private ImageView imgFinishGiraffe;
    private ImageView imgFinishLion;
    private ImageView imgFinishSquirrel;

    private Account account;
    private Bet bet;
    private boolean isEnded;
    private boolean isGameRunning;
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
            this.bet = (Bet) bundle.getSerializable("bet");
            if (bet == null) this.bet = new Bet();
        } else {
            Intent goToLogin = new Intent(this, LoginActivity.class);
            startActivity(goToLogin);
        }
        txtDisplayName = (TextView) findViewById(R.id.txtDisplayName);
        txtDisplayBalance = (TextView) findViewById(R.id.txtDisplayBalance);
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnBet = (Button) findViewById(R.id.btnBet);
        sbAnimalGiraffe = (SeekBar) findViewById(R.id.sbAnimal1);
        sbAnimalLion = (SeekBar) findViewById(R.id.sbAnimal2);
        sbAnimalSquirrel = (SeekBar) findViewById(R.id.sbAnimal3);
        imgFinishGiraffe = (ImageView) findViewById(R.id.imgFinishGiraffe);
        imgFinishLion = (ImageView) findViewById(R.id.imgFinishLion);
        imgFinishSquirrel = (ImageView) findViewById(R.id.imgFinishSquirrel);

        btnStartGame.setOnClickListener(v -> startGame());
        btnReset.setOnClickListener(v -> reset());
        btnBet.setOnClickListener(v -> bet());
        disableSeekBar();
        display();
    }
    @SuppressLint("SetTextI18n")
    private void display() {
        txtDisplayName.setText(account.getDisplayName());
        txtDisplayBalance.setText(account.getBalanceString() + " $");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void disableSeekBar() {
        //Make seekbar unable to change while touching
        sbAnimalGiraffe.setOnTouchListener((v, event) -> true);
        sbAnimalLion.setOnTouchListener((v, event) -> true);
        sbAnimalSquirrel.setOnTouchListener((v, event) -> true);
        animalRepository = AnimalRepository.getInstance(sbAnimalGiraffe, sbAnimalLion, sbAnimalSquirrel);
    }

    private void startGame() {
        if (isEnded) {
            Toast.makeText(this, "Game has ended please reset", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isGameRunning) {
            Toast.makeText(this, "Game is already running", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bet.getBetAmount() == 0) {
            Toast.makeText(this, "Please bet before start game", Toast.LENGTH_SHORT).show();
            return;
        }
        isGameRunning = true;
        int giraffeSpeed = animalRepository.getGiraffe().getRandomSpeed();
        int lionSpeed = animalRepository.getLion().getRandomSpeed();
        int squirrelSpeed = animalRepository.getSquirrel().getRandomSpeed();
        animateProgress(giraffeSpeed, findViewById(animalRepository.getGiraffe().getSeekBar().getId()));
        animateProgress(lionSpeed, findViewById(animalRepository.getLion().getSeekBar().getId()));
        animateProgress(squirrelSpeed, findViewById(animalRepository.getSquirrel().getSeekBar().getId()));
        long delayTime = Math.max(giraffeSpeed, Math.max(lionSpeed, squirrelSpeed)) * 1000L;
        new Handler().postDelayed(() -> {
            ImageView winner = null;
            ImageView loser1 = null;
            ImageView loser2 = null;
            String winnerName = "";
            if (giraffeSpeed <= lionSpeed && giraffeSpeed <= squirrelSpeed) {
                winnerName = "Giraffe";
                winner = imgFinishGiraffe;
                loser1 = imgFinishLion;
                loser2 = imgFinishSquirrel;
            } else if (lionSpeed <= giraffeSpeed && lionSpeed <= squirrelSpeed) {
                winnerName = "Lion";
                winner = imgFinishLion;
                loser1 = imgFinishGiraffe;
                loser2 = imgFinishSquirrel;
            } else {
                winnerName = "Squirrel";
                winner = imgFinishSquirrel;
                loser1 = imgFinishGiraffe;
                loser2 = imgFinishLion;
            }
            winner.setImageResource(R.drawable.ic_trophy);
            loser1.setImageResource(R.drawable.ic_bronze_medal);
            loser2.setImageResource(R.drawable.ic_bronze_medal);
            endGame(winnerName);
        }, delayTime);
    }

    private void animateProgress(int speed, SeekBar seekBar) {
        final ObjectAnimator animator = ObjectAnimator.ofInt(seekBar, "progress", 0, seekBar.getMax());
        animator.setDuration(speed * 1000L);
        animator.start();
    }

    private void endGame(String winnerName) {
        isGameRunning = false;
        isEnded = true;
        Toast.makeText(this, winnerName + " wins", Toast.LENGTH_SHORT).show();
        if (winnerName.equals("Giraffe")) {
            account.setBalance(account.getBalance() + bet.getGiraffeBetAmount() * 2);
        } else if (winnerName.equals("Lion")) {
            account.setBalance(account.getBalance() + bet.getLionBetAmount() * 2);
        } else {
            account.setBalance(account.getBalance() + bet.getSquirrelBetAmount() * 2);
        }
    }

    private void reset() {
        sbAnimalGiraffe.setProgress(0);
        sbAnimalLion.setProgress(0);
        sbAnimalSquirrel.setProgress(0);
        imgFinishGiraffe.setImageResource(R.drawable.flag);
        imgFinishLion.setImageResource(R.drawable.flag);
        imgFinishSquirrel.setImageResource(R.drawable.flag);
        bet = new Bet();
        isEnded = false;
    }

    private void bet() {
        if (isEnded) {
            Toast.makeText(this, "Game has ended please reset", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isGameRunning) {
            Toast.makeText(this, "Game is already running", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent goToBetIntent = new Intent(this, BetActivity.class);
        Bundle bundle = new Bundle();
        if (bundle != null) {
            bundle.putSerializable("bet",bet);
            bundle.putSerializable("account",account);
            goToBetIntent.putExtras(bundle);
        }
        startActivity(goToBetIntent);
    }
}