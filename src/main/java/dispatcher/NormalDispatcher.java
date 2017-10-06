package dispatcher;

import Utilities.BotsUtils;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Renaud on 28/06/2017.
 */
public class NormalDispatcher {

    public static IGuild renaissance;
    public static List<IUser> admins;
    public static List<IUser> moderators;
    public static String currentEvent = "";
    public static File eventFile = new File("C:\\Users\\Renaud\\IdeaProjects\\discordbot\\eventList.txt");

    private List<IPrivateChannel> pmAdmins = new ArrayList<>();
    private List<IPrivateChannel> pmModerators = new ArrayList<>();

    //Initialization of the bot.
    @EventSubscriber
    public void OnConnection(ReadyEvent event) {
        List<IGuild> guilds = event.getClient().getGuilds();
        int index = -1;

        for (IGuild guild : guilds) {
            if (guild.getName().contains("Renaissance"))
                renaissance = guild;
        }

        admins = renaissance.getUsersByRole(renaissance.getRolesByName("Server Admin").get(0));
        moderators = renaissance.getUsersByRole(renaissance.getRolesByName("Server Moderator").get(0));

        admins.removeIf(iUser -> iUser.isBot());

        for (IUser user: admins) {
                pmAdmins.add(user.getOrCreatePMChannel());
        }

        for (IUser user  : moderators) {
            pmModerators.add(user.getOrCreatePMChannel());
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(eventFile));
            String buffer = "";

            while (buffer != null) {
                buffer = br.readLine();

                if(buffer != null) {
                    System.out.println(buffer);
                    String[] args = buffer.split("\\$");
                    currentEvent += args[0] + " le " + args[1] + " à " + args[2] + "\n";
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        BotsUtils.sendMessage(renaissance.getChannelsByName("annonce").get(0), "Bot awaken !");
    }

    @EventSubscriber
    public void onUserJoinEvent(UserJoinEvent event) {
        BotsUtils.sendMessage(renaissance.getChannelsByName("secretworldlegends").get(0), "Bienvenue " + event.getUser().mention() + " !");
        IPrivateChannel pmchan = event.getUser().getOrCreatePMChannel();
        BotsUtils.sendMessage(pmchan, "Bonjour et bienvenue sur le Discord de la communauté RP Renaissance de" +
                " Secret World Legends !\n" +
                "Tout d'abord je te conseille d'aller lire le réglement du Discord dans le channel #reglement_et_infos.\n" +
                "D'autre part veille à avoir les mêmes pseudonymes entre le forum, le discord et le jeu afin que tout le " +
                "monde puisse facilement te reconnaître. Pour rappel, si tu modifies ton pseudonyme sur un serveur il ne " +
                "modifie pas pour tes autres serveurs discord ou tes amis. Enfin, s'il est nécessaire de modifier ton " +
                "pseudonyme sur le forum, contacte un des Server Admin ou Server Moderator pour qu'il le fasse.\n" +
                "Pour t'attribuer le rôle d'illuminati, templiers ou dragon utilise la commande !role nom qui te donnera" +
                "directement accès à ta faction.\n" +
                "En me répondant gentillement tu auras accès au reste du discord !\n");
    }

    @EventSubscriber
    public void onPrivateMessage(MessageReceivedEvent event) {
        IUser author = event.getAuthor();

        if(event.getChannel().isPrivate()) {
            for (IUser admin: NormalDispatcher.admins ) {
                admin.getOrCreatePMChannel().sendMessage(author.getName() + " a été ajouté au groupe Member après " +
                        "avoir lu le message de bienvenue.\n Il a répondu au bot : " + event.getMessage());
            }

            for (IUser moderator: NormalDispatcher.moderators ) {
                moderator.getOrCreatePMChannel().sendMessage(author.getName() + " a été ajouté au groupe Member " +
                        "après avoir lu le message de bienvenue.\n Il a répondu au bot : " + event.getMessage());
            }

            author.addRole(renaissance.getRolesByName("Member").get(0));
        }
    }
}
