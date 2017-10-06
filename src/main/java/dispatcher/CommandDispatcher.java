package dispatcher;

import Utilities.BotsUtils;
import Utilities.Command;
import javafx.beans.binding.IntegerBinding;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class CommandDispatcher {

    private static String PREFIX = "!";

    private static boolean isPing = false, isPartyEnded = true;

    private static int bounds = -1;
    private static Random random = new Random();

    private static Timer timer = new Timer();

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("role", (event, args) -> {
            BotsUtils.assignRole(event, args != null ? args.get(0) : null);
        });

        commandMap.put("help", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "Liste des commandes :\n \n !role illuminati/templiers" +
                    "/dragon/outsiders : Assigne le membre la faction choisis. Merci d'être raisonnable de toute façon" +
                    " les admins et modérateurs sont au courant de vos changements de faction.\n !help : liste les " +
                    "commandes et leur fonctionalité\n !event : donne la liste des event en cours ou à venir avec leur " +
                    "date et leur horaire.\n !addevent nom jj/mm/aaaa hh:mm : ajoute un event avec le nom, la date et " +
                    "l'heure. Pour retirer un event merci de contacter le " +
                    event.getGuild().getRolesByName("Server Admin").get(0).mention() + " pour  qu'il le fasse.");
        });

        commandMap.put("cafe", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "*Orochi-bot sert un café à " + event.getAuthor().mention()
                    + ".*");
        });

        commandMap.put("roy", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "https://media.giphy.com/media/mh124i42yZWWk/giphy.gif");
        });

        commandMap.put("code", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "Kneel and pray your god !");
        });

        commandMap.put("jevaismecoucher", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "Il est 5h du mat', il serait peut-être temps !");
        });

        commandMap.put("codelyoko", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "https://www.youtube.com/watch?v=1zKk6z-s18E  \n" +
                    "Il existe un monde virtuel et différent\n" +
                    "Ou chaque seconde fait de nous des combattant\n" +
                    "Notre seul espoir est de tout reprogrammer\n" +
                    "On ira on saura sauver notre existence\n" +
                    "Se donner une chance de tout effacer\n" +
                    "On ira on saura sauver notre existence\n" +
                    "Pour refaire un monde sans danger\n" +
                    "Code Lyoko tout reprogrammer\n" +
                    "Code Lyoko un monde sans danger\n" +
                    "Code Lyoko tout reprogrammer\n" +
                    "Code Lyoko un monde sans danger\n" +
                    "Tout est numérique et pixellisé dans ce monde\n" +
                    "Il nous faudra du courage et de l'entre aide\n" +
                    "Mais dites-vous bien que l'on risque notre vie\n" +
                    "On ira on saura sauver notre existence\n" +
                    "Se donner une chance de tout effacer\n" +
                    "On ira on saura sauver notre existence\n" +
                    "Pour refaire un monde sans danger\n" +
                    "On ira on saura sauver notre existence\n" +
                    "Se donner une chance de tout effacer\n" +
                    "On ira on saura sauver notre existence\n" +
                    "Pour refaire un monde sans danger\n" +
                    "Code Lyoko tout reprogrammer\n" +
                    "Code Lyoko un monde sans danger\n" +
                    "Code Lyoko tout reprogrammer\n" +
                    "Code Lyoko un monde sans danger\n" +
                    "On vous promet de donner le maximum\n" +
                    "Contre la menace et de sauver tous les hommes");
        });

        commandMap.put("laszar", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(),
                    event.getGuild().getUsersByName("Korvanyi (Modo Templiers)").get(0).mention()
                            + "> https://giphy.com/gifs/bedroom-eyes-CWMyaXDWaulwY");
        });

        //TODO Fix it
