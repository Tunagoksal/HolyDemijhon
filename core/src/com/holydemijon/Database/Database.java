package com.holydemijon.Database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://borayetkin:Bo1234ra@cluster0.p8gn1ac.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");

        MongoCollection collection = database.getCollection("gameCollection");

        System.out.println("working fine pls :)");

        Document sampleDoc = new Document("score", "123456");
        collection.insertOne(sampleDoc);

    }
}
