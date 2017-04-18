import java.util.ArrayList;

public class RSSItoDistance
{
    protected static double calculateDistance(double rssi)
    {
        /*
        * RSSI = TxPower - 10 * n * lg(d)
        * n = 2 (in free space)
        *
        * n = (RSSI - TxPower) / (10 * lg(d))
        *
        * d = 10 ^ ((TxPower - RSSI) / (10 * n))
        */

        return Math.pow(10.0, (-41.94871795 - rssi) / (10.0 * 1.75));
    }

    protected static double calculateEnvConst(double rssi, double distance)
    {
        return Math.abs((rssi + 47.45745746) / (10 * Math.log10(distance)));
    }
    
    protected static double radialScale(double radialDistance)
    {
    	// Dimensions of CoordinateScene is 700 pixels by 700 pixels
    	double dimension = 700;
    	return  dimension / radialDistance;
    }

    protected static double calculateAverage(ArrayList<Integer> marks) {
        double avg = 0;
        double size = marks.size();
        if(!marks.isEmpty()) {
            for (Integer mark : marks) {
                avg += (double) mark;
            }
            return avg / size;
        }
        return avg;
    }
}
