package uz.bankomat.ATM.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uz.bankomat.ATM.cardDates.Card;
import uz.bankomat.ATM.cardDates.CardType;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public  class SelectMenu {
    //
    Scanner scanner = new Scanner(System.in);

    public void miniService() throws IOException, ParseException {

        System.out.println("If have a card please write the card number else write 0");
        String choosing = scanner.nextLine();
        if (choosing.equals("0")) {
            System.out.println("Creating a new Card");
            cratingCard();
        } else if (choosing.length() == 16) {
            Card selectedcard = (getCard(choosing));
            if (selectedcard != null) {
                if (checkPin(selectedcard)==null){
                    return;
                }

                CardServiceImplement cardServiceImplement = new CardServiceImplement();
                while (true){
                System.out.println("What do you want \n 1: Show Balance \n 2: Transfering \n 3: Cashing \n 4: History");
                switch (scanner.nextInt()) {
                    case 1: {
                        cardServiceImplement.cardBalance(selectedcard);
                        break;
                    }
                    case 2: {
                        if (secondCard()!=null){
                            Card secondc = secondCard();
                            cardServiceImplement.transfering(selectedcard, secondc);
                        }else {
                            System.out.println("Recievers cars is blocked or not found");
                        }
                        break;
                    }
                    case 3: {
                        cardServiceImplement.cashing(selectedcard);

                        break;
                    }
                    case 4: {
                        cardServiceImplement.history(selectedcard);
                        break;
                    }
                }
                }

            } else System.out.println("card is not found");
        } else {
            System.out.println("error...!!!");
        }
    }

    public void cratingCard() throws IOException, ParseException {

        System.out.println("Write your name please");
        String cardOwner = scanner.nextLine();
        System.out.println("Choose the type of card \n 1: HUMO \n 2: UZCARD");

        switch (scanner.nextInt()) {
            case 1: {
                chooseCard("9860", cardOwner, CardType.HUMO);
                break;
            }
            case 2: {
                chooseCard("8600", cardOwner, CardType.UZCARD);
                break;
            }
            default:
        }

    }

    public void chooseCard(String cardNumber, String cardOwner, CardType cardType) throws IOException, ParseException {

        File file = new File("cardFiles/CardData.json");
        FileReader inputStreamReader = new FileReader(file);

        JSONParser jsonParser = new JSONParser();
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        String newCard;
        if (!inputStreamReader.ready()) {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("[]");
            fileWriter.flush();
            fileWriter.close();
        }
        Object obj = jsonParser.parse(inputStreamReader);
        JSONArray jsonArray = (JSONArray) obj;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("Write PIN code");
        Scanner scanner1 = new Scanner(System.in);
        String pin = scanner1.nextLine();
        newCard = cardNumber + (random.ints(min, max).findFirst().getAsInt() + "" + (random.ints(min, max).findFirst().getAsInt() + "" + (random.ints(min, max).findFirst().getAsInt())));
        Card card = new Card(jsonArray.size() + 1, cardOwner, newCard, 0.0, true, cardType, pin);
        jsonArray.add(card);
        File file1 = new File("cardFiles/CardData.json");
        OutputStreamWriter outputStreamWriter = new FileWriter(file1);
        outputStreamWriter.write(gson.toJson(jsonArray));
        outputStreamWriter.flush();

        System.out.println("Card created congratulations!!! " + gson.toJson(card));
    }

    public Card getCard(String choosing) throws IOException, ParseException {
        Card card = null;
        File file = new File("cardFiles/CardData.json");
        FileReader fileReader = new FileReader(file);
        if (fileReader.ready()) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileReader);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject;
            for (Object o : jsonArray) {
                jsonObject = (JSONObject) o;
                if (jsonObject.get("cardNum").equals(choosing)) {
                    Gson gson = new Gson();
                    card = gson.fromJson(String.valueOf(jsonObject), Card.class);
                    return card;
                }
            }
        } else {
            return null;
        }

        return card;
    }

    public Card checkPin(Card selectedcard) throws IOException, ParseException {
            File file = new File("cardFiles/CardData.json");
            FileReader fileReader = new FileReader(file);
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(fileReader);

            Gson gson = new  GsonBuilder().setPrettyPrinting().create();

            Scanner scanner = new Scanner(System.in);
            if (selectedcard.getState()) {
                for (int i = 0; i <3; i++) {
                    System.out.println("write the PIN");
                    String pin = scanner.nextLine();
                    if (selectedcard.getPin().equals(pin)) {
                        return selectedcard;
                        } else
                            System.out.println("Wrong PIN");
                    if (i==2){
                        selectedcard.setState(false);
                        FileWriter fileWriter = new FileWriter(file);
                        JSONArray jsonArray = (JSONArray) object;
                        jsonArray.remove(selectedcard.getId()-1);
                        jsonArray.add(selectedcard.getId()-1, selectedcard);
                        fileWriter.write(gson.toJson(jsonArray));
                        fileWriter.flush();
                        fileWriter.close();
                        System.out.println("Your card is blocked!");
                    }
                }

            }
            else { System.out.println("Card is not working or blocked");

            }

        return null;
    }

    public Card secondCard() throws IOException, ParseException {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Which card do you want to transfer");
        String second = scanner1.nextLine();
        Card card1 = null;
        File file = new File("cardFiles/CardData.json");
        FileReader fileReader = new FileReader(file);
        if (fileReader.ready()){
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject jsonObject;
        for (int i = 0; i <jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get("cardNum").equals(second)) {
                Gson gson = new Gson();
                card1 = gson.fromJson(String.valueOf(jsonObject), Card.class);
                if (card1.getState()){
                    return card1;
                }
            }
        }
        }
        return null;
    }
}
