package com.heqing.nosql.mongodb.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * 文档
 * @author heqing
 * @date 2018/8/9 15:44
 */
public interface DocumentService extends CollectionService {

    /**
     * 在数据库集合中添加文档
     * @param dbName 数据库名
     * @param collName 集合名
     * @param document 新文档名
     */
    void createDocument(String dbName, String collName, Document document);

    /**
     * 在数据库集合中添加文档
     * @param dbName 数据库名
     * @param collName 集合名
     * @param documents 多个文档名
     */
    void createDocument(String dbName, String collName, List<Document> documents);

    /**
     * 在数据库集合中添加文档
     * @param coll 集合
     * @param document 新文档名
     */
    void createDocument(MongoCollection<Document> coll, Document document);

    /**
     * 在数据库集合中添加文档
     * @param coll 集合
     * @param documents 多个文档名
     */
    void createDocument(MongoCollection<Document> coll, List<Document> documents);

    /**
     * 通过 id 删除数据库集合中文档
     * @param dbName 库名
     * @param collName 集合名
     * @param id id
     * @return 删除数量
     */
    int deleteDocumentById(String dbName, String collName, String id);

    /**
     * 通过 id 删除数据库集合中文档
     * @param coll 集合
     * @param id id
     * @return 删除数量
     */
    int deleteDocumentById(MongoCollection<Document> coll, String id);

    /**
     * 通过 id 删除数据库集合中文档
     * @param dbName 库名
     * @param collName 集合名
     * @param filter 查询条件
     * @return 删除数量
     */
    int deleteDocument(String dbName, String collName, Bson filter);

    /**
     * 通过 id 删除数据库集合中文档
     * @param coll 集合
     * @param filter 查询条件
     * @return 删除数量
     */
    int deleteDocument(MongoCollection<Document> coll, Bson filter);

    /**
     * 通过 id 查找数据库集合中文档
     * @param dbName 库名
     * @param collName 集合名
     * @param id 文档id
     * @return 查找到文档
     */
    Document getDocumentById(String dbName, String collName, String id);

    /**
     * 通过 id 查找数据库集合中文档
     * @param coll 集合
     * @param id 文档id
     * @return 查找到文档
     */
    Document getDocumentById(MongoCollection<Document> coll, String id);

    /**
     * 查找数据库集合中所有文档
     * @param dbName 库名
     * @param collName 集合名
     * @return  查找到文档
     */
    List<Document> getAllDocumentList(String dbName, String collName);

    /**
     * 查找数据库集合中所有文档
     * @param coll 集合文档
     * @return 查找到文档
     */
    List<Document> getAllDocumentList(MongoCollection<Document> coll);

    /**
     * 获取文档数量
     * @param dbName 库名
     * @param collName 集合名
     * @return 查找到文档
     */
    long getDocumentCount(String dbName, String collName);

    /**
     * 获取文档数量
     * @param coll 集合文档
     * @return 查找到文档
     */
    long getDocumentCount(MongoCollection<Document> coll);

    /**
     * 根据条件获取文档数量
     * @param dbName 库名
     * @param collName 集合名
     * @param param	查询条件
     * @return 查找到文档
     */
    long getDocumentCountByParam(String dbName, String collName, Bson param);

    /**
     * 根据条件获取文档数量
     * @param coll 集合文档
     * @param param	查询条件
     * @return 查找到文档
     */
    long getDocumentCountByParam(MongoCollection<Document> coll, Bson param);

    /**
     * 根据条件查找文档
     * @param dbName 库名
     * @param collName 集合名
     * @param param	查询条件
     * @return 查找到文档
     */
    List<Document> getDocumentListByParam(String dbName, String collName, Bson param);

    /**
     * 根据条件查找文档
     * @param coll 集合文档
     * @param param	查询条件
     * @return 查找到文档
     */
    List<Document> getDocumentListByParam(MongoCollection<Document> coll, Bson param);

    /**
     * 查找数据库集合中所有文档，并排序
     * @param dbName 库名
     * @param collName 集合名
     * @param order	排序条件
     * @return 查找到文档
     */
    List<Document> getDocumentListByOrder(String dbName, String collName, Bson order);

