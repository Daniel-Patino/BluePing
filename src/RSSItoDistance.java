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

        return Math.pow(10.0, (-47.45745746 - rssi) / (10.0 * 2.015694976));
    }

    protected static double calculateEnvConst(double rssi, double distance)
    {
        return Math.abs((rssi + 47.45745746) / (10 * Math.log10(distance)));
    }
}
