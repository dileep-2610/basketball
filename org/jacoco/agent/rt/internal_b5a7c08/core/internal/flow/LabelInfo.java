/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.analysis.Instruction;
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
/*     */ public final class LabelInfo
/*     */ {
/*     */   public static final int NO_PROBE = -1;
/*     */   private boolean target = false;
/*     */   private boolean multiTarget = false;
/*     */   private boolean successor = false;
/*     */   private boolean methodInvocationLine = false;
/*     */   private boolean done = false;
/*  40 */   private int probeid = -1;
/*     */   
/*  42 */   private Label intermediate = null;
/*     */   
/*  44 */   private Instruction instruction = null;
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
/*     */   public static void setTarget(Label label) {
/*  57 */     LabelInfo info = create(label);
/*  58 */     if (info.target || info.successor) {
/*  59 */       info.multiTarget = true;
/*     */     } else {
/*  61 */       info.target = true;
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
/*     */   public static void setSuccessor(Label label) {
/*  73 */     LabelInfo info = create(label);
/*  74 */     info.successor = true;
/*  75 */     if (info.target) {
/*  76 */       info.multiTarget = true;
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
/*     */   public static boolean isMultiTarget(Label label) {
/*  92 */     LabelInfo info = get(label);
/*  93 */     return (info == null) ? false : info.multiTarget;
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
/*     */   public static boolean isSuccessor(Label label) {
/* 107 */     LabelInfo info = get(label);
/* 108 */     return (info == null) ? false : info.successor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setMethodInvocationLine(Label label) {
/* 118 */     (create(label)).methodInvocationLine = true;
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
/*     */   public static boolean isMethodInvocationLine(Label label) {
/* 131 */     LabelInfo info = get(label);
/* 132 */     return (info == null) ? false : info.methodInvocationLine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean needsProbe(Label label) {
/* 143 */     LabelInfo info = get(label);
/* 144 */     return (info != null && info.successor && (info.multiTarget || info.methodInvocationLine));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setDone(Label label) {
/* 155 */     (create(label)).done = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetDone(Label label) {
/* 165 */     LabelInfo info = get(label);
/* 166 */     if (info != null) {
/* 167 */       info.done = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetDone(Label[] labels) {
/* 178 */     for (Label label : labels) {
/* 179 */       resetDone(label);
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
/*     */   public static boolean isDone(Label label) {
/* 191 */     LabelInfo info = get(label);
/* 192 */     return (info == null) ? false : info.done;
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
/*     */   public static void setProbeId(Label label, int id) {
/* 204 */     (create(label)).probeid = id;
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
/*     */   public static int getProbeId(Label label) {
/* 216 */     LabelInfo info = get(label);
/* 217 */     return (info == null) ? -1 : info.probeid;
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
/*     */   public static void setIntermediateLabel(Label label, Label intermediate) {
/* 231 */     (create(label)).intermediate = intermediate;
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
/*     */   public static Label getIntermediateLabel(Label label) {
/* 243 */     LabelInfo info = get(label);
/* 244 */     return (info == null) ? null : info.intermediate;
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
/*     */   public static void setInstruction(Label label, Instruction instruction) {
/* 257 */     (create(label)).instruction = instruction;
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
/*     */   public static Instruction getInstruction(Label label) {
/* 269 */     LabelInfo info = get(label);
/* 270 */     return (info == null) ? null : info.instruction;
/*     */   }
/*     */   
/*     */   private static LabelInfo get(Label label) {
/* 274 */     Object info = label.info;
/* 275 */     return (info instanceof LabelInfo) ? (LabelInfo)info : null;
/*     */   }
/*     */   
/*     */   private static LabelInfo create(Label label) {
/* 279 */     LabelInfo info = get(label);
/* 280 */     if (info == null) {
/* 281 */       info = new LabelInfo();
/* 282 */       label.info = info;
/*     */     } 
/* 284 */     return info;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/flow/LabelInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */