/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*    */ 
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassVisitor;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.IExecutionDataAccessorGenerator;
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
/*    */ class LocalProbeArrayStrategy
/*    */   implements IProbeArrayStrategy
/*    */ {
/*    */   private final String className;
/*    */   private final long classId;
/*    */   private final int probeCount;
/*    */   private final IExecutionDataAccessorGenerator accessorGenerator;
/*    */   
/*    */   LocalProbeArrayStrategy(String className, long classId, int probeCount, IExecutionDataAccessorGenerator accessorGenerator) {
/* 36 */     this.className = className;
/* 37 */     this.classId = classId;
/* 38 */     this.probeCount = probeCount;
/* 39 */     this.accessorGenerator = accessorGenerator;
/*    */   }
/*    */ 
/*    */   
/*    */   public int storeInstance(MethodVisitor mv, boolean clinit, int variable) {
/* 44 */     int maxStack = this.accessorGenerator.generateDataAccessor(this.classId, this.className, this.probeCount, mv);
/*    */     
/* 46 */     mv.visitVarInsn(58, variable);
/* 47 */     return maxStack;
/*    */   }
/*    */   
/*    */   public void addMembers(ClassVisitor delegate, int probeCount) {}
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/LocalProbeArrayStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */