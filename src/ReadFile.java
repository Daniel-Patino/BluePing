import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {

    public static void main(String[] args) {

        ArrayList<String> fileInput = new ArrayList<String>();
        ArrayList<String> macAddresses = new ArrayList<String>();

        try {
            FileReader fr = new FileReader("./BluepingExampleOutput.txt");
            BufferedReader reader = new BufferedReader(fr);
            String s;
            
            while ((s = reader.readLine()) != null) {
                fileInput.add(s);
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Pattern macPattern = Pattern.compile("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
        Matcher m;

        for (String str : fileInput) {
            StringTokenizer tok = new StringTokenizer(str);
            while (tok.hasMoreTokens()) {
                String s = tok.nextToken();
                System.out.println(s);
                m = macPattern.matcher(s);
                if (m.matches()) {
                    macAddresses.add(s);
                }
            }
        }

        for (String str : macAddresses) {
            System.out.println(str);
        }
    }
}
