/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ExecutionDataWriter;
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
/*    */ public class RemoteControlWriter
/*    */   extends ExecutionDataWriter
/*    */   implements IRemoteCommandVisitor
/*    */ {
/*    */   public static final byte BLOCK_CMDOK = 32;
/*    */   public static final byte BLOCK_CMDDUMP = 64;
/*    */   
/*    */   public RemoteControlWriter(OutputStream output) throws IOException {
/* 41 */     super(output);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendCmdOk() throws IOException {
/* 52 */     this.out.writeByte(32);
/*    */   }
/*    */ 
/*    */   
/*    */   public void visitDumpCommand(boolean dump, boolean reset) throws IOException {
/* 57 */     this.out.writeByte(64);
/* 58 */     this.out.writeBoolean(dump);
/* 59 */     this.out.writeBoolean(reset);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/RemoteControlWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */