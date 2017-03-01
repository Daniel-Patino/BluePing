/**
 * Created by dannysuarez on 2/27/17.
 */

public class RSSItoDistance {
    protected static double calculateDistance(double rssi) {
        int txPower = -59;

        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }

        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio,10);
        }
        else {
            double distance =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;
            return distance;
        }
    }
}
