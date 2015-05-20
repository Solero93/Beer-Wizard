package pis2015.ub.com.beerwizard.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import pis2015.ub.com.beerwizard.R;
import pis2015.ub.com.beerwizard.game.SpellManager;
import pis2015.ub.com.beerwizard.network.GameData;
import pis2015.ub.com.beerwizard.util.Constants;

/*
The activity where you can select a spell.
 */
public class SpellsActivity extends ActionBarActivity {
    public Handler spellsHandler;
    private int[] tText, tImage, tSpell; // Tables of Component identifiers
    private int lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.tText = new int[]{
                R.id.textViewSpell1, R.id.textViewSpell2, R.id.textViewSpell3, R.id.textViewSpell4,
                R.id.textViewSpell5, R.id.textViewSpell6, R.id.textViewSpell7, R.id.textViewSpell8};
        this.tImage = new int[]{
                R.id.imageViewSpell1, R.id.imageViewSpell2, R.id.imageViewSpell3, R.id.imageViewSpell4,
                R.id.imageViewSpell5, R.id.imageViewSpell6, R.id.imageViewSpell7, R.id.imageViewSpell8};
        this.tSpell = new int[]{
                R.id.spell1, R.id.spell2, R.id.spell3, R.id.spell4,
                R.id.spell5, R.id.spell6, R.id.spell7, R.id.spell8};

