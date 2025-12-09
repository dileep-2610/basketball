/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.data;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ public class IncompatibleExecDataVersionException
/*    */   extends IOException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final int actualVersion;
/*    */   
/*    */   public IncompatibleExecDataVersionException(int actualVersion) {
/* 36 */     super(String.format("Cannot read execution data version 0x%x. This version of JaCoCo uses execution data version 0x%x.", new Object[] {
/*    */             
/* 38 */             Integer.valueOf(actualVersion), 
/* 39 */             Integer.valueOf(ExecutionDataWriter.FORMAT_VERSION) }));
/* 40 */     this.actualVersion = actualVersion;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExpectedVersion() {
/* 50 */     return ExecutionDataWriter.FORMAT_VERSION;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getActualVersion() {
/* 59 */     return this.actualVersion;
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/data/IncompatibleExecDataVersionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */