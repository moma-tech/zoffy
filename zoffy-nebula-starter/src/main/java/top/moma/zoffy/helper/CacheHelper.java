package top.moma.zoffy.helper;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CacheHelper {
  private static Cache<String, Object> localCache =
      Caffeine.newBuilder()
          .initialCapacity(1000)
          .maximumSize(500)
          .expireAfterWrite(20, TimeUnit.MINUTES)
          .recordStats()
          .build();

  private CacheHelper() {}

  public static Cache<String, Object> getCashier() {
    return localCache;
  }
}
