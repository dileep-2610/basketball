/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ public abstract class AbstractRuntime
/*    */   implements IRuntime
/*    */ {
/*    */   protected RuntimeData data;
/*    */   
/*    */   public void startup(RuntimeData data) throws Exception {
/* 29 */     this.data = data;
/*    */   }
/*    */   
/* 32 */   private static final Random RANDOM = new Random();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String createRandomId() {
/* 40 */     return Integer.toHexString(RANDOM.nextInt());
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/AbstractRuntime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */