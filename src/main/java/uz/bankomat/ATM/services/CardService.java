package uz.bankomat.ATM.services;

import org.json.simple.parser.ParseException;
import uz.bankomat.ATM.cardDates.Card;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CardService {

    public  void cardBalance(Card selectedcard);
    public  void transfering(Card selectedcard, Card secondc) throws IOException, ParseException;
    public void cashing(Card selectedcard) throws IOException, ParseException;
    public void history(Card selectedcard) throws IOException, ParseException;

}
