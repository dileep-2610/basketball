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
/*      */ class Frame
/*      */ {
/*      */   static final int SAME_FRAME = 0;
/*      */   static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
/*      */   static final int RESERVED = 128;
/*      */   static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
/*      */   static final int CHOP_FRAME = 248;
/*      */   static final int SAME_FRAME_EXTENDED = 251;
/*      */   static final int APPEND_FRAME = 252;
/*      */   static final int FULL_FRAME = 255;
/*      */   static final int ITEM_TOP = 0;
/*      */   static final int ITEM_INTEGER = 1;
/*      */   static final int ITEM_FLOAT = 2;
/*      */   static final int ITEM_DOUBLE = 3;
/*      */   static final int ITEM_LONG = 4;
/*      */   static final int ITEM_NULL = 5;
/*      */   static final int ITEM_UNINITIALIZED_THIS = 6;
/*      */   static final int ITEM_OBJECT = 7;
/*      */   static final int ITEM_UNINITIALIZED = 8;
/*      */   private static final int ITEM_ASM_BOOLEAN = 9;
/*      */   private static final int ITEM_ASM_BYTE = 10;
/*      */   private static final int ITEM_ASM_CHAR = 11;
/*      */   private static final int ITEM_ASM_SHORT = 12;
/*      */   private static final int DIM_SIZE = 6;
/*      */   private static final int KIND_SIZE = 4;
/*      */   private static final int FLAGS_SIZE = 2;
/*      */   private static final int VALUE_SIZE = 20;
/*      */   private static final int DIM_SHIFT = 26;
/*      */   private static final int KIND_SHIFT = 22;
/*      */   private static final int FLAGS_SHIFT = 20;
/*      */   private static final int DIM_MASK = -67108864;
/*      */   private static final int KIND_MASK = 62914560;
/*      */   private static final int VALUE_MASK = 1048575;
/*      */   private static final int ARRAY_OF = 67108864;
/*      */   private static final int ELEMENT_OF = -67108864;
/*      */   private static final int CONSTANT_KIND = 4194304;
/*      */   private static final int REFERENCE_KIND = 8388608;
/*      */   private static final int UNINITIALIZED_KIND = 12582912;
/*      */   private static final int FORWARD_UNINITIALIZED_KIND = 16777216;
/*      */   private static final int LOCAL_KIND = 20971520;
/*      */   private static final int STACK_KIND = 25165824;
/*      */   private static final int TOP_IF_LONG_OR_DOUBLE_FLAG = 1048576;
/*      */   private static final int TOP = 4194304;
/*      */   private static final int BOOLEAN = 4194313;
/*      */   private static final int BYTE = 4194314;
/*      */   private static final int CHAR = 4194315;
/*      */   private static final int SHORT = 4194316;
/*      */   private static final int INTEGER = 4194305;
/*      */   private static final int FLOAT = 4194306;
/*      */   private static final int LONG = 4194308;
/*      */   private static final int DOUBLE = 4194307;
/*      */   private static final int NULL = 4194309;
/*      */   private static final int UNINITIALIZED_THIS = 4194310;
/*      */   Label owner;
/*      */   private int[] inputLocals;
/*      */   private int[] inputStack;
/*      */   private int[] outputLocals;
/*      */   private int[] outputStack;
/*      */   private short outputStackStart;
/*      */   private short outputStackTop;
/*      */   private int initializationCount;
/*      */   private int[] initializations;
/*      */   
/*      */   Frame(Label owner) {
/*  247 */     this.owner = owner;
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
/*      */   final void copyFrom(Frame frame) {
/*  259 */     this.inputLocals = frame.inputLocals;
/*  260 */     this.inputStack = frame.inputStack;
/*  261 */     this.outputStackStart = 0;
/*  262 */     this.outputLocals = frame.outputLocals;
/*  263 */     this.outputStack = frame.outputStack;
/*  264 */     this.outputStackTop = frame.outputStackTop;
/*  265 */     this.initializationCount = frame.initializationCount;
/*  266 */     this.initializations = frame.initializations;
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
/*      */   static int getAbstractTypeFromApiFormat(SymbolTable symbolTable, Object type) {
/*  285 */     if (type instanceof Integer)
/*  286 */       return 0x400000 | ((Integer)type).intValue(); 
/*  287 */     if (type instanceof String) {
/*  288 */       String descriptor = Type.getObjectType((String)type).getDescriptor();
/*  289 */       return getAbstractTypeFromDescriptor(symbolTable, descriptor, 0);
/*      */     } 
/*  291 */     Label label = (Label)type;
/*  292 */     if ((label.flags & 0x4) != 0) {
/*  293 */       return 0xC00000 | symbolTable.addUninitializedType("", label.bytecodeOffset);
/*      */     }
/*  295 */     return 0x1000000 | symbolTable.addForwardUninitializedType("", label);
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
/*      */   static int getAbstractTypeFromInternalName(SymbolTable symbolTable, String internalName) {
/*  310 */     return 0x800000 | symbolTable.addType(internalName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getAbstractTypeFromDescriptor(SymbolTable symbolTable, String buffer, int offset) {
/*      */     String internalName;
/*      */     int elementDescriptorOffset;
/*      */     int typeValue;
/*  324 */     switch (buffer.charAt(offset)) {
/*      */       case 'V':
/*  326 */         return 0;
/*      */       case 'B':
/*      */       case 'C':
/*      */       case 'I':
/*      */       case 'S':
/*      */       case 'Z':
/*  332 */         return 4194305;
/*      */       case 'F':
/*  334 */         return 4194306;
/*      */       case 'J':
/*  336 */         return 4194308;
/*      */       case 'D':
/*  338 */         return 4194307;
/*      */       case 'L':
/*  340 */         internalName = buffer.substring(offset + 1, buffer.length() - 1);
/*  341 */         return 0x800000 | symbolTable.addType(internalName);
/*      */       case '[':
/*  343 */         elementDescriptorOffset = offset + 1;
/*  344 */         while (buffer.charAt(elementDescriptorOffset) == '[') {
/*  345 */           elementDescriptorOffset++;
/*      */         }
/*      */         
/*  348 */         switch (buffer.charAt(elementDescriptorOffset)) {
/*      */           case 'Z':
/*  350 */             typeValue = 4194313;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  381 */             return elementDescriptorOffset - offset << 26 | typeValue;case 'C': typeValue = 4194315; return elementDescriptorOffset - offset << 26 | typeValue;case 'B': typeValue = 4194314; return elementDescriptorOffset - offset << 26 | typeValue;case 'S': typeValue = 4194316; return elementDescriptorOffset - offset << 26 | typeValue;case 'I': typeValue = 4194305; return elementDescriptorOffset - offset << 26 | typeValue;case 'F': typeValue = 4194306; return elementDescriptorOffset - offset << 26 | typeValue;case 'J': typeValue = 4194308; return elementDescriptorOffset - offset << 26 | typeValue;case 'D': typeValue = 4194307; return elementDescriptorOffset - offset << 26 | typeValue;case 'L': internalName = buffer.substring(elementDescriptorOffset + 1, buffer.length() - 1); typeValue = 0x800000 | symbolTable.addType(internalName); return elementDescriptorOffset - offset << 26 | typeValue;
/*      */         }  throw new IllegalArgumentException(stringConcat$0(buffer.substring(elementDescriptorOffset)));
/*  383 */     }  throw new IllegalArgumentException(stringConcat$1(buffer.substring(offset)));
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
/*      */   final void setInputFrameFromDescriptor(SymbolTable symbolTable, int access, String descriptor, int maxLocals) {
/*  406 */     this.inputLocals = new int[maxLocals];
/*  407 */     this.inputStack = new int[0];
/*  408 */     int inputLocalIndex = 0;
/*  409 */     if ((access & 0x8) == 0) {
/*  410 */       if ((access & 0x40000) == 0) {
/*  411 */         this.inputLocals[inputLocalIndex++] = 0x800000 | symbolTable
/*  412 */           .addType(symbolTable.getClassName());
/*      */       } else {
/*  414 */         this.inputLocals[inputLocalIndex++] = 4194310;
/*      */       } 
/*      */     }
/*  417 */     for (Type argumentType : Type.getArgumentTypes(descriptor)) {
/*      */       
/*  419 */       int abstractType = getAbstractTypeFromDescriptor(symbolTable, argumentType.getDescriptor(), 0);
/*  420 */       this.inputLocals[inputLocalIndex++] = abstractType;
/*  421 */       if (abstractType == 4194308 || abstractType == 4194307) {
/*  422 */         this.inputLocals[inputLocalIndex++] = 4194304;
/*      */       }
/*      */     } 
/*  425 */     while (inputLocalIndex < maxLocals) {
/*  426 */       this.inputLocals[inputLocalIndex++] = 4194304;
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
/*      */   final void setInputFrameFromApiFormat(SymbolTable symbolTable, int numLocal, Object[] local, int numStack, Object[] stack) {
/*  447 */     int inputLocalIndex = 0;
/*  448 */     for (int i = 0; i < numLocal; i++) {
/*  449 */       this.inputLocals[inputLocalIndex++] = getAbstractTypeFromApiFormat(symbolTable, local[i]);
/*  450 */       if (local[i] == Opcodes.LONG || local[i] == Opcodes.DOUBLE) {
/*  451 */         this.inputLocals[inputLocalIndex++] = 4194304;
/*      */       }
/*      */     } 
/*  454 */     while (inputLocalIndex < this.inputLocals.length) {
/*  455 */       this.inputLocals[inputLocalIndex++] = 4194304;
/*      */     }
/*  457 */     int numStackTop = 0;
/*  458 */     for (int j = 0; j < numStack; j++) {
/*  459 */       if (stack[j] == Opcodes.LONG || stack[j] == Opcodes.DOUBLE) {
/*  460 */         numStackTop++;
/*      */       }
/*      */     } 
/*  463 */     this.inputStack = new int[numStack + numStackTop];
/*  464 */     int inputStackIndex = 0;
/*  465 */     for (int k = 0; k < numStack; k++) {
/*  466 */       this.inputStack[inputStackIndex++] = getAbstractTypeFromApiFormat(symbolTable, stack[k]);
/*  467 */       if (stack[k] == Opcodes.LONG || stack[k] == Opcodes.DOUBLE) {
/*  468 */         this.inputStack[inputStackIndex++] = 4194304;
/*      */       }
/*      */     } 
/*  471 */     this.outputStackTop = 0;
/*  472 */     this.initializationCount = 0;
/*      */   }
/*      */   
/*      */   final int getInputStackSize() {
/*  476 */     return this.inputStack.length;
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
/*      */   private int getLocal(int localIndex) {
/*  490 */     if (this.outputLocals == null || localIndex >= this.outputLocals.length)
/*      */     {
/*      */       
/*  493 */       return 0x1400000 | localIndex;
/*      */     }
/*  495 */     int abstractType = this.outputLocals[localIndex];
/*  496 */     if (abstractType == 0)
/*      */     {
/*      */       
/*  499 */       abstractType = this.outputLocals[localIndex] = 0x1400000 | localIndex;
/*      */     }
/*  501 */     return abstractType;
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
/*      */   private void setLocal(int localIndex, int abstractType) {
/*  513 */     if (this.outputLocals == null) {
/*  514 */       this.outputLocals = new int[10];
/*      */     }
/*  516 */     int outputLocalsLength = this.outputLocals.length;
/*  517 */     if (localIndex >= outputLocalsLength) {
/*  518 */       int[] newOutputLocals = new int[Math.max(localIndex + 1, 2 * outputLocalsLength)];
/*  519 */       System.arraycopy(this.outputLocals, 0, newOutputLocals, 0, outputLocalsLength);
/*  520 */       this.outputLocals = newOutputLocals;
/*      */     } 
/*      */     
/*  523 */     this.outputLocals[localIndex] = abstractType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void push(int abstractType) {
/*  533 */     if (this.outputStack == null) {
/*  534 */       this.outputStack = new int[10];
/*      */     }
/*  536 */     int outputStackLength = this.outputStack.length;
/*  537 */     if (this.outputStackTop >= outputStackLength) {
/*  538 */       int[] newOutputStack = new int[Math.max(this.outputStackTop + 1, 2 * outputStackLength)];
/*  539 */       System.arraycopy(this.outputStack, 0, newOutputStack, 0, outputStackLength);
/*  540 */       this.outputStack = newOutputStack;
/*      */     } 
/*      */     
/*  543 */     this.outputStackTop = (short)(this.outputStackTop + 1); this.outputStack[this.outputStackTop] = abstractType;
/*      */ 
/*      */     
/*  546 */     short outputStackSize = (short)(this.outputStackStart + this.outputStackTop);
/*  547 */     if (outputStackSize > this.owner.outputStackMax) {
/*  548 */       this.owner.outputStackMax = outputStackSize;
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
/*      */   private void push(SymbolTable symbolTable, String descriptor) {
/*  560 */     int typeDescriptorOffset = (descriptor.charAt(0) == '(') ? Type.getReturnTypeOffset(descriptor) : 0;
/*  561 */     int abstractType = getAbstractTypeFromDescriptor(symbolTable, descriptor, typeDescriptorOffset);
/*  562 */     if (abstractType != 0) {
/*  563 */       push(abstractType);
/*  564 */       if (abstractType == 4194308 || abstractType == 4194307) {
/*  565 */         push(4194304);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int pop() {
/*  576 */     if (this.outputStackTop > 0) {
/*  577 */       return this.outputStack[this.outputStackTop = (short)(this.outputStackTop - 1)];
/*      */     }
/*      */     
/*  580 */     return 0x1800000 | -(this.outputStackStart = (short)(this.outputStackStart - 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void pop(int elements) {
/*  590 */     if (this.outputStackTop >= elements) {
/*  591 */       this.outputStackTop = (short)(this.outputStackTop - elements);
/*      */     }
/*      */     else {
/*      */       
/*  595 */       this.outputStackStart = (short)(this.outputStackStart - elements - this.outputStackTop);
/*  596 */       this.outputStackTop = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void pop(String descriptor) {
/*  606 */     char firstDescriptorChar = descriptor.charAt(0);
/*  607 */     if (firstDescriptorChar == '(') {
/*  608 */       pop((Type.getArgumentsAndReturnSizes(descriptor) >> 2) - 1);
/*  609 */     } else if (firstDescriptorChar == 'J' || firstDescriptorChar == 'D') {
/*  610 */       pop(2);
/*      */     } else {
/*  612 */       pop(1);
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
/*      */   private void addInitializedType(int abstractType) {
/*  628 */     if (this.initializations == null) {
/*  629 */       this.initializations = new int[2];
/*      */     }
/*  631 */     int initializationsLength = this.initializations.length;
/*  632 */     if (this.initializationCount >= initializationsLength) {
/*      */       
/*  634 */       int[] newInitializations = new int[Math.max(this.initializationCount + 1, 2 * initializationsLength)];
/*  635 */       System.arraycopy(this.initializations, 0, newInitializations, 0, initializationsLength);
/*  636 */       this.initializations = newInitializations;
/*      */     } 
/*      */     
/*  639 */     this.initializations[this.initializationCount++] = abstractType;
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
/*      */   private int getInitializedType(SymbolTable symbolTable, int abstractType) {
/*  653 */     if (abstractType == 4194310 || (abstractType & 0xFFC00000) == 12582912 || (abstractType & 0xFFC00000) == 16777216)
/*      */     {
/*      */       
/*  656 */       for (int i = 0; i < this.initializationCount; i++) {
/*  657 */         int initializedType = this.initializations[i];
/*  658 */         int dim = initializedType & 0xFC000000;
/*  659 */         int kind = initializedType & 0x3C00000;
/*  660 */         int value = initializedType & 0xFFFFF;
/*  661 */         if (kind == 20971520) {
/*  662 */           initializedType = dim + this.inputLocals[value];
/*  663 */         } else if (kind == 25165824) {
/*  664 */           initializedType = dim + this.inputStack[this.inputStack.length - value];
/*      */         } 
/*  666 */         if (abstractType == initializedType) {
/*  667 */           if (abstractType == 4194310) {
/*  668 */             return 0x800000 | symbolTable.addType(symbolTable.getClassName());
/*      */           }
/*  670 */           return 0x800000 | symbolTable
/*  671 */             .addType((symbolTable.getType(abstractType & 0xFFFFF)).value);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  676 */     return abstractType;
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
/*      */   void execute(int opcode, int arg, Symbol argSymbol, SymbolTable symbolTable) {
/*      */     int abstractType1;
/*      */     int abstractType2;
/*      */     int abstractType3;
/*      */     int abstractType4;
/*      */     String arrayElementType;
/*      */     String castType;
/*  698 */     switch (opcode) {
/*      */       case 0:
/*      */       case 116:
/*      */       case 117:
/*      */       case 118:
/*      */       case 119:
/*      */       case 145:
/*      */       case 146:
/*      */       case 147:
/*      */       case 167:
/*      */       case 177:
/*      */         return;
/*      */       case 1:
/*  711 */         push(4194309);
/*      */       
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 16:
/*      */       case 17:
/*      */       case 21:
/*  723 */         push(4194305);
/*      */       
/*      */       case 9:
/*      */       case 10:
/*      */       case 22:
/*  728 */         push(4194308);
/*  729 */         push(4194304);
/*      */       
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*      */       case 23:
/*  735 */         push(4194306);
/*      */       
/*      */       case 14:
/*      */       case 15:
/*      */       case 24:
/*  740 */         push(4194307);
/*  741 */         push(4194304);
/*      */       
/*      */       case 18:
/*  744 */         switch (argSymbol.tag) {
/*      */           case 3:
/*  746 */             push(4194305);
/*      */           
/*      */           case 5:
/*  749 */             push(4194308);
/*  750 */             push(4194304);
/*      */           
/*      */           case 4:
/*  753 */             push(4194306);
/*      */           
/*      */           case 6:
/*  756 */             push(4194307);
/*  757 */             push(4194304);
/*      */           
/*      */           case 7:
/*  760 */             push(0x800000 | symbolTable.addType("java/lang/Class"));
/*      */           
/*      */           case 8:
/*  763 */             push(0x800000 | symbolTable.addType("java/lang/String"));
/*      */           
/*      */           case 16:
/*  766 */             push(0x800000 | symbolTable.addType("java/lang/invoke/MethodType"));
/*      */           
/*      */           case 15:
/*  769 */             push(0x800000 | symbolTable.addType("java/lang/invoke/MethodHandle"));
/*      */           
/*      */           case 17:
/*  772 */             push(symbolTable, argSymbol.value);
/*      */         } 
/*      */         
/*  775 */         throw new AssertionError();
/*      */ 
/*      */       
/*      */       case 25:
/*  779 */         push(getLocal(arg));
/*      */       
/*      */       case 47:
/*      */       case 143:
/*  783 */         pop(2);
/*  784 */         push(4194308);
/*  785 */         push(4194304);
/*      */       
/*      */       case 49:
/*      */       case 138:
/*  789 */         pop(2);
/*  790 */         push(4194307);
/*  791 */         push(4194304);
/*      */       
/*      */       case 50:
/*  794 */         pop(1);
/*  795 */         abstractType1 = pop();
/*  796 */         push((abstractType1 == 4194309) ? abstractType1 : (-67108864 + abstractType1));
/*      */       
/*      */       case 54:
/*      */       case 56:
/*      */       case 58:
/*  801 */         abstractType1 = pop();
/*  802 */         setLocal(arg, abstractType1);
/*  803 */         if (arg > 0) {
/*  804 */           int previousLocalType = getLocal(arg - 1);
/*  805 */           if (previousLocalType == 4194308 || previousLocalType == 4194307) {
/*  806 */             setLocal(arg - 1, 4194304);
/*  807 */           } else if ((previousLocalType & 0x3C00000) == 20971520 || (previousLocalType & 0x3C00000) == 25165824) {
/*      */ 
/*      */ 
/*      */             
/*  811 */             setLocal(arg - 1, previousLocalType | 0x100000);
/*      */           } 
/*      */         } 
/*      */       
/*      */       case 55:
/*      */       case 57:
/*  817 */         pop(1);
/*  818 */         abstractType1 = pop();
/*  819 */         setLocal(arg, abstractType1);
/*  820 */         setLocal(arg + 1, 4194304);
/*  821 */         if (arg > 0) {
/*  822 */           int previousLocalType = getLocal(arg - 1);
/*  823 */           if (previousLocalType == 4194308 || previousLocalType == 4194307) {
/*  824 */             setLocal(arg - 1, 4194304);
/*  825 */           } else if ((previousLocalType & 0x3C00000) == 20971520 || (previousLocalType & 0x3C00000) == 25165824) {
/*      */ 
/*      */ 
/*      */             
/*  829 */             setLocal(arg - 1, previousLocalType | 0x100000);
/*      */           } 
/*      */         } 
/*      */       
/*      */       case 79:
/*      */       case 81:
/*      */       case 83:
/*      */       case 84:
/*      */       case 85:
/*      */       case 86:
/*  839 */         pop(3);
/*      */       
/*      */       case 80:
/*      */       case 82:
/*  843 */         pop(4);
/*      */       
/*      */       case 87:
/*      */       case 153:
/*      */       case 154:
/*      */       case 155:
/*      */       case 156:
/*      */       case 157:
/*      */       case 158:
/*      */       case 170:
/*      */       case 171:
/*      */       case 172:
/*      */       case 174:
/*      */       case 176:
/*      */       case 191:
/*      */       case 194:
/*      */       case 195:
/*      */       case 198:
/*      */       case 199:
/*  862 */         pop(1);
/*      */       
/*      */       case 88:
/*      */       case 159:
/*      */       case 160:
/*      */       case 161:
/*      */       case 162:
/*      */       case 163:
/*      */       case 164:
/*      */       case 165:
/*      */       case 166:
/*      */       case 173:
/*      */       case 175:
/*  875 */         pop(2);
/*      */       
/*      */       case 89:
/*  878 */         abstractType1 = pop();
/*  879 */         push(abstractType1);
/*  880 */         push(abstractType1);
/*      */       
/*      */       case 90:
/*  883 */         abstractType1 = pop();
/*  884 */         abstractType2 = pop();
/*  885 */         push(abstractType1);
/*  886 */         push(abstractType2);
/*  887 */         push(abstractType1);
/*      */       
/*      */       case 91:
/*  890 */         abstractType1 = pop();
/*  891 */         abstractType2 = pop();
/*  892 */         abstractType3 = pop();
/*  893 */         push(abstractType1);
/*  894 */         push(abstractType3);
/*  895 */         push(abstractType2);
/*  896 */         push(abstractType1);
/*      */       
/*      */       case 92:
/*  899 */         abstractType1 = pop();
/*  900 */         abstractType2 = pop();
/*  901 */         push(abstractType2);
/*  902 */         push(abstractType1);
/*  903 */         push(abstractType2);
/*  904 */         push(abstractType1);
/*      */       
/*      */       case 93:
/*  907 */         abstractType1 = pop();
/*  908 */         abstractType2 = pop();
/*  909 */         abstractType3 = pop();
/*  910 */         push(abstractType2);
/*  911 */         push(abstractType1);
/*  912 */         push(abstractType3);
/*  913 */         push(abstractType2);
/*  914 */         push(abstractType1);
/*      */       
/*      */       case 94:
/*  917 */         abstractType1 = pop();
/*  918 */         abstractType2 = pop();
/*  919 */         abstractType3 = pop();
/*  920 */         abstractType4 = pop();
/*  921 */         push(abstractType2);
/*  922 */         push(abstractType1);
/*  923 */         push(abstractType4);
/*  924 */         push(abstractType3);
/*  925 */         push(abstractType2);
/*  926 */         push(abstractType1);
/*      */       
/*      */       case 95:
/*  929 */         abstractType1 = pop();
/*  930 */         abstractType2 = pop();
/*  931 */         push(abstractType1);
/*  932 */         push(abstractType2);
/*      */       
/*      */       case 46:
/*      */       case 51:
/*      */       case 52:
/*      */       case 53:
/*      */       case 96:
/*      */       case 100:
/*      */       case 104:
/*      */       case 108:
/*      */       case 112:
/*      */       case 120:
/*      */       case 122:
/*      */       case 124:
/*      */       case 126:
/*      */       case 128:
/*      */       case 130:
/*      */       case 136:
/*      */       case 142:
/*      */       case 149:
/*      */       case 150:
/*  953 */         pop(2);
/*  954 */         push(4194305);
/*      */       
/*      */       case 97:
/*      */       case 101:
/*      */       case 105:
/*      */       case 109:
/*      */       case 113:
/*      */       case 127:
/*      */       case 129:
/*      */       case 131:
/*  964 */         pop(4);
/*  965 */         push(4194308);
/*  966 */         push(4194304);
/*      */       
/*      */       case 48:
/*      */       case 98:
/*      */       case 102:
/*      */       case 106:
/*      */       case 110:
/*      */       case 114:
/*      */       case 137:
/*      */       case 144:
/*  976 */         pop(2);
/*  977 */         push(4194306);
/*      */       
/*      */       case 99:
/*      */       case 103:
/*      */       case 107:
/*      */       case 111:
/*      */       case 115:
/*  984 */         pop(4);
/*  985 */         push(4194307);
/*  986 */         push(4194304);
/*      */       
/*      */       case 121:
/*      */       case 123:
/*      */       case 125:
/*  991 */         pop(3);
/*  992 */         push(4194308);
/*  993 */         push(4194304);
/*      */       
/*      */       case 132:
/*  996 */         setLocal(arg, 4194305);
/*      */       
/*      */       case 133:
/*      */       case 140:
/* 1000 */         pop(1);
/* 1001 */         push(4194308);
/* 1002 */         push(4194304);
/*      */       
/*      */       case 134:
/* 1005 */         pop(1);
/* 1006 */         push(4194306);
/*      */       
/*      */       case 135:
/*      */       case 141:
/* 1010 */         pop(1);
/* 1011 */         push(4194307);
/* 1012 */         push(4194304);
/*      */       
/*      */       case 139:
/*      */       case 190:
/*      */       case 193:
/* 1017 */         pop(1);
/* 1018 */         push(4194305);
/*      */       
/*      */       case 148:
/*      */       case 151:
/*      */       case 152:
/* 1023 */         pop(4);
/* 1024 */         push(4194305);
/*      */       
/*      */       case 168:
/*      */       case 169:
/* 1028 */         throw new IllegalArgumentException("JSR/RET are not supported with computeFrames option");
/*      */       case 178:
/* 1030 */         push(symbolTable, argSymbol.value);
/*      */       
/*      */       case 179:
/* 1033 */         pop(argSymbol.value);
/*      */       
/*      */       case 180:
/* 1036 */         pop(1);
/* 1037 */         push(symbolTable, argSymbol.value);
/*      */       
/*      */       case 181:
/* 1040 */         pop(argSymbol.value);
/* 1041 */         pop();
/*      */       
/*      */       case 182:
/*      */       case 183:
/*      */       case 184:
/*      */       case 185:
/* 1047 */         pop(argSymbol.value);
/* 1048 */         if (opcode != 184) {
/* 1049 */           abstractType1 = pop();
/* 1050 */           if (opcode == 183 && argSymbol.name.charAt(0) == '<') {
/* 1051 */             addInitializedType(abstractType1);
/*      */           }
/*      */         } 
/* 1054 */         push(symbolTable, argSymbol.value);
/*      */       
/*      */       case 186:
/* 1057 */         pop(argSymbol.value);
/* 1058 */         push(symbolTable, argSymbol.value);
/*      */       
/*      */       case 187:
/* 1061 */         push(0xC00000 | symbolTable.addUninitializedType(argSymbol.value, arg));
/*      */       
/*      */       case 188:
/* 1064 */         pop();
/* 1065 */         switch (arg) {
/*      */           case 4:
/* 1067 */             push(71303177);
/*      */           
/*      */           case 5:
/* 1070 */             push(71303179);
/*      */           
/*      */           case 8:
/* 1073 */             push(71303178);
/*      */           
/*      */           case 9:
/* 1076 */             push(71303180);
/*      */           
/*      */           case 10:
/* 1079 */             push(71303169);
/*      */           
/*      */           case 6:
/* 1082 */             push(71303170);
/*      */           
/*      */           case 7:
/* 1085 */             push(71303171);
/*      */           
/*      */           case 11:
/* 1088 */             push(71303172);
/*      */         } 
/*      */         
/* 1091 */         throw new IllegalArgumentException();
/*      */ 
/*      */       
/*      */       case 189:
/* 1095 */         arrayElementType = argSymbol.value;
/* 1096 */         pop();
/* 1097 */         if (arrayElementType.charAt(0) == '[') {
/* 1098 */           push(symbolTable, stringConcat$2(arrayElementType));
/*      */         } else {
/* 1100 */           push(0x4800000 | symbolTable.addType(arrayElementType));
/*      */         } 
/*      */       
/*      */       case 192:
/* 1104 */         castType = argSymbol.value;
/* 1105 */         pop();
/* 1106 */         if (castType.charAt(0) == '[') {
/* 1107 */           push(symbolTable, castType);
/*      */         } else {
/* 1109 */           push(0x800000 | symbolTable.addType(castType));
/*      */         } 
/*      */       
/*      */       case 197:
/* 1113 */         pop(arg);
/* 1114 */         push(symbolTable, argSymbol.value);
/*      */     } 
/*      */     
/* 1117 */     throw new IllegalArgumentException();
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
/*      */   private int getConcreteOutputType(int abstractOutputType, int numStack) {
/* 1134 */     int dim = abstractOutputType & 0xFC000000;
/* 1135 */     int kind = abstractOutputType & 0x3C00000;
/* 1136 */     if (kind == 20971520) {
/*      */ 
/*      */ 
/*      */       
/* 1140 */       int concreteOutputType = dim + this.inputLocals[abstractOutputType & 0xFFFFF];
/* 1141 */       if ((abstractOutputType & 0x100000) != 0 && (concreteOutputType == 4194308 || concreteOutputType == 4194307))
/*      */       {
/* 1143 */         concreteOutputType = 4194304;
/*      */       }
/* 1145 */       return concreteOutputType;
/* 1146 */     }  if (kind == 25165824) {
/*      */ 
/*      */ 
/*      */       
/* 1150 */       int concreteOutputType = dim + this.inputStack[numStack - (abstractOutputType & 0xFFFFF)];
/* 1151 */       if ((abstractOutputType & 0x100000) != 0 && (concreteOutputType == 4194308 || concreteOutputType == 4194307))
/*      */       {
/* 1153 */         concreteOutputType = 4194304;
/*      */       }
/* 1155 */       return concreteOutputType;
/*      */     } 
/* 1157 */     return abstractOutputType;
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
/*      */   final boolean merge(SymbolTable symbolTable, Frame dstFrame, int catchTypeIndex) {
/* 1175 */     boolean frameChanged = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1180 */     int numLocal = this.inputLocals.length;
/* 1181 */     int numStack = this.inputStack.length;
/* 1182 */     if (dstFrame.inputLocals == null) {
/* 1183 */       dstFrame.inputLocals = new int[numLocal];
/* 1184 */       frameChanged = true;
/*      */     }  int i;
/* 1186 */     for (i = 0; i < numLocal; i++) {
/*      */       int concreteOutputType;
/* 1188 */       if (this.outputLocals != null && i < this.outputLocals.length) {
/* 1189 */         int abstractOutputType = this.outputLocals[i];
/* 1190 */         if (abstractOutputType == 0) {
/*      */ 
/*      */           
/* 1193 */           concreteOutputType = this.inputLocals[i];
/*      */         } else {
/* 1195 */           concreteOutputType = getConcreteOutputType(abstractOutputType, numStack);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1200 */         concreteOutputType = this.inputLocals[i];
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1205 */       if (this.initializations != null) {
/* 1206 */         concreteOutputType = getInitializedType(symbolTable, concreteOutputType);
/*      */       }
/* 1208 */       frameChanged |= merge(symbolTable, concreteOutputType, dstFrame.inputLocals, i);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1216 */     if (catchTypeIndex > 0) {
/* 1217 */       for (i = 0; i < numLocal; i++) {
/* 1218 */         frameChanged |= merge(symbolTable, this.inputLocals[i], dstFrame.inputLocals, i);
/*      */       }
/* 1220 */       if (dstFrame.inputStack == null) {
/* 1221 */         dstFrame.inputStack = new int[1];
/* 1222 */         frameChanged = true;
/*      */       } 
/* 1224 */       frameChanged |= merge(symbolTable, catchTypeIndex, dstFrame.inputStack, 0);
/* 1225 */       return frameChanged;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1231 */     int numInputStack = this.inputStack.length + this.outputStackStart;
/* 1232 */     if (dstFrame.inputStack == null) {
/* 1233 */       dstFrame.inputStack = new int[numInputStack + this.outputStackTop];
/* 1234 */       frameChanged = true;
/*      */     } 
/*      */     
/*      */     int j;
/*      */     
/* 1239 */     for (j = 0; j < numInputStack; j++) {
/* 1240 */       int concreteOutputType = this.inputStack[j];
/* 1241 */       if (this.initializations != null) {
/* 1242 */         concreteOutputType = getInitializedType(symbolTable, concreteOutputType);
/*      */       }
/* 1244 */       frameChanged |= merge(symbolTable, concreteOutputType, dstFrame.inputStack, j);
/*      */     } 
/*      */ 
/*      */     
/* 1248 */     for (j = 0; j < this.outputStackTop; j++) {
/* 1249 */       int abstractOutputType = this.outputStack[j];
/* 1250 */       int concreteOutputType = getConcreteOutputType(abstractOutputType, numStack);
/* 1251 */       if (this.initializations != null) {
/* 1252 */         concreteOutputType = getInitializedType(symbolTable, concreteOutputType);
/*      */       }
/* 1254 */       frameChanged |= 
/* 1255 */         merge(symbolTable, concreteOutputType, dstFrame.inputStack, numInputStack + j);
/*      */     } 
/* 1257 */     return frameChanged;
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
/*      */   private static boolean merge(SymbolTable symbolTable, int sourceType, int[] dstTypes, int dstIndex) {
/* 1280 */     int mergedType, dstType = dstTypes[dstIndex];
/* 1281 */     if (dstType == sourceType)
/*      */     {
/* 1283 */       return false;
/*      */     }
/* 1285 */     int srcType = sourceType;
/* 1286 */     if ((sourceType & 0x3FFFFFF) == 4194309) {
/* 1287 */       if (dstType == 4194309) {
/* 1288 */         return false;
/*      */       }
/* 1290 */       srcType = 4194309;
/*      */     } 
/* 1292 */     if (dstType == 0) {
/*      */       
/* 1294 */       dstTypes[dstIndex] = srcType;
/* 1295 */       return true;
/*      */     } 
/*      */     
/* 1298 */     if ((dstType & 0xFC000000) != 0 || (dstType & 0x3C00000) == 8388608) {
/*      */       
/* 1300 */       if (srcType == 4194309)
/*      */       {
/* 1302 */         return false; } 
/* 1303 */       if ((srcType & 0xFFC00000) == (dstType & 0xFFC00000)) {
/*      */         
/* 1305 */         if ((dstType & 0x3C00000) == 8388608) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1311 */           mergedType = srcType & 0xFC000000 | 0x800000 | symbolTable.addMergedType(srcType & 0xFFFFF, dstType & 0xFFFFF);
/*      */         }
/*      */         else {
/*      */           
/* 1315 */           int mergedDim = -67108864 + (srcType & 0xFC000000);
/* 1316 */           mergedType = mergedDim | 0x800000 | symbolTable.addType("java/lang/Object");
/*      */         } 
/* 1318 */       } else if ((srcType & 0xFC000000) != 0 || (srcType & 0x3C00000) == 8388608) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1323 */         int srcDim = srcType & 0xFC000000;
/* 1324 */         if (srcDim != 0 && (srcType & 0x3C00000) != 8388608) {
/* 1325 */           srcDim = -67108864 + srcDim;
/*      */         }
/* 1327 */         int dstDim = dstType & 0xFC000000;
/* 1328 */         if (dstDim != 0 && (dstType & 0x3C00000) != 8388608) {
/* 1329 */           dstDim = -67108864 + dstDim;
/*      */         }
/*      */         
/* 1332 */         mergedType = Math.min(srcDim, dstDim) | 0x800000 | symbolTable.addType("java/lang/Object");
/*      */       } else {
/*      */         
/* 1335 */         mergedType = 4194304;
/*      */       } 
/* 1337 */     } else if (dstType == 4194309) {
/*      */ 
/*      */ 
/*      */       
/* 1341 */       mergedType = ((srcType & 0xFC000000) != 0 || (srcType & 0x3C00000) == 8388608) ? srcType : 4194304;
/*      */     } else {
/*      */       
/* 1344 */       mergedType = 4194304;
/*      */     } 
/* 1346 */     if (mergedType != dstType) {
/* 1347 */       dstTypes[dstIndex] = mergedType;
/* 1348 */       return true;
/*      */     } 
/* 1350 */     return false;
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
/*      */   final void accept(MethodWriter methodWriter) {
/* 1368 */     int[] localTypes = this.inputLocals;
/* 1369 */     int numLocal = 0;
/* 1370 */     int numTrailingTop = 0;
/* 1371 */     int i = 0;
/* 1372 */     while (i < localTypes.length) {
/* 1373 */       int localType = localTypes[i];
/* 1374 */       i += (localType == 4194308 || localType == 4194307) ? 2 : 1;
/* 1375 */       if (localType == 4194304) {
/* 1376 */         numTrailingTop++; continue;
/*      */       } 
/* 1378 */       numLocal += numTrailingTop + 1;
/* 1379 */       numTrailingTop = 0;
/*      */     } 
/*      */ 
/*      */     
/* 1383 */     int[] stackTypes = this.inputStack;
/* 1384 */     int numStack = 0;
/* 1385 */     i = 0;
/* 1386 */     while (i < stackTypes.length) {
/* 1387 */       int stackType = stackTypes[i];
/* 1388 */       i += (stackType == 4194308 || stackType == 4194307) ? 2 : 1;
/* 1389 */       numStack++;
/*      */     } 
/*      */     
/* 1392 */     int frameIndex = methodWriter.visitFrameStart(this.owner.bytecodeOffset, numLocal, numStack);
/* 1393 */     i = 0;
/* 1394 */     while (numLocal-- > 0) {
/* 1395 */       int localType = localTypes[i];
/* 1396 */       i += (localType == 4194308 || localType == 4194307) ? 2 : 1;
/* 1397 */       methodWriter.visitAbstractType(frameIndex++, localType);
/*      */     } 
/* 1399 */     i = 0;
/* 1400 */     while (numStack-- > 0) {
/* 1401 */       int stackType = stackTypes[i];
/* 1402 */       i += (stackType == 4194308 || stackType == 4194307) ? 2 : 1;
/* 1403 */       methodWriter.visitAbstractType(frameIndex++, stackType);
/*      */     } 
/* 1405 */     methodWriter.visitFrameEnd();
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
/*      */   static void putAbstractType(SymbolTable symbolTable, int abstractType, ByteVector output) {
/* 1422 */     int arrayDimensions = (abstractType & 0xFC000000) >> 26;
/* 1423 */     if (arrayDimensions == 0) {
/* 1424 */       int typeValue = abstractType & 0xFFFFF;
/* 1425 */       switch (abstractType & 0x3C00000) {
/*      */         case 4194304:
/* 1427 */           output.putByte(typeValue);
/*      */           return;
/*      */         case 8388608:
/* 1430 */           output
/* 1431 */             .putByte(7)
/* 1432 */             .putShort((symbolTable.addConstantClass((symbolTable.getType(typeValue)).value)).index);
/*      */           return;
/*      */         case 12582912:
/* 1435 */           output.putByte(8).putShort((int)(symbolTable.getType(typeValue)).data);
/*      */           return;
/*      */         case 16777216:
/* 1438 */           output.putByte(8);
/* 1439 */           symbolTable.getForwardUninitializedLabel(typeValue).put(output);
/*      */           return;
/*      */       } 
/* 1442 */       throw new AssertionError();
/*      */     } 
/*      */ 
/*      */     
/* 1446 */     StringBuilder typeDescriptor = new StringBuilder();
/* 1447 */     while (arrayDimensions-- > 0) {
/* 1448 */       typeDescriptor.append('[');
/*      */     }
/* 1450 */     if ((abstractType & 0x3C00000) == 8388608) {
/* 1451 */       typeDescriptor
/* 1452 */         .append('L')
/* 1453 */         .append((symbolTable.getType(abstractType & 0xFFFFF)).value)
/* 1454 */         .append(';');
/*      */     } else {
/* 1456 */       switch (abstractType & 0xFFFFF) {
/*      */         case 9:
/* 1458 */           typeDescriptor.append('Z');
/*      */           break;
/*      */         case 10:
/* 1461 */           typeDescriptor.append('B');
/*      */           break;
/*      */         case 11:
/* 1464 */           typeDescriptor.append('C');
/*      */           break;
/*      */         case 12:
/* 1467 */           typeDescriptor.append('S');
/*      */           break;
/*      */         case 1:
/* 1470 */           typeDescriptor.append('I');
/*      */           break;
/*      */         case 2:
/* 1473 */           typeDescriptor.append('F');
/*      */           break;
/*      */         case 4:
/* 1476 */           typeDescriptor.append('J');
/*      */           break;
/*      */         case 3:
/* 1479 */           typeDescriptor.append('D');
/*      */           break;
/*      */         default:
/* 1482 */           throw new AssertionError();
/*      */       } 
/*      */     } 
/* 1485 */     output
/* 1486 */       .putByte(7)
/* 1487 */       .putShort((symbolTable.addConstantClass(typeDescriptor.toString())).index);
/*      */   }
/*      */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/Frame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */