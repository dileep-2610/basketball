/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FieldVisitor
/*     */ {
/*     */   protected final int api;
/*     */   protected FieldVisitor fv;
/*     */   
/*     */   protected FieldVisitor(int api) {
/*  55 */     this(api, null);
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
/*     */   protected FieldVisitor(int api, FieldVisitor fieldVisitor) {
/*  67 */     if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  74 */       throw new IllegalArgumentException(stringConcat$0(api));
/*     */     }
/*  76 */     if (api == 17432576) {
/*  77 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  79 */     this.api = api;
/*  80 */     this.fv = fieldVisitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldVisitor getDelegate() {
/*  89 */     return this.fv;
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
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 101 */     if (this.fv != null) {
/* 102 */       return this.fv.visitAnnotation(descriptor, visible);
/*     */     }
/* 104 */     return null;
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
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 122 */     if (this.api < 327680) {
/* 123 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 125 */     if (this.fv != null) {
/* 126 */       return this.fv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 137 */     if (this.fv != null) {
/* 138 */       this.fv.visitAttribute(attribute);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 147 */     if (this.fv != null)
/* 148 */       this.fv.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/FieldVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */