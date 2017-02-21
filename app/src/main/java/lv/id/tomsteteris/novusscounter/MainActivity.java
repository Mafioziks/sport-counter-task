package lv.id.tomsteteris.novusscounter;

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
                    "Winner: Team Red \n" +
                    "Minuses: " + minusesRed + "\n" +
                    "Centers: " + centersRed
            );
            blockCounterButtons();
        }

        if (scoreBlack - minusesBlack >= maxPieces) {
            TextView gameWinInfo = (TextView) findViewById(R.id.game_win_info);
            gameWinInfo.setText(
                    "Winner: Team Black \n" +
                            "Minuses: " + minusesBlack + "\n" +
                            "Centers: " + centersBlack
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

        ((Button) findViewById(R.id.game_end)).setText("New Game");
    }

    public void switchGameType(View view) {
        TextView gameTypeInfo = (TextView) findViewById(R.id.game_type);

        if (maxPieces == 8) {
            maxPieces = 16;
            gameTypeInfo.setText("Game type: Team vs Team");
            return;
        }

        maxPieces = 8;
        gameTypeInfo.setText("Game type: Player vs Player");
        return;
    }


    public void gameEnd(View view) {
        ((Button) view).setText("End Game");
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
