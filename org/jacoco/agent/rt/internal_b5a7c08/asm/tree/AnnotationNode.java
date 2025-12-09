/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.AnnotationVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationNode
/*     */   extends AnnotationVisitor
/*     */ {
/*     */   public String desc;
/*     */   public List<Object> values;
/*     */   
/*     */   public AnnotationNode(String descriptor) {
/*  63 */     this(589824, descriptor);
/*  64 */     if (getClass() != AnnotationNode.class) {
/*  65 */       throw new IllegalStateException();
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
/*     */   public AnnotationNode(int api, String descriptor) {
/*  77 */     super(api);
/*  78 */     this.desc = descriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AnnotationNode(List<Object> values) {
/*  87 */     super(589824);
/*  88 */     this.values = values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(String name, Object value) {
/*  97 */     if (this.values == null) {
/*  98 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 100 */     if (this.desc != null) {
/* 101 */       this.values.add(name);
/*     */     }
/* 103 */     if (value instanceof byte[]) {
/* 104 */       this.values.add(Util.asArrayList((byte[])value));
/* 105 */     } else if (value instanceof boolean[]) {
/* 106 */       this.values.add(Util.asArrayList((boolean[])value));
/* 107 */     } else if (value instanceof short[]) {
/* 108 */       this.values.add(Util.asArrayList((short[])value));
/* 109 */     } else if (value instanceof char[]) {
/* 110 */       this.values.add(Util.asArrayList((char[])value));
/* 111 */     } else if (value instanceof int[]) {
/* 112 */       this.values.add(Util.asArrayList((int[])value));
/* 113 */     } else if (value instanceof long[]) {
/* 114 */       this.values.add(Util.asArrayList((long[])value));
/* 115 */     } else if (value instanceof float[]) {
/* 116 */       this.values.add(Util.asArrayList((float[])value));
/* 117 */     } else if (value instanceof double[]) {
/* 118 */       this.values.add(Util.asArrayList((double[])value));
/*     */     } else {
/* 120 */       this.values.add(value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnum(String name, String descriptor, String value) {
/* 126 */     if (this.values == null) {
/* 127 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 129 */     if (this.desc != null) {
/* 130 */       this.values.add(name);
/*     */     }
/* 132 */     this.values.add(new String[] { descriptor, value });
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String name, String descriptor) {
/* 137 */     if (this.values == null) {
/* 138 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 140 */     if (this.desc != null) {
/* 141 */       this.values.add(name);
/*     */     }
/* 143 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 144 */     this.values.add(annotation);
/* 145 */     return annotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitArray(String name) {
/* 150 */     if (this.values == null) {
/* 151 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 153 */     if (this.desc != null) {
/* 154 */       this.values.add(name);
/*     */     }
/* 156 */     List<Object> array = new ArrayList();
/* 157 */     this.values.add(array);
/* 158 */     return new AnnotationNode(array);
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
/*     */   public void visitEnd() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check(int api) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(AnnotationVisitor annotationVisitor) {
/* 188 */     if (annotationVisitor != null) {
/* 189 */       if (this.values != null) {
/* 190 */         for (int i = 0, n = this.values.size(); i < n; i += 2) {
/* 191 */           String name = (String)this.values.get(i);
/* 192 */           Object value = this.values.get(i + 1);
/* 193 */           accept(annotationVisitor, name, value);
/*     */         } 
/*     */       }
/* 196 */       annotationVisitor.visitEnd();
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
/*     */   static void accept(AnnotationVisitor annotationVisitor, String name, Object value) {
/* 209 */     if (annotationVisitor != null)
/* 210 */       if (value instanceof String[]) {
/* 211 */         String[] typeValue = (String[])value;
/* 212 */         annotationVisitor.visitEnum(name, typeValue[0], typeValue[1]);
/* 213 */       } else if (value instanceof AnnotationNode) {
/* 214 */         AnnotationNode annotationValue = (AnnotationNode)value;
/* 215 */         annotationValue.accept(annotationVisitor.visitAnnotation(name, annotationValue.desc));
/* 216 */       } else if (value instanceof List) {
/* 217 */         AnnotationVisitor arrayAnnotationVisitor = annotationVisitor.visitArray(name);
/* 218 */         if (arrayAnnotationVisitor != null) {
/* 219 */           List<?> arrayValue = (List)value;
/* 220 */           for (int i = 0, n = arrayValue.size(); i < n; i++) {
/* 221 */             accept(arrayAnnotationVisitor, null, arrayValue.get(i));
/*     */           }
/* 223 */           arrayAnnotationVisitor.visitEnd();
/*     */         } 
/*     */       } else {
/* 226 */         annotationVisitor.visit(name, value);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/AnnotationNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */