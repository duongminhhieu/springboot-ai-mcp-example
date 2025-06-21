package com.ai.spring.mcpserver.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoService {

    private static final Logger logger = LoggerFactory.getLogger(MongoService.class);

    private final MongoClient mongoClient;

    /**
     * Lists all databases in MongoDB.
     */
    @Tool(description = "[MongoDB] List all databases")
    public List<String> listDatabases() {
        logger.info("Fetching list of databases.");
        List<String> databaseNames = new ArrayList<>();
        for (Document db : mongoClient.listDatabases()) {
            databaseNames.add(db.getString("name"));
        }
        logger.info("Databases found: {}", databaseNames);
        return databaseNames;
    }

    /**
     * Lists all collections in the specified database.
     */
    @Tool(description = "[MongoDB] List all collections in the specified database.")
    public List<String> listCollections(String dbName) {
        logger.info("Fetching collections for database: {}", dbName);
        List<String> collectionNames = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase(dbName);
        for (String name : database.listCollectionNames()) {
            collectionNames.add(name);
        }
        logger.info("Collections found in {}: {}", dbName, collectionNames);
        return collectionNames;
    }

    /**
     * Gets all documents in a specific collection.
     */
    @Tool(description = "[MongoDB] Get all documents in a specific collection.")
    public List<Document> getAllDocuments(String dbName, String collectionName) {
        logger.info("Fetching all documents from {}.{}", dbName, collectionName);
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        List<Document> documents = new ArrayList<>();
        collection.find().into(documents);
        logger.info("Found {} documents in {}.{}", documents.size(), dbName, collectionName);
        return documents;
    }


    /**
     * Executes a complex query on a collection.
     */
    @Tool(description = "[MongoDB] Execute a complex query on a collection.")
    public List<Document> complexQuery(String dbName, String collectionName, String jsonQuery) {
        logger.info("Executing complex query on {}.{} with query: {}", dbName, collectionName, jsonQuery);
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        Document query = Document.parse(jsonQuery);
        List<Document> results = new ArrayList<>();
        collection.find(query).into(results);
        logger.info("Complex query returned {} results.", results.size());
        return results;
    }

    /**
     * Lists all indexes for a specific collection.
     */
    @Tool(description = "[MongoDB] List all indexes for a specific collection.")
    public List<Document> listIndexes(String dbName, String collectionName) {
        logger.info("Fetching indexes for {}.{}", dbName, collectionName);
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        List<Document> indexes = new ArrayList<>();
        collection.listIndexes().into(indexes);
        logger.info("Indexes found: {}", indexes);
        return indexes;
    }

    /**
     * Creates a new collection in the specified database.
     */
    @Tool(description = "[MongoDB] Create a new collection in the specified database.")
    public String createCollection(String dbName, String collectionName) {
        logger.info("Creating collection '{}' in database '{}'", collectionName, dbName);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        database.createCollection(collectionName);
        logger.info("Collection '{}' created successfully.", collectionName);
        return "Collection '" + collectionName + "' created successfully in database '" + dbName + "'.";
    }

    /**
     * Inserts a document into a collection.
     */
    @Tool(description = "[MongoDB] Insert a document into a collection.")
    public String insertDocument(String dbName, String collectionName, String jsonDocument) {
        logger.info("Inserting document into {}.{}: {}", dbName, collectionName, jsonDocument);
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        Document document = Document.parse(jsonDocument);
        collection.insertOne(document);
        logger.info("Document inserted successfully into {}.{}", dbName, collectionName);
        return "Document inserted successfully into collection '" + collectionName + "'.";
    }
}
