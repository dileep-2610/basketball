/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ExecutionDataReader;
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
/*    */ public class RemoteControlReader
/*    */   extends ExecutionDataReader
/*    */ {
/*    */   private IRemoteCommandVisitor remoteCommandVisitor;
/*    */   
/*    */   public RemoteControlReader(InputStream input) throws IOException {
/* 36 */     super(input);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean readBlock(byte blockid) throws IOException {
/* 41 */     switch (blockid) {
/*    */       case 64:
/* 43 */         readDumpCommand();
/* 44 */         return true;
/*    */       case 32:
/* 46 */         return false;
/*    */     } 
/* 48 */     return super.readBlock(blockid);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRemoteCommandVisitor(IRemoteCommandVisitor visitor) {
/* 59 */     this.remoteCommandVisitor = visitor;
/*    */   }
/*    */   
/*    */   private void readDumpCommand() throws IOException {
/* 63 */     if (this.remoteCommandVisitor == null) {
/* 64 */       throw new IOException("No remote command visitor.");
/*    */     }
/* 66 */     boolean dump = this.in.readBoolean();
/* 67 */     boolean reset = this.in.readBoolean();
/* 68 */     this.remoteCommandVisitor.visitDumpCommand(dump, reset);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/RemoteControlReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */