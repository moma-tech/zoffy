package top.moma.zoffy.table.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class MysqlGenerator {

  /** 数据源配置 */
  private static final DataSourceConfig DATA_SOURCE_CONFIG =
      new DataSourceConfig.Builder(
              "jdbc:mysql://155.138.159.168:3306/moma?serverTimezone=Asia/Shanghai",
              "ivan",
              "1qaw3@123")
          .schema("moma")
          .build();

  public void testSimple() {
    AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
    generator.strategy(new StrategyConfig.Builder().build());
    generator.global(new GlobalConfig.Builder().build());
    generator.execute();
  }

  public static void main(String[] args) {
    MysqlGenerator mysqlGenerator = new MysqlGenerator();
    mysqlGenerator.testSimple();
  }
}
