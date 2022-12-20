package uz.bankomat.ATM.cardDates;

import java.util.Date;

public class Transfering {
    private int id;
    private String senderCardnum;
    private String senderOwner;
    private String incomCardNum;
    private String incomeOwner;
    private Double incomingSum;
    private Double transferedSum;
    private Double moneyLeft;
    private Date date;

    public Transfering(int id, String senderCardnum, String senderOwner, String incomCardNum, Double incomingSum, String incomeOwner,  Double transferedSum,  Double moneyLeft, Date date) {

        this.id = id;
        this.senderCardnum = senderCardnum;
        this.senderOwner = senderOwner;
        this.incomCardNum = incomCardNum;
        this.incomingSum = incomingSum;
        this.incomeOwner = incomeOwner;
        this.transferedSum = transferedSum;
        this.moneyLeft = moneyLeft;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIncomCardNum() {
        return incomCardNum;
    }

    public void setIncomCardNum(String incomCardNum) {
        this.incomCardNum = incomCardNum;
    }

    public String getIncomeOwner() {
        return incomeOwner;
    }

    public void setIncomeOwner(String incomeOwner) {
        this.incomeOwner = incomeOwner;
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

    @Override
    public String toString() {
        return "Transfering{" +
                "id=" + id +
                ", senderCardnum='" + senderCardnum + '\'' +
                ", senderOwner='" + senderOwner + '\'' +
                ", incomCardNum='" + incomCardNum + '\'' +
                ", incomeOwner='" + incomeOwner + '\'' +
                ", incomingSum=" + incomingSum +
                ", transferedSum=" + transferedSum +
                ", moneyLeft=" + moneyLeft +
                ", date=" + date +
                '}';
    }
}
