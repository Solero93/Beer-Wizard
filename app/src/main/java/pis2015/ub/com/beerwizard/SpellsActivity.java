package pis2015.ub.com.beerwizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SpellsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells);
        getSupportActionBar().setIcon(R.drawable.cara);//pone la cara en la barra
        //setTitle("cacaman");//para cambiar el nombre de usuario
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spells, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return super.onCreateOptionsMenu(menu);

    }
    public void onClickSpell(View v) {

        Intent intent = new Intent(this, CastSpellActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_tutorial) {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
