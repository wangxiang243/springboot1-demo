package com.wx.springbootdemo;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.util.JdbcUtils;
import com.wx.springbootdemo.util.Base64Utils;
import com.wx.springbootdemo.util.MySqlParserUtils;
import com.wx.springbootdemo.util.UserUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import java.util.Base64;
import java.util.List;

public class CommonTest {

    @Test
    public void test1() {

        System.out.println("XMLHttpRequest".equalsIgnoreCase("xmLhttprequest"));

    }

    @Test
    public void testEncrypt() {

        String salt = "8d78869f470951332959580424d4bf4f";
        HashRequest hashRequest = new HashRequest.Builder()
                .setAlgorithmName("md5").setSource(ByteSource.Util.bytes("123456"))
                .setSalt(ByteSource.Util.bytes("admin" + salt)).setIterations(2).build();
//        hashRequest.hashCode()
        DefaultHashService defaultHashService = new DefaultHashService();
        String pwd = defaultHashService.computeHash(hashRequest).toHex();
        System.out.println(pwd);

        SecureRandomNumberGenerator randomNumberGenerator =
                new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        String hex = randomNumberGenerator.nextBytes().toHex();
        System.out.println(hex);

        DefaultPasswordService defaultPasswordService = new DefaultPasswordService();
//        defaultPasswordService.creat
//        defaultPasswordService.

        String salt1 = new Md5Hash("wx" + "admin").toString();
        System.out.println(salt1);
        HashRequest hashRequest1 = new HashRequest.Builder().setAlgorithmName("md5").setSource(ByteSource.Util.bytes("123456")).setSalt("admin" + salt1).setIterations(2).build();
        String pwd1 = new DefaultHashService().computeHash(hashRequest1).toHex();
        System.out.println(pwd1);


    }

    public void a() {
    }

    protected void b() {
    }

    void c() {
    }

    private void d() {
    }


    @Test
    public void testbase64() {

        String sourcefilepath = "/Users/wangxiang/Desktop/sc.jpeg";
        String base64 = Base64Utils.getFileBase64(sourcefilepath);
        System.out.println(base64);

        String filepath = "/Users/wangxiang/Desktop/a.jpg";
        System.out.println(Base64Utils.generateFileByBase64(filepath, base64));


    }

    @Test
    public void testbyte() {
        String username = "system";
        String sourcePassword = "123456";
        String salt = username + new Md5Hash("wx" + username).toString();
        String password = UserUtils.getPassword(sourcePassword, salt);
        System.out.println("salt:" + salt);
        System.out.println("password:" + password);

        username = "ccc";
        salt = username + new Md5Hash("wx" + username).toString();
        password = UserUtils.getPassword(sourcePassword, salt);
        System.out.println("salt:" + salt);
        System.out.println("password:" + password);
    }

    @Test
    public void testsqlparse() {

        // String sql = "update t set name = 'x' where id < 100 limit 10";
//        String sql = "SELECT ID, NAME, AGE FROM USER WHERE ID = ? order by ID DESC limit 2";
        String sql = "select * from (SELECT ID, NAME, AGE FROM USER WHERE ID = ? order by ID DESC limit 2) where id = 1";
        // String sql = "select * from tablename limit 10";

//        String sql = "select user from emp_table";
        String dbType = JdbcConstants.MYSQL;

        //格式化输出
//        String result = SQLUtils.format(sql, dbType);
//        System.out.println(result); // 缺省大写格式
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

        //解析出的独立语句的个数
        System.out.println("size is:" + stmtList.size());
        for (int i = 0; i < stmtList.size(); i++) {

            SQLStatement stmt = stmtList.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            stmt.accept(visitor);

            //获取表名称
//            System.out.println("Tables : " + visitor.getCurrentTable());
            //获取操作方法名称,依赖于表名称
            System.out.println("Manipulation : " + visitor.getTables());
            //获取字段名称
            System.out.println("fields : " + visitor.getColumns());

            List<TableStat.Condition> conditionList = visitor.getConditions();
            System.out.println("where条件:" + conditionList);

            List<TableStat.Column> columnList = visitor.getOrderByColumns();
            System.out.println("orderby:" + columnList);


        }

        StringBuilder out = new StringBuilder();
        MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        for (SQLStatement statement : statementList) {
            statement.accept(visitor);
            visitor.println();
        }
        System.out.println(out.toString());

    }

    @Test
    public void testsql() {
//        String sql = "select * from (SELECT ID, NAME, AGE FROM USER WHERE ID = ? order by ID DESC limit 2) where id = 1";
        String sql = "select * from (SELECT ID, NAME, AGE FROM USER WHERE ID = ? order by ID DESC limit 2) ";
        String afterSql = MySqlParserUtils.parseSelectSqlStatementAndAddWhereCondition(sql, "a =3");
        System.out.println(afterSql);
    }


    @Test
    public void testsql1() {

//        String sql = " select eventId,eventKey,eventName,flag from event where eventId = ? and eventKey = ? and eventName = ? order by id desc limit 10";
        String sql = " select eventId,eventKey,eventName,flag from event order by id desc limit 10";
        //使用mysql解析
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);
        //解析select查询
        SQLSelectStatement sqlStatement = (SQLSelectStatement) sqlStatementParser.parseSelect();
        SQLSelect sqlSelect = sqlStatement.getSelect();
        //获取sql查询块
        SQLSelectQueryBlock sqlSelectQuery = (SQLSelectQueryBlock) sqlSelect.getQuery();

        StringBuffer out = new StringBuffer();
        //创建sql解析的标准化输出
        SQLASTOutputVisitor sqlastOutputVisitor = SQLUtils.createFormatOutputVisitor(out, null, JdbcUtils.MYSQL);

        //解析select项
//        out.delete(0, out.length());
        out.append("SELECT ");
        for (int i=0; i<sqlSelectQuery.getSelectList().size(); i++) {
            SQLSelectItem sqlSelectItem = sqlSelectQuery.getSelectList().get(i);
            if (i != 0) {
                out.append(",");
            }
            sqlSelectItem.accept(sqlastOutputVisitor);
        }
//        System.out.println("SELECT " + out);

        //解析from
//        out.delete(0, out.length());
        out.append(" FROM ");
        sqlSelectQuery.getFrom().accept(sqlastOutputVisitor);
//        System.out.println("FROM " + out);
        out.append(" ");

        //解析where
//        out.delete(0, out.length());
        if(sqlSelectQuery.getWhere() != null) {
            out.append(" WHERE ");
            sqlSelectQuery.getWhere().accept(sqlastOutputVisitor);
            out.append(" AND (A!='A' OR B!='B') ");
        } else {
            out.append(" WHERE (A!='A' OR B!='B') ");
        }

        //解析orderby
//        out.delete(0, out.length());
        out.append(" ");
        sqlSelectQuery.getOrderBy().accept(sqlastOutputVisitor);
//        System.out.println(out);

        //解析limit
//        out.delete(0, out.length());
        out.append(" ");
        sqlSelectQuery.getLimit().accept(sqlastOutputVisitor);

        System.out.println(out);

    }

}