        this.initSpellsHandler(); // Initializes spellsHandler

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_screen);
        getSupportActionBar().setIcon(R.drawable.cara);//change the icon, avatar
        setTitle(GUIFacade.getUserName());//change the Nickname
        TextView ruleText = (TextView) findViewById(R.id.textRule);
        ruleText.setText(GUIFacade.getRule());

        lvl = 1;
        if (savedInstanceState != null) {
            for (int i = 1; i < savedInstanceState.getInt("lvl"); i++) {
                lvlUp();
                if (SpellManager.isSpellCooldown(i - 1)) {
                    cooldown(i - 1);
                }
            }
        } else if (GUIFacade.getLevel() > 1) {
            for (int i = 1; i < GUIFacade.getLevel(); i++) {
                lvlUp();
                if (SpellManager.isSpellCooldown(i - 1)) {
                    cooldown(i - 1);
                }
            }
        }
        GameData.setSpellsActivityHandler(this.spellsHandler); // Assigns Handler to GameData
    }

    //Action bar constructor
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spells, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return super.onCreateOptionsMenu(menu);

    }

    //Disabling Back Button in this activity
    public void onBackPressed() {
    }

    //Save de info if it restarts
    protected void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        icicle.putInt("lvl", lvl);
    }
    /*
    event of the spells
     */
    public void onClickSpell(View v) {
        int id = v.getId();
        int i;
        for (i = 0; i <= 7; i++) {
            if ((id == tSpell[i]) && (lvl >= (i + 2)) && (!SpellManager.isSpellCooldown(i))) {
                Intent intent = new Intent(this, CastSpellActivity.class);
                intent.putExtra("spell", i); //Your id
                startActivityForResult(intent, 1);
                break;
            }

        }
        /*
        Intent intent = new Intent(this, CastSpellActivity.class);
        if ((id == R.id.spell1) && (this.lvl >= 2)) {
            intent.putExtra("spell", SpellManager.CAN_TO_THE_FACE); //Your id
            startActivityForResult(intent, 1);
        } else if ((id == R.id.spell2) && (this.lvl >= 3)) {
            intent.putExtra("spell", SpellManager.WIZARD_DUEL); //Your id
            startActivityForResult(intent, 1);
        } else if ((id == R.id.spell3) && (this.lvl >= 4)) {
            intent.putExtra("spell", SpellManager.BEEREKINESIS); //Your id
            startActivityForResult(intent, 1);
        } else if ((id == R.id.spell4) && (this.lvl >= 5)) {
            intent.putExtra("spell", SpellManager.SHIELD); //Your id
            startActivityForResult(intent, 1);
        } else if ((id == R.id.spell5) && (this.lvl >= 6)) {
            intent.putExtra("spell", SpellManager.CREATE_RULE); //Your id
            startActivityForResult(intent, 1);
        } else if ((id == R.id.spell6) && (this.lvl >= 7)) {
            intent.putExtra("spell", SpellManager.TRUTH_OR_SHOT); //Your id
            startActivityForResult(intent, 1);
        } else if ((id == R.id.spell7) && (this.lvl >= 8)) {
            intent.putExtra("spell", SpellManager.HAT_OF_SHAME); //Your id
            startActivityForResult(intent, 1);
        } else if ((id == R.id.spell8) && (this.lvl >= 9)) {
            intent.putExtra("spell", SpellManager.ALL_IN_BEER); //Your id
            startActivityForResult(intent, 1);
        }*/
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent refresh = new Intent(this, SpellsActivity.class);
            startActivity(refresh);
            this.finish();
        } else if ((resultCode <= 7) && (resultCode >= 0)) {
            cooldown(resultCode);

        }
    }

    /*
    options and settings
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_lvl_up:
                GUIFacade.levelUp();
                break;
            case R.id.action_profile: // Profile
                intent = new Intent(this, ProfileActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.action_tutorial: // Tutorial
                intent = new Intent(this, TutorialActivity.class);
                startActivity(intent);
                break;
            case R.id.action_exit: // Exit To Menu
                GUIFacade.exitGame(this);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void lvlUp() {

        if (lvl < 9) {
            lvl++;
            TextView textLvl = (TextView) findViewById(R.id.textLvl);
            ImageView image = (ImageView) findViewById(tImage[lvl - 2]);
            TextView text = (TextView) findViewById(tText[lvl - 2]);
            textLvl.setText("Level " + lvl);
            text.setText(SpellManager.getSpellName(lvl - 2));
            image.setImageResource(SpellManager.getSpellImage(lvl - 2));
        } else if (lvl == 9) {
            lvl++;
            masterUp();
        }
    }

    private void lvlDown() {
        TextView textLvl = (TextView) findViewById(R.id.textLvl);
        textLvl.setText("Level " + lvl);
        if (lvl > 9) {
            lvl--;
            textLvl.setText("Level " + lvl);
        } else if (lvl > 1) {
            lvl--;
            ImageView image = (ImageView) findViewById(tImage[lvl - 1]);
            TextView text = (TextView) findViewById(tText[lvl - 1]);
            textLvl.setText("Level " + lvl);
            text.setText(SpellManager.getSpellLockedText(lvl - 1));
            image.setImageResource(R.drawable.candado);
        }
    }

    private void masterUp() {
        TextView textLvl = (TextView) findViewById(R.id.textLvl);
        textLvl.setText("MASTER");
    }

    private void masterDown() {
        TextView textLvl = (TextView) findViewById(R.id.textLvl);
        textLvl.setText("Level " + lvl);
    }

    private void cooldown(final int idSpell) {
        final ImageView image = (ImageView) findViewById(tImage[idSpell]);
        final TextView text = (TextView) findViewById(tText[idSpell]);
        image.setImageResource(R.drawable.candado);
        SpellManager.startSpellCooldown(idSpell);
        new CountDownTimer(SpellManager.getSpellCooldown(idSpell), 1000) {//CountDownTimer(edittext1.getText()+edittext2.getText()) also parse it to long

            public void onTick(long millisUntilFinished) {
                text.setText("" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                image.setImageResource(SpellManager.getSpellImage(idSpell));
                text.setText(SpellManager.getSpellName(idSpell));
            }
        }
                .start();
    }


    private void cooldownReload(final int idSpell) {
        final ImageView image = (ImageView) findViewById(tImage[idSpell]);
        final TextView text = (TextView) findViewById(tText[idSpell]);
        image.setImageResource(R.drawable.candado);
        new CountDownTimer(SpellManager.getMilisecondsLeftFromSpellCooldown(idSpell), 1000) {//CountDownTimer(edittext1.getText()+edittext2.getText()) also parse it to long

            public void onTick(long millisUntilFinished) {
                text.setText("" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                image.setImageResource(SpellManager.getSpellImage(idSpell));
                text.setText(SpellManager.getSpellName(idSpell));
            }
        }
                .start();
    }
    /**
     * Auxiliar method to Initialize the Handler that manages the Spell arrivals
     */
    private void initSpellsHandler() {
        this.spellsHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                String edited;
                TextView changetext2;
                String edit;

                switch (inputMessage.what) {
                    case Constants.MSG_DECIDE_LEVEL:
                        final String targetUser1 = (String) inputMessage.obj;
                        /**
                         * Here we create a popup using a LayoutInflater to allow players to lvl up
                         * sending a request to another player, this player can accept the level up
                         * or deny it
                         */
                        //Here we create the layout inflater
                        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        //Here we associate the layout inflater with the layout we need, in this case popup_lvlup
                        View popupView = layoutInflater.inflate(R.layout.popup_lvlup, null);
                        //Now we create the popup window
                        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        //Time to only interact with the popup
                        popupWindow.setFocusable(true);

                        //Now we change the text on the TextView to show WHO wants to level up
                        final String who_lvl = GUIFacade.getUserName(targetUser1);
                        String texto = getResources().getString(R.string.level_up_popup_name);
                        String strMeatMsg = String.format(texto, who_lvl);
                        TextView changetext = (TextView) popupView.findViewById(R.id.name_lvl);
                        changetext.setText(strMeatMsg);

                        //Now we declare the 2 possible buttons, player can level ul, or player cannot level up
                        //this button increases the player level in 1
                        Button btnlvlup = (Button) popupView.findViewById(R.id.btn_lvlup);
                        btnlvlup.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                GUIFacade.levelUp(targetUser1);
                                popupWindow.dismiss();
                            }
                        });
                        // If player tries to fake a level up, the player who receives this popup can dismiss his intent to level up
                        Button btn_NO_lvlup = (Button) popupView.findViewById(R.id.btn_NO_lvlup);
                        btn_NO_lvlup.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                popupWindow.dismiss();
                            }
                        });

                        //We set the animation to show the popup
                        popupWindow.setAnimationStyle(R.style.popup_animation);
                        //Here we show the position where will appear the popup
                        popupWindow.showAtLocation(findViewById(R.id.handle), Gravity.CENTER, 0, 0);

                        break;
                    case Constants.MSG_LEVEL_UP:
                        lvlUp();
                        break;

                    case Constants.MSG_LEVEL_DOWN:
                        lvlDown();
                        break;
                    case Constants.MSG_CASTED_SPELL:

                        int idSpell = (int) ((Object[]) inputMessage.obj)[0];
                        String targetUser = (String) ((Object[]) inputMessage.obj)[1];
                        String param = (String) ((Object[]) inputMessage.obj)[2];
                        //If user has shield, spell is not casted and shield is broken.
                        if (GUIFacade.getUserShield()) {
                            GUIFacade.breakUserShield();
                            break;
                        }

                        /**
                         * Here we create a popup using a LayoutInflater to allow players to lvl up
                         * sending a request to another player, this player can accept the level up
                         * or deny it
                         */
                        LayoutInflater layoutInflater2 = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView2 = layoutInflater2.inflate(R.layout.popup_received_spell, null);
                        final PopupWindow popupWindow2 = new PopupWindow(popupView2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow2.setFocusable(true);


                        String descr = getResources().getString(SpellManager.getSpellDescription(idSpell));
                        String name = GUIFacade.getUserName(targetUser);
                        String spellName = getResources().getString(SpellManager.getSpellName(idSpell));

                        edit = getResources().getString(R.string.popup_received_spell_user_spell);
                        changetext2 = (TextView) popupView2.findViewById(R.id.name_spell);
                        edited = String.format(edit, name, spellName);
                        changetext2.setText(edited);

                        if (param != "") {
                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, param);
                            changetext2.setText(edited);
                        } else {
                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, descr);
                            changetext2.setText(edited);
                        }

                        Button got = (Button) popupView2.findViewById(R.id.btn_got);
                        got.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                popupWindow2.dismiss();
                            }
                        });

                        //We set the animation to show the popup
                        popupWindow2.setAnimationStyle(R.style.popup_animation);
                        //Here we show the position where will appear the popup
                        popupWindow2.showAtLocation(findViewById(R.id.handle), Gravity.CENTER, 0, 0);

                        break;
                    case Constants.MSG_UPDATE_RULE:
                        String newRule = (String) inputMessage.obj;
                        Log.d("Rule", newRule);
                        TextView ruleText = (TextView) findViewById(R.id.textRule);
                        ruleText.setText(newRule);
                        break;
                    case Constants.MSG_DECIDE_DUEL:
                        final String user1 = (String) ((Object[]) inputMessage.obj)[0];
                        final String user1_name = GUIFacade.getUserName(user1);

                        final String user2 = (String) ((Object[]) inputMessage.obj)[1];
                        final String user2_name = GUIFacade.getUserName(user2);

                        LayoutInflater layoutInflater3 = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView3 = layoutInflater3.inflate(R.layout.popup_duel, null);
                        final PopupWindow popupWindow3 = new PopupWindow(popupView3, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow3.setFocusable(true);

                        edit = getResources().getString(R.string.duel_user1);
                        changetext2 = (Button) popupView3.findViewById(R.id.player1);
                        edited = String.format(edit, user1_name);
                        changetext2.setText(edited);

                        edit = getResources().getString(R.string.duel_user2);
                        changetext2 = (Button) popupView3.findViewById(R.id.player2);
                        edited = String.format(edit, user2_name);
                        changetext2.setText(edited);

                        Button win1 = (Button) popupView3.findViewById(R.id.player1);
                        Button win2 = (Button) popupView3.findViewById(R.id.player2);
                        win1.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                GUIFacade.levelUp(user1);
                                GUIFacade.levelDown(user2);
                                popupWindow3.dismiss();
                            }

                        });
                        win2.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                GUIFacade.levelUp(user2);
                                GUIFacade.levelDown(user1);
                                popupWindow3.dismiss();
                            }
                        });

                        popupWindow3.setAnimationStyle(R.style.popup_animation);
                        popupWindow3.showAtLocation(findViewById(R.id.handle), Gravity.CENTER, 0, 0);
                        break;
                }
            }
        };
    }
}