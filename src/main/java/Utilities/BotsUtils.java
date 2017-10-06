package Utilities;

import dispatcher.NormalDispatcher;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class BotsUtils {

    public static IDiscordClient getBuiltDiscordClient(String token) {
        return new ClientBuilder().withToken(token).build();
    }

    public static void sendMessage(IChannel channel, String message) {
        if (!message.isEmpty()) {
            RequestBuffer.request(() -> {
                try {
                    channel.sendMessage(message);
                } catch (DiscordException e) {
                    System.err.println("Message could not be sent with error: ");
                    e.printStackTrace();
                }
            });
        }
    }

    public static void assignRole(MessageReceivedEvent event, String role) {

        if(role == null)
            return;

        IUser author = event.getMessage().getAuthor();
        IRole dragon  =  null, templar = null, illuminati = null, outsiders = null;
        for (IRole iRole : event.getGuild().getRoles()) {
            switch (iRole.getName()) {
                case "Dragon" :
                    dragon = iRole;
                    break;
                case "Illuminati" :
                    illuminati = iRole;
                    break;
                case "Templiers" :
                    templar = iRole;
                    break;
                case "Outsiders" :
                    outsiders = iRole;
                        break;
            }
        }

        switch (role.toLowerCase()) {
            case "dragon" :
                if (author.getRolesForGuild(NormalDispatcher.renaissance).contains(dragon))
                    return;
                author.addRole(dragon);
                break;
            case "illuminati"  :
                if (author.getRolesForGuild(NormalDispatcher.renaissance).contains(illuminati))
                    return;
                author.addRole(illuminati);
                break;
            case "templiers" :
                if (author.getRolesForGuild(NormalDispatcher.renaissance).contains(templar))
                    return;
                author.addRole(templar);
                break;
            case "outsiders" :
                if (author.getRolesForGuild(NormalDispatcher.renaissance).contains(outsiders))
                    return;
                author.addRole(outsiders);
                break;
        }

        for (IUser admin: NormalDispatcher.admins ) {
            admin.getOrCreatePMChannel().sendMessage(author.getName() + " a été ajouté au groupe " + role);
        }

        for (IUser moderator: NormalDispatcher.moderators ) {
            moderator.getOrCreatePMChannel().sendMessage(author.getName() + " a été ajouté au groupe " + role);
        }
    }
}
