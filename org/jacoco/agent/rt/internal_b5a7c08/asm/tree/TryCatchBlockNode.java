/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class TryCatchBlockNode
/*     */ {
/*     */   public LabelNode start;
/*     */   public LabelNode end;
/*     */   public LabelNode handler;
/*     */   public String type;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   
/*     */   public TryCatchBlockNode(LabelNode start, LabelNode end, LabelNode handler, String type) {
/*  75 */     this.start = start;
/*  76 */     this.end = end;
/*  77 */     this.handler = handler;
/*  78 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateIndex(int index) {
/*  89 */     int newTypeRef = 0x42000000 | index << 8;
/*  90 */     if (this.visibleTypeAnnotations != null) {
/*  91 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/*  92 */         ((TypeAnnotationNode)this.visibleTypeAnnotations.get(i)).typeRef = newTypeRef;
/*     */       }
/*     */     }
/*  95 */     if (this.invisibleTypeAnnotations != null) {
/*  96 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/*  97 */         ((TypeAnnotationNode)this.invisibleTypeAnnotations.get(i)).typeRef = newTypeRef;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 108 */     methodVisitor.visitTryCatchBlock(this.start
/* 109 */         .getLabel(), this.end.getLabel(), (this.handler == null) ? null : this.handler.getLabel(), this.type);
/* 110 */     if (this.visibleTypeAnnotations != null) {
/* 111 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 112 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 113 */         typeAnnotation.accept(methodVisitor
/* 114 */             .visitTryCatchAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 118 */     if (this.invisibleTypeAnnotations != null)
/* 119 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 120 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 121 */         typeAnnotation.accept(methodVisitor
/* 122 */             .visitTryCatchAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       }  
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/TryCatchBlockNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */