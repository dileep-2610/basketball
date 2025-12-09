/*    */ package org.jacoco.agent.rt.internal_b5a7c08;
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
/*    */ public interface IExceptionLogger
/*    */ {
/* 24 */   public static final IExceptionLogger SYSTEM_ERR = new IExceptionLogger() {
/*    */       public void logExeption(Exception ex) {
/* 26 */         ex.printStackTrace();
/*    */       }
/*    */     };
/*    */   
/*    */   void logExeption(Exception paramException);
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/IExceptionLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */