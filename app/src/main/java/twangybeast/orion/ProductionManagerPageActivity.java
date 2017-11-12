package twangybeast.orion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductionManagerPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_manager_page);


        int planetIndex = 0;

        Intent intent = getIntent();
        planetIndex = Integer.parseInt(intent.getStringExtra("planet_index"));

        final Planet planet = FullscreenActivity.gm.planets[planetIndex];
        setTitle(planet.getName());
        TextView defense = (TextView) findViewById(R.id.defense);
        defense.setText("Defense: " + planet.getDefense());
        TextView production = (TextView) findViewById(R.id.production);
        production.setText("Production: " + planet.getProduction());
        TextView troops = (TextView) findViewById(R.id.troops);
        troops.setText("Troops: " + planet.getTroop().getStrength());
        final TextView cPro = (TextView) findViewById(R.id.current_production);
        cPro.setText("Current Production: " + planet.getCurrentProduction().prodName + "(" + planet.getCurrentProduction().cost + ")");
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<Improvement> availableImprovements = FullscreenActivity.gm.pma.getAvailableImprovements(planet);
        availableImprovements.add(new Improvement(TroopManagerActivity.TROOP_NAME, 0,0,0,TroopManagerActivity.getTroopCost(planet.getOwner())));
        ArrayList<String> improvements = new ArrayList<String>();
        for(Improvement improv : availableImprovements) {
            improvements.add(improv.getName() + "(" + improv.getCost() + ")");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, improvements);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Button produce = (Button) findViewById(R.id.button);
        produce.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String[] st = spinner.getSelectedItem().toString().split("\\(");
                for(int i = 0; i < FullscreenActivity.gm.pma.getAvailableImprovements(planet).size(); i++){
                    if(st[0].equals(FullscreenActivity.gm.pma.getAvailableImprovements(planet).get(i).getName())){
                        planet.setCurrentProduction(new CurrentProduction(FullscreenActivity.gm.pma.getAvailableImprovements(planet).get(i).getName(), FullscreenActivity.gm.pma.getAvailableImprovements(planet).get(i).getCost()));
                        cPro.setText("Current Production: " + planet.getCurrentProduction().prodName + "(" + planet.getCurrentProduction().cost + ")");
                        break;
                    }
                }
            }
        });
    }
}
