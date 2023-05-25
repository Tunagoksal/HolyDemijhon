package com.holydemijon.Database;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    private String name;
    private double score;

    public Database(){}

    public void setName(String name){
        this.name = name;
    }
    public void setScore(double score){
        this.score = score;
    }
    public String getName() {
        return this.name;
    }
    public double getScore(){
        return this.score;
    }

    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:<password>@cluster0.h6zakeh.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection collection = database.getCollection("Scores");

        System.out.println("working fine pls :)");

        Document sampleDoc = new Document("name", "score");
        collection.insertOne(sampleDoc);
    }
}