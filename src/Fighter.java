public class Fighter {
    public int health;
    public String name;
    public int attackpower;

    public Fighter(String name, int health, int attackpower) {

        this.name = name;
        this.health = health;
        this.attackpower = attackpower;
    }

    public void changeHealth(int health) {
        this.health += health;
    }
}