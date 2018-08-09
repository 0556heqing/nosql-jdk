package com.heqing.nosql.mongodb.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

/**
 * 集合
 * @author heqing
 * @date 2018/8/9 15:44
 */
public interface CollectionService extends DataBaseService {

    /**
     * 在指定的数据库中创建一个名为 collectionName 的集合。
     * @param dbName 指定的数据库
     * @param collectionName 集合名
     * @return 是否成功，成功返回true,失败返回false
     */
    boolean createCollection(String dbName, String collectionName);

    /**
     * 在指定的数据库中删除一个名为 collectionName 的集合。
     * @param dbName 指定的数据库
     * @param collectionName 集合名
     * @return 是否成功，成功返回true,失败返回false
     */
    boolean deleteCollection(String dbName, String collectionName);

    /**
     * 获取某个库下，某个集合中文档
     * @param dbName	库名
     * @param collName	集合名
     * @return  集合中所有文档
     */
    MongoCollection<Document> getCollectionByName(String dbName, String collName);

    /**
     * 获取某个库下，某个集合中文档的名字
     * 注意：dbName相当于关系数据库里的数据库名，mongodb中若没有该数据库名也不会报错，默认mongodb会建立这个数据库名，为空。
     * @param dbName 库名
     * @return 集合中所有文档的名字
     */
    List<String> getAllCollections(String dbName);

}
