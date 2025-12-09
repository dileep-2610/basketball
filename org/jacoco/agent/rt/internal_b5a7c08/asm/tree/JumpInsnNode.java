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
/*    */ public class JumpInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public LabelNode label;
/*    */   
/*    */   public JumpInsnNode(int opcode, LabelNode label) {
/* 57 */     super(opcode);
/* 58 */     this.label = label;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOpcode(int opcode) {
/* 69 */     this.opcode = opcode;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 74 */     return 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 79 */     methodVisitor.visitJumpInsn(this.opcode, this.label.getLabel());
/* 80 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 85 */     return (new JumpInsnNode(this.opcode, clone(this.label, clonedLabels))).cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/JumpInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */