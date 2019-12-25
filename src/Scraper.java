/**
 * Created by Pearse Lehmann on 12/9/2019.
 */
import org.jsoup.*;
import java.util.*;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Scraper {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.draftscout.com/players.php?GenPos=QB&DraftYear=2020").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(doc.title());
        System.out.println(doc.select("table").get(7));
    }

}
