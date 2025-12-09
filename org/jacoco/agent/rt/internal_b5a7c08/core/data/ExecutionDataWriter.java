/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.data;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.data.CompactDataOutput;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExecutionDataWriter
/*     */   implements ISessionInfoVisitor, IExecutionDataVisitor
/*     */ {
/*  34 */   public static final char FORMAT_VERSION = 'ဇ';
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final char MAGIC_NUMBER = '샀';
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte BLOCK_HEADER = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte BLOCK_SESSIONINFO = 16;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final byte BLOCK_EXECUTIONDATA = 17;
/*     */ 
/*     */ 
/*     */   
/*     */   protected final CompactDataOutput out;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExecutionDataWriter(OutputStream output) throws IOException {
/*  63 */     this.out = new CompactDataOutput(output);
/*  64 */     writeHeader();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeHeader() throws IOException {
/*  74 */     this.out.writeByte(1);
/*  75 */     this.out.writeChar(49344);
/*  76 */     this.out.writeChar(FORMAT_VERSION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/*  86 */     this.out.flush();
/*     */   }
/*     */   
/*     */   public void visitSessionInfo(SessionInfo info) {
/*     */     try {
/*  91 */       this.out.writeByte(16);
/*  92 */       this.out.writeUTF(info.getId());
/*  93 */       this.out.writeLong(info.getStartTimeStamp());
/*  94 */       this.out.writeLong(info.getDumpTimeStamp());
/*  95 */     } catch (IOException e) {
/*  96 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visitClassExecution(ExecutionData data) {
/* 101 */     if (data.hasHits()) {
/*     */       try {
/* 103 */         this.out.writeByte(17);
/* 104 */         this.out.writeLong(data.getId());
/* 105 */         this.out.writeUTF(data.getName());
/* 106 */         this.out.writeBooleanArray(data.getProbes());
/* 107 */       } catch (IOException e) {
/* 108 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getFileHeader() {
/* 121 */     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/*     */     try {
/* 123 */       new ExecutionDataWriter(buffer);
/* 124 */     } catch (IOException e) {
/*     */       
/* 126 */       throw new AssertionError(e);
/*     */     } 
/* 128 */     return buffer.toByteArray();
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/data/ExecutionDataWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */