package tw.org.iii.lab08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = (GameView)findViewById(R.id.gameView);
    }

    @Override
    public void finish() {
        if (gameView.timer == null) {
            gameView.timer.purge();
            gameView.timer.cancel();
            gameView.timer = null;
        }
        super.finish();
    }
}
