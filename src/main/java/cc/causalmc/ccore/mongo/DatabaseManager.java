package cc.causalmc.ccore.mongo;

import cc.causalmc.ccore.CCore;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.sql.*;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class DatabaseManager {


    public static void loadAccount(UUID uuid) {
        if (CCore.getInstance().getMongo().getCollection().find(new BasicDBObject("uuid", uuid.toString())).one() == null){
            try {
                DBObject profile = new BasicDBObject("_id", uuid.toString())
                        .append("uuid", uuid.toString())
                        .append("coins", 0)
                        .append("credits", 0)
                        .append("tag", null);
                CCore.getInstance().getMongo().getCollection().insert(profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static Object getFromCollection(UUID uuid, String string) {
        try {
        DBObject q = new BasicDBObject("_id", uuid.toString());
        DBObject r = CCore.getInstance().getMongo().getCollection().find(q).one();
        /*if(r.get(string) == null){
            return "";
        }*/
        return r.get(string);
    } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
