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
/*     */ class InterfaceFieldProbeArrayStrategy
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
/*     */   private final int probeCount;
/*     */   
/*     */   private final IExecutionDataAccessorGenerator accessorGenerator;
/*     */   private boolean seenClinit = false;
/*     */   
/*     */   InterfaceFieldProbeArrayStrategy(String className, long classId, int probeCount, IExecutionDataAccessorGenerator accessorGenerator) {
/*  49 */     this.className = className;
/*  50 */     this.classId = classId;
/*  51 */     this.probeCount = probeCount;
/*  52 */     this.accessorGenerator = accessorGenerator;
/*     */   }
/*     */ 
/*     */   
/*     */   public int storeInstance(MethodVisitor mv, boolean clinit, int variable) {
/*  57 */     if (clinit) {
/*  58 */       int maxStack = this.accessorGenerator.generateDataAccessor(this.classId, this.className, this.probeCount, mv);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  63 */       mv.visitInsn(89);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  68 */       mv.visitFieldInsn(179, this.className, "$jacocoData", "[Z");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  73 */       mv.visitVarInsn(58, variable);
/*     */       
/*  75 */       this.seenClinit = true;
/*  76 */       return Math.max(maxStack, 2);
/*     */     } 
/*  78 */     mv.visitMethodInsn(184, this.className, "$jacocoInit", "()[Z", true);
/*     */ 
/*     */     
/*  81 */     mv.visitVarInsn(58, variable);
/*  82 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMembers(ClassVisitor cv, int probeCount) {
/*  87 */     createDataField(cv);
/*  88 */     createInitMethod(cv, probeCount);
/*  89 */     if (!this.seenClinit) {
/*  90 */       createClinitMethod(cv, probeCount);
/*     */     }
/*     */   }
/*     */   
/*     */   private void createDataField(ClassVisitor cv) {
/*  95 */     cv.visitField(4121, "$jacocoData", "[Z", null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createInitMethod(ClassVisitor cv, int probeCount) {
/* 101 */     MethodVisitor mv = cv.visitMethod(4106, "$jacocoInit", "()[Z", null, null);
/*     */ 
/*     */     
/* 104 */     mv.visitCode();
/*     */ 
/*     */     
/* 107 */     mv.visitFieldInsn(178, this.className, "$jacocoData", "[Z");
/*     */     
/* 109 */     mv.visitInsn(89);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     Label alreadyInitialized = new Label();
/* 116 */     mv.visitJumpInsn(199, alreadyInitialized);
/*     */ 
/*     */ 
/*     */     
/* 120 */     mv.visitInsn(87);
/* 121 */     int size = this.accessorGenerator.generateDataAccessor(this.classId, this.className, probeCount, mv);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     mv.visitFrame(-1, 0, FRAME_LOCALS_EMPTY, 1, FRAME_STACK_ARRZ);
/*     */     
/* 129 */     mv.visitLabel(alreadyInitialized);
/* 130 */     mv.visitInsn(176);
/*     */     
/* 132 */     mv.visitMaxs(Math.max(size, 2), 0);
/* 133 */     mv.visitEnd();
/*     */   }
/*     */ 
/*     */   
/*     */   private void createClinitMethod(ClassVisitor cv, int probeCount) {
/* 138 */     MethodVisitor mv = cv.visitMethod(4104, "<clinit>", "()V", null, null);
/*     */     
/* 140 */     mv.visitCode();
/*     */     
/* 142 */     int maxStack = this.accessorGenerator.generateDataAccessor(this.classId, this.className, probeCount, mv);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     mv.visitFieldInsn(179, this.className, "$jacocoData", "[Z");
/*     */ 
/*     */     
/* 150 */     mv.visitInsn(177);
/*     */     
/* 152 */     mv.visitMaxs(maxStack, 0);
/* 153 */     mv.visitEnd();
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/InterfaceFieldProbeArrayStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */