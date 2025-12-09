/*    */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
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
/*    */ public class TableSwitchInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public int min;
/*    */   public int max;
/*    */   public LabelNode dflt;
/*    */   public List<LabelNode> labels;
/*    */   
/*    */   public TableSwitchInsnNode(int min, int max, LabelNode dflt, LabelNode... labels) {
/* 66 */     super(170);
/* 67 */     this.min = min;
/* 68 */     this.max = max;
/* 69 */     this.dflt = dflt;
/* 70 */     this.labels = Util.asArrayList(labels);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 75 */     return 11;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 80 */     Label[] labelsArray = new Label[this.labels.size()];
/* 81 */     for (int i = 0, n = labelsArray.length; i < n; i++) {
/* 82 */       labelsArray[i] = ((LabelNode)this.labels.get(i)).getLabel();
/*    */     }
/* 84 */     methodVisitor.visitTableSwitchInsn(this.min, this.max, this.dflt.getLabel(), labelsArray);
/* 85 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 90 */     return (new TableSwitchInsnNode(this.min, this.max, clone(this.dflt, clonedLabels), clone(this.labels, clonedLabels)))
/* 91 */       .cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/TableSwitchInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */