package top.moma.zoffy.support;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * MapKeyComparator
 *
 * @version 1.0
 * @author Created by ivan at 18:14.
 */
public class MapKeyComparator implements Comparator<String> {

  @Override
  public int compare(String str1, String str2) {
    return str1.compareTo(str2);
  }

  /**
   * 按key进行排序 sortMapByKey
   *
   * @param map map
   * @return java.util.Map<java.lang.String,java.lang.String[]>
   * @author Created by ivan
   * @since 2023/3/22 15:51
   */
  public static Map<String, String[]> sortMapByKey(Map<String, String[]> map) {
    if (map == null || map.isEmpty()) {
      return Collections.emptyMap();
    }
    Map<String, String[]> orderedMap = new TreeMap<>(new MapKeyComparator());
    orderedMap.putAll(map);
    return orderedMap;
  }
}