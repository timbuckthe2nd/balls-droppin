import java.util.*;

public class ballsDroppin 
{
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Enter value for seed: ");
        int seed = keyboard.nextInt();
        
        System.out.print("How many balls will be dropped? ");
        int numberOfBalls = keyboard.nextInt();
        
        System.out.print("How many pegs are on the board? ");
        int numberOfPegs = keyboard.nextInt();
        
        System.out.println();

        int[] slots = initializeSlots(numberOfPegs);
        
        System.out.println("Here are the paths for the first five balls:");
        dropBallsIntoSlots(slots, seed, numberOfBalls);
        
        System.out.println("\nHere are the counts for balls in each slot (slot 0 is the leftmost):\n");
        showSlotlength(slots);
        
        System.out.println("\nHere is a graphic picture of the distribution:");
        showDistribution(slots);
    }

    // sets quantity of slots and their default value equal to zero
    public static int[] initializeSlots(int numberOfPegs)
    {
        int[] result = new int[numberOfPegs + 1];
        
        for (int i = 0; i < result.length; i++)
            result[i] = 0;
        
        return result;
    }

    // drops balls into the bean machine
    public static void dropBallsIntoSlots(int[] slots, int seed, int numberOfBalls)
    {
        System.out.println();
        int numberOfPegs = slots.length - 1;
        Random pegDecider = new Random(seed);
        int ballCounter = 0;
        
        for (ballCounter = 0; ballCounter < 5; ballCounter++)
        {
            int rightCounter = 0;
            
            for (int pegCounter = 0; pegCounter < numberOfPegs; pegCounter++)
            {
                if (pegDecider.nextInt(2) == 1)
                {
                    rightCounter++;
                    System.out.print("R");
                } 
                else
                    System.out.print("L");
            }
            
            slots[rightCounter]++;
            System.out.println();
        }

        for ( ; ballCounter < numberOfBalls; ballCounter++)
        {
            int rightCounter = 0;
            
            for (int pegCounter = 0; pegCounter < numberOfPegs; pegCounter++)
            {
                if (pegDecider.nextInt(2) == 1)
                    rightCounter++;
            }
            slots[rightCounter]++;
        }
    }

    // displays the amount of balls in the first five slots
    public static void showSlotlength(int[] slots)
    {
        for (int i = 0; i < slots.length; i++)
            System.out.printf("Slot#%d -- %2d\n", i, slots[i]);
    }

    // prints histogram
    public static void showDistribution(int[] slots)
    {
        int maximumCount = getMaximum(slots);
        
        System.out.println();
        for (int displayCounter = maximumCount; displayCounter > 0; displayCounter--)
        {
            for (int slot : slots)
            {
                if (slot >= displayCounter) 
                		System.out.printf("%5s", "XXX");
                else 
                		System.out.printf("%5s", "");
            }
            System.out.println();
        }
        
        //display each slot
        for (int slotNo = 0; slotNo < slots.length; slotNo++)
            System.out.printf("%5s", " ---");
    }

	// returns the largest integer in a list
    public static int getMaximum(int[] list)
    {
    		int max = list[0];

		for (int i = 1; i < list.length; i++) 
		{
			if (list[i] > max)
				max = list[i];
		}
		return max;
    }
}