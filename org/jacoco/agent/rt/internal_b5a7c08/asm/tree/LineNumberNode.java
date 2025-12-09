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
/*    */ public class LineNumberNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public int line;
/*    */   public LabelNode start;
/*    */   
/*    */   public LineNumberNode(int line, LabelNode start) {
/* 55 */     super(-1);
/* 56 */     this.line = line;
/* 57 */     this.start = start;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 62 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 67 */     methodVisitor.visitLineNumber(this.line, this.start.getLabel());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 72 */     return new LineNumberNode(this.line, clone(this.start, clonedLabels));
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/LineNumberNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */