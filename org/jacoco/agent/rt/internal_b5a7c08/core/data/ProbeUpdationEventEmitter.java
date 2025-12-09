/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.data;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProbeUpdationEventEmitter
/*    */ {
/* 21 */   static ProbeUpdateListener probeUpdateListener = null;
/*    */   
/*    */   public static void updateProbe(boolean[] probes, int probeId) {
/* 24 */     if (probeUpdateListener != null) {
/*    */       
/* 26 */       ExecutionData executionData = ProbeArrRefToExecutionDataMap.get(probes);
/* 27 */       long classId = executionData.getId();
/* 28 */       String className = executionData.getName();
/*    */       
/* 30 */       probeUpdateListener.listener(classId, probeId, className, probes);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void addListener(ProbeUpdateListener probeUpdateListener) {
/* 35 */     ProbeUpdationEventEmitter.probeUpdateListener = probeUpdateListener;
/* 36 */     System.out.println("Successfully added probe updation listener!");
/*    */   }
/*    */   
/*    */   public static void noopFn() {}
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/data/ProbeUpdationEventEmitter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */