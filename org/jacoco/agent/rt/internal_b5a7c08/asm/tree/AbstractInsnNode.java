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
/*     */ public abstract class AbstractInsnNode
/*     */ {
/*     */   public static final int INSN = 0;
/*     */   public static final int INT_INSN = 1;
/*     */   public static final int VAR_INSN = 2;
/*     */   public static final int TYPE_INSN = 3;
/*     */   public static final int FIELD_INSN = 4;
/*     */   public static final int METHOD_INSN = 5;
/*     */   public static final int INVOKE_DYNAMIC_INSN = 6;
/*     */   public static final int JUMP_INSN = 7;
/*     */   public static final int LABEL = 8;
/*     */   public static final int LDC_INSN = 9;
/*     */   public static final int IINC_INSN = 10;
/*     */   public static final int TABLESWITCH_INSN = 11;
/*     */   public static final int LOOKUPSWITCH_INSN = 12;
/*     */   public static final int MULTIANEWARRAY_INSN = 13;
/*     */   public static final int FRAME = 14;
/*     */   public static final int LINE = 15;
/*     */   protected int opcode;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   AbstractInsnNode previousInsn;
/*     */   AbstractInsnNode nextInsn;
/*     */   int index;
/*     */   
/*     */   protected AbstractInsnNode(int opcode) {
/* 130 */     this.opcode = opcode;
/* 131 */     this.index = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpcode() {
/* 141 */     return this.opcode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode getPrevious() {
/* 158 */     return this.previousInsn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode getNext() {
/* 168 */     return this.nextInsn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void accept(MethodVisitor paramMethodVisitor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void acceptAnnotations(MethodVisitor methodVisitor) {
/* 184 */     if (this.visibleTypeAnnotations != null) {
/* 185 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 186 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 187 */         typeAnnotation.accept(methodVisitor
/* 188 */             .visitInsnAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 192 */     if (this.invisibleTypeAnnotations != null) {
/* 193 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 194 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 195 */         typeAnnotation.accept(methodVisitor
/* 196 */             .visitInsnAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       } 
/*     */     }
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
/*     */   public abstract AbstractInsnNode clone(Map<LabelNode, LabelNode> paramMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static LabelNode clone(LabelNode label, Map<LabelNode, LabelNode> clonedLabels) {
/* 219 */     return clonedLabels.get(label);
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
/*     */   static LabelNode[] clone(List<LabelNode> labels, Map<LabelNode, LabelNode> clonedLabels) {
/* 231 */     LabelNode[] clones = new LabelNode[labels.size()];
/* 232 */     for (int i = 0, n = clones.length; i < n; i++) {
/* 233 */       clones[i] = clonedLabels.get(labels.get(i));
/*     */     }
/* 235 */     return clones;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final AbstractInsnNode cloneAnnotations(AbstractInsnNode insnNode) {
/* 245 */     if (insnNode.visibleTypeAnnotations != null) {
/* 246 */       this.visibleTypeAnnotations = new ArrayList<TypeAnnotationNode>();
/* 247 */       for (int i = 0, n = insnNode.visibleTypeAnnotations.size(); i < n; i++) {
/* 248 */         TypeAnnotationNode sourceAnnotation = insnNode.visibleTypeAnnotations.get(i);
/* 249 */         TypeAnnotationNode cloneAnnotation = new TypeAnnotationNode(sourceAnnotation.typeRef, sourceAnnotation.typePath, sourceAnnotation.desc);
/*     */ 
/*     */         
/* 252 */         sourceAnnotation.accept(cloneAnnotation);
/* 253 */         this.visibleTypeAnnotations.add(cloneAnnotation);
/*     */       } 
/*     */     } 
/* 256 */     if (insnNode.invisibleTypeAnnotations != null) {
/* 257 */       this.invisibleTypeAnnotations = new ArrayList<TypeAnnotationNode>();
/* 258 */       for (int i = 0, n = insnNode.invisibleTypeAnnotations.size(); i < n; i++) {
/* 259 */         TypeAnnotationNode sourceAnnotation = insnNode.invisibleTypeAnnotations.get(i);
/* 260 */         TypeAnnotationNode cloneAnnotation = new TypeAnnotationNode(sourceAnnotation.typeRef, sourceAnnotation.typePath, sourceAnnotation.desc);
/*     */ 
/*     */         
/* 263 */         sourceAnnotation.accept(cloneAnnotation);
/* 264 */         this.invisibleTypeAnnotations.add(cloneAnnotation);
/*     */       } 
/*     */     } 
/* 267 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/AbstractInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */