package com.company;

import java.util.Scanner;

public class Main {

    Scanner sc = new Scanner(System.in);
    char[] alfabet = {' ', 'A', 'B', 'C', 'D','E','F','G','H','I','J','K','L','M','N',
            'O','P','Q','R','S','T','U','V','W','X','Y','Z','Æ','Ø','Å'};


    //Spørger brugeren om de skal enten bruge Caesar el. Vigenere.
    public String caesarOrVigenere(){
        do{
            System.out.println("Skal du bruge Caesar eller Vigenere?");
            String dilemma = sc.next();
            switch(dilemma) {
                case "Caesar":
                    return "Caesar";
                case "Vigenere":
                    return "Vigenere";
                default:
                    System.out.println("Det passer ikke noget! Prøv igen!");
            }
        } while (true);
    }

    //Sprøger om hvis den skal enten encrypt eller decrypt.
    public boolean vigenereEncryptOrDecrypt(){
        do{
            System.out.println("Skal du Encrypt eller Decrypt?");
            String dilemma = sc.next();
            switch(dilemma) {
                case "Encrypt":
                    return false;
                case "Decrypt":
                    return true;
                default:
                    System.out.println("Det passer ikke noget! Prøv igen!");
            }
        } while (true);
    }


    //Sprøger om hvis den skal enten encrypt eller decrypt.
    public int encryptOrDecrypt(int shift){
        do{
            System.out.println("Skal du Encrypt eller Decrypt?");
            String dilemma = sc.next();
            switch(dilemma) {
                case "Encrypt":
                    return shift;
                case "Decrypt":
                    return redefineShift(shift);
                default:
                    System.out.println("Det passer ikke noget! Prøv igen!");
            }
        } while (true);
    }

    //Hvis den skal dekryptere, bliver shift sat til negativ.
    public int redefineShift(int shift){
        //Sætter shift til negativ.
        int newShift = shift * -1;
        return newShift;
    }

    //Bruger en step variabel, så den ved hvilken del i beskeden skal returneres.
    public char messageToChar(String message, int step){
        char bogstav = message.charAt(step);
        return bogstav;
    }

    //Laver et char om til et tal.
    public int bogstaverTilTal(char inputBogstav){
        //Går igennem alle bogstaver indtil den finder hvilket tal passer til et char i alfabet Arrayet.
        for (int i = 0;alfabet.length >= i; i++ ){
            //Tjekker om alfabet Array er det samme som inputtet.
            if (alfabet[i] == inputBogstav){
                //Returnerer tallet.
                return i;
            }
        }
        return 404;
    }

    //Tilføjer shift til chars tal.
    public int applyShift(int talBogstav, int shift){

        //Bruger denne til når der kommer mellemrum, så de bliver ignoreret.
        if ( talBogstav != 0) {
            int shifted = shift + talBogstav;

            //Hvis encryption går over 28 i alfabetet, sætter den tilbage til A,B,C.
            if (shifted > alfabet.length - 1){
                shifted = shifted % alfabet.length +1;

                //Ellers hvis den er under nul, plusser den bare med 29.
            } else if (shifted <= 0) {
                shifted += alfabet.length -1;
            }
            return shifted;
        }
        return talBogstav;
    }

    public char taltilBogstaver(int inputTal) {
        //Spørger efter et char med det int fra input.
        char result = alfabet[inputTal];
        return result;
    }

    public void printOutMessage(char shiftedMessage){
        System.out.print(shiftedMessage);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main obj = new Main();
        int step = 0;

        //Introduktion til Casesar Cipher
        //Sprøger efter besked og shift.
        System.out.println("Velkommen til Caesar & Vigenere Cipher 2022! v0.5!");
        System.out.println("Venligst tage et valg om du skal bruge Vigenere eller Caesar.");
        String valg = obj.caesarOrVigenere();

        System.out.println("Venligst indtast en besked som skal kodes: ");
        String besked = sc.nextLine().toUpperCase();

        if ( valg.equals("Caesar")) {
            System.out.println("Mange tak! Venligst indtast en shift.");
            int shift = sc.nextInt();

            //Tager beslutning om shift er til decrypt eller encrypt.
            shift = obj.encryptOrDecrypt(shift);

            //Skal igennem hele beskeden, og printer den ud én gang, hver gang den looper.
            for (int i = 0; besked.length() > i; i++) {

                //Laver hele beskeden om til en char, én af gangen.
                char bogstav = obj.messageToChar(besked, step);

                //Laver char om til et tal.
                int bogstavTal = obj.bogstaverTilTal(bogstav);

                //Tilføjer shift til det oversatte tal fra char.
                int shiftedBogstavTal = obj.applyShift(bogstavTal, shift);

                //Oversætter det shifted tal til et bogstav igen.
                char shiftedBogstav = obj.taltilBogstaver(shiftedBogstavTal);

                //Printer ud det Encrypteret/Decrypterert message
                obj.printOutMessage(shiftedBogstav);

                //Tilføjer step til sidst så det næste bogstav bliver valgt til næste gang i messageToChar.
                step++;

            }
        } else {
        System.out.println();
        boolean decryption = obj.vigenereEncryptOrDecrypt();
        System.out.println("Indtast venligst et kodeord til at Vignereify.");
        String kodeOrd = sc.next().toUpperCase();
        step = 0;
        int kodeStep = 0;

        for (int i = 0;besked.length() > i ; i++) {

            if (kodeStep == kodeOrd.length()) {
                kodeStep = 0;
            }

            //Laver hele kodeordet om til en char, én af gangen.
            char kodeBogstav = obj.messageToChar(kodeOrd, kodeStep);

            //Laver kodeord-char om til et tal.
            int kodeOrdBogstavTal = obj.bogstaverTilTal(kodeBogstav);

            if ( decryption == true){
                kodeOrdBogstavTal = obj.redefineShift(kodeOrdBogstavTal);
            }

            //Laver hele beskeden om til en char, én af gangen.
            char bogstav = obj.messageToChar(besked, step);

            //Laver char om til et tal.
            int bogstavTal = obj.bogstaverTilTal(bogstav);


            //Tilføjer shift til det oversatte tal fra char.
            int shiftedBogstavTal = obj.applyShift(bogstavTal, kodeOrdBogstavTal);

            //Oversætter det shifted tal til et bogstav igen.
            char shiftedBogstav = obj.taltilBogstaver(shiftedBogstavTal);

            //Printer ud det Encrypteret/Decrypterert message
            obj.printOutMessage(shiftedBogstav);

            //Tilføjer step til sidst så det næste bogstav bliver valgt til næste gang i messageToChar.
            step++;
            kodeStep++;
        }

        }
    }
}