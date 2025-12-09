/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
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
/*     */ public class FrameNode
/*     */   extends AbstractInsnNode
/*     */ {
/*     */   public int type;
/*     */   public List<Object> local;
/*     */   public List<Object> stack;
/*     */   
/*     */   private FrameNode() {
/*  73 */     super(-1);
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
/*     */   public FrameNode(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 101 */     super(-1);
/* 102 */     this.type = type;
/* 103 */     switch (type) {
/*     */       case -1:
/*     */       case 0:
/* 106 */         this.local = Util.asArrayList(numLocal, local);
/* 107 */         this.stack = Util.asArrayList(numStack, stack);
/*     */       
/*     */       case 1:
/* 110 */         this.local = Util.asArrayList(numLocal, local);
/*     */       
/*     */       case 2:
/* 113 */         this.local = Util.asArrayList(numLocal);
/*     */       
/*     */       case 3:
/*     */         return;
/*     */       case 4:
/* 118 */         this.stack = Util.asArrayList(1, stack);
/*     */     } 
/*     */     
/* 121 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 127 */     return 14;
/*     */   }
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 132 */     switch (this.type) {
/*     */       case -1:
/*     */       case 0:
/* 135 */         methodVisitor.visitFrame(this.type, this.local.size(), asArray(this.local), this.stack.size(), asArray(this.stack));
/*     */         return;
/*     */       case 1:
/* 138 */         methodVisitor.visitFrame(this.type, this.local.size(), asArray(this.local), 0, null);
/*     */         return;
/*     */       case 2:
/* 141 */         methodVisitor.visitFrame(this.type, this.local.size(), null, 0, null);
/*     */         return;
/*     */       case 3:
/* 144 */         methodVisitor.visitFrame(this.type, 0, null, 0, null);
/*     */         return;
/*     */       case 4:
/* 147 */         methodVisitor.visitFrame(this.type, 0, null, 1, asArray(this.stack));
/*     */         return;
/*     */     } 
/* 150 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 156 */     FrameNode clone = new FrameNode();
/* 157 */     clone.type = this.type;
/* 158 */     if (this.local != null) {
/* 159 */       clone.local = new ArrayList();
/* 160 */       for (int i = 0, n = this.local.size(); i < n; i++) {
/* 161 */         Object localElement = this.local.get(i);
/* 162 */         if (localElement instanceof LabelNode) {
/* 163 */           localElement = clonedLabels.get(localElement);
/*     */         }
/* 165 */         clone.local.add(localElement);
/*     */       } 
/*     */     } 
/* 168 */     if (this.stack != null) {
/* 169 */       clone.stack = new ArrayList();
/* 170 */       for (int i = 0, n = this.stack.size(); i < n; i++) {
/* 171 */         Object stackElement = this.stack.get(i);
/* 172 */         if (stackElement instanceof LabelNode) {
/* 173 */           stackElement = clonedLabels.get(stackElement);
/*     */         }
/* 175 */         clone.stack.add(stackElement);
/*     */       } 
/*     */     } 
/* 178 */     return clone;
/*     */   }
/*     */   
/*     */   private static Object[] asArray(List<Object> list) {
/* 182 */     Object[] array = new Object[list.size()];
/* 183 */     for (int i = 0, n = array.length; i < n; i++) {
/* 184 */       Object o = list.get(i);
/* 185 */       if (o instanceof LabelNode) {
/* 186 */         o = ((LabelNode)o).getLabel();
/*     */       }
/* 188 */       array[i] = o;
/*     */     } 
/* 190 */     return array;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/FrameNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */