package twangybeast.orion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ProductionManagerPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_manager_page);


        int planetIndex = 0;

        Intent intent = getIntent();
        planetIndex = Integer.parseInt(intent.getStringExtra("planet_index"));

        Planet planet = FullscreenActivity.gm.planets[planetIndex];
        setTitle(planet.getName());
        TextView defense = (TextView) findViewById(R.id.defense);
        defense.setText("Defense: " + planet.getDefense());
        TextView production = (TextView) findViewById(R.id.production);
        production.setText("Production: " + planet.getProduction());
        TextView troops = (TextView) findViewById(R.id.troops);
        troops.setText("Troops: " + planet.getTroop().getStrength());
        TextView cPro = (TextView) findViewById(R.id.current_production);
        cPro.setText("Current Production: " + planet.getCurrentProduction().prodName + "(" + planet.getCurrentProduction().progress + ")");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        Button produce = (Button) findViewById(R.id.button);
        produce.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
    }
}