    /**
     * 查找数据库集合中所有文档，并排序
     * @param coll 集合文档
     * @param order	排序条件
     * @return 查找到文档
     */
    List<Document> getDocumentListByOrder(MongoCollection<Document> coll, Bson order);

    /**
     * 根据页码查找文档
     * @param dbName 库名
     * @param collName 集合名
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return 查找到文档
     */
    List<Document> getDocumentListByPage(String dbName, String collName, int pageNo, int pageSize);

    /**
     * 根据页码查找文档
     * @param coll 集合文档
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return 查找到文档
     */
    List<Document> getDocumentListByPage(MongoCollection<Document> coll, int pageNo, int pageSize);

    /**
     * 根据条件查找文档,并且根据排序条件排序
     * @param dbName 库名
     * @param collName 集合名
     * @param param	查询条件
     * @param order	排序条件
     * @return 查找到文档
     */
    List<Document> getDocumentListByParamAndOrder(String dbName, String collName, Bson param, Bson order);

    /**
     * 根据条件查找文档,并且根据排序条件排序
     * @param coll 集合文档
     * @param param	查询条件
     * @param orderBy 排序条件
     * @return 查找到文档
     */
    List<Document> getDocumentListByParamAndOrder(MongoCollection<Document> coll, Bson param, Bson orderBy);

    /**
     * 根据条件查找文档,并且分页
     * @param dbName 库名
     * @param collName 集合名
     * @param param	查询条件
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return 查找到文档
     */
    List<Document> getDocumentListByParamAndPage(String dbName, String collName, Bson param, int pageNo, int pageSize);

    /**
     * 根据条件查找文档,并且分页
     * @param coll 集合文档
     * @param param	查询条件
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return 查找到文档
     */
    List<Document> getDocumentListByParamAndPage(MongoCollection<Document> coll, Bson param, int pageNo, int pageSize);

    /**
     * 根据条件排序查找文件,并且分页
     * @param dbName 库名
     * @param collName 集合名
     * @param order	排序条件
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return 查找到文档
     */
    List<Document> getDocumentListByOrderAndPage(String dbName, String collName, Bson order, int pageNo, int pageSize);

    /**
     * 根据条件排序查找文件,并且分页
     * @param coll 集合文档
     * @param order	排序条件
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return 查找到文档
     */
    List<Document> getDocumentListByOrderAndPage(MongoCollection<Document> coll, Bson order, int pageNo, int pageSize);

    /**
     * 根据条件查找文档,并且根据排序条件排序,并且根据条件分页
     * @param dbName 库名
     * @param collName 集合名
     * @param param	条件查询
     * @param order	排序查询
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return 查找到文档
     */
    List<Document> getDocumentList(String dbName, String collName, Bson param, Bson order, int pageNo, int pageSize);

    /**
     * 根据条件查找文档,并且根据排序条件排序,并且根据条件分页
     * @param coll 集合文档
     * @param param	条件查询
     * @param order	排序查询
     * @param pageNo 页数
     * @param pageSize 每页数量
     * @return  查找到文档
     */
    List<Document> getDocumentList(MongoCollection<Document> coll, Bson param, Bson order, int pageNo, int pageSize);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param dbName 库名
     * @param collName 集合名
     * @param id id
     * @param newdoc 新文档
     * @return 修改后的文档
     */
    Document updateDocumentById(String dbName, String collName, String id, Document newdoc);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param coll 集合文档
     * @param id id
     * @param newdoc 新文档
     * @return 修改后的文档
     */
    Document updateDocumentById(MongoCollection<Document> coll, String id, Document newdoc);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param dbName 库名
     * @param collName 集合名
     * @param filter 查询条件
     * @param newdoc 新文档
     * @return 修改后的文档
     */
    Document updateDocument(String dbName, String collName, Bson filter, Document newdoc);

    /**
     * 根据文档 Id 修改数据库集合中文档
     * @param coll 集合文档
     * @param filter 查询条件
     * @param newdoc 新文档
     * @return 修改后的文档
     */
    Document updateDocument(MongoCollection<Document> coll, Bson filter, Document newdoc);

}
