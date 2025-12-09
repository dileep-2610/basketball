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
/*      */ public class ClassWriter
/*      */   extends ClassVisitor
/*      */ {
/*      */   public static final int COMPUTE_MAXS = 1;
/*      */   public static final int COMPUTE_FRAMES = 2;
/*      */   private final int flags;
/*      */   private int version;
/*      */   private final SymbolTable symbolTable;
/*      */   private int accessFlags;
/*      */   private int thisClass;
/*      */   private int superClass;
/*      */   private int interfaceCount;
/*      */   private int[] interfaces;
/*      */   private FieldWriter firstField;
/*      */   private FieldWriter lastField;
/*      */   private MethodWriter firstMethod;
/*      */   private MethodWriter lastMethod;
/*      */   private int numberOfInnerClasses;
/*      */   private ByteVector innerClasses;
/*      */   private int enclosingClassIndex;
/*      */   private int enclosingMethodIndex;
/*      */   private int signatureIndex;
/*      */   private int sourceFileIndex;
/*      */   private ByteVector debugExtension;
/*      */   private AnnotationWriter lastRuntimeVisibleAnnotation;
/*      */   private AnnotationWriter lastRuntimeInvisibleAnnotation;
/*      */   private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
/*      */   private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
/*      */   private ModuleWriter moduleWriter;
/*      */   private int nestHostClassIndex;
/*      */   private int numberOfNestMemberClasses;
/*      */   private ByteVector nestMemberClasses;
/*      */   private int numberOfPermittedSubclasses;
/*      */   private ByteVector permittedSubclasses;
/*      */   private RecordComponentWriter firstRecordComponent;
/*      */   private RecordComponentWriter lastRecordComponent;
/*      */   private Attribute firstAttribute;
/*      */   private int compute;
/*      */   
/*      */   public ClassWriter(int flags) {
/*  236 */     this(null, flags);
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
/*      */   public ClassWriter(ClassReader classReader, int flags) {
/*  264 */     super(589824);
/*  265 */     this.flags = flags;
/*  266 */     this.symbolTable = (classReader == null) ? new SymbolTable(this) : new SymbolTable(this, classReader);
/*  267 */     setFlags(flags);
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
/*      */   public boolean hasFlags(int flags) {
/*  282 */     return ((this.flags & flags) == flags);
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
/*      */   public final void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/*  297 */     this.version = version;
/*  298 */     this.accessFlags = access;
/*  299 */     this.thisClass = this.symbolTable.setMajorVersionAndClassName(version & 0xFFFF, name);
/*  300 */     if (signature != null) {
/*  301 */       this.signatureIndex = this.symbolTable.addConstantUtf8(signature);
/*      */     }
/*  303 */     this.superClass = (superName == null) ? 0 : (this.symbolTable.addConstantClass(superName)).index;
/*  304 */     if (interfaces != null && interfaces.length > 0) {
/*  305 */       this.interfaceCount = interfaces.length;
/*  306 */       this.interfaces = new int[this.interfaceCount];
/*  307 */       for (int i = 0; i < this.interfaceCount; i++) {
/*  308 */         this.interfaces[i] = (this.symbolTable.addConstantClass(interfaces[i])).index;
/*      */       }
/*      */     } 
/*  311 */     if (this.compute == 1 && (version & 0xFFFF) >= 51) {
/*  312 */       this.compute = 2;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final void visitSource(String file, String debug) {
/*  318 */     if (file != null) {
/*  319 */       this.sourceFileIndex = this.symbolTable.addConstantUtf8(file);
/*      */     }
/*  321 */     if (debug != null) {
/*  322 */       this.debugExtension = (new ByteVector()).encodeUtf8(debug, 0, 2147483647);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final ModuleVisitor visitModule(String name, int access, String version) {
/*  329 */     return this
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  334 */       .moduleWriter = new ModuleWriter(this.symbolTable, (this.symbolTable.addConstantModule(name)).index, access, (version == null) ? 0 : this.symbolTable.addConstantUtf8(version));
/*      */   }
/*      */ 
/*      */   
/*      */   public final void visitNestHost(String nestHost) {
/*  339 */     this.nestHostClassIndex = (this.symbolTable.addConstantClass(nestHost)).index;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void visitOuterClass(String owner, String name, String descriptor) {
/*  345 */     this.enclosingClassIndex = (this.symbolTable.addConstantClass(owner)).index;
/*  346 */     if (name != null && descriptor != null) {
/*  347 */       this.enclosingMethodIndex = this.symbolTable.addConstantNameAndType(name, descriptor);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/*  353 */     if (visible) {
/*  354 */       return this
/*  355 */         .lastRuntimeVisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeVisibleAnnotation);
/*      */     }
/*  357 */     return this
/*  358 */       .lastRuntimeInvisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeInvisibleAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/*  365 */     if (visible) {
/*  366 */       return this
/*  367 */         .lastRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeVisibleTypeAnnotation);
/*      */     }
/*      */     
/*  370 */     return this
/*  371 */       .lastRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeInvisibleTypeAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void visitAttribute(Attribute attribute) {
/*  379 */     attribute.nextAttribute = this.firstAttribute;
/*  380 */     this.firstAttribute = attribute;
/*      */   }
/*      */ 
/*      */   
/*      */   public final void visitNestMember(String nestMember) {
/*  385 */     if (this.nestMemberClasses == null) {
/*  386 */       this.nestMemberClasses = new ByteVector();
/*      */     }
/*  388 */     this.numberOfNestMemberClasses++;
/*  389 */     this.nestMemberClasses.putShort((this.symbolTable.addConstantClass(nestMember)).index);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void visitPermittedSubclass(String permittedSubclass) {
/*  394 */     if (this.permittedSubclasses == null) {
/*  395 */       this.permittedSubclasses = new ByteVector();
/*      */     }
/*  397 */     this.numberOfPermittedSubclasses++;
/*  398 */     this.permittedSubclasses.putShort((this.symbolTable.addConstantClass(permittedSubclass)).index);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void visitInnerClass(String name, String outerName, String innerName, int access) {
/*  404 */     if (this.innerClasses == null) {
/*  405 */       this.innerClasses = new ByteVector();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  413 */     Symbol nameSymbol = this.symbolTable.addConstantClass(name);
/*  414 */     if (nameSymbol.info == 0) {
/*  415 */       this.numberOfInnerClasses++;
/*  416 */       this.innerClasses.putShort(nameSymbol.index);
/*  417 */       this.innerClasses.putShort((outerName == null) ? 0 : (this.symbolTable.addConstantClass(outerName)).index);
/*  418 */       this.innerClasses.putShort((innerName == null) ? 0 : this.symbolTable.addConstantUtf8(innerName));
/*  419 */       this.innerClasses.putShort(access);
/*  420 */       nameSymbol.info = this.numberOfInnerClasses;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
/*  429 */     RecordComponentWriter recordComponentWriter = new RecordComponentWriter(this.symbolTable, name, descriptor, signature);
/*      */     
/*  431 */     if (this.firstRecordComponent == null) {
/*  432 */       this.firstRecordComponent = recordComponentWriter;
/*      */     } else {
/*  434 */       this.lastRecordComponent.delegate = recordComponentWriter;
/*      */     } 
/*  436 */     return this.lastRecordComponent = recordComponentWriter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
/*  446 */     FieldWriter fieldWriter = new FieldWriter(this.symbolTable, access, name, descriptor, signature, value);
/*      */     
/*  448 */     if (this.firstField == null) {
/*  449 */       this.firstField = fieldWriter;
/*      */     } else {
/*  451 */       this.lastField.fv = fieldWriter;
/*      */     } 
/*  453 */     return this.lastField = fieldWriter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/*  463 */     MethodWriter methodWriter = new MethodWriter(this.symbolTable, access, name, descriptor, signature, exceptions, this.compute);
/*      */     
/*  465 */     if (this.firstMethod == null) {
/*  466 */       this.firstMethod = methodWriter;
/*      */     } else {
/*  468 */       this.lastMethod.mv = methodWriter;
/*      */     } 
/*  470 */     return this.lastMethod = methodWriter;
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
/*      */   public final void visitEnd() {}
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
/*      */   public byte[] toByteArray() {
/*  494 */     int size = 24 + 2 * this.interfaceCount;
/*  495 */     int fieldsCount = 0;
/*  496 */     FieldWriter fieldWriter = this.firstField;
/*  497 */     while (fieldWriter != null) {
/*  498 */       fieldsCount++;
/*  499 */       size += fieldWriter.computeFieldInfoSize();
/*  500 */       fieldWriter = (FieldWriter)fieldWriter.fv;
/*      */     } 
/*  502 */     int methodsCount = 0;
/*  503 */     MethodWriter methodWriter = this.firstMethod;
/*  504 */     while (methodWriter != null) {
/*  505 */       methodsCount++;
/*  506 */       size += methodWriter.computeMethodInfoSize();
/*  507 */       methodWriter = (MethodWriter)methodWriter.mv;
/*      */     } 
/*      */ 
/*      */     
/*  511 */     int attributesCount = 0;
/*  512 */     if (this.innerClasses != null) {
/*  513 */       attributesCount++;
/*  514 */       size += 8 + this.innerClasses.length;
/*  515 */       this.symbolTable.addConstantUtf8("InnerClasses");
/*      */     } 
/*  517 */     if (this.enclosingClassIndex != 0) {
/*  518 */       attributesCount++;
/*  519 */       size += 10;
/*  520 */       this.symbolTable.addConstantUtf8("EnclosingMethod");
/*      */     } 
/*  522 */     if ((this.accessFlags & 0x1000) != 0 && (this.version & 0xFFFF) < 49) {
/*  523 */       attributesCount++;
/*  524 */       size += 6;
/*  525 */       this.symbolTable.addConstantUtf8("Synthetic");
/*      */     } 
/*  527 */     if (this.signatureIndex != 0) {
/*  528 */       attributesCount++;
/*  529 */       size += 8;
/*  530 */       this.symbolTable.addConstantUtf8("Signature");
/*      */     } 
/*  532 */     if (this.sourceFileIndex != 0) {
/*  533 */       attributesCount++;
/*  534 */       size += 8;
/*  535 */       this.symbolTable.addConstantUtf8("SourceFile");
/*      */     } 
/*  537 */     if (this.debugExtension != null) {
/*  538 */       attributesCount++;
/*  539 */       size += 6 + this.debugExtension.length;
/*  540 */       this.symbolTable.addConstantUtf8("SourceDebugExtension");
/*      */     } 
/*  542 */     if ((this.accessFlags & 0x20000) != 0) {
/*  543 */       attributesCount++;
/*  544 */       size += 6;
/*  545 */       this.symbolTable.addConstantUtf8("Deprecated");
/*      */     } 
/*  547 */     if (this.lastRuntimeVisibleAnnotation != null) {
/*  548 */       attributesCount++;
/*  549 */       size += this.lastRuntimeVisibleAnnotation
/*  550 */         .computeAnnotationsSize("RuntimeVisibleAnnotations");
/*      */     } 
/*      */     
/*  553 */     if (this.lastRuntimeInvisibleAnnotation != null) {
/*  554 */       attributesCount++;
/*  555 */       size += this.lastRuntimeInvisibleAnnotation
/*  556 */         .computeAnnotationsSize("RuntimeInvisibleAnnotations");
/*      */     } 
/*      */     
/*  559 */     if (this.lastRuntimeVisibleTypeAnnotation != null) {
/*  560 */       attributesCount++;
/*  561 */       size += this.lastRuntimeVisibleTypeAnnotation
/*  562 */         .computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
/*      */     } 
/*      */     
/*  565 */     if (this.lastRuntimeInvisibleTypeAnnotation != null) {
/*  566 */       attributesCount++;
/*  567 */       size += this.lastRuntimeInvisibleTypeAnnotation
/*  568 */         .computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
/*      */     } 
/*      */     
/*  571 */     if (this.symbolTable.computeBootstrapMethodsSize() > 0) {
/*  572 */       attributesCount++;
/*  573 */       size += this.symbolTable.computeBootstrapMethodsSize();
/*      */     } 
/*  575 */     if (this.moduleWriter != null) {
/*  576 */       attributesCount += this.moduleWriter.getAttributeCount();
/*  577 */       size += this.moduleWriter.computeAttributesSize();
/*      */     } 
/*  579 */     if (this.nestHostClassIndex != 0) {
/*  580 */       attributesCount++;
/*  581 */       size += 8;
/*  582 */       this.symbolTable.addConstantUtf8("NestHost");
/*      */     } 
/*  584 */     if (this.nestMemberClasses != null) {
/*  585 */       attributesCount++;
/*  586 */       size += 8 + this.nestMemberClasses.length;
/*  587 */       this.symbolTable.addConstantUtf8("NestMembers");
/*      */     } 
/*  589 */     if (this.permittedSubclasses != null) {
/*  590 */       attributesCount++;
/*  591 */       size += 8 + this.permittedSubclasses.length;
/*  592 */       this.symbolTable.addConstantUtf8("PermittedSubclasses");
/*      */     } 
/*  594 */     int recordComponentCount = 0;
/*  595 */     int recordSize = 0;
/*  596 */     if ((this.accessFlags & 0x10000) != 0 || this.firstRecordComponent != null) {
/*  597 */       RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
/*  598 */       while (recordComponentWriter != null) {
/*  599 */         recordComponentCount++;
/*  600 */         recordSize += recordComponentWriter.computeRecordComponentInfoSize();
/*  601 */         recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
/*      */       } 
/*  603 */       attributesCount++;
/*  604 */       size += 8 + recordSize;
/*  605 */       this.symbolTable.addConstantUtf8("Record");
/*      */     } 
/*  607 */     if (this.firstAttribute != null) {
/*  608 */       attributesCount += this.firstAttribute.getAttributeCount();
/*  609 */       size += this.firstAttribute.computeAttributesSize(this.symbolTable);
/*      */     } 
/*      */ 
/*      */     
/*  613 */     size += this.symbolTable.getConstantPoolLength();
/*  614 */     int constantPoolCount = this.symbolTable.getConstantPoolCount();
/*  615 */     if (constantPoolCount > 65535) {
/*  616 */       throw new ClassTooLargeException(this.symbolTable.getClassName(), constantPoolCount);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  621 */     ByteVector result = new ByteVector(size);
/*  622 */     result.putInt(-889275714).putInt(this.version);
/*  623 */     this.symbolTable.putConstantPool(result);
/*  624 */     int mask = ((this.version & 0xFFFF) < 49) ? 4096 : 0;
/*  625 */     result.putShort(this.accessFlags & (mask ^ 0xFFFFFFFF)).putShort(this.thisClass).putShort(this.superClass);
/*  626 */     result.putShort(this.interfaceCount);
/*  627 */     for (int i = 0; i < this.interfaceCount; i++) {
/*  628 */       result.putShort(this.interfaces[i]);
/*      */     }
/*  630 */     result.putShort(fieldsCount);
/*  631 */     fieldWriter = this.firstField;
/*  632 */     while (fieldWriter != null) {
/*  633 */       fieldWriter.putFieldInfo(result);
/*  634 */       fieldWriter = (FieldWriter)fieldWriter.fv;
/*      */     } 
/*  636 */     result.putShort(methodsCount);
/*  637 */     boolean hasFrames = false;
/*  638 */     boolean hasAsmInstructions = false;
/*  639 */     methodWriter = this.firstMethod;
/*  640 */     while (methodWriter != null) {
/*  641 */       hasFrames |= methodWriter.hasFrames();
/*  642 */       hasAsmInstructions |= methodWriter.hasAsmInstructions();
/*  643 */       methodWriter.putMethodInfo(result);
/*  644 */       methodWriter = (MethodWriter)methodWriter.mv;
/*      */     } 
/*      */     
/*  647 */     result.putShort(attributesCount);
/*  648 */     if (this.innerClasses != null) {
/*  649 */       result
/*  650 */         .putShort(this.symbolTable.addConstantUtf8("InnerClasses"))
/*  651 */         .putInt(this.innerClasses.length + 2)
/*  652 */         .putShort(this.numberOfInnerClasses)
/*  653 */         .putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
/*      */     }
/*  655 */     if (this.enclosingClassIndex != 0) {
/*  656 */       result
/*  657 */         .putShort(this.symbolTable.addConstantUtf8("EnclosingMethod"))
/*  658 */         .putInt(4)
/*  659 */         .putShort(this.enclosingClassIndex)
/*  660 */         .putShort(this.enclosingMethodIndex);
/*      */     }
/*  662 */     if ((this.accessFlags & 0x1000) != 0 && (this.version & 0xFFFF) < 49) {
/*  663 */       result.putShort(this.symbolTable.addConstantUtf8("Synthetic")).putInt(0);
/*      */     }
/*  665 */     if (this.signatureIndex != 0) {
/*  666 */       result
/*  667 */         .putShort(this.symbolTable.addConstantUtf8("Signature"))
/*  668 */         .putInt(2)
/*  669 */         .putShort(this.signatureIndex);
/*      */     }
/*  671 */     if (this.sourceFileIndex != 0) {
/*  672 */       result
/*  673 */         .putShort(this.symbolTable.addConstantUtf8("SourceFile"))
/*  674 */         .putInt(2)
/*  675 */         .putShort(this.sourceFileIndex);
/*      */     }
/*  677 */     if (this.debugExtension != null) {
/*  678 */       int length = this.debugExtension.length;
/*  679 */       result
/*  680 */         .putShort(this.symbolTable.addConstantUtf8("SourceDebugExtension"))
/*  681 */         .putInt(length)
/*  682 */         .putByteArray(this.debugExtension.data, 0, length);
/*      */     } 
/*  684 */     if ((this.accessFlags & 0x20000) != 0) {
/*  685 */       result.putShort(this.symbolTable.addConstantUtf8("Deprecated")).putInt(0);
/*      */     }
/*  687 */     AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, result);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  694 */     this.symbolTable.putBootstrapMethods(result);
/*  695 */     if (this.moduleWriter != null) {
/*  696 */       this.moduleWriter.putAttributes(result);
/*      */     }
/*  698 */     if (this.nestHostClassIndex != 0) {
/*  699 */       result
/*  700 */         .putShort(this.symbolTable.addConstantUtf8("NestHost"))
/*  701 */         .putInt(2)
/*  702 */         .putShort(this.nestHostClassIndex);
/*      */     }
/*  704 */     if (this.nestMemberClasses != null) {
/*  705 */       result
/*  706 */         .putShort(this.symbolTable.addConstantUtf8("NestMembers"))
/*  707 */         .putInt(this.nestMemberClasses.length + 2)
/*  708 */         .putShort(this.numberOfNestMemberClasses)
/*  709 */         .putByteArray(this.nestMemberClasses.data, 0, this.nestMemberClasses.length);
/*      */     }
/*  711 */     if (this.permittedSubclasses != null) {
/*  712 */       result
/*  713 */         .putShort(this.symbolTable.addConstantUtf8("PermittedSubclasses"))
/*  714 */         .putInt(this.permittedSubclasses.length + 2)
/*  715 */         .putShort(this.numberOfPermittedSubclasses)
/*  716 */         .putByteArray(this.permittedSubclasses.data, 0, this.permittedSubclasses.length);
/*      */     }
/*  718 */     if ((this.accessFlags & 0x10000) != 0 || this.firstRecordComponent != null) {
/*  719 */       result
/*  720 */         .putShort(this.symbolTable.addConstantUtf8("Record"))
/*  721 */         .putInt(recordSize + 2)
/*  722 */         .putShort(recordComponentCount);
/*  723 */       RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
/*  724 */       while (recordComponentWriter != null) {
/*  725 */         recordComponentWriter.putRecordComponentInfo(result);
/*  726 */         recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
/*      */       } 
/*      */     } 
/*  729 */     if (this.firstAttribute != null) {
/*  730 */       this.firstAttribute.putAttributes(this.symbolTable, result);
/*      */     }
/*      */ 
/*      */     
/*  734 */     if (hasAsmInstructions) {
/*  735 */       return replaceAsmInstructions(result.data, hasFrames);
/*      */     }
/*  737 */     return result.data;
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
/*      */   private byte[] replaceAsmInstructions(byte[] classFile, boolean hasFrames) {
/*  752 */     Attribute[] attributes = getAttributePrototypes();
/*  753 */     this.firstField = null;
/*  754 */     this.lastField = null;
/*  755 */     this.firstMethod = null;
/*  756 */     this.lastMethod = null;
/*  757 */     this.lastRuntimeVisibleAnnotation = null;
/*  758 */     this.lastRuntimeInvisibleAnnotation = null;
/*  759 */     this.lastRuntimeVisibleTypeAnnotation = null;
/*  760 */     this.lastRuntimeInvisibleTypeAnnotation = null;
/*  761 */     this.moduleWriter = null;
/*  762 */     this.nestHostClassIndex = 0;
/*  763 */     this.numberOfNestMemberClasses = 0;
/*  764 */     this.nestMemberClasses = null;
/*  765 */     this.numberOfPermittedSubclasses = 0;
/*  766 */     this.permittedSubclasses = null;
/*  767 */     this.firstRecordComponent = null;
/*  768 */     this.lastRecordComponent = null;
/*  769 */     this.firstAttribute = null;
/*  770 */     this.compute = hasFrames ? 3 : 0;
/*  771 */     (new ClassReader(classFile, 0, false))
/*  772 */       .accept(this, attributes, (
/*      */ 
/*      */         
/*  775 */         hasFrames ? 8 : 0) | 0x100);
/*  776 */     return toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Attribute[] getAttributePrototypes() {
/*  785 */     Attribute.Set attributePrototypes = new Attribute.Set();
/*  786 */     attributePrototypes.addAttributes(this.firstAttribute);
/*  787 */     FieldWriter fieldWriter = this.firstField;
/*  788 */     while (fieldWriter != null) {
/*  789 */       fieldWriter.collectAttributePrototypes(attributePrototypes);
/*  790 */       fieldWriter = (FieldWriter)fieldWriter.fv;
/*      */     } 
/*  792 */     MethodWriter methodWriter = this.firstMethod;
/*  793 */     while (methodWriter != null) {
/*  794 */       methodWriter.collectAttributePrototypes(attributePrototypes);
/*  795 */       methodWriter = (MethodWriter)methodWriter.mv;
/*      */     } 
/*  797 */     RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
/*  798 */     while (recordComponentWriter != null) {
/*  799 */       recordComponentWriter.collectAttributePrototypes(attributePrototypes);
/*  800 */       recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
/*      */     } 
/*  802 */     return attributePrototypes.toArray();
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
/*      */   public int newConst(Object value) {
/*  819 */     return (this.symbolTable.addConstant(value)).index;
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
/*      */   public int newUTF8(String value) {
/*  832 */     return this.symbolTable.addConstantUtf8(value);
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
/*      */   public int newClass(String value) {
/*  844 */     return (this.symbolTable.addConstantClass(value)).index;
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
/*      */   public int newMethodType(String methodDescriptor) {
/*  856 */     return (this.symbolTable.addConstantMethodType(methodDescriptor)).index;
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
/*      */   public int newModule(String moduleName) {
/*  868 */     return (this.symbolTable.addConstantModule(moduleName)).index;
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
/*      */   public int newPackage(String packageName) {
/*  880 */     return (this.symbolTable.addConstantPackage(packageName)).index;
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
/*      */   @Deprecated
/*      */   public int newHandle(int tag, String owner, String name, String descriptor) {
/*  903 */     return newHandle(tag, owner, name, descriptor, (tag == 9));
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
/*      */   public int newHandle(int tag, String owner, String name, String descriptor, boolean isInterface) {
/*  928 */     return (this.symbolTable.addConstantMethodHandle(tag, owner, name, descriptor, isInterface)).index;
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
/*      */   public int newConstantDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/*  947 */     return (this.symbolTable.addConstantDynamic(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments)).index;
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
/*      */   public int newInvokeDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/*  968 */     return (this.symbolTable.addConstantInvokeDynamic(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments)).index;
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
/*      */   public int newField(String owner, String name, String descriptor) {
/*  984 */     return (this.symbolTable.addConstantFieldref(owner, name, descriptor)).index;
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
/*      */   public int newMethod(String owner, String name, String descriptor, boolean isInterface) {
/* 1001 */     return (this.symbolTable.addConstantMethodref(owner, name, descriptor, isInterface)).index;
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
/*      */   public int newNameType(String name, String descriptor) {
/* 1014 */     return this.symbolTable.addConstantNameAndType(name, descriptor);
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
/*      */   public final void setFlags(int flags) {
/* 1030 */     if ((flags & 0x2) != 0) {
/* 1031 */       this.compute = 4;
/* 1032 */     } else if ((flags & 0x1) != 0) {
/* 1033 */       this.compute = 1;
/*      */     } else {
/* 1035 */       this.compute = 0;
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
/*      */   protected String getCommonSuperClass(String type1, String type2) {
/*      */     Class<?> class1, class2;
/* 1057 */     ClassLoader classLoader = getClassLoader();
/*      */     
/*      */     try {
/* 1060 */       class1 = Class.forName(type1.replace('/', '.'), false, classLoader);
/* 1061 */     } catch (ClassNotFoundException e) {
/* 1062 */       throw new TypeNotPresentException(type1, e);
/*      */     } 
/*      */     
/*      */     try {
/* 1066 */       class2 = Class.forName(type2.replace('/', '.'), false, classLoader);
/* 1067 */     } catch (ClassNotFoundException e) {
/* 1068 */       throw new TypeNotPresentException(type2, e);
/*      */     } 
/* 1070 */     if (class1.isAssignableFrom(class2)) {
/* 1071 */       return type1;
/*      */     }
/* 1073 */     if (class2.isAssignableFrom(class1)) {
/* 1074 */       return type2;
/*      */     }
/* 1076 */     if (class1.isInterface() || class2.isInterface()) {
/* 1077 */       return "java/lang/Object";
/*      */     }
/*      */     while (true) {
/* 1080 */       class1 = class1.getSuperclass();
/* 1081 */       if (class1.isAssignableFrom(class2)) {
/* 1082 */         return class1.getName().replace('.', '/');
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
/*      */   protected ClassLoader getClassLoader() {
/* 1094 */     return getClass().getClassLoader();
/*      */   }
/*      */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/ClassWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */