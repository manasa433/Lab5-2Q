2Q.WaterConservationSystem Problem

// Interface Definition
interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

// Abstract Class
abstract class RainySeasonConservation implements WaterConservationSystem {}

// Concrete Class
class CityBlockConservation extends RainySeasonConservation {
    public int calculateTrappedWater(int[] blockHeights) {
        int n = blockHeights.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int trappedWater = 0;

        leftMax[0] = blockHeights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], blockHeights[i]);
        }

        rightMax[n - 1] = blockHeights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], blockHeights[i]);
        }

        for (int i = 0; i < n; i++) {
            trappedWater += Math.min(leftMax[i], rightMax[i]) - blockHeights[i];
        }

        return trappedWater;
    }
}

// Main Method
public class Main {
    public static void main(String[] args) {
        int[] blockHeights = {3, 0, 2, 0, 4};
        CityBlockConservation conservation = new CityBlockConservation();
        int trappedWater = conservation.calculateTrappedWater(blockHeights);

        System.out.println("Total trapped water: " + trappedWater + " units");
    }
}
