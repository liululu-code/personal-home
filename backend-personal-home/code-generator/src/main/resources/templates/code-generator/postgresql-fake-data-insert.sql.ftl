-- TODO: 根据建表 SQL 解析表名、字段、字段类型、主键和默认值。
-- TODO: 根据字段元数据生成 PostgreSQL 假数据 INSERT SQL。
-- TODO: 对 PostgreSQL 可以使用 generate_series() 批量生成测试数据。
-- inputType: ${inputType}
-- databaseType: ${databaseType}
-- outputType: ${outputType}

/*
原始输入:
${inputContent}
*/

-- TODO 示例占位，后续替换为真实表名和字段列表。
INSERT INTO public.todo_table (
    todo_column
)
SELECT
    'TODO-' || gs::text AS todo_column
FROM generate_series(1, 10) AS gs;
