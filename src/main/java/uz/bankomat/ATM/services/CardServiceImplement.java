package uz.bankomat.ATM.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uz.bankomat.ATM.cardDates.Card;
import uz.bankomat.ATM.cardDates.HistoryOfCashing;
import uz.bankomat.ATM.cardDates.Transfering;

import java.io.*;
import java.util.*;

public class CardServiceImplement implements CardService {
    //
    @Override
    public void cardBalance(Card selectedcard) {
        System.out.println("Your balance is " + selectedcard.getSum() + " sum");

    }

    @Override
    public void transfering(Card selectedcard, Card secondc) throws IOException, ParseException {
        File file = new File("cardFiles/HistoryOfTransfering.json");
        File file1 = new File("cardFiles/CardData.json");
        FileReader fileReader = new FileReader(file);
        FileReader fileReader1 = new FileReader(file1);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        JSONParser jsonParser = new JSONParser();
        if (!bufferedReader.ready()){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("[]");
            fileWriter.flush();
            fileWriter.close();
        }
        Object obj = jsonParser.parse(fileReader);
        JSONArray jsonArray = (JSONArray) obj;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("How much you want to transfer");
        Scanner scanner = new Scanner(System.in);
        double transfer=scanner.nextDouble();
        double percent = transfer*0.1;
        if (selectedcard.getSum()>(transfer+percent)){
            Date date = new Date();
            double moneyLeftout = selectedcard.getSum()-(percent+transfer);
            double afterIncoming = secondc.getSum()+transfer;
            Transfering transfering = new Transfering(selectedcard.getId(),  selectedcard.getCardNum(), selectedcard.getOwner(), secondc.getCardNum(), 0.0, secondc.getOwner(), transfer, moneyLeftout, date );
            jsonArray.add(transfering);
            Transfering transfering1 = new Transfering(secondc.getId(), selectedcard.getCardNum(), selectedcard.getOwner(), secondc.getCardNum(), transfer, secondc.getOwner(), transfer, afterIncoming, date );
            jsonArray.add(transfering1);
            selectedcard.setSum(moneyLeftout);
            secondc.setSum(afterIncoming);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(jsonArray));
            fileWriter.flush();
            Object obj1=jsonParser.parse(fileReader1);
            JSONArray jsonArray1 = (JSONArray) obj1;
            FileWriter fileWriter1 =new FileWriter(file1);
            jsonArray1.remove(selectedcard.getId()-1);
            jsonArray1.add(selectedcard.getId()-1,selectedcard);
            jsonArray1.remove(secondc.getId()-1);
            jsonArray1.add(secondc.getId()-1, secondc);
            fileWriter1.write(gson.toJson(jsonArray1));
            fileWriter1.close();

        }



    }

    @Override
    public void cashing(Card selectedcard) throws IOException, ParseException {
        File file = new File("cardFiles/HistoryOfCashing.json");
        File file1 = new File("cardFiles/CardData.json");

        FileReader fileReader = new FileReader(file);
        FileReader fileReader1 = new FileReader(file1);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        JSONParser jsonParser = new JSONParser();
        if (!bufferedReader.ready()){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("[]");
            fileWriter.flush();
            fileWriter.close();
        }
        Object obj = jsonParser.parse(fileReader);
        JSONArray jsonArray = (JSONArray) obj;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Scanner scanner = new Scanner(System.in);
        System.out.println("How much you want to cash?");
        double cashing = scanner.nextDouble();
        double percent = cashing*0.01;
        if (selectedcard.getSum()>(cashing+percent)){
            Date date = new Date();

            double moneyLeft =selectedcard.getSum()-(cashing+percent);
            System.out.println("Cashed money " + cashing + " sum" + "\n Money Left " + moneyLeft);
            HistoryOfCashing historyOfCashing =new HistoryOfCashing(selectedcard.getId(), selectedcard.getCardNum(), selectedcard.getOwner(), cashing, moneyLeft, date);
            selectedcard.setSum(moneyLeft);
            Object object = jsonParser.parse(fileReader1);
            JSONArray jsonArray1 = (JSONArray) object;

            FileWriter fileWriter = new FileWriter(file);
            FileWriter fileWriter1 = new FileWriter(file1);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);

            jsonArray1.remove(selectedcard.getId()-1);
            jsonArray1.add(selectedcard.getId()-1, selectedcard);

            bufferedWriter1.write(gson.toJson(jsonArray1));
            bufferedWriter1.flush();

            jsonArray.add(historyOfCashing);

            bufferedWriter.write(gson.toJson(jsonArray));
            bufferedWriter.flush();
            bufferedWriter.close();

        }
        else System.out.println("Not enough money to cash");


    }
    @Override
    public void history(Card selectedcard) throws IOException, ParseException {
        long id = selectedcard.getId();
        File file = new File("cardFiles/HistoryOfTransfering.json");
        File file1 = new File("cardFiles/HistoryOfCashing.json");
        FileReader fileReader = new FileReader(file);
        FileReader fileReader1 = new FileReader(file1);
        if (fileReader.ready()) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileReader);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject;
            System.out.println("Here Is Transfering History");
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                if (jsonObject.get("id").equals(id)) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Transfering transfering = gson.fromJson(String.valueOf(jsonObject), Transfering.class);
                    System.out.println("Sender " + transfering.getSenderOwner() + " SenderCardNum " + transfering.getSenderCardnum() + " TransferedSum " + transfering.getTransferedSum() + " IncomingSum " + transfering.getIncomingSum() + " RecieverOwner " + transfering.getIncomeOwner() + " RecieverCardNum " + transfering.getIncomCardNum() + " MoneyLeft " + transfering.getMoneyLeft() + " Date " + transfering.getDate());
                }
            }
            System.out.println();
        }
        if (fileReader1.ready()) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileReader1);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject;
            System.out.println("Here is Cashing History");
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                if (jsonObject.get("id").equals(id)) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    HistoryOfCashing historyOfCashing = gson.fromJson(String.valueOf(jsonObject), HistoryOfCashing.class);
                    System.out.println(" CardOwner " + historyOfCashing.getSenderCardnum()  + " Cashed " + historyOfCashing.getTransferedSum() + " MoneyLeft " + historyOfCashing.getMoneyLeft() + " Date " + historyOfCashing.getDate());
                }

            }

        }
    }
}
