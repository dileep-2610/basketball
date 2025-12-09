/*     */ package org.jacoco.agent.rt.internal_b5a7c08.output;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.IExecutionDataVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ISessionInfoVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.IRemoteCommandVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.RemoteControlReader;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.RemoteControlWriter;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.RuntimeData;
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
/*     */ class TcpConnection
/*     */   implements IRemoteCommandVisitor
/*     */ {
/*     */   private final RuntimeData data;
/*     */   private final Socket socket;
/*     */   private RemoteControlWriter writer;
/*     */   private RemoteControlReader reader;
/*     */   private boolean initialized;
/*     */   
/*     */   public TcpConnection(Socket socket, RuntimeData data) {
/*  40 */     this.socket = socket;
/*  41 */     this.data = data;
/*  42 */     this.initialized = false;
/*     */   }
/*     */   
/*     */   public void init() throws IOException {
/*  46 */     this.writer = new RemoteControlWriter(this.socket.getOutputStream());
/*  47 */     this.reader = new RemoteControlReader(this.socket.getInputStream());
/*  48 */     this.reader.setRemoteCommandVisitor(this);
/*  49 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() throws IOException {
/*     */     try {
/*  60 */       while (this.reader.read());
/*     */     }
/*  62 */     catch (SocketException e) {
/*     */ 
/*     */       
/*  65 */       if (!this.socket.isClosed()) {
/*  66 */         throw e;
/*     */       }
/*     */     } finally {
/*  69 */       close();
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
/*     */   public void writeExecutionData(boolean reset) throws IOException {
/*  82 */     if (this.initialized && !this.socket.isClosed()) {
/*  83 */       visitDumpCommand(true, reset);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  93 */     if (!this.socket.isClosed()) {
/*  94 */       this.socket.close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitDumpCommand(boolean dump, boolean reset) throws IOException {
/* 102 */     if (dump) {
/* 103 */       this.data.collect((IExecutionDataVisitor)this.writer, (ISessionInfoVisitor)this.writer, reset);
/*     */     }
/* 105 */     else if (reset) {
/* 106 */       this.data.reset();
/*     */     } 
/*     */     
/* 109 */     this.writer.sendCmdOk();
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/output/TcpConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */