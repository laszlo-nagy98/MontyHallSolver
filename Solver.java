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

  private static boolean switchDoor;

  private static int pickedDoor;
  private static int oldPickedDoor;


  public static void main(String args[])
  {
    //variables for keeping track of wins and loses
    int wins = 0;
    int loses = 0;

    //whether to switch doors or not
    switchDoor = true;
    //number of doors in the game
    noOfDoors = Integer.parseInt(args[0]);

    //make array
    doors = new char[noOfDoors];

    /*repeat game enough times for results to be representetive of
      probabilities*/
    for (int i = 0; i < 1000000; i++)
    {

      //initially all doors are closed
      closedDoors = noOfDoors;

      //initialise array with one car and the rest goats
      Arrays.fill(doors, 'G');
      doors[rand.nextInt(noOfDoors)] = 'C';

      /*for (char c : doors)
        System.out.print(c);
      System.out.println();*/

      //randomly pick a door
      pickedDoor = rand.nextInt(noOfDoors);
      /*System.out.println("Door picked is: " + (pickedDoor + 1));*/
      //keep opening doors until there are only 2
      while (closedDoors > 2)
      {

        int openedDoor = openDoor(pickedDoor);
        /*for (char c : doors)
          System.out.print(c);
        System.out.println();*/
        if(closedDoors == 2)
          switchDoor();
      }//while

      if(doors[pickedDoor] == 'C')
      {
        wins++;
        /*System.out.println("You won!");*/
      }
      else
      {
        loses++;
        /*System.out.println("You lost!");*/
      }

    }//for
    System.out.println("Wins: {" + wins + "} Loses: {" + loses + "}");
    System.out.println("Chance of winning is: " + (double)wins / (wins + loses)
                        *100 + "%");
  }//main

  //method representing the opening of a door with a goat behind it
  private static int openDoor(int pickedDoor)
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
    return doorToOpen;

  }//openDoor

  private static void switchDoor()
  {
    if(switchDoor)
    {
      oldPickedDoor = pickedDoor;
      do
      {
        pickedDoor = rand.nextInt(noOfDoors);
      } while (doors[pickedDoor] == 'O' || pickedDoor == oldPickedDoor);
      oldPickedDoor = pickedDoor;
      /*System.out.println("Door picked is: " + (pickedDoor + 1));*/
    }//if
  }//switchDoor


}//class
