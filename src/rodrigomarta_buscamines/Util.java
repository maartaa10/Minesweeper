/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rodrigomarta_buscamines;
import java.util.Scanner;
/**
 *
 * @author martarodrigo
 */
public class Util {
private static Scanner teclat = new Scanner(System.in);
public static int llegirInt(String text) {
        int val = 0;
        boolean llegit = false;
        do {
            System.out.print(text);
            if (teclat.hasNextInt()) {
                val = teclat.nextInt();
                llegit = true;
            } else {
                System.out.println("Error");
            }
            teclat.nextLine();
        } while (!llegit);
        return val;
    }
public static int llegirIntLimit(String text, int limitMin, int limitMax) {
        int val = 0;
        boolean llegit = false;
        do {
            System.out.print(text);
            if (teclat.hasNextInt()) {
                    val = teclat.nextInt();
                if(val < limitMin || val > limitMax) {
                    System.out.println("Estan fora de limit");
                }
                else {
                    llegit = true;
                }
                
            } else {
                System.out.println("Error");
                teclat.nextLine();
            }
            teclat.nextLine();
        } while (!llegit);
        return val;
    }

    public static double llegirDouble(String text) {
        double val = 0.00;
        boolean llegit = false;
        do {
            System.out.print(text);
            if (teclat.hasNextDouble()) {
                val = teclat.nextDouble();
                llegit = true;
            } else {
                System.out.println("Error");
            }
            teclat.nextLine();
        } while (!llegit);
        return val;
    }

  public static char demanaSexe(String text) {
    char sexe;
    do {
        System.out.print(text + " (H/D): ");
        sexe = teclat.nextLine().toUpperCase().charAt(0);
    } while (sexe != 'H' && sexe != 'D');
    return sexe;
}



    public static int demanaEdat(String text) {
        System.out.print("Introdueix l'edat: ");
        return teclat.nextInt();
    }

   public static int demanaTempsAnterior(String text) {
    System.out.print("Quant temps has trigat en fer la marato anterior? ");
      return teclat.nextInt();
    }

 public static void main(String[] args) {
        String cadena = llegirString("Introdueix una cadena: ");
        System.out.println("Has introduït: " + cadena);
    }

    public static String llegirString(String text) {
        String input = "";
        boolean llegit = false;

        do {
            System.out.print(text);
            if (teclat.hasNextLine()) {
                input = teclat.nextLine();
                llegit = true;
            } else {
                System.out.println("Error: No és una cadena vàlida.");
                teclat.nextLine(); 
            }
        } while (!llegit);

        return input;
    }




}









    
