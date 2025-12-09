/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*    */ 
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassVisitor;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ConstantDynamic;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Handle;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CondyProbeArrayStrategy
/*    */   implements IProbeArrayStrategy
/*    */ {
/*    */   public static final String B_DESC = "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;)[Z";
/*    */   private final String className;
/*    */   private final boolean isInterface;
/*    */   private final long classId;
/*    */   private final IExecutionDataAccessorGenerator accessorGenerator;
/*    */   
/*    */   CondyProbeArrayStrategy(String className, boolean isInterface, long classId, IExecutionDataAccessorGenerator accessorGenerator) {
/* 45 */     this.className = className;
/* 46 */     this.isInterface = isInterface;
/* 47 */     this.classId = classId;
/* 48 */     this.accessorGenerator = accessorGenerator;
/*    */   }
/*    */ 
/*    */   
/*    */   public int storeInstance(MethodVisitor mv, boolean clinit, int variable) {
/* 53 */     Handle bootstrapMethod = new Handle(6, this.className, "$jacocoInit", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;)[Z", this.isInterface);
/*    */ 
/*    */ 
/*    */     
/* 57 */     mv.visitLdcInsn(new ConstantDynamic("$jacocoData", "Ljava/lang/Object;", bootstrapMethod, new Object[0]));
/*    */     
/* 59 */     mv.visitTypeInsn(192, "[Z");
/* 60 */     mv.visitVarInsn(58, variable);
/* 61 */     return 1;
/*    */   }
/*    */   
/*    */   public void addMembers(ClassVisitor cv, int probeCount) {
/* 65 */     MethodVisitor mv = cv.visitMethod(4106, "$jacocoInit", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;)[Z", null, null);
/*    */     
/* 67 */     int maxStack = this.accessorGenerator.generateDataAccessor(this.classId, this.className, probeCount, mv);
/*    */     
/* 69 */     mv.visitInsn(176);
/* 70 */     mv.visitMaxs(maxStack, 3);
/* 71 */     mv.visitEnd();
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/CondyProbeArrayStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */