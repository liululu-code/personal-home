package top.lll44556.codeGenerator.cteateCodeUtils;

import lombok.Data;

@Data
public class FieldForCreateUtil {
    private String upperFieldName;
    private String lowerFieldName;
    private String columnName;
    private String explain;

    public FieldForCreateUtil() {
    }

    /**
     * 创建模板字段生成集合
     * @param upperFieldName  首字母大写属性名
     * @param lowerFieldName  首字母小写属性名
     * @param columnName    数据库列名
     * @param explain 说明
     */
    public FieldForCreateUtil(String upperFieldName,String lowerFieldName,String columnName,String explain){
        this.upperFieldName = upperFieldName;
        this.lowerFieldName = lowerFieldName;
        this.columnName = columnName;
        this.explain =explain;
    }

}
