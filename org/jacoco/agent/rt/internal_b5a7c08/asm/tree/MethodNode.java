/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.AnnotationVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Attribute;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Handle;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Type;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MethodNode
/*     */   extends MethodVisitor
/*     */ {
/*     */   public int access;
/*     */   public String name;
/*     */   public String desc;
/*     */   public String signature;
/*     */   public List<String> exceptions;
/*     */   public List<ParameterNode> parameters;
/*     */   public List<AnnotationNode> visibleAnnotations;
/*     */   public List<AnnotationNode> invisibleAnnotations;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   public List<Attribute> attrs;
/*     */   public Object annotationDefault;
/*     */   public int visibleAnnotableParameterCount;
/*     */   public List<AnnotationNode>[] visibleParameterAnnotations;
/*     */   public int invisibleAnnotableParameterCount;
/*     */   public List<AnnotationNode>[] invisibleParameterAnnotations;
/*     */   public InsnList instructions;
/*     */   public List<TryCatchBlockNode> tryCatchBlocks;
/*     */   public int maxStack;
/*     */   public int maxLocals;
/*     */   public List<LocalVariableNode> localVariables;
/*     */   public List<LocalVariableAnnotationNode> visibleLocalVariableAnnotations;
/*     */   public List<LocalVariableAnnotationNode> invisibleLocalVariableAnnotations;
/*     */   private boolean visited;
/*     */   
/*     */   public MethodNode() {
/* 158 */     this(589824);
/* 159 */     if (getClass() != MethodNode.class) {
/* 160 */       throw new IllegalStateException();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodNode(int api) {
/* 171 */     super(api);
/* 172 */     this.instructions = new InsnList();
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
/*     */   public MethodNode(int access, String name, String descriptor, String signature, String[] exceptions) {
/* 194 */     this(589824, access, name, descriptor, signature, exceptions);
/* 195 */     if (getClass() != MethodNode.class) {
/* 196 */       throw new IllegalStateException();
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
/*     */   public MethodNode(int api, int access, String name, String descriptor, String signature, String[] exceptions) {
/* 220 */     super(api);
/* 221 */     this.access = access;
/* 222 */     this.name = name;
/* 223 */     this.desc = descriptor;
/* 224 */     this.signature = signature;
/* 225 */     this.exceptions = Util.asArrayList(exceptions);
/* 226 */     if ((access & 0x400) == 0) {
/* 227 */       this.localVariables = new ArrayList<LocalVariableNode>(5);
/*     */     }
/* 229 */     this.tryCatchBlocks = new ArrayList<TryCatchBlockNode>();
/* 230 */     this.instructions = new InsnList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitParameter(String name, int access) {
/* 239 */     if (this.parameters == null) {
/* 240 */       this.parameters = new ArrayList<ParameterNode>(5);
/*     */     }
/* 242 */     this.parameters.add(new ParameterNode(name, access));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotationDefault() {
/* 248 */     return new AnnotationNode(new ArrayList(0)
/*     */         {
/*     */           public boolean add(Object o)
/*     */           {
/* 252 */             MethodNode.this.annotationDefault = o;
/* 253 */             return super.add(o);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 260 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 261 */     if (visible) {
/* 262 */       this.visibleAnnotations = Util.add(this.visibleAnnotations, annotation);
/*     */     } else {
/* 264 */       this.invisibleAnnotations = Util.add(this.invisibleAnnotations, annotation);
/*     */     } 
/* 266 */     return annotation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 272 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 273 */     if (visible) {
/* 274 */       this.visibleTypeAnnotations = Util.add(this.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 276 */       this.invisibleTypeAnnotations = Util.add(this.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 278 */     return typeAnnotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
/* 283 */     if (visible) {
/* 284 */       this.visibleAnnotableParameterCount = parameterCount;
/*     */     } else {
/* 286 */       this.invisibleAnnotableParameterCount = parameterCount;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
/* 294 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 295 */     if (visible) {
/* 296 */       if (this.visibleParameterAnnotations == null) {
/* 297 */         int params = Type.getArgumentCount(this.desc);
/* 298 */         this.visibleParameterAnnotations = (List<AnnotationNode>[])new List[params];
/*     */       } 
/* 300 */       this.visibleParameterAnnotations[parameter] = 
/* 301 */         Util.add(this.visibleParameterAnnotations[parameter], annotation);
/*     */     } else {
/* 303 */       if (this.invisibleParameterAnnotations == null) {
/* 304 */         int params = Type.getArgumentCount(this.desc);
/* 305 */         this.invisibleParameterAnnotations = (List<AnnotationNode>[])new List[params];
/*     */       } 
/* 307 */       this.invisibleParameterAnnotations[parameter] = 
/* 308 */         Util.add(this.invisibleParameterAnnotations[parameter], annotation);
/*     */     } 
/* 310 */     return annotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 315 */     this.attrs = Util.add(this.attrs, attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitCode() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 330 */     this.instructions.add(new FrameNode(type, numLocal, 
/*     */ 
/*     */ 
/*     */           
/* 334 */           (local == null) ? null : getLabelNodes(local), numStack, 
/*     */           
/* 336 */           (stack == null) ? null : getLabelNodes(stack)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/* 341 */     this.instructions.add(new InsnNode(opcode));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 346 */     this.instructions.add(new IntInsnNode(opcode, operand));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int varIndex) {
/* 351 */     this.instructions.add(new VarInsnNode(opcode, varIndex));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 356 */     this.instructions.add(new TypeInsnNode(opcode, type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 362 */     this.instructions.add(new FieldInsnNode(opcode, owner, name, descriptor));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/* 372 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*     */       
/* 374 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */       return;
/*     */     } 
/* 377 */     int opcode = opcodeAndSource & 0xFFFFFEFF;
/*     */     
/* 379 */     this.instructions.add(new MethodInsnNode(opcode, owner, name, descriptor, isInterface));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 388 */     this.instructions.add(new InvokeDynamicInsnNode(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 395 */     this.instructions.add(new JumpInsnNode(opcode, getLabelNode(label)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/* 400 */     this.instructions.add(getLabelNode(label));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 405 */     this.instructions.add(new LdcInsnNode(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int varIndex, int increment) {
/* 410 */     this.instructions.add(new IincInsnNode(varIndex, increment));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 416 */     this.instructions.add(new TableSwitchInsnNode(min, max, getLabelNode(dflt), getLabelNodes(labels)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 421 */     this.instructions.add(new LookupSwitchInsnNode(getLabelNode(dflt), keys, getLabelNodes(labels)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 426 */     this.instructions.add(new MultiANewArrayInsnNode(descriptor, numDimensions));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 433 */     AbstractInsnNode currentInsn = this.instructions.getLast();
/* 434 */     while (currentInsn.getOpcode() == -1) {
/* 435 */       currentInsn = currentInsn.getPrevious();
/*     */     }
/*     */     
/* 438 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 439 */     if (visible) {
/* 440 */       currentInsn
/* 441 */         .visibleTypeAnnotations = Util.add(currentInsn.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 443 */       currentInsn
/* 444 */         .invisibleTypeAnnotations = Util.add(currentInsn.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 446 */     return typeAnnotation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/* 453 */     TryCatchBlockNode tryCatchBlock = new TryCatchBlockNode(getLabelNode(start), getLabelNode(end), getLabelNode(handler), type);
/* 454 */     this.tryCatchBlocks = Util.add(this.tryCatchBlocks, tryCatchBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 460 */     TryCatchBlockNode tryCatchBlock = this.tryCatchBlocks.get((typeRef & 0xFFFF00) >> 8);
/* 461 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 462 */     if (visible) {
/* 463 */       tryCatchBlock
/* 464 */         .visibleTypeAnnotations = Util.add(tryCatchBlock.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 466 */       tryCatchBlock
/* 467 */         .invisibleTypeAnnotations = Util.add(tryCatchBlock.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 469 */     return typeAnnotation;
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
/*     */   public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 482 */     LocalVariableNode localVariable = new LocalVariableNode(name, descriptor, signature, getLabelNode(start), getLabelNode(end), index);
/* 483 */     this.localVariables = Util.add(this.localVariables, localVariable);
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
/*     */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 497 */     LocalVariableAnnotationNode localVariableAnnotation = new LocalVariableAnnotationNode(typeRef, typePath, getLabelNodes(start), getLabelNodes(end), index, descriptor);
/* 498 */     if (visible) {
/* 499 */       this
/* 500 */         .visibleLocalVariableAnnotations = Util.add(this.visibleLocalVariableAnnotations, localVariableAnnotation);
/*     */     } else {
/* 502 */       this
/* 503 */         .invisibleLocalVariableAnnotations = Util.add(this.invisibleLocalVariableAnnotations, localVariableAnnotation);
/*     */     } 
/* 505 */     return localVariableAnnotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLineNumber(int line, Label start) {
/* 510 */     this.instructions.add(new LineNumberNode(line, getLabelNode(start)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 515 */     this.maxStack = maxStack;
/* 516 */     this.maxLocals = maxLocals;
/*     */   }
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
/*     */   protected LabelNode getLabelNode(Label label) {
/* 533 */     if (!(label.info instanceof LabelNode)) {
/* 534 */       label.info = new LabelNode();
/*     */     }
/* 536 */     return (LabelNode)label.info;
/*     */   }
/*     */   
/*     */   private LabelNode[] getLabelNodes(Label[] labels) {
/* 540 */     LabelNode[] labelNodes = new LabelNode[labels.length];
/* 541 */     for (int i = 0, n = labels.length; i < n; i++) {
/* 542 */       labelNodes[i] = getLabelNode(labels[i]);
/*     */     }
/* 544 */     return labelNodes;
/*     */   }
/*     */   
/*     */   private Object[] getLabelNodes(Object[] objects) {
/* 548 */     Object[] labelNodes = new Object[objects.length];
/* 549 */     for (int i = 0, n = objects.length; i < n; i++) {
/* 550 */       Object o = objects[i];
/* 551 */       if (o instanceof Label) {
/* 552 */         o = getLabelNode((Label)o);
/*     */       }
/* 554 */       labelNodes[i] = o;
/*     */     } 
/* 556 */     return labelNodes;
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
/*     */   public void check(int api) {
/* 572 */     if (api == 262144) {
/* 573 */       if (this.parameters != null && !this.parameters.isEmpty()) {
/* 574 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 576 */       if (this.visibleTypeAnnotations != null && !this.visibleTypeAnnotations.isEmpty()) {
/* 577 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 579 */       if (this.invisibleTypeAnnotations != null && !this.invisibleTypeAnnotations.isEmpty()) {
/* 580 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 582 */       if (this.tryCatchBlocks != null) {
/* 583 */         for (int j = this.tryCatchBlocks.size() - 1; j >= 0; j--) {
/* 584 */           TryCatchBlockNode tryCatchBlock = this.tryCatchBlocks.get(j);
/* 585 */           if (tryCatchBlock.visibleTypeAnnotations != null && 
/* 586 */             !tryCatchBlock.visibleTypeAnnotations.isEmpty()) {
/* 587 */             throw new UnsupportedClassVersionException();
/*     */           }
/* 589 */           if (tryCatchBlock.invisibleTypeAnnotations != null && 
/* 590 */             !tryCatchBlock.invisibleTypeAnnotations.isEmpty()) {
/* 591 */             throw new UnsupportedClassVersionException();
/*     */           }
/*     */         } 
/*     */       }
/* 595 */       for (int i = this.instructions.size() - 1; i >= 0; i--) {
/* 596 */         AbstractInsnNode insn = this.instructions.get(i);
/* 597 */         if (insn.visibleTypeAnnotations != null && !insn.visibleTypeAnnotations.isEmpty()) {
/* 598 */           throw new UnsupportedClassVersionException();
/*     */         }
/* 600 */         if (insn.invisibleTypeAnnotations != null && !insn.invisibleTypeAnnotations.isEmpty()) {
/* 601 */           throw new UnsupportedClassVersionException();
/*     */         }
/* 603 */         if (insn instanceof MethodInsnNode) {
/* 604 */           boolean isInterface = ((MethodInsnNode)insn).itf;
/* 605 */           if (isInterface != ((insn.opcode == 185))) {
/* 606 */             throw new UnsupportedClassVersionException();
/*     */           }
/* 608 */         } else if (insn instanceof LdcInsnNode) {
/* 609 */           Object value = ((LdcInsnNode)insn).cst;
/* 610 */           if (value instanceof Handle || (value instanceof Type && ((Type)value)
/* 611 */             .getSort() == 11)) {
/* 612 */             throw new UnsupportedClassVersionException();
/*     */           }
/*     */         } 
/*     */       } 
/* 616 */       if (this.visibleLocalVariableAnnotations != null && !this.visibleLocalVariableAnnotations.isEmpty()) {
/* 617 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 619 */       if (this.invisibleLocalVariableAnnotations != null && 
/* 620 */         !this.invisibleLocalVariableAnnotations.isEmpty()) {
/* 621 */         throw new UnsupportedClassVersionException();
/*     */       }
/*     */     } 
/* 624 */     if (api < 458752) {
/* 625 */       for (int i = this.instructions.size() - 1; i >= 0; i--) {
/* 626 */         AbstractInsnNode insn = this.instructions.get(i);
/* 627 */         if (insn instanceof LdcInsnNode) {
/* 628 */           Object value = ((LdcInsnNode)insn).cst;
/* 629 */           if (value instanceof org.jacoco.agent.rt.internal_b5a7c08.asm.ConstantDynamic) {
/* 630 */             throw new UnsupportedClassVersionException();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(ClassVisitor classVisitor) {
/* 643 */     String[] exceptionsArray = (this.exceptions == null) ? null : this.exceptions.<String>toArray(new String[0]);
/*     */     
/* 645 */     MethodVisitor methodVisitor = classVisitor.visitMethod(this.access, this.name, this.desc, this.signature, exceptionsArray);
/* 646 */     if (methodVisitor != null) {
/* 647 */       accept(methodVisitor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 658 */     if (this.parameters != null) {
/* 659 */       for (int i = 0, n = this.parameters.size(); i < n; i++) {
/* 660 */         ((ParameterNode)this.parameters.get(i)).accept(methodVisitor);
/*     */       }
/*     */     }
/*     */     
/* 664 */     if (this.annotationDefault != null) {
/* 665 */       AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
/* 666 */       AnnotationNode.accept(annotationVisitor, null, this.annotationDefault);
/* 667 */       if (annotationVisitor != null) {
/* 668 */         annotationVisitor.visitEnd();
/*     */       }
/*     */     } 
/* 671 */     if (this.visibleAnnotations != null) {
/* 672 */       for (int i = 0, n = this.visibleAnnotations.size(); i < n; i++) {
/* 673 */         AnnotationNode annotation = this.visibleAnnotations.get(i);
/* 674 */         annotation.accept(methodVisitor.visitAnnotation(annotation.desc, true));
/*     */       } 
/*     */     }
/* 677 */     if (this.invisibleAnnotations != null) {
/* 678 */       for (int i = 0, n = this.invisibleAnnotations.size(); i < n; i++) {
/* 679 */         AnnotationNode annotation = this.invisibleAnnotations.get(i);
/* 680 */         annotation.accept(methodVisitor.visitAnnotation(annotation.desc, false));
/*     */       } 
/*     */     }
/* 683 */     if (this.visibleTypeAnnotations != null) {
/* 684 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 685 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 686 */         typeAnnotation.accept(methodVisitor
/* 687 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 691 */     if (this.invisibleTypeAnnotations != null) {
/* 692 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 693 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 694 */         typeAnnotation.accept(methodVisitor
/* 695 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       } 
/*     */     }
/*     */     
/* 699 */     if (this.visibleAnnotableParameterCount > 0) {
/* 700 */       methodVisitor.visitAnnotableParameterCount(this.visibleAnnotableParameterCount, true);
/*     */     }
/* 702 */     if (this.visibleParameterAnnotations != null)
/* 703 */       for (int i = 0, n = this.visibleParameterAnnotations.length; i < n; i++) {
/* 704 */         List<AnnotationNode> parameterAnnotations = this.visibleParameterAnnotations[i];
/* 705 */         if (parameterAnnotations != null)
/*     */         {
/*     */           
/* 708 */           for (int j = 0, m = parameterAnnotations.size(); j < m; j++) {
/* 709 */             AnnotationNode annotation = parameterAnnotations.get(j);
/* 710 */             annotation.accept(methodVisitor.visitParameterAnnotation(i, annotation.desc, true));
/*     */           } 
/*     */         }
/*     */       }  
/* 714 */     if (this.invisibleAnnotableParameterCount > 0) {
/* 715 */       methodVisitor.visitAnnotableParameterCount(this.invisibleAnnotableParameterCount, false);
/*     */     }
/* 717 */     if (this.invisibleParameterAnnotations != null) {
/* 718 */       for (int i = 0, n = this.invisibleParameterAnnotations.length; i < n; i++) {
/* 719 */         List<AnnotationNode> parameterAnnotations = this.invisibleParameterAnnotations[i];
/* 720 */         if (parameterAnnotations != null)
/*     */         {
/*     */           
/* 723 */           for (int j = 0, m = parameterAnnotations.size(); j < m; j++) {
/* 724 */             AnnotationNode annotation = parameterAnnotations.get(j);
/* 725 */             annotation.accept(methodVisitor.visitParameterAnnotation(i, annotation.desc, false));
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/* 730 */     if (this.visited) {
/* 731 */       this.instructions.resetLabels();
/*     */     }
/* 733 */     if (this.attrs != null) {
/* 734 */       for (int i = 0, n = this.attrs.size(); i < n; i++) {
/* 735 */         methodVisitor.visitAttribute(this.attrs.get(i));
/*     */       }
/*     */     }
/*     */     
/* 739 */     if (this.instructions.size() > 0) {
/* 740 */       methodVisitor.visitCode();
/*     */       
/* 742 */       if (this.tryCatchBlocks != null) {
/* 743 */         for (int i = 0, n = this.tryCatchBlocks.size(); i < n; i++) {
/* 744 */           ((TryCatchBlockNode)this.tryCatchBlocks.get(i)).updateIndex(i);
/* 745 */           ((TryCatchBlockNode)this.tryCatchBlocks.get(i)).accept(methodVisitor);
/*     */         } 
/*     */       }
/*     */       
/* 749 */       this.instructions.accept(methodVisitor);
/*     */       
/* 751 */       if (this.localVariables != null) {
/* 752 */         for (int i = 0, n = this.localVariables.size(); i < n; i++) {
/* 753 */           ((LocalVariableNode)this.localVariables.get(i)).accept(methodVisitor);
/*     */         }
/*     */       }
/*     */       
/* 757 */       if (this.visibleLocalVariableAnnotations != null) {
/* 758 */         for (int i = 0, n = this.visibleLocalVariableAnnotations.size(); i < n; i++) {
/* 759 */           ((LocalVariableAnnotationNode)this.visibleLocalVariableAnnotations.get(i)).accept(methodVisitor, true);
/*     */         }
/*     */       }
/* 762 */       if (this.invisibleLocalVariableAnnotations != null) {
/* 763 */         for (int i = 0, n = this.invisibleLocalVariableAnnotations.size(); i < n; i++) {
/* 764 */           ((LocalVariableAnnotationNode)this.invisibleLocalVariableAnnotations.get(i)).accept(methodVisitor, false);
/*     */         }
/*     */       }
/* 767 */       methodVisitor.visitMaxs(this.maxStack, this.maxLocals);
/* 768 */       this.visited = true;
/*     */     } 
/* 770 */     methodVisitor.visitEnd();
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/MethodNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */