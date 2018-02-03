/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import billPrinter.BillConstructor;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Main {

    public static void main(String[] args) {
        Configuracion config = new Configuracion();
        HashMap<String, String> configMap = config.getConfiguration();
        try {
            int branchOfficeId = Integer.parseInt(configMap.get("branchOfficeId"));
            String printName = configMap.get("printerName");
            int printLine = Integer.parseInt(configMap.get("printerLines"));
            int[] salePointIds = getSalepointsIds(configMap.get("salePointIds"));
            String url = configMap.get("url");

            new BillConstructor(branchOfficeId, printName, printLine, salePointIds, url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private static int[] getSalepointsIds(String salePointsIds) {
        String[] salePointsString = salePointsIds.split(",");
        int[] salePointsInt = new int[salePointsString.length];
        for (int i = 0; i < salePointsString.length; i++) {
            salePointsInt[i] = Integer.parseInt(salePointsString[i]);
        }
        return salePointsInt;
    }
}
