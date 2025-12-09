/*    */ package org.jacoco.agent.rt.internal_b5a7c08;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.AgentOptions;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.RuntimeData;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Offline
/*    */ {
/*    */   private static final String CONFIG_RESOURCE = "/jacoco-agent.properties";
/*    */   private static RuntimeData data;
/*    */   
/*    */   private static synchronized RuntimeData getRuntimeData() {
/* 35 */     if (data == null) {
/* 36 */       Properties config = ConfigLoader.load("/jacoco-agent.properties", 
/* 37 */           System.getProperties());
/*    */       try {
/* 39 */         data = Agent.getInstance(new AgentOptions(config)).getData();
/* 40 */       } catch (Exception e) {
/* 41 */         throw new RuntimeException("Failed to initialize JaCoCo.", e);
/*    */       } 
/*    */     } 
/* 44 */     return data;
/*    */   }
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
/*    */   public static boolean[] getProbes(long classid, String classname, int probecount) {
/* 60 */     return getRuntimeData()
/* 61 */       .getExecutionData(Long.valueOf(classid), classname, probecount)
/* 62 */       .getProbes();
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/Offline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */