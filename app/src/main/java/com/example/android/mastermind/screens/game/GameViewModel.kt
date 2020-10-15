

package com.example.android.mastermind.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * ViewModel containing all the logic needed to run the game
 */
class GameViewModel : ViewModel() {

    var randomString = ""
    var guessString = ""

    // The current word
    var word = ""
    var guess=""

    private lateinit var guessList: MutableList<Int>

    /**
     * Resets the list of words and randomizes the order
     */


    init {
        Log.i("GameViewModel", "GameViewModel created!")

    }

    /**
     * Callback called when the ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }


    fun getRandomText(randomtxt: String) {
        setRandomText(randomtxt);
        Log.i("GameViewModel", "Mastermind get RandomPin "+randomString)
    }
    fun setRandomText(randomtxt: String) {
        randomString=randomtxt;
        Log.i("GameViewModel", "Mastermind set RandomPin "+randomtxt)
    }
    fun getGuessText(guesstxt: String) {
        setGuessText(guesstxt)
        Log.i("GameViewModel", "Mastermind get guessText "+guesstxt)
        }
    fun setGuessText(guesstxt: String) {
        guessString=guesstxt;
        Log.i("GameViewModel", "Mastermind set guessText "+guessString)
    }
    fun clearGuessPin() {
        guessString="";
        Log.i("GameViewModel", "Mastermind clearGuessPin "+guessString)
    }

    fun convertToCharacterArray(str: String): CharArray {
        return str.toCharArray()
    }

    fun convertedString(guesstxt: String, randomtext: String) : Any{
        //val randomWord = generateRandomString()
        word =randomtext
        guess=guesstxt
        val quizChar: CharArray = convertToCharacterArray(word)
        val guessChar: CharArray = convertToCharacterArray(guesstxt)

        Log.i("GameViewModel", "Mastermind created random character"+quizChar.contentToString())
        Log.i("GameViewModel", "Mastermind created guess character "+guessChar.contentToString())

        return chkMatchChar(quizChar,guessChar)


    }

     fun chkMatchChar(quizCharArr: CharArray, guessCharArr: CharArray): Any  {
         var matchCount =0 ;
         var postionMatch=0;
         quizCharArr.forEachIndexed {
             i,c -> println("GameViewModel created! looping quiz word character"+"Index $i Character $c")
             guessCharArr.forEachIndexed {
                 j,ch -> println("GameViewModel created! looping guess word character"+"Index $j Character $ch"+"Index $i Character $c")

                 if (ch == c ) {
                     println("GameViewModel created! charactermatch  index match"+"Index $j Character $ch"+"Index $i Character $c")
                     matchCount=matchCount+1;
                     if (ch == c && i == j) {
                         postionMatch = postionMatch + 1;
                     }

                    }

                 else {
                     println("GameViewModel created! no guess letters match quiz word "+"Index $j Character $ch"+"Index $i Character $c")

                 }
             }
             println("GameViewModel created! no of letters mached "+matchCount+"postion match"+postionMatch)

         }
         guessList=mutableListOf(matchCount,postionMatch)
        return guessList

    }


    fun generateRandomString(): String {
        val randomFirstLetter = ('a'..'z').random()
        val randomSecondLetter = ('a'..'z').random()
        val randomThirdLetter = ('a'..'z').random()
        val randomFourthLetter = ('a'..'z').random()

        val randomText = (randomFirstLetter.toString()+randomSecondLetter.toString()+randomThirdLetter.toString()+randomFourthLetter.toString())
        println("Mastermind RandomPin Generated"+randomText)
        getRandomText(randomText)
        return randomText



    }

}
