package top.moma.zoffy;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MbGenerator
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 5:30 PM.
 */
@SuppressWarnings("AlibabaMethodTooLong")
public class MbGenerator {
  public static void main(String[] args) {
    MbGenerator.generator();
  }
  /** MySQL generator */
  public static void generator() {

    String tableName = "auth_rel_role_resource";
    String packageName = "auth.rel";

    String outputDir ="/home/ivan/github-git/zoffy/src/main/java/";
    String outputXml ="/home/ivan/github-git/zoffy/src/main/resources/mapper/";

    String baseModel="top.moma.m78.framework.common.model.entity.SuperModel";
    String baseDao="top.moma.m78.framework.common.dao.SuperDao";
    String baseController="top.moma.m78.framework.common.controller.SuperController";
    String baseServiceImpl="top.moma.m78.framework.common.service.impl.SuperServiceImpl";
    String baseService="top.moma.m78.framework.common.service.SuperService";

    String packagePath = "top.moma.zoffy.beam.";

    // ??????????????????????????????
    List<TableFill> tableFillList = new ArrayList<>();
    tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
    tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));

    // ???????????????
    AutoGenerator mpg =
        new AutoGenerator()
            .setGlobalConfig(
                // ????????????
                new GlobalConfig()
                    // ????????????
                    .setOutputDir(outputDir)
                    // ??????????????????
                    .setFileOverride(true)
                    // ?????? activeRecord ??????
                    .setActiveRecord(false)
                    // XML ????????????
                    .setEnableCache(false)
                    // XML ResultMap
                    .setBaseResultMap(false)
                    // XML columList
                    .setBaseColumnList(false)
                    // ???????????? kotlin ??????
                    .setKotlin(false)
                    // ??????
                    .setAuthor("Ivan")
                    // ?????????????????????????????? %s ?????????????????????????????????
                    .setEntityName("%s")
                    .setMapperName("%sDao")
                    .setXmlName("%s")
                    .setServiceName("%sService")
                    .setServiceImplName("%sServiceImpl")
                    .setControllerName("%sController"))
            .setDataSource(
                // ???????????????
                new DataSourceConfig()
                    // ???????????????
                    .setDbType(DbType.MYSQL)
                    .setTypeConvert(
                        new MySqlTypeConvert() {
                          @Override
                          public IColumnType processTypeConvert(
                              GlobalConfig globalConfig, String fieldType) {
                            if (fieldType.toLowerCase().contains("bit")) {
                              return DbColumnType.BOOLEAN;
                            }
                            if (fieldType.toLowerCase().contains("tinyint")) {
                              return DbColumnType.BOOLEAN;
                            }
                            if (fieldType.toLowerCase().contains("date")) {
                              return DbColumnType.LOCAL_DATE;
                            }
                            if (fieldType.toLowerCase().contains("time")) {
                              return DbColumnType.LOCAL_TIME;
                            }
                            if (fieldType.toLowerCase().contains("datetime")) {
                              return DbColumnType.LOCAL_DATE_TIME;
                            }
                            return super.processTypeConvert(globalConfig, fieldType);
                          }
                        })
                    .setDriverName("com.mysql.cj.jdbc.Driver")
                    .setUsername("root")
                    .setPassword("Ivanna83@")
                    .setUrl("jdbc:mysql://127.0.0.1:3306/open-api?characterEncoding=utf8"))
            .setStrategy(
                // ????????????
                new StrategyConfig()
                    // ??????????????????
                    .setCapitalMode(false)
                    // ????????????
                    .setTablePrefix("api_")
                    // ??????????????????
                    .setNaming(NamingStrategy.underline_to_camel)
                    // ??????????????????
                    .setInclude(new String[] {tableName})
                    // ?????????????????????
                    .setSuperEntityClass(baseModel)
                    // ??????????????????????????????
                    // .setSuperEntityColumns("id")
                    .setTableFillList(tableFillList)
                    // ????????? mapper ??????
                    .setSuperMapperClass(baseDao)
                    // ????????? controller ??????
                    .setSuperControllerClass(baseController)
                    // ????????? service ???????????????
                    .setSuperServiceImplClass(baseServiceImpl)
                    // ????????? service ????????????
                    .setSuperServiceClass(baseService)
                    // ????????????????????????????????????????????? false???
                    .setEntityColumnConstant(true)
                    // ????????????????????????????????????????????? false???
                    .setEntityBuilderModel(true)
                    // ?????????????????????lombok??????????????? false???<a href="https://projectlombok.org/">document</a>
                    .setEntityLombokModel(true)
                    // Boolean????????????????????????is????????????
                    .setEntityBooleanColumnRemoveIsPrefix(true)
                    .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
                )
            .setPackageInfo(
                // ?????????
                new PackageConfig()
                    .setParent(packagePath + packageName)
                    .setController("controller")
                    .setEntity("model.domain")
                    .setMapper("dao")
                    .setService("service")
                    .setServiceImpl("service.impl"))
            .setCfg(
                // ????????????????????????????????? VM ????????? cfg.abc ????????????
                new InjectionConfig() {
                  @Override
                  public void initMap() {
                    Map<String, Object> map = new HashMap<>(16);
                    this.setMap(map);
                  }
                }.setFileOutConfigList(
                    Collections.<FileOutConfig>singletonList(
                        new FileOutConfig("/templates/mapper.xml.vm") {
                          // ???????????????????????????
                          @Override
                          public String outputFile(TableInfo tableInfo) {
                            return outputXml
                                + tableInfo.getEntityName()
                                + ".xml";
                          }
                        })))
            .setTemplate(
                // ???????????? xml ????????????????????? ??? ?????????
                new TemplateConfig().setXml(null)
                /* ???????????????????????????????????????????????? /mybatis-plus/src/main/resources/template ?????? copy
                ???????????? src/main/resources/template ??????????????????????????????????????????????????????
                .setController("...");
                .setEntity("...");
                .setMapper("...");
                .setXml("...");
                .setService("...");
                .setServiceImpl("...");*/
                );
    mpg.execute();
  }
}
