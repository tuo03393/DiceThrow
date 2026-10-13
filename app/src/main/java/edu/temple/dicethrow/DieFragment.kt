package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

const val DIESIDE = "sidenumber"
class DieFragment : Fragment() {


    val CURRENT_ROLL = "current_roll"

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }

        savedInstanceState?.let {
            it.getInt(CURRENT_ROLL).run {
                currentRoll = this
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (currentRoll == 0)
            throwDie()
        else {
            dieTextView.text = currentRoll.toString()
        }

        view.setOnClickListener{
            throwDie()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) { //Created this.
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_ROLL, currentRoll)
    }
    fun throwDie() {
        //dieTextView.text = (Random.nextInt(dieSides) + 1).toString()

        currentRoll = (Random.nextInt(dieSides) + 1)
        dieTextView.text = currentRoll.toString()
        //This is bad. We'll fix it later.
    }

    //Added companion ojbect. Teachers says that the code he suggested feels incorrect.
   /* companion object {
        fun newInstance (sides: Int) = DieFragment()
            .arguments?.apply {
                Bundle().apply{
                    putInt(DIESIDE, sides)
                }
            }
    } */

}