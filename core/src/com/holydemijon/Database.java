package com.holydemijon;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    public void addScore(int score) {

        MongoClient mongoClient = MongoClients.create("mongodb+srv://UnknownUser:Demijhon321@cluster0.ubmtxtw.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijhonDB");

        MongoCollection collection = database.getCollection("GameCollection");

        System.out.println("working fine pls :)");

        //Document doc = new Document("score", "123456");
        //collection.insertOne(doc);
    }
}
