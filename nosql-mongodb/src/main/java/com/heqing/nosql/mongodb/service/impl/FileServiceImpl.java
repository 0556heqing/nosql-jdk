package com.heqing.nosql.mongodb.service.impl;

import com.heqing.nosql.mongodb.service.FileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author heqing
 * @date 2018/8/9 15:44
 */
public class FileServiceImpl extends DataBaseServiceImpl implements FileService {

    GridFS gridFS = null;

    public FileServiceImpl(String dbName) {
        if(gridFS == null) {
            gridFS = new GridFS(getMongoClient().getDB(dbName));
        }
    }


    @Override
    public GridFSDBFile getFileById(Object id) {
        DBObject query  = new BasicDBObject("_id", id);
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        return gridFSDBFile;
    }

    @Override
    public GridFSDBFile getFileByName(String fileName) {
        DBObject query  = new BasicDBObject("filename", fileName);
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        return gridFSDBFile;
    }

    @Override
    public boolean write(File file) {
        return write(file.getAbsolutePath(), file.getName());
    }

    @Override
    public boolean write(String filePath, String fileName) {
        File file =new File(filePath+"/"+fileName);
        if(!file.exists()) {
            return false;
        }
        try {
            Object id = System.currentTimeMillis();
            DBObject query  = new BasicDBObject("_id", id);
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
    public boolean read(String filePath, String fileName) {
        GridFSDBFile gridFSDBFile = getFileByName(fileName);
        if(gridFSDBFile == null) {
            return false;
        }
        return read(gridFSDBFile, filePath, fileName);
    }

    @Override
    public boolean read(GridFSDBFile gridFSDBFile, String filePath, String fileName) {
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
    public void deleteByFileId(String id) {
        gridFS.remove(id);
    }

    @Override
    public void deleteByFileName(String fileName) {
        gridFS.remove(fileName);
    }
}
