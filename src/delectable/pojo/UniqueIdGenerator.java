package delectable.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdGenerator {
    static AtomicInteger atomicOrderInteger = new AtomicInteger(-1);
    static AtomicInteger atomicMenuInteger = new AtomicInteger(-1);
    static AtomicInteger atomicCustomerInteger = new AtomicInteger(-1);
    
    public static int getUniqueOrderID() {
        return atomicOrderInteger.incrementAndGet();
    }
    
    public static int getUniqueMenuID() {
        return atomicMenuInteger.incrementAndGet();
    }
    
    public static int getUniqueCustomerID() {
        return atomicCustomerInteger.incrementAndGet();
    }
}
