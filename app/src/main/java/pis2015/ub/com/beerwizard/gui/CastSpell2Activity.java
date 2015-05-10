package pis2015.ub.com.beerwizard.gui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import pis2015.ub.com.beerwizard.R;

public class CastSpell2Activity extends ActionBarActivity {
    int idSpell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_spell2);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        idSpell = b.getInt("spell");//id spell de 1 a 8

        setTitle(GUIFacade.getSpellName(idSpell - 1));//title

        //description and Quote
        TextView description = (TextView) findViewById(R.id.descriptionText);
        TextView quote = (TextView) findViewById(R.id.quoteText);
        description.setText(GUIFacade.getSpellDescription(idSpell - 1));
        quote.setText(GUIFacade.getSpellQuote(idSpell - 1));

    }

    //Event onClick
    public void onClickCast(View v) {
        //En cada caso hara una cosa
        if (idSpell == 1) {

        } else if (idSpell == 2) {

        } else if (idSpell == 3) {

        } else if (idSpell == 4) {

        } else if (idSpell == 5) {

        } else if (idSpell == 6) {

        } else if (idSpell == 7) {

        } else if (idSpell == 8) {

        }
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cast_spell2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