//        commandMap.put("ping", (event, args) -> {
//            //Cancel current timer.
//            timer.purge();
//
//            //If party ended we generate a new nb of bounds.
//            if (isPartyEnded) {
//                bounds = random.nextInt(10);
//                isPartyEnded = false;
//                //If there is no more bound, user win.
//            } else if (bounds == 0) {
//                BotsUtils.sendMessage(event.getChannel(), "Vous avez gagné " + event.getAuthor().mention() + " !");
//                isPartyEnded = true;
//                return;
//            }
//            //Bot send a pong.
//            BotsUtils.sendMessage(event.getChannel(), "pong");
//            bounds--;
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    BotsUtils.sendMessage(event.getChannel(), "Vous avez perdu " + event.getAuthor().mention() + " !");
//                    isPartyEnded = true;
//                }
//            }, 3000);
//        });

        commandMap.put("the", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "Je ne suis pas autorisé à servir les hipsters, veuillez " +
                    "prendre contact avec mon créateur pour plus d'information.\n" +
                    "Cordialement.\n");
        });

        commandMap.put("witch", (event, args) -> {
            IUser usr = null;

            //Use iterator
            String usrName = args.get(0);
            if (args.size() > 1) {
                for (int i = 1; i < args.size(); i++) {
                    usrName += " " + args.get(i);
                }
            }

            if (event.getGuild().getUsersByName(usrName) != null) {
                usr = event.getGuild().getUsersByName(usrName).get(0);
                BotsUtils.sendMessage(event.getChannel(), usr.mention() + " est une sorcière ! Au bûcher ! " +
                event.getGuild().getRolesByName("Templiers").get(0).mention());
            }
        });

        commandMap.put("pyramide", (event, args) -> {
            int i = random.nextInt(5);
            switch (i) {
                case 0:
                    BotsUtils.sendMessage(event.getChannel(), "La pyramide n'est pas votre amie !");
                    break;
                case 1:
                    BotsUtils.sendMessage(event.getChannel(), "La pyramide vous ment !");
                    break;
                case 2:
                    BotsUtils.sendMessage(event.getChannel(), "La pyramide vous drogue !");
                    break;
                case 3:
                    BotsUtils.sendMessage(event.getChannel(), "La pyramide vend votre âme et votre corps au plus offrant !");
                    break;
                case 4:
                    BotsUtils.sendMessage(event.getChannel(), "Ne faites pas confiance à la pyramide !");
                    break;
            }
        });

        commandMap.put("hans", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(),
                    "http://i1.kym-cdn.com/photos/images/newsfeed/001/229/782/fb4.jpg");
        });

        commandMap.put("lorelei", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "La pyramide est votre amie !");
        });

        commandMap.put("templiers", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "https://giphy.com/gifs/door-red-panda-locked-out-zLydqDQu8fDLW");
        });

        commandMap.put("gaia", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "https://giphy.com/gifs/grooving-12uhzw7y9aB8v6");
        });

        commandMap.put("addevent", (event, args) -> {
            try {

                System.out.println("addEvent");
                String name = "", date = "", hour = "", buffer;
                Iterator<String> it = args.iterator();

                while (it.hasNext()) {
                    buffer = it.next();
                    if (buffer.contains("/"))
                        date = buffer;
                    else if (buffer.contains(":"))
                        hour  = buffer;
                    else
                        name += buffer + " ";
                }
                name = name.trim();

                FileWriter fw = new FileWriter(NormalDispatcher.eventFile, true);
                fw.append( name + "$" + date + "$" + hour + "\n");
                NormalDispatcher.currentEvent += name + " le " + date + " à " + hour + "\n";

                BotsUtils.sendMessage(event.getGuild().getChannelsByName("event").get(0), name + " le " +
                        date + " à " + hour);

                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        //TODO Fix it
//        commandMap.put("removeevent", (event, args) -> {
//
//
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(NormalDispatcher.eventFile));
//
//                //Get all the lines
//                List<String> lines = new ArrayList<>();
//                String buffer = br.readLine();
//                while (buffer != null) {
//                    lines.add(buffer);
//                }
//
//                //Remove the selected line
//                if (lines.size() != 0) {
//                    lines.remove(Integer.parseInt(args.get(0)));
//                }
//                br.close();
//
//                //Rewrite the file
//                FileWriter fw = new FileWriter(NormalDispatcher.eventFile);
//
//                Iterator<String> it = lines.iterator();
//
//                while(it.hasNext()) {
//                    fw.append(it.next() + "\n");
//                }
//
//                fw.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        });

        commandMap.put("event", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), NormalDispatcher.currentEvent);
        });

        commandMap.put("dragon", (event, args) -> {
            BotsUtils.sendMessage(event.getChannel(), "https://giphy.com/gifs/O38dU2kkQ9sWc");
        });
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        //Split command and arguments.
        String[] arrays = event.getMessage().getContent().split(" ");

        //Check string has been splited
        if (arrays.length == 0)
            return;

        //Check if it's a command.
        if (!arrays[0].startsWith(PREFIX))
            return;

        //Extract the command
        String command = arrays[0].substring(1).toLowerCase();

        List<String> argsList = new ArrayList<>(Arrays.asList(arrays));
        argsList.remove(0);

        if (commandMap.containsKey(command))
            commandMap.get(command).runCommand(event, argsList);
    }

}
