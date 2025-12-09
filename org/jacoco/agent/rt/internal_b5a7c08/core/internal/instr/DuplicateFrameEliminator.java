/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Handle;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
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
/*     */ class DuplicateFrameEliminator
/*     */   extends MethodVisitor
/*     */ {
/*     */   private boolean instruction;
/*     */   
/*     */   public DuplicateFrameEliminator(MethodVisitor mv) {
/*  30 */     super(589824, mv);
/*  31 */     this.instruction = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
/*  37 */     if (this.instruction) {
/*  38 */       this.instruction = false;
/*  39 */       this.mv.visitFrame(type, nLocal, local, nStack, stack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/*  45 */     this.instruction = true;
/*  46 */     this.mv.visitInsn(opcode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/*  51 */     this.instruction = true;
/*  52 */     this.mv.visitIntInsn(opcode, operand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int var) {
/*  57 */     this.instruction = true;
/*  58 */     this.mv.visitVarInsn(opcode, var);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/*  63 */     this.instruction = true;
/*  64 */     this.mv.visitTypeInsn(opcode, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String desc) {
/*  70 */     this.instruction = true;
/*  71 */     this.mv.visitFieldInsn(opcode, owner, name, desc);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
/*  77 */     this.instruction = true;
/*  78 */     this.mv.visitMethodInsn(opcode, owner, name, desc, itf);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
/*  84 */     this.instruction = true;
/*  85 */     this.mv.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/*  90 */     this.instruction = true;
/*  91 */     this.mv.visitJumpInsn(opcode, label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object cst) {
/*  96 */     this.instruction = true;
/*  97 */     this.mv.visitLdcInsn(cst);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int var, int increment) {
/* 102 */     this.instruction = true;
/* 103 */     this.mv.visitIincInsn(var, increment);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 109 */     this.instruction = true;
/* 110 */     this.mv.visitTableSwitchInsn(min, max, dflt, labels);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 116 */     this.instruction = true;
/* 117 */     this.mv.visitLookupSwitchInsn(dflt, keys, labels);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String desc, int dims) {
/* 122 */     this.instruction = true;
/* 123 */     this.mv.visitMultiANewArrayInsn(desc, dims);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/DuplicateFrameEliminator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */