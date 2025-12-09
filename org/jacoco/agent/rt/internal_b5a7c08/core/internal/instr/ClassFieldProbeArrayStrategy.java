/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.IExecutionDataAccessorGenerator;
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
/*     */ class ClassFieldProbeArrayStrategy
/*     */   implements IProbeArrayStrategy
/*     */ {
/*  31 */   private static final Object[] FRAME_STACK_ARRZ = new Object[] { "[Z" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   private static final Object[] FRAME_LOCALS_EMPTY = new Object[0];
/*     */   
/*     */   private final String className;
/*     */   
/*     */   private final long classId;
/*     */   
/*     */   private final boolean withFrames;
/*     */   private final IExecutionDataAccessorGenerator accessorGenerator;
/*     */   
/*     */   ClassFieldProbeArrayStrategy(String className, long classId, boolean withFrames, IExecutionDataAccessorGenerator accessorGenerator) {
/*  47 */     this.className = className;
/*  48 */     this.classId = classId;
/*  49 */     this.withFrames = withFrames;
/*  50 */     this.accessorGenerator = accessorGenerator;
/*     */   }
/*     */ 
/*     */   
/*     */   public int storeInstance(MethodVisitor mv, boolean clinit, int variable) {
/*  55 */     mv.visitMethodInsn(184, this.className, "$jacocoInit", "()[Z", false);
/*     */ 
/*     */     
/*  58 */     mv.visitVarInsn(58, variable);
/*  59 */     return 1;
/*     */   }
/*     */   
/*     */   public void addMembers(ClassVisitor cv, int probeCount) {
/*  63 */     createDataField(cv);
/*  64 */     createInitMethod(cv, probeCount);
/*     */   }
/*     */   
/*     */   private void createDataField(ClassVisitor cv) {
/*  68 */     cv.visitField(4234, "$jacocoData", "[Z", null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createInitMethod(ClassVisitor cv, int probeCount) {
/*  73 */     MethodVisitor mv = cv.visitMethod(4106, "$jacocoInit", "()[Z", null, null);
/*     */ 
/*     */     
/*  76 */     mv.visitCode();
/*     */ 
/*     */     
/*  79 */     mv.visitFieldInsn(178, this.className, "$jacocoData", "[Z");
/*     */     
/*  81 */     mv.visitInsn(89);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     Label alreadyInitialized = new Label();
/*  88 */     mv.visitJumpInsn(199, alreadyInitialized);
/*     */ 
/*     */ 
/*     */     
/*  92 */     mv.visitInsn(87);
/*  93 */     int size = genInitializeDataField(mv, probeCount);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     if (this.withFrames) {
/*  99 */       mv.visitFrame(-1, 0, FRAME_LOCALS_EMPTY, 1, FRAME_STACK_ARRZ);
/*     */     }
/*     */     
/* 102 */     mv.visitLabel(alreadyInitialized);
/* 103 */     mv.visitInsn(176);
/*     */     
/* 105 */     mv.visitMaxs(Math.max(size, 2), 0);
/* 106 */     mv.visitEnd();
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
/*     */   private int genInitializeDataField(MethodVisitor mv, int probeCount) {
/* 120 */     int size = this.accessorGenerator.generateDataAccessor(this.classId, this.className, probeCount, mv);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     mv.visitInsn(89);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     mv.visitFieldInsn(179, this.className, "$jacocoData", "[Z");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     return Math.max(size, 2);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/ClassFieldProbeArrayStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */