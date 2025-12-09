/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.TypePath;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalVariableAnnotationNode
/*     */   extends TypeAnnotationNode
/*     */ {
/*     */   public List<LabelNode> start;
/*     */   public List<LabelNode> end;
/*     */   public List<Integer> index;
/*     */   
/*     */   public LocalVariableAnnotationNode(int typeRef, TypePath typePath, LabelNode[] start, LabelNode[] end, int[] index, String descriptor) {
/*  87 */     this(589824, typeRef, typePath, start, end, index, descriptor);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariableAnnotationNode(int api, int typeRef, TypePath typePath, LabelNode[] start, LabelNode[] end, int[] index, String descriptor) {
/* 115 */     super(api, typeRef, typePath, descriptor);
/* 116 */     this.start = Util.asArrayList(start);
/* 117 */     this.end = Util.asArrayList(end);
/* 118 */     this.index = Util.asArrayList(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor, boolean visible) {
/* 128 */     Label[] startLabels = new Label[this.start.size()];
/* 129 */     Label[] endLabels = new Label[this.end.size()];
/* 130 */     int[] indices = new int[this.index.size()];
/* 131 */     for (int i = 0, n = startLabels.length; i < n; i++) {
/* 132 */       startLabels[i] = ((LabelNode)this.start.get(i)).getLabel();
/* 133 */       endLabels[i] = ((LabelNode)this.end.get(i)).getLabel();
/* 134 */       indices[i] = ((Integer)this.index.get(i)).intValue();
/*     */     } 
/* 136 */     accept(methodVisitor
/* 137 */         .visitLocalVariableAnnotation(this.typeRef, this.typePath, startLabels, endLabels, indices, this.desc, visible));
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/LocalVariableAnnotationNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */