package thirdParty.mongo;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import java.net.UnknownHostException;
import java.util.Properties;

public class WorkingWithMongo {
    ////discord:discord123

    // Создаем подключение
    private MongoClient mongoClient = MongoClients.create("mongodb://discord:discord123@ds137605.mlab.com:37605/discord_mamasha_bot");
    // Выбираем БД для дальнейшей работы
    private MongoDatabase database = mongoClient.getDatabase("discord_mamasha_bot");

    public void test(){
        //Выбираем коллекцию
        MongoCollection<Document> collection = database.getCollection("test");
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);
    }
}

//examples
//Document doc = new Document("name", "MongoDB")
//        .append("type", "database")
//        .append("count", 1)
//        .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//        .append("info", new Document("x", 203).append("y", 102));