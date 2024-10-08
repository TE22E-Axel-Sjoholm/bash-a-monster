import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        var player = new Fighter(" ", 400, 0);
        var opp = new Fighter(" ", 500, 100);

        System.out.println("Vad heter din karaktär?");
        player.name = sc.nextLine();
        System.out.println("Vad heter din motståndare?");
        opp.name = sc.nextLine();
        System.out.println("Din motståndares namn är " + opp.name);
        System.out.println("Din karaktärs namn är " + player.name);
        System.out.println("Hur vill du utdela dina karaktärpoäng");
        int charPoints = 150; // characterpoints
        while (charPoints > 0) {
            System.out.println("Du har " + charPoints + " points");
            System.out.println("1. Attackpower (" + player.attackPower + ")");
            System.out.println("2. Health (" + player.health + ")");
            var choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Hur många poäng vill du ge till attackpower?");
                choice = sc.nextInt();
                if (choice <= charPoints) {
                    player.attackPower += choice;
                    charPoints -= choice;
                } else {
                    System.out.println("Du har bara " + charPoints + " Points och kan därför inte spendera " + choice + " Points");
                }
            } else if (choice == 2) {
                System.out.println("Hur många poäng vill du ge till health?");
                choice = sc.nextInt();
                if (choice <= charPoints) {
                    player.health += choice;
                    charPoints -= choice;
                } else {
                    System.out.println("Du har bara " + charPoints + " Points och kan därför inte spendera " + choice + " Points");
                }
            }
        }
        sc.nextLine();
        System.out.println(player.name + " har " + player.health + " Health Points och " + player.attackPower + " attackpower");
        while (player.health > 0 && opp.health > 0) {
            int val = menuChoice(new String[]{"Attack", "Återhämta"});
            if (player.health <= 0 || opp.health <= 0) {
                break;
            }
            if (val == 1) {
                opp.changeHealth(-player.attackPower);
                System.out.println("Du träffade " + opp.name + " för " + player.attackPower + " skada");
            } else {
                player.heal(rand.nextInt(10), rand.nextInt(30));
            }
            val = 1 + rand.nextInt(2);
            if (player.health <= 0 || opp.health <= 0) {
                break;
            }
            if (val == 1) {
                player.changeHealth(-opp.attackPower);
                System.out.println(opp.name + " träffade " + player.name + " för " + opp.attackPower + " skada");
            } else {
                opp.heal(rand.nextInt(50), rand.nextInt(80));
            }
        }
        if (player.health <= 0) {
            System.out.println(player.name + " har blivit besegrad av " + opp.name);
        }
        if (opp.health <= 0) {
            System.out.println(opp.name + " har blivit besegrad av " + player.name);
        }
    }

    private static int menuChoice(String[] val) {
        while (true) {
            for (int i = 0; i < val.length; i++) {
                System.out.println(i + 1 + ". " + val[i]);
            }
            var choice = sc.nextLine();
            if (Character.isDigit(choice.charAt(0)) && choice.length() == 1) {
                return Integer.parseInt(choice);
            }
        }
    }
}
