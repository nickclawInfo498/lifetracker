package edu.washington.nickclaw.lifetracker;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by nickclaw on 1/26/15.
 */
public class Player {

    private View view;
    private String name;
    private int health;
    private DeathListener listener;

    public Player(String name) {
        this(name, 20);
    }

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(health, 0);

        if (didLose() && listener != null) {
            listener.onDeath(this);
        }
    }

    public boolean didLose() {
        return health <= 0;
    }

    public void setOnDeathListener(DeathListener listener) {
        this.listener = listener;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return this.view;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + health + ")";
    }

    public static interface DeathListener {
        public void onDeath(Player p);
    }
}
