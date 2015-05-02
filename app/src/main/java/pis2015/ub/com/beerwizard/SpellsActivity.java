package pis2015.ub.com.beerwizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/*
The activity where you can select a spell.
 */
public class SpellsActivity extends ActionBarActivity {
    private int lvl;
    //temporal, cuando tengan los metodos lvl hechos
    int[] tText= new int[]{R.id.textViewSpell1, R.id.textViewSpell2, R.id.textViewSpell3, R.id.textViewSpell4, R.id.textViewSpell5, R.id.textViewSpell6, R.id.textViewSpell7, R.id.textViewSpell8};
    int[] tImage= new int[]{R.id.imageViewSpell1, R.id.imageViewSpell2, R.id.imageViewSpell3, R.id.imageViewSpell4, R.id.imageViewSpell5, R.id.imageViewSpell6, R.id.imageViewSpell7, R.id.imageViewSpell8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells);
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

        Intent intent = new Intent(this, CastSpellActivity.class);
        startActivity(intent);
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
            lvl++;
            if(lvl<10){
                TextView textLvl = (TextView) findViewById(R.id.textLvl);
                ImageView image = (ImageView) findViewById(tImage[lvl-2]);
                TextView text = (TextView) findViewById(tText[lvl-2]);
                textLvl.setText("Level "+lvl);
                text.setText("CAN TO \nTHE FACE");
                image.setImageResource(R.drawable.duel_of_wizards);
            }

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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
