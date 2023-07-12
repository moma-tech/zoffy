package top.moma.zoffy.rabc.infrastrcuture.helper.tenant;

public class TenantHolder {
  private static ThreadLocal<Long> currentTenant = new InheritableThreadLocal<>();

  public static Long getCurrentTenant() {
    return currentTenant.get();
  }

  public static void setCurrentTenant(Long tenantId) {
    currentTenant.set(tenantId);
  }

  public static void clear() {
    currentTenant.set(null);
  }
}
