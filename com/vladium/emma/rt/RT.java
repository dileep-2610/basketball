/*    */ package com.vladium.emma.rt;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ @Deprecated
/*    */ public final class RT
/*    */ {
/*    */   public static void dumpCoverageData(File outFile, boolean merge, boolean stopDataCollection) throws IOException {
/* 50 */     OutputStream out = new FileOutputStream(outFile, merge);
/*    */     try {
/* 52 */       out.write(
/* 53 */           org.jacoco.agent.rt.RT.getAgent().getExecutionData(false));
/*    */     } finally {
/* 55 */       out.close();
/*    */     } 
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
/*    */   public static synchronized void dumpCoverageData(File outFile, boolean stopDataCollection) throws IOException {
/* 72 */     dumpCoverageData(outFile, true, stopDataCollection);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/com/vladium/emma/rt/RT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */