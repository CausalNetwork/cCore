package cc.causalmc.ccore.mongo;

import cc.causalmc.ccore.CCore;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerData {

    private UUID uuid;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public void addCoins(int amount){
        CompletableFuture.runAsync(() -> CCore.getInstance().getMongo().getCollection().update(new BasicDBObject("_id", uuid.toString()),
                new BasicDBObject("$inc", new BasicDBObject("coins", amount))));
    }
    public void removeCoins(int amount){
        CompletableFuture.runAsync(() -> CCore.getInstance().getMongo().getCollection().update(new BasicDBObject("_id", uuid.toString()),
                new BasicDBObject("$inc", new BasicDBObject("coins", -amount))));
    }
    public int getCoins(){
        return (int) DatabaseManager.getFromCollection(uuid, "coins");
    }
    public void addCredits(int amount){
        CompletableFuture.runAsync(() -> CCore.getInstance().getMongo().getCollection().update(new BasicDBObject("_id", uuid.toString()),
                new BasicDBObject("$inc", new BasicDBObject("credits", amount))));
    }
    public void removeCredits(float amount){
        CompletableFuture.runAsync(() -> CCore.getInstance().getMongo().getCollection().update(new BasicDBObject("_id", uuid.toString()),
                new BasicDBObject("$inc", new BasicDBObject("credits", -amount))));
    }
    public int getCredits(){
       return (int) DatabaseManager.getFromCollection(uuid, "credits");
    }
    public String getTag(){
        if(DatabaseManager.getFromCollection(uuid, "tag") == null || (String) DatabaseManager.getFromCollection(uuid, "tag") == ""){
            return "";
        }else {
            return (String) DatabaseManager.getFromCollection(uuid, "tag");
        }
    }
    public void setTag(String name) {
        CompletableFuture.runAsync(() -> CCore.getInstance().getMongo().getCollection().update(new BasicDBObject("_id", uuid.toString()),
                new BasicDBObject("$set", new BasicDBObject("tag", name))));
    }
    public void resetTag() {
        CompletableFuture.runAsync(() -> CCore.getInstance().getMongo().getCollection().update(new BasicDBObject("_id", uuid.toString()),
                new BasicDBObject("$set", new BasicDBObject("tag", ""))));
    }
    /*
    public long getParcoursTime(String parcoursID){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT time FROM jump_times WHERE (jump_name, uuid.toString()) = (?, ?)");
            ps.setString(1, parcoursID);
            ps.setString(2, uuid.toString().toString());
            ResultSet rs = ps.executeQuery();

            long time = 0L;

            while (rs.next()){
                time = rs.getInt("time");
            }
            ps.close();
            return time;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }
    public void setParcoursTime(String parcoursID, Long time){
        long t = 0L;
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT time FROM jump_times WHERE (jump_name, uuid.toString()) = (?, ?)");
            ps.setString(1, parcoursID);
            ps.setString(2, uuid.toString().toString());
            ResultSet rs = ps.executeQuery();



            while (rs.next()){
                t = rs.getInt("time");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(t != 0L) {
            try {
                PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE jump_times SET time = ? WHERE (jump_name, uuid.toString()) = (?, ?)");
                ps.setInt(1, time.intValue());
                ps.setString(2, parcoursID);
                ps.setString(3, uuid.toString().toString());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO jump_times (jump_name, uuid.toString(), time) VALUES (?, ?, ?)");
                ps.setString(1, parcoursID);
                ps.setString(2, uuid.toString().toString());
                ps.setInt(3, time.intValue());
                ps.execute();
                ps.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static HashMap<UUID, Long> getParcoursLB(String parcoursID) {
        HashMap<UUID, Long> lb = new HashMap<>();
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM jump_times ORDER BY time DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lb.put(UUID.fromString(rs.getString("uuid")), rs.getLong("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lb;
    } */
}
