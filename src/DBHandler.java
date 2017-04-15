import java.util.ArrayList;

/**
 * Created by dannysuarez on 4/14/17.
 */
public class DBHandler {
	
    public static Database connectDatabase() throws Exception
    {
        /* Implements the Database*/
        String url = "jdbc:mysql://localhost:3306/blueping?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "addjteam4";

        Database db = new Database(url, user, password);
        db.connect();

        return db;
    }

    public static TrilaterationTest retrieveData(Database db) throws Exception
    {
        double rssi1, rssi2, rssi3;
        rssi1 = rssi2 = rssi3 = 0;

        String mac = "";

        TrilaterationTest test = new TrilaterationTest();

        ArrayList<Integer> size = db.runIntQuery("SELECT COUNT(*) FROM `beacon1`");

        for (int i = size.get(0) - 10; i <= size.get(0); i++) {

            db.prepareQuery("SELECT MAC FROM beacon1 WHERE id = (?)");
            db.setQueryId(i);
            mac = db.runStringPrepQuery();
            //System.out.println(mac);

            db.prepareQuery("SELECT RSSI FROM beacon1 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 1");
            db.setQueryId(mac);
            rssi1 = db.runIntPrepQuery();
            System.out.println(rssi1);
            rssi1 = RSSItoDistance.calculateDistance(rssi1);
            //System.out.println(rssi1);

            db.prepareQuery("SELECT RSSI FROM beacon2 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 1");
            db.setQueryId(mac);
            rssi2 = db.runIntPrepQuery();
            //System.out.println(rssi2);
            rssi2 = RSSItoDistance.calculateDistance(rssi2);
            //System.out.println(rssi2);

            db.prepareQuery("SELECT RSSI FROM beacon3 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 1");
            db.setQueryId(mac);
            rssi3 = db.runIntPrepQuery();
            //System.out.println(rssi3);
            rssi3 = RSSItoDistance.calculateDistance(rssi3);
            //System.out.println(rssi3);

            test.idToDistances.put(mac, new double[]{rssi1, rssi2, rssi3});
        }

        return test;
    }

}
