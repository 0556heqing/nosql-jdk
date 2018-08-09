package com.heqing.nosql.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heqing
 * @date 2018/8/9 18:33
 */
public class TestDocument {

    @Test
    public void testCreateDocument() {
        List<Document> documentList = new ArrayList<>();
        Document document1 = new Document().append("name1", "hq1").append("age", 27);
        Document document2 = new Document().append("name2", "hq2").append("age", 28);
        documentList.add(document1);documentList.add(document2);

        MongodbUtil.getDocument().createDocument("hq_test", "test1", document1);

        MongodbUtil.getDocument().createDocument("hq_test", "test2", documentList);

        MongoCollection<Document> coll3 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test3");
        MongodbUtil.getDocument().createDocument(coll3, document1);

        MongoCollection<Document> coll4 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test4");
        MongodbUtil.getDocument().createDocument(coll4, documentList);
    }

    @Test
    public void testDeleteDocument() {
        int result = 0;
        result = MongodbUtil.getDocument().deleteDocumentById("hq_test", "test1", "5b6c216f92a9a5282c751e5f");
        System.out.println("---> result = "+result);

        MongoCollection<Document> coll3 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test3");
        result = MongodbUtil.getDocument().deleteDocumentById(coll3, "1");
        System.out.println("---> result = "+result);

        Bson filter = Filters.eq("name2", "hq2");
        result = MongodbUtil.getDocument().deleteDocument("hq_test", "test2", filter);
        System.out.println("---> result = "+result);

        MongoCollection<Document> coll4 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test4");
        result = MongodbUtil.getDocument().deleteDocument(coll4, filter);
        System.out.println("---> result = "+result);
    }

    @Test
    public void testGetDocumentById() {
        Document document = MongodbUtil.getDocument().getDocumentById("hq_test", "test1", "5b6c32c792a9a5465424dc3c");
        System.out.println("--->"+document.getString("name1"));

        MongoCollection<Document> coll3 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test3");
        document = MongodbUtil.getDocument().getDocumentById(coll3, "5b6c32c792a9a5465424dc3c");
        System.out.println("--->"+document.getString("name1"));
    }

    @Test
    public void testGetAllDocumentList() {
        List<Document> docList = MongodbUtil.getDocument().getAllDocumentList("hq_test", "test2");
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2"));
        }

        System.out.println("--------------------------------------");

        MongoCollection<Document> coll3 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test4");
        docList = MongodbUtil.getDocument().getAllDocumentList(coll3);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2"));
        }
    }

    @Test
    public void testGetDocumentListByParam() {
        Bson orderBy = Filters.eq("name1", "hq1");
        List<Document> docList = MongodbUtil.getDocument().getDocumentListByParam("hq_test", "test2", orderBy);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }

        System.out.println("--------------------------------------");

        MongoCollection<Document> coll3 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test4");
        docList = MongodbUtil.getDocument().getDocumentListByParam(coll3, orderBy);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }
    }

    @Test
    public void testGetDocumentListByOrder() {
        Bson orderBy = Filters.eq("age", -1);
        List<Document> docList = MongodbUtil.getDocument().getDocumentListByOrder("hq_test", "test2", orderBy);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }

        System.out.println("--------------------------------------");

        MongoCollection<Document> coll3 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test4");
        docList = MongodbUtil.getDocument().getDocumentListByOrder(coll3, orderBy);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }
    }

    @Test
    public void testGetDocumentListByPage() {
        List<Document> docList = MongodbUtil.getDocument().getDocumentListByPage("hq_test", "test2", 1, 2);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }

        System.out.println("--------------------------------------");

        MongoCollection<Document> coll4 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test4");
        docList = MongodbUtil.getDocument().getDocumentListByPage(coll4, 1, 1);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }
    }

    @Test
    public void testGetDocumentList() {
        Bson param = Filters.eq("name1", "hq1");
        Bson order = Filters.eq("age", -1);

        System.out.println("-----------------param + order---------------------");
        List<Document>  docList = MongodbUtil.getDocument().getDocumentListByParamAndOrder("hq_test", "test2", param, order);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }
        System.out.println("----------------param + page----------------------");

        docList = MongodbUtil.getDocument().getDocumentListByParamAndPage("hq_test", "test2", param, 1, 2);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }
        System.out.println("----------------order + page----------------------");

        docList = MongodbUtil.getDocument().getDocumentListByOrderAndPage("hq_test", "test2", order, 1, 2);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }

        System.out.println("----------------param + order + page----------------------");

        docList = MongodbUtil.getDocument().getDocumentList("hq_test", "test2", param, order, 1, 2);
        for(Document doc : docList) {
            System.out.println("--->name1="+doc.getString("name1")+", name2="+doc.getString("name2")+", age="+doc.getInteger("age"));
        }
    }

    @Test
    public void testUpdateDocument() {
        Document document1 = new Document().append("name1", "hq11");

        Document document = MongodbUtil.getDocument().updateDocumentById("hq_test", "test1", "5b6c32c792a9a5465424dc3c", document1);
        System.out.println("--->"+document.getString("name1"));

        document = MongodbUtil.getDocument().updateDocumentById("hq_test", "test2", "5b6c32c792a9a5465424dc3c", document1);
        System.out.println("--->"+document.getString("name1"));

        Bson filter = Filters.eq("name1", "hq1");

        document = MongodbUtil.getDocument().updateDocument("hq_test", "test3", filter, document1);
        System.out.println("--->"+document.getString("name1"));

        MongoCollection<Document> coll4 = MongodbUtil.getDocument().getCollectionByName("hq_test", "test4");
        document = MongodbUtil.getDocument().updateDocument(coll4, filter, document1);
        System.out.println("--->"+document.getString("name1"));
    }
}
