package com.heqing.nosql.mongodb.service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.List;

/**
 * 库
 * @author heqing
 * @date 2018/8/9 15:44
 */
public interface DataBaseService {

    /**
     * 该删除一个名为 dbName 的 MongoDB 数据库实例。注意：mongodb 区分大小写
     * @param dbName 数据库名
     */
    void deleteMongoByName(String dbName);

    /**
     * 查询DB下的所有表名。注意：只能返回有文档集合的库名
     * @return 所有数据库的名称
     */
    List<String> getDBAllName();

    /**
     * 获取一个 MongoDB 数据库实例。
     * 注意：mongodb 区分大小写dataBaseName相当于关系数据库里的数据库名，mongodb中若没有该数据库名也不会报错，默认mongodb会建立这个数据库名，为空。
     * @param dbName 数据库名
     * @return 数据库实例。
     */
    MongoDatabase getMongoByName(String dbName);

}
