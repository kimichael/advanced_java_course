import Pokemon.Pokemon;

/**
 * Created by mikim on 06.11.16.
 */
public class BattleSession {

    private PokemonTrainer firstTrainer;
    private PokemonTrainer secondTrainer;
    private Pokemon firstPokemon;
    private Pokemon secondPokemon;
    private int turn = 1;
    BattleState battleState = BattleState.IN_PROGRESS;

    private enum BattleState {
        FIRST_WON,
        SECOND_WON,
        IN_PROGRESS
    }

    public BattleSession(PokemonTrainer firstTrainer, PokemonTrainer secondTrainer){
        this.firstTrainer = firstTrainer;
        this.secondTrainer = secondTrainer;
        startBattle();
    }

    public void startBattle(){
        System.out.println(firstTrainer.getName() + ", choose pokemon: ");
        firstPokemon = firstTrainer.choosePokemon();
        System.out.println(secondTrainer.getName() + ", choose pokemon: ");
        secondPokemon = secondTrainer.choosePokemon();
        System.out.println("Battle" + "\n" +
                firstPokemon.toString() + "\n" +
                "VS" + "\n" +
                secondPokemon.toString());
        nextTurn();
    }

    private void nextTurn() {
        firstTrainer.askToChangeStrategy(firstPokemon);
        secondTrainer.askToChangeStrategy(secondPokemon);
        performTurn();
        turn++;
        if (battleState == BattleState.IN_PROGRESS) {
            nextTurn();
        } else {
            endBattle();
        }
    }

    private void performTurn() {
        System.out.println(firstPokemon + "\n" + secondPokemon);
        if (turn % 2 == 1) {
            firstPokemon.performTurn(secondPokemon);
            updateState();
            secondPokemon.performTurn(firstPokemon);
        } else {
            secondPokemon.performTurn(secondPokemon);
            updateState();
            firstPokemon.performTurn(firstPokemon);
        }
    }


    private void updateState(){
        if (firstPokemon.isDead()) {
            battleState = BattleState.SECOND_WON;
        } else if (secondPokemon.isDead()){
            battleState = BattleState.FIRST_WON;
        } else {
            battleState = BattleState.IN_PROGRESS;
        }
    }

    private void endBattle(){
        if (battleState == BattleState.FIRST_WON){
            System.out.println(firstPokemon.getName() + " has won!");
        } else {
            System.out.println(secondPokemon.getName() + " has won!");
        }
        System.out.println(firstPokemon + "\n"
                            + secondPokemon);
    }


}
