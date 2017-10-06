package twitter;

import Utilities.BotsUtils;
import dispatcher.NormalDispatcher;
import twitter4j.*;

public class SWLStreamListener implements UserStreamListener {
    @Override
    public void onStatus(Status status) {
        if (!status.isRetweet() && status.getInReplyToScreenName() == null && status.getUser().getName().equals("Secret World Legends")) {
            System.out.println(status.getText() + " from " + status.getUser().getName());
            System.out.println("isRetweet : " + status.isRetweet());
            System.out.println("isRetweeted : " + status.isRetweeted());
            System.out.println("in_reply  : " + status.getInReplyToScreenName());
            BotsUtils.sendMessage(NormalDispatcher.renaissance.getChannelsByName("annonce").get(0),
                    "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId());
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {

    }

    @Override
    public void onDeletionNotice(long l, long l1) {

    }

    @Override
    public void onFriendList(long[] longs) {

    }

    @Override
    public void onFavorite(User user, User user1, Status status) {

    }

    @Override
    public void onUnfavorite(User user, User user1, Status status) {

    }

    @Override
    public void onFollow(User user, User user1) {

    }

    @Override
    public void onUnfollow(User user, User user1) {

    }

    @Override
    public void onDirectMessage(DirectMessage directMessage) {

    }

    @Override
    public void onUserListMemberAddition(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListMemberDeletion(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListSubscription(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListUnsubscription(User user, User user1, UserList userList) {

    }

    @Override
    public void onUserListCreation(User user, UserList userList) {

    }

    @Override
    public void onUserListUpdate(User user, UserList userList) {

    }

    @Override
    public void onUserListDeletion(User user, UserList userList) {

    }

    @Override
    public void onUserProfileUpdate(User user) {

    }

    @Override
    public void onUserSuspension(long l) {

    }

    @Override
    public void onUserDeletion(long l) {

    }

    @Override
    public void onBlock(User user, User user1) {

    }

    @Override
    public void onUnblock(User user, User user1) {

    }

    @Override
    public void onRetweetedRetweet(User user, User user1, Status status) {

    }

    @Override
    public void onFavoritedRetweet(User user, User user1, Status status) {

    }

    @Override
    public void onQuotedTweet(User user, User user1, Status status) {

    }

    @Override
    public void onException(Exception e) {

    }
}
