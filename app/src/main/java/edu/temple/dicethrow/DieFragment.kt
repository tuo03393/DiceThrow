package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

const val DIESIDE = "sidenumber"
class DieFragment : Fragment() {

    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }
    //If you change requireActivity() to this, then if you have multiple fragments reading the view model
    //Each one will create its own view model. This would let us have two separate dice that can have two independent values being displayed.

    val CURRENT_ROLL = "current_roll"

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    //var currentRoll: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
/*
        savedInstanceState?.let {
            it.getInt(CURRENT_ROLL).run {
                currentRoll = this
            }
        }
*/
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

        dieViewModel.getRoll().observe(viewLifecycleOwner){ rollValue ->
            dieTextView.text = rollValue.toString()
        }
/*
        if (currentRoll == 0)
            throwDie()
        else {
            dieTextView.text = currentRoll.toString()
        }
*/
        //if (clickable)
        view.setOnClickListener{
            //throwDie()
            //(requireActivity() as MainActivity).rollDice() works but is bad because it means this fragment cannot be used except with this specific main activity.
            //Instead use this:
            if (requireActivity() is DieRollInterface)
                (requireActivity() as DieRollInterface).rollDice()
        }
    }
/*
    override fun onSaveInstanceState(outState: Bundle) { //Created this.
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_ROLL, currentRoll)
    } */
    fun throwDie() {
        //dieTextView.text = (Random.nextInt(dieSides) + 1).toString()

        //currentRoll = (Random.nextInt(dieSides) + 1)
        //dieTextView.text = currentRoll.toString()
        //This is bad. We'll fix it later.

        dieViewModel.setRoll(Random.nextInt(dieSides) + 1)
    }

    //Added companion object. Teachers says that the code he suggested feels incorrect.
   companion object {
        fun newInstance(sides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
        }
    }

    interface DieRollInterface {
        fun rollDice()
    }

}