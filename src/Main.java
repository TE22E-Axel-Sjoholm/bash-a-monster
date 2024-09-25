import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        var player = new Fighter(" ", 100, 0);
        var opp = new Fighter(" ", 200, 100);

        System.out.println("Vad heter din karaktär?");
        player.name = sc.nextLine();
        System.out.println("Vad heter din motståndare?");
        opp.name = sc.nextLine();
        System.out.println("Din motståndares namn är " + opp.name);
        System.out.println("Din karaktärs namn är " + player.name);
        System.out.println("Hur vill du utdela dina karaktärpoäng");
        int cp = 150; // characterpoints
        while (cp > 0) {
            System.out.println("Du har " + cp + " points");
            System.out.println("1. Attackpower (" + player.attackpower + ")");
            System.out.println("2. Health (" + player.health + ")");
            var choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Hur många poäng vill du ge till attackpower?");
                choice = sc.nextInt();
                if (choice <= cp) {
                    player.attackpower += choice;
                    cp -= choice;
                } else {
                    System.out.println("Du har bara " + cp + " Points och kan därför inte spendera " + choice + " Points");
                    continue;
                }
            } else if (choice == 2) {
                System.out.println("Hur många poäng vill du ge till health?");
                choice = sc.nextInt();
                if (choice <= cp) {
                    player.health += choice;
                    cp -= choice;
                } else {
                    System.out.println("Du har bara " + cp + " Points och kan därför inte spendera " + choice + " Points");
                    continue;
                }
            } else {
                continue;
            }
        }
        sc.nextLine();
        System.out.println(player.name + " har " + player.health + " Health Points och " + player.attackpower + " attackpower");
        while (player.health > 0 && opp.health > 0) {
            int val = menuChoice(new String[]{"Attack", "Återhämta"});
            if (player.health <= 0 || opp.health <= 0) {
                break;
            }
            if (val == 1) {
                opp.changeHealth(-player.attackpower);
                System.out.println("Du träffade " + opp.name + " för " + player.attackpower + " skada");
            } else if (val == 2) {
                player.attackpower += rand.nextInt(10);
                player.changeHealth(rand.nextInt(30));
                System.out.println("Du återhämtade dig själv och har nu " + player.attackpower + " attackpower och " + player.health + " Health Points");
            }
            val = 1 + rand.nextInt(2);
            if (player.health <= 0 || opp.health <= 0) {
                break;
            }
            if (val == 1) {
                player.changeHealth(-opp.attackpower);
                System.out.println(opp.name + " träffade " + player.name + " för " + opp.attackpower + " skada");
            } else {
                opp.attackpower += rand.nextInt(10);
                opp.changeHealth(rand.nextInt(30));
                System.out.println(opp.name + " återhämtade sig själv och har nu " + opp.attackpower + " attackpower och " + opp.health + " Health Points");
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
            if (choice.length() > 1) {
            } else if (Character.isDigit(choice.charAt(0))) {
                return Integer.parseInt(choice);
            } else {
                continue;
            }
        }
    }
}
