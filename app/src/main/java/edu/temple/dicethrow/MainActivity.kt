package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show _only_ ButtonFragment if portrait
            - show _both_ fragments if Landscape
          */
        if (savedInstanceState == null) {
            val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

            // Always load the button fragment in container1
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, ButtonFragment())
                .commit()

            // Load the die fragment only if in landscape mode in container2
            if (isLandscape) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container2, DieFragment())
                    .commit()
            }
        }
    }

    /* TODO 2: switch fragments if die rolled and in portrait (no need to switch fragments if Landscape) */

    // This callback function gets invoked when the child Fragment invokes it
    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked() {
        // Check if we're in portrait mode
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Replace the button fragment with the die fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DieFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}