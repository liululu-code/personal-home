-- 根据建表 SQL 自动生成的 PostgreSQL 假数据插入语句
-- inputType: ${inputType}
-- databaseType: ${databaseType}
-- outputType: ${outputType}

INSERT INTO ${tableMeta.fullTableName} (
<#list tableMeta.columns as column>
    ${column.quotedColumnName}<#sep>,
</#list>
)
SELECT
<#list tableMeta.columns as column>
    ${column.insertValueExpression} AS ${column.quotedColumnName}<#sep>,
</#list>
FROM generate_series(1, ${rowCount}) AS gs;
