/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billPrinter;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JOptionPane;
import printer.PrintJobWatcher;

/**
 *
 * @author Dell
 */
public class PrintBill {
    private String printName;

    public PrintBill(String PrintName) {
        this.printName = PrintName;
    }
   
    public void printBill(Bill bill){
        byte[] b = bill.getBytes();
        try {
            AttributeSet attrSet = new HashPrintServiceAttributeSet(new PrinterName(printName, null));

            DocPrintJob job = PrintServiceLookup.lookupPrintServices(null, attrSet)[0].createPrintJob();

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(b, flavor, null);
            PrintJobWatcher pjDone = new PrintJobWatcher(job);

            job.print(doc, null);
            pjDone.waitForDone();
        } catch (javax.print.PrintException pex) {
            JOptionPane.showMessageDialog(null, "Printer Error " + pex.getMessage());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
