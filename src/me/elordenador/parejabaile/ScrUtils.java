package me.elordenador.parejabaile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ScrUtils {
    /**
     * Función para imprimir un separador, en Linux y en una terminal compatible, se extenderá al largo de la terminal, en Windows o en consolas desconocidas no.
     * @throws IOException
     */
    public static void printSeparator() throws IOException {
        String OS = System.getProperty("os.name");
        if (OS.toUpperCase().equals("LINUX")) {
            int width = getScreenSize()[0];
            if (width == 0) {
                System.out.println("------------------------------");
            } else {
                for (int i = 0; i < width; i++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        } else {
            System.out.println("-------------------------");
        }
    }

    /**
     * Este comando se deberia usar en Linux, te da el tamaño de la terminal, si se usa en Windows, devuelve 80, 25
     * @return array con el tamaño del terminal
     * @throws IOException
     */
    public static int[] getScreenSize() throws IOException {
        int[] returned = new int[2];

        if (System.getProperty("os.name").toUpperCase().startsWith("WINDOWS")) {
            returned[0] = 80;
            returned[1] = 25;
            return returned;
        }
        Process p1 = Runtime.getRuntime().exec(new String[] {
                "bash", "-c", "tput cols 2> /dev/tty"
        });
        InputStream stream = p1.getInputStream();
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        result = result.replaceAll("\\s","");
        if (result.equals("")) {
            returned[0] = 0;
        } else {
            returned[0] = Integer.parseInt(result);
        }

        p1 = Runtime.getRuntime().exec(new String[] {
                "bash", "-c", "tput cols 2> /dev/tty"
        });
        stream = p1.getInputStream();
        s = new Scanner(stream).useDelimiter("\\A");
        result = s.hasNext() ? s.next() : "";
        result = result.replaceAll("\\s", "");
        if (result.equals("")) {
            returned[1] = 0;
        } else {
            returned[1] = Integer.parseInt(result);
        }
        return returned;
    }
    /**
     * Imprime lentamente poquito a poquito el texto.
     * @param text El texto a imprimir
     * @throws InterruptedException Si se hace Ctrl + C durante la escritura.
     */
    public static void slowWrite(String text) throws InterruptedException {
        char[] textArray = text.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            System.out.print(textArray[i]);
            Thread.sleep(10);
        }
    }

    /**
     * Se ejecuta slowWrite(text) y luego se imprime un salto de linea.
     * @param text EL texto a imprimir
     * @throws InterruptedException Si se hace Ctrl + C durante la escritura.
     */
    public static void slowPrint(String text) throws InterruptedException {
        slowWrite(text);
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void go(int x, int y) {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df", escCode, y, x));
    }
}
