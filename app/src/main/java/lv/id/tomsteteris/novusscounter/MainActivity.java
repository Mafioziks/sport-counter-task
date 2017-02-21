package lv.id.tomsteteris.novusscounter;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreRed = 0;
    private int scoreBlack = 0;
    private int minusesRed = 0;
    private int minusesBlack = 0;
    private int centersRed = 0;
    private int centersBlack = 0;
    private int maxPieces = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateScore("red");
        updateScore("black");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);

        updateScore("red");
        updateScore("black");
    }


    public void addPointRed(View view) {
        scoreRed++;
        updateScore("red");
    }

    public void addPointBlack(View view) {
        scoreBlack++;
        updateScore("black");
    }

    public void addMinusRed(View view) {
        minusesRed++;
    }

    public void addMinusBlack(View view) {
        minusesBlack++;
    }

    public void addCenterRed(View view) {
        centersRed++;
    }

    public void addCenterBlack(View view) {
        centersBlack++;
    }

    public void updateScore(String team) {
        if (team.equals("red")) {
            TextView teamCounter = (TextView) findViewById(R.id.red_score);
            teamCounter.setText("" + scoreRed);
        } else if (team.equals("black")) {
            TextView teamCounter = (TextView) findViewById(R.id.black_score);
            teamCounter.setText("" + scoreBlack);
        }

        if (scoreRed - minusesRed >= maxPieces) {
            TextView gameWinInfo = (TextView) findViewById(R.id.game_win_info);
            gameWinInfo.setText(
                    getText(R.string.winner) + ": " + getText(R.string.red_team) + "\n" +
                    getText(R.string.minuses) + ": " + minusesRed + "\n" +
                    getText(R.string.centers) + ": " + centersRed
            );
            blockCounterButtons();
        }

        if (scoreBlack - minusesBlack >= maxPieces) {
            TextView gameWinInfo = (TextView) findViewById(R.id.game_win_info);
            gameWinInfo.setText(
                    getText(R.string.winner) + ": " + getText(R.string.black_team) + "\n" +
                    getText(R.string.minuses) + ": " + minusesBlack + "\n" +
                    getText(R.string.centers) + ": " + centersBlack
            );
            blockCounterButtons();
        }

        return;
    }

    public void blockCounterButtons() {
        findViewById(R.id.red_point).setEnabled(false);
        findViewById(R.id.red_center).setEnabled(false);
        findViewById(R.id.red_minus).setEnabled(false);
        findViewById(R.id.black_point).setEnabled(false);
        findViewById(R.id.black_center).setEnabled(false);
        findViewById(R.id.black_minus).setEnabled(false);

        ((Button) findViewById(R.id.game_end)).setText(getText(R.string.new_game));
    }

    public void switchGameType(View view) {
        TextView gameTypeInfo = (TextView) findViewById(R.id.game_type);

        if (maxPieces == 8) {
            maxPieces = 16;
            gameTypeInfo.setText(getText(R.string.game_type_team_vs_team));
            return;
        }

        maxPieces = 8;
        gameTypeInfo.setText(getText(R.string.game_type_player_vs_player));
        return;
    }


    public void gameEnd(View view) {
        ((Button) view).setText(getText(R.string.end_game));
        scoreRed = 0;
        scoreBlack = 0;
        minusesRed = 0;
        minusesBlack = 0;
        centersRed = 0;
        centersBlack = 0;
        TextView gameWinInfo = (TextView) findViewById(R.id.game_win_info);
        gameWinInfo.setText("");
        updateScore("red");
        updateScore("black");

        findViewById(R.id.red_point).setEnabled(true);
        findViewById(R.id.red_center).setEnabled(true);
        findViewById(R.id.red_minus).setEnabled(true);
        findViewById(R.id.black_point).setEnabled(true);
        findViewById(R.id.black_center).setEnabled(true);
        findViewById(R.id.black_minus).setEnabled(true);
    }
}
