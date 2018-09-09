package com.heqing.nosql.neo4j;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.neo4j.driver.v1.*;

import java.util.Iterator;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * @author heqing
 * @date 2018/9/7 9:38
 */
public class TestCql {

    @Test
    public void test() {

        Driver driver = GraphDatabase.driver( "bolt://127.0.0.1:7687", AuthTokens.basic( "neo4j", "246512" ) );
        Session session = driver.session();

//        session.run( "CREATE (wd:P {name: {name}, title: {title}})",
//        parameters( "name", "node", "title", "Test" ) );

        StatementResult result = session.run( "MATCH (node:Person)  " +
                        "RETURN node",
                parameters( "name", "node" ) );
        while (result.hasNext()) {
            Record record = result.next();
            Iterator<String> v = record.get(0).asNode().labels().iterator();
            List<String> myList= Neo4jUtil.IteratorToList(v);
            System.out.println("-->"+JSON.toJSONString(myList));

            System.out.println("-->"+JSON.toJSONString(record.get(0).asNode().id()));
            System.out.println("-->"+JSON.toJSONString(record.get(0).asNode().asMap()));
//            System.out.println("-->"+record.get("node"));
//            System.out.println( record.get( "title" ).asString() + " " + record.get( "node" ).asString() );
        }
        session.close();
        driver.close();
    }
}
