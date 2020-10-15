
package com.example.android.mastermind.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.mastermind.R
import com.example.android.mastermind.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var binding: GameFragmentBinding

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        Log.i("GameFragment", "Called ViewModelProvider.get")

        // Get the viewModel
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.guessButton.setOnClickListener {

            onGuess() }
        binding.tryButton.setOnClickListener {

            clearGuessPin()
        }
        binding.endGameButton.setOnClickListener { onEndGame() }
        binding.guesspinview.setPinViewEventListener { pinview, fromUser ->

            var guessText=viewModel.getGuessText((pinview.value).toString())

        }

        updateWordText()
        return binding.root
    }

    private fun onGuess() {
        var randomText=binding.wordText.text
        var guessText=binding.guesspinview.value
        println("Mastermind RandomPin Generated has to be validated"+randomText+guessText)

        var checkResult=  viewModel.convertedString((guessText).toString(),randomText.toString())
        println("Mastermind validation result"+checkResult)
       checkResult as MutableList<Int>
        println("Mastermind matched number of characters"+checkResult.get(0))
        println("Mastermind Number of matched positions"+checkResult.get(1))
        updateWordText(checkResult.get(0),checkResult.get(1))
        var v = if((checkResult.get(0)===4&& checkResult.get(1)===4))  onEndGame()else onFalseGuess()
    }

    private fun updateWordText(charMatchCount: Int, posMatchCount: Int) {
        binding.scoreText.text="Number of character Matched "+charMatchCount+
                "No of character in postion Matched "+posMatchCount

    }
    private fun generateRandomPin() {
        var randomPin=viewModel.generateRandomString()
        println("Mastermind RandomPin Generated generateRandomPin"+randomPin)
    }
    /** Methods for buttons presses **/

    private fun clearGuessPin() {

        binding.guesspinview.clearValue()

        viewModel.clearGuessPin()
        println("Mastermind clear generated guessPin")
    }


    private fun onEndGame() {
        gameFinished()
    }

    /** Methods for updating the UI **/

    private fun updateWordText() {

        binding.wordText.text =viewModel.generateRandomString()
                    }



    /**
     * Called when the game is finished
     */
    private fun onFalseGuess() {
        Toast.makeText(activity, "You can Try Again", Toast.LENGTH_SHORT).show()
           }
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()

    }
}
