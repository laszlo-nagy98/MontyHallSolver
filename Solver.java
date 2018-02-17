//used for creating random numbers
import java.util.Random;
//used for filling array
import java.util.Arrays;
//used for getting user input
import java.util.Scanner;

public class Solver
{
  //allows generating random numbers
  private static Random rand = new Random();

  //number of doors in the game
  private static int noOfDoors;

  //number of closed doors
  private static int closedDoors;

  //array representing the doors
  private static char doors[];

  //variable to store our strategy
  private static boolean switchDoor;

  // the door currenctly picked
  private static int pickedDoor;


  public static void main(String args[])
  {
    //variables for keeping track of wins and loses
    int wins;
    int loses;

    // create a scanner so we can read the command-line input
    Scanner scanner = new Scanner(System.in);

    //text explaining the program to the user
    System.out.println("This program simulates the Monty Hall problem for any"
                    + " amount of doors, using three strategies.");
    System.out.println("Strategy 1: never switch doors");
    System.out.println("Strategy 2: always switch doors");
    System.out.println("Strategy 3: only switch doors the last time");

    //prompt to enter number of doors
    System.out.print("\nEnter the number of doors: ");


    try
    {
      //save input in appropriate variable
      noOfDoors = scanner.nextInt();
      if(noOfDoors <= 0)
        throw new Exception();
    }
    catch(Exception e)
    {
      System.out.println("Next time enter a valid number!");
      System.out.println("For now here's the simulation with 3 doors:");
      noOfDoors = 3;
    }
    //make an entry in the array for each door
    doors = new char[noOfDoors];

    //play a set of games with all three strategies
    for(int strategy = 1; strategy <= 3; strategy++)
    {
      wins = 0;
      loses = 0;
      /*repeat game enough times for results to be representetive of
        probabilities, and keep track of score*/
      for (int i = 0; i < 1000000; i++)
      {
        if(playGame(strategy))
          wins++;
        else
          loses++;
      }//for

      //print results
      System.out.println("\nStrategy " + (strategy) + " results:");
      System.out.println("Wins: {" + wins + "} Loses: {" + loses + "}");
      System.out.println("Chance of winning is: " + (double)wins / (wins + loses)
                        *100 + "%");
    }//for
  }//main

  //this method sets up the initial game environment
  private static void initialiseGame()
  {
    //initially all doors are closed
    closedDoors = noOfDoors;

    //initialise array with one car and the rest goats
    Arrays.fill(doors, 'G');
    doors[rand.nextInt(noOfDoors)] = 'C';
  }

  private static boolean playGame(int strategy)
  {
    //set up game environment
    initialiseGame();

    //randomly pick a door
    pickedDoor = rand.nextInt(noOfDoors);

    //keep opening doors until there are only 2 closed ones
    while (closedDoors > 2)
    {
      openDoor();

      //with strategy 2, always switch
      if (strategy == 2)
        switchDoor();
      //with strategy 3, only switch on the last one
      else if (strategy == 3 && closedDoors == 2)
        switchDoor();
      //strategy 1 never switches
    }//while

    //determine whether the player won and return result
    if(doors[pickedDoor] == 'C')
      return true;
    else
      return false;
  }//playGame

  //method representing the opening of a door with a goat behind it
  private static void openDoor()
  {
    //variable stores which door will be opened
    int doorToOpen;

    //find a random goat door that isn't picked
    do
    {
      doorToOpen = rand.nextInt(noOfDoors);
    } while (doors[doorToOpen] != 'G' || doorToOpen == pickedDoor);

    //"open the door" by setting it's value to 'O' in the array
    doors[doorToOpen] = 'O';

    //update the amount of closed doors
    closedDoors--;
  }//openDoor

  //method for switching to another unopened door
  private static void switchDoor()
  {
      //save our current pick in a variable
      int oldPickedDoor = pickedDoor;

      //find a new door that isn't open, and isn't our old pick
      do
      {
        pickedDoor = rand.nextInt(noOfDoors);
      } while (doors[pickedDoor] == 'O' || pickedDoor == oldPickedDoor);

      //update our old pick
      oldPickedDoor = pickedDoor;
  }//switchDoor


}//class
