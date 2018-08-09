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

    /** 服务器IP和端口 */
    protected static String mongodbAddress = "";

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
        try {
            ResourceBundle budleEnv = ResourceBundle.getBundle("mongodb_config");

            mongodbAddress = budleEnv.getString("mongodbAddress");
            databaseName = budleEnv.getString("databaseName");
            userName = budleEnv.getString("userName");
            password = budleEnv.getString("password");
            perHost = Integer.parseInt(budleEnv.getString("perHost"));
            timeout = Integer.parseInt(budleEnv.getString("timeout"));
            maxWaitTime = Integer.parseInt(budleEnv.getString("maxWaitTime"));
            socketTimeout = Integer.parseInt(budleEnv.getString("socketTimeout"));
            allowedToBlockForConnection = Integer.parseInt(budleEnv.getString("allowedToBlockForConnection"));

            // mongo系统参数
            MongoClientOptions.Builder build = new MongoClientOptions.Builder();
            build.connectionsPerHost(perHost);
            build.connectTimeout(timeout);
            build.maxWaitTime(maxWaitTime);
            build.socketTimeout(socketTimeout);
            build.threadsAllowedToBlockForConnectionMultiplier(allowedToBlockForConnection);
            MongoClientOptions options = build.build();

            // 数据库地址端口
            List<ServerAddress> addrList = new ArrayList<>();
            String delimiter = ", *";
            for (String hostport : mongodbAddress.split(delimiter)) {
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
}
