package assignment.dictionary;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import javafx.application.Platform;

/**
 * A Thread that contains the application we are going to animate
 *
 */

public class MisSpellActionThread implements Runnable {

    DictionaryController controller;
    private final String textFileName;
    private final String dictionaryFileName;

    private LinesToDisplay myLines;
    private DictionaryInterface<String, String> myDictionary;
    private boolean dictionaryLoaded;

    /**
     * Constructor for objects of class MisspellActionThread
     *
     * @param controller
     */
    public MisSpellActionThread(DictionaryController controller) {
        super();

        this.controller = controller;
        textFileName = "src/main/resources/assignment/dictionary/check.txt";
        dictionaryFileName = "src/main/resources/assignment/dictionary/sampleDictionary.txt";

        myDictionary = new HashedMapAdaptor<String, String>();
        myLines = new LinesToDisplay();
        dictionaryLoaded = false;

    }

    @Override
    public void run() {

        loadDictionary(dictionaryFileName, myDictionary);


        Platform.runLater(() -> {
            if (dictionaryLoaded) {
               controller.SetMsg("The Dictionary has been loaded"); 
            } else {
               controller.SetMsg("No Dictionary is loaded"); 
            }
        });
        
        checkWords(textFileName, myDictionary);

    }

    /**
     * Load the words into the dictionary.
     *
     * @param theFileName The name of the file holding the words to put in the
     * dictionary.
     * @param theDictionary The dictionary to load.
     */
    public void loadDictionary(String theFileName, DictionaryInterface<String, String> theDictionary) {
        Scanner input;
        try {
            String inString;
            String correctWord;

            input = new Scanner(new File(theFileName));


            while (input.hasNext()) // read until  end of file
            {
                correctWord = input.next();
                theDictionary.add(correctWord, correctWord);
            }


            // TEST HASH MAP
            Iterator<String> itr = theDictionary.getKeyIterator();
            while (itr.hasNext()){
                String test = itr.next();
                String itrVal = theDictionary.getValue(test);
                System.out.println(test + ": " + itrVal);
            }
            // END TEST


            dictionaryLoaded = true;
           
            
            

        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }

    }

    /**
     * Get the words to check, check them, then put Wordlets into myLines. When
     * a single line has been read do an animation step to wait for the user.
     *
     */
    public void checkWords(String theFileName, DictionaryInterface<String, String> theDictionary) {
        Scanner input;
        try {
            String inString;
            String aWord;

            input = new Scanner(new File(theFileName));


            while (input.hasNextLine()) // Read until end of file
            {
// ADD CODE HERE
// >>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                inString = input.nextLine(); //read 1 line at a time
                Scanner scnr = new Scanner(inString); //new string stream
                scnr.useDelimiter("\\b");

                while (scnr.hasNext()) {
                    aWord = scnr.next();    //iterating each word one by one

                    //if the current word contains all characters
                    if (aWord.matches("[a-zA-Z]+")) {

                        //check if the current word is in the dictionary
                        boolean spellCorrect = checkWord(aWord, theDictionary);
                        Wordlet wordToAdd = new Wordlet(aWord, spellCorrect);
                        myLines.addWordlet(wordToAdd);
                    }

                    //if the current word is not a character aka punctuation marks
                    else{
                        Wordlet otherChars = new Wordlet(aWord, true);
                        myLines.addWordlet(otherChars);
                    }
                }

                //show the wordlet on interface
                showLines(myLines);
                myLines.nextLine();

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


            }

        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }

    }

    /**
     * Check the spelling of a single word.
     *
     */
    public boolean checkWord(String word, DictionaryInterface<String, String> theDictionary) {
        boolean result = false;

        // ADD CODE HERE
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        if (theDictionary.contains(word)) {
            result = true;
        }

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        return result;

    }

    private void showLines(LinesToDisplay lines) {
        try {
            Thread.sleep(500);
            Platform.runLater(() -> {
                if (myLines != null) {
                    controller.UpdateView(lines);
                }
            });
        } catch (InterruptedException ex) {
        }
    }

} // end class MisspellActionThread

