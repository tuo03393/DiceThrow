package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), DieFragment.DieRollInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null) //!is DieFragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(sides = 20))
                .commit()

        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            (supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView) as DieFragment).throwDie() //This returns a fragment type.
        }

    }
    override fun rollDice() {
        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null) //!is DieFragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(sides = 20))
                .commit()
    }

}