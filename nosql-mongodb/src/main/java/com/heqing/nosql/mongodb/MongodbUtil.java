package com.heqing.nosql.mongodb;

import com.heqing.nosql.mongodb.manager.MongodbManager;
import com.heqing.nosql.mongodb.service.CollectionService;
import com.heqing.nosql.mongodb.service.DataBaseService;
import com.heqing.nosql.mongodb.service.DocumentService;
import com.heqing.nosql.mongodb.service.FileService;
import com.heqing.nosql.mongodb.service.impl.CollectionServiceImpl;
import com.heqing.nosql.mongodb.service.impl.DataBaseServiceImpl;
import com.heqing.nosql.mongodb.service.impl.DocumentServiceImpl;
import com.heqing.nosql.mongodb.service.impl.FileServiceImpl;
import com.mongodb.MongoClient;

/**
 * @author heqing
 * @date 2018/8/9 15:44
 */
public class MongodbUtil {

    private static MongoClient mongoClient;
    private static DataBaseService dataBaseOperations;
    private static CollectionService collectionOperations;
    private static DocumentService documentOperations;
    private static FileService fileOperations;

    public static MongoClient getMongoClient() {
        if(mongoClient == null) {
            mongoClient = MongodbManager.mongoClient;
        }
        return mongoClient;
    }

    public static void closeClient() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
    /**
     * 该方法用于获取操作库的方法集合
     */
    public static DataBaseService getDataBase() {
        if(dataBaseOperations == null) {
            dataBaseOperations = new DataBaseServiceImpl();
        }
        return dataBaseOperations;
    }

    /**
     * 该方法用于获取操作集合的方法集合
     */
    public static CollectionService getCollection() {
        if(collectionOperations == null) {
            collectionOperations = new CollectionServiceImpl();
        }
        return collectionOperations;
    }

    /**
     * 该方法用于获取操作文档的方法集合
     */
    public static DocumentService getDocument() {
        if(documentOperations == null) {
            documentOperations = new DocumentServiceImpl();
        }
        return documentOperations;
    }

    /**
     * 该方法用于获取操作文件的方法集合
     */
    public static FileService getFile(String dbName) {
        if(fileOperations == null) {
            fileOperations = new FileServiceImpl(dbName);
        }
        return fileOperations;
    }

}
