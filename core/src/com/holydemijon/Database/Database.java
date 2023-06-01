package com.holydemijon.Database;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

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
        Database db = new Database();
        db.setName("Bora");
        db.setScore(3000);

        MongoClient mongoClient = MongoClients.create("mongodb+srv://borayetkin:Bo1234ra@cluster0.p8gn1ac.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection collection = database.getCollection("gameCollection");

        System.out.println("working fine pls :)");

        Document sampleDoc = new Document(db.getName(), db.getScore());
        collection.insertOne(sampleDoc);
    }

    public static void getTopScores(){



        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection<Document> collection = database.getCollection("ScoreCollection");

        Bson sort = Sorts.ascending("score");

        FindIterable<Document> iterDoc = collection.find().sort(sort).limit(5);

        MongoCursor<Document> it = iterDoc.iterator();

        while(it.hasNext()){
            Document doc = it.next();
            System.out.println("Score: " + doc.getInteger("score"));
        }

        System.out.println("getTopScores working fine pls :)");
    }

    public static void getTopNames(){


        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection<Document> collection = database.getCollection("ScoreCollection");

        Bson sort = Sorts.ascending("score");

        FindIterable<Document> iterDoc = collection.find().sort(sort).limit(5);

        MongoCursor<Document> it = iterDoc.iterator();

        while(it.hasNext()){
            Document doc = it.next();
            System.out.println("Name: " + doc.getString("name"));
        }

        System.out.println("getTopScores working fine pls :)");
    }
}