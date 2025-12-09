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
/*    */ public class IincInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public int var;
/*    */   public int incr;
/*    */   
/*    */   public IincInsnNode(int varIndex, int incr) {
/* 54 */     super(132);
/* 55 */     this.var = varIndex;
/* 56 */     this.incr = incr;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 61 */     return 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 66 */     methodVisitor.visitIincInsn(this.var, this.incr);
/* 67 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 72 */     return (new IincInsnNode(this.var, this.incr)).cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/IincInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */