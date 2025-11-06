package oop.tanregister.register;

import com.mongodb.client.*;
import org.bson.Document;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB {
    private static final String CONNECTION_STRING = "mongodb+srv://jeshemymarie:kUkwADF4GNu4aMgE@test-cluster.6ihleq7.mongodb.net/?retryWrites=true&w=majority";
    private static final String DB_NAME = "Userdata";
    private static final String COLLECTION_NAME = "users";

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    static {
        mongoClient = MongoClients.create(CONNECTION_STRING);
        database = mongoClient.getDatabase(DB_NAME);
        collection = database.getCollection(COLLECTION_NAME);
    }

    public static boolean emailExists(String email) {
        Document user = collection.find(eq("email", email)).first();
        return user != null;
    }

    public static void addUser(String firstName, String lastName, String email,
                               String phone, String address, String password,
                               LocalDateTime createdAt, LocalDateTime updatedAt) throws Exception {

        String passwordHash = hashPassword(password);

        Document doc = new Document("first_name", firstName)
                .append("last_name", lastName)
                .append("email", email)
                .append("phone", phone)
                .append("address", address)
                .append("password_hash", passwordHash)
                .append("created_At", Date.from(createdAt.toInstant(ZoneOffset.UTC)))
                .append("updated_At", Date.from(updatedAt.toInstant(ZoneOffset.UTC)));

        collection.insertOne(doc);
    }

    public static boolean validateLogin(String email, String password) throws Exception {
        String passwordHash = hashPassword(password);

        Document user = collection.find(
                new Document("email", email).append("password_hash", passwordHash)
        ).first();
        return user != null;
    }

    private static String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
