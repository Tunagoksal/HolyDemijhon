package com.holydemijon.Database;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    private String name;
    private static int score;

    public void setName(String name){
        this.name = name;
    }
    public void setScore(int score){
        this.score = score;
    }
    public String getName() {
        return this.name;
    }
    public static int getScore(){
        return score;
    }

    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection collection = database.getCollection("ScoreCollection");

        Document doc = new Document("name", "joohn");
        doc.append("score", getScore());
        collection.insertOne(doc);

        System.out.println("working fine pls :)");
    }

}