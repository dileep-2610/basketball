/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ExecutionData;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ExecutionDataStore;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.IExecutionDataVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ISessionInfoVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.SessionInfo;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr.InstrSupport;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuntimeData
/*     */ {
/*  41 */   protected final ExecutionDataStore store = new ExecutionDataStore();
/*  42 */   private String sessionId = "<none>";
/*  43 */   private long startTimeStamp = System.currentTimeMillis();
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
/*     */   public void setSessionId(String id) {
/*  57 */     this.sessionId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSessionId() {
/*  67 */     return this.sessionId;
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
/*     */   public final void collect(IExecutionDataVisitor executionDataVisitor, ISessionInfoVisitor sessionInfoVisitor, boolean reset) {
/*  84 */     synchronized (this.store) {
/*     */       
/*  86 */       SessionInfo info = new SessionInfo(this.sessionId, this.startTimeStamp, System.currentTimeMillis());
/*  87 */       sessionInfoVisitor.visitSessionInfo(info);
/*  88 */       this.store.accept(executionDataVisitor);
/*  89 */       if (reset) {
/*  90 */         reset();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void reset() {
/*  99 */     synchronized (this.store) {
/* 100 */       this.store.reset();
/* 101 */       this.startTimeStamp = System.currentTimeMillis();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExecutionData getExecutionData(Long id, String name, int probecount) {
/* 120 */     synchronized (this.store) {
/* 121 */       return this.store.get(id, name, probecount);
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
/*     */   public void getProbes(Object[] args) {
/* 146 */     Long classid = (Long)args[0];
/* 147 */     String name = (String)args[1];
/* 148 */     int probecount = ((Integer)args[2]).intValue();
/* 149 */     args[0] = getExecutionData(classid, name, probecount).getProbes();
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
/*     */   public boolean equals(Object args) {
/* 162 */     if (args instanceof Object[]) {
/* 163 */       getProbes((Object[])args);
/*     */     }
/* 165 */     return super.equals(args);
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
/*     */ 
/*     */   
/*     */   public static void generateArgumentArray(long classid, String classname, int probecount, MethodVisitor mv) {
/* 185 */     mv.visitInsn(6);
/* 186 */     mv.visitTypeInsn(189, "java/lang/Object");
/*     */ 
/*     */     
/* 189 */     mv.visitInsn(89);
/* 190 */     mv.visitInsn(3);
/* 191 */     mv.visitLdcInsn(Long.valueOf(classid));
/* 192 */     mv.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
/*     */     
/* 194 */     mv.visitInsn(83);
/*     */ 
/*     */     
/* 197 */     mv.visitInsn(89);
/* 198 */     mv.visitInsn(4);
/* 199 */     mv.visitLdcInsn(classname);
/* 200 */     mv.visitInsn(83);
/*     */ 
/*     */     
/* 203 */     mv.visitInsn(89);
/* 204 */     mv.visitInsn(5);
/* 205 */     InstrSupport.push(mv, probecount);
/* 206 */     mv.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
/*     */     
/* 208 */     mv.visitInsn(83);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void generateAccessCall(long classid, String classname, int probecount, MethodVisitor mv) {
/* 232 */     generateArgumentArray(classid, classname, probecount, mv);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     mv.visitInsn(90);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     mv.visitMethodInsn(182, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
/*     */     
/* 245 */     mv.visitInsn(87);
/*     */ 
/*     */ 
/*     */     
/* 249 */     mv.visitInsn(3);
/* 250 */     mv.visitInsn(50);
/*     */ 
/*     */ 
/*     */     
/* 254 */     mv.visitTypeInsn(192, "[Z");
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/RuntimeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */