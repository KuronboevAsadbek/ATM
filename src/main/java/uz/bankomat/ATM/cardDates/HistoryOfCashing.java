package uz.bankomat.ATM.cardDates;

import java.util.Date;

public class HistoryOfCashing {

    private int id;

    private String cardNum;
    private String senderCardnum;
    private String senderOwner;
    private String owner;
    private String incomCardNum;
    private String incomeOwner;
    private Double incomingSum;
    private Double transferedSum;
    private Double cashed;
    private Double moneyLeft;
    private Date date;

      public HistoryOfCashing() {
    }


    public HistoryOfCashing(int id, String senderCardnum, String senderOwner, Double transferedSum, Double moneyLeft, Date date) {
        this.id=id;
        this.senderCardnum = senderCardnum;
        this.senderOwner = senderOwner;
        this.transferedSum = transferedSum;
        this.moneyLeft = moneyLeft;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getIncomeOwner() {
        return incomeOwner;
    }

    public void setIncomeOwner(String incomeOwner) {
        this.incomeOwner = incomeOwner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getCashed() {
        return cashed;
    }

    public void setCashed(Double cashed) {
        this.cashed = cashed;
    }

    public Double getMoneyLeft() {
        return moneyLeft;
    }

    public void setMoneyLeft(Double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderCardnum() {
        return senderCardnum;
    }

    public void setSenderCardnum(String senderCardnum) {
        this.senderCardnum = senderCardnum;
    }

    public String getSenderOwner() {
        return senderOwner;
    }

    public void setSenderOwner(String senderOwner) {
        this.senderOwner = senderOwner;
    }

    public Double getIncomingSum() {
        return incomingSum;
    }

    public void setIncomingSum(Double incomingSum) {
        this.incomingSum = incomingSum;
    }

    public Double getTransferedSum() {
        return transferedSum;
    }

    public void setTransferedSum(Double transferedSum) {
        this.transferedSum = transferedSum;
    }

    public String getIncomCardNum() {
        return incomCardNum;
    }

    public void setIncomCardNum(String incomCardNum) {
        this.incomCardNum = incomCardNum;
    }

    @Override
    public String toString() {
        return "HistoryOfCashing{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                ", senderCardnum='" + senderCardnum + '\'' +
                ", senderOwner='" + senderOwner + '\'' +
                ", owner='" + owner + '\'' +
                ", incomCardNum='" + incomCardNum + '\'' +
                ", incomingSum=" + incomingSum +
                ", transferedSum=" + transferedSum +
                ", cashed=" + cashed +
                ", moneyLeft=" + moneyLeft +
                ", date=" + date +
                '}';
    }

}
