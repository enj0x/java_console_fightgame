import java.util.Scanner;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		Fighter f1 = createFighter("1");
		System.out.println("-------------------------------------\n");
		Fighter f2 = createFighter("2");
		System.out.println("\n=======Kaempfer wurden erstellt=======\n");
		System.out.println(f1);
		System.out.println(f2);
		System.out.println("\n");
		
		
		while(f1.getHp() > 0 && f2.getHp() > 0) {
			if(f1.isSpecialAttackReady()) {
				System.out.println("\n===========================================");
				System.out.println("Sie koennen ihre Spezial Attacke einsetzen");
				System.out.println("===========================================");
			}
			
			choosePlayerAttack(f1, f2);
			enemyAttack(f1, f2);
		}
		
		if(f2.isKo()) {
			System.out.println("\n=====================================\n");
			System.out.println("Sie haben gewonnen!");
			System.out.println("\n=====================================\n");
		}
		else  {
			System.out.println("\n=====================================\n");
			System.out.println("Sie haben verloren!");
			System.out.println("\n=====================================\n");
		}
		
}
	
	

	public static Fighter createFighter(String fighterNr) {
		
		System.out.println("\nBitte geben sie den Namen von Kaempfer " + fighterNr + " ein: ");
		final String name = sc.nextLine();
		return chooseCharacterType(name);
	}
	
	public static Fighter chooseCharacterType (String name) {
		
		System.out.println("\n Bitte waehlen sie den Kaempfertyp: ");
		System.out.println("1 -> Krieger \n"
				+ "2 -> Bogenschuetze \n"
				+ "3 -> Magier \n");
		
		try {
			String fighterType = sc.nextLine();
			switch (Integer.valueOf(fighterType)) {
			case 1:
				Fighter krieger = new Fighter(name, "Krieger", 5, 15, 5, 20, 2);
				return krieger;
			case 2:
				Fighter Bogenschuetze = new Fighter(name, "Bogenschuetze", 5, 5, 15, 20, 2);
				return Bogenschuetze;
			case 3:
				Fighter magier = new Fighter(name, "Magier", 6, 5, 8, 30, 3);
				return magier;
			default:
				System.out.println("Das ist keine valide Eingabe, bitte waehlen Sie zwischen Option 1 und 3");
				return chooseCharacterType(name);
			}
		} catch(NumberFormatException ex) {
			System.out.println("Das ist keine valide Eingabe, bitte waehlen Sie zwischen Option 1 und 3");
			return chooseCharacterType(name);
		}
		
	}
	
	public static void choosePlayerAttack(Fighter f1, Fighter f2) {
		System.out.println( "\n"
				+ "-----------------------------------------------------"
				+ "\nBitte waehlen sie einen Angriff gegen "
				+ f2.getName()
				+ " aus \n"
				+ "1 -> Nahkampf \n"
				+ "2 -> Fernkampf \n"
				+ "3 -> Spezialattacke \n"
				+ "4 -> Lebensanzeige auslesen \n"
				+ "5 -> aufgeben \n"
				+ "-----------------------------------------------------");
		
		try {
			String attackType = sc.nextLine();
			switch (Integer.valueOf(attackType)) {
			case 1: 
				f1.attack(f1, f2, f1.getAttack1(), "Nahkampf");
				f1.increaseSpecialAttackCounter();
				break;
			case 2:
				f1.attack(f1, f2, f1.getAttack2(), "Fernkampf");
				f1.increaseSpecialAttackCounter();
				break;
			case 3:
				if(f1.isSpecialAttackReady()) {
					f1.attack(f1, f2, f1.getAttackSpecial(), "Spezial");	
					f1.setSpecialAttackCounter(0);
				} else {
					System.out.println("Ihre Spezialattacke ist noch nicht bereit bitte waehlen sie eine andere Option");
					choosePlayerAttack(f1, f2);
				}
				
				break;
			case 4:
				System.out.println("\n=====================================\n");
				System.out.println(f1.getName() + " hat noch " + f1.getHp() + " hp");
				System.out.println(f2.getName() + " hat noch " + f2.getHp() + " hp");
				System.out.println("\n=====================================\n");
				choosePlayerAttack(f1, f2);
				break;
			case 5:
				System.out.println("\n=====================================\n");
				System.out.println("Sie haben aufgegeben. " + f2.getName() + " hat gewonnen");
				System.out.println("\n=====================================\n");
				System.exit(0);
			default:
				System.out.println("\n=====================================\n");
				System.out.println("Das ist keine valide Eingabe, bitte probieren sie die Eingabe erneut");
				System.out.println("\n=====================================\n");
				choosePlayerAttack(f1, f2);
				break;
			}
		} catch(NumberFormatException ex) {
			System.out.println("Das ist keine valide Eingabe, bitte probieren sie die Eingabe erneut");
			choosePlayerAttack(f1, f2);
		}
	}
	
	public static void enemyAttack(Fighter f1, Fighter f2) {
		if(!f2.isKo()) {
			if(f2.isSpecialAttackReady()) {
				f2.attack(f2, f1, f2.getAttackSpecial(), "Spezial");
				f2.setSpecialAttackCounter(0);
			} else if(f2.getAttack2() > f2.getAttack1()) {
				f2.attack(f2, f1, f2.getAttack2(), "Fernkampf");
				f2.increaseSpecialAttackCounter();
			} else {
				f2.attack(f2, f1, f2.getAttack1(), "Nahkampf");
				f2.increaseSpecialAttackCounter();
			}
			
		}
	}
	
}

	
	