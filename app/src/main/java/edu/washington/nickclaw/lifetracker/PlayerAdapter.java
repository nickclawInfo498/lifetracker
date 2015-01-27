package edu.washington.nickclaw.lifetracker;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by nickclaw on 1/26/15.
 */
public class PlayerAdapter extends ArrayAdapter<Player> {

    private static View negFive;
    private static View negOne;
    private static View posOne;
    private static View posFive;


    public PlayerAdapter(Context context, int a, int b) {
        super(context, a, b);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        negFive = (View) convertView.findViewById(R.id.negFive);
        negOne = (View) convertView.findViewById(R.id.negOne);
        posOne = (View) convertView.findViewById(R.id.posOne);
        posFive = (View) convertView.findViewById(R.id.posFive);

        negFive.setOnClickListener(listener);
        negOne.setOnClickListener(listener);
        posOne.setOnClickListener(listener);
        posFive.setOnClickListener(listener);

        convertView.setTag(R.integer.abc_max_action_buttons, getItem(position));

        return convertView;
    }

    private static View.OnClickListener listener = new View.OnClickListener(){
        public void onClick(View view) {
            Player player = (Player) ((View) view.getParent().getParent()).getTag(R.integer.abc_max_action_buttons);
            int health = player.getHealth();

            switch(view.getId()) {
                case R.id.negFive:
                    player.setHealth(health - 5);
                    break;
                case R.id.negOne:
                    player.setHealth(health - 1);
                    break;
                case R.id.posOne:
                    player.setHealth(health + 1);
                    break;
                case R.id.posFive:
                    player.setHealth(health + 5);
                    break;
            }

            // oh please god, spare my soul, for I know not what I have done.
            ((TextView) ((View) view.getParent().getParent()).findViewById(R.id.textView)).setText(player.toString());

            Log.i("test", player.toString());
        }
    };
}
