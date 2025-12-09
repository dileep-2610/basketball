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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MethodVisitor
/*     */ {
/*     */   private static final String REQUIRES_ASM5 = "This feature requires ASM5";
/*     */   protected final int api;
/*     */   protected MethodVisitor mv;
/*     */   
/*     */   protected MethodVisitor(int api) {
/*  72 */     this(api, null);
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
/*     */   protected MethodVisitor(int api, MethodVisitor methodVisitor) {
/*  84 */     if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  91 */       throw new IllegalArgumentException(stringConcat$0(api));
/*     */     }
/*  93 */     if (api == 17432576) {
/*  94 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  96 */     this.api = api;
/*  97 */     this.mv = methodVisitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodVisitor getDelegate() {
/* 107 */     return this.mv;
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
/*     */   public void visitParameter(String name, int access) {
/* 122 */     if (this.api < 327680) {
/* 123 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 125 */     if (this.mv != null) {
/* 126 */       this.mv.visitParameter(name, access);
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
/*     */   public AnnotationVisitor visitAnnotationDefault() {
/* 139 */     if (this.mv != null) {
/* 140 */       return this.mv.visitAnnotationDefault();
/*     */     }
/* 142 */     return null;
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
/* 154 */     if (this.mv != null) {
/* 155 */       return this.mv.visitAnnotation(descriptor, visible);
/*     */     }
/* 157 */     return null;
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
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 178 */     if (this.api < 327680) {
/* 179 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 181 */     if (this.mv != null) {
/* 182 */       return this.mv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 184 */     return null;
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
/*     */   public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
/* 202 */     if (this.mv != null) {
/* 203 */       this.mv.visitAnnotableParameterCount(parameterCount, visible);
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
/*     */   public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
/* 223 */     if (this.mv != null) {
/* 224 */       return this.mv.visitParameterAnnotation(parameter, descriptor, visible);
/*     */     }
/* 226 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 235 */     if (this.mv != null) {
/* 236 */       this.mv.visitAttribute(attribute);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitCode() {
/* 242 */     if (this.mv != null) {
/* 243 */       this.mv.visitCode();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 312 */     if (this.mv != null) {
/* 313 */       this.mv.visitFrame(type, numLocal, local, numStack, stack);
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
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/* 336 */     if (this.mv != null) {
/* 337 */       this.mv.visitInsn(opcode);
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
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 356 */     if (this.mv != null) {
/* 357 */       this.mv.visitIntInsn(opcode, operand);
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
/*     */   public void visitVarInsn(int opcode, int varIndex) {
/* 371 */     if (this.mv != null) {
/* 372 */       this.mv.visitVarInsn(opcode, varIndex);
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
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 386 */     if (this.mv != null) {
/* 387 */       this.mv.visitTypeInsn(opcode, type);
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
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 403 */     if (this.mv != null) {
/* 404 */       this.mv.visitFieldInsn(opcode, owner, name, descriptor);
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
/*     */   @Deprecated
/*     */   public void visitMethodInsn(int opcode, String owner, String name, String descriptor) {
/* 422 */     int opcodeAndSource = opcode | ((this.api < 327680) ? 256 : 0);
/* 423 */     visitMethodInsn(opcodeAndSource, owner, name, descriptor, (opcode == 185));
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
/*     */   public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
/* 443 */     if (this.api < 327680 && (opcode & 0x100) == 0) {
/* 444 */       if (isInterface != ((opcode == 185))) {
/* 445 */         throw new UnsupportedOperationException("INVOKESPECIAL/STATIC on interfaces requires ASM5");
/*     */       }
/* 447 */       visitMethodInsn(opcode, owner, name, descriptor);
/*     */       return;
/*     */     } 
/* 450 */     if (this.mv != null) {
/* 451 */       this.mv.visitMethodInsn(opcode & 0xFFFFFEFF, owner, name, descriptor, isInterface);
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
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 471 */     if (this.api < 327680) {
/* 472 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 474 */     if (this.mv != null) {
/* 475 */       this.mv.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
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
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 490 */     if (this.mv != null) {
/* 491 */       this.mv.visitJumpInsn(opcode, label);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/* 501 */     if (this.mv != null) {
/* 502 */       this.mv.visitLabel(label);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 554 */     if (this.api < 327680 && (value instanceof Handle || (value instanceof Type && ((Type)value)
/*     */       
/* 556 */       .getSort() == 11))) {
/* 557 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 559 */     if (this.api < 458752 && value instanceof ConstantDynamic) {
/* 560 */       throw new UnsupportedOperationException("This feature requires ASM7");
/*     */     }
/* 562 */     if (this.mv != null) {
/* 563 */       this.mv.visitLdcInsn(value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int varIndex, int increment) {
/* 574 */     if (this.mv != null) {
/* 575 */       this.mv.visitIincInsn(varIndex, increment);
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
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 590 */     if (this.mv != null) {
/* 591 */       this.mv.visitTableSwitchInsn(min, max, dflt, labels);
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
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 604 */     if (this.mv != null) {
/* 605 */       this.mv.visitLookupSwitchInsn(dflt, keys, labels);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 616 */     if (this.mv != null) {
/* 617 */       this.mv.visitMultiANewArrayInsn(descriptor, numDimensions);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 642 */     if (this.api < 327680) {
/* 643 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 645 */     if (this.mv != null) {
/* 646 */       return this.mv.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 648 */     return null;
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
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/* 669 */     if (this.mv != null) {
/* 670 */       this.mv.visitTryCatchBlock(start, end, handler, type);
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
/*     */   public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 691 */     if (this.api < 327680) {
/* 692 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 694 */     if (this.mv != null) {
/* 695 */       return this.mv.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 697 */     return null;
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
/*     */   public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 721 */     if (this.mv != null) {
/* 722 */       this.mv.visitLocalVariable(name, descriptor, signature, start, end, index);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 754 */     if (this.api < 327680) {
/* 755 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 757 */     if (this.mv != null) {
/* 758 */       return this.mv.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
/*     */     }
/*     */     
/* 761 */     return null;
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
/*     */   public void visitLineNumber(int line, Label start) {
/* 774 */     if (this.mv != null) {
/* 775 */       this.mv.visitLineNumber(line, start);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 786 */     if (this.mv != null) {
/* 787 */       this.mv.visitMaxs(maxStack, maxLocals);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 796 */     if (this.mv != null)
/* 797 */       this.mv.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/MethodVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */