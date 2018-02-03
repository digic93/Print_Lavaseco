/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirnt.lavaseco;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;
import printer.PrintJobWatcher;
import printer.PrinterOptions;

/**
 *
 * @author Dell
 */
public class PirntLavaseco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        print();
    }
    
    public static void print() {
        PrinterOptions p = new PrinterOptions();

        p.resetAll();
        p.initialize();
        p.feedBack((byte) 1);
        p.alignCenter();
//        p.doubleHeight(true);
//        p.doubleStrik(true);
//        p.alignCenter();
//        p.setText("Lavaseco Modelo");
//        p.doubleHeight(false);
//        p.doubleStrik(false);
//        p.newLine();
//        p.newLine();
//        p.alignCenter();
//        p.setText("Nit 519318156");
//        p.newLine();
//        p.alignCenter();
//        p.setText("Domicilios 6301948 - 3157981066");
//        p.newLine();
//        p.addLineSeperator();
//        p.newLine();
//
//        p.alignLeft();
//        p.setText("POD No \t: 2001 \tTable \t: E511");
//        p.newLine();
//
//        p.setText("Res Date \t: " + "01/01/1801 22:59");
//
//        p.newLine();
//        p.setText("Session \t: Evening Session");
//        p.newLine();
//        p.setText("Staff \t: Bum Dale");
//        p.newLine();
//        p.addLineSeperator();
//        p.newLine();
//        p.alignCenter();
//        p.setText(" - Some Items - ");
//        p.newLine();
//        p.alignLeft();
//        p.addLineSeperator();
        p.newLine();
        p.printQR("testLavaseco modedo jummm", 50 , 10);
//        p.addLineSeperator();
        p.newLine();
//
//        p.setText("No \tItem\t\tUnit\tQty");
//        p.newLine();
//        p.addLineSeperator();
//        p.setText("1" + "\t" + "Aliens Everywhere" + "\n" + "Rats" + "\t" + "500");
//        p.doubleHeight(true);
//        p.setText("1" + "\t" + "Aliens Everywhere" + "\n" + "Rats" + "\t" + "500");
//        p.chooseFont(2);
//        p.setText("1" + "\t" + "Aliens Everywhere" + "\n" + "Rats" + "\t" + "500");
//        p.addLineSeperator();
        p.feed((byte) 2);
        p.finit();

        feedPrinter(p.finalCommandSet().getBytes());
    }
    
    private static boolean feedPrinter(byte[] b) {
        try {
            AttributeSet attrSet = new HashPrintServiceAttributeSet(new PrinterName("Generic / Text Only", null)); //EPSON TM-U220 ReceiptE4

            DocPrintJob job = PrintServiceLookup.lookupPrintServices(null, attrSet)[0].createPrintJob();
            //PrintServiceLookup.lookupDefaultPrintService().createPrintJob();  

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(b, flavor, null);
            PrintJobWatcher pjDone = new PrintJobWatcher(job);

            job.print(doc, null);
            pjDone.waitForDone();
            System.out.println("Done !");
        } catch (javax.print.PrintException pex) {
            System.out.println("Printer Error " + pex.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
