package thirdParty.mongo;

public class DiscordToMongo {
    private WorkingWithMongo mongo = new WorkingWithMongo();
    public String addLink(String link) {
        String action;
        action = mongo.addLink(link);
        return action;
    }
    public String showLink() {
        String action;
        action = mongo.showLink();
        return action;
    }
    public String deleteLink(String link) {
        String action;
        action = mongo.deleteLink(link);
        return action;

    }
}
