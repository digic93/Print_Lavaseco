/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Configuracion {

    public HashMap<String, String> getConfiguration() {
        int line = 1;
        HashMap<String, String> configuration = new HashMap();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            File f = new File("");
            
            fr = new FileReader(f.getAbsolutePath() +"/Config.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;
            
            while ((sCurrentLine = br.readLine()) != null) {
                switch(line){
                    case 1: //BranchOfficeId
                        configuration.put("branchOfficeId", sCurrentLine);
                        break;
                    case 2: //SalePoints ids
                        configuration.put("salePointIds", sCurrentLine);
                        break;
                    case 3: //printer name
                        configuration.put("printerName", sCurrentLine);
                        break;
                    case 4: //80 0 50 lines printer
                        configuration.put("printerLines", sCurrentLine);
                        break;
                    case 5: //url to print
                        configuration.put("url", sCurrentLine);
                        break;
                }
                line++;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al configurar: " + e.getMessage());
        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error 2 al configurar: " + ex.getMessage());
            }

        }
        return configuration;
    }
}