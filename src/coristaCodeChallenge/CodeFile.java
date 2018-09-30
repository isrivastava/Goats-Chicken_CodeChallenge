package coristaCodeChallenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CodeFile {

	public static void main(String args[]) throws IOException {
		// The name of the file to open.
		String fileName = "GuessNumber.txt";

		String number = null;
		int[] newNumber = new int[5];
		int[] secretArray = new int[5];
		int MyNumber = 00000;

		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter a 5 digit secret number: ");
		String secretNumber = reader.nextLine(); 
		reader.close();

		// Initialising variables
		int a = 0;
		int GuessCounter = 0;
		int goatsCounter = 0;
		int chickenCounter = 0;
		int goatsCounterOld = 0;

		StringBuilder tempNumber = new StringBuilder();
		tempNumber.append(MyNumber);
		String myNumber = tempNumber.toString();
		try {

			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically append a newline character.
			bufferedWriter.write(myNumber);

			// Always close files.
			bufferedWriter.close();
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((number = bufferedReader.readLine()) != null) {

				for (int i = 0; i < number.length(); i++) {
					newNumber[i] = Integer.parseInt(number.substring(i, i + 1));
				}
			}

			// Always close files.
			bufferedReader.close();
			
			//Writing the code to convert secret number provided by neighbour to integer array
			for (int i = 0; i < secretNumber.length(); i++) {
				secretArray[i] = Integer.parseInt(secretNumber.substring(i, i + 1));
			}
			while (goatsCounter < 5) {
				// Logic to check Goat Count
				for (int i = 0; i < 5; i++) {
					if (newNumber[i] == secretArray[i]) {
						goatsCounter++;
					}
					// Logic to check Chicken Count
					else {
						for (int j = i; j < 5; j++) {
							if (newNumber[i] == secretArray[j]) {
								chickenCounter++;
								break;
							}
						}
					}

				}
				GuessCounter++;
				// Requested Output in console
				StringBuilder Guess = new StringBuilder();
				for (int num : newNumber) {
					Guess.append(num);
				}
				System.out.println("\n" + "Guess: " + Guess);
				System.out.println("Number Of guesses: " + GuessCounter);
				System.out.println("Goats: " + goatsCounter);
				System.out.println("Chicken: " + chickenCounter);

				// Check if we already get best result
				if (goatsCounter == 5) {
					break;
				}
				// To check GuessCounter which will be used in comparison
				if (GuessCounter == 1) {
					newNumber[a] = newNumber[a] + 1;
					goatsCounterOld = goatsCounter;
				}
				// Logic to proceed further if goatsCounter of previous and present number is equal
				else if (goatsCounterOld == goatsCounter) {
					newNumber[a] = newNumber[a] + 1;
				}
				// Logic to proceed further if goatsCounter of previous number is greater present number
				else if (goatsCounterOld > goatsCounter) {
					newNumber[a] = newNumber[a] - 1;
					newNumber[a + 1] = newNumber[a + 1] + 1;
					a = a + 1;
				}
				// Logic to proceed further if goatsCounter of previous number is smaller present number
				else if (goatsCounterOld < goatsCounter) {
					a = a + 1;
					newNumber[a] = newNumber[a] + 1;
					goatsCounterOld = goatsCounterOld + 1;
				}
				goatsCounter = 0;
				chickenCounter = 0;
				
				//Update next number in input file
				StringBuilder strNum = new StringBuilder();
				for (int num : newNumber) {
					strNum.append(num);
				}
				MyNumber = Integer.parseInt(strNum.toString());

				tempNumber = new StringBuilder();
				tempNumber.append(MyNumber);
				myNumber = tempNumber.toString();

				// Assume default encoding.
				fileWriter = new FileWriter(fileName);

				// Always wrap FileWriter in BufferedWriter.
				bufferedWriter = new BufferedWriter(fileWriter);

				// Note that write() does not automatically
				// append a newline character.
				bufferedWriter.write(myNumber);

				// Always close files.
				bufferedWriter.close();
			}
		}

		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
}
