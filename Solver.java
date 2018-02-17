//used for creating random numbers
import java.util.Random;
//used for filling array
import java.util.Arrays;

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
    int wins = 0;
    int loses = 0;

    //whether to switch doors or not
    switchDoor = true;

    //number of doors in the game, given by command line argument
    noOfDoors = Integer.parseInt(args[0]);

    //make an entry in the array for each door
    doors = new char[noOfDoors];

    /*repeat game enough times for results to be representetive of
      probabilities, and keep track of score*/
    for (int i = 0; i < 1000000; i++)
    {
      if(playGame())
        wins++;
      else
        loses++;
    }//for

    //print results
    System.out.println("Wins: {" + wins + "} Loses: {" + loses + "}");
    System.out.println("Chance of winning is: " + (double)wins / (wins + loses)
                        *100 + "%");
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

  private static boolean playGame()
  {
    //set up game environment
    initialiseGame();

    //randomly pick a door
    pickedDoor = rand.nextInt(noOfDoors);

    //keep opening doors until there are only 2 closed ones
    while (closedDoors > 2)
    {
      openDoor();

      //switch if this is the last chance to do so
      if(closedDoors == 2)
        switchDoor();
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
