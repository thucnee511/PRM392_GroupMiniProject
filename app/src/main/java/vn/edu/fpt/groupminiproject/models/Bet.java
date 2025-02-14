package vn.edu.fpt.groupminiproject.models;

import java.io.Serializable;

public class Bet implements Serializable {
    private int betAmount;
    private int giraffeBetAmount;
    private int lionBetAmount;
    private int squirrelBetAmount;

    public Bet() {
        this.betAmount = 0;
        this.giraffeBetAmount = 0;
        this.lionBetAmount = 0;
        this.squirrelBetAmount = 0;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public int getGiraffeBetAmount() {
        return giraffeBetAmount;
    }

    public void setGiraffeBetAmount(int giraffeBetAmount) {
        this.giraffeBetAmount = giraffeBetAmount;
        updateBetAmount();
    }

    public int getLionBetAmount() {
        return lionBetAmount;
    }

    public void setLionBetAmount(int lionBetAmount) {
        this.lionBetAmount = lionBetAmount;
        updateBetAmount();
    }

    public int getSquirrelBetAmount() {
        return squirrelBetAmount;
    }

    public void setSquirrelBetAmount(int squirrelBetAmount) {
        this.squirrelBetAmount = squirrelBetAmount;
        updateBetAmount();
    }

    private void updateBetAmount() {
        this.betAmount = this.giraffeBetAmount + this.lionBetAmount + this.squirrelBetAmount;
    }
}
