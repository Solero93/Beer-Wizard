package pis2015.ub.com.beerwizard.game;

/**
 * Singleton Game Class
 */
public class Game {
    private static Game ourInstance = new Game();
    private String name;
    private String rule;

    private Game() {
    }

    public static Game getInstance() {
        return this.ourInstance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }



    /* TODO This shouldn't be here - speak with others
    public void userExitsGame(String idUser) {}
    public void exitGame() {}
    */
}