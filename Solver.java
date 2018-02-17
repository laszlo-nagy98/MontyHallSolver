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

  public static void main(String args[])
  {
    //variables for keeping track of wins and loses
    int wins = 0;
    int loses = 0;

    //number of doors in the game
    noOfDoors = Integer.parseInt(args[0]);

    //initially all doors are closed
    closedDoors = noOfDoors;

    //make array
    doors = new char[noOfDoors];

    /*repeat game enough times for results to be representetive of
      probabilities*/
    for (int i = 0; i < 10000000; i++)
    {
      //initialise array with one car and the rest goats
      Arrays.fill(doors, 'G');
      doors[rand.nextInt(noOfDoors)] = 'C';

      //randomly pick a door
      int pickedDoor = rand.nextInt(noOfDoors);
      /*System.out.println("Door picked is: " + (pickedDoor + 1));*/

      //keep opening doors until there are only 2
      while (closedDoors > 2)
      {
        /*for (char c : doors)
          System.out.print(c);

        System.out.println();*/
        openDoor(pickedDoor);
      }
      /*for (char c : doors)
        System.out.print(c);

      System.out.println();*/
      if(doors[pickedDoor] == 'C')
        wins++;
      else
        loses++;

    }//for
    System.out.println("Wins: {" + wins + "} Loses: {" + loses + "}");
    System.out.println("Chance of winning is: " + (double)wins / (wins + loses)
                        *100 + "%");
  }//main

  //method representing the opening of a door with a goat behind it
  private static void openDoor(int pickedDoor)
  {
    //variable stores which door will be opened
    int doorToOpen;

    //find a random goat door that isn't picked
    do
    {
      doorToOpen = rand.nextInt(noOfDoors - 1);
    } while (doors[doorToOpen] == 'C' || doors[doorToOpen] == pickedDoor
             || doors[doorToOpen] == 'O');

    //"open the door" by setting it's value to 'O' in the array
    doors[doorToOpen] = 'O';

    //update the amount of closed doors
    closedDoors--;
  }//openDoor
}//class
