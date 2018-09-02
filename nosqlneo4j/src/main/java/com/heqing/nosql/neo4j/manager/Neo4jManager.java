package com.heqing.nosql.neo4j.manager;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * @author heqing
 * @date 2018/8/24 15:51
 */
public class Neo4jManager {

    private static final Logger logger = LoggerFactory.getLogger(Neo4jManager.class);

    public static Driver neo4jDriver = null;

    /** 服务器IP和端口 */
    protected static String ip = "";

    /** 数据库名 */
    protected static String port = "";

    /** 用户名P */
    protected static String userName = "";

    /** 密码 */
    protected static String password = "";

    public Neo4jManager() { }

    static {
        ResourceBundle budleEnv = ResourceBundle.getBundle("neo4j_config");

        ip = budleEnv.getString("neo4j.ip");
        port = budleEnv.getString("neo4j.port");
        userName = budleEnv.getString("neo4j.user_name");
        password = budleEnv.getString("neo4j.password");

    }

    public static Driver getNeo4jDriver() {
        if (neo4jDriver == null) {
            neo4jDriver = GraphDatabase.driver("bolt://"+ip+":"+port, AuthTokens.basic(userName, password));
        }
        return neo4jDriver;
    }

    public static void closeNeo4jDriver() {
        neo4jDriver.close();
    }
}
