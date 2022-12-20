package uz.bankomat.ATM.cardDates;

import java.util.List;

public class Card {
    private int id;
    private String owner;
    private String cardNum;
    private double sum;
    private Boolean state;
    private CardType type;
    private String pin;

    public Card(int id, String owner, String cardNum, Double sum, Boolean state, CardType type, String pin) {
        this.id = id;
        this.owner = owner;
        this.cardNum=cardNum;
        this.sum = sum;
        this.state = state;
        this.type = type;
        this.pin=pin;

    }

    public Card() {

    }

    public Card(String cardNum, String owner, double sum, String type, String pin, boolean state) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", sum='" + sum + '\'' +
                ", state=" + state +
                ", type='" + type + '\'' +
                ", pin=" + pin +
                '}';
    }
}
