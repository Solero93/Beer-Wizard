package pis2015.ub.com.beerwizard.network;

import pis2015.ub.com.beerwizard.game.Game;

public class NetworkFacade {
    private String serverIP;
    private Thread serverListener;

    public void castSpell(String idCasterUser, String idTargetUser, String idSpell, String[] params) {

    }

    public void modifyUserProfile(String idUser, String name, String avatar) {

    }

    public void levelUp(String idUser) throws BossException {

    }

    public void enterGame(String serverIP) {
        this.serverIP = serverIP;
        serverListener = new Thread(new ServerListener());
        serverListener.run();
        downloadUsers();
    }

    private void downloadUsers() {

    }

    public void exitGame() {
        serverListener.stop();
    }

    private void userExitsGame(String idUser) {
        Game game = Game.getInstance();
        game.userExitsGame(idUser);
    }
}
