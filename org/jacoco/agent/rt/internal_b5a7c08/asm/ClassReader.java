/*      */ package org.jacoco.agent.rt.internal_b5a7c08.asm;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ClassReader
/*      */ {
/*      */   public static final int SKIP_CODE = 1;
/*      */   public static final int SKIP_DEBUG = 2;
/*      */   public static final int SKIP_FRAMES = 4;
/*      */   public static final int EXPAND_FRAMES = 8;
/*      */   static final int EXPAND_ASM_INSNS = 256;
/*      */   private static final int MAX_BUFFER_SIZE = 1048576;
/*      */   private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;
/*      */   @Deprecated
/*      */   public final byte[] b;
/*      */   public final int header;
/*      */   final byte[] classFileBuffer;
/*      */   private final int[] cpInfoOffsets;
/*      */   private final String[] constantUtf8Values;
/*      */   private final ConstantDynamic[] constantDynamicValues;
/*      */   private final int[] bootstrapMethodOffsets;
/*      */   private final int maxStringLength;
/*      */   
/*      */   public ClassReader(byte[] classFile) {
/*  166 */     this(classFile, 0, classFile.length);
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
/*      */   public ClassReader(byte[] classFileBuffer, int classFileOffset, int classFileLength) {
/*  180 */     this(classFileBuffer, classFileOffset, true);
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
/*      */   ClassReader(byte[] classFileBuffer, int classFileOffset, boolean checkClassVersion) {
/*  194 */     this.classFileBuffer = classFileBuffer;
/*  195 */     this.b = classFileBuffer;
/*      */ 
/*      */     
/*  198 */     if (checkClassVersion && readShort(classFileOffset + 6) > 69) {
/*  199 */       throw new IllegalArgumentException(
/*  200 */           stringConcat$0(readShort(classFileOffset + 6)));
/*      */     }
/*      */ 
/*      */     
/*  204 */     int constantPoolCount = readUnsignedShort(classFileOffset + 8);
/*  205 */     this.cpInfoOffsets = new int[constantPoolCount];
/*  206 */     this.constantUtf8Values = new String[constantPoolCount];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  211 */     int currentCpInfoIndex = 1;
/*  212 */     int currentCpInfoOffset = classFileOffset + 10;
/*  213 */     int currentMaxStringLength = 0;
/*  214 */     boolean hasBootstrapMethods = false;
/*  215 */     boolean hasConstantDynamic = false;
/*      */     
/*  217 */     while (currentCpInfoIndex < constantPoolCount) {
/*  218 */       int cpInfoSize; this.cpInfoOffsets[currentCpInfoIndex++] = currentCpInfoOffset + 1;
/*      */       
/*  220 */       switch (classFileBuffer[currentCpInfoOffset]) {
/*      */         case 3:
/*      */         case 4:
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/*  227 */           cpInfoSize = 5;
/*      */           break;
/*      */         case 17:
/*  230 */           cpInfoSize = 5;
/*  231 */           hasBootstrapMethods = true;
/*  232 */           hasConstantDynamic = true;
/*      */           break;
/*      */         case 18:
/*  235 */           cpInfoSize = 5;
/*  236 */           hasBootstrapMethods = true;
/*      */           break;
/*      */         case 5:
/*      */         case 6:
/*  240 */           cpInfoSize = 9;
/*  241 */           currentCpInfoIndex++;
/*      */           break;
/*      */         case 1:
/*  244 */           cpInfoSize = 3 + readUnsignedShort(currentCpInfoOffset + 1);
/*  245 */           if (cpInfoSize > currentMaxStringLength)
/*      */           {
/*      */ 
/*      */             
/*  249 */             currentMaxStringLength = cpInfoSize;
/*      */           }
/*      */           break;
/*      */         case 15:
/*  253 */           cpInfoSize = 4;
/*      */           break;
/*      */         case 7:
/*      */         case 8:
/*      */         case 16:
/*      */         case 19:
/*      */         case 20:
/*  260 */           cpInfoSize = 3;
/*      */           break;
/*      */         default:
/*  263 */           throw new IllegalArgumentException();
/*      */       } 
/*  265 */       currentCpInfoOffset += cpInfoSize;
/*      */     } 
/*  267 */     this.maxStringLength = currentMaxStringLength;
/*      */     
/*  269 */     this.header = currentCpInfoOffset;
/*      */ 
/*      */     
/*  272 */     this.constantDynamicValues = hasConstantDynamic ? new ConstantDynamic[constantPoolCount] : null;
/*      */ 
/*      */     
/*  275 */     this
/*  276 */       .bootstrapMethodOffsets = hasBootstrapMethods ? readBootstrapMethodsAttribute(currentMaxStringLength) : null;
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
/*      */   public ClassReader(InputStream inputStream) throws IOException {
/*  288 */     this(readStream(inputStream, false));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassReader(String className) throws IOException {
/*  299 */     this(
/*  300 */         readStream(
/*  301 */           ClassLoader.getSystemResourceAsStream(stringConcat$1(className.replace('.', '/'))), true));
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
/*      */   private static byte[] readStream(InputStream inputStream, boolean close) throws IOException {
/*  315 */     if (inputStream == null) {
/*  316 */       throw new IOException("Class not found");
/*      */     }
/*  318 */     int bufferSize = computeBufferSize(inputStream); try {
/*  319 */       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  333 */       if (close) {
/*  334 */         inputStream.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int computeBufferSize(InputStream inputStream) throws IOException {
/*  340 */     int expectedLength = inputStream.available();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  346 */     if (expectedLength < 256) {
/*  347 */       return 4096;
/*      */     }
/*  349 */     return Math.min(expectedLength, 1048576);
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
/*      */   public int getAccess() {
/*  364 */     return readUnsignedShort(this.header);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClassName() {
/*  375 */     return readClass(this.header + 2, new char[this.maxStringLength]);
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
/*      */   public String getSuperName() {
/*  387 */     return readClass(this.header + 4, new char[this.maxStringLength]);
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
/*      */   public String[] getInterfaces() {
/*  399 */     int currentOffset = this.header + 6;
/*  400 */     int interfacesCount = readUnsignedShort(currentOffset);
/*  401 */     String[] interfaces = new String[interfacesCount];
/*  402 */     if (interfacesCount > 0) {
/*  403 */       char[] charBuffer = new char[this.maxStringLength];
/*  404 */       for (int i = 0; i < interfacesCount; i++) {
/*  405 */         currentOffset += 2;
/*  406 */         interfaces[i] = readClass(currentOffset, charBuffer);
/*      */       } 
/*      */     } 
/*  409 */     return interfaces;
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
/*      */   public void accept(ClassVisitor classVisitor, int parsingOptions) {
/*  425 */     accept(classVisitor, new Attribute[0], parsingOptions);
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
/*      */   public void accept(ClassVisitor classVisitor, Attribute[] attributePrototypes, int parsingOptions) {
/*  446 */     Context context = new Context();
/*  447 */     context.attributePrototypes = attributePrototypes;
/*  448 */     context.parsingOptions = parsingOptions;
/*  449 */     context.charBuffer = new char[this.maxStringLength];
/*      */ 
/*      */     
/*  452 */     char[] charBuffer = context.charBuffer;
/*  453 */     int currentOffset = this.header;
/*  454 */     int accessFlags = readUnsignedShort(currentOffset);
/*  455 */     String thisClass = readClass(currentOffset + 2, charBuffer);
/*  456 */     String superClass = readClass(currentOffset + 4, charBuffer);
/*  457 */     String[] interfaces = new String[readUnsignedShort(currentOffset + 6)];
/*  458 */     currentOffset += 8;
/*  459 */     for (int i = 0; i < interfaces.length; i++) {
/*  460 */       interfaces[i] = readClass(currentOffset, charBuffer);
/*  461 */       currentOffset += 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  467 */     int innerClassesOffset = 0;
/*      */     
/*  469 */     int enclosingMethodOffset = 0;
/*      */     
/*  471 */     String signature = null;
/*      */     
/*  473 */     String sourceFile = null;
/*      */     
/*  475 */     String sourceDebugExtension = null;
/*      */     
/*  477 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/*  479 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/*  481 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/*  483 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */     
/*  485 */     int moduleOffset = 0;
/*      */     
/*  487 */     int modulePackagesOffset = 0;
/*      */     
/*  489 */     String moduleMainClass = null;
/*      */     
/*  491 */     String nestHostClass = null;
/*      */     
/*  493 */     int nestMembersOffset = 0;
/*      */     
/*  495 */     int permittedSubclassesOffset = 0;
/*      */     
/*  497 */     int recordOffset = 0;
/*      */ 
/*      */     
/*  500 */     Attribute attributes = null;
/*      */     
/*  502 */     int currentAttributeOffset = getFirstAttributeOffset();
/*  503 */     for (int j = readUnsignedShort(currentAttributeOffset - 2); j > 0; j--) {
/*      */       
/*  505 */       String attributeName = readUTF8(currentAttributeOffset, charBuffer);
/*  506 */       int attributeLength = readInt(currentAttributeOffset + 2);
/*  507 */       currentAttributeOffset += 6;
/*      */ 
/*      */       
/*  510 */       if ("SourceFile".equals(attributeName)) {
/*  511 */         sourceFile = readUTF8(currentAttributeOffset, charBuffer);
/*  512 */       } else if ("InnerClasses".equals(attributeName)) {
/*  513 */         innerClassesOffset = currentAttributeOffset;
/*  514 */       } else if ("EnclosingMethod".equals(attributeName)) {
/*  515 */         enclosingMethodOffset = currentAttributeOffset;
/*  516 */       } else if ("NestHost".equals(attributeName)) {
/*  517 */         nestHostClass = readClass(currentAttributeOffset, charBuffer);
/*  518 */       } else if ("NestMembers".equals(attributeName)) {
/*  519 */         nestMembersOffset = currentAttributeOffset;
/*  520 */       } else if ("PermittedSubclasses".equals(attributeName)) {
/*  521 */         permittedSubclassesOffset = currentAttributeOffset;
/*  522 */       } else if ("Signature".equals(attributeName)) {
/*  523 */         signature = readUTF8(currentAttributeOffset, charBuffer);
/*  524 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/*  525 */         runtimeVisibleAnnotationsOffset = currentAttributeOffset;
/*  526 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/*  527 */         runtimeVisibleTypeAnnotationsOffset = currentAttributeOffset;
/*  528 */       } else if ("Deprecated".equals(attributeName)) {
/*  529 */         accessFlags |= 0x20000;
/*  530 */       } else if ("Synthetic".equals(attributeName)) {
/*  531 */         accessFlags |= 0x1000;
/*  532 */       } else if ("SourceDebugExtension".equals(attributeName)) {
/*  533 */         if (attributeLength > this.classFileBuffer.length - currentAttributeOffset) {
/*  534 */           throw new IllegalArgumentException();
/*      */         }
/*      */         
/*  537 */         sourceDebugExtension = readUtf(currentAttributeOffset, attributeLength, new char[attributeLength]);
/*  538 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/*  539 */         runtimeInvisibleAnnotationsOffset = currentAttributeOffset;
/*  540 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/*  541 */         runtimeInvisibleTypeAnnotationsOffset = currentAttributeOffset;
/*  542 */       } else if ("Record".equals(attributeName)) {
/*  543 */         recordOffset = currentAttributeOffset;
/*  544 */         accessFlags |= 0x10000;
/*  545 */       } else if ("Module".equals(attributeName)) {
/*  546 */         moduleOffset = currentAttributeOffset;
/*  547 */       } else if ("ModuleMainClass".equals(attributeName)) {
/*  548 */         moduleMainClass = readClass(currentAttributeOffset, charBuffer);
/*  549 */       } else if ("ModulePackages".equals(attributeName)) {
/*  550 */         modulePackagesOffset = currentAttributeOffset;
/*  551 */       } else if (!"BootstrapMethods".equals(attributeName)) {
/*      */ 
/*      */         
/*  554 */         Attribute attribute = readAttribute(attributePrototypes, attributeName, currentAttributeOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  562 */         attribute.nextAttribute = attributes;
/*  563 */         attributes = attribute;
/*      */       } 
/*  565 */       currentAttributeOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  570 */     classVisitor.visit(
/*  571 */         readInt(this.cpInfoOffsets[1] - 7), accessFlags, thisClass, signature, superClass, interfaces);
/*      */ 
/*      */     
/*  574 */     if ((parsingOptions & 0x2) == 0 && (sourceFile != null || sourceDebugExtension != null))
/*      */     {
/*  576 */       classVisitor.visitSource(sourceFile, sourceDebugExtension);
/*      */     }
/*      */ 
/*      */     
/*  580 */     if (moduleOffset != 0) {
/*  581 */       readModuleAttributes(classVisitor, context, moduleOffset, modulePackagesOffset, moduleMainClass);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  586 */     if (nestHostClass != null) {
/*  587 */       classVisitor.visitNestHost(nestHostClass);
/*      */     }
/*      */ 
/*      */     
/*  591 */     if (enclosingMethodOffset != 0) {
/*  592 */       String className = readClass(enclosingMethodOffset, charBuffer);
/*  593 */       int methodIndex = readUnsignedShort(enclosingMethodOffset + 2);
/*  594 */       String name = (methodIndex == 0) ? null : readUTF8(this.cpInfoOffsets[methodIndex], charBuffer);
/*  595 */       String type = (methodIndex == 0) ? null : readUTF8(this.cpInfoOffsets[methodIndex] + 2, charBuffer);
/*  596 */       classVisitor.visitOuterClass(className, name, type);
/*      */     } 
/*      */ 
/*      */     
/*  600 */     if (runtimeVisibleAnnotationsOffset != 0) {
/*  601 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/*  602 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/*  603 */       while (numAnnotations-- > 0) {
/*      */         
/*  605 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  606 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  609 */         currentAnnotationOffset = readElementValues(classVisitor
/*  610 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  618 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/*  619 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/*  620 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/*  621 */       while (numAnnotations-- > 0) {
/*      */         
/*  623 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  624 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  627 */         currentAnnotationOffset = readElementValues(classVisitor
/*  628 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  636 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/*  637 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/*  638 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/*  639 */       while (numAnnotations-- > 0) {
/*      */         
/*  641 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/*  643 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  644 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  647 */         currentAnnotationOffset = readElementValues(classVisitor
/*  648 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  660 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/*  661 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/*  662 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/*  663 */       while (numAnnotations-- > 0) {
/*      */         
/*  665 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/*  667 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  668 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  671 */         currentAnnotationOffset = readElementValues(classVisitor
/*  672 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  684 */     while (attributes != null) {
/*      */       
/*  686 */       Attribute nextAttribute = attributes.nextAttribute;
/*  687 */       attributes.nextAttribute = null;
/*  688 */       classVisitor.visitAttribute(attributes);
/*  689 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/*  693 */     if (nestMembersOffset != 0) {
/*  694 */       int numberOfNestMembers = readUnsignedShort(nestMembersOffset);
/*  695 */       int currentNestMemberOffset = nestMembersOffset + 2;
/*  696 */       while (numberOfNestMembers-- > 0) {
/*  697 */         classVisitor.visitNestMember(readClass(currentNestMemberOffset, charBuffer));
/*  698 */         currentNestMemberOffset += 2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  703 */     if (permittedSubclassesOffset != 0) {
/*  704 */       int numberOfPermittedSubclasses = readUnsignedShort(permittedSubclassesOffset);
/*  705 */       int currentPermittedSubclassesOffset = permittedSubclassesOffset + 2;
/*  706 */       while (numberOfPermittedSubclasses-- > 0) {
/*  707 */         classVisitor.visitPermittedSubclass(
/*  708 */             readClass(currentPermittedSubclassesOffset, charBuffer));
/*  709 */         currentPermittedSubclassesOffset += 2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  714 */     if (innerClassesOffset != 0) {
/*  715 */       int numberOfClasses = readUnsignedShort(innerClassesOffset);
/*  716 */       int currentClassesOffset = innerClassesOffset + 2;
/*  717 */       while (numberOfClasses-- > 0) {
/*  718 */         classVisitor.visitInnerClass(
/*  719 */             readClass(currentClassesOffset, charBuffer), 
/*  720 */             readClass(currentClassesOffset + 2, charBuffer), 
/*  721 */             readUTF8(currentClassesOffset + 4, charBuffer), 
/*  722 */             readUnsignedShort(currentClassesOffset + 6));
/*  723 */         currentClassesOffset += 8;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  728 */     if (recordOffset != 0) {
/*  729 */       int recordComponentsCount = readUnsignedShort(recordOffset);
/*  730 */       recordOffset += 2;
/*  731 */       while (recordComponentsCount-- > 0) {
/*  732 */         recordOffset = readRecordComponent(classVisitor, context, recordOffset);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  737 */     int fieldsCount = readUnsignedShort(currentOffset);
/*  738 */     currentOffset += 2;
/*  739 */     while (fieldsCount-- > 0) {
/*  740 */       currentOffset = readField(classVisitor, context, currentOffset);
/*      */     }
/*  742 */     int methodsCount = readUnsignedShort(currentOffset);
/*  743 */     currentOffset += 2;
/*  744 */     while (methodsCount-- > 0) {
/*  745 */       currentOffset = readMethod(classVisitor, context, currentOffset);
/*      */     }
/*      */ 
/*      */     
/*  749 */     classVisitor.visitEnd();
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
/*      */   private void readModuleAttributes(ClassVisitor classVisitor, Context context, int moduleOffset, int modulePackagesOffset, String moduleMainClass) {
/*  774 */     char[] buffer = context.charBuffer;
/*      */ 
/*      */     
/*  777 */     int currentOffset = moduleOffset;
/*  778 */     String moduleName = readModule(currentOffset, buffer);
/*  779 */     int moduleFlags = readUnsignedShort(currentOffset + 2);
/*  780 */     String moduleVersion = readUTF8(currentOffset + 4, buffer);
/*  781 */     currentOffset += 6;
/*  782 */     ModuleVisitor moduleVisitor = classVisitor.visitModule(moduleName, moduleFlags, moduleVersion);
/*  783 */     if (moduleVisitor == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  788 */     if (moduleMainClass != null) {
/*  789 */       moduleVisitor.visitMainClass(moduleMainClass);
/*      */     }
/*      */ 
/*      */     
/*  793 */     if (modulePackagesOffset != 0) {
/*  794 */       int packageCount = readUnsignedShort(modulePackagesOffset);
/*  795 */       int currentPackageOffset = modulePackagesOffset + 2;
/*  796 */       while (packageCount-- > 0) {
/*  797 */         moduleVisitor.visitPackage(readPackage(currentPackageOffset, buffer));
/*  798 */         currentPackageOffset += 2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  803 */     int requiresCount = readUnsignedShort(currentOffset);
/*  804 */     currentOffset += 2;
/*  805 */     while (requiresCount-- > 0) {
/*      */       
/*  807 */       String requires = readModule(currentOffset, buffer);
/*  808 */       int requiresFlags = readUnsignedShort(currentOffset + 2);
/*  809 */       String requiresVersion = readUTF8(currentOffset + 4, buffer);
/*  810 */       currentOffset += 6;
/*  811 */       moduleVisitor.visitRequire(requires, requiresFlags, requiresVersion);
/*      */     } 
/*      */ 
/*      */     
/*  815 */     int exportsCount = readUnsignedShort(currentOffset);
/*  816 */     currentOffset += 2;
/*  817 */     while (exportsCount-- > 0) {
/*      */ 
/*      */       
/*  820 */       String exports = readPackage(currentOffset, buffer);
/*  821 */       int exportsFlags = readUnsignedShort(currentOffset + 2);
/*  822 */       int exportsToCount = readUnsignedShort(currentOffset + 4);
/*  823 */       currentOffset += 6;
/*  824 */       String[] exportsTo = null;
/*  825 */       if (exportsToCount != 0) {
/*  826 */         exportsTo = new String[exportsToCount];
/*  827 */         for (int i = 0; i < exportsToCount; i++) {
/*  828 */           exportsTo[i] = readModule(currentOffset, buffer);
/*  829 */           currentOffset += 2;
/*      */         } 
/*      */       } 
/*  832 */       moduleVisitor.visitExport(exports, exportsFlags, exportsTo);
/*      */     } 
/*      */ 
/*      */     
/*  836 */     int opensCount = readUnsignedShort(currentOffset);
/*  837 */     currentOffset += 2;
/*  838 */     while (opensCount-- > 0) {
/*      */       
/*  840 */       String opens = readPackage(currentOffset, buffer);
/*  841 */       int opensFlags = readUnsignedShort(currentOffset + 2);
/*  842 */       int opensToCount = readUnsignedShort(currentOffset + 4);
/*  843 */       currentOffset += 6;
/*  844 */       String[] opensTo = null;
/*  845 */       if (opensToCount != 0) {
/*  846 */         opensTo = new String[opensToCount];
/*  847 */         for (int i = 0; i < opensToCount; i++) {
/*  848 */           opensTo[i] = readModule(currentOffset, buffer);
/*  849 */           currentOffset += 2;
/*      */         } 
/*      */       } 
/*  852 */       moduleVisitor.visitOpen(opens, opensFlags, opensTo);
/*      */     } 
/*      */ 
/*      */     
/*  856 */     int usesCount = readUnsignedShort(currentOffset);
/*  857 */     currentOffset += 2;
/*  858 */     while (usesCount-- > 0) {
/*  859 */       moduleVisitor.visitUse(readClass(currentOffset, buffer));
/*  860 */       currentOffset += 2;
/*      */     } 
/*      */ 
/*      */     
/*  864 */     int providesCount = readUnsignedShort(currentOffset);
/*  865 */     currentOffset += 2;
/*  866 */     while (providesCount-- > 0) {
/*      */       
/*  868 */       String provides = readClass(currentOffset, buffer);
/*  869 */       int providesWithCount = readUnsignedShort(currentOffset + 2);
/*  870 */       currentOffset += 4;
/*  871 */       String[] providesWith = new String[providesWithCount];
/*  872 */       for (int i = 0; i < providesWithCount; i++) {
/*  873 */         providesWith[i] = readClass(currentOffset, buffer);
/*  874 */         currentOffset += 2;
/*      */       } 
/*  876 */       moduleVisitor.visitProvide(provides, providesWith);
/*      */     } 
/*      */ 
/*      */     
/*  880 */     moduleVisitor.visitEnd();
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
/*      */   private int readRecordComponent(ClassVisitor classVisitor, Context context, int recordComponentOffset) {
/*  893 */     char[] charBuffer = context.charBuffer;
/*      */     
/*  895 */     int currentOffset = recordComponentOffset;
/*  896 */     String name = readUTF8(currentOffset, charBuffer);
/*  897 */     String descriptor = readUTF8(currentOffset + 2, charBuffer);
/*  898 */     currentOffset += 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     String signature = null;
/*      */     
/*  907 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/*  909 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/*  911 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/*  913 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */ 
/*      */     
/*  916 */     Attribute attributes = null;
/*      */     
/*  918 */     int attributesCount = readUnsignedShort(currentOffset);
/*  919 */     currentOffset += 2;
/*  920 */     while (attributesCount-- > 0) {
/*      */       
/*  922 */       String attributeName = readUTF8(currentOffset, charBuffer);
/*  923 */       int attributeLength = readInt(currentOffset + 2);
/*  924 */       currentOffset += 6;
/*      */ 
/*      */       
/*  927 */       if ("Signature".equals(attributeName)) {
/*  928 */         signature = readUTF8(currentOffset, charBuffer);
/*  929 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/*  930 */         runtimeVisibleAnnotationsOffset = currentOffset;
/*  931 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/*  932 */         runtimeVisibleTypeAnnotationsOffset = currentOffset;
/*  933 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/*  934 */         runtimeInvisibleAnnotationsOffset = currentOffset;
/*  935 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/*  936 */         runtimeInvisibleTypeAnnotationsOffset = currentOffset;
/*      */       } else {
/*      */         
/*  939 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  947 */         attribute.nextAttribute = attributes;
/*  948 */         attributes = attribute;
/*      */       } 
/*  950 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */     
/*  954 */     RecordComponentVisitor recordComponentVisitor = classVisitor.visitRecordComponent(name, descriptor, signature);
/*  955 */     if (recordComponentVisitor == null) {
/*  956 */       return currentOffset;
/*      */     }
/*      */ 
/*      */     
/*  960 */     if (runtimeVisibleAnnotationsOffset != 0) {
/*  961 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/*  962 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/*  963 */       while (numAnnotations-- > 0) {
/*      */         
/*  965 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  966 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  969 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/*  970 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  978 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/*  979 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/*  980 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/*  981 */       while (numAnnotations-- > 0) {
/*      */         
/*  983 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  984 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  987 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/*  988 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  996 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/*  997 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/*  998 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/*  999 */       while (numAnnotations-- > 0) {
/*      */         
/* 1001 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1003 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1004 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1007 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/* 1008 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1020 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/* 1021 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/* 1022 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/* 1023 */       while (numAnnotations-- > 0) {
/*      */         
/* 1025 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1027 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1028 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1031 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/* 1032 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1044 */     while (attributes != null) {
/*      */       
/* 1046 */       Attribute nextAttribute = attributes.nextAttribute;
/* 1047 */       attributes.nextAttribute = null;
/* 1048 */       recordComponentVisitor.visitAttribute(attributes);
/* 1049 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 1053 */     recordComponentVisitor.visitEnd();
/* 1054 */     return currentOffset;
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
/*      */   private int readField(ClassVisitor classVisitor, Context context, int fieldInfoOffset) {
/* 1067 */     char[] charBuffer = context.charBuffer;
/*      */ 
/*      */     
/* 1070 */     int currentOffset = fieldInfoOffset;
/* 1071 */     int accessFlags = readUnsignedShort(currentOffset);
/* 1072 */     String name = readUTF8(currentOffset + 2, charBuffer);
/* 1073 */     String descriptor = readUTF8(currentOffset + 4, charBuffer);
/* 1074 */     currentOffset += 6;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1079 */     Object constantValue = null;
/*      */     
/* 1081 */     String signature = null;
/*      */     
/* 1083 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/* 1085 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/* 1087 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/* 1089 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */ 
/*      */     
/* 1092 */     Attribute attributes = null;
/*      */     
/* 1094 */     int attributesCount = readUnsignedShort(currentOffset);
/* 1095 */     currentOffset += 2;
/* 1096 */     while (attributesCount-- > 0) {
/*      */       
/* 1098 */       String attributeName = readUTF8(currentOffset, charBuffer);
/* 1099 */       int attributeLength = readInt(currentOffset + 2);
/* 1100 */       currentOffset += 6;
/*      */ 
/*      */       
/* 1103 */       if ("ConstantValue".equals(attributeName)) {
/* 1104 */         int constantvalueIndex = readUnsignedShort(currentOffset);
/* 1105 */         constantValue = (constantvalueIndex == 0) ? null : readConst(constantvalueIndex, charBuffer);
/* 1106 */       } else if ("Signature".equals(attributeName)) {
/* 1107 */         signature = readUTF8(currentOffset, charBuffer);
/* 1108 */       } else if ("Deprecated".equals(attributeName)) {
/* 1109 */         accessFlags |= 0x20000;
/* 1110 */       } else if ("Synthetic".equals(attributeName)) {
/* 1111 */         accessFlags |= 0x1000;
/* 1112 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/* 1113 */         runtimeVisibleAnnotationsOffset = currentOffset;
/* 1114 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/* 1115 */         runtimeVisibleTypeAnnotationsOffset = currentOffset;
/* 1116 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/* 1117 */         runtimeInvisibleAnnotationsOffset = currentOffset;
/* 1118 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/* 1119 */         runtimeInvisibleTypeAnnotationsOffset = currentOffset;
/*      */       } else {
/*      */         
/* 1122 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1130 */         attribute.nextAttribute = attributes;
/* 1131 */         attributes = attribute;
/*      */       } 
/* 1133 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1138 */     FieldVisitor fieldVisitor = classVisitor.visitField(accessFlags, name, descriptor, signature, constantValue);
/* 1139 */     if (fieldVisitor == null) {
/* 1140 */       return currentOffset;
/*      */     }
/*      */ 
/*      */     
/* 1144 */     if (runtimeVisibleAnnotationsOffset != 0) {
/* 1145 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/* 1146 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/* 1147 */       while (numAnnotations-- > 0) {
/*      */         
/* 1149 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1150 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1153 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1154 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1162 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/* 1163 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/* 1164 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/* 1165 */       while (numAnnotations-- > 0) {
/*      */         
/* 1167 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1168 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1171 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1172 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1180 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/* 1181 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/* 1182 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/* 1183 */       while (numAnnotations-- > 0) {
/*      */         
/* 1185 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1187 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1188 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1191 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1192 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1204 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/* 1205 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/* 1206 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/* 1207 */       while (numAnnotations-- > 0) {
/*      */         
/* 1209 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1211 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1212 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1215 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1216 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1228 */     while (attributes != null) {
/*      */       
/* 1230 */       Attribute nextAttribute = attributes.nextAttribute;
/* 1231 */       attributes.nextAttribute = null;
/* 1232 */       fieldVisitor.visitAttribute(attributes);
/* 1233 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 1237 */     fieldVisitor.visitEnd();
/* 1238 */     return currentOffset;
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
/*      */   private int readMethod(ClassVisitor classVisitor, Context context, int methodInfoOffset) {
/* 1251 */     char[] charBuffer = context.charBuffer;
/*      */ 
/*      */     
/* 1254 */     int currentOffset = methodInfoOffset;
/* 1255 */     context.currentMethodAccessFlags = readUnsignedShort(currentOffset);
/* 1256 */     context.currentMethodName = readUTF8(currentOffset + 2, charBuffer);
/* 1257 */     context.currentMethodDescriptor = readUTF8(currentOffset + 4, charBuffer);
/* 1258 */     currentOffset += 6;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1263 */     int codeOffset = 0;
/*      */     
/* 1265 */     int exceptionsOffset = 0;
/*      */     
/* 1267 */     String[] exceptions = null;
/*      */     
/* 1269 */     boolean synthetic = false;
/*      */     
/* 1271 */     int signatureIndex = 0;
/*      */     
/* 1273 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/* 1275 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/* 1277 */     int runtimeVisibleParameterAnnotationsOffset = 0;
/*      */     
/* 1279 */     int runtimeInvisibleParameterAnnotationsOffset = 0;
/*      */     
/* 1281 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/* 1283 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */     
/* 1285 */     int annotationDefaultOffset = 0;
/*      */     
/* 1287 */     int methodParametersOffset = 0;
/*      */ 
/*      */     
/* 1290 */     Attribute attributes = null;
/*      */     
/* 1292 */     int attributesCount = readUnsignedShort(currentOffset);
/* 1293 */     currentOffset += 2;
/* 1294 */     while (attributesCount-- > 0) {
/*      */       
/* 1296 */       String attributeName = readUTF8(currentOffset, charBuffer);
/* 1297 */       int attributeLength = readInt(currentOffset + 2);
/* 1298 */       currentOffset += 6;
/*      */ 
/*      */       
/* 1301 */       if ("Code".equals(attributeName)) {
/* 1302 */         if ((context.parsingOptions & 0x1) == 0) {
/* 1303 */           codeOffset = currentOffset;
/*      */         }
/* 1305 */       } else if ("Exceptions".equals(attributeName)) {
/* 1306 */         exceptionsOffset = currentOffset;
/* 1307 */         exceptions = new String[readUnsignedShort(exceptionsOffset)];
/* 1308 */         int currentExceptionOffset = exceptionsOffset + 2;
/* 1309 */         for (int i = 0; i < exceptions.length; i++) {
/* 1310 */           exceptions[i] = readClass(currentExceptionOffset, charBuffer);
/* 1311 */           currentExceptionOffset += 2;
/*      */         } 
/* 1313 */       } else if ("Signature".equals(attributeName)) {
/* 1314 */         signatureIndex = readUnsignedShort(currentOffset);
/* 1315 */       } else if ("Deprecated".equals(attributeName)) {
/* 1316 */         context.currentMethodAccessFlags |= 0x20000;
/* 1317 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/* 1318 */         runtimeVisibleAnnotationsOffset = currentOffset;
/* 1319 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/* 1320 */         runtimeVisibleTypeAnnotationsOffset = currentOffset;
/* 1321 */       } else if ("AnnotationDefault".equals(attributeName)) {
/* 1322 */         annotationDefaultOffset = currentOffset;
/* 1323 */       } else if ("Synthetic".equals(attributeName)) {
/* 1324 */         synthetic = true;
/* 1325 */         context.currentMethodAccessFlags |= 0x1000;
/* 1326 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/* 1327 */         runtimeInvisibleAnnotationsOffset = currentOffset;
/* 1328 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/* 1329 */         runtimeInvisibleTypeAnnotationsOffset = currentOffset;
/* 1330 */       } else if ("RuntimeVisibleParameterAnnotations".equals(attributeName)) {
/* 1331 */         runtimeVisibleParameterAnnotationsOffset = currentOffset;
/* 1332 */       } else if ("RuntimeInvisibleParameterAnnotations".equals(attributeName)) {
/* 1333 */         runtimeInvisibleParameterAnnotationsOffset = currentOffset;
/* 1334 */       } else if ("MethodParameters".equals(attributeName)) {
/* 1335 */         methodParametersOffset = currentOffset;
/*      */       } else {
/*      */         
/* 1338 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1346 */         attribute.nextAttribute = attributes;
/* 1347 */         attributes = attribute;
/*      */       } 
/* 1349 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1354 */     MethodVisitor methodVisitor = classVisitor.visitMethod(context.currentMethodAccessFlags, context.currentMethodName, context.currentMethodDescriptor, 
/*      */ 
/*      */ 
/*      */         
/* 1358 */         (signatureIndex == 0) ? null : readUtf(signatureIndex, charBuffer), exceptions);
/*      */     
/* 1360 */     if (methodVisitor == null) {
/* 1361 */       return currentOffset;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1368 */     if (methodVisitor instanceof MethodWriter) {
/* 1369 */       MethodWriter methodWriter = (MethodWriter)methodVisitor;
/* 1370 */       if (methodWriter.canCopyMethodAttributes(this, synthetic, ((context.currentMethodAccessFlags & 0x20000) != 0), 
/*      */ 
/*      */ 
/*      */           
/* 1374 */           readUnsignedShort(methodInfoOffset + 4), signatureIndex, exceptionsOffset)) {
/*      */ 
/*      */         
/* 1377 */         methodWriter.setMethodAttributesSource(methodInfoOffset, currentOffset - methodInfoOffset);
/* 1378 */         return currentOffset;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1383 */     if (methodParametersOffset != 0 && (context.parsingOptions & 0x2) == 0) {
/* 1384 */       int parametersCount = readByte(methodParametersOffset);
/* 1385 */       int currentParameterOffset = methodParametersOffset + 1;
/* 1386 */       while (parametersCount-- > 0) {
/*      */         
/* 1388 */         methodVisitor.visitParameter(
/* 1389 */             readUTF8(currentParameterOffset, charBuffer), 
/* 1390 */             readUnsignedShort(currentParameterOffset + 2));
/* 1391 */         currentParameterOffset += 4;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1396 */     if (annotationDefaultOffset != 0) {
/* 1397 */       AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
/* 1398 */       readElementValue(annotationVisitor, annotationDefaultOffset, null, charBuffer);
/* 1399 */       if (annotationVisitor != null) {
/* 1400 */         annotationVisitor.visitEnd();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1405 */     if (runtimeVisibleAnnotationsOffset != 0) {
/* 1406 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/* 1407 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/* 1408 */       while (numAnnotations-- > 0) {
/*      */         
/* 1410 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1411 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1414 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1415 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1423 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/* 1424 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/* 1425 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/* 1426 */       while (numAnnotations-- > 0) {
/*      */         
/* 1428 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1429 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1432 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1433 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1441 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/* 1442 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/* 1443 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/* 1444 */       while (numAnnotations-- > 0) {
/*      */         
/* 1446 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1448 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1449 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1452 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1453 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1465 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/* 1466 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/* 1467 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/* 1468 */       while (numAnnotations-- > 0) {
/*      */         
/* 1470 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1472 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1473 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1476 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1477 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1489 */     if (runtimeVisibleParameterAnnotationsOffset != 0) {
/* 1490 */       readParameterAnnotations(methodVisitor, context, runtimeVisibleParameterAnnotationsOffset, true);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1495 */     if (runtimeInvisibleParameterAnnotationsOffset != 0) {
/* 1496 */       readParameterAnnotations(methodVisitor, context, runtimeInvisibleParameterAnnotationsOffset, false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1501 */     while (attributes != null) {
/*      */       
/* 1503 */       Attribute nextAttribute = attributes.nextAttribute;
/* 1504 */       attributes.nextAttribute = null;
/* 1505 */       methodVisitor.visitAttribute(attributes);
/* 1506 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 1510 */     if (codeOffset != 0) {
/* 1511 */       methodVisitor.visitCode();
/* 1512 */       readCode(methodVisitor, context, codeOffset);
/*      */     } 
/*      */ 
/*      */     
/* 1516 */     methodVisitor.visitEnd();
/* 1517 */     return currentOffset;
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
/*      */   private void readCode(MethodVisitor methodVisitor, Context context, int codeOffset) {
/* 1534 */     int currentOffset = codeOffset;
/*      */ 
/*      */     
/* 1537 */     byte[] classBuffer = this.classFileBuffer;
/* 1538 */     char[] charBuffer = context.charBuffer;
/* 1539 */     int maxStack = readUnsignedShort(currentOffset);
/* 1540 */     int maxLocals = readUnsignedShort(currentOffset + 2);
/* 1541 */     int codeLength = readInt(currentOffset + 4);
/* 1542 */     currentOffset += 8;
/* 1543 */     if (codeLength > this.classFileBuffer.length - currentOffset) {
/* 1544 */       throw new IllegalArgumentException();
/*      */     }
/*      */ 
/*      */     
/* 1548 */     int bytecodeStartOffset = currentOffset;
/* 1549 */     int bytecodeEndOffset = currentOffset + codeLength;
/* 1550 */     Label[] labels = context.currentMethodLabels = new Label[codeLength + 1];
/* 1551 */     while (currentOffset < bytecodeEndOffset) {
/* 1552 */       int numTableEntries, numSwitchCases, bytecodeOffset = currentOffset - bytecodeStartOffset;
/* 1553 */       int opcode = classBuffer[currentOffset] & 0xFF;
/* 1554 */       switch (opcode) {
/*      */         case 0:
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 6:
/*      */         case 7:
/*      */         case 8:
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/*      */         case 13:
/*      */         case 14:
/*      */         case 15:
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         case 32:
/*      */         case 33:
/*      */         case 34:
/*      */         case 35:
/*      */         case 36:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         case 40:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/*      */         case 46:
/*      */         case 47:
/*      */         case 48:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 59:
/*      */         case 60:
/*      */         case 61:
/*      */         case 62:
/*      */         case 63:
/*      */         case 64:
/*      */         case 65:
/*      */         case 66:
/*      */         case 67:
/*      */         case 68:
/*      */         case 69:
/*      */         case 70:
/*      */         case 71:
/*      */         case 72:
/*      */         case 73:
/*      */         case 74:
/*      */         case 75:
/*      */         case 76:
/*      */         case 77:
/*      */         case 78:
/*      */         case 79:
/*      */         case 80:
/*      */         case 81:
/*      */         case 82:
/*      */         case 83:
/*      */         case 84:
/*      */         case 85:
/*      */         case 86:
/*      */         case 87:
/*      */         case 88:
/*      */         case 89:
/*      */         case 90:
/*      */         case 91:
/*      */         case 92:
/*      */         case 93:
/*      */         case 94:
/*      */         case 95:
/*      */         case 96:
/*      */         case 97:
/*      */         case 98:
/*      */         case 99:
/*      */         case 100:
/*      */         case 101:
/*      */         case 102:
/*      */         case 103:
/*      */         case 104:
/*      */         case 105:
/*      */         case 106:
/*      */         case 107:
/*      */         case 108:
/*      */         case 109:
/*      */         case 110:
/*      */         case 111:
/*      */         case 112:
/*      */         case 113:
/*      */         case 114:
/*      */         case 115:
/*      */         case 116:
/*      */         case 117:
/*      */         case 118:
/*      */         case 119:
/*      */         case 120:
/*      */         case 121:
/*      */         case 122:
/*      */         case 123:
/*      */         case 124:
/*      */         case 125:
/*      */         case 126:
/*      */         case 127:
/*      */         case 128:
/*      */         case 129:
/*      */         case 130:
/*      */         case 131:
/*      */         case 133:
/*      */         case 134:
/*      */         case 135:
/*      */         case 136:
/*      */         case 137:
/*      */         case 138:
/*      */         case 139:
/*      */         case 140:
/*      */         case 141:
/*      */         case 142:
/*      */         case 143:
/*      */         case 144:
/*      */         case 145:
/*      */         case 146:
/*      */         case 147:
/*      */         case 148:
/*      */         case 149:
/*      */         case 150:
/*      */         case 151:
/*      */         case 152:
/*      */         case 172:
/*      */         case 173:
/*      */         case 174:
/*      */         case 175:
/*      */         case 176:
/*      */         case 177:
/*      */         case 190:
/*      */         case 191:
/*      */         case 194:
/*      */         case 195:
/* 1702 */           currentOffset++;
/*      */           continue;
/*      */         case 153:
/*      */         case 154:
/*      */         case 155:
/*      */         case 156:
/*      */         case 157:
/*      */         case 158:
/*      */         case 159:
/*      */         case 160:
/*      */         case 161:
/*      */         case 162:
/*      */         case 163:
/*      */         case 164:
/*      */         case 165:
/*      */         case 166:
/*      */         case 167:
/*      */         case 168:
/*      */         case 198:
/*      */         case 199:
/* 1722 */           createLabel(bytecodeOffset + readShort(currentOffset + 1), labels);
/* 1723 */           currentOffset += 3;
/*      */           continue;
/*      */         case 202:
/*      */         case 203:
/*      */         case 204:
/*      */         case 205:
/*      */         case 206:
/*      */         case 207:
/*      */         case 208:
/*      */         case 209:
/*      */         case 210:
/*      */         case 211:
/*      */         case 212:
/*      */         case 213:
/*      */         case 214:
/*      */         case 215:
/*      */         case 216:
/*      */         case 217:
/*      */         case 218:
/*      */         case 219:
/* 1743 */           createLabel(bytecodeOffset + readUnsignedShort(currentOffset + 1), labels);
/* 1744 */           currentOffset += 3;
/*      */           continue;
/*      */         case 200:
/*      */         case 201:
/*      */         case 220:
/* 1749 */           createLabel(bytecodeOffset + readInt(currentOffset + 1), labels);
/* 1750 */           currentOffset += 5;
/*      */           continue;
/*      */         case 196:
/* 1753 */           switch (classBuffer[currentOffset + 1] & 0xFF) {
/*      */             case 21:
/*      */             case 22:
/*      */             case 23:
/*      */             case 24:
/*      */             case 25:
/*      */             case 54:
/*      */             case 55:
/*      */             case 56:
/*      */             case 57:
/*      */             case 58:
/*      */             case 169:
/* 1765 */               currentOffset += 4;
/*      */               continue;
/*      */             case 132:
/* 1768 */               currentOffset += 6;
/*      */               continue;
/*      */           } 
/* 1771 */           throw new IllegalArgumentException();
/*      */ 
/*      */ 
/*      */         
/*      */         case 170:
/* 1776 */           currentOffset += 4 - (bytecodeOffset & 0x3);
/*      */           
/* 1778 */           createLabel(bytecodeOffset + readInt(currentOffset), labels);
/* 1779 */           numTableEntries = readInt(currentOffset + 8) - readInt(currentOffset + 4) + 1;
/* 1780 */           currentOffset += 12;
/*      */           
/* 1782 */           while (numTableEntries-- > 0) {
/* 1783 */             createLabel(bytecodeOffset + readInt(currentOffset), labels);
/* 1784 */             currentOffset += 4;
/*      */           } 
/*      */           continue;
/*      */         
/*      */         case 171:
/* 1789 */           currentOffset += 4 - (bytecodeOffset & 0x3);
/*      */           
/* 1791 */           createLabel(bytecodeOffset + readInt(currentOffset), labels);
/* 1792 */           numSwitchCases = readInt(currentOffset + 4);
/* 1793 */           currentOffset += 8;
/*      */           
/* 1795 */           while (numSwitchCases-- > 0) {
/* 1796 */             createLabel(bytecodeOffset + readInt(currentOffset + 4), labels);
/* 1797 */             currentOffset += 8;
/*      */           } 
/*      */           continue;
/*      */         case 16:
/*      */         case 18:
/*      */         case 21:
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*      */         case 58:
/*      */         case 169:
/*      */         case 188:
/* 1814 */           currentOffset += 2;
/*      */           continue;
/*      */         case 17:
/*      */         case 19:
/*      */         case 20:
/*      */         case 132:
/*      */         case 178:
/*      */         case 179:
/*      */         case 180:
/*      */         case 181:
/*      */         case 182:
/*      */         case 183:
/*      */         case 184:
/*      */         case 187:
/*      */         case 189:
/*      */         case 192:
/*      */         case 193:
/* 1831 */           currentOffset += 3;
/*      */           continue;
/*      */         case 185:
/*      */         case 186:
/* 1835 */           currentOffset += 5;
/*      */           continue;
/*      */         case 197:
/* 1838 */           currentOffset += 4;
/*      */           continue;
/*      */       } 
/* 1841 */       throw new IllegalArgumentException();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1847 */     int exceptionTableLength = readUnsignedShort(currentOffset);
/* 1848 */     currentOffset += 2;
/* 1849 */     while (exceptionTableLength-- > 0) {
/* 1850 */       Label start = createLabel(readUnsignedShort(currentOffset), labels);
/* 1851 */       Label end = createLabel(readUnsignedShort(currentOffset + 2), labels);
/* 1852 */       Label handler = createLabel(readUnsignedShort(currentOffset + 4), labels);
/* 1853 */       String catchType = readUTF8(this.cpInfoOffsets[readUnsignedShort(currentOffset + 6)], charBuffer);
/* 1854 */       currentOffset += 8;
/* 1855 */       methodVisitor.visitTryCatchBlock(start, end, handler, catchType);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1864 */     int stackMapFrameOffset = 0;
/*      */     
/* 1866 */     int stackMapTableEndOffset = 0;
/*      */     
/* 1868 */     boolean compressedFrames = true;
/*      */     
/* 1870 */     int localVariableTableOffset = 0;
/*      */     
/* 1872 */     int localVariableTypeTableOffset = 0;
/*      */ 
/*      */     
/* 1875 */     int[] visibleTypeAnnotationOffsets = null;
/*      */ 
/*      */     
/* 1878 */     int[] invisibleTypeAnnotationOffsets = null;
/*      */ 
/*      */     
/* 1881 */     Attribute attributes = null;
/*      */     
/* 1883 */     int attributesCount = readUnsignedShort(currentOffset);
/* 1884 */     currentOffset += 2;
/* 1885 */     while (attributesCount-- > 0) {
/*      */       
/* 1887 */       String attributeName = readUTF8(currentOffset, charBuffer);
/* 1888 */       int attributeLength = readInt(currentOffset + 2);
/* 1889 */       currentOffset += 6;
/* 1890 */       if ("LocalVariableTable".equals(attributeName)) {
/* 1891 */         if ((context.parsingOptions & 0x2) == 0) {
/* 1892 */           localVariableTableOffset = currentOffset;
/*      */           
/* 1894 */           int currentLocalVariableTableOffset = currentOffset;
/* 1895 */           int localVariableTableLength = readUnsignedShort(currentLocalVariableTableOffset);
/* 1896 */           currentLocalVariableTableOffset += 2;
/* 1897 */           while (localVariableTableLength-- > 0) {
/* 1898 */             int startPc = readUnsignedShort(currentLocalVariableTableOffset);
/* 1899 */             createDebugLabel(startPc, labels);
/* 1900 */             int length = readUnsignedShort(currentLocalVariableTableOffset + 2);
/* 1901 */             createDebugLabel(startPc + length, labels);
/*      */             
/* 1903 */             currentLocalVariableTableOffset += 10;
/*      */           } 
/*      */         } 
/* 1906 */       } else if ("LocalVariableTypeTable".equals(attributeName)) {
/* 1907 */         localVariableTypeTableOffset = currentOffset;
/*      */       
/*      */       }
/* 1910 */       else if ("LineNumberTable".equals(attributeName)) {
/* 1911 */         if ((context.parsingOptions & 0x2) == 0) {
/*      */           
/* 1913 */           int currentLineNumberTableOffset = currentOffset;
/* 1914 */           int lineNumberTableLength = readUnsignedShort(currentLineNumberTableOffset);
/* 1915 */           currentLineNumberTableOffset += 2;
/* 1916 */           while (lineNumberTableLength-- > 0) {
/* 1917 */             int startPc = readUnsignedShort(currentLineNumberTableOffset);
/* 1918 */             int lineNumber = readUnsignedShort(currentLineNumberTableOffset + 2);
/* 1919 */             currentLineNumberTableOffset += 4;
/* 1920 */             createDebugLabel(startPc, labels);
/* 1921 */             labels[startPc].addLineNumber(lineNumber);
/*      */           } 
/*      */         } 
/* 1924 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/*      */         
/* 1926 */         visibleTypeAnnotationOffsets = readTypeAnnotations(methodVisitor, context, currentOffset, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1933 */       else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/*      */         
/* 1935 */         invisibleTypeAnnotationOffsets = readTypeAnnotations(methodVisitor, context, currentOffset, false);
/*      */       }
/* 1937 */       else if ("StackMapTable".equals(attributeName)) {
/* 1938 */         if ((context.parsingOptions & 0x4) == 0) {
/* 1939 */           stackMapFrameOffset = currentOffset + 2;
/* 1940 */           stackMapTableEndOffset = currentOffset + attributeLength;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1951 */       else if ("StackMap".equals(attributeName)) {
/* 1952 */         if ((context.parsingOptions & 0x4) == 0) {
/* 1953 */           stackMapFrameOffset = currentOffset + 2;
/* 1954 */           stackMapTableEndOffset = currentOffset + attributeLength;
/* 1955 */           compressedFrames = false;
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1963 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, codeOffset, labels);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1971 */         attribute.nextAttribute = attributes;
/* 1972 */         attributes = attribute;
/*      */       } 
/* 1974 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1979 */     boolean expandFrames = ((context.parsingOptions & 0x8) != 0);
/* 1980 */     if (stackMapFrameOffset != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1984 */       context.currentFrameOffset = -1;
/* 1985 */       context.currentFrameType = 0;
/* 1986 */       context.currentFrameLocalCount = 0;
/* 1987 */       context.currentFrameLocalCountDelta = 0;
/* 1988 */       context.currentFrameLocalTypes = new Object[maxLocals];
/* 1989 */       context.currentFrameStackCount = 0;
/* 1990 */       context.currentFrameStackTypes = new Object[maxStack];
/* 1991 */       if (expandFrames) {
/* 1992 */         computeImplicitFrame(context);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2001 */       for (int offset = stackMapFrameOffset; offset < stackMapTableEndOffset - 2; offset++) {
/* 2002 */         if (classBuffer[offset] == 8) {
/* 2003 */           int potentialBytecodeOffset = readUnsignedShort(offset + 1);
/* 2004 */           if (potentialBytecodeOffset >= 0 && potentialBytecodeOffset < codeLength && (classBuffer[bytecodeStartOffset + potentialBytecodeOffset] & 0xFF) == 187)
/*      */           {
/*      */ 
/*      */             
/* 2008 */             createLabel(potentialBytecodeOffset, labels);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 2013 */     if (expandFrames && (context.parsingOptions & 0x100) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2020 */       methodVisitor.visitFrame(-1, maxLocals, null, 0, null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2028 */     int currentVisibleTypeAnnotationIndex = 0;
/*      */ 
/*      */     
/* 2031 */     int currentVisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets, 0);
/*      */ 
/*      */     
/* 2034 */     int currentInvisibleTypeAnnotationIndex = 0;
/*      */ 
/*      */     
/* 2037 */     int currentInvisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets, 0);
/*      */ 
/*      */     
/* 2040 */     boolean insertFrame = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2046 */     int wideJumpOpcodeDelta = ((context.parsingOptions & 0x100) == 0) ? 33 : 0;
/*      */     
/* 2048 */     currentOffset = bytecodeStartOffset;
/* 2049 */     while (currentOffset < bytecodeEndOffset) {
/* 2050 */       Label target, defaultLabel; int cpInfoOffset, low, numPairs, nameAndTypeCpInfoOffset, high, keys[]; String owner, name; Label[] table, values; String str1, descriptor; int i; String str2; int bootstrapMethodOffset; Handle handle; Object[] bootstrapMethodArguments; int j, currentBytecodeOffset = currentOffset - bytecodeStartOffset;
/* 2051 */       readBytecodeInstructionOffset(currentBytecodeOffset);
/*      */ 
/*      */       
/* 2054 */       Label currentLabel = labels[currentBytecodeOffset];
/* 2055 */       if (currentLabel != null) {
/* 2056 */         currentLabel.accept(methodVisitor, ((context.parsingOptions & 0x2) == 0));
/*      */       }
/*      */ 
/*      */       
/* 2060 */       while (stackMapFrameOffset != 0 && (context.currentFrameOffset == currentBytecodeOffset || context.currentFrameOffset == -1)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2065 */         if (context.currentFrameOffset != -1) {
/* 2066 */           if (!compressedFrames || expandFrames) {
/* 2067 */             methodVisitor.visitFrame(-1, context.currentFrameLocalCount, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 2074 */             methodVisitor.visitFrame(context.currentFrameType, context.currentFrameLocalCountDelta, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2083 */           insertFrame = false;
/*      */         } 
/* 2085 */         if (stackMapFrameOffset < stackMapTableEndOffset) {
/*      */           
/* 2087 */           stackMapFrameOffset = readStackMapFrame(stackMapFrameOffset, compressedFrames, expandFrames, context); continue;
/*      */         } 
/* 2089 */         stackMapFrameOffset = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2095 */       if (insertFrame) {
/* 2096 */         if ((context.parsingOptions & 0x8) != 0) {
/* 2097 */           methodVisitor.visitFrame(256, 0, null, 0, null);
/*      */         }
/* 2099 */         insertFrame = false;
/*      */       } 
/*      */ 
/*      */       
/* 2103 */       int opcode = classBuffer[currentOffset] & 0xFF;
/* 2104 */       switch (opcode) {
/*      */         case 0:
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 6:
/*      */         case 7:
/*      */         case 8:
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/*      */         case 13:
/*      */         case 14:
/*      */         case 15:
/*      */         case 46:
/*      */         case 47:
/*      */         case 48:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 79:
/*      */         case 80:
/*      */         case 81:
/*      */         case 82:
/*      */         case 83:
/*      */         case 84:
/*      */         case 85:
/*      */         case 86:
/*      */         case 87:
/*      */         case 88:
/*      */         case 89:
/*      */         case 90:
/*      */         case 91:
/*      */         case 92:
/*      */         case 93:
/*      */         case 94:
/*      */         case 95:
/*      */         case 96:
/*      */         case 97:
/*      */         case 98:
/*      */         case 99:
/*      */         case 100:
/*      */         case 101:
/*      */         case 102:
/*      */         case 103:
/*      */         case 104:
/*      */         case 105:
/*      */         case 106:
/*      */         case 107:
/*      */         case 108:
/*      */         case 109:
/*      */         case 110:
/*      */         case 111:
/*      */         case 112:
/*      */         case 113:
/*      */         case 114:
/*      */         case 115:
/*      */         case 116:
/*      */         case 117:
/*      */         case 118:
/*      */         case 119:
/*      */         case 120:
/*      */         case 121:
/*      */         case 122:
/*      */         case 123:
/*      */         case 124:
/*      */         case 125:
/*      */         case 126:
/*      */         case 127:
/*      */         case 128:
/*      */         case 129:
/*      */         case 130:
/*      */         case 131:
/*      */         case 133:
/*      */         case 134:
/*      */         case 135:
/*      */         case 136:
/*      */         case 137:
/*      */         case 138:
/*      */         case 139:
/*      */         case 140:
/*      */         case 141:
/*      */         case 142:
/*      */         case 143:
/*      */         case 144:
/*      */         case 145:
/*      */         case 146:
/*      */         case 147:
/*      */         case 148:
/*      */         case 149:
/*      */         case 150:
/*      */         case 151:
/*      */         case 152:
/*      */         case 172:
/*      */         case 173:
/*      */         case 174:
/*      */         case 175:
/*      */         case 176:
/*      */         case 177:
/*      */         case 190:
/*      */         case 191:
/*      */         case 194:
/*      */         case 195:
/* 2212 */           methodVisitor.visitInsn(opcode);
/* 2213 */           currentOffset++;
/*      */           break;
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         case 32:
/*      */         case 33:
/*      */         case 34:
/*      */         case 35:
/*      */         case 36:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         case 40:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/* 2235 */           opcode -= 26;
/* 2236 */           methodVisitor.visitVarInsn(21 + (opcode >> 2), opcode & 0x3);
/* 2237 */           currentOffset++;
/*      */           break;
/*      */         case 59:
/*      */         case 60:
/*      */         case 61:
/*      */         case 62:
/*      */         case 63:
/*      */         case 64:
/*      */         case 65:
/*      */         case 66:
/*      */         case 67:
/*      */         case 68:
/*      */         case 69:
/*      */         case 70:
/*      */         case 71:
/*      */         case 72:
/*      */         case 73:
/*      */         case 74:
/*      */         case 75:
/*      */         case 76:
/*      */         case 77:
/*      */         case 78:
/* 2259 */           opcode -= 59;
/* 2260 */           methodVisitor.visitVarInsn(54 + (opcode >> 2), opcode & 0x3);
/* 2261 */           currentOffset++;
/*      */           break;
/*      */         case 153:
/*      */         case 154:
/*      */         case 155:
/*      */         case 156:
/*      */         case 157:
/*      */         case 158:
/*      */         case 159:
/*      */         case 160:
/*      */         case 161:
/*      */         case 162:
/*      */         case 163:
/*      */         case 164:
/*      */         case 165:
/*      */         case 166:
/*      */         case 167:
/*      */         case 168:
/*      */         case 198:
/*      */         case 199:
/* 2281 */           methodVisitor.visitJumpInsn(opcode, labels[currentBytecodeOffset + 
/* 2282 */                 readShort(currentOffset + 1)]);
/* 2283 */           currentOffset += 3;
/*      */           break;
/*      */         case 200:
/*      */         case 201:
/* 2287 */           methodVisitor.visitJumpInsn(opcode - wideJumpOpcodeDelta, labels[currentBytecodeOffset + 
/*      */                 
/* 2289 */                 readInt(currentOffset + 1)]);
/* 2290 */           currentOffset += 5;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 202:
/*      */         case 203:
/*      */         case 204:
/*      */         case 205:
/*      */         case 206:
/*      */         case 207:
/*      */         case 208:
/*      */         case 209:
/*      */         case 210:
/*      */         case 211:
/*      */         case 212:
/*      */         case 213:
/*      */         case 214:
/*      */         case 215:
/*      */         case 216:
/*      */         case 217:
/*      */         case 218:
/*      */         case 219:
/* 2320 */           opcode = (opcode < 218) ? (opcode - 49) : (opcode - 20);
/* 2321 */           target = labels[currentBytecodeOffset + readUnsignedShort(currentOffset + 1)];
/* 2322 */           if (opcode == 167 || opcode == 168) {
/*      */             
/* 2324 */             methodVisitor.visitJumpInsn(opcode + 33, target);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 2329 */             opcode = (opcode < 167) ? ((opcode + 1 ^ 0x1) - 1) : (opcode ^ 0x1);
/* 2330 */             Label endif = createLabel(currentBytecodeOffset + 3, labels);
/* 2331 */             methodVisitor.visitJumpInsn(opcode, endif);
/* 2332 */             methodVisitor.visitJumpInsn(200, target);
/*      */ 
/*      */             
/* 2335 */             insertFrame = true;
/*      */           } 
/* 2337 */           currentOffset += 3;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 220:
/* 2342 */           methodVisitor.visitJumpInsn(200, labels[currentBytecodeOffset + 
/* 2343 */                 readInt(currentOffset + 1)]);
/*      */ 
/*      */ 
/*      */           
/* 2347 */           insertFrame = true;
/* 2348 */           currentOffset += 5;
/*      */           break;
/*      */         case 196:
/* 2351 */           opcode = classBuffer[currentOffset + 1] & 0xFF;
/* 2352 */           if (opcode == 132) {
/* 2353 */             methodVisitor.visitIincInsn(
/* 2354 */                 readUnsignedShort(currentOffset + 2), readShort(currentOffset + 4));
/* 2355 */             currentOffset += 6; break;
/*      */           } 
/* 2357 */           methodVisitor.visitVarInsn(opcode, readUnsignedShort(currentOffset + 2));
/* 2358 */           currentOffset += 4;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 170:
/* 2364 */           currentOffset += 4 - (currentBytecodeOffset & 0x3);
/*      */           
/* 2366 */           defaultLabel = labels[currentBytecodeOffset + readInt(currentOffset)];
/* 2367 */           low = readInt(currentOffset + 4);
/* 2368 */           high = readInt(currentOffset + 8);
/* 2369 */           currentOffset += 12;
/* 2370 */           table = new Label[high - low + 1];
/* 2371 */           for (i = 0; i < table.length; i++) {
/* 2372 */             table[i] = labels[currentBytecodeOffset + readInt(currentOffset)];
/* 2373 */             currentOffset += 4;
/*      */           } 
/* 2375 */           methodVisitor.visitTableSwitchInsn(low, high, defaultLabel, table);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 171:
/* 2381 */           currentOffset += 4 - (currentBytecodeOffset & 0x3);
/*      */           
/* 2383 */           defaultLabel = labels[currentBytecodeOffset + readInt(currentOffset)];
/* 2384 */           numPairs = readInt(currentOffset + 4);
/* 2385 */           currentOffset += 8;
/* 2386 */           keys = new int[numPairs];
/* 2387 */           values = new Label[numPairs];
/* 2388 */           for (i = 0; i < numPairs; i++) {
/* 2389 */             keys[i] = readInt(currentOffset);
/* 2390 */             values[i] = labels[currentBytecodeOffset + readInt(currentOffset + 4)];
/* 2391 */             currentOffset += 8;
/*      */           } 
/* 2393 */           methodVisitor.visitLookupSwitchInsn(defaultLabel, keys, values);
/*      */           break;
/*      */         
/*      */         case 21:
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*      */         case 58:
/*      */         case 169:
/* 2407 */           methodVisitor.visitVarInsn(opcode, classBuffer[currentOffset + 1] & 0xFF);
/* 2408 */           currentOffset += 2;
/*      */           break;
/*      */         case 16:
/*      */         case 188:
/* 2412 */           methodVisitor.visitIntInsn(opcode, classBuffer[currentOffset + 1]);
/* 2413 */           currentOffset += 2;
/*      */           break;
/*      */         case 17:
/* 2416 */           methodVisitor.visitIntInsn(opcode, readShort(currentOffset + 1));
/* 2417 */           currentOffset += 3;
/*      */           break;
/*      */         case 18:
/* 2420 */           methodVisitor.visitLdcInsn(readConst(classBuffer[currentOffset + 1] & 0xFF, charBuffer));
/* 2421 */           currentOffset += 2;
/*      */           break;
/*      */         case 19:
/*      */         case 20:
/* 2425 */           methodVisitor.visitLdcInsn(readConst(readUnsignedShort(currentOffset + 1), charBuffer));
/* 2426 */           currentOffset += 3;
/*      */           break;
/*      */         
/*      */         case 178:
/*      */         case 179:
/*      */         case 180:
/*      */         case 181:
/*      */         case 182:
/*      */         case 183:
/*      */         case 184:
/*      */         case 185:
/* 2437 */           cpInfoOffset = this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)];
/* 2438 */           nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
/* 2439 */           owner = readClass(cpInfoOffset, charBuffer);
/* 2440 */           str1 = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 2441 */           str2 = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 2442 */           if (opcode < 182) {
/* 2443 */             methodVisitor.visitFieldInsn(opcode, owner, str1, str2);
/*      */           } else {
/* 2445 */             boolean isInterface = (classBuffer[cpInfoOffset - 1] == 11);
/*      */             
/* 2447 */             methodVisitor.visitMethodInsn(opcode, owner, str1, str2, isInterface);
/*      */           } 
/* 2449 */           if (opcode == 185) {
/* 2450 */             currentOffset += 5; break;
/*      */           } 
/* 2452 */           currentOffset += 3;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 186:
/* 2458 */           cpInfoOffset = this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)];
/* 2459 */           nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
/* 2460 */           name = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 2461 */           descriptor = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 2462 */           bootstrapMethodOffset = this.bootstrapMethodOffsets[readUnsignedShort(cpInfoOffset)];
/*      */           
/* 2464 */           handle = (Handle)readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/*      */           
/* 2466 */           bootstrapMethodArguments = new Object[readUnsignedShort(bootstrapMethodOffset + 2)];
/* 2467 */           bootstrapMethodOffset += 4;
/* 2468 */           for (j = 0; j < bootstrapMethodArguments.length; j++) {
/* 2469 */             bootstrapMethodArguments[j] = 
/* 2470 */               readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/* 2471 */             bootstrapMethodOffset += 2;
/*      */           } 
/* 2473 */           methodVisitor.visitInvokeDynamicInsn(name, descriptor, handle, bootstrapMethodArguments);
/*      */           
/* 2475 */           currentOffset += 5;
/*      */           break;
/*      */         
/*      */         case 187:
/*      */         case 189:
/*      */         case 192:
/*      */         case 193:
/* 2482 */           methodVisitor.visitTypeInsn(opcode, readClass(currentOffset + 1, charBuffer));
/* 2483 */           currentOffset += 3;
/*      */           break;
/*      */         case 132:
/* 2486 */           methodVisitor.visitIincInsn(classBuffer[currentOffset + 1] & 0xFF, classBuffer[currentOffset + 2]);
/*      */           
/* 2488 */           currentOffset += 3;
/*      */           break;
/*      */         case 197:
/* 2491 */           methodVisitor.visitMultiANewArrayInsn(
/* 2492 */               readClass(currentOffset + 1, charBuffer), classBuffer[currentOffset + 3] & 0xFF);
/* 2493 */           currentOffset += 4;
/*      */           break;
/*      */         default:
/* 2496 */           throw new AssertionError();
/*      */       } 
/*      */ 
/*      */       
/* 2500 */       while (visibleTypeAnnotationOffsets != null && currentVisibleTypeAnnotationIndex < visibleTypeAnnotationOffsets.length && currentVisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
/*      */ 
/*      */         
/* 2503 */         if (currentVisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
/*      */ 
/*      */           
/* 2506 */           int currentAnnotationOffset = readTypeAnnotationTarget(context, visibleTypeAnnotationOffsets[currentVisibleTypeAnnotationIndex]);
/*      */ 
/*      */           
/* 2509 */           String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 2510 */           currentAnnotationOffset += 2;
/*      */           
/* 2512 */           readElementValues(methodVisitor
/* 2513 */               .visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2523 */         currentVisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets, ++currentVisibleTypeAnnotationIndex);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2528 */       while (invisibleTypeAnnotationOffsets != null && currentInvisibleTypeAnnotationIndex < invisibleTypeAnnotationOffsets.length && currentInvisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
/*      */ 
/*      */         
/* 2531 */         if (currentInvisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
/*      */ 
/*      */           
/* 2534 */           int currentAnnotationOffset = readTypeAnnotationTarget(context, invisibleTypeAnnotationOffsets[currentInvisibleTypeAnnotationIndex]);
/*      */ 
/*      */           
/* 2537 */           String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 2538 */           currentAnnotationOffset += 2;
/*      */           
/* 2540 */           readElementValues(methodVisitor
/* 2541 */               .visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2551 */         currentInvisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets, ++currentInvisibleTypeAnnotationIndex);
/*      */       } 
/*      */     } 
/*      */     
/* 2555 */     if (labels[codeLength] != null) {
/* 2556 */       methodVisitor.visitLabel(labels[codeLength]);
/*      */     }
/*      */ 
/*      */     
/* 2560 */     if (localVariableTableOffset != 0 && (context.parsingOptions & 0x2) == 0) {
/*      */       
/* 2562 */       int[] typeTable = null;
/* 2563 */       if (localVariableTypeTableOffset != 0) {
/* 2564 */         typeTable = new int[readUnsignedShort(localVariableTypeTableOffset) * 3];
/* 2565 */         currentOffset = localVariableTypeTableOffset + 2;
/* 2566 */         int typeTableIndex = typeTable.length;
/* 2567 */         while (typeTableIndex > 0) {
/*      */           
/* 2569 */           typeTable[--typeTableIndex] = currentOffset + 6;
/* 2570 */           typeTable[--typeTableIndex] = readUnsignedShort(currentOffset + 8);
/* 2571 */           typeTable[--typeTableIndex] = readUnsignedShort(currentOffset);
/* 2572 */           currentOffset += 10;
/*      */         } 
/*      */       } 
/* 2575 */       int localVariableTableLength = readUnsignedShort(localVariableTableOffset);
/* 2576 */       currentOffset = localVariableTableOffset + 2;
/* 2577 */       while (localVariableTableLength-- > 0) {
/* 2578 */         int startPc = readUnsignedShort(currentOffset);
/* 2579 */         int length = readUnsignedShort(currentOffset + 2);
/* 2580 */         String name = readUTF8(currentOffset + 4, charBuffer);
/* 2581 */         String descriptor = readUTF8(currentOffset + 6, charBuffer);
/* 2582 */         int index = readUnsignedShort(currentOffset + 8);
/* 2583 */         currentOffset += 10;
/* 2584 */         String signature = null;
/* 2585 */         if (typeTable != null) {
/* 2586 */           for (int i = 0; i < typeTable.length; i += 3) {
/* 2587 */             if (typeTable[i] == startPc && typeTable[i + 1] == index) {
/* 2588 */               signature = readUTF8(typeTable[i + 2], charBuffer);
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/* 2593 */         methodVisitor.visitLocalVariable(name, descriptor, signature, labels[startPc], labels[startPc + length], index);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2599 */     if (visibleTypeAnnotationOffsets != null) {
/* 2600 */       for (int typeAnnotationOffset : visibleTypeAnnotationOffsets) {
/* 2601 */         int targetType = readByte(typeAnnotationOffset);
/* 2602 */         if (targetType == 64 || targetType == 65) {
/*      */ 
/*      */           
/* 2605 */           currentOffset = readTypeAnnotationTarget(context, typeAnnotationOffset);
/*      */           
/* 2607 */           String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2608 */           currentOffset += 2;
/*      */           
/* 2610 */           readElementValues(methodVisitor
/* 2611 */               .visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, annotationDescriptor, true), currentOffset, true, charBuffer);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2627 */     if (invisibleTypeAnnotationOffsets != null) {
/* 2628 */       for (int typeAnnotationOffset : invisibleTypeAnnotationOffsets) {
/* 2629 */         int targetType = readByte(typeAnnotationOffset);
/* 2630 */         if (targetType == 64 || targetType == 65) {
/*      */ 
/*      */           
/* 2633 */           currentOffset = readTypeAnnotationTarget(context, typeAnnotationOffset);
/*      */           
/* 2635 */           String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2636 */           currentOffset += 2;
/*      */           
/* 2638 */           readElementValues(methodVisitor
/* 2639 */               .visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, annotationDescriptor, false), currentOffset, true, charBuffer);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2655 */     while (attributes != null) {
/*      */       
/* 2657 */       Attribute nextAttribute = attributes.nextAttribute;
/* 2658 */       attributes.nextAttribute = null;
/* 2659 */       methodVisitor.visitAttribute(attributes);
/* 2660 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 2664 */     methodVisitor.visitMaxs(maxStack, maxLocals);
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
/*      */   protected void readBytecodeInstructionOffset(int bytecodeOffset) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Label readLabel(int bytecodeOffset, Label[] labels) {
/* 2692 */     if (labels[bytecodeOffset] == null) {
/* 2693 */       labels[bytecodeOffset] = new Label();
/*      */     }
/* 2695 */     return labels[bytecodeOffset];
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
/*      */   private Label createLabel(int bytecodeOffset, Label[] labels) {
/* 2708 */     Label label = readLabel(bytecodeOffset, labels);
/* 2709 */     label.flags = (short)(label.flags & 0xFFFFFFFE);
/* 2710 */     return label;
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
/*      */   private void createDebugLabel(int bytecodeOffset, Label[] labels) {
/* 2722 */     if (labels[bytecodeOffset] == null) {
/* 2723 */       (readLabel(bytecodeOffset, labels)).flags = (short)((readLabel(bytecodeOffset, labels)).flags | 0x1);
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
/*      */   private int[] readTypeAnnotations(MethodVisitor methodVisitor, Context context, int runtimeTypeAnnotationsOffset, boolean visible) {
/* 2750 */     char[] charBuffer = context.charBuffer;
/* 2751 */     int currentOffset = runtimeTypeAnnotationsOffset;
/*      */     
/* 2753 */     int[] typeAnnotationsOffsets = new int[readUnsignedShort(currentOffset)];
/* 2754 */     currentOffset += 2;
/*      */     
/* 2756 */     for (int i = 0; i < typeAnnotationsOffsets.length; i++) {
/* 2757 */       int tableLength; typeAnnotationsOffsets[i] = currentOffset;
/*      */ 
/*      */       
/* 2760 */       int targetType = readInt(currentOffset);
/* 2761 */       switch (targetType >>> 24) {
/*      */ 
/*      */         
/*      */         case 64:
/*      */         case 65:
/* 2766 */           tableLength = readUnsignedShort(currentOffset + 1);
/* 2767 */           currentOffset += 3;
/* 2768 */           while (tableLength-- > 0) {
/* 2769 */             int startPc = readUnsignedShort(currentOffset);
/* 2770 */             int length = readUnsignedShort(currentOffset + 2);
/*      */             
/* 2772 */             currentOffset += 6;
/* 2773 */             createLabel(startPc, context.currentMethodLabels);
/* 2774 */             createLabel(startPc + length, context.currentMethodLabels);
/*      */           } 
/*      */           break;
/*      */         case 71:
/*      */         case 72:
/*      */         case 73:
/*      */         case 74:
/*      */         case 75:
/* 2782 */           currentOffset += 4;
/*      */           break;
/*      */         case 16:
/*      */         case 17:
/*      */         case 18:
/*      */         case 23:
/*      */         case 66:
/*      */         case 67:
/*      */         case 68:
/*      */         case 69:
/*      */         case 70:
/* 2793 */           currentOffset += 3;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 2803 */           throw new IllegalArgumentException();
/*      */       } 
/*      */ 
/*      */       
/* 2807 */       int pathLength = readByte(currentOffset);
/* 2808 */       if (targetType >>> 24 == 66) {
/*      */         
/* 2810 */         TypePath path = (pathLength == 0) ? null : new TypePath(this.classFileBuffer, currentOffset);
/* 2811 */         currentOffset += 1 + 2 * pathLength;
/*      */         
/* 2813 */         String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2814 */         currentOffset += 2;
/*      */ 
/*      */         
/* 2817 */         currentOffset = readElementValues(methodVisitor
/* 2818 */             .visitTryCatchAnnotation(targetType & 0xFFFFFF00, path, annotationDescriptor, visible), currentOffset, true, charBuffer);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 2827 */         currentOffset += 3 + 2 * pathLength;
/*      */ 
/*      */ 
/*      */         
/* 2831 */         currentOffset = readElementValues(null, currentOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */     
/* 2835 */     return typeAnnotationsOffsets;
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
/*      */   private int getTypeAnnotationBytecodeOffset(int[] typeAnnotationOffsets, int typeAnnotationIndex) {
/* 2850 */     if (typeAnnotationOffsets == null || typeAnnotationIndex >= typeAnnotationOffsets.length || 
/*      */       
/* 2852 */       readByte(typeAnnotationOffsets[typeAnnotationIndex]) < 67) {
/* 2853 */       return -1;
/*      */     }
/* 2855 */     return readUnsignedShort(typeAnnotationOffsets[typeAnnotationIndex] + 1);
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
/*      */   private int readTypeAnnotationTarget(Context context, int typeAnnotationOffset) {
/* 2869 */     int tableLength, i, currentOffset = typeAnnotationOffset;
/*      */     
/* 2871 */     int targetType = readInt(typeAnnotationOffset);
/* 2872 */     switch (targetType >>> 24) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 22:
/* 2876 */         targetType &= 0xFFFF0000;
/* 2877 */         currentOffset += 2;
/*      */         break;
/*      */       case 19:
/*      */       case 20:
/*      */       case 21:
/* 2882 */         targetType &= 0xFF000000;
/* 2883 */         currentOffset++;
/*      */         break;
/*      */       case 64:
/*      */       case 65:
/* 2887 */         targetType &= 0xFF000000;
/* 2888 */         tableLength = readUnsignedShort(currentOffset + 1);
/* 2889 */         currentOffset += 3;
/* 2890 */         context.currentLocalVariableAnnotationRangeStarts = new Label[tableLength];
/* 2891 */         context.currentLocalVariableAnnotationRangeEnds = new Label[tableLength];
/* 2892 */         context.currentLocalVariableAnnotationRangeIndices = new int[tableLength];
/* 2893 */         for (i = 0; i < tableLength; i++) {
/* 2894 */           int startPc = readUnsignedShort(currentOffset);
/* 2895 */           int length = readUnsignedShort(currentOffset + 2);
/* 2896 */           int index = readUnsignedShort(currentOffset + 4);
/* 2897 */           currentOffset += 6;
/* 2898 */           context.currentLocalVariableAnnotationRangeStarts[i] = 
/* 2899 */             createLabel(startPc, context.currentMethodLabels);
/* 2900 */           context.currentLocalVariableAnnotationRangeEnds[i] = 
/* 2901 */             createLabel(startPc + length, context.currentMethodLabels);
/* 2902 */           context.currentLocalVariableAnnotationRangeIndices[i] = index;
/*      */         } 
/*      */         break;
/*      */       case 71:
/*      */       case 72:
/*      */       case 73:
/*      */       case 74:
/*      */       case 75:
/* 2910 */         targetType &= 0xFF0000FF;
/* 2911 */         currentOffset += 4;
/*      */         break;
/*      */       case 16:
/*      */       case 17:
/*      */       case 18:
/*      */       case 23:
/*      */       case 66:
/* 2918 */         targetType &= 0xFFFFFF00;
/* 2919 */         currentOffset += 3;
/*      */         break;
/*      */       case 67:
/*      */       case 68:
/*      */       case 69:
/*      */       case 70:
/* 2925 */         targetType &= 0xFF000000;
/* 2926 */         currentOffset += 3;
/*      */         break;
/*      */       default:
/* 2929 */         throw new IllegalArgumentException();
/*      */     } 
/* 2931 */     context.currentTypeAnnotationTarget = targetType;
/*      */     
/* 2933 */     int pathLength = readByte(currentOffset);
/* 2934 */     context
/* 2935 */       .currentTypeAnnotationTargetPath = (pathLength == 0) ? null : new TypePath(this.classFileBuffer, currentOffset);
/*      */     
/* 2937 */     return currentOffset + 1 + 2 * pathLength;
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
/*      */   private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int runtimeParameterAnnotationsOffset, boolean visible) {
/* 2956 */     int currentOffset = runtimeParameterAnnotationsOffset;
/* 2957 */     int numParameters = this.classFileBuffer[currentOffset++] & 0xFF;
/* 2958 */     methodVisitor.visitAnnotableParameterCount(numParameters, visible);
/* 2959 */     char[] charBuffer = context.charBuffer;
/* 2960 */     for (int i = 0; i < numParameters; i++) {
/* 2961 */       int numAnnotations = readUnsignedShort(currentOffset);
/* 2962 */       currentOffset += 2;
/* 2963 */       while (numAnnotations-- > 0) {
/*      */         
/* 2965 */         String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2966 */         currentOffset += 2;
/*      */ 
/*      */         
/* 2969 */         currentOffset = readElementValues(methodVisitor
/* 2970 */             .visitParameterAnnotation(i, annotationDescriptor, visible), currentOffset, true, charBuffer);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readElementValues(AnnotationVisitor annotationVisitor, int annotationOffset, boolean named, char[] charBuffer) {
/* 2997 */     int currentOffset = annotationOffset;
/*      */     
/* 2999 */     int numElementValuePairs = readUnsignedShort(currentOffset);
/* 3000 */     currentOffset += 2;
/* 3001 */     if (named) {
/*      */       
/* 3003 */       while (numElementValuePairs-- > 0) {
/* 3004 */         String elementName = readUTF8(currentOffset, charBuffer);
/*      */         
/* 3006 */         currentOffset = readElementValue(annotationVisitor, currentOffset + 2, elementName, charBuffer);
/*      */       } 
/*      */     } else {
/*      */       
/* 3010 */       while (numElementValuePairs-- > 0)
/*      */       {
/* 3012 */         currentOffset = readElementValue(annotationVisitor, currentOffset, null, charBuffer);
/*      */       }
/*      */     } 
/* 3015 */     if (annotationVisitor != null) {
/* 3016 */       annotationVisitor.visitEnd();
/*      */     }
/* 3018 */     return currentOffset;
/*      */   }
/*      */   
/*      */   private int readElementValue(AnnotationVisitor annotationVisitor, int elementValueOffset, String elementName, char[] charBuffer) {
/*      */     int numValues;
/*      */     byte[] byteValues;
/*      */     int i;
/*      */     boolean[] booleanValues;
/*      */     int j;
/*      */     short[] shortValues;
/*      */     int k;
/*      */     char[] charValues;
/*      */     int m, intValues[], n;
/*      */     long[] longValues;
/*      */     int i1;
/*      */     float[] floatValues;
/*      */     int i2;
/*      */     double[] doubleValues;
/* 3036 */     int i3, currentOffset = elementValueOffset;
/* 3037 */     if (annotationVisitor == null) {
/* 3038 */       switch (this.classFileBuffer[currentOffset] & 0xFF) {
/*      */         case 101:
/* 3040 */           return currentOffset + 5;
/*      */         case 64:
/* 3042 */           return readElementValues(null, currentOffset + 3, true, charBuffer);
/*      */         case 91:
/* 3044 */           return readElementValues(null, currentOffset + 1, false, charBuffer);
/*      */       } 
/* 3046 */       return currentOffset + 3;
/*      */     } 
/*      */     
/* 3049 */     switch (this.classFileBuffer[currentOffset++] & 0xFF) {
/*      */       case 66:
/* 3051 */         annotationVisitor.visit(elementName, 
/* 3052 */             Byte.valueOf((byte)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)])));
/* 3053 */         currentOffset += 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3197 */         return currentOffset;case 67: annotationVisitor.visit(elementName, Character.valueOf((char)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)]))); currentOffset += 2; return currentOffset;case 68: case 70: case 73: case 74: annotationVisitor.visit(elementName, readConst(readUnsignedShort(currentOffset), charBuffer)); currentOffset += 2; return currentOffset;case 83: annotationVisitor.visit(elementName, Short.valueOf((short)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)]))); currentOffset += 2; return currentOffset;case 90: annotationVisitor.visit(elementName, (readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)]) == 0) ? Boolean.FALSE : Boolean.TRUE); currentOffset += 2; return currentOffset;case 115: annotationVisitor.visit(elementName, readUTF8(currentOffset, charBuffer)); currentOffset += 2; return currentOffset;case 101: annotationVisitor.visitEnum(elementName, readUTF8(currentOffset, charBuffer), readUTF8(currentOffset + 2, charBuffer)); currentOffset += 4; return currentOffset;case 99: annotationVisitor.visit(elementName, Type.getType(readUTF8(currentOffset, charBuffer))); currentOffset += 2; return currentOffset;case 64: currentOffset = readElementValues(annotationVisitor.visitAnnotation(elementName, readUTF8(currentOffset, charBuffer)), currentOffset + 2, true, charBuffer); return currentOffset;case 91: numValues = readUnsignedShort(currentOffset); currentOffset += 2; if (numValues == 0) return readElementValues(annotationVisitor.visitArray(elementName), currentOffset - 2, false, charBuffer);  switch (this.classFileBuffer[currentOffset] & 0xFF) { case 66: byteValues = new byte[numValues]; for (i = 0; i < numValues; i++) { byteValues[i] = (byte)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, byteValues); return currentOffset;case 90: booleanValues = new boolean[numValues]; for (j = 0; j < numValues; j++) { booleanValues[j] = (readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]) != 0); currentOffset += 3; }  annotationVisitor.visit(elementName, booleanValues); return currentOffset;case 83: shortValues = new short[numValues]; for (k = 0; k < numValues; k++) { shortValues[k] = (short)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, shortValues); return currentOffset;case 67: charValues = new char[numValues]; for (m = 0; m < numValues; m++) { charValues[m] = (char)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, charValues); return currentOffset;case 73: intValues = new int[numValues]; for (n = 0; n < numValues; n++) { intValues[n] = readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, intValues); return currentOffset;case 74: longValues = new long[numValues]; for (i1 = 0; i1 < numValues; i1++) { longValues[i1] = readLong(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, longValues); return currentOffset;case 70: floatValues = new float[numValues]; for (i2 = 0; i2 < numValues; i2++) { floatValues[i2] = Float.intBitsToFloat(readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)])); currentOffset += 3; }  annotationVisitor.visit(elementName, floatValues); return currentOffset;case 68: doubleValues = new double[numValues]; for (i3 = 0; i3 < numValues; i3++) { doubleValues[i3] = Double.longBitsToDouble(readLong(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)])); currentOffset += 3; }  annotationVisitor.visit(elementName, doubleValues); return currentOffset; }  currentOffset = readElementValues(annotationVisitor.visitArray(elementName), currentOffset - 2, false, charBuffer); return currentOffset;
/*      */     } 
/*      */     throw new IllegalArgumentException();
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
/*      */   private void computeImplicitFrame(Context context) {
/* 3211 */     String methodDescriptor = context.currentMethodDescriptor;
/* 3212 */     Object[] locals = context.currentFrameLocalTypes;
/* 3213 */     int numLocal = 0;
/* 3214 */     if ((context.currentMethodAccessFlags & 0x8) == 0) {
/* 3215 */       if ("<init>".equals(context.currentMethodName)) {
/* 3216 */         locals[numLocal++] = Opcodes.UNINITIALIZED_THIS;
/*      */       } else {
/* 3218 */         locals[numLocal++] = readClass(this.header + 2, context.charBuffer);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 3223 */     int currentMethodDescritorOffset = 1;
/*      */     while (true) {
/* 3225 */       int currentArgumentDescriptorStartOffset = currentMethodDescritorOffset;
/* 3226 */       switch (methodDescriptor.charAt(currentMethodDescritorOffset++)) {
/*      */         case 'B':
/*      */         case 'C':
/*      */         case 'I':
/*      */         case 'S':
/*      */         case 'Z':
/* 3232 */           locals[numLocal++] = Opcodes.INTEGER;
/*      */           continue;
/*      */         case 'F':
/* 3235 */           locals[numLocal++] = Opcodes.FLOAT;
/*      */           continue;
/*      */         case 'J':
/* 3238 */           locals[numLocal++] = Opcodes.LONG;
/*      */           continue;
/*      */         case 'D':
/* 3241 */           locals[numLocal++] = Opcodes.DOUBLE;
/*      */           continue;
/*      */         case '[':
/* 3244 */           while (methodDescriptor.charAt(currentMethodDescritorOffset) == '[') {
/* 3245 */             currentMethodDescritorOffset++;
/*      */           }
/* 3247 */           if (methodDescriptor.charAt(currentMethodDescritorOffset) == 'L') {
/* 3248 */             currentMethodDescritorOffset++;
/* 3249 */             while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
/* 3250 */               currentMethodDescritorOffset++;
/*      */             }
/*      */           } 
/* 3253 */           locals[numLocal++] = methodDescriptor
/* 3254 */             .substring(currentArgumentDescriptorStartOffset, ++currentMethodDescritorOffset);
/*      */           continue;
/*      */         
/*      */         case 'L':
/* 3258 */           while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
/* 3259 */             currentMethodDescritorOffset++;
/*      */           }
/* 3261 */           locals[numLocal++] = methodDescriptor
/* 3262 */             .substring(currentArgumentDescriptorStartOffset + 1, currentMethodDescritorOffset++); continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 3266 */     context.currentFrameLocalCount = numLocal;
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
/*      */   private int readStackMapFrame(int stackMapFrameOffset, boolean compressed, boolean expand, Context context) {
/* 3291 */     int frameType, offsetDelta, currentOffset = stackMapFrameOffset;
/* 3292 */     char[] charBuffer = context.charBuffer;
/* 3293 */     Label[] labels = context.currentMethodLabels;
/*      */     
/* 3295 */     if (compressed) {
/*      */       
/* 3297 */       frameType = this.classFileBuffer[currentOffset++] & 0xFF;
/*      */     } else {
/* 3299 */       frameType = 255;
/* 3300 */       context.currentFrameOffset = -1;
/*      */     } 
/*      */     
/* 3303 */     context.currentFrameLocalCountDelta = 0;
/* 3304 */     if (frameType < 64) {
/* 3305 */       offsetDelta = frameType;
/* 3306 */       context.currentFrameType = 3;
/* 3307 */       context.currentFrameStackCount = 0;
/* 3308 */     } else if (frameType < 128) {
/* 3309 */       offsetDelta = frameType - 64;
/*      */       
/* 3311 */       currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, 0, charBuffer, labels);
/*      */       
/* 3313 */       context.currentFrameType = 4;
/* 3314 */       context.currentFrameStackCount = 1;
/* 3315 */     } else if (frameType >= 247) {
/* 3316 */       offsetDelta = readUnsignedShort(currentOffset);
/* 3317 */       currentOffset += 2;
/* 3318 */       if (frameType == 247) {
/*      */         
/* 3320 */         currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, 0, charBuffer, labels);
/*      */         
/* 3322 */         context.currentFrameType = 4;
/* 3323 */         context.currentFrameStackCount = 1;
/* 3324 */       } else if (frameType >= 248 && frameType < 251) {
/* 3325 */         context.currentFrameType = 2;
/* 3326 */         context.currentFrameLocalCountDelta = 251 - frameType;
/* 3327 */         context.currentFrameLocalCount -= context.currentFrameLocalCountDelta;
/* 3328 */         context.currentFrameStackCount = 0;
/* 3329 */       } else if (frameType == 251) {
/* 3330 */         context.currentFrameType = 3;
/* 3331 */         context.currentFrameStackCount = 0;
/* 3332 */       } else if (frameType < 255) {
/* 3333 */         int local = expand ? context.currentFrameLocalCount : 0;
/* 3334 */         for (int k = frameType - 251; k > 0; k--)
/*      */         {
/* 3336 */           currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameLocalTypes, local++, charBuffer, labels);
/*      */         }
/*      */         
/* 3339 */         context.currentFrameType = 1;
/* 3340 */         context.currentFrameLocalCountDelta = frameType - 251;
/* 3341 */         context.currentFrameLocalCount += context.currentFrameLocalCountDelta;
/* 3342 */         context.currentFrameStackCount = 0;
/*      */       } else {
/* 3344 */         int numberOfLocals = readUnsignedShort(currentOffset);
/* 3345 */         currentOffset += 2;
/* 3346 */         context.currentFrameType = 0;
/* 3347 */         context.currentFrameLocalCountDelta = numberOfLocals;
/* 3348 */         context.currentFrameLocalCount = numberOfLocals;
/* 3349 */         for (int local = 0; local < numberOfLocals; local++)
/*      */         {
/* 3351 */           currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameLocalTypes, local, charBuffer, labels);
/*      */         }
/*      */         
/* 3354 */         int numberOfStackItems = readUnsignedShort(currentOffset);
/* 3355 */         currentOffset += 2;
/* 3356 */         context.currentFrameStackCount = numberOfStackItems;
/* 3357 */         for (int stack = 0; stack < numberOfStackItems; stack++)
/*      */         {
/* 3359 */           currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, stack, charBuffer, labels);
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 3364 */       throw new IllegalArgumentException();
/*      */     } 
/* 3366 */     context.currentFrameOffset += offsetDelta + 1;
/* 3367 */     createLabel(context.currentFrameOffset, labels);
/* 3368 */     return currentOffset;
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
/*      */   private int readVerificationTypeInfo(int verificationTypeInfoOffset, Object[] frame, int index, char[] charBuffer, Label[] labels) {
/* 3391 */     int currentOffset = verificationTypeInfoOffset;
/* 3392 */     int tag = this.classFileBuffer[currentOffset++] & 0xFF;
/* 3393 */     switch (tag) {
/*      */       case 0:
/* 3395 */         frame[index] = Opcodes.TOP;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3426 */         return currentOffset;case 1: frame[index] = Opcodes.INTEGER; return currentOffset;case 2: frame[index] = Opcodes.FLOAT; return currentOffset;case 3: frame[index] = Opcodes.DOUBLE; return currentOffset;case 4: frame[index] = Opcodes.LONG; return currentOffset;case 5: frame[index] = Opcodes.NULL; return currentOffset;case 6: frame[index] = Opcodes.UNINITIALIZED_THIS; return currentOffset;case 7: frame[index] = readClass(currentOffset, charBuffer); currentOffset += 2; return currentOffset;case 8: frame[index] = createLabel(readUnsignedShort(currentOffset), labels); currentOffset += 2; return currentOffset;
/*      */     } 
/*      */     throw new IllegalArgumentException();
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
/*      */   final int getFirstAttributeOffset() {
/* 3443 */     int currentOffset = this.header + 8 + readUnsignedShort(this.header + 6) * 2;
/*      */ 
/*      */     
/* 3446 */     int fieldsCount = readUnsignedShort(currentOffset);
/* 3447 */     currentOffset += 2;
/*      */     
/* 3449 */     while (fieldsCount-- > 0) {
/*      */ 
/*      */ 
/*      */       
/* 3453 */       int attributesCount = readUnsignedShort(currentOffset + 6);
/* 3454 */       currentOffset += 8;
/*      */       
/* 3456 */       while (attributesCount-- > 0)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 3461 */         currentOffset += 6 + readInt(currentOffset + 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3466 */     int methodsCount = readUnsignedShort(currentOffset);
/* 3467 */     currentOffset += 2;
/* 3468 */     while (methodsCount-- > 0) {
/* 3469 */       int attributesCount = readUnsignedShort(currentOffset + 6);
/* 3470 */       currentOffset += 8;
/* 3471 */       while (attributesCount-- > 0) {
/* 3472 */         currentOffset += 6 + readInt(currentOffset + 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3477 */     return currentOffset + 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] readBootstrapMethodsAttribute(int maxStringLength) {
/* 3488 */     char[] charBuffer = new char[maxStringLength];
/* 3489 */     int currentAttributeOffset = getFirstAttributeOffset();
/* 3490 */     for (int i = readUnsignedShort(currentAttributeOffset - 2); i > 0; i--) {
/*      */       
/* 3492 */       String attributeName = readUTF8(currentAttributeOffset, charBuffer);
/* 3493 */       int attributeLength = readInt(currentAttributeOffset + 2);
/* 3494 */       currentAttributeOffset += 6;
/* 3495 */       if ("BootstrapMethods".equals(attributeName)) {
/*      */         
/* 3497 */         int[] result = new int[readUnsignedShort(currentAttributeOffset)];
/*      */         
/* 3499 */         int currentBootstrapMethodOffset = currentAttributeOffset + 2;
/* 3500 */         for (int j = 0; j < result.length; j++) {
/* 3501 */           result[j] = currentBootstrapMethodOffset;
/*      */ 
/*      */           
/* 3504 */           currentBootstrapMethodOffset += 4 + 
/* 3505 */             readUnsignedShort(currentBootstrapMethodOffset + 2) * 2;
/*      */         } 
/* 3507 */         return result;
/*      */       } 
/* 3509 */       currentAttributeOffset += attributeLength;
/*      */     } 
/* 3511 */     throw new IllegalArgumentException();
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
/*      */   private Attribute readAttribute(Attribute[] attributePrototypes, String type, int offset, int length, char[] charBuffer, int codeAttributeOffset, Label[] labels) {
/* 3542 */     for (Attribute attributePrototype : attributePrototypes) {
/* 3543 */       if (attributePrototype.type.equals(type)) {
/* 3544 */         return attributePrototype.read(this, offset, length, charBuffer, codeAttributeOffset, labels);
/*      */       }
/*      */     } 
/*      */     
/* 3548 */     return (new Attribute(type)).read(this, offset, length, null, -1, null);
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
/*      */   public int getItemCount() {
/* 3561 */     return this.cpInfoOffsets.length;
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
/*      */   public int getItem(int constantPoolEntryIndex) {
/* 3575 */     return this.cpInfoOffsets[constantPoolEntryIndex];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxStringLength() {
/* 3586 */     return this.maxStringLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readByte(int offset) {
/* 3597 */     return this.classFileBuffer[offset] & 0xFF;
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
/*      */   public byte[] readBytes(int offset, int length) {
/* 3609 */     byte[] result = new byte[length];
/* 3610 */     System.arraycopy(this.classFileBuffer, offset, result, 0, length);
/* 3611 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readUnsignedShort(int offset) {
/* 3622 */     byte[] classBuffer = this.classFileBuffer;
/* 3623 */     return (classBuffer[offset] & 0xFF) << 8 | classBuffer[offset + 1] & 0xFF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short readShort(int offset) {
/* 3634 */     byte[] classBuffer = this.classFileBuffer;
/* 3635 */     return (short)((classBuffer[offset] & 0xFF) << 8 | classBuffer[offset + 1] & 0xFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readInt(int offset) {
/* 3646 */     byte[] classBuffer = this.classFileBuffer;
/* 3647 */     return (classBuffer[offset] & 0xFF) << 24 | (classBuffer[offset + 1] & 0xFF) << 16 | (classBuffer[offset + 2] & 0xFF) << 8 | classBuffer[offset + 3] & 0xFF;
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
/*      */   public long readLong(int offset) {
/* 3661 */     long l1 = readInt(offset);
/* 3662 */     long l0 = readInt(offset + 4) & 0xFFFFFFFFL;
/* 3663 */     return l1 << 32L | l0;
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
/*      */   public String readUTF8(int offset, char[] charBuffer) {
/* 3679 */     int constantPoolEntryIndex = readUnsignedShort(offset);
/* 3680 */     if (offset == 0 || constantPoolEntryIndex == 0) {
/* 3681 */       return null;
/*      */     }
/* 3683 */     return readUtf(constantPoolEntryIndex, charBuffer);
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
/*      */   final String readUtf(int constantPoolEntryIndex, char[] charBuffer) {
/* 3696 */     String value = this.constantUtf8Values[constantPoolEntryIndex];
/* 3697 */     if (value != null) {
/* 3698 */       return value;
/*      */     }
/* 3700 */     int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
/* 3701 */     this.constantUtf8Values[constantPoolEntryIndex] = 
/* 3702 */       readUtf(cpInfoOffset + 2, readUnsignedShort(cpInfoOffset), charBuffer); return readUtf(cpInfoOffset + 2, readUnsignedShort(cpInfoOffset), charBuffer);
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
/*      */   private String readUtf(int utfOffset, int utfLength, char[] charBuffer) {
/* 3715 */     int currentOffset = utfOffset;
/* 3716 */     int endOffset = currentOffset + utfLength;
/* 3717 */     int strLength = 0;
/* 3718 */     byte[] classBuffer = this.classFileBuffer;
/* 3719 */     while (currentOffset < endOffset) {
/* 3720 */       int currentByte = classBuffer[currentOffset++];
/* 3721 */       if ((currentByte & 0x80) == 0) {
/* 3722 */         charBuffer[strLength++] = (char)(currentByte & 0x7F); continue;
/* 3723 */       }  if ((currentByte & 0xE0) == 192) {
/* 3724 */         charBuffer[strLength++] = (char)(((currentByte & 0x1F) << 6) + (classBuffer[currentOffset++] & 0x3F));
/*      */         continue;
/*      */       } 
/* 3727 */       charBuffer[strLength++] = (char)(((currentByte & 0xF) << 12) + ((classBuffer[currentOffset++] & 0x3F) << 6) + (classBuffer[currentOffset++] & 0x3F));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3734 */     return new String(charBuffer, 0, strLength);
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
/*      */   private String readStringish(int offset, char[] charBuffer) {
/* 3753 */     return readUTF8(this.cpInfoOffsets[readUnsignedShort(offset)], charBuffer);
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
/*      */   public String readClass(int offset, char[] charBuffer) {
/* 3768 */     return readStringish(offset, charBuffer);
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
/*      */   public String readModule(int offset, char[] charBuffer) {
/* 3783 */     return readStringish(offset, charBuffer);
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
/*      */   public String readPackage(int offset, char[] charBuffer) {
/* 3798 */     return readStringish(offset, charBuffer);
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
/*      */   private ConstantDynamic readConstantDynamic(int constantPoolEntryIndex, char[] charBuffer) {
/* 3812 */     ConstantDynamic constantDynamic = this.constantDynamicValues[constantPoolEntryIndex];
/* 3813 */     if (constantDynamic != null) {
/* 3814 */       return constantDynamic;
/*      */     }
/* 3816 */     int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
/* 3817 */     int nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
/* 3818 */     String name = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 3819 */     String descriptor = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 3820 */     int bootstrapMethodOffset = this.bootstrapMethodOffsets[readUnsignedShort(cpInfoOffset)];
/* 3821 */     Handle handle = (Handle)readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/* 3822 */     Object[] bootstrapMethodArguments = new Object[readUnsignedShort(bootstrapMethodOffset + 2)];
/* 3823 */     bootstrapMethodOffset += 4;
/* 3824 */     for (int i = 0; i < bootstrapMethodArguments.length; i++) {
/* 3825 */       bootstrapMethodArguments[i] = readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/* 3826 */       bootstrapMethodOffset += 2;
/*      */     } 
/* 3828 */     this.constantDynamicValues[constantPoolEntryIndex] = new ConstantDynamic(name, descriptor, handle, bootstrapMethodArguments); return new ConstantDynamic(name, descriptor, handle, bootstrapMethodArguments);
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
/*      */   public Object readConst(int constantPoolEntryIndex, char[] charBuffer) {
/*      */     int referenceKind, referenceCpInfoOffset, nameAndTypeCpInfoOffset;
/*      */     String owner, name, descriptor;
/*      */     boolean isInterface;
/* 3847 */     int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
/* 3848 */     switch (this.classFileBuffer[cpInfoOffset - 1]) {
/*      */       case 3:
/* 3850 */         return Integer.valueOf(readInt(cpInfoOffset));
/*      */       case 4:
/* 3852 */         return Float.valueOf(Float.intBitsToFloat(readInt(cpInfoOffset)));
/*      */       case 5:
/* 3854 */         return Long.valueOf(readLong(cpInfoOffset));
/*      */       case 6:
/* 3856 */         return Double.valueOf(Double.longBitsToDouble(readLong(cpInfoOffset)));
/*      */       case 7:
/* 3858 */         return Type.getObjectType(readUTF8(cpInfoOffset, charBuffer));
/*      */       case 8:
/* 3860 */         return readUTF8(cpInfoOffset, charBuffer);
/*      */       case 16:
/* 3862 */         return Type.getMethodType(readUTF8(cpInfoOffset, charBuffer));
/*      */       case 15:
/* 3864 */         referenceKind = readByte(cpInfoOffset);
/* 3865 */         referenceCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 1)];
/* 3866 */         nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(referenceCpInfoOffset + 2)];
/* 3867 */         owner = readClass(referenceCpInfoOffset, charBuffer);
/* 3868 */         name = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 3869 */         descriptor = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 3870 */         isInterface = (this.classFileBuffer[referenceCpInfoOffset - 1] == 11);
/*      */         
/* 3872 */         return new Handle(referenceKind, owner, name, descriptor, isInterface);
/*      */       case 17:
/* 3874 */         return readConstantDynamic(constantPoolEntryIndex, charBuffer);
/*      */     } 
/* 3876 */     throw new IllegalArgumentException();
/*      */   }
/*      */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/ClassReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */