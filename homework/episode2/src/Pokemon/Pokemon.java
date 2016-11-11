package Pokemon;

public class Pokemon {

    enum State {
        IN_BATTLE,
        SLEEPING,
        PLAYING,
        DEAD
    }

    public enum TurnStrategy{
        REGEN,
        ATTACK,
        DEFEND,
        SPECIAL_ATTACK
    }

    private String name;
    private int attack;
    private int defence;
    private int health = 100;
    private int regeneration = 10;
    private int stamina = 0;

    private TurnStrategy turnStrategy;
    private State state = State.SLEEPING;

    protected Pokemon(String name, int attack, int defence, int health, int regeneration) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.regeneration = regeneration;
    }

    public String getName() {return this.name;}
    public int getAttack() {return this.attack;}
    public int getDefence() {return this.defence;}
    public int getHealth() {return this.health;}
    public boolean isDead() {return this.state == State.DEAD;}

    public void changeTurnStrategy(TurnStrategy strategy) {
        this.turnStrategy = strategy;
    }

    public void performTurn(Pokemon enemyPokemon) {
        stamina = (stamina + 15) > 100 ? 100 : stamina + 15;
        switch (turnStrategy){
            case ATTACK:
                this.attack(enemyPokemon);
                break;
            case SPECIAL_ATTACK:
                this.specialAttack(enemyPokemon);
                break;
            case REGEN:
                this.regenerate();
                break;
        }
    }

    public void getAttacked(int attack) {
        if (this.turnStrategy == TurnStrategy.DEFEND){
            this.health -= attack < this.defence ?
                    0 : attack - this.defence;
        } else {
            this.health -= attack;
        }
    }

    public void attack(Pokemon pokemon) {
        pokemon.getAttacked(this.getAttack());
        if (this.health <= 0){
            this.state = State.DEAD;
            this.health = 0;
        }
    }

    public void specialAttack(Pokemon pokemon){
        this.stamina -= 70;
        pokemon.getAttacked(this.attack);
    }

    public void regenerate(){
        this.health += this.regeneration;
    }

    @Override
    public String toString(){
        return ("Name: " + this.name + " " +
                "Health: " + this.health + " " +
                "Attack: " + this.attack + " " +
                "Defence: " + this.defence);
    }
}
