package com.heqing.nosql.mongodb.service.impl;

import com.heqing.nosql.mongodb.service.DocumentService;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heqing
 * @date 2018/8/9 15:44
 */
public class DocumentServiceImpl extends CollectionServiceImpl implements DocumentService {

    @Override
    public void createDocument(String dbName, String collName, Document document) {
        createDocument(getCollectionByName(dbName, collName), document);
    }

    @Override
    public void createDocument(String dbName, String collName, List<Document> documents) {
        createDocument(getCollectionByName(dbName, collName), documents);
    }

    @Override
    public void createDocument(MongoCollection<Document> coll, Document document) {
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        createDocument(coll, documents);
    }

    @Override
    public void createDocument(MongoCollection<Document> coll, List<Document> documents) {
        coll.insertMany(documents);
    }

    @Override
    public int deleteDocumentById(String dbName, String collName, String id) {
        return deleteDocumentById(getCollectionByName(dbName, collName), id);
    }

    @Override
    public int deleteDocumentById(MongoCollection<Document> coll, String id) {
        ObjectId tempId;
        try {
            tempId = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", tempId);
        return deleteDocument(coll, filter);
    }

    @Override
    public int deleteDocument(String dbName, String collName, Bson filter) {
        return deleteDocument(getCollectionByName(dbName, collName), filter);
    }

    @Override
    public int deleteDocument(MongoCollection<Document> coll, Bson filter) {
        DeleteResult deleteResult = coll.deleteOne(filter);
        return (int) deleteResult.getDeletedCount();
    }

    @Override
    public Document getDocumentById(String dbName, String collName, String id) {
        return getDocumentById(getCollectionByName(dbName, collName), id);
    }

    @Override
    public Document getDocumentById(MongoCollection<Document> coll, String id) {
        ObjectId idObj;
        try {
            idObj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Document myDoc = coll.find(Filters.eq("_id", idObj)).first();
        return myDoc;
    }

    @Override
    public List<Document> listAllDocument(String dbName, String collName) {
        return listAllDocument(getCollectionByName(dbName, collName));
    }

    @Override
    public List<Document> listAllDocument(MongoCollection<Document> coll) {
        Bson orderBy = new BasicDBObject("_id", -1);
        MongoCursor<Document> mongoCursor = coll.find().sort(orderBy).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public long getDocumentCount(String dbName, String collName) {
        return getDocumentCount(getCollectionByName(dbName, collName));
    }

    @Override
    public long getDocumentCount(MongoCollection<Document> coll) {
        return coll.countDocuments();
    }

    @Override
    public long getDocumentCountByParam(String dbName, String collName, Bson param) {
        return getDocumentCountByParam(getCollectionByName(dbName, collName), param);
    }

    @Override
    public long getDocumentCountByParam(MongoCollection<Document> coll, Bson param) {
        return coll.countDocuments(param);
    }

    @Override
    public List<Document> listDocumentByParam(String dbName, String collName, Bson param) {
        return listDocumentByParam(getCollectionByName(dbName, collName), param);
    }

    @Override
    public List<Document> listDocumentByParam(MongoCollection<Document> coll, Bson param) {
        Bson orderBy = new BasicDBObject("_id", -1);
        MongoCursor<Document> mongoCursor = coll.find(param).sort(orderBy).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public List<Document> listDocumentByOrder(String dbName, String collName, Bson order) {
        return listDocumentByOrder(getCollectionByName(dbName, collName), order);
    }

    @Override
    public List<Document> listDocumentByOrder(MongoCollection<Document> coll, Bson order) {
        MongoCursor<Document> mongoCursor = coll.find().sort(order).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public List<Document> listDocumentByPage(String dbName, String collName, int pageNo, int pageSize) {
        return listDocumentByPage(getCollectionByName(dbName, collName), pageNo, pageSize);
    }

    @Override
    public List<Document> listDocumentByPage(MongoCollection<Document> coll, int pageNo, int pageSize) {
        MongoCursor<Document> mongoCursor = coll.find().skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public List<Document> listDocumentByParamAndOrder(String dbName, String collName, Bson param, Bson order) {
        return listDocumentByParamAndOrder(getCollectionByName(dbName, collName), param, order);
    }

    @Override
    public List<Document> listDocumentByParamAndOrder(MongoCollection<Document> coll, Bson param, Bson orderBy) {
        MongoCursor<Document> mongoCursor = coll.find(param).sort(orderBy).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public List<Document> listDocumentByParamAndPage(String dbName, String collName, Bson param, int pageNo, int pageSize) {
        return listDocumentByParamAndPage(getCollectionByName(dbName, collName), param, pageNo, pageSize);
    }

    @Override
    public List<Document> listDocumentByParamAndPage(MongoCollection<Document> coll, Bson param, int pageNo, int pageSize) {
        MongoCursor<Document> mongoCursor = coll.find(param).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public List<Document> listDocumentByOrderAndPage(String dbName, String collName, Bson order, int pageNo, int pageSize) {
        return listDocumentByOrderAndPage(getCollectionByName(dbName, collName), order, pageNo, pageSize);
    }

    @Override
    public List<Document> listDocumentByOrderAndPage(MongoCollection<Document> coll, Bson order, int pageNo, int pageSize) {
        if(order == null) {
            order = new BasicDBObject("_id", -1);
        }
        MongoCursor<Document> mongoCursor = coll.find().sort(order).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public List<Document> listDocument(String dbName, String collName, Bson param, Bson order, int pageNo, int pageSize) {
        return listDocument(getCollectionByName(dbName, collName), param, order, pageNo, pageSize);
    }

    @Override
    public List<Document> listDocument(MongoCollection<Document> coll, Bson param, Bson order, int pageNo, int pageSize) {
        if(order == null) {
            order = new BasicDBObject("_id", -1);
        }
        MongoCursor<Document> mongoCursor = coll.find(param).sort(order).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
        List<Document> result = new ArrayList<>();
        while(mongoCursor.hasNext()){
            result.add(mongoCursor.next());
        }
        return result;
    }

    @Override
    public Document updateDocumentById(String dbName, String collName, String id, Document newdoc) {
        return updateDocumentById(getCollectionByName(dbName, collName), id, newdoc);
    }

    @Override
    public Document updateDocumentById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId idObj;
        try {
            idObj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Bson filter = Filters.eq("_id", idObj);
        return updateDocument(coll, filter, newdoc);
    }

    @Override
    public Document updateDocument(String dbName, String collName, Bson filter, Document newdoc) {
        return updateDocument(getCollectionByName(dbName, collName), filter, newdoc);
    }

    @Override
    public Document updateDocument(MongoCollection<Document> coll, Bson filter, Document newdoc) {
        // coll.replaceOne(filter, newdoc); // 完全替代
        coll.updateMany(filter, new Document("$set", newdoc));
        return newdoc;
    }

}
