
       package kalkulatorkonsol;

import java.util.Scanner;

/**
 *
 * @author ERICO FERDIANTO
 */
public class KalkulatorKonsol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            String ulang;
            
            System.out.println("=== KALKULATOR SEDERHANA ===");
            
            do {
                double angka1 = bacaAngka(input, "Masukkan angka pertama : ");
                System.out.print("Masukkan operator (+, -, *, /) : ");
                String operator = input.nextLine().trim();
                double angka2 = bacaAngka(input, "Masukkan angka kedua   : ");
                
                switch (operator) {
                    case "+":
                        tampilkanHasil(angka1 + angka2);
                        break;
                    case "-":
                        tampilkanHasil(angka1 - angka2);
                        break;
                    case "*":
                        tampilkanHasil(angka1 * angka2);
                        break;
                    case "/":
                        if (angka2 == 0) {
                            System.out.println("Error: tidak bisa membagi dengan nol!");
                        } else {
                            tampilkanHasil(angka1 / angka2);
                        }
                        break;
                    default:
                        System.out.println("Error: operator tidak valid!");
                }
                
                System.out.print("\nHitung lagi? (y/n) : ");
                ulang = input.nextLine().trim();
                System.out.println();
            } while (ulang.equalsIgnoreCase("y"));
            
            System.out.println("Terima kasih!");
        }
    }

    // Meminta angka sampai pengguna memasukkan angka yang valid
    private static double bacaAngka(Scanner input, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Double.parseDouble(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka, coba lagi.");
            }
        }
    }

    private static void tampilkanHasil(double hasil) {
        if (hasil == (long) hasil) {
            System.out.println("Hasil : " + (long) hasil); // 5.0 -> 5
        } else {
            System.out.println("Hasil : " + hasil);
        }
    }
}
    

