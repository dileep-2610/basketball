/*    */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Handle;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
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
/*    */ 
/*    */ public class InvokeDynamicInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public String name;
/*    */   public String desc;
/*    */   public Handle bsm;
/*    */   public Object[] bsmArgs;
/*    */   
/*    */   public InvokeDynamicInsnNode(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 70 */     super(186);
/* 71 */     this.name = name;
/* 72 */     this.desc = descriptor;
/* 73 */     this.bsm = bootstrapMethodHandle;
/* 74 */     this.bsmArgs = bootstrapMethodArguments;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 79 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 84 */     methodVisitor.visitInvokeDynamicInsn(this.name, this.desc, this.bsm, this.bsmArgs);
/* 85 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 90 */     return (new InvokeDynamicInsnNode(this.name, this.desc, this.bsm, this.bsmArgs)).cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/InvokeDynamicInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */