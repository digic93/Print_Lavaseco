/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billPrinter;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import printer.PrinterOptions;

/**
 *
 * @author Dell
 */
class Bill {

    private int printLine;
    private JSONObject billInfo;
    private JSONObject billData;
    private PrinterOptions print;

    public Bill(JSONObject billInfo, JSONObject billData, int printLine) {
        this.billInfo = billInfo;
        this.billData = billData;
        this.printLine = printLine;
        this.print = new PrinterOptions();
    }

    public void builderBill() {
        print.resetAll();
        print.initialize();
        print.feedBack((byte) 1);

        printBillHeader();

        print.newLine();
        print.setText((String) billInfo.get("head"));
        
        print.newLine();
        print.addLineSeperator(printLine);

        print.newLine();
        printBillData();

        print.addLineSeperator(printLine);

        printBillDetails();
        
        print.addLineSeperator(printLine);

        pritnFoot();
        
        print.newLine();
        print.setText((String) billInfo.get("foot"));
        print.feed((byte) 2);
        print.finit();

    }

    public byte[] getBytes() {
        return print.finalCommandSet().getBytes();
    }

    private void printBillHeader() {
        print.alignCenter();
        print.doubleHeight(true);
        print.doubleStrik(true);
        print.alignCenter();
        print.setText((String) billInfo.get("companyName"));
        print.doubleHeight(false);
        print.doubleStrik(false);
        print.newLine();
        print.newLine();
        print.alignCenter();
        print.setText((String) billInfo.get("fiscalId"));
        print.newLine();
        print.alignCenter();
        print.setText((String) billInfo.get("branchOffice"));
        print.newLine();
        print.alignCenter();
        print.setText((String) billInfo.get("address"));
        print.newLine();
        print.alignCenter();
        print.setText("Domicilios: " + (String) billInfo.get("phoneNumber"));
        print.newLine();
    }

    private void printBillData() {
        print.alignLeft();
        print.setText("Factura \t: " + (String) billData.get("id"));
        print.newLine();
        if (!((String) billData.get("customerName")).equals("")) {
            print.setText("Cliente \t: " + (String) billData.get("customerName"));
            print.newLine();
            print.setText("Telefono \t: " + (String) billData.get("customerPhone"));
            print.newLine();
            print.setText("Puntos \t: " + (long) billData.get("currentPoints"));
            print.newLine();
        }
        print.setText("Cajero \t: " + (String) billData.get("seller"));
        print.newLine();
        print.setText("PV \t: " + (String) billData.get("salePoint"));
        print.newLine();
        print.setText("Fecha \t: " + (String) billData.get("date"));
        print.newLine();
    }

    private void printBillDetails() {
        JSONArray msg = (JSONArray) billData.get("billDetails");
        Iterator<JSONObject> iterator = msg.iterator();
        while (iterator.hasNext()) {
            printBillDetail(iterator.next());
        }
        
        print.alignLeft();
        print.addLineSeperator(printLine);
        print.newLine();
        print.alignRight();
        print.setText("SubTotal: $" + (long) billData.get("subTotal"));
        print.setText("Puntos Redimidos: $" + (long) billData.get("discount"));
        print.newLine();
        print.alignLeft();
        print.addLineSeperator(printLine);
        print.newLine();
        print.alignRight();
        print.setText("Total: $" + (long) billData.get("total"));
        print.newLine();
    }

    private void printBillDetail(JSONObject detail) {
        long quantity = (long) detail.get("quantity");
        long value = Long.parseLong((String) detail.get("price"));
        long total = quantity * value;

        print.alignLeft();
        print.setText((String) detail.get("serviceName"));
        print.newLine();

        print.alignRight();
        print.setText(quantity + " unds");

        if (printLine == 50) {
            print.newLine();
            print.setText("$" + value + " c/u");
            print.newLine();
            print.setText("$" + total);

        } else {
            print.setText(" - $" + value + " c/u");
            print.setText(" - $" + total);
        }

        print.newLine();
        print.setText("Obs: " + (String) detail.get("observations"));

        if (!((String) detail.get("Descriptors")).equals("")) {
            print.newLine();
            print.setText((String) detail.get("Descriptors"));
        }
        print.newLine();
    }

    private void pritnFoot() {
        print.newLine();
        print.alignLeft();
 
        print.setText("Pago\t: " + (String) billData.get("billState"));
        print.newLine();
        print.setText("Acuerdo Pago\t: " + (String) billData.get("paymentAgreement"));
        print.newLine();
        if (!((String) billData.get("customerName")).equals("")) {
            print.setText("Puntos Obtenidos \t: " + (long) billData.get("pointsBySale"));
            print.newLine();
        }
        
        if (!((String) billData.get("observation")).equals("")) {
            print.setText("Puntos Obtenidos \t: " + (String) billData.get("observation"));
            print.newLine();
        }
    }

}
