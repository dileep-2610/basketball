/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.data;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProbeArrRefToExecutionDataMap
/*    */ {
/* 18 */   private static final HashMap<boolean[], ExecutionData> hashMap = (HashMap)new HashMap<>();
/*    */   
/* 20 */   static final Object lock = new Object();
/*    */   
/*    */   public static void set(boolean[] array, ExecutionData data) {
/* 23 */     synchronized (lock) {
/* 24 */       hashMap.put(array, data);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static ExecutionData get(boolean[] array) {
/* 29 */     synchronized (lock) {
/* 30 */       return hashMap.get(array);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/data/ProbeArrRefToExecutionDataMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */