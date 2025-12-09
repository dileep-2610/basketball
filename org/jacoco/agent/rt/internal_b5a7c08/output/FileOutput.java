/*    */ package org.jacoco.agent.rt.internal_b5a7c08.output;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InterruptedIOException;
/*    */ import java.io.OutputStream;
/*    */ import java.nio.channels.FileChannel;
/*    */ import java.nio.channels.OverlappingFileLockException;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ExecutionDataWriter;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.IExecutionDataVisitor;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ISessionInfoVisitor;
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
/*    */ 
/*    */ public class FileOutput
/*    */   implements IAgentOutput
/*    */ {
/*    */   private static final int LOCK_RETRY_COUNT = 30;
/*    */   private static final long LOCK_RETRY_WAIT_TIME_MS = 100L;
/*    */   private RuntimeData data;
/*    */   private File destFile;
/*    */   private boolean append;
/*    */   
/*    */   public final void startup(AgentOptions options, RuntimeData data) throws IOException {
/* 49 */     this.data = data;
/* 50 */     this.destFile = (new File(options.getDestfile())).getAbsoluteFile();
/* 51 */     this.append = options.getAppend();
/* 52 */     File folder = this.destFile.getParentFile();
/* 53 */     if (folder != null) {
/* 54 */       folder.mkdirs();
/*    */     }
/*    */     
/* 57 */     openFile().close();
/*    */   }
/*    */   
/*    */   public void writeExecutionData(boolean reset) throws IOException {
/* 61 */     OutputStream output = openFile();
/*    */     try {
/* 63 */       ExecutionDataWriter writer = new ExecutionDataWriter(output);
/* 64 */       this.data.collect((IExecutionDataVisitor)writer, (ISessionInfoVisitor)writer, reset);
/*    */     } finally {
/* 66 */       output.close();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void shutdown() throws IOException {}
/*    */ 
/*    */   
/*    */   private OutputStream openFile() throws IOException {
/* 75 */     FileOutputStream file = new FileOutputStream(this.destFile, this.append);
/*    */     
/* 77 */     FileChannel fc = file.getChannel();
/* 78 */     int retries = 0;
/*    */ 
/*    */     
/*    */     while (true) {
/*    */       try {
/* 83 */         fc.lock();
/* 84 */         return file;
/* 85 */       } catch (OverlappingFileLockException e) {
/*    */ 
/*    */ 
/*    */         
/* 89 */         if (retries++ > 30) {
/* 90 */           throw e;
/*    */         }
/*    */         
/*    */         try {
/* 94 */           Thread.sleep(100L);
/* 95 */         } catch (InterruptedException interruptedException) {
/* 96 */           throw new InterruptedIOException();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/output/FileOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */