package geeks_for_geeks.algo.divide_and_conquer;

import geeks_for_geeks.util.Util;
import javafx.util.Pair;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-12-2018 08:40
 * Purpose: TODO:
 **/
public class ClosestPairOfPoints {
    /**
     * T=O(1)
     *
     * @param points
     * @param l
     * @param r
     * @return
     */
    private static double closestPairOfPointsBruteForce(Pair<Double, Double>[] points, int l, int r) {
        Double min = Double.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                min = Math.min(min, Util.eucledianDistance(points[i], points[j]));
            }
        }
        return min;
    }

    /**
     * T=O(1)
     * Their would be constant no of points in the strip
     *
     * @param pointsInStrip
     * @param stripLength
     * @return
     */
    private static double getMinInStrip(List<Pair<Double, Double>> pointsInStrip, double stripLength) {
        pointsInStrip.sort((o1, o2) -> {
                    return (int) (o1.getValue() - o2.getValue());
                }
        );
        double min = stripLength;
        for (int i = 0; i < pointsInStrip.size(); i++) {
            for (int j = i + 1; j < pointsInStrip.size() && Math.abs(pointsInStrip.get(j).getValue() -
                    pointsInStrip.get(i).getValue()) < stripLength; j++) {
                min = Math.min(min, Util.eucledianDistance(pointsInStrip.get(i), pointsInStrip.get(j)));
            }
        }
        return min;
    }

    /**
     * T=O(NlogN)
     *
     * @param points
     * @param l
     * @param r
     * @return
     */
    private static double closestPairOfPointsUtil(Pair<Double, Double>[] points, int l, int r) {
        if (r - l <= 3) {
            return closestPairOfPointsBruteForce(points, l, r);
        }
        int mid = (l + r) >> 1;
        Double min = Double.MAX_VALUE;
        min = Math.min(closestPairOfPointsUtil(points, l, mid), min);
        min = Math.min(closestPairOfPointsUtil(points, mid + 1, r), min);
        List<Pair<Double, Double>> strip = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (Math.abs(points[i].getKey() - points[mid].getKey()) < min) {
                strip.add(points[i]);
            }
        }
        return getMinInStrip(strip, min);
    }

    public static double closestPairOfPoints(Pair<Double, Double>[] points) {
        assert points.length >= 2;
        Arrays.sort(points, (o1, o2) -> {
                    int ans = (int) (o1.getKey() - o2.getKey());
                    return ans;
                }
        );
        return closestPairOfPointsUtil(points, 0, points.length - 1);
    }

    public static void main(String[] args) {
        Pair<Double, Double>[] points = new Pair[]{
                new Pair(2.0, 3.0), new Pair(12.0, 30.0), new Pair(40.0, 50.0), new Pair(5.0, 1.0)
                , new Pair(12.0, 10.0), new Pair(3.0, 4.0)
        };
        System.out.println(closestPairOfPoints(points));
    }
}