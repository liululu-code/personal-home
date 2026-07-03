//package top.lll44556.codeGenerator.cteateCodeUtils;//package com.dscomm.ems.ychz.utils.cteateCodeUtils;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.StringWriter;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Component
//public class CreateCodeUtil {
//    private static Configuration configuration;
//    private static String path;
//
//    static {
//        configuration = new Configuration(Configuration.VERSION_2_3_0);
//        configuration.setDefaultEncoding("UTF-8");
//        path = CreateCodeUtil.class.getResource("").getPath();
//        path = path.substring(1,path.indexOf("com"));
//    }
//
//    public static void main(String[] args) {
//        try {
//
//            String path = "D:\\codeGenerator";
//            // 基础数据
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//            Map<String, Object> baseData = new HashMap<>();
//            baseData.put("author","lll");//作者
//            baseData.put("packageName","com.dscomm.ems.ychz");//路径
//            baseData.put("UpperPojoName","ExpertJoinRecord");//不加entity 大写
//            baseData.put("LowerPojoName","expertJoinRecord");//不加entity 小写
//            baseData.put("tableName","t_hz_zjrhjl");//表名
//            baseData.put("date",sdf.format(new Date(System.currentTimeMillis())));
//            baseData.put("name","专家入会记录");
//
//            List<FieldForCreateUtil> list = new ArrayList<>();
//            //大写属性  小写属性 字段名称 字段说明
//            list.add(new FieldForCreateUtil("ConsultationId","consultationId","hzid","会诊id"));
//
//            baseData.put("attribute",list);
//
//            // 创建基础的entity相关类
//            baseData.put("fileName",path+"/dal/entity/"+baseData.get("UpperPojoName")+"Entity.java");
//            createCodeFromTemple("entityTemple.ftl",baseData);
////
////            // 创建基础的 queryInputInfo 相关类
////            baseData.put("fileName",path+"/web/vo/req/"+baseData.get("UpperPojoName")+"QueryReqVO.java");
////            createCodeFromTemple("queryInputInfoTemple.ftl",baseData);
////
////            // 创建基础的 saveInputInfo 相关类
////            baseData.put("fileName",path+"/web/vo/req/"+baseData.get("UpperPojoName")+"SaveReqVO.java");
////            createCodeFromTemple("saveInputInfoTemple.ftl",baseData);
////
////            // 创建基础的 vo 相关类
////            baseData.put("fileName",path+"/web/vo/res/"+baseData.get("UpperPojoName")+"ResVO.java");
////            createCodeFromTemple("resVOTemple.ftl",baseData);
////
////            // 创建基础的 bean 相关类
////            baseData.put("fileName",path+"/service/bean/"+baseData.get("UpperPojoName")+"Bean.java");
////            createCodeFromTemple("BeanTemple.ftl",baseData);
////
////            // 创建基础的 convert 相关类
////            baseData.put("fileName",path+"/converts/"+baseData.get("UpperPojoName")+"Convert.java");
////            createCodeFromTemple("Convert.ftl",baseData);
////
////            // 创建基础的 controller 相关类
////            baseData.put("fileName",path+"/web/controller/"+baseData.get("UpperPojoName")+"Controller.java");
////            createCodeFromTemple("controllerTemple.ftl",baseData);
////
////            // 创建service 的类
////            baseData.put("fileName",path+"/service/"+baseData.get("UpperPojoName")+"Service.java");
////            createCodeFromTemple("serviceTemple.ftl",baseData);
////
////            // 创建serviceImpl 的类
////            baseData.put("fileName",path+"/service/impl/"+baseData.get("UpperPojoName")+"ServiceImpl.java");
////            createCodeFromTemple("serviceImplTemple.ftl",baseData);
////
////            // 创建 Repository 的类
////            baseData.put("fileName",path+"/dal/repository/"+baseData.get("UpperPojoName")+"Repository.java");
////            createCodeFromTemple("repositoryTemple.ftl",baseData);
////
////            // 创建 query 的类
////            baseData.put("fileName",path+"/service/nativequery/"+baseData.get("UpperPojoName")+"NativeQuery.java");
////            createCodeFromTemple("queryTemple.ftl",baseData);
////
////            //创建 querySQL 的类
////            baseData.put("fileName",path+"/service/nativequery/"+baseData.get("UpperPojoName")+"NativeQueryPostgreSQL.java");
////            createCodeFromTemple("querySQLTemple.ftl",baseData);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    private static void createCodeFromTemple(String templeName,Map<String,Object> data) throws Exception {
//        // 1. 创建FreeMarker配置
//        File file = new File(path+"/template");
//        configuration.setDirectoryForTemplateLoading(file);
//
//        // 2. 加载模板
//        Template template = configuration.getTemplate(templeName);
//        // 4. 执行模板
//        StringWriter writer = new StringWriter();
//        template.process(data, writer);
//
//        // 5、将结果写入文件
//        File outFile = new File(data.get("fileName").toString());
//        if (!outFile.exists()){
//            outFile.createNewFile();
//        }
//        FileWriter fileWriter = new FileWriter(data.get("fileName").toString());
//        template.process(data, fileWriter);
//        fileWriter.flush();
//        fileWriter.close();
//    }
//
//}
