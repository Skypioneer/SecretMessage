package cwang_P2EC;

import java.io.*;              // Needed for File I/O object
import java.util.Scanner;      // Needed for Scanner object

/**
 *This program reads an encoded message from a file supplied by the user and
 * displays the contents of the message before it was encoded.
 * The user can enter multiple files and the decoded result words will become
 * interleaved.
 *
 * @author Jason Wang
 * @version 42.0
 */
public class SecretMessage {
    /**
     * The main method executes three methods and one nested method to unravel
     * the encoded message.
     *
     * @param args A string array containing the command line arguments.
     * @throws IOException when file error occurs.
     */
    public static void main (String[] args) throws IOException {

        // Create a Scanner object
        Scanner keyboard = new Scanner(System.in);

        // Call the welcome method
        welcome();

        // Determine if the user wanna repeat
        do {

            // Call getFileName, checkFile, fileReadIn, and displayContents
            // methods
            displayContents(fileReadIn(getFileName(keyboard)));

        } while (!loopRepeat(keyboard));    // Call the loopRepeat method

        // Call the goodBye method
        goodbye();

        // Close the Scanner object
        keyboard.close();
    }

    /**
     * The welcome method display the welcome message.
     */
    public static void welcome(){

        // Display the welcome message
        System.out.println("This program reads an encoded message from a " +
                "file supplied by the user and\ndisplays the contents of " +
                "the message before it was encoded.\nThe user can enter " +
                "multiple files and the decoded\nresult words will become " +
                " interleaved.");
    }

    /**
     * The loopRepeat method determine if the user wanna repeat.
     *
     * @param keyboard The Scanner object.
     * @return Whether the user wanna repeat.
     */
    public static boolean loopRepeat(Scanner keyboard){
        String answer;      // The user's input
        char ans;           // The first word of the input

        // Ask if the user wanna repeat
        System.out.print("\n\nWould you like to try again? (no to exit): ");
        answer = keyboard.nextLine();
        ans = answer.charAt(0);

        // Return the first letter of the user's input
        return ans == 'n' || ans == 'N';
    }

    /**
     * The fetFileName method prompts for a file name.
     *
     * @param keyboard The Scanner object.
     * @return The file that stores the file name
     */
    public static File getFileName(Scanner keyboard) {
        File file;

        // Determine whether the file exists and is not a directory
        do {
            // Prompt for a file name
            System.out.print("\nEnter secret file name: ");

            // Open the file
            file = new File(keyboard.nextLine());
        }
        while (!checkFile(file));


        System.out.print("Enter another secret file name (or blank): ");

        String fileName = keyboard.nextLine();

        if (fileName.equals(" "))
            return file;
        else


        // Return the file name
        return file;
    }

    /**
     * The checkFile method check if the file exist.
     *
     * @param file The file that stores the file name.
     * @return The file that stores the file name.
     */
    public static boolean checkFile(File file){
        // Check if the file exist
        if (!file.exists() || file.isDirectory()){
            System.out.println("The file is not found. Try again.");
            return false;
        }

        // Return the file
        return true;
    }

    /**
     * The fileReadIn method reads in the contents of the file.
     *
     * @param file The file that stores the file name.
     * @return The MessageDecoder object.
     * @throws IOException when file error occurs.
     */
    public static MessageDecoder fileReadIn(File file)
                                                            throws IOException{
        // Create a MessageDecoder object
        MessageDecoder decoder = new MessageDecoder();

        // Create a Scanner object
        Scanner inputFile = new Scanner(file);

        /*
        InputValue = in.nextLine();
        String[] Value = InputValue.split(" ");
        name[x] = Value[0];
        number[x] = Integer.parseInt(Value[1]);
         */

        // Read the constants of the file into the linked list
        while (inputFile.hasNext()){

            String character;    // The character in each line of the file
            int index;           // The index of the character

            // Assign inputFile split by space to value
            String[] value = inputFile.nextLine().split(" ");

            // Assign the int content to index and
            // assign the String content to character
            if (value[0].equals("")){
                character = " ";
                index = Integer.parseInt(value[2]);

            }
            else {
                character = value[0];
                index = Integer.parseInt(value[1]);
            }

            // Add character and index to the linked list
            decoder.add(character, index);
        }

        // Close the Scanner
        inputFile.close();

        // Return the MessageDecoder object
        return decoder;
    }

    /**
     * The displayContents method unravel the encoded message.
     *
     * @param decoder The MessageDecoder object.
     */
    public static void displayContents(MessageDecoder decoder){

        // Call the prints method of the MessageDecoder object
        decoder.print();
    }

    /**
     * The goodbye method displays the goodbye message.
     */
    public static void goodbye(){

        // Display the goodbye message
        System.out.println("\nThank you for using the message decoder.");
    }
}
