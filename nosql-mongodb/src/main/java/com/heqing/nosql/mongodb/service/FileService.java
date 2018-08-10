package com.heqing.nosql.mongodb.service;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;

/**
 * @author heqing
 * @date 2018/8/9 15:44
 */
public interface FileService extends DataBaseService {

    /**
     * 该方法用于将文件保存在 MongoDB 中
     * @param dbName 库名
     * @param file 需要保存的文件
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    boolean createFile(String dbName, File file);

    /**
     * 该方法用于将文件保存在 MongoDB 中
     * @param dbName 库名
     * @param filePath 文件地址
     * @param fileName 文件名
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    boolean createFile(String dbName, String filePath, String fileName);

    /**
     * 该方法用于根据文件 Id 删除保存在 MongoDB 中的文件
     * @param dbName 库名
     * @param id 文件Id
     */
    void deleteByFileId(String dbName, Long id);

    /**
     * 该方法用于根据文件文件名删除保存在 MongoDB 中的文件
     * @param dbName 库名
     * @param fileName 文件名
     */
    void deleteFileByName(String dbName, String fileName);

    /**
     * 该方法用于将保存在 MongoDB 中的文件输出到指定地址
     * @param dbName 库名
     * @param filePath 需要保存的地址
     * @param fileName 保存在 MongoDB 中的文件名
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    boolean getFile(String dbName, String filePath, String fileName);

    /**
     * 该方法用于将保存在 MongoDB 中的文件输出到指定地址
     * @param gridFSDBFile 文件地址
     * @param filePath 需要保存的地址
     * @param fileName 保存在 MongoDB 中的文件名
     * @return 是否成功，成功返回 true ，失败返回false。
     */
    boolean getFile(GridFSDBFile gridFSDBFile, String filePath, String fileName);

    /**
     * 获取文件规范
     * @param dbName 库名
     * @return 文件规范
     */
    GridFS getGridFS(String dbName);

    /**
     * 该方法用于根据文件Id 返回文件，如果有多个则只返回第一个
     * @param dbName 库名
     * @param id 文件id
     * @return 流文件
     */
    GridFSDBFile getGridFileById(String dbName, Long id);

    /**
     * 该方法用于根据文件名返回文件，如果有多个则只返回第一个
     * @param dbName 库名
     * @param fileName 文件名
     * @return 流文件
     */
    GridFSDBFile getGridFileByName(String dbName, String fileName);

}
