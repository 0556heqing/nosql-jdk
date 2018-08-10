package com.heqing.nosql.mongodb;

import com.mongodb.gridfs.GridFSDBFile;
import org.junit.Test;

import java.io.File;

/**
 * @author heqing
 * @date 2018/8/10 10:08
 */
public class TestFile {

    String dbName = "hq_test";

    @Test
    public void testCreateFile() {
        File file = new File("E:\\test\\test.jpg");
        boolean result = MongodbUtil.getFile().createFile(dbName, file);
        System.out.println("--->result = "+result);

        result = MongodbUtil.getFile().createFile(dbName,"E:\\test", "test.jpg");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testGetFile() {
        boolean result = MongodbUtil.getFile().getFile(dbName, "E:\\test", "test.jpg");
        System.out.println("--->result = "+result);

        GridFSDBFile gridFSDBFile = MongodbUtil.getFile().getGridFileByName(dbName, "test.jpg");
        result = MongodbUtil.getFile().getFile(gridFSDBFile, "E:\\test","test.jpg");
        System.out.println("--->result = "+result);
    }

    @Test
    public void testDeleteFile() {
        MongodbUtil.getFile().deleteByFileId(dbName, 1533873207312L);

        MongodbUtil.getFile().deleteFileByName(dbName, "test.jpg");
    }
}
