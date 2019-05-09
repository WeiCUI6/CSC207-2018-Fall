package fall2018.csc2017.GameCenter.Hanoi;

/*
Adapted from:
https://github.com/ashokgujju/Towers-of-Hanoi/blob/master/src/com/as/tohanoi/Levels.java
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import fall2018.csc2017.GameCenter.R;
import fall2018.csc2017.GameCenter.Score;
import fall2018.csc2017.GameCenter.UserManager;

public class ChooseLevelActivity extends Activity implements OnClickListener {


    /**
     * Create a new activity view.
     *
     * @param bundle the layout
     */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hanoi_level);

        // Set the button
        findViewById(R.id.easy).setOnClickListener(this);
        findViewById(R.id.medium).setOnClickListener(this);
        findViewById(R.id.hard).setOnClickListener(this);

        addBackButtonListener();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ChooseLevelActivity.this, HanoiGameActivity.class);
        UserManager userManager = UserManager.getInstance();
        Score score = userManager.getCurrentUser().getScore();

        // Link the button i.e. easy, medium and hard to new game of hanoi
        switch (v.getId()) {
            case R.id.easy:
                intent.putExtra("numofdisks", 4);
                userManager.setCurrentGameType(3);
                score.resetScoreTemp(3);
                break;

            case R.id.medium:
                intent.putExtra("numofdisks", 5);
                userManager.setCurrentGameType(4);
                score.resetScoreTemp(4);
                break;

            case R.id.hard:
                intent.putExtra("numofdisks", 6);
                userManager.setCurrentGameType(5);
                score.resetScoreTemp(5);
                break;
        }
        startActivity(intent);
    }

    /**
     * Activate the Back Button.
     */
    private void addBackButtonListener() {
        Button backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this,
                        StartActivity.class);
                startActivity(intent);
            }
        });
    }
}