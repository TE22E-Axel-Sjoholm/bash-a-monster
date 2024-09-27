public class Fighter {
    public int health;
    public String name;
    public int attackPower;

    public Fighter(String name, int health, int attackpower) {

        this.name = name;
        this.health = health;
        this.attackPower = attackpower;
    }

    public void changeHealth(int health) {
        this.health += health;
    }

    public void heal(int attackPower, int health) {
        this.attackPower += attackPower;
        changeHealth(health);
        System.out.println(name + " återhämtade sig själv med " + attackPower + " attackpower och har nu " + this.attackPower + " attackpower");
        System.out.println(name + " återhämtade sig själv med " + health + " health och har nu " + this.health + " health");
    }
}