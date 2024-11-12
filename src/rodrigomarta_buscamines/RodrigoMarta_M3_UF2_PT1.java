/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rodrigomarta_buscamines;

import java.util.Scanner;
import java.util.Random;
import java.lang.*;

/**
 *
 * @author martarodrigo
 */
public class RodrigoMarta_M3_UF2_PT1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        personalitzat per = new personalitzat();

        per.nom = Util.llegirString("Com et dius?: ").toUpperCase();
        per.sexe = Util.demanaSexe("Ets home o dona? ");

        int opcio;
        if (per.sexe == 'H') {
            System.out.println("HOLA, " + per.nom + " BENVINGUT AL BUSCAMINES! EN QUIN MODE VOLS JUGAR?:\n ");
        } else {
            System.out.println("HOLA, " + per.nom + " BENVINGUDA AL BUSCAMINES! EN QUIN MODE VOLS JUGAR?:\n ");
        }

        do {
            mostrarMenu();
            opcio = Util.llegirInt("Quina opcio vols?: ");
            switch (opcio) {
                case 1:
                    jugarNivell(8, 8, 10, per);
                    break;
                case 2:

                    jugarNivell(16, 20, 50, per);
                    break;
                case 3:
                    int fil = Util.llegirIntLimit("De quantes files vols el tauler?: ", 1,999);
                    int colum = Util.llegirIntLimit("De quantes columnes vols el tauler: ", 1,999);
                    int bombes = Util.llegirIntLimit("Amb quantes bombes vols jugar?: ", 1, (fil * colum - 1));
                    jugarNivell(fil, colum, bombes, per);
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Fins aviat, " + per.nom + "! " + "Juga quan vulguis");
                    break;
                default:
                    System.out.println("Opció no vàlida");
            }
        } while (opcio != 4 || opcio < 1 || opcio > 4);

    }

    /**
     * Mostrem el menu segons les opcions que tenim disponible
     */
    public static void mostrarMenu() {
        System.out.println("1. Taulell 8X8 amb 10 bombes. (Nivell 1)");
        System.out.println("2. Taulell 16X20 i 50 bombes. (Nivell 2)");
        System.out.println("3. Vull personalitzar-m'ho");
        System.out.println("4. Finalitzar el joc");
    }

    /**
     * Inicialitzem el taulell segons el nivell triat
     *
     * @param files creem les files segons el nivell triat
     * @param columnes creem les columnes segons el nivell triat
     * @return ens retorna el taulell complet
     */
    public static char[][] inicialitzarTaulell(int files, int columnes) {
        char[][] taulell = new char[files][columnes];

        for (int f = 0; f < files; f++) {
            for (int c = 0; c < columnes; c++) {
                taulell[f][c] = '-';
            }
        }
        return taulell;
    }

    /**
     * Mostrem el taulell per pantalla
     *
     * @param taulell ens mostra el taulell per pantalla segons l'opcio triada
     * EX: a l'opcio 1 ens mostrara el taulell de 8x8
     */
    public static void mostrarTaulell(char[][] taulell) {
        //mostrem el numero de columnes segons la mida del taulell triat
        int numeroColumnas = 0;
        
        System.out.print("  ");
        do {
            numeroColumnas++;
            System.out.printf("%3d",numeroColumnas);
        } while (numeroColumnas < taulell[0].length);
        System.out.println("");
        for (int f = 0; f < taulell.length; f++) {
            System.out.print((f + 1) + " ");
            for (int c = 0; c < taulell[0].length; c++) {
                System.out.printf("%3s",taulell[f][c]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Iniciar una partida segons el nivell que ha triat l'usuari
     *
     * @param files Li passem les files segons l'opcio que es tria
     * @param columnes Li passem les columnes segons l'opcio que es tria
     * @param bombes Li passem les bombes segons l'opcio que es tria EX: si
     * @param per li passem l'objecte "per" per a que el jugador tingui una
     * "experiencia" mes personalitzada
     */
    public static void jugarNivell(int files, int columnes, int bombes, personalitzat per) {
        // Inicialitzem el tauler per jugar i el tauler que te les bombes
        char[][] taulellBombes = inicialitzarTaulell(files, columnes);
        char[][] taulellJoc = inicialitzarTaulell(files, columnes);
        // Coloquem les bombes segons el nivell i mostrem el taulell
        colocarBombes(taulellBombes, bombes);
        mostrarTaulell(taulellBombes);

        //Declarem un boolean per iniciar "la partida"
        boolean jocComença = true;

        //Iniciem el cronometre per al temps de joc
        long tempsInici = System.currentTimeMillis();
        //Entrem en el while per començar a jugar
        while (jocComença) {

            long tempsTotal;
            long tempsFi;
            //Demanem i validem les "coordenades" del jugador per a que, entre d'altres, les "coordenades" no se surtin del tauler
            int fila;
            int columna;
            do {
                fila = Util.llegirInt("Introdueix fila (1-" + files + ")");
            } while (fila < -1 || fila > files);
            if (fila == -1) {
                mostrarTaulell(taulellBombes);
            } else {
                do {
                    columna = Util.llegirInt("Introdueix columna (1-" + columnes + ")");
                } while (columna < 1 || columna > columnes);
                //verifiquem que les files i columnes no surten del taulell
                //es a dir, si el meu tauler es de 8x8 i jo poso fila 9 i columna 9, no seria valid perque estaria fora del taulell
                if (fila >= 1 && fila <= files && columna >= 1 && columna <= columnes) {
                    //verifiquem que les files/columnes introduides "s'acomodin" al tauler, es a dir, un array sol començar en la posicio 0, 
                    //pero el nostre tauler comença en 1 aixi que hem d'ajustar "les files i columnes" per accedir correctament al lloc que volem     
                    if (taulellJoc[fila - 1][columna - 1] == '-') {
                        jocComença = destaparBombes(taulellBombes, taulellJoc, fila - 1, columna - 1, per, bombes);
                        if (jocComença) {
                            mostrarTaulell(taulellJoc);
                            tempsFi = System.currentTimeMillis();
                            tempsTotal = tempsFi - tempsInici;

                            System.out.println("Portes: " + tempsTotal / 1000 + " segons");
                        }
                        //comprovem que el jugador no ha perdut i d'aquesta manera seguir mostrant el taulell per jugar
                        if (!jocComença) {
                            mostrarTaulell(taulellBombes);
                            tempsFi = System.currentTimeMillis();
                            tempsTotal = tempsFi - tempsInici;
                            System.out.println("Has trigat: " + tempsTotal / 1000 + " segons");
                        } else if (totesCasellesDestapades(taulellJoc, bombes)) {
                            int punt = contarPuntuacio(taulellJoc, bombes);
                            System.out.println("Has guanyat! La teva puntuacio es de: " + punt);
                            mostrarTaulell(taulellJoc);
                            tempsFi = System.currentTimeMillis();
                            tempsTotal = tempsFi - tempsInici;

                            System.out.println("Has trigat: " + tempsTotal / 1000 + " segons");

                            jocComença = false;
                        }
                    } else {
                        System.out.println("Aquesta casella ja l'has marcat abans. Introdueix noves coordenades.");
                    }
                } else {
                    System.out.println("Aquestes coordenades no son vàlides. Introdueix noves coordenades.");
                }
            }
        }
    }

    /**
     * Coloquem les bombes de forma aleatoria
     *
     * @param taulell ens mostra el taulell per pantalla segons l'opcio triada
     * @param bombes li passem les bombes que es necessiten en cada nivell
     */
    public static void colocarBombes(char[][] taulell, int bombes) {
        Random rnd = new Random();
        int bombesColocades = 0;

        while (bombesColocades < bombes) {
            int fila = rnd.nextInt(taulell.length);
            int columna = rnd.nextInt(taulell[0].length);
            // Comprovem si la posició està lliure abans de col·locar la bomba
            if (taulell[fila][columna] != 'B') {
                taulell[fila][columna] = 'B';
                bombesColocades++;
            }
        }
    }

    /**
     * Destapar una casella en el taulell del joc, mostrant si hi ha una bomba o
     * indicant el nombre de bombes que hi ha a prop. En cas que es trobi una
     * bomba, el joc s'acaba, i es mostra la puntuació obtinguda fins a aquest
     * moment.
     *
     * @param taulellBombes El taulell amb la ubicació de les bombes.
     * @param taulellJoc El taulell del joc on es mostren les caselles
     * destapades.
     * @param fila La fila de la casella que es vol destapar.
     * @param columna La columna de la casella que es vol destapar.
     * @param per L'objecte que representa les dades personalitzades del
     * jugador.
     * @param bombes El nombre total de bombes en el taulell.
     * @return ens mostra "True" si la casella no conté una bomba i el joc
     * continua (es a dir, no ha perdut) o "False" si es troba una bomba i el
     * joc s'acaba.
     */
    public static boolean destaparBombes(char[][] taulellBombes, char[][] taulellJoc, int fila, int columna, personalitzat per, int bombes) {
        if (taulellBombes[fila][columna] == 'B') {
            int punt = contarPuntuacio(taulellJoc, bombes);
            System.out.println("Ho sento, " + per.nom + " Has trobat una bomba! Game Over. La teva puntuacio es de: " + punt);

            taulellJoc[fila][columna] = 'B';
            mostrarTaulell(taulellJoc);
            return false;
        } else {
            int bombesVoltant = contarBombes(taulellBombes, fila, columna);
            taulellJoc[fila][columna] = (char) ('0' + bombesVoltant);

            return true;
        }
    }

    /**
     * Compta el nombre de bombes proximes a una casella específica del taulell.
     *
     * @param taulell El taulell de joc on es troba la casella.
     * @param fila La fila de la casella.
     * @param columna La columna de la casella.
     * @return El nombre de bombes proximes a la casella especificada.
     */
    public static int contarBombes(char[][] taulell, int fila, int columna) {
        int bombesVoltant = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int novaFila = fila + i;
                int novaColumna = columna + j;

                if (novaFila >= 0 && novaFila < taulell.length && novaColumna >= 0 && novaColumna < taulell[0].length) {
                    if (taulell[novaFila][novaColumna] == 'B') {
                        bombesVoltant++;
                    }
                }
            }
        }

        return bombesVoltant;
    }

    /**
     * Verifica si totes les caselles del tauler de joc han estat destapades.
     *
     * @param taulellJoc El tauler de joc per jugar.
     * @param bombes El nombre total de bombes al tauler.
     * @return mostra true si totes les caselles han estat destapades o false
     * si, per exemple, en falta alguna per destapar.
     */
    public static boolean totesCasellesDestapades(char[][] taulellJoc, int bombes) {
        int casellesDest = 0;

        for (int f = 0; f < taulellJoc.length; f++) {
            for (int c = 0; c < taulellJoc[0].length; c++) {
                if (taulellJoc[f][c] != '-') {
                    casellesDest++;
                }
            }
        }

        return casellesDest == (taulellJoc.length * taulellJoc[0].length) - bombes;
    }

    /**
     * Compta la puntuació total segons les caselles destapades del tauler de
     * joc (per exemple si jo destapo 3 caselles amb el numero 1 en cadascuna
     * d'elles, la meva puntuacio sera de 3).
     *
     * @param taulellJoc El tauler de joc actual.
     * @param bombes El nombre total de bombes al tauler.
     * @return La puntuació total obtinguda.
     */
    public static int contarPuntuacio(char[][] taulellJoc, int bombes) {
        int puntuacio = 0;

        for (int f = 0; f < taulellJoc.length; f++) {
            for (int c = 0; c < taulellJoc[0].length; c++) {
                if (taulellJoc[f][c] != '-') {
                    puntuacio += (taulellJoc[f][c] - '0');
                }
            }
        }

        return puntuacio;
    }

}
