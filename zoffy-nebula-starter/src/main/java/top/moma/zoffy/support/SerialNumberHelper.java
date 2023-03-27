package top.moma.zoffy.support;

import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import top.moma.m64.core.exceptions.M64Exception;
import top.moma.m64.core.helper.date.DateTimeHelper;

/**
 * SerialNumberHelper
 *
 * <p>仿雪花序列号生成
 *
 * @version 1.0
 * @author Created by ivan at 18:14.
 */
@Slf4j
public class SerialNumberHelper {

  private static final long START_STAMP = 1675303377983L;
  private static final long SEQUENCE_BIT = 12;
  private static final long MACHINE_BIT = 8;
  private static final long DATA_CENTER_BIT = 2;
  private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
  private static final long MACHINE_LEFT = SEQUENCE_BIT;
  private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
  private static final long TIME_STAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;
  private static final long DATA_CENTER_ID = 1;
  private static final long MACHINE_ADDRESS;
  private long sequence = 0L;
  private long lastStamp = -1L;

  static {
    InetAddress localIp = IpHelper.getLocalIp();
    MACHINE_ADDRESS = localIp.getAddress()[3] & 0xff;
  }

  private synchronized long nextId() {
    long currStamp = getNewStamp();
    if (currStamp < lastStamp) {
      throw new M64Exception("Clock moved backwards.  Refusing to generate id");
    }
    if (currStamp == lastStamp) {
      sequence = (sequence + 1) & MAX_SEQUENCE;
      if (sequence == 0L) {
        currStamp = getNextMill();
      }
    } else {
      sequence = 0L;
    }
    lastStamp = currStamp;
    return (currStamp - START_STAMP) << TIME_STAMP_LEFT
        | DATA_CENTER_ID << DATA_CENTER_LEFT
        | MACHINE_ADDRESS << MACHINE_LEFT
        | sequence;
  }

  private long getNextMill() {
    long mill = getNewStamp();
    while (mill <= lastStamp) {
      mill = getNewStamp();
    }
    return mill;
  }

  private long getNewStamp() {
    return System.currentTimeMillis();
  }

  private SerialNumberHelper() {}

  private static SerialNumberHelper serialNumberHelper;

  private static synchronized SerialNumberHelper getInstance() {
    if (serialNumberHelper == null) {
      serialNumberHelper = new SerialNumberHelper();
    }
    return serialNumberHelper;
  }
  /**
   * getSerialNumber
   *
   * <p>入口方法
   *
   * <p>当前时间戳+STAMP+DATA_CENTER+MACHINE_ADDRESS+Sequence
   *
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/3/22 18:18
   */
  public static String getSerialNumber() {
    return DateTimeHelper.getDateTime8Length() + getInstance().nextId();
  }

  public static Long getId() {
    return getInstance().nextId();
  }
}
