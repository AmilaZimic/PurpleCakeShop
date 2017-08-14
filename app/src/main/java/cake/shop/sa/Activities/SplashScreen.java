package cake.shop.sa.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cake.shop.sa.R;

public class SplashScreen extends AppCompatActivity {

    /* Splash screen timer */
    private static int SPLASH_TIME_OUT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /* Showing splash screen with a timer. */

            @Override
            public void run() {
                /* This method will be executed once the timer is over. */
                /* Start SignupActivity */
                Intent i = new Intent(SplashScreen.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
