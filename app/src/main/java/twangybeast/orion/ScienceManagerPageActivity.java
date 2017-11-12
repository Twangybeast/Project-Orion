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

public class ScienceManagerPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_manager_page);
        setTitle("Science");
        int playerIndex = 0;

        Intent intent = getIntent();
        playerIndex = Integer.parseInt(intent.getStringExtra("player_index"));

        final Player player = FullscreenActivity.gm.players[playerIndex];
        final TextView cSci = (TextView) findViewById(R.id.current_science);
//        String science_name = "None";
//        String time = "Complete";
//        if(player.getCurrentResearch().scienceName != "Science Level 1"){
//            science_name = player.getCurrentResearch().scienceName;
//            time =    player.getCurrentResearch().progress + "/" + FullscreenActivity.gm.sma.getScience(player.getCurrentResearch().scienceName).getCost();
//        }
//        System.out.println(science_name);
        cSci.setText("Current Science: " + player.getCurrentResearch().scienceName);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<Science> availableSciences = FullscreenActivity.gm.sma.getAvailableSciences(player.getSciences());

        ArrayList<String> sciences = new ArrayList<String>();
        for(Science science : availableSciences) {
            sciences.add(science.getName() + "(" + science.getCost() + ")");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, sciences);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Button produce = (Button) findViewById(R.id.button);
        produce.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String[] st = spinner.getSelectedItem().toString().split("\\(");
                for(int i = 0; i < FullscreenActivity.gm.sma.getAvailableSciences(player.getSciences()).size(); i++){
                    if(st[0].equals(FullscreenActivity.gm.sma.getAvailableSciences(player.getSciences()).get(i).getName())){
                        player.setCurrentResearch(new CurrentResearch(FullscreenActivity.gm.sma.getAvailableSciences(player.getSciences()).get(i).getName()));
                        cSci.setText("Current Science: " + player.getCurrentResearch().scienceName);
                        break;
                    }
                }
            }
        });
    }
}
