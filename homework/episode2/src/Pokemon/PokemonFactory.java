package Pokemon;

public class PokemonFactory {

    public enum PokemonList {
        PIKACHU("Pikachu", 32, 20, 100, 15),
        SLOWPOKE("Slowpoke", 20, 20, 100, 10),
        PSYDUCK("Psyduck", 20, 20, 120, 15);

        private String name;
        private int attack, defence, health, regeneration;
        PokemonList(String name, int attack, int defence, int health, int regeneration){
            this.name = name;
            this.attack = attack;
            this.defence = defence;
            this.health = health;
            this.regeneration = regeneration;
        }
    }


    public static Pokemon getPokemon(PokemonFactory.PokemonList pokemon){
        return new Pokemon(pokemon.name, pokemon.attack, pokemon.defence, pokemon.health, pokemon.regeneration);
    }

}
