import java.util.ArrayList;

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
        ArrayList<Integer> rssi1List, rssi2List, rssi3List;
        rssi1List = rssi2List = rssi3List = new ArrayList<>();

        double rssi1, rssi2, rssi3;
        rssi1 = rssi2 = rssi3 = 0;

        double radialDistance = 6.7056;
        
        String mac = "";

        TrilaterationTest test = new TrilaterationTest();

        ArrayList<Integer> size = db.runIntQuery("SELECT COUNT(*) FROM `beacon1`");

        //double testrssi = RSSItoDistance.radialScale(radialDistance) * (RSSItoDistance.calculateDistance(-71.30612245));

        //test.idToDistances.put(mac, new double[]{testrssi, testrssi, testrssi});

        for (int i = size.get(0) - 10; i <= size.get(0); i++) {

            db.prepareQuery("SELECT MAC FROM beacon1 WHERE id = (?)");
            db.setQueryId(i);
            mac = db.runStringPrepQuery();
            //System.out.println(mac);

            db.prepareQuery("SELECT RSSI FROM beacon1 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 5");
            db.setQueryId(mac);
            rssi1List = db.runIntPrepQuery();
            //System.out.println(rssi1);
            rssi1 = RSSItoDistance.radialScale(radialDistance) * RSSItoDistance.calculateDistance(RSSItoDistance.calculateAverage(rssi1List));
            //System.out.println(rssi1List);
            System.out.print("Beacon 1: " + RSSItoDistance.calculateAverage(rssi1List) + " ");
            System.out.print(RSSItoDistance.calculateDistance(RSSItoDistance.calculateAverage(rssi1List)) + " ");
            System.out.println(rssi1);

            db.prepareQuery("SELECT RSSI FROM beacon2 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 5");
            db.setQueryId(mac);
            rssi2List = db.runIntPrepQuery();
            //System.out.println(rssi2);
            rssi2 = RSSItoDistance.radialScale(radialDistance) * RSSItoDistance.calculateDistance(RSSItoDistance.calculateAverage(rssi2List));
            System.out.print("Beacon 2: " + RSSItoDistance.calculateAverage(rssi2List) + " ");
            System.out.print(RSSItoDistance.calculateDistance(RSSItoDistance.calculateAverage(rssi2List)) + " ");
            System.out.println(rssi2);

            db.prepareQuery("SELECT RSSI FROM beacon3 WHERE MAC = (?) ORDER BY `TIME` DESC LIMIT 5");
            db.setQueryId(mac);
            rssi3List = db.runIntPrepQuery();
            //System.out.println(rssi3);
            rssi3 = RSSItoDistance.radialScale(radialDistance) * (RSSItoDistance.calculateDistance(RSSItoDistance.calculateAverage(rssi3List)));
            System.out.print("Beacon 3: " + RSSItoDistance.calculateAverage(rssi3List) + " ");
            System.out.print(RSSItoDistance.calculateDistance(RSSItoDistance.calculateAverage(rssi3List)) + " ");
            System.out.println(rssi3);

            System.out.println(" ");

            test.idToDistances.put(mac, new double[]{rssi1, rssi2, rssi3});
        }

        return test;
    }

}
