/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.data.CompactDataInput;
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
/*     */ public class ExecutionDataReader
/*     */ {
/*     */   protected final CompactDataInput in;
/*  30 */   private ISessionInfoVisitor sessionInfoVisitor = null;
/*     */   
/*  32 */   private IExecutionDataVisitor executionDataVisitor = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean firstBlock = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExecutionDataReader(InputStream input) {
/*  45 */     this.in = new CompactDataInput(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSessionInfoVisitor(ISessionInfoVisitor visitor) {
/*  55 */     this.sessionInfoVisitor = visitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExecutionDataVisitor(IExecutionDataVisitor visitor) {
/*  65 */     this.executionDataVisitor = visitor;
/*     */   }
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
/*     */   public boolean read() throws IOException, IncompatibleExecDataVersionException {
/*     */     while (true) {
/*  84 */       int i = this.in.read();
/*  85 */       if (i == -1) {
/*  86 */         return false;
/*     */       }
/*  88 */       byte type = (byte)i;
/*  89 */       if (this.firstBlock && type != 1) {
/*  90 */         throw new IOException("Invalid execution data file.");
/*     */       }
/*  92 */       this.firstBlock = false;
/*  93 */       if (!readBlock(type)) {
/*  94 */         return true;
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
/*     */   
/*     */   protected boolean readBlock(byte blocktype) throws IOException {
/* 108 */     switch (blocktype) {
/*     */       case 1:
/* 110 */         readHeader();
/* 111 */         return true;
/*     */       case 16:
/* 113 */         readSessionInfo();
/* 114 */         return true;
/*     */       case 17:
/* 116 */         readExecutionData();
/* 117 */         return true;
/*     */     } 
/* 119 */     throw new IOException(
/* 120 */         String.format("Unknown block type %x.", new Object[] { Byte.valueOf(blocktype) }));
/*     */   }
/*     */ 
/*     */   
/*     */   private void readHeader() throws IOException {
/* 125 */     if (this.in.readChar() != '샀') {
/* 126 */       throw new IOException("Invalid execution data file.");
/*     */     }
/* 128 */     char version = this.in.readChar();
/* 129 */     if (version != ExecutionDataWriter.FORMAT_VERSION) {
/* 130 */       throw new IncompatibleExecDataVersionException(version);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readSessionInfo() throws IOException {
/* 135 */     if (this.sessionInfoVisitor == null) {
/* 136 */       throw new IOException("No session info visitor.");
/*     */     }
/* 138 */     String id = this.in.readUTF();
/* 139 */     long start = this.in.readLong();
/* 140 */     long dump = this.in.readLong();
/* 141 */     this.sessionInfoVisitor.visitSessionInfo(new SessionInfo(id, start, dump));
/*     */   }
/*     */   
/*     */   private void readExecutionData() throws IOException {
/* 145 */     if (this.executionDataVisitor == null) {
/* 146 */       throw new IOException("No execution data visitor.");
/*     */     }
/* 148 */     long id = this.in.readLong();
/* 149 */     String name = this.in.readUTF();
/* 150 */     boolean[] probes = this.in.readBooleanArray();
/* 151 */     this.executionDataVisitor
/* 152 */       .visitClassExecution(new ExecutionData(id, name, probes));
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/data/ExecutionDataReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */