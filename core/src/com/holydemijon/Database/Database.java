package com.holydemijon.Database;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
        Document sampleDoc = new Document(db.getName(), db.getScore());
        collection.insertOne(sampleDoc);
    }

    /* public static void getTopScores(){



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


            labelName1 = new Label(""+names.get(0), style);
            labelScore1 = new Label(""+scores.get(0), style);
            labelName2 = new Label(""+names.get(1), style);
            labelScore2 = new Label(""+scores.get(1), style);
            labelName3 = new Label(""+names.get(2), style);
            labelScore3 = new Label(""+scores.get(2), style);
            labelName4 = new Label(""+names.get(3), style);
            labelScore4 = new Label(""+scores.get(3), style);
            labelName5 = new Label(names.get(4), style);
            labelScore5 = new Label(""+scores.get(4), style);
            labelName6 = new Label(names.get(5), style);
            labelScore6 = new Label(""+scores.get(5), style);


        }

        /*System.out.println("getTopScores working fine pls :)");
    }
    public static void addToDatabase(String name, int time){

        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection collection = database.getCollection("ScoreCollection");

        Document doc = new Document("name", name);

        doc.append("score", time);
        collection.insertOne(doc);


        System.out.println("addToDatabase working fine pls :)");

    }*/

    /*public static void getTopScores(){

        MongoClient mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection<Document> collection = database.getCollection("ScoreCollection");


        Bson sort = Sorts.ascending("score");


        FindIterable<Document> iterDoc = collection.find().sort(sort).limit(6);
        MongoCursor<Document> it = iterDoc.iterator();


        while(it.hasNext()){
            Document doc = it.next();
            names.add(doc.getString("name"));
            scores.add(doc.getInteger("score"));

        }

        public static void getTopScores() {
    MongoClient mongoClient = null;
    try {
        mongoClient = MongoClients.create("mongodb+srv://boraytkn:1234mdb@cluster0.ris0uvf.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("HolyDemijohnDB");
        MongoCollection<Document> collection = database.getCollection("ScoreCollection");

        Bson sort = Sorts.ascending("score");
        Bson projection = Projections.include("name", "score");

        FindIterable<Document> iterDoc = collection.find().projection(projection).sort(sort).limit(6);

        MongoCursor<Document> it = iterDoc.iterator();

        while (it.hasNext()) {
            Document doc = it.next();
            names.add(doc.getString("name"));
            scores.add(doc.getInteger("score"));
        }
        System.out.println("getTopScores test");
    } finally {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}


    }*/
}