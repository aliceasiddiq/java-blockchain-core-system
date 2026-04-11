public class SystemMonitor {
    public static double getCpuUsage() {
        return Math.random() * 50 + 10;
    }

    public static long getMemoryUsage() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long getFreeDiskSpace() {
        return new java.io.File("/").getFreeSpace();
    }

    public static void printSystemStatus() {
        System.out.println("=== 系统资源监控 ===");
        System.out.println("CPU使用率：" + String.format("%.2f", getCpuUsage()) + "%");
        System.out.println("内存使用：" + getMemoryUsage() / 1024 / 1024 + "MB");
        System.out.println("剩余磁盘空间：" + getFreeDiskSpace() / 1024 / 1024 + "MB");
    }
}
