// Base class for all creatures
abstract class Creature {
    protected String name;
    protected int health;

    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public abstract void attack(Creature target);
    public abstract void takeDamage(int damage);

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
}

// Player class
class Player extends Creature {
    public Player(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(name + " attacks " + target.getName());
        target.takeDamage(10); // Player does 10 damage
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health now: " + health);
    }
}

// Goblin class
class Goblin extends Creature {
    public Goblin(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(name + " (Goblin) attacks " + target.getName());
        target.takeDamage(5); // Goblin does 5 damage
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health now: " + health);
    }
}

// Dragon class
class Dragon extends Creature {
    public Dragon(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(name + " (Dragon) attacks " + target.getName());
        target.takeDamage(20); // Dragon does 20 damage
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health now: " + health);
    }
}

// Base class for items
abstract class Item {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void use(Player player);
}

// Potion class
class Potion extends Item {
    public Potion(String name) {
        super(name);
    }

    @Override
    public void use(Player player) {
        System.out.println(player.getName() + " uses " + name);
        player.takeDamage(-20); // Heal the player by 20
    }
}

// Main game class
public class DungeonAdventure {
    public static void main(String[] args) {
        Player player = new Player("Hero", 100);
        Creature goblin = new Goblin("Goblin", 30);
        Creature dragon = new Dragon("Dragon", 100);
        Item potion = new Potion("Healing Potion");

        // Example game interactions
        System.out.println("A wild Goblin appears!");
        player.attack(goblin);
        goblin.attack(player);
        player.attack(goblin);

        System.out.println("You found a potion!");
        potion.use(player);

        System.out.println("A wild Dragon appears!");
        player.attack(dragon);
        dragon.attack(player);

        // Continue the game loop as needed...
    }
}
