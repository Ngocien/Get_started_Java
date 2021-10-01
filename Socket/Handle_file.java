package Socket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Handle_file {
    public static List<Client> read_csv() {
        String csvFile = "src/information/data.csv";
        List<Client> student = new ArrayList<>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
                tempArr = line.split(",");
                Client t = Client.createClient(tempArr);
                student.add(t);
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return student;
    }

    public static void write_file(List<Client> student) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/information/data.csv");
        for (Client i : student) {
                writer.println( i.getName().toString() + "," + i.getPass().toString());
        }
        writer.close();
    }

}

