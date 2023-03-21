package top.moma.zoffy.support;

import java.net.InetAddress;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import top.moma.m64.core.exceptions.M64Exception;

@Slf4j
public class SerialNumberUtil {

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

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyyMMdd");

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

  private SerialNumberUtil() {}

  private static SerialNumberUtil serialNumberUtil;

  private static synchronized SerialNumberUtil getInstance() {
    if (serialNumberUtil == null) {
      serialNumberUtil = new SerialNumberUtil();
    }
    return serialNumberUtil;
  }

  public static String getSerialNumber() {
    String localDate = LocalDate.now().format(DATE_TIME_FORMATTER);
    return localDate + getInstance().nextId();
  }
}
