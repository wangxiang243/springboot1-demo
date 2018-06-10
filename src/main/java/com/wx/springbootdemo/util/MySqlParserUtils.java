package com.wx.springbootdemo.util;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcUtils;

public class MySqlParserUtils {

    public static String parseSelectSqlStatementAndAddWhereCondition(String sql, String condition) {

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
        out.append(" SELECT ");
        SQLSelectItem sqlSelectItem;
        for (int i=0; i<sqlSelectQuery.getSelectList().size(); i++) {
            sqlSelectItem = sqlSelectQuery.getSelectList().get(i);
            if (i != 0) {
                out.append(",");
            }
            sqlSelectItem.accept(sqlastOutputVisitor);
        }
        //解析from
        out.append(" FROM ");
        sqlSelectQuery.getFrom().accept(sqlastOutputVisitor);
        out.append(" ");

        //解析where
        if(sqlSelectQuery.getWhere() != null) {
            out.append(" WHERE ");
            sqlSelectQuery.getWhere().accept(sqlastOutputVisitor);
            out.append(" AND " + condition);
        } else {
            out.append(" WHERE " + condition);
        }

        //解析orderby
        out.append(" ");
        if(sqlSelectQuery.getOrderBy() != null) {
            sqlSelectQuery.getOrderBy().accept(sqlastOutputVisitor);
        }

        //解析limit
        out.append(" ");
        if(sqlSelectQuery.getLimit() != null) {
            sqlSelectQuery.getLimit().accept(sqlastOutputVisitor);
        }

        return out.toString();
    }

}
