package com.music;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class Generator {
    public static void main(String[] args) {
//        获取代码生成器的对象
        AutoGenerator autoGenerator = new AutoGenerator();
//        设置数据库相关配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/music?serverTimezone=UTC&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        autoGenerator.setDataSource(dataSource);
//        设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");//设置代码生成路径
        globalConfig.setOpen(false);//设置生成完毕后是否打开生成代码所在目录
        globalConfig.setAuthor("lsk");//设置作者
        globalConfig.setFileOverride(true);//设置是否覆盖原始生成的文件
        globalConfig.setMapperName("%sDao");//设置数据层接口名，%s为占位符，指代模块名称
        globalConfig.setIdType(IdType.ASSIGN_ID);//设置ID生成策略
        autoGenerator.setGlobalConfig(globalConfig);
//        设置包名相关配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.music");//设置生成的包名,与代码所在位置不冲突,二者叠加组成完整路径
        packageConfig.setEntity("domain");//设置实体类包名
        packageConfig.setMapper("dao");//设置数据层包名
        autoGenerator.setPackageInfo(packageConfig);
//        策略设置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("admin","singer","song","songlist","user");//设置当前参与生成的表名,参数为可变参数
//        strategyConfig.setTablePrefix("tbl_");//设置数据库表的前缀名称
        strategyConfig.setRestControllerStyle(true);//设置是否启用Rest风格
//        strategyConfig.setVersionFieldName("version");//设置乐观锁字段名
        strategyConfig.setLogicDeleteFieldName("deleted");//设置逻辑删除字段名
        strategyConfig.setEntityLombokModel(true);//设置是否启用Lombok
        autoGenerator.setStrategy(strategyConfig);
//        执行生成操作
        autoGenerator.execute();
    }
}
