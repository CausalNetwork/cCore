package cc.causalmc.ccore.mongo;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.utils.CC;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DatabaseManager {

    @SuppressWarnings("unused")
    public static void loadAccount(UUID uuid) {
        CompletableFuture.runAsync(() -> {
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
        });
    }
    /*public static CompletableFuture<Object> getFromCollection(UUID uuid, String string){
         return CompletableFuture.supplyAsync(() -> {
             try {
                 DBObject q = new BasicDBObject("_id", uuid.toString());
                 DBObject r = CCore.getInstance().getMongo().getCollection().find(q).one();

                 return r.get(string);
             } catch (Exception e) {
                 e.printStackTrace();
                 return null;
             }
         });
    }*/


    public static Object getFromCollection(UUID uuid, String string) {
        try {
            DBObject q = new BasicDBObject("_id", uuid.toString());
            DBObject r = CCore.getInstance().getMongo().getCollection().find(q).one();
            return r.get(string);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
