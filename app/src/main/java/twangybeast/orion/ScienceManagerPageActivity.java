package twangybeast.orion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ScienceManagerPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_manager_page);
        setTitle("Science");
        int playerIndex = 0;

        Intent intent = getIntent();
        playerIndex = Integer.parseInt(intent.getStringExtra("player_index"));

        Player player = FullscreenActivity.gm.players[playerIndex];
        TextView cPro = (TextView) findViewById(R.id.current_science);
        String science_name = "None";
        String time = "Complete";
        if(player.getCurrentResearch().scienceName != "Science Level 1"){
            science_name = player.getCurrentResearch().scienceName;
            time =    player.getCurrentResearch().progress + "/" + FullscreenActivity.gm.sma.getScience(player.getCurrentResearch().scienceName).getCost();
        }
        System.out.println(science_name);
        cPro.setText("Current Science: " + science_name + "(" + time +  ")");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        Button produce = (Button) findViewById(R.id.button);
        produce.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
    }
}
