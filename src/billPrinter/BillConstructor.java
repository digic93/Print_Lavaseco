/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billPrinter;

import ApiLavaseco.Client;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dell
 */
public class BillConstructor {

    private int printLine;
    private PrintBill print;
    private Client apiClient;

    private Timer timer;
    private TimerTask timerTask;

    public BillConstructor(int branchOfficeId, String printName, int printLine, int[] salePointIds, String url) {
        this.printLine = printLine;
        print = new PrintBill(printName);
        apiClient = new Client(branchOfficeId, salePointIds, url);
        timerTask = new TimerTask() {
            public void run() {
                try {
                    print();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 7000);
    }

    public void print() throws IOException, MalformedURLException, ParseException {
        JSONObject data = apiClient.getBills();
        JSONObject billInfo = (JSONObject) data.get("billInfo");
        JSONArray bills = (JSONArray) data.get("bills");
        JSONArray billsTikess = (JSONArray) data.get("billsTikes");
        
        if(bills != null){
            printBills(billInfo, bills);
        }
        if(billsTikess != null){
            printTikets(billInfo, billsTikess);
        }
    }

    private void printBills(JSONObject billInfo, JSONArray bills) {
        Iterator<JSONObject> iterator = bills.iterator();
        while (iterator.hasNext()) {
            Bill bill = new Bill(billInfo, iterator.next(), printLine);
            bill.builderBill();
            print.printBill(bill);
            print.printBill(bill);
        }
    }

    private void printTikets(JSONObject billInfo, JSONArray billsTikess) {
        Iterator<JSONObject> iterator = billsTikess.iterator();
        while (iterator.hasNext()) {
            Bill bill = new Bill(billInfo, iterator.next(), printLine);
            bill.builderBill();
            print.printBill(bill);
        }
    }
}
