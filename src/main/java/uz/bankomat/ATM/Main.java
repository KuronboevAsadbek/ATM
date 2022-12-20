package uz.bankomat.ATM;

import org.json.simple.parser.ParseException;
import uz.bankomat.ATM.services.SelectMenu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        while (true) {
            SelectMenu selectMenu = new SelectMenu();
            selectMenu.miniService();
        }

    }
}