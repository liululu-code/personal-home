package top.lll44556.codeGenerator;

import org.junit.jupiter.api.Test;
import top.lll44556.codeGenerator.enums.SqlTypeCategory;
import top.lll44556.codeGenerator.parser.PostgresqlTypeClassifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostgresqlTypeClassifierTest {

    private final PostgresqlTypeClassifier classifier = new PostgresqlTypeClassifier();

    @Test
    void classifyTextTypes() {
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("varchar"));
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("varchar (32)"));
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("varchar(32)"));
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("VARCHAR"));
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("text"));
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("char"));
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("character"));
        assertEquals(SqlTypeCategory.TEXT, classifier.classify("character varying"));
    }

    @Test
    void classifyIntegerTypes() {
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("int"));
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("int2"));
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("int4"));
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("int8"));
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("integer"));
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("smallint"));
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("bigint"));
        assertEquals(SqlTypeCategory.INTEGER, classifier.classify("BIGINT"));
    }

    @Test
    void classifyBooleanTypes() {
        assertEquals(SqlTypeCategory.BOOLEAN, classifier.classify("bool"));
        assertEquals(SqlTypeCategory.BOOLEAN, classifier.classify("boolean"));
    }

    @Test
    void classifyUnknownTypes() {
        assertEquals(SqlTypeCategory.UNKNOWN, classifier.classify("numeric"));
        assertEquals(SqlTypeCategory.UNKNOWN, classifier.classify("jsonb"));
        assertEquals(SqlTypeCategory.UNKNOWN, classifier.classify(""));
        assertEquals(SqlTypeCategory.UNKNOWN, classifier.classify(null));
    }
}
