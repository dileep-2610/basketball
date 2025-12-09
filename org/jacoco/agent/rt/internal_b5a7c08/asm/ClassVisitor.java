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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ClassVisitor
/*     */ {
/*     */   protected final int api;
/*     */   protected ClassVisitor cv;
/*     */   
/*     */   protected ClassVisitor(int api) {
/*  58 */     this(api, null);
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
/*     */   protected ClassVisitor(int api, ClassVisitor classVisitor) {
/*  70 */     if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       throw new IllegalArgumentException(stringConcat$0(api));
/*     */     }
/*  79 */     if (api == 17432576) {
/*  80 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  82 */     this.api = api;
/*  83 */     this.cv = classVisitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassVisitor getDelegate() {
/*  92 */     return this.cv;
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
/*     */   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/* 119 */     if (this.api < 524288 && (access & 0x10000) != 0) {
/* 120 */       throw new UnsupportedOperationException("Records requires ASM8");
/*     */     }
/* 122 */     if (this.cv != null) {
/* 123 */       this.cv.visit(version, access, name, signature, superName, interfaces);
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
/*     */   public void visitSource(String source, String debug) {
/* 136 */     if (this.cv != null) {
/* 137 */       this.cv.visitSource(source, debug);
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
/*     */ 
/*     */   
/*     */   public ModuleVisitor visitModule(String name, int access, String version) {
/* 152 */     if (this.api < 393216) {
/* 153 */       throw new UnsupportedOperationException("Module requires ASM6");
/*     */     }
/* 155 */     if (this.cv != null) {
/* 156 */       return this.cv.visitModule(name, access, version);
/*     */     }
/* 158 */     return null;
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
/*     */   public void visitNestHost(String nestHost) {
/* 173 */     if (this.api < 458752) {
/* 174 */       throw new UnsupportedOperationException("NestHost requires ASM7");
/*     */     }
/* 176 */     if (this.cv != null) {
/* 177 */       this.cv.visitNestHost(nestHost);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitOuterClass(String owner, String name, String descriptor) {
/* 197 */     if (this.cv != null) {
/* 198 */       this.cv.visitOuterClass(owner, name, descriptor);
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
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 211 */     if (this.cv != null) {
/* 212 */       return this.cv.visitAnnotation(descriptor, visible);
/*     */     }
/* 214 */     return null;
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
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 234 */     if (this.api < 327680) {
/* 235 */       throw new UnsupportedOperationException("TypeAnnotation requires ASM5");
/*     */     }
/* 237 */     if (this.cv != null) {
/* 238 */       return this.cv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 249 */     if (this.cv != null) {
/* 250 */       this.cv.visitAttribute(attribute);
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
/*     */   
/*     */   public void visitNestMember(String nestMember) {
/* 264 */     if (this.api < 458752) {
/* 265 */       throw new UnsupportedOperationException("NestMember requires ASM7");
/*     */     }
/* 267 */     if (this.cv != null) {
/* 268 */       this.cv.visitNestMember(nestMember);
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
/*     */   public void visitPermittedSubclass(String permittedSubclass) {
/* 280 */     if (this.api < 589824) {
/* 281 */       throw new UnsupportedOperationException("PermittedSubclasses requires ASM9");
/*     */     }
/* 283 */     if (this.cv != null) {
/* 284 */       this.cv.visitPermittedSubclass(permittedSubclass);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInnerClass(String name, String outerName, String innerName, int access) {
/* 305 */     if (this.cv != null) {
/* 306 */       this.cv.visitInnerClass(name, outerName, innerName, access);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
/* 322 */     if (this.api < 524288) {
/* 323 */       throw new UnsupportedOperationException("Record requires ASM8");
/*     */     }
/* 325 */     if (this.cv != null) {
/* 326 */       return this.cv.visitRecordComponent(name, descriptor, signature);
/*     */     }
/* 328 */     return null;
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
/*     */   public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
/* 355 */     if (this.cv != null) {
/* 356 */       return this.cv.visitField(access, name, descriptor, signature, value);
/*     */     }
/* 358 */     return null;
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
/*     */   public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/* 383 */     if (this.cv != null) {
/* 384 */       return this.cv.visitMethod(access, name, descriptor, signature, exceptions);
/*     */     }
/* 386 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 394 */     if (this.cv != null)
/* 395 */       this.cv.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/ClassVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */