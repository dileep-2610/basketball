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
/*    */ public class IntInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public int operand;
/*    */   
/*    */   public IntInsnNode(int opcode, int operand) {
/* 51 */     super(opcode);
/* 52 */     this.operand = operand;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOpcode(int opcode) {
/* 61 */     this.opcode = opcode;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 66 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 71 */     methodVisitor.visitIntInsn(this.opcode, this.operand);
/* 72 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 77 */     return (new IntInsnNode(this.opcode, this.operand)).cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/IntInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */