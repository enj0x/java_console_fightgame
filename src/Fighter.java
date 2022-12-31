import java.util.Random;

public class Fighter {
	
	
	public static Random randomNumber = new Random();
	private String name;
	private String characterType;
	private int hp = 100;
	private int dex; 
	private int attack1;
	private int attack2;
	private int attackSpecial;
	private int specialAttackPossibilityFactor;
	private int specialAttackCounter = 0;
	private boolean ko;


	public Fighter(String name, String characterType, int dex, int attack1, int attack2, int attackSpecial, int specialAttackPossibilityFactor) {
		this.name = name;
		this.characterType = characterType;
		this.dex = dex;
		this.attack1 = attack1;
		this.attack2 = attack2; 
		this.attackSpecial = attackSpecial; 
		this.specialAttackPossibilityFactor = specialAttackPossibilityFactor;
	}

	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}


	public int getDex() {
		return dex;
	}


	public void setDex(int dex) {
		this.dex = dex;
	}


	public int getAttack1() {
		return attack1;
	}


	public void setAttack1(int attack1) {
		this.attack1 = attack1;
	}


	public int getAttack2() {
		return attack2;
	}
	

	public void setAttack2(int attack2) {
		this.attack2 = attack2;
	}


	public int getAttackSpecial() {
		return attackSpecial;
	}


	public void setAttackSpecial(int attackSpecial) {
		this.attackSpecial = attackSpecial;
	}
	
	public int getSpecialAttackCounter() {
		return specialAttackCounter;
	}


	public void setSpecialAttackCounter(int specialAttackCounter) {
		this.specialAttackCounter = specialAttackCounter;
	}
	

	public boolean isKo() {
		return ko;
	}


	public void setKo(boolean ko) {
		this.ko = ko;
	}

	

	
	public void attack(Fighter attacker, Fighter defender, int attackDmg, String attackType) {
		if(defender.dodge()) {
			System.out.println("\n" + attacker.name + " greift mit " + attackType + "attacke an: \n" + defender.name + " ist ausgewichen");
		}
		else {
			System.out.println("\n" + attacker.name + " greift mit " + attackType + "attacke an: ");
			int hitStrength = randomNumber.nextInt(5)+1;
			boolean gotCriticalDmg = hitStrength == 5 ? true : false;
			defender.takeDmg(attackDmg + hitStrength, gotCriticalDmg);
		}
	}
	
	
	public boolean dodge() {
		if(randomNumber.nextInt(5)+dex >= 10) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void takeDmg(int dmg, boolean gotCriticalDamage) {
		hp = hp - dmg;
		if(hp <= 0) {
			this.setKo(true);
			System.out.println(name +" hat " + dmg + " Schaden abbekommen (Knock Out)");
		} else {
			if(gotCriticalDamage) {
				System.out.println(name +" hat " + dmg + " Schaden abbekommen *Kritischer Schaden* (verbleibend:" + hp + "hp)");
			} else {
				System.out.println(name +" hat " + dmg + " Schaden abbekommen (verbleibend:" + hp + "hp)");
			}
			
		}
		
	}
	
	
	public void increaseSpecialAttackCounter () {
		specialAttackCounter = specialAttackCounter + specialAttackPossibilityFactor + randomNumber.nextInt(2);
	}
		
	
	public boolean isSpecialAttackReady() {
		if(specialAttackCounter >= 10) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public String toString() {
		return "Fighter [name=" + name + ", characterType=" + characterType + ", hp=" + hp + ", dex=" + dex
				+ ", attack1Dmg=" + attack1 + ", attack2Dmg=" + attack2 + ", attackSpecial=" + attackSpecial
				+ ", specialAttackPossibilityFactor=" + specialAttackPossibilityFactor + ", specialAttackCounter="
				+ specialAttackCounter + ", ko=" + ko + "]";
	}
	
	


	


	
	
}
