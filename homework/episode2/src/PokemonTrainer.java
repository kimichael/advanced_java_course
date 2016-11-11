import Pokemon.Pokemon;

import java.util.HashMap;

public class PokemonTrainer {
    private String name;
    private HashMap<String, Pokemon> pokemons = new HashMap<>();

    PokemonTrainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void addPokemon(Pokemon pokemon){
        if (pokemon != null) {pokemons.put(pokemon.getName(), pokemon);};
    }

    void changeStrategyToPokemon(Pokemon pokemon, Pokemon.TurnStrategy strategy){
        pokemon.changeTurnStrategy(strategy);
    }

    void askToChangeStrategy(Pokemon pokemon) {
        System.out.println(this.name + ", choose your action:\n"
                + "(A)ttack (R)egenerate\n"
                + "(D)efend (S)pecial attack\n");
        switch (Main.in.next()){
            case "A":
            case "a":
                this.changeStrategyToPokemon(pokemon, Pokemon.TurnStrategy.ATTACK);
                break;
            case "R":
            case "r":
                this.changeStrategyToPokemon(pokemon, Pokemon.TurnStrategy.REGEN);
                break;
            case "D":
            case "d":
                this.changeStrategyToPokemon(pokemon, Pokemon.TurnStrategy.DEFEND);
                break;
            case "S":
            case "s":
                this.changeStrategyToPokemon(pokemon, Pokemon.TurnStrategy.SPECIAL_ATTACK);
                break;
        }
    }

    Pokemon choosePokemon() {
        for (Pokemon pokemon: this.pokemons.values()) {
            System.out.println(pokemon);
        }
        String chosenPokemon = Main.in.next();
        for (String name: this.pokemons.keySet()) {
            if (name.startsWith(chosenPokemon)) {return this.pokemons.get(name);}
        }
        System.out.println("Pokemon.Pokemon not found. Choose another");
        return this.choosePokemon();
    }
}
