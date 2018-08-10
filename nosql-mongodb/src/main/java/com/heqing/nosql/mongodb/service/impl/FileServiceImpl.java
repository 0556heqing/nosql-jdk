package com.heqing.nosql.mongodb.service.impl;

import com.heqing.nosql.mongodb.service.FileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @author heqing
 * @date 2018/8/9 15:44
 */
public class FileServiceImpl extends DataBaseServiceImpl implements FileService {

    @Override
    public boolean createFile(String dbName, File file) {
        return createFile(dbName, file.getParent(), file.getName());
    }

    @Override
    public boolean createFile(String dbName, String filePath, String fileName) {
        File file =new File(filePath+"/"+fileName);
        if(!file.exists()) {
            return false;
        }
        try {
            Object id = System.currentTimeMillis();
            DBObject query  = new BasicDBObject("_id", id);
            GridFS gridFS = getGridFS(dbName);
            GridFSDBFile gridFSDBFile = gridFS.findOne(query);
            if(gridFSDBFile == null){
                GridFSInputFile gridFSInputFile = gridFS.createFile(file);
                gridFSInputFile.setId(id);
                gridFSInputFile.setFilename(fileName);
                gridFSInputFile.save();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void deleteByFileId(String dbName, Long id) {
        GridFS gridFS = getGridFS(dbName);
        DBObject query  = new BasicDBObject("_id", id);
        gridFS.remove(query);
    }

    @Override
    public void deleteFileByName(String dbName, String fileName) {
        GridFS gridFS = getGridFS(dbName);
        gridFS.remove(fileName);
    }

    @Override
    public boolean getFile(String dbName, String filePath, String fileName) {
        GridFSDBFile gridFSDBFile = getGridFileByName(dbName, fileName);
        if(gridFSDBFile == null) {
            return false;
        }
        return getFile(gridFSDBFile, filePath, fileName);
    }

    @Override
    public boolean getFile(GridFSDBFile gridFSDBFile, String filePath, String fileName) {
        if(gridFSDBFile != null){
            try {
                gridFSDBFile.writeTo(new FileOutputStream(filePath+"/"+fileName));
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public GridFS getGridFS(String dbName) {
        GridFS gridFS = new GridFS(getMongoClient().getDB(dbName));
        return gridFS;
    }

    @Override
    public GridFSDBFile getGridFileById(String dbName, Long id) {
        DBObject query  = new BasicDBObject("_id", id);
        GridFSDBFile gridFSDBFile = getGridFS(dbName).findOne(query);
        return gridFSDBFile;
    }

    @Override
    public GridFSDBFile getGridFileByName(String dbName, String fileName) {
        DBObject query  = new BasicDBObject("filename", fileName);
        GridFSDBFile gridFSDBFile = getGridFS(dbName).findOne(query);
        return gridFSDBFile;
    }

}
