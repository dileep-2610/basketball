/*      */ package org.jacoco.agent.rt.internal_b5a7c08.asm;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ final class MethodWriter
/*      */   extends MethodVisitor
/*      */ {
/*      */   static final int COMPUTE_NOTHING = 0;
/*      */   static final int COMPUTE_MAX_STACK_AND_LOCAL = 1;
/*      */   static final int COMPUTE_MAX_STACK_AND_LOCAL_FROM_FRAMES = 2;
/*      */   static final int COMPUTE_INSERTED_FRAMES = 3;
/*      */   static final int COMPUTE_ALL_FRAMES = 4;
/*      */   private static final int NA = 0;
/*   81 */   private static final int[] STACK_SIZE_DELTA = new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 0, 0, 1, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -4, -3, -4, -3, -3, -3, -3, -1, -2, 1, 1, 1, 2, 2, 2, 0, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -2, -1, -2, -1, -2, 0, 1, 0, 1, -1, -1, 0, 0, 1, 1, -1, 0, -1, 0, 0, 0, -3, -1, -1, -3, -3, -1, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, 0, 1, 0, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final SymbolTable symbolTable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int accessFlags;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int nameIndex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final String name;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int descriptorIndex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final String descriptor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int maxStack;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int maxLocals;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  320 */   private final ByteVector code = new ByteVector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Handler firstHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Handler lastHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int lineNumberTableLength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteVector lineNumberTable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int localVariableTableLength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteVector localVariableTable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int localVariableTypeTableLength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteVector localVariableTypeTable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int stackMapTableNumberOfEntries;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteVector stackMapTableEntries;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter lastCodeRuntimeVisibleTypeAnnotation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter lastCodeRuntimeInvisibleTypeAnnotation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Attribute firstCodeAttribute;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int numberOfExceptions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int[] exceptionIndexTable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int signatureIndex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter lastRuntimeVisibleAnnotation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter lastRuntimeInvisibleAnnotation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int visibleAnnotableParameterCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter[] lastRuntimeVisibleParameterAnnotations;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int invisibleAnnotableParameterCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter[] lastRuntimeInvisibleParameterAnnotations;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteVector defaultValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int parametersCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteVector parameters;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Attribute firstAttribute;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int compute;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Label firstBasicBlock;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Label lastBasicBlock;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Label currentBasicBlock;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int relativeStackSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int maxRelativeStackSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int currentLocals;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int previousFrameOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] previousFrame;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] currentFrame;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean hasSubroutines;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean hasAsmInstructions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int lastBytecodeOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int sourceOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int sourceLength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MethodWriter(SymbolTable symbolTable, int access, String name, String descriptor, String signature, String[] exceptions, int compute) {
/*  597 */     super(589824);
/*  598 */     this.symbolTable = symbolTable;
/*  599 */     this.accessFlags = "<init>".equals(name) ? (access | 0x40000) : access;
/*  600 */     this.nameIndex = symbolTable.addConstantUtf8(name);
/*  601 */     this.name = name;
/*  602 */     this.descriptorIndex = symbolTable.addConstantUtf8(descriptor);
/*  603 */     this.descriptor = descriptor;
/*  604 */     this.signatureIndex = (signature == null) ? 0 : symbolTable.addConstantUtf8(signature);
/*  605 */     if (exceptions != null && exceptions.length > 0) {
/*  606 */       this.numberOfExceptions = exceptions.length;
/*  607 */       this.exceptionIndexTable = new int[this.numberOfExceptions];
/*  608 */       for (int i = 0; i < this.numberOfExceptions; i++) {
/*  609 */         this.exceptionIndexTable[i] = (symbolTable.addConstantClass(exceptions[i])).index;
/*      */       }
/*      */     } else {
/*  612 */       this.numberOfExceptions = 0;
/*  613 */       this.exceptionIndexTable = null;
/*      */     } 
/*  615 */     this.compute = compute;
/*  616 */     if (compute != 0) {
/*      */       
/*  618 */       int argumentsSize = Type.getArgumentsAndReturnSizes(descriptor) >> 2;
/*  619 */       if ((access & 0x8) != 0) {
/*  620 */         argumentsSize--;
/*      */       }
/*  622 */       this.maxLocals = argumentsSize;
/*  623 */       this.currentLocals = argumentsSize;
/*      */       
/*  625 */       this.firstBasicBlock = new Label();
/*  626 */       visitLabel(this.firstBasicBlock);
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean hasFrames() {
/*  631 */     return (this.stackMapTableNumberOfEntries > 0);
/*      */   }
/*      */   
/*      */   boolean hasAsmInstructions() {
/*  635 */     return this.hasAsmInstructions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitParameter(String name, int access) {
/*  644 */     if (this.parameters == null) {
/*  645 */       this.parameters = new ByteVector();
/*      */     }
/*  647 */     this.parametersCount++;
/*  648 */     this.parameters.putShort((name == null) ? 0 : this.symbolTable.addConstantUtf8(name)).putShort(access);
/*      */   }
/*      */ 
/*      */   
/*      */   public AnnotationVisitor visitAnnotationDefault() {
/*  653 */     this.defaultValue = new ByteVector();
/*  654 */     return new AnnotationWriter(this.symbolTable, false, this.defaultValue, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/*  659 */     if (visible) {
/*  660 */       return this
/*  661 */         .lastRuntimeVisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeVisibleAnnotation);
/*      */     }
/*  663 */     return this
/*  664 */       .lastRuntimeInvisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeInvisibleAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/*  671 */     if (visible) {
/*  672 */       return this
/*  673 */         .lastRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeVisibleTypeAnnotation);
/*      */     }
/*      */     
/*  676 */     return this
/*  677 */       .lastRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeInvisibleTypeAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
/*  684 */     if (visible) {
/*  685 */       this.visibleAnnotableParameterCount = parameterCount;
/*      */     } else {
/*  687 */       this.invisibleAnnotableParameterCount = parameterCount;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AnnotationVisitor visitParameterAnnotation(int parameter, String annotationDescriptor, boolean visible) {
/*  694 */     if (visible) {
/*  695 */       if (this.lastRuntimeVisibleParameterAnnotations == null) {
/*  696 */         this
/*  697 */           .lastRuntimeVisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentCount(this.descriptor)];
/*      */       }
/*  699 */       this.lastRuntimeVisibleParameterAnnotations[parameter] = 
/*  700 */         AnnotationWriter.create(this.symbolTable, annotationDescriptor, this.lastRuntimeVisibleParameterAnnotations[parameter]); return AnnotationWriter.create(this.symbolTable, annotationDescriptor, this.lastRuntimeVisibleParameterAnnotations[parameter]);
/*      */     } 
/*      */     
/*  703 */     if (this.lastRuntimeInvisibleParameterAnnotations == null) {
/*  704 */       this
/*  705 */         .lastRuntimeInvisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentCount(this.descriptor)];
/*      */     }
/*  707 */     this.lastRuntimeInvisibleParameterAnnotations[parameter] = 
/*  708 */       AnnotationWriter.create(this.symbolTable, annotationDescriptor, this.lastRuntimeInvisibleParameterAnnotations[parameter]); return AnnotationWriter.create(this.symbolTable, annotationDescriptor, this.lastRuntimeInvisibleParameterAnnotations[parameter]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitAttribute(Attribute attribute) {
/*  718 */     if (attribute.isCodeAttribute()) {
/*  719 */       attribute.nextAttribute = this.firstCodeAttribute;
/*  720 */       this.firstCodeAttribute = attribute;
/*      */     } else {
/*  722 */       attribute.nextAttribute = this.firstAttribute;
/*  723 */       this.firstAttribute = attribute;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitCode() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/*  739 */     if (this.compute == 4) {
/*      */       return;
/*      */     }
/*      */     
/*  743 */     if (this.compute == 3) {
/*  744 */       if (this.currentBasicBlock.frame == null) {
/*      */ 
/*      */ 
/*      */         
/*  748 */         this.currentBasicBlock.frame = new CurrentFrame(this.currentBasicBlock);
/*  749 */         this.currentBasicBlock.frame.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, numLocal);
/*      */         
/*  751 */         this.currentBasicBlock.frame.accept(this);
/*      */       } else {
/*  753 */         if (type == -1) {
/*  754 */           this.currentBasicBlock.frame.setInputFrameFromApiFormat(this.symbolTable, numLocal, local, numStack, stack);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  760 */         this.currentBasicBlock.frame.accept(this);
/*      */       } 
/*  762 */     } else if (type == -1) {
/*  763 */       if (this.previousFrame == null) {
/*  764 */         int argumentsSize = Type.getArgumentsAndReturnSizes(this.descriptor) >> 2;
/*  765 */         Frame implicitFirstFrame = new Frame(new Label());
/*  766 */         implicitFirstFrame.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, argumentsSize);
/*      */         
/*  768 */         implicitFirstFrame.accept(this);
/*      */       } 
/*  770 */       this.currentLocals = numLocal;
/*  771 */       int frameIndex = visitFrameStart(this.code.length, numLocal, numStack); int i;
/*  772 */       for (i = 0; i < numLocal; i++) {
/*  773 */         this.currentFrame[frameIndex++] = Frame.getAbstractTypeFromApiFormat(this.symbolTable, local[i]);
/*      */       }
/*  775 */       for (i = 0; i < numStack; i++) {
/*  776 */         this.currentFrame[frameIndex++] = Frame.getAbstractTypeFromApiFormat(this.symbolTable, stack[i]);
/*      */       }
/*  778 */       visitFrameEnd();
/*      */     } else {
/*  780 */       int offsetDelta, i; if (this.symbolTable.getMajorVersion() < 50) {
/*  781 */         throw new IllegalArgumentException("Class versions V1_5 or less must use F_NEW frames.");
/*      */       }
/*      */       
/*  784 */       if (this.stackMapTableEntries == null) {
/*  785 */         this.stackMapTableEntries = new ByteVector();
/*  786 */         offsetDelta = this.code.length;
/*      */       } else {
/*  788 */         offsetDelta = this.code.length - this.previousFrameOffset - 1;
/*  789 */         if (offsetDelta < 0) {
/*  790 */           if (type == 3) {
/*      */             return;
/*      */           }
/*  793 */           throw new IllegalStateException();
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  798 */       switch (type) {
/*      */         case 0:
/*  800 */           this.currentLocals = numLocal;
/*  801 */           this.stackMapTableEntries.putByte(255).putShort(offsetDelta).putShort(numLocal);
/*  802 */           for (i = 0; i < numLocal; i++) {
/*  803 */             putFrameType(local[i]);
/*      */           }
/*  805 */           this.stackMapTableEntries.putShort(numStack);
/*  806 */           for (i = 0; i < numStack; i++) {
/*  807 */             putFrameType(stack[i]);
/*      */           }
/*      */           break;
/*      */         case 1:
/*  811 */           this.currentLocals += numLocal;
/*  812 */           this.stackMapTableEntries.putByte(251 + numLocal).putShort(offsetDelta);
/*  813 */           for (i = 0; i < numLocal; i++) {
/*  814 */             putFrameType(local[i]);
/*      */           }
/*      */           break;
/*      */         case 2:
/*  818 */           this.currentLocals -= numLocal;
/*  819 */           this.stackMapTableEntries.putByte(251 - numLocal).putShort(offsetDelta);
/*      */           break;
/*      */         case 3:
/*  822 */           if (offsetDelta < 64) {
/*  823 */             this.stackMapTableEntries.putByte(offsetDelta); break;
/*      */           } 
/*  825 */           this.stackMapTableEntries.putByte(251).putShort(offsetDelta);
/*      */           break;
/*      */         
/*      */         case 4:
/*  829 */           if (offsetDelta < 64) {
/*  830 */             this.stackMapTableEntries.putByte(64 + offsetDelta);
/*      */           } else {
/*  832 */             this.stackMapTableEntries
/*  833 */               .putByte(247)
/*  834 */               .putShort(offsetDelta);
/*      */           } 
/*  836 */           putFrameType(stack[0]);
/*      */           break;
/*      */         default:
/*  839 */           throw new IllegalArgumentException();
/*      */       } 
/*      */       
/*  842 */       this.previousFrameOffset = this.code.length;
/*  843 */       this.stackMapTableNumberOfEntries++;
/*      */     } 
/*      */     
/*  846 */     if (this.compute == 2) {
/*  847 */       this.relativeStackSize = numStack;
/*  848 */       for (int i = 0; i < numStack; i++) {
/*  849 */         if (stack[i] == Opcodes.LONG || stack[i] == Opcodes.DOUBLE) {
/*  850 */           this.relativeStackSize++;
/*      */         }
/*      */       } 
/*  853 */       if (this.relativeStackSize > this.maxRelativeStackSize) {
/*  854 */         this.maxRelativeStackSize = this.relativeStackSize;
/*      */       }
/*      */     } 
/*      */     
/*  858 */     this.maxStack = Math.max(this.maxStack, numStack);
/*  859 */     this.maxLocals = Math.max(this.maxLocals, this.currentLocals);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitInsn(int opcode) {
/*  864 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/*  866 */     this.code.putByte(opcode);
/*      */     
/*  868 */     if (this.currentBasicBlock != null) {
/*  869 */       if (this.compute == 4 || this.compute == 3) {
/*  870 */         this.currentBasicBlock.frame.execute(opcode, 0, null, null);
/*      */       } else {
/*  872 */         int size = this.relativeStackSize + STACK_SIZE_DELTA[opcode];
/*  873 */         if (size > this.maxRelativeStackSize) {
/*  874 */           this.maxRelativeStackSize = size;
/*      */         }
/*  876 */         this.relativeStackSize = size;
/*      */       } 
/*  878 */       if ((opcode >= 172 && opcode <= 177) || opcode == 191) {
/*  879 */         endCurrentBasicBlockWithNoSuccessor();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitIntInsn(int opcode, int operand) {
/*  886 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/*  888 */     if (opcode == 17) {
/*  889 */       this.code.put12(opcode, operand);
/*      */     } else {
/*  891 */       this.code.put11(opcode, operand);
/*      */     } 
/*      */     
/*  894 */     if (this.currentBasicBlock != null) {
/*  895 */       if (this.compute == 4 || this.compute == 3) {
/*  896 */         this.currentBasicBlock.frame.execute(opcode, operand, null, null);
/*  897 */       } else if (opcode != 188) {
/*      */         
/*  899 */         int size = this.relativeStackSize + 1;
/*  900 */         if (size > this.maxRelativeStackSize) {
/*  901 */           this.maxRelativeStackSize = size;
/*      */         }
/*  903 */         this.relativeStackSize = size;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitVarInsn(int opcode, int varIndex) {
/*  910 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/*  912 */     if (varIndex < 4 && opcode != 169) {
/*      */       int optimizedOpcode;
/*  914 */       if (opcode < 54) {
/*  915 */         optimizedOpcode = 26 + (opcode - 21 << 2) + varIndex;
/*      */       } else {
/*  917 */         optimizedOpcode = 59 + (opcode - 54 << 2) + varIndex;
/*      */       } 
/*  919 */       this.code.putByte(optimizedOpcode);
/*  920 */     } else if (varIndex >= 256) {
/*  921 */       this.code.putByte(196).put12(opcode, varIndex);
/*      */     } else {
/*  923 */       this.code.put11(opcode, varIndex);
/*      */     } 
/*      */     
/*  926 */     if (this.currentBasicBlock != null) {
/*  927 */       if (this.compute == 4 || this.compute == 3) {
/*  928 */         this.currentBasicBlock.frame.execute(opcode, varIndex, null, null);
/*      */       }
/*  930 */       else if (opcode == 169) {
/*      */         
/*  932 */         this.currentBasicBlock.flags = (short)(this.currentBasicBlock.flags | 0x40);
/*  933 */         this.currentBasicBlock.outputStackSize = (short)this.relativeStackSize;
/*  934 */         endCurrentBasicBlockWithNoSuccessor();
/*      */       } else {
/*  936 */         int size = this.relativeStackSize + STACK_SIZE_DELTA[opcode];
/*  937 */         if (size > this.maxRelativeStackSize) {
/*  938 */           this.maxRelativeStackSize = size;
/*      */         }
/*  940 */         this.relativeStackSize = size;
/*      */       } 
/*      */     }
/*      */     
/*  944 */     if (this.compute != 0) {
/*      */       int currentMaxLocals;
/*  946 */       if (opcode == 22 || opcode == 24 || opcode == 55 || opcode == 57) {
/*      */ 
/*      */ 
/*      */         
/*  950 */         currentMaxLocals = varIndex + 2;
/*      */       } else {
/*  952 */         currentMaxLocals = varIndex + 1;
/*      */       } 
/*  954 */       if (currentMaxLocals > this.maxLocals) {
/*  955 */         this.maxLocals = currentMaxLocals;
/*      */       }
/*      */     } 
/*  958 */     if (opcode >= 54 && this.compute == 4 && this.firstHandler != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  966 */       visitLabel(new Label());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitTypeInsn(int opcode, String type) {
/*  972 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/*  974 */     Symbol typeSymbol = this.symbolTable.addConstantClass(type);
/*  975 */     this.code.put12(opcode, typeSymbol.index);
/*      */     
/*  977 */     if (this.currentBasicBlock != null) {
/*  978 */       if (this.compute == 4 || this.compute == 3) {
/*  979 */         this.currentBasicBlock.frame.execute(opcode, this.lastBytecodeOffset, typeSymbol, this.symbolTable);
/*  980 */       } else if (opcode == 187) {
/*      */         
/*  982 */         int size = this.relativeStackSize + 1;
/*  983 */         if (size > this.maxRelativeStackSize) {
/*  984 */           this.maxRelativeStackSize = size;
/*      */         }
/*  986 */         this.relativeStackSize = size;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/*  994 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/*  996 */     Symbol fieldrefSymbol = this.symbolTable.addConstantFieldref(owner, name, descriptor);
/*  997 */     this.code.put12(opcode, fieldrefSymbol.index);
/*      */     
/*  999 */     if (this.currentBasicBlock != null) {
/* 1000 */       if (this.compute == 4 || this.compute == 3) {
/* 1001 */         this.currentBasicBlock.frame.execute(opcode, 0, fieldrefSymbol, this.symbolTable);
/*      */       } else {
/*      */         int size;
/* 1004 */         char firstDescChar = descriptor.charAt(0);
/* 1005 */         switch (opcode) {
/*      */           case 178:
/* 1007 */             size = this.relativeStackSize + ((firstDescChar == 'D' || firstDescChar == 'J') ? 2 : 1);
/*      */             break;
/*      */           case 179:
/* 1010 */             size = this.relativeStackSize + ((firstDescChar == 'D' || firstDescChar == 'J') ? -2 : -1);
/*      */             break;
/*      */           case 180:
/* 1013 */             size = this.relativeStackSize + ((firstDescChar == 'D' || firstDescChar == 'J') ? 1 : 0);
/*      */             break;
/*      */           
/*      */           default:
/* 1017 */             size = this.relativeStackSize + ((firstDescChar == 'D' || firstDescChar == 'J') ? -3 : -2);
/*      */             break;
/*      */         } 
/* 1020 */         if (size > this.maxRelativeStackSize) {
/* 1021 */           this.maxRelativeStackSize = size;
/*      */         }
/* 1023 */         this.relativeStackSize = size;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
/* 1035 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/* 1037 */     Symbol methodrefSymbol = this.symbolTable.addConstantMethodref(owner, name, descriptor, isInterface);
/* 1038 */     if (opcode == 185) {
/* 1039 */       this.code.put12(185, methodrefSymbol.index)
/* 1040 */         .put11(methodrefSymbol.getArgumentsAndReturnSizes() >> 2, 0);
/*      */     } else {
/* 1042 */       this.code.put12(opcode, methodrefSymbol.index);
/*      */     } 
/*      */     
/* 1045 */     if (this.currentBasicBlock != null) {
/* 1046 */       if (this.compute == 4 || this.compute == 3) {
/* 1047 */         this.currentBasicBlock.frame.execute(opcode, 0, methodrefSymbol, this.symbolTable);
/*      */       } else {
/* 1049 */         int size, argumentsAndReturnSize = methodrefSymbol.getArgumentsAndReturnSizes();
/* 1050 */         int stackSizeDelta = (argumentsAndReturnSize & 0x3) - (argumentsAndReturnSize >> 2);
/*      */         
/* 1052 */         if (opcode == 184) {
/* 1053 */           size = this.relativeStackSize + stackSizeDelta + 1;
/*      */         } else {
/* 1055 */           size = this.relativeStackSize + stackSizeDelta;
/*      */         } 
/* 1057 */         if (size > this.maxRelativeStackSize) {
/* 1058 */           this.maxRelativeStackSize = size;
/*      */         }
/* 1060 */         this.relativeStackSize = size;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 1071 */     this.lastBytecodeOffset = this.code.length;
/*      */ 
/*      */     
/* 1074 */     Symbol invokeDynamicSymbol = this.symbolTable.addConstantInvokeDynamic(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
/*      */     
/* 1076 */     this.code.put12(186, invokeDynamicSymbol.index);
/* 1077 */     this.code.putShort(0);
/*      */     
/* 1079 */     if (this.currentBasicBlock != null) {
/* 1080 */       if (this.compute == 4 || this.compute == 3) {
/* 1081 */         this.currentBasicBlock.frame.execute(186, 0, invokeDynamicSymbol, this.symbolTable);
/*      */       } else {
/* 1083 */         int argumentsAndReturnSize = invokeDynamicSymbol.getArgumentsAndReturnSizes();
/* 1084 */         int stackSizeDelta = (argumentsAndReturnSize & 0x3) - (argumentsAndReturnSize >> 2) + 1;
/* 1085 */         int size = this.relativeStackSize + stackSizeDelta;
/* 1086 */         if (size > this.maxRelativeStackSize) {
/* 1087 */           this.maxRelativeStackSize = size;
/*      */         }
/* 1089 */         this.relativeStackSize = size;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitJumpInsn(int opcode, Label label) {
/* 1096 */     this.lastBytecodeOffset = this.code.length;
/*      */ 
/*      */ 
/*      */     
/* 1100 */     int baseOpcode = (opcode >= 200) ? (opcode - 33) : opcode;
/* 1101 */     boolean nextInsnIsJumpTarget = false;
/* 1102 */     if ((label.flags & 0x4) != 0 && label.bytecodeOffset - this.code.length < -32768) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1108 */       if (baseOpcode == 167) {
/* 1109 */         this.code.putByte(200);
/* 1110 */       } else if (baseOpcode == 168) {
/* 1111 */         this.code.putByte(201);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1116 */         this.code.putByte((baseOpcode >= 198) ? (baseOpcode ^ 0x1) : ((baseOpcode + 1 ^ 0x1) - 1));
/* 1117 */         this.code.putShort(8);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1124 */         this.code.putByte(220);
/* 1125 */         this.hasAsmInstructions = true;
/*      */         
/* 1127 */         nextInsnIsJumpTarget = true;
/*      */       } 
/* 1129 */       label.put(this.code, this.code.length - 1, true);
/* 1130 */     } else if (baseOpcode != opcode) {
/*      */ 
/*      */       
/* 1133 */       this.code.putByte(opcode);
/* 1134 */       label.put(this.code, this.code.length - 1, true);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1139 */       this.code.putByte(baseOpcode);
/* 1140 */       label.put(this.code, this.code.length - 1, false);
/*      */     } 
/*      */ 
/*      */     
/* 1144 */     if (this.currentBasicBlock != null) {
/* 1145 */       Label nextBasicBlock = null;
/* 1146 */       if (this.compute == 4) {
/* 1147 */         this.currentBasicBlock.frame.execute(baseOpcode, 0, null, null);
/*      */         
/* 1149 */         (label.getCanonicalInstance()).flags = (short)((label.getCanonicalInstance()).flags | 0x2);
/*      */         
/* 1151 */         addSuccessorToCurrentBasicBlock(0, label);
/* 1152 */         if (baseOpcode != 167)
/*      */         {
/*      */ 
/*      */           
/* 1156 */           nextBasicBlock = new Label();
/*      */         }
/* 1158 */       } else if (this.compute == 3) {
/* 1159 */         this.currentBasicBlock.frame.execute(baseOpcode, 0, null, null);
/* 1160 */       } else if (this.compute == 2) {
/*      */         
/* 1162 */         this.relativeStackSize += STACK_SIZE_DELTA[baseOpcode];
/*      */       }
/* 1164 */       else if (baseOpcode == 168) {
/*      */         
/* 1166 */         if ((label.flags & 0x20) == 0) {
/* 1167 */           label.flags = (short)(label.flags | 0x20);
/* 1168 */           this.hasSubroutines = true;
/*      */         } 
/* 1170 */         this.currentBasicBlock.flags = (short)(this.currentBasicBlock.flags | 0x10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1177 */         addSuccessorToCurrentBasicBlock(this.relativeStackSize + 1, label);
/*      */         
/* 1179 */         nextBasicBlock = new Label();
/*      */       } else {
/*      */         
/* 1182 */         this.relativeStackSize += STACK_SIZE_DELTA[baseOpcode];
/* 1183 */         addSuccessorToCurrentBasicBlock(this.relativeStackSize, label);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1188 */       if (nextBasicBlock != null) {
/* 1189 */         if (nextInsnIsJumpTarget) {
/* 1190 */           nextBasicBlock.flags = (short)(nextBasicBlock.flags | 0x2);
/*      */         }
/* 1192 */         visitLabel(nextBasicBlock);
/*      */       } 
/* 1194 */       if (baseOpcode == 167) {
/* 1195 */         endCurrentBasicBlockWithNoSuccessor();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLabel(Label label) {
/* 1203 */     this.hasAsmInstructions |= label.resolve(this.code.data, this.stackMapTableEntries, this.code.length);
/*      */ 
/*      */     
/* 1206 */     if ((label.flags & 0x1) != 0) {
/*      */       return;
/*      */     }
/* 1209 */     if (this.compute == 4) {
/* 1210 */       if (this.currentBasicBlock != null) {
/* 1211 */         if (label.bytecodeOffset == this.currentBasicBlock.bytecodeOffset) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1216 */           this.currentBasicBlock.flags = (short)(this.currentBasicBlock.flags | label.flags & 0x2);
/*      */ 
/*      */ 
/*      */           
/* 1220 */           label.frame = this.currentBasicBlock.frame;
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1226 */         addSuccessorToCurrentBasicBlock(0, label);
/*      */       } 
/*      */       
/* 1229 */       if (this.lastBasicBlock != null) {
/* 1230 */         if (label.bytecodeOffset == this.lastBasicBlock.bytecodeOffset) {
/*      */           
/* 1232 */           this.lastBasicBlock.flags = (short)(this.lastBasicBlock.flags | label.flags & 0x2);
/*      */           
/* 1234 */           label.frame = this.lastBasicBlock.frame;
/* 1235 */           this.currentBasicBlock = this.lastBasicBlock;
/*      */           return;
/*      */         } 
/* 1238 */         this.lastBasicBlock.nextBasicBlock = label;
/*      */       } 
/* 1240 */       this.lastBasicBlock = label;
/*      */       
/* 1242 */       this.currentBasicBlock = label;
/*      */       
/* 1244 */       label.frame = new Frame(label);
/* 1245 */     } else if (this.compute == 3) {
/* 1246 */       if (this.currentBasicBlock == null) {
/*      */ 
/*      */         
/* 1249 */         this.currentBasicBlock = label;
/*      */       } else {
/*      */         
/* 1252 */         this.currentBasicBlock.frame.owner = label;
/*      */       } 
/* 1254 */     } else if (this.compute == 1) {
/* 1255 */       if (this.currentBasicBlock != null) {
/*      */         
/* 1257 */         this.currentBasicBlock.outputStackMax = (short)this.maxRelativeStackSize;
/* 1258 */         addSuccessorToCurrentBasicBlock(this.relativeStackSize, label);
/*      */       } 
/*      */       
/* 1261 */       this.currentBasicBlock = label;
/* 1262 */       this.relativeStackSize = 0;
/* 1263 */       this.maxRelativeStackSize = 0;
/*      */       
/* 1265 */       if (this.lastBasicBlock != null) {
/* 1266 */         this.lastBasicBlock.nextBasicBlock = label;
/*      */       }
/* 1268 */       this.lastBasicBlock = label;
/* 1269 */     } else if (this.compute == 2 && this.currentBasicBlock == null) {
/*      */ 
/*      */ 
/*      */       
/* 1273 */       this.currentBasicBlock = label;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitLdcInsn(Object value) {
/* 1279 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/* 1281 */     Symbol constantSymbol = this.symbolTable.addConstant(value);
/* 1282 */     int constantIndex = constantSymbol.index;
/*      */ 
/*      */     
/*      */     char firstDescriptorChar;
/*      */ 
/*      */     
/* 1288 */     boolean isLongOrDouble = (constantSymbol.tag == 5 || constantSymbol.tag == 6 || (constantSymbol.tag == 17 && ((firstDescriptorChar = constantSymbol.value.charAt(0)) == 'J' || firstDescriptorChar == 'D')));
/*      */     
/* 1290 */     if (isLongOrDouble) {
/* 1291 */       this.code.put12(20, constantIndex);
/* 1292 */     } else if (constantIndex >= 256) {
/* 1293 */       this.code.put12(19, constantIndex);
/*      */     } else {
/* 1295 */       this.code.put11(18, constantIndex);
/*      */     } 
/*      */     
/* 1298 */     if (this.currentBasicBlock != null) {
/* 1299 */       if (this.compute == 4 || this.compute == 3) {
/* 1300 */         this.currentBasicBlock.frame.execute(18, 0, constantSymbol, this.symbolTable);
/*      */       } else {
/* 1302 */         int size = this.relativeStackSize + (isLongOrDouble ? 2 : 1);
/* 1303 */         if (size > this.maxRelativeStackSize) {
/* 1304 */           this.maxRelativeStackSize = size;
/*      */         }
/* 1306 */         this.relativeStackSize = size;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitIincInsn(int varIndex, int increment) {
/* 1313 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/* 1315 */     if (varIndex > 255 || increment > 127 || increment < -128) {
/* 1316 */       this.code.putByte(196).put12(132, varIndex).putShort(increment);
/*      */     } else {
/* 1318 */       this.code.putByte(132).put11(varIndex, increment);
/*      */     } 
/*      */     
/* 1321 */     if (this.currentBasicBlock != null && (this.compute == 4 || this.compute == 3))
/*      */     {
/* 1323 */       this.currentBasicBlock.frame.execute(132, varIndex, null, null);
/*      */     }
/* 1325 */     if (this.compute != 0) {
/* 1326 */       int currentMaxLocals = varIndex + 1;
/* 1327 */       if (currentMaxLocals > this.maxLocals) {
/* 1328 */         this.maxLocals = currentMaxLocals;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 1336 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/* 1338 */     this.code.putByte(170).putByteArray(null, 0, (4 - this.code.length % 4) % 4);
/* 1339 */     dflt.put(this.code, this.lastBytecodeOffset, true);
/* 1340 */     this.code.putInt(min).putInt(max);
/* 1341 */     for (Label label : labels) {
/* 1342 */       label.put(this.code, this.lastBytecodeOffset, true);
/*      */     }
/*      */     
/* 1345 */     visitSwitchInsn(dflt, labels);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 1350 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/* 1352 */     this.code.putByte(171).putByteArray(null, 0, (4 - this.code.length % 4) % 4);
/* 1353 */     dflt.put(this.code, this.lastBytecodeOffset, true);
/* 1354 */     this.code.putInt(labels.length);
/* 1355 */     for (int i = 0; i < labels.length; i++) {
/* 1356 */       this.code.putInt(keys[i]);
/* 1357 */       labels[i].put(this.code, this.lastBytecodeOffset, true);
/*      */     } 
/*      */     
/* 1360 */     visitSwitchInsn(dflt, labels);
/*      */   }
/*      */   
/*      */   private void visitSwitchInsn(Label dflt, Label[] labels) {
/* 1364 */     if (this.currentBasicBlock != null) {
/* 1365 */       if (this.compute == 4) {
/* 1366 */         this.currentBasicBlock.frame.execute(171, 0, null, null);
/*      */         
/* 1368 */         addSuccessorToCurrentBasicBlock(0, dflt);
/* 1369 */         (dflt.getCanonicalInstance()).flags = (short)((dflt.getCanonicalInstance()).flags | 0x2);
/* 1370 */         for (Label label : labels) {
/* 1371 */           addSuccessorToCurrentBasicBlock(0, label);
/* 1372 */           (label.getCanonicalInstance()).flags = (short)((label.getCanonicalInstance()).flags | 0x2);
/*      */         } 
/* 1374 */       } else if (this.compute == 1) {
/*      */         
/* 1376 */         this.relativeStackSize--;
/*      */         
/* 1378 */         addSuccessorToCurrentBasicBlock(this.relativeStackSize, dflt);
/* 1379 */         for (Label label : labels) {
/* 1380 */           addSuccessorToCurrentBasicBlock(this.relativeStackSize, label);
/*      */         }
/*      */       } 
/*      */       
/* 1384 */       endCurrentBasicBlockWithNoSuccessor();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 1390 */     this.lastBytecodeOffset = this.code.length;
/*      */     
/* 1392 */     Symbol descSymbol = this.symbolTable.addConstantClass(descriptor);
/* 1393 */     this.code.put12(197, descSymbol.index).putByte(numDimensions);
/*      */     
/* 1395 */     if (this.currentBasicBlock != null) {
/* 1396 */       if (this.compute == 4 || this.compute == 3) {
/* 1397 */         this.currentBasicBlock.frame.execute(197, numDimensions, descSymbol, this.symbolTable);
/*      */       }
/*      */       else {
/*      */         
/* 1401 */         this.relativeStackSize += 1 - numDimensions;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 1409 */     if (visible) {
/* 1410 */       return this
/* 1411 */         .lastCodeRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef & 0xFF0000FF | this.lastBytecodeOffset << 8, typePath, descriptor, this.lastCodeRuntimeVisibleTypeAnnotation);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1418 */     return this
/* 1419 */       .lastCodeRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef & 0xFF0000FF | this.lastBytecodeOffset << 8, typePath, descriptor, this.lastCodeRuntimeInvisibleTypeAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/* 1433 */     Handler newHandler = new Handler(start, end, handler, (type != null) ? (this.symbolTable.addConstantClass(type)).index : 0, type);
/* 1434 */     if (this.firstHandler == null) {
/* 1435 */       this.firstHandler = newHandler;
/*      */     } else {
/* 1437 */       this.lastHandler.nextHandler = newHandler;
/*      */     } 
/* 1439 */     this.lastHandler = newHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 1445 */     if (visible) {
/* 1446 */       return this
/* 1447 */         .lastCodeRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastCodeRuntimeVisibleTypeAnnotation);
/*      */     }
/*      */     
/* 1450 */     return this
/* 1451 */       .lastCodeRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastCodeRuntimeInvisibleTypeAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 1464 */     if (signature != null) {
/* 1465 */       if (this.localVariableTypeTable == null) {
/* 1466 */         this.localVariableTypeTable = new ByteVector();
/*      */       }
/* 1468 */       this.localVariableTypeTableLength++;
/* 1469 */       this.localVariableTypeTable
/* 1470 */         .putShort(start.bytecodeOffset)
/* 1471 */         .putShort(end.bytecodeOffset - start.bytecodeOffset)
/* 1472 */         .putShort(this.symbolTable.addConstantUtf8(name))
/* 1473 */         .putShort(this.symbolTable.addConstantUtf8(signature))
/* 1474 */         .putShort(index);
/*      */     } 
/* 1476 */     if (this.localVariableTable == null) {
/* 1477 */       this.localVariableTable = new ByteVector();
/*      */     }
/* 1479 */     this.localVariableTableLength++;
/* 1480 */     this.localVariableTable
/* 1481 */       .putShort(start.bytecodeOffset)
/* 1482 */       .putShort(end.bytecodeOffset - start.bytecodeOffset)
/* 1483 */       .putShort(this.symbolTable.addConstantUtf8(name))
/* 1484 */       .putShort(this.symbolTable.addConstantUtf8(descriptor))
/* 1485 */       .putShort(index);
/* 1486 */     if (this.compute != 0) {
/* 1487 */       char firstDescChar = descriptor.charAt(0);
/* 1488 */       int currentMaxLocals = index + ((firstDescChar == 'J' || firstDescChar == 'D') ? 2 : 1);
/* 1489 */       if (currentMaxLocals > this.maxLocals) {
/* 1490 */         this.maxLocals = currentMaxLocals;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 1506 */     ByteVector typeAnnotation = new ByteVector();
/*      */     
/* 1508 */     typeAnnotation.putByte(typeRef >>> 24).putShort(start.length);
/* 1509 */     for (int i = 0; i < start.length; i++) {
/* 1510 */       typeAnnotation
/* 1511 */         .putShort((start[i]).bytecodeOffset)
/* 1512 */         .putShort((end[i]).bytecodeOffset - (start[i]).bytecodeOffset)
/* 1513 */         .putShort(index[i]);
/*      */     }
/* 1515 */     TypePath.put(typePath, typeAnnotation);
/*      */     
/* 1517 */     typeAnnotation.putShort(this.symbolTable.addConstantUtf8(descriptor)).putShort(0);
/* 1518 */     if (visible) {
/* 1519 */       return this.lastCodeRuntimeVisibleTypeAnnotation = new AnnotationWriter(this.symbolTable, true, typeAnnotation, this.lastCodeRuntimeVisibleTypeAnnotation);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1526 */     return this.lastCodeRuntimeInvisibleTypeAnnotation = new AnnotationWriter(this.symbolTable, true, typeAnnotation, this.lastCodeRuntimeInvisibleTypeAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLineNumber(int line, Label start) {
/* 1537 */     if (this.lineNumberTable == null) {
/* 1538 */       this.lineNumberTable = new ByteVector();
/*      */     }
/* 1540 */     this.lineNumberTableLength++;
/* 1541 */     this.lineNumberTable.putShort(start.bytecodeOffset);
/* 1542 */     this.lineNumberTable.putShort(line);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitMaxs(int maxStack, int maxLocals) {
/* 1547 */     if (this.compute == 4) {
/* 1548 */       computeAllFrames();
/* 1549 */     } else if (this.compute == 1) {
/* 1550 */       computeMaxStackAndLocal();
/* 1551 */     } else if (this.compute == 2) {
/* 1552 */       this.maxStack = this.maxRelativeStackSize;
/*      */     } else {
/* 1554 */       this.maxStack = maxStack;
/* 1555 */       this.maxLocals = maxLocals;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeAllFrames() {
/* 1562 */     Handler handler = this.firstHandler;
/* 1563 */     while (handler != null) {
/*      */       
/* 1565 */       String catchTypeDescriptor = (handler.catchTypeDescriptor == null) ? "java/lang/Throwable" : handler.catchTypeDescriptor;
/* 1566 */       int catchType = Frame.getAbstractTypeFromInternalName(this.symbolTable, catchTypeDescriptor);
/*      */       
/* 1568 */       Label handlerBlock = handler.handlerPc.getCanonicalInstance();
/* 1569 */       handlerBlock.flags = (short)(handlerBlock.flags | 0x2);
/*      */       
/* 1571 */       Label handlerRangeBlock = handler.startPc.getCanonicalInstance();
/* 1572 */       Label handlerRangeEnd = handler.endPc.getCanonicalInstance();
/* 1573 */       while (handlerRangeBlock != handlerRangeEnd) {
/* 1574 */         handlerRangeBlock.outgoingEdges = new Edge(catchType, handlerBlock, handlerRangeBlock.outgoingEdges);
/*      */         
/* 1576 */         handlerRangeBlock = handlerRangeBlock.nextBasicBlock;
/*      */       } 
/* 1578 */       handler = handler.nextHandler;
/*      */     } 
/*      */ 
/*      */     
/* 1582 */     Frame firstFrame = this.firstBasicBlock.frame;
/* 1583 */     firstFrame.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, this.maxLocals);
/* 1584 */     firstFrame.accept(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1592 */     Label listOfBlocksToProcess = this.firstBasicBlock;
/* 1593 */     listOfBlocksToProcess.nextListElement = Label.EMPTY_LIST;
/* 1594 */     int maxStackSize = 0;
/* 1595 */     while (listOfBlocksToProcess != Label.EMPTY_LIST) {
/*      */       
/* 1597 */       Label label = listOfBlocksToProcess;
/* 1598 */       listOfBlocksToProcess = listOfBlocksToProcess.nextListElement;
/* 1599 */       label.nextListElement = null;
/*      */       
/* 1601 */       label.flags = (short)(label.flags | 0x8);
/*      */       
/* 1603 */       int maxBlockStackSize = label.frame.getInputStackSize() + label.outputStackMax;
/* 1604 */       if (maxBlockStackSize > maxStackSize) {
/* 1605 */         maxStackSize = maxBlockStackSize;
/*      */       }
/*      */       
/* 1608 */       Edge outgoingEdge = label.outgoingEdges;
/* 1609 */       while (outgoingEdge != null) {
/* 1610 */         Label successorBlock = outgoingEdge.successor.getCanonicalInstance();
/*      */         
/* 1612 */         boolean successorBlockChanged = label.frame.merge(this.symbolTable, successorBlock.frame, outgoingEdge.info);
/* 1613 */         if (successorBlockChanged && successorBlock.nextListElement == null) {
/*      */ 
/*      */           
/* 1616 */           successorBlock.nextListElement = listOfBlocksToProcess;
/* 1617 */           listOfBlocksToProcess = successorBlock;
/*      */         } 
/* 1619 */         outgoingEdge = outgoingEdge.nextEdge;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1626 */     Label basicBlock = this.firstBasicBlock;
/* 1627 */     while (basicBlock != null) {
/* 1628 */       if ((basicBlock.flags & 0xA) == 10)
/*      */       {
/* 1630 */         basicBlock.frame.accept(this);
/*      */       }
/* 1632 */       if ((basicBlock.flags & 0x8) == 0) {
/*      */         
/* 1634 */         Label nextBasicBlock = basicBlock.nextBasicBlock;
/* 1635 */         int startOffset = basicBlock.bytecodeOffset;
/* 1636 */         int endOffset = ((nextBasicBlock == null) ? this.code.length : nextBasicBlock.bytecodeOffset) - 1;
/* 1637 */         if (endOffset >= startOffset) {
/*      */           
/* 1639 */           for (int i = startOffset; i < endOffset; i++) {
/* 1640 */             this.code.data[i] = 0;
/*      */           }
/* 1642 */           this.code.data[endOffset] = -65;
/*      */ 
/*      */           
/* 1645 */           int frameIndex = visitFrameStart(startOffset, 0, 1);
/* 1646 */           this.currentFrame[frameIndex] = 
/* 1647 */             Frame.getAbstractTypeFromInternalName(this.symbolTable, "java/lang/Throwable");
/* 1648 */           visitFrameEnd();
/*      */           
/* 1650 */           this.firstHandler = Handler.removeRange(this.firstHandler, basicBlock, nextBasicBlock);
/*      */           
/* 1652 */           maxStackSize = Math.max(maxStackSize, 1);
/*      */         } 
/*      */       } 
/* 1655 */       basicBlock = basicBlock.nextBasicBlock;
/*      */     } 
/*      */     
/* 1658 */     this.maxStack = maxStackSize;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeMaxStackAndLocal() {
/* 1664 */     Handler handler = this.firstHandler;
/* 1665 */     while (handler != null) {
/* 1666 */       Label handlerBlock = handler.handlerPc;
/* 1667 */       Label handlerRangeBlock = handler.startPc;
/* 1668 */       Label handlerRangeEnd = handler.endPc;
/*      */       
/* 1670 */       while (handlerRangeBlock != handlerRangeEnd) {
/* 1671 */         if ((handlerRangeBlock.flags & 0x10) == 0) {
/* 1672 */           handlerRangeBlock.outgoingEdges = new Edge(2147483647, handlerBlock, handlerRangeBlock.outgoingEdges);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1678 */           handlerRangeBlock.outgoingEdges.nextEdge.nextEdge = new Edge(2147483647, handlerBlock, handlerRangeBlock.outgoingEdges.nextEdge.nextEdge);
/*      */         } 
/*      */ 
/*      */         
/* 1682 */         handlerRangeBlock = handlerRangeBlock.nextBasicBlock;
/*      */       } 
/* 1684 */       handler = handler.nextHandler;
/*      */     } 
/*      */ 
/*      */     
/* 1688 */     if (this.hasSubroutines) {
/*      */ 
/*      */       
/* 1691 */       short numSubroutines = 1;
/* 1692 */       this.firstBasicBlock.markSubroutine(numSubroutines);
/*      */ 
/*      */       
/* 1695 */       for (short currentSubroutine = 1; currentSubroutine <= numSubroutines; currentSubroutine = (short)(currentSubroutine + 1)) {
/* 1696 */         Label label = this.firstBasicBlock;
/* 1697 */         while (label != null) {
/* 1698 */           if ((label.flags & 0x10) != 0 && label.subroutineId == currentSubroutine) {
/*      */             
/* 1700 */             Label jsrTarget = label.outgoingEdges.nextEdge.successor;
/* 1701 */             if (jsrTarget.subroutineId == 0) {
/*      */               
/* 1703 */               numSubroutines = (short)(numSubroutines + 1); jsrTarget.markSubroutine(numSubroutines);
/*      */             } 
/*      */           } 
/* 1706 */           label = label.nextBasicBlock;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1712 */       Label basicBlock = this.firstBasicBlock;
/* 1713 */       while (basicBlock != null) {
/* 1714 */         if ((basicBlock.flags & 0x10) != 0) {
/*      */ 
/*      */           
/* 1717 */           Label subroutine = basicBlock.outgoingEdges.nextEdge.successor;
/* 1718 */           subroutine.addSubroutineRetSuccessors(basicBlock);
/*      */         } 
/* 1720 */         basicBlock = basicBlock.nextBasicBlock;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1728 */     Label listOfBlocksToProcess = this.firstBasicBlock;
/* 1729 */     listOfBlocksToProcess.nextListElement = Label.EMPTY_LIST;
/* 1730 */     int maxStackSize = this.maxStack;
/* 1731 */     while (listOfBlocksToProcess != Label.EMPTY_LIST) {
/*      */ 
/*      */ 
/*      */       
/* 1735 */       Label basicBlock = listOfBlocksToProcess;
/* 1736 */       listOfBlocksToProcess = listOfBlocksToProcess.nextListElement;
/*      */       
/* 1738 */       int inputStackTop = basicBlock.inputStackSize;
/* 1739 */       int maxBlockStackSize = inputStackTop + basicBlock.outputStackMax;
/*      */       
/* 1741 */       if (maxBlockStackSize > maxStackSize) {
/* 1742 */         maxStackSize = maxBlockStackSize;
/*      */       }
/*      */ 
/*      */       
/* 1746 */       Edge outgoingEdge = basicBlock.outgoingEdges;
/* 1747 */       if ((basicBlock.flags & 0x10) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1752 */         outgoingEdge = outgoingEdge.nextEdge;
/*      */       }
/* 1754 */       while (outgoingEdge != null) {
/* 1755 */         Label successorBlock = outgoingEdge.successor;
/* 1756 */         if (successorBlock.nextListElement == null) {
/* 1757 */           successorBlock
/* 1758 */             .inputStackSize = (short)((outgoingEdge.info == Integer.MAX_VALUE) ? 1 : (inputStackTop + outgoingEdge.info));
/* 1759 */           successorBlock.nextListElement = listOfBlocksToProcess;
/* 1760 */           listOfBlocksToProcess = successorBlock;
/*      */         } 
/* 1762 */         outgoingEdge = outgoingEdge.nextEdge;
/*      */       } 
/*      */     } 
/* 1765 */     this.maxStack = maxStackSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitEnd() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addSuccessorToCurrentBasicBlock(int info, Label successor) {
/* 1784 */     this.currentBasicBlock.outgoingEdges = new Edge(info, successor, this.currentBasicBlock.outgoingEdges);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void endCurrentBasicBlockWithNoSuccessor() {
/* 1796 */     if (this.compute == 4) {
/* 1797 */       Label nextBasicBlock = new Label();
/* 1798 */       nextBasicBlock.frame = new Frame(nextBasicBlock);
/* 1799 */       nextBasicBlock.resolve(this.code.data, this.stackMapTableEntries, this.code.length);
/* 1800 */       this.lastBasicBlock.nextBasicBlock = nextBasicBlock;
/* 1801 */       this.lastBasicBlock = nextBasicBlock;
/* 1802 */       this.currentBasicBlock = null;
/* 1803 */     } else if (this.compute == 1) {
/* 1804 */       this.currentBasicBlock.outputStackMax = (short)this.maxRelativeStackSize;
/* 1805 */       this.currentBasicBlock = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int visitFrameStart(int offset, int numLocal, int numStack) {
/* 1822 */     int frameLength = 3 + numLocal + numStack;
/* 1823 */     if (this.currentFrame == null || this.currentFrame.length < frameLength) {
/* 1824 */       this.currentFrame = new int[frameLength];
/*      */     }
/* 1826 */     this.currentFrame[0] = offset;
/* 1827 */     this.currentFrame[1] = numLocal;
/* 1828 */     this.currentFrame[2] = numStack;
/* 1829 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void visitAbstractType(int frameIndex, int abstractType) {
/* 1839 */     this.currentFrame[frameIndex] = abstractType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void visitFrameEnd() {
/* 1848 */     if (this.previousFrame != null) {
/* 1849 */       if (this.stackMapTableEntries == null) {
/* 1850 */         this.stackMapTableEntries = new ByteVector();
/*      */       }
/* 1852 */       putFrame();
/* 1853 */       this.stackMapTableNumberOfEntries++;
/*      */     } 
/* 1855 */     this.previousFrame = this.currentFrame;
/* 1856 */     this.currentFrame = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void putFrame() {
/* 1861 */     int numLocal = this.currentFrame[1];
/* 1862 */     int numStack = this.currentFrame[2];
/* 1863 */     if (this.symbolTable.getMajorVersion() < 50) {
/*      */       
/* 1865 */       this.stackMapTableEntries.putShort(this.currentFrame[0]).putShort(numLocal);
/* 1866 */       putAbstractTypes(3, 3 + numLocal);
/* 1867 */       this.stackMapTableEntries.putShort(numStack);
/* 1868 */       putAbstractTypes(3 + numLocal, 3 + numLocal + numStack);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1874 */     int offsetDelta = (this.stackMapTableNumberOfEntries == 0) ? this.currentFrame[0] : (this.currentFrame[0] - this.previousFrame[0] - 1);
/* 1875 */     int previousNumlocal = this.previousFrame[1];
/* 1876 */     int numLocalDelta = numLocal - previousNumlocal;
/* 1877 */     int type = 255;
/* 1878 */     if (numStack == 0) {
/* 1879 */       switch (numLocalDelta) {
/*      */         case -3:
/*      */         case -2:
/*      */         case -1:
/* 1883 */           type = 248;
/*      */           break;
/*      */         case 0:
/* 1886 */           type = (offsetDelta < 64) ? 0 : 251;
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/* 1891 */           type = 252;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/* 1897 */     } else if (numLocalDelta == 0 && numStack == 1) {
/*      */ 
/*      */ 
/*      */       
/* 1901 */       type = (offsetDelta < 63) ? 64 : 247;
/*      */     } 
/* 1903 */     if (type != 255) {
/*      */       
/* 1905 */       int frameIndex = 3;
/* 1906 */       for (int i = 0; i < previousNumlocal && i < numLocal; i++) {
/* 1907 */         if (this.currentFrame[frameIndex] != this.previousFrame[frameIndex]) {
/* 1908 */           type = 255;
/*      */           break;
/*      */         } 
/* 1911 */         frameIndex++;
/*      */       } 
/*      */     } 
/* 1914 */     switch (type) {
/*      */       case 0:
/* 1916 */         this.stackMapTableEntries.putByte(offsetDelta);
/*      */         return;
/*      */       case 64:
/* 1919 */         this.stackMapTableEntries.putByte(64 + offsetDelta);
/* 1920 */         putAbstractTypes(3 + numLocal, 4 + numLocal);
/*      */         return;
/*      */       case 247:
/* 1923 */         this.stackMapTableEntries
/* 1924 */           .putByte(247)
/* 1925 */           .putShort(offsetDelta);
/* 1926 */         putAbstractTypes(3 + numLocal, 4 + numLocal);
/*      */         return;
/*      */       case 251:
/* 1929 */         this.stackMapTableEntries.putByte(251).putShort(offsetDelta);
/*      */         return;
/*      */       case 248:
/* 1932 */         this.stackMapTableEntries
/* 1933 */           .putByte(251 + numLocalDelta)
/* 1934 */           .putShort(offsetDelta);
/*      */         return;
/*      */       case 252:
/* 1937 */         this.stackMapTableEntries
/* 1938 */           .putByte(251 + numLocalDelta)
/* 1939 */           .putShort(offsetDelta);
/* 1940 */         putAbstractTypes(3 + previousNumlocal, 3 + numLocal);
/*      */         return;
/*      */     } 
/*      */     
/* 1944 */     this.stackMapTableEntries.putByte(255).putShort(offsetDelta).putShort(numLocal);
/* 1945 */     putAbstractTypes(3, 3 + numLocal);
/* 1946 */     this.stackMapTableEntries.putShort(numStack);
/* 1947 */     putAbstractTypes(3 + numLocal, 3 + numLocal + numStack);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void putAbstractTypes(int start, int end) {
/* 1960 */     for (int i = start; i < end; i++) {
/* 1961 */       Frame.putAbstractType(this.symbolTable, this.currentFrame[i], this.stackMapTableEntries);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void putFrameType(Object type) {
/* 1976 */     if (type instanceof Integer) {
/* 1977 */       this.stackMapTableEntries.putByte(((Integer)type).intValue());
/* 1978 */     } else if (type instanceof String) {
/* 1979 */       this.stackMapTableEntries
/* 1980 */         .putByte(7)
/* 1981 */         .putShort((this.symbolTable.addConstantClass((String)type)).index);
/*      */     } else {
/* 1983 */       this.stackMapTableEntries.putByte(8);
/* 1984 */       ((Label)type).put(this.stackMapTableEntries);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean canCopyMethodAttributes(ClassReader source, boolean hasSyntheticAttribute, boolean hasDeprecatedAttribute, int descriptorIndex, int signatureIndex, int exceptionsOffset) {
/* 2027 */     if (source != this.symbolTable.getSource() || descriptorIndex != this.descriptorIndex || signatureIndex != this.signatureIndex || hasDeprecatedAttribute != (((this.accessFlags & 0x20000) != 0)))
/*      */     {
/*      */ 
/*      */       
/* 2031 */       return false;
/*      */     }
/*      */     
/* 2034 */     boolean needSyntheticAttribute = (this.symbolTable.getMajorVersion() < 49 && (this.accessFlags & 0x1000) != 0);
/* 2035 */     if (hasSyntheticAttribute != needSyntheticAttribute) {
/* 2036 */       return false;
/*      */     }
/* 2038 */     if (exceptionsOffset == 0) {
/* 2039 */       if (this.numberOfExceptions != 0) {
/* 2040 */         return false;
/*      */       }
/* 2042 */     } else if (source.readUnsignedShort(exceptionsOffset) == this.numberOfExceptions) {
/* 2043 */       int currentExceptionOffset = exceptionsOffset + 2;
/* 2044 */       for (int i = 0; i < this.numberOfExceptions; i++) {
/* 2045 */         if (source.readUnsignedShort(currentExceptionOffset) != this.exceptionIndexTable[i]) {
/* 2046 */           return false;
/*      */         }
/* 2048 */         currentExceptionOffset += 2;
/*      */       } 
/*      */     } 
/* 2051 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMethodAttributesSource(int methodInfoOffset, int methodInfoLength) {
/* 2066 */     this.sourceOffset = methodInfoOffset + 6;
/* 2067 */     this.sourceLength = methodInfoLength - 6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int computeMethodInfoSize() {
/* 2078 */     if (this.sourceOffset != 0)
/*      */     {
/* 2080 */       return 6 + this.sourceLength;
/*      */     }
/*      */     
/* 2083 */     int size = 8;
/*      */     
/* 2085 */     if (this.code.length > 0) {
/* 2086 */       if (this.code.length > 65535) {
/* 2087 */         throw new MethodTooLargeException(this.symbolTable
/* 2088 */             .getClassName(), this.name, this.descriptor, this.code.length);
/*      */       }
/* 2090 */       this.symbolTable.addConstantUtf8("Code");
/*      */ 
/*      */       
/* 2093 */       size += 16 + this.code.length + Handler.getExceptionTableSize(this.firstHandler);
/* 2094 */       if (this.stackMapTableEntries != null) {
/* 2095 */         boolean useStackMapTable = (this.symbolTable.getMajorVersion() >= 50);
/* 2096 */         this.symbolTable.addConstantUtf8(useStackMapTable ? "StackMapTable" : "StackMap");
/*      */         
/* 2098 */         size += 8 + this.stackMapTableEntries.length;
/*      */       } 
/* 2100 */       if (this.lineNumberTable != null) {
/* 2101 */         this.symbolTable.addConstantUtf8("LineNumberTable");
/*      */         
/* 2103 */         size += 8 + this.lineNumberTable.length;
/*      */       } 
/* 2105 */       if (this.localVariableTable != null) {
/* 2106 */         this.symbolTable.addConstantUtf8("LocalVariableTable");
/*      */         
/* 2108 */         size += 8 + this.localVariableTable.length;
/*      */       } 
/* 2110 */       if (this.localVariableTypeTable != null) {
/* 2111 */         this.symbolTable.addConstantUtf8("LocalVariableTypeTable");
/*      */         
/* 2113 */         size += 8 + this.localVariableTypeTable.length;
/*      */       } 
/* 2115 */       if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
/* 2116 */         size += this.lastCodeRuntimeVisibleTypeAnnotation
/* 2117 */           .computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
/*      */       }
/*      */       
/* 2120 */       if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
/* 2121 */         size += this.lastCodeRuntimeInvisibleTypeAnnotation
/* 2122 */           .computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
/*      */       }
/*      */       
/* 2125 */       if (this.firstCodeAttribute != null) {
/* 2126 */         size += this.firstCodeAttribute
/* 2127 */           .computeAttributesSize(this.symbolTable, this.code.data, this.code.length, this.maxStack, this.maxLocals);
/*      */       }
/*      */     } 
/*      */     
/* 2131 */     if (this.numberOfExceptions > 0) {
/* 2132 */       this.symbolTable.addConstantUtf8("Exceptions");
/* 2133 */       size += 8 + 2 * this.numberOfExceptions;
/*      */     } 
/* 2135 */     size += Attribute.computeAttributesSize(this.symbolTable, this.accessFlags, this.signatureIndex);
/* 2136 */     size += 
/* 2137 */       AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2142 */     if (this.lastRuntimeVisibleParameterAnnotations != null) {
/* 2143 */       size += 
/* 2144 */         AnnotationWriter.computeParameterAnnotationsSize("RuntimeVisibleParameterAnnotations", this.lastRuntimeVisibleParameterAnnotations, 
/*      */ 
/*      */           
/* 2147 */           (this.visibleAnnotableParameterCount == 0) ? 
/* 2148 */           this.lastRuntimeVisibleParameterAnnotations.length : 
/* 2149 */           this.visibleAnnotableParameterCount);
/*      */     }
/* 2151 */     if (this.lastRuntimeInvisibleParameterAnnotations != null) {
/* 2152 */       size += 
/* 2153 */         AnnotationWriter.computeParameterAnnotationsSize("RuntimeInvisibleParameterAnnotations", this.lastRuntimeInvisibleParameterAnnotations, 
/*      */ 
/*      */           
/* 2156 */           (this.invisibleAnnotableParameterCount == 0) ? 
/* 2157 */           this.lastRuntimeInvisibleParameterAnnotations.length : 
/* 2158 */           this.invisibleAnnotableParameterCount);
/*      */     }
/* 2160 */     if (this.defaultValue != null) {
/* 2161 */       this.symbolTable.addConstantUtf8("AnnotationDefault");
/* 2162 */       size += 6 + this.defaultValue.length;
/*      */     } 
/* 2164 */     if (this.parameters != null) {
/* 2165 */       this.symbolTable.addConstantUtf8("MethodParameters");
/*      */       
/* 2167 */       size += 7 + this.parameters.length;
/*      */     } 
/* 2169 */     if (this.firstAttribute != null) {
/* 2170 */       size += this.firstAttribute.computeAttributesSize(this.symbolTable);
/*      */     }
/* 2172 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void putMethodInfo(ByteVector output) {
/* 2182 */     boolean useSyntheticAttribute = (this.symbolTable.getMajorVersion() < 49);
/* 2183 */     int mask = useSyntheticAttribute ? 4096 : 0;
/* 2184 */     output.putShort(this.accessFlags & (mask ^ 0xFFFFFFFF)).putShort(this.nameIndex).putShort(this.descriptorIndex);
/*      */     
/* 2186 */     if (this.sourceOffset != 0) {
/* 2187 */       output.putByteArray((this.symbolTable.getSource()).classFileBuffer, this.sourceOffset, this.sourceLength);
/*      */       
/*      */       return;
/*      */     } 
/* 2191 */     int attributeCount = 0;
/* 2192 */     if (this.code.length > 0) {
/* 2193 */       attributeCount++;
/*      */     }
/* 2195 */     if (this.numberOfExceptions > 0) {
/* 2196 */       attributeCount++;
/*      */     }
/* 2198 */     if ((this.accessFlags & 0x1000) != 0 && useSyntheticAttribute) {
/* 2199 */       attributeCount++;
/*      */     }
/* 2201 */     if (this.signatureIndex != 0) {
/* 2202 */       attributeCount++;
/*      */     }
/* 2204 */     if ((this.accessFlags & 0x20000) != 0) {
/* 2205 */       attributeCount++;
/*      */     }
/* 2207 */     if (this.lastRuntimeVisibleAnnotation != null) {
/* 2208 */       attributeCount++;
/*      */     }
/* 2210 */     if (this.lastRuntimeInvisibleAnnotation != null) {
/* 2211 */       attributeCount++;
/*      */     }
/* 2213 */     if (this.lastRuntimeVisibleParameterAnnotations != null) {
/* 2214 */       attributeCount++;
/*      */     }
/* 2216 */     if (this.lastRuntimeInvisibleParameterAnnotations != null) {
/* 2217 */       attributeCount++;
/*      */     }
/* 2219 */     if (this.lastRuntimeVisibleTypeAnnotation != null) {
/* 2220 */       attributeCount++;
/*      */     }
/* 2222 */     if (this.lastRuntimeInvisibleTypeAnnotation != null) {
/* 2223 */       attributeCount++;
/*      */     }
/* 2225 */     if (this.defaultValue != null) {
/* 2226 */       attributeCount++;
/*      */     }
/* 2228 */     if (this.parameters != null) {
/* 2229 */       attributeCount++;
/*      */     }
/* 2231 */     if (this.firstAttribute != null) {
/* 2232 */       attributeCount += this.firstAttribute.getAttributeCount();
/*      */     }
/*      */     
/* 2235 */     output.putShort(attributeCount);
/* 2236 */     if (this.code.length > 0) {
/*      */ 
/*      */       
/* 2239 */       int size = 10 + this.code.length + Handler.getExceptionTableSize(this.firstHandler);
/* 2240 */       int codeAttributeCount = 0;
/* 2241 */       if (this.stackMapTableEntries != null) {
/*      */         
/* 2243 */         size += 8 + this.stackMapTableEntries.length;
/* 2244 */         codeAttributeCount++;
/*      */       } 
/* 2246 */       if (this.lineNumberTable != null) {
/*      */         
/* 2248 */         size += 8 + this.lineNumberTable.length;
/* 2249 */         codeAttributeCount++;
/*      */       } 
/* 2251 */       if (this.localVariableTable != null) {
/*      */         
/* 2253 */         size += 8 + this.localVariableTable.length;
/* 2254 */         codeAttributeCount++;
/*      */       } 
/* 2256 */       if (this.localVariableTypeTable != null) {
/*      */         
/* 2258 */         size += 8 + this.localVariableTypeTable.length;
/* 2259 */         codeAttributeCount++;
/*      */       } 
/* 2261 */       if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
/* 2262 */         size += this.lastCodeRuntimeVisibleTypeAnnotation
/* 2263 */           .computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
/*      */         
/* 2265 */         codeAttributeCount++;
/*      */       } 
/* 2267 */       if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
/* 2268 */         size += this.lastCodeRuntimeInvisibleTypeAnnotation
/* 2269 */           .computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
/*      */         
/* 2271 */         codeAttributeCount++;
/*      */       } 
/* 2273 */       if (this.firstCodeAttribute != null) {
/* 2274 */         size += this.firstCodeAttribute
/* 2275 */           .computeAttributesSize(this.symbolTable, this.code.data, this.code.length, this.maxStack, this.maxLocals);
/*      */         
/* 2277 */         codeAttributeCount += this.firstCodeAttribute.getAttributeCount();
/*      */       } 
/* 2279 */       output
/* 2280 */         .putShort(this.symbolTable.addConstantUtf8("Code"))
/* 2281 */         .putInt(size)
/* 2282 */         .putShort(this.maxStack)
/* 2283 */         .putShort(this.maxLocals)
/* 2284 */         .putInt(this.code.length)
/* 2285 */         .putByteArray(this.code.data, 0, this.code.length);
/* 2286 */       Handler.putExceptionTable(this.firstHandler, output);
/* 2287 */       output.putShort(codeAttributeCount);
/* 2288 */       if (this.stackMapTableEntries != null) {
/* 2289 */         boolean useStackMapTable = (this.symbolTable.getMajorVersion() >= 50);
/* 2290 */         output
/* 2291 */           .putShort(this.symbolTable
/* 2292 */             .addConstantUtf8(
/* 2293 */               useStackMapTable ? "StackMapTable" : "StackMap"))
/* 2294 */           .putInt(2 + this.stackMapTableEntries.length)
/* 2295 */           .putShort(this.stackMapTableNumberOfEntries)
/* 2296 */           .putByteArray(this.stackMapTableEntries.data, 0, this.stackMapTableEntries.length);
/*      */       } 
/* 2298 */       if (this.lineNumberTable != null) {
/* 2299 */         output
/* 2300 */           .putShort(this.symbolTable.addConstantUtf8("LineNumberTable"))
/* 2301 */           .putInt(2 + this.lineNumberTable.length)
/* 2302 */           .putShort(this.lineNumberTableLength)
/* 2303 */           .putByteArray(this.lineNumberTable.data, 0, this.lineNumberTable.length);
/*      */       }
/* 2305 */       if (this.localVariableTable != null) {
/* 2306 */         output
/* 2307 */           .putShort(this.symbolTable.addConstantUtf8("LocalVariableTable"))
/* 2308 */           .putInt(2 + this.localVariableTable.length)
/* 2309 */           .putShort(this.localVariableTableLength)
/* 2310 */           .putByteArray(this.localVariableTable.data, 0, this.localVariableTable.length);
/*      */       }
/* 2312 */       if (this.localVariableTypeTable != null) {
/* 2313 */         output
/* 2314 */           .putShort(this.symbolTable.addConstantUtf8("LocalVariableTypeTable"))
/* 2315 */           .putInt(2 + this.localVariableTypeTable.length)
/* 2316 */           .putShort(this.localVariableTypeTableLength)
/* 2317 */           .putByteArray(this.localVariableTypeTable.data, 0, this.localVariableTypeTable.length);
/*      */       }
/* 2319 */       if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
/* 2320 */         this.lastCodeRuntimeVisibleTypeAnnotation.putAnnotations(this.symbolTable
/* 2321 */             .addConstantUtf8("RuntimeVisibleTypeAnnotations"), output);
/*      */       }
/* 2323 */       if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
/* 2324 */         this.lastCodeRuntimeInvisibleTypeAnnotation.putAnnotations(this.symbolTable
/* 2325 */             .addConstantUtf8("RuntimeInvisibleTypeAnnotations"), output);
/*      */       }
/* 2327 */       if (this.firstCodeAttribute != null) {
/* 2328 */         this.firstCodeAttribute.putAttributes(this.symbolTable, this.code.data, this.code.length, this.maxStack, this.maxLocals, output);
/*      */       }
/*      */     } 
/*      */     
/* 2332 */     if (this.numberOfExceptions > 0) {
/* 2333 */       output
/* 2334 */         .putShort(this.symbolTable.addConstantUtf8("Exceptions"))
/* 2335 */         .putInt(2 + 2 * this.numberOfExceptions)
/* 2336 */         .putShort(this.numberOfExceptions);
/* 2337 */       for (int exceptionIndex : this.exceptionIndexTable) {
/* 2338 */         output.putShort(exceptionIndex);
/*      */       }
/*      */     } 
/* 2341 */     Attribute.putAttributes(this.symbolTable, this.accessFlags, this.signatureIndex, output);
/* 2342 */     AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, output);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2349 */     if (this.lastRuntimeVisibleParameterAnnotations != null) {
/* 2350 */       AnnotationWriter.putParameterAnnotations(this.symbolTable
/* 2351 */           .addConstantUtf8("RuntimeVisibleParameterAnnotations"), this.lastRuntimeVisibleParameterAnnotations, 
/*      */           
/* 2353 */           (this.visibleAnnotableParameterCount == 0) ? 
/* 2354 */           this.lastRuntimeVisibleParameterAnnotations.length : 
/* 2355 */           this.visibleAnnotableParameterCount, output);
/*      */     }
/*      */     
/* 2358 */     if (this.lastRuntimeInvisibleParameterAnnotations != null) {
/* 2359 */       AnnotationWriter.putParameterAnnotations(this.symbolTable
/* 2360 */           .addConstantUtf8("RuntimeInvisibleParameterAnnotations"), this.lastRuntimeInvisibleParameterAnnotations, 
/*      */           
/* 2362 */           (this.invisibleAnnotableParameterCount == 0) ? 
/* 2363 */           this.lastRuntimeInvisibleParameterAnnotations.length : 
/* 2364 */           this.invisibleAnnotableParameterCount, output);
/*      */     }
/*      */     
/* 2367 */     if (this.defaultValue != null) {
/* 2368 */       output
/* 2369 */         .putShort(this.symbolTable.addConstantUtf8("AnnotationDefault"))
/* 2370 */         .putInt(this.defaultValue.length)
/* 2371 */         .putByteArray(this.defaultValue.data, 0, this.defaultValue.length);
/*      */     }
/* 2373 */     if (this.parameters != null) {
/* 2374 */       output
/* 2375 */         .putShort(this.symbolTable.addConstantUtf8("MethodParameters"))
/* 2376 */         .putInt(1 + this.parameters.length)
/* 2377 */         .putByte(this.parametersCount)
/* 2378 */         .putByteArray(this.parameters.data, 0, this.parameters.length);
/*      */     }
/* 2380 */     if (this.firstAttribute != null) {
/* 2381 */       this.firstAttribute.putAttributes(this.symbolTable, output);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void collectAttributePrototypes(Attribute.Set attributePrototypes) {
/* 2391 */     attributePrototypes.addAttributes(this.firstAttribute);
/* 2392 */     attributePrototypes.addAttributes(this.firstCodeAttribute);
/*      */   }
/*      */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/MethodWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */