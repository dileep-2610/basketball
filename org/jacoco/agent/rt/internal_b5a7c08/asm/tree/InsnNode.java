/*    */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class InsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public InsnNode(int opcode) {
/* 55 */     super(opcode);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 60 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 65 */     methodVisitor.visitInsn(this.opcode);
/* 66 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 71 */     return (new InsnNode(this.opcode)).cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/InsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */