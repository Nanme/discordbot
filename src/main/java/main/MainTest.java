package main;

import Utilities.BotsUtils;
import dispatcher.CommandDispatcher;
import dispatcher.NormalDispatcher;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import twitter.SWLStreamListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.io.*;

/**
 * Created by Renaud on 28/06/2017.
 */
public class MainTest {


    public static String currentEvent = "";
    public static File eventFile = new File("eventList.txt");


    public static void main(String[] args) {

        //Discord
        IDiscordClient client = BotsUtils.getBuiltDiscordClient("MjY5NDI4NzMwNjg4OTYyNTY0.DDVQCQ.DmBzpY6cIYfZvzD8rBn-pWRZlqw");
        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(new NormalDispatcher());
        dispatcher.registerListener(new CommandDispatcher());
        client.login();

        //Twitter
        TwitterStream twitter = new TwitterStreamFactory().getInstance();
        twitter.addListener(new SWLStreamListener());

        FilterQuery query = new FilterQuery();
        query.follow(72571040);
        twitter.filter(query);

//        try {
//            BufferedReader br = new BufferedReader(new FileReader(eventFile));
//            String buffer;
//
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//
////            do {
////                buffer = br.readLine();
////                String[] args1 = buffer.split("\\$");
////                currentEvent += args1[0] + " le " + args1[1] + " à " + args1[2] + "\n";
////            } while (buffer != null);
//
//            br.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
