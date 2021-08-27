package cc.causalmc.ccore.mongo;

import cc.causalmc.ccore.CCore;
import com.mongodb.*;
import lombok.Getter;
import org.bukkit.entity.Player;

public class MongoUtils {

    @Getter public final MongoClient mongoClient;
    @Getter private final DB database;
    @Getter private final DBCollection collection;




    public MongoUtils() throws Exception {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("Causal");
        collection = database.getCollection("CausalPlayer");
    }

    public boolean collectionExists(final String collectionName) {
        return database.collectionExists(collectionName);
    }

    public static Object getFromCollection(Player player, String string) {
        DBObject query = new BasicDBObject("name", player.getName());
        DBObject cplayer = CCore.getInstance().getMongo().getCollection().find(query).one();
        return cplayer.get(string);
    }

}
