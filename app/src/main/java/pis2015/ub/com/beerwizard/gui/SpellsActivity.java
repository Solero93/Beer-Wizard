package pis2015.ub.com.beerwizard.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pis2015.ub.com.beerwizard.R;


/*
The activity where you can select a spell.
 */
public class SpellsActivity extends ActionBarActivity {
    //temporal, cuando tengan los metodos lvl hechos
    int[] tText= new int[]{R.id.textViewSpell1, R.id.textViewSpell2, R.id.textViewSpell3, R.id.textViewSpell4, R.id.textViewSpell5, R.id.textViewSpell6, R.id.textViewSpell7, R.id.textViewSpell8};
    int[] tImage= new int[]{R.id.imageViewSpell1, R.id.imageViewSpell2, R.id.imageViewSpell3, R.id.imageViewSpell4, R.id.imageViewSpell5, R.id.imageViewSpell6, R.id.imageViewSpell7, R.id.imageViewSpell8};
    private int lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_screen);
        getSupportActionBar().setIcon(R.drawable.cara);//change the icon, avatar
        //setTitle("nick");//change the Nickname
        lvl=1;
    }

    /*
    Action bar constructor
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spells, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return super.onCreateOptionsMenu(menu);

    }

    /*
    event of the spells
     */
    public void onClickSpell(View v) {
        int id = v.getId();
        if((id == R.id.spell1 ) && (this.lvl>=2)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 1); //Your id
            startActivity(intent);
        }if((id == R.id.spell2)&& (this.lvl>=3)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 2); //Your id
            startActivity(intent);
        }if((id == R.id.spell3) && (this.lvl>=4)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 3); //Your id
            startActivity(intent);
        }if((id == R.id.spell4) && (this.lvl>=5)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 4); //Your id
            startActivity(intent);
        }if((id == R.id.spell5) && (this.lvl>=6)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 5); //Your id
            startActivity(intent);
        }if((id == R.id.spell6) && (this.lvl>=7)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 6); //Your id
            startActivity(intent);
        }if((id == R.id.spell7) && (this.lvl>=8)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 7); //Your id
            startActivity(intent);
        }if((id == R.id.spell8) && (this.lvl>=9)){
            Intent intent = new Intent(this, CastSpell2Activity.class);
            intent.putExtra("spell", 8); //Your id
            startActivity(intent);
        }
    }


    public void lvlUp() {

        if (lvl < 9) {
            lvl++;
            TextView textLvl = (TextView) findViewById(R.id.textLvl);
            ImageView image = (ImageView) findViewById(tImage[lvl - 2]);
            TextView text = (TextView) findViewById(tText[lvl - 2]);
            textLvl.setText("Level " + lvl);
            text.setText(GUIFacade.getSpellName(lvl - 2));
            image.setImageResource(R.drawable.duel_of_wizards);
        }
    }


    public void lvlDown() {

        if (lvl > 1) {
            lvl--;
            TextView textLvl = (TextView) findViewById(R.id.textLvl);
            ImageView image = (ImageView) findViewById(tImage[lvl - 1]);
            TextView text = (TextView) findViewById(tText[lvl - 1]);
            textLvl.setText("Level " + lvl);
            text.setText(GUIFacade.getSpellLockedText(lvl - 1));
            image.setImageResource(R.drawable.candado);
        }
    }

    public void masterUp() {
        TextView textLvl = (TextView) findViewById(R.id.textLvl);
        textLvl.setText("MASTER");
    }


    public void masterDown() {
        TextView textLvl = (TextView) findViewById(R.id.textLvl);
        textLvl.setText("Level " + lvl);
    }
    /*
    options and settings
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //PROILE
        if (id == R.id.action_lvl_up) {
            lvlUp();

        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }

        //TUTORIAL
        if (id == R.id.action_tutorial) {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
            return true;
        }

        //Exit to Menu
        if (id == R.id.action_exit) {
            GUIFacade.exitGame(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
