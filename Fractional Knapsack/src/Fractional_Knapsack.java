import java.util.*;
public class Fractional_Knapsack {
    static class Box {
        int weight;
        int profit;
        double ratio;
        Box(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
            if (weight == 0) {
                this.ratio = 0;
            } else {
                this.ratio = (double) profit / weight;
            }
        }
    }
    public static double knapsackProcess(Box[] items, int cap) {
        double totalProfit = 0.0;
        int remainingCapacity = cap;
        for (Box item : items) {
            if (remainingCapacity == 0) break;
            if (item.weight <= remainingCapacity) {
                totalProfit += item.profit;
                remainingCapacity -= item.weight;
            } else {
                double fraction = (double) remainingCapacity / item.weight;
                totalProfit += item.profit * fraction;
                remainingCapacity = 0;
            }
        }
        return totalProfit;
    }
    public static double frac_Knapsack(Box[] items, int cap) {
        long start = System.nanoTime();
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        double result = knapsackProcess(items, cap);
        long end = System.nanoTime();
        System.out.println("Time (Ratio method): " + (end - start) + " ns");

        return result;
    }
    public static double frac_Knapsack_MinWeight(Box[] items, int cap) {
        long start = System.nanoTime();
        Arrays.sort(items, Comparator.comparingInt(a -> a.weight));
        double result = knapsackProcess(items, cap);
        long end = System.nanoTime();
        System.out.println("Time (MinWeight method): " + (end - start) + "ns");
        return result;
    }
    public static double frac_Knapsack_MaxProfit(Box[] items, int cap) {
        long start = System.nanoTime();
        Arrays.sort(items, (a, b) -> Integer.compare(b.profit, a.profit));
        double result = knapsackProcess(items, cap);
        long end = System.nanoTime();
        System.out.println("Time (MaxProfit method): " + (end - start) + "ns");
        return result;
    }
    public static void main(String[] args) {
        int capacity = 850;
        int[] Weight = {
                7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3, 8,
                15, 42, 9, 0,
                42, 47, 52, 32, 26, 48, 55, 6, 29, 84, 2, 4, 18, 56, 29, 7,
                93, 44, 71, 3,
                86, 66, 31, 65, 0, 79, 20, 65, 52, 13
        };
        int[] Price = {
                360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892, 600,
                38, 48, 147,
                78, 256, 63, 17, 120, 164, 432, 35, 92, 110, 22, 42, 50,
                323, 514, 28, 87,
                73, 78, 15, 26, 78, 210, 36, 85, 189, 274, 43, 33, 10, 19,
                389, 276, 312
        };
        Box[] items = new Box[Weight.length];
        for (int i = 0; i < Weight.length; i++) {
            items[i] = new Box(Weight[i], Price[i]);
        }
        double maxProfitRatio = frac_Knapsack(items.clone(), capacity);
        double maxProfitMinWeight = frac_Knapsack_MinWeight(items.clone(),
                capacity);
        double maxProfitMaxProfit = frac_Knapsack_MaxProfit(items.clone(),
                capacity);
        System.out.println("Maximum profit (Profit/Weight Ratio) = " +

                maxProfitRatio);
        System.out.println("Maximum profit (Minimum Weight) = " +
                maxProfitMinWeight);
        System.out.println("Maximum profit (Maximum Profit) = " +
                maxProfitMaxProfit);
    }
}
