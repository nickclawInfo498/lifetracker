package edu.washington.nickclaw.lifetracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private static Button removeButton;
    private static Button addButton;
    private static ArrayAdapter<Player> adapter;
    private static TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.listView);
        removeButton = (Button) findViewById(R.id.removeButton);
        addButton = (Button) findViewById(R.id.addButton);
        alert = (TextView) findViewById(R.id.alertView);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePlayer();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newPlayer();
            }
        });

        adapter = new PlayerAdapter(this, R.layout.listitem, R.id.textView);

        if (savedInstanceState != null && savedInstanceState.containsKey("players")) {
            ArrayList<Integer> players = savedInstanceState.getIntegerArrayList("players");
            for(int i = 0; i < players.size(); i++) {
                Player player = new Player("Player " + (i + 1), players.get(i));
                player.setOnDeathListener(deathListener);
                adapter.add(player);
                updateButtons();
            }
        } else {
            newPlayer();
            newPlayer();
            newPlayer();
            newPlayer();
        }

        list.setAdapter(adapter);

    }

    public void onSaveInstanceState(Bundle state) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        for (int i = 0; i < adapter.getCount(); i++) {
            l.add(adapter.getItem(i).getHealth());
        }
        state.putIntegerArrayList("players", l);
    }

    public void newPlayer() {
        Player player = new Player("Player " + (adapter.getCount() + 1));
        player.setOnDeathListener(deathListener);
        adapter.add(player);
        updateButtons();
    }

    public void removePlayer() {
        adapter.remove(adapter.getItem(adapter.getCount() - 1));
        updateButtons();
    }

    public void updateButtons() {
        if (adapter.getCount() >= 8) {
            removeButton.setEnabled(true);
            addButton.setEnabled(false);
        } else if (adapter.getCount() <= 2) {
            removeButton.setEnabled(false);
            addButton.setEnabled(true);
        } else {
            removeButton.setEnabled(true);
            addButton.setEnabled(true);
        }
    }

    private static Player.DeathListener deathListener = new Player.DeathListener() {
        public void onDeath(Player p) {
            alert.setText(p.getName() + " LOSES");
        }
    };
}
