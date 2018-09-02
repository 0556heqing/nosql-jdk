package com.heqing.nosql.mongodb.manager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author heqing
 * @date 2018/8/9 14:20
 */
public class MongodbManager {

    private static final Logger logger = LoggerFactory.getLogger(MongodbManager.class);

    public static MongoClient mongoClient = null;

    protected static MongoClientOptions.Builder build = new MongoClientOptions.Builder();

    /** 服务器IP和端口 */
    protected static String address = "";

    /** 数据库名 */
    protected static String databaseName = "";

    /** 用户名P */
    protected static String userName = "";

    /** 密码 */
    protected static String password = "";

    /** 连接池的最大数量，默认为100 */
    protected static int perHost = 100;

    /** 连接超时，推荐>3000毫秒 */
    protected static int timeout = 3000;

    /** 最大等待时间 */
    protected static int maxWaitTime = 5000;

    /** 套接字超时时间，0无限制 */
    protected static int socketTimeout = 0;

    /** 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。 */
    protected static int allowedToBlockForConnection = 5000;

    static {
        ResourceBundle budleEnv = ResourceBundle.getBundle("mongodb_config");

        address = budleEnv.getString("mongodb.address");
        databaseName = budleEnv.getString("mongodb.db_name");
        userName = budleEnv.getString("mongodb.user_name");
        password = budleEnv.getString("mongodb.password");
        perHost = Integer.parseInt(budleEnv.getString("mongodb.per_host"));
        timeout = Integer.parseInt(budleEnv.getString("mongodb.time_out"));
        maxWaitTime = Integer.parseInt(budleEnv.getString("mongodb.max_wait_time"));
        socketTimeout = Integer.parseInt(budleEnv.getString("mongodb.socket_time_out"));
        allowedToBlockForConnection = Integer.parseInt(budleEnv.getString("mongodb.allowed_to_block_for_connection"));

        // mongo系统参数
        build.connectionsPerHost(perHost);
        build.connectTimeout(timeout);
        build.maxWaitTime(maxWaitTime);
        build.socketTimeout(socketTimeout);
        build.threadsAllowedToBlockForConnectionMultiplier(allowedToBlockForConnection);
    }

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            try {
                MongoClientOptions options = build.build();
                // 数据库地址端口
                List<ServerAddress> addrList = new ArrayList<>();
                String delimiter = ", *";
                for (String hostport : address.split(delimiter)) {
                    if (hostport == null || "".equals(hostport)) {
                        continue;
                    }
                    hostport = hostport.trim();

                    ServerAddress serverAddress = new ServerAddress(hostport.split(":")[0], Integer.valueOf(hostport.split(":")[1]));
                    addrList.add(serverAddress);
                }

                if(databaseName == null || "".equals(databaseName)) {
                    mongoClient = new MongoClient(addrList, options);
                } else {
                    // MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
                    List<MongoCredential> credentialList = new ArrayList<>();
                    MongoCredential credential = MongoCredential.createScramSha1Credential(userName, databaseName, password.toCharArray());
                    credentialList.add(credential);
                    mongoClient = new MongoClient(addrList, credential, options);
                }
            } catch (Exception e) {
                logger.error("初始化 ShardedJedis 服务器失败>>>" + e.getMessage(), e);
            }
        }
        return mongoClient;
    }

    public static void closeClient() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

}
