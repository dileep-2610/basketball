/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Handle;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.MethodNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.TryCatchBlockNode;
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
/*     */ public final class LabelFlowAnalyzer
/*     */   extends MethodVisitor
/*     */ {
/*     */   public static void markLabels(MethodNode method) {
/*  38 */     MethodVisitor lfa = new LabelFlowAnalyzer();
/*  39 */     for (int i = method.tryCatchBlocks.size(); --i >= 0;) {
/*  40 */       ((TryCatchBlockNode)method.tryCatchBlocks.get(i)).accept(lfa);
/*     */     }
/*  42 */     method.instructions.accept(lfa);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean successor = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean first = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   Label lineStart = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LabelFlowAnalyzer() {
/*  66 */     super(589824);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/*  76 */     LabelInfo.setTarget(start);
/*     */ 
/*     */     
/*  79 */     LabelInfo.setTarget(handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/*  84 */     LabelInfo.setTarget(label);
/*  85 */     if (opcode == 168) {
/*  86 */       throw new AssertionError("Subroutines not supported.");
/*     */     }
/*  88 */     this.successor = (opcode != 167);
/*  89 */     this.first = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/*  94 */     if (this.first) {
/*  95 */       LabelInfo.setTarget(label);
/*     */     }
/*  97 */     if (this.successor) {
/*  98 */       LabelInfo.setSuccessor(label);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLineNumber(int line, Label start) {
/* 104 */     if (line == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 110 */     this.lineStart = start;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 116 */     visitSwitchInsn(dflt, labels);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 122 */     visitSwitchInsn(dflt, labels);
/*     */   }
/*     */   
/*     */   private void visitSwitchInsn(Label dflt, Label[] labels) {
/* 126 */     LabelInfo.resetDone(dflt);
/* 127 */     LabelInfo.resetDone(labels);
/* 128 */     setTargetIfNotDone(dflt);
/* 129 */     for (Label l : labels) {
/* 130 */       setTargetIfNotDone(l);
/*     */     }
/* 132 */     this.successor = false;
/* 133 */     this.first = false;
/*     */   }
/*     */   
/*     */   private static void setTargetIfNotDone(Label label) {
/* 137 */     if (!LabelInfo.isDone(label)) {
/* 138 */       LabelInfo.setTarget(label);
/* 139 */       LabelInfo.setDone(label);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/* 145 */     switch (opcode) {
/*     */       case 172:
/*     */       case 173:
/*     */       case 174:
/*     */       case 175:
/*     */       case 176:
/*     */       case 177:
/*     */       case 191:
/* 153 */         this.successor = false;
/*     */         break;
/*     */       default:
/* 156 */         this.successor = true;
/*     */         break;
/*     */     } 
/* 159 */     this.first = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 164 */     this.successor = true;
/* 165 */     this.first = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int var) {
/* 170 */     if (169 == opcode) {
/* 171 */       throw new AssertionError("Subroutines not supported.");
/*     */     }
/* 173 */     this.successor = true;
/* 174 */     this.first = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 179 */     this.successor = true;
/* 180 */     this.first = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String desc) {
/* 186 */     this.successor = true;
/* 187 */     this.first = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
/* 193 */     this.successor = true;
/* 194 */     this.first = false;
/* 195 */     markMethodInvocationLine();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
/* 201 */     this.successor = true;
/* 202 */     this.first = false;
/* 203 */     markMethodInvocationLine();
/*     */   }
/*     */   
/*     */   private void markMethodInvocationLine() {
/* 207 */     if (this.lineStart != null) {
/* 208 */       LabelInfo.setMethodInvocationLine(this.lineStart);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object cst) {
/* 214 */     this.successor = true;
/* 215 */     this.first = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int var, int increment) {
/* 220 */     this.successor = true;
/* 221 */     this.first = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String desc, int dims) {
/* 226 */     this.successor = true;
/* 227 */     this.first = false;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/flow/LabelFlowAnalyzer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */