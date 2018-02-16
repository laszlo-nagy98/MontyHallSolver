//used for creating random numbers
import java.util.Random;
//used for filling array
import java.util.Arrays;

public class Solver
{
  public static void main(String args[])
  {
    //local variables for keeping track of wins and loses
    int wins = 0;
    int loses = 0;

    //allows generating random numbers
    Random rand = new Random();

    //number of doors in the game
    int noOfDoors = 3;

    //array representing the doors
    char doors[] = new char[noOfDoors];


    /*repeat routine enough times for results to be representetive of
      probabilities*/
    for (int i = 0; i < 5; i++)
    {
      //initialise array with one car and the rest goats
      Arrays.fill(doors, 'G');
      doors[rand.nextInt(noOfDoors)] = 'C';

    }//for
  }//main
}//class
