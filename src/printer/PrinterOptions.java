/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printer;

/**
 *
 * @author Dell
 */
public class PrinterOptions {

    String commandSet = "";

    public String initialize() {
        final byte[] Init = {27, 64};
        commandSet += new String(Init);
        return new String(Init);
    }

    public String chooseFont(int Options) {
        String s = "";
        final byte[] ChooseFontA = {27, 77, 0};
        final byte[] ChooseFontB = {27, 77, 1};
        final byte[] ChooseFontC = {27, 77, 48};
        final byte[] ChooseFontD = {27, 77, 49};

        switch (Options) {
            case 1:
                s = new String(ChooseFontA);
                break;

            case 2:
                s = new String(ChooseFontB);
                break;

            case 3:
                s = new String(ChooseFontC);
                break;

            case 4:
                s = new String(ChooseFontD);
                break;

            default:
                s = new String(ChooseFontB);
        }
        commandSet += s;
        return new String(s);
    }

    public String feedBack(byte lines) {
        final byte[] Feed = {27, 101, lines};
        String s = new String(Feed);
        commandSet += s;
        return s;
    }

    public String feed(byte lines) {
        final byte[] Feed = {27, 100, lines};
        String s = new String(Feed);
        commandSet += s;
        return s;
    }

    public String alignLeft() {
        final byte[] AlignLeft = {27, 97, 48};
        String s = new String(AlignLeft);
        commandSet += s;
        return s;
    }

    public String alignCenter() {
        final byte[] AlignCenter = {27, 97, 49};
        String s = new String(AlignCenter);
        commandSet += s;
        return s;
    }

    public String alignRight() {
        final byte[] AlignRight = {27, 97, 50};
        String s = new String(AlignRight);
        commandSet += s;
        return s;
    }

    public String newLine() {
        final byte[] LF = {10};
        String s = new String(LF);
        commandSet += s;
        return s;
    }

    public String reverseColorMode(boolean enabled) {
        final byte[] ReverseModeColorOn = {29, 66, 1};
        final byte[] ReverseModeColorOff = {29, 66, 0};

        String s = "";
        if (enabled) {
            s = new String(ReverseModeColorOn);
        } else {
            s = new String(ReverseModeColorOff);
        }

        commandSet += s;
        return s;
    }

    public String doubleStrik(boolean enabled) {
        final byte[] DoubleStrikeModeOn = {27, 71, 1};
        final byte[] DoubleStrikeModeOff = {27, 71, 0};

        String s = "";
        if (enabled) {
            s = new String(DoubleStrikeModeOn);
        } else {
            s = new String(DoubleStrikeModeOff);
        }

        commandSet += s;
        return s;
    }

    public String doubleHeight(boolean enabled) {
        final byte[] DoubleHeight = {27, 33, 17};
        final byte[] UnDoubleHeight = {27, 33, 0};

        String s = "";
        if (enabled) {
            s = new String(DoubleHeight);
        } else {
            s = new String(UnDoubleHeight);
        }

        commandSet += s;
        return s;
    }

    public String emphasized(boolean enabled) {
        final byte[] EmphasizedOff = {27, 0};
        final byte[] EmphasizedOn = {27, 1};

        String s = "";
        if (enabled) {
            s = new String(EmphasizedOn);
        } else {
            s = new String(EmphasizedOff);
        }

        commandSet += s;
        return s;
    }

    public String underLine(int Options) {
        final byte[] UnderLine2Dot = {27, 45, 50};
        final byte[] UnderLine1Dot = {27, 45, 49};
        final byte[] NoUnderLine = {27, 45, 48};

        String s = "";
        switch (Options) {
            case 0:
                s = new String(NoUnderLine);
                break;

            case 1:
                s = new String(UnderLine1Dot);
                break;

            default:
                s = new String(UnderLine2Dot);
        }
        commandSet += s;
        return new String(s);
    }

    public String color(int Options) {
        final byte[] ColorRed = {27, 114, 49};
        final byte[] ColorBlack = {27, 114, 48};

        String s = "";
        switch (Options) {
            case 0:
                s = new String(ColorBlack);
                break;

            case 1:
                s = new String(ColorRed);
                break;

            default:
                s = new String(ColorBlack);
        }
        commandSet += s;
        return s;
    }

    public String finit() {
        final byte[] FeedAndCut = {29, 'V', 66, 0};

        String s = new String(FeedAndCut);

        final byte[] DrawerKick = {27, 70, 0, 60, 120};
        s += new String(DrawerKick);

        commandSet += s;
        return s;
    }

    public void printQR(String qrText, int errCorrect, int moduleSize) {
        StringBuilder str = new StringBuilder();

        //Sets barcode type
        //Sets to model 1
        str.append((char) 27); //EZC is set to  (char) 27
        str.append((char) 29);
        str.append((char) 'y');
        str.append((char) 'S');
        str.append((char) 2);
        str.append((char) 3);

        //Sets mistake correction level to L
        str.append((char) 27);
        str.append((char) 29);
        str.append((char) 'y');
        str.append((char) 'S');
        str.append((char) 1);
        str.append((char) 0);

        //Sets cell size to 3 dots.
        str.append((char) 27);
        str.append((char) 29);
        str.append((char) 'y');
        str.append((char) 'S');
        str.append((char) 2);
        str.append((char) 3);

        //Sets bar code data
        str.append((char) 27);
        str.append((char) 29);
        str.append((char) 'y');
        str.append((char) 'D');
        str.append((char) 1);
        str.append((char) 0);
        str.append((char) 20);
        str.append((char) 0);
        str.append(qrText); // the QR text
        str.append((char) 10);

        //Print bar code
        //Check bar code expansion information
        str.append((char) 27);
        str.append((char) 29);
        str.append((char) 'y');
        str.append((char) 'l');

        commandSet += str.toString();
        System.out.println(commandSet);

    }

    public String addLineSeperator(int printLine) {
        String lineSpace;
        if (printLine == 50) {
            lineSpace = "--------------------------------";
        } else {
            lineSpace = "________________________________________";
        }
        commandSet += lineSpace;
        return lineSpace;
    }

    public void resetAll() {
        commandSet = "";
    }

    public void setText(String s) {
        commandSet += s;
    }

    public String finalCommandSet() {
        return commandSet;
    }
}
