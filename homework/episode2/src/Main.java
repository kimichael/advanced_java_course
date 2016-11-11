import Pokemon.Pokemon;
import Pokemon.PokemonFactory;

import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        PokemonTrainer firstTrainer = new PokemonTrainer("Ash");
        PokemonTrainer secondTrainer = new PokemonTrainer("Abra");
        PokemonTrainer thirdTrainer = new PokemonTrainer("Aaron");
        firstTrainer.addPokemon(PokemonFactory.getPokemon(PokemonFactory.PokemonList.PIKACHU));
        firstTrainer.addPokemon(PokemonFactory.getPokemon(PokemonFactory.PokemonList.PSYDUCK));
        secondTrainer.addPokemon(PokemonFactory.getPokemon(PokemonFactory.PokemonList.SLOWPOKE));
        BattleSession battleSession = new BattleSession(firstTrainer, secondTrainer);
    }
}
