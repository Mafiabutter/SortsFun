import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
public class bogosort
{
  public static void main (String args[])
  {
    Random r = new Random ();
    Scanner asking = new Scanner (System.in);
    int cardinalMax = 0;
    int range = 0;
    int numLoops = 0;
    System.out.print("Please enter the maximum cardinal of numbers you'd like to sort:  ");
    try
    {
      cardinalMax = asking.nextInt();
      System.out.print("Now, please enter the range for the numbers you'd like to sort:  ");
      range = asking.nextInt();
      System.out.print("Finally, please enter the number of times you want the cardinal-range to be repeated:  ");
      numLoops = asking.nextInt();
    }
    catch (Exception e)
    {
      System.out.println("Error -- Something entered was not an integer");
    }
    double result = 0;
    for (int y = 0; y < numLoops; y++)
    {
      double[] iterNums = new double[cardinalMax + 1];
      for (int cardinal = 0; cardinal <= cardinalMax; cardinal++)
      {
        System.out.println("Going to sort a set of " + cardinal + " numbers");
        double[] nums = new double[cardinal];
        for (int b = 0; b < nums.length; b++)
        {
          nums[b] = r.nextInt(range + 1);
        }
        System.out.println("Unsorted numbers generated:  " + Arrays.toString(nums));
        double iterSum = 0;
        for (int z = 0; z < numLoops; z++)
        {
          long iter = 0;
          while (!isSorted(nums))
          {
            iter++;
            nums = shuffle(nums);
          }
          System.out.println("Sorted after " + iter + " tries");
          iterSum += iter;
          nums = shuffle(nums);
        }
        iterSum /= 10.0;
        iterNums[cardinal] = iterSum;
        System.out.println("Average number of iterations over " + numLoops + " runs for this cardinal:  " + iterSum);
      }
      System.out.println("Average number of iterations for each cardinal:  " + Arrays.toString(iterNums));
      double[] iterationDifferentials = new double[iterNums.length - 1];
      for (int d = 0; d < iterationDifferentials.length; d++)
      {
        if (iterNums[d] == 0)
        {
          iterationDifferentials[d] = 0;
        }
        else
        {
          iterationDifferentials[d] = (double) iterNums[d + 1] / iterNums[d];
        }
      }
      System.out.println("Differentials for each iteration-change:  " + Arrays.toString(iterationDifferentials));
      double diffSum = 0;
      for (double e : iterationDifferentials)
      {
        diffSum += e;
      }
      diffSum /= iterationDifferentials.length;
      System.out.println("Average iteration differential:  " + diffSum);
      result += diffSum;
    }
    result /= (double) numLoops;
    System.out.println("Cardinal iteration differential superaverage over " + numLoops + " loops:  " + result);
  }
  public static boolean isSorted (double[] nums)
  {
    for (int a = 0; a < (nums.length - 1); a++)
    {
      if (nums[a] > nums[a + 1])
      {
        return false;
      }
    }
    return true;
  }
  public static double[] shuffle (double[] nums)
  {
    Random r = new Random ();
    ArrayList<Integer> indicies = new ArrayList<Integer> ();
    double[] shaker = new double[nums.length];
    for (int a = 0; a < nums.length; a++)
    {
      int index = r.nextInt(nums.length);
      while (indicies.contains(index))
      {
        index = r.nextInt(nums.length);
      }
      indicies.add(index);
      shaker[index] = nums[a];
    }
    return shaker;
  }
}
