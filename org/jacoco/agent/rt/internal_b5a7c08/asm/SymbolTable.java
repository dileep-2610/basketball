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
/*      */ final class SymbolTable
/*      */ {
/*      */   final ClassWriter classWriter;
/*      */   private final ClassReader sourceClassReader;
/*      */   private int majorVersion;
/*      */   private String className;
/*      */   private int entryCount;
/*      */   private Entry[] entries;
/*      */   private int constantPoolCount;
/*      */   private ByteVector constantPool;
/*      */   private int bootstrapMethodCount;
/*      */   private ByteVector bootstrapMethods;
/*      */   private int typeCount;
/*      */   private Entry[] typeTable;
/*      */   private int labelCount;
/*      */   private LabelEntry[] labelTable;
/*      */   private LabelEntry[] labelEntries;
/*      */   
/*      */   SymbolTable(ClassWriter classWriter) {
/*  146 */     this.classWriter = classWriter;
/*  147 */     this.sourceClassReader = null;
/*  148 */     this.entries = new Entry[256];
/*  149 */     this.constantPoolCount = 1;
/*  150 */     this.constantPool = new ByteVector();
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
/*      */   SymbolTable(ClassWriter classWriter, ClassReader classReader) {
/*  162 */     this.classWriter = classWriter;
/*  163 */     this.sourceClassReader = classReader;
/*      */ 
/*      */     
/*  166 */     byte[] inputBytes = classReader.classFileBuffer;
/*  167 */     int constantPoolOffset = classReader.getItem(1) - 1;
/*  168 */     int constantPoolLength = classReader.header - constantPoolOffset;
/*  169 */     this.constantPoolCount = classReader.getItemCount();
/*  170 */     this.constantPool = new ByteVector(constantPoolLength);
/*  171 */     this.constantPool.putByteArray(inputBytes, constantPoolOffset, constantPoolLength);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  176 */     this.entries = new Entry[this.constantPoolCount * 2];
/*  177 */     char[] charBuffer = new char[classReader.getMaxStringLength()];
/*  178 */     boolean hasBootstrapMethods = false;
/*  179 */     int itemIndex = 1;
/*  180 */     while (itemIndex < this.constantPoolCount) {
/*  181 */       int nameAndTypeItemOffset, memberRefItemOffset, itemOffset = classReader.getItem(itemIndex);
/*  182 */       int itemTag = inputBytes[itemOffset - 1];
/*      */       
/*  184 */       switch (itemTag) {
/*      */         
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/*  189 */           nameAndTypeItemOffset = classReader.getItem(classReader.readUnsignedShort(itemOffset + 2));
/*  190 */           addConstantMemberReference(itemIndex, itemTag, classReader
/*      */ 
/*      */               
/*  193 */               .readClass(itemOffset, charBuffer), classReader
/*  194 */               .readUTF8(nameAndTypeItemOffset, charBuffer), classReader
/*  195 */               .readUTF8(nameAndTypeItemOffset + 2, charBuffer));
/*      */           break;
/*      */         case 3:
/*      */         case 4:
/*  199 */           addConstantIntegerOrFloat(itemIndex, itemTag, classReader.readInt(itemOffset));
/*      */           break;
/*      */         case 12:
/*  202 */           addConstantNameAndType(itemIndex, classReader
/*      */               
/*  204 */               .readUTF8(itemOffset, charBuffer), classReader
/*  205 */               .readUTF8(itemOffset + 2, charBuffer));
/*      */           break;
/*      */         case 5:
/*      */         case 6:
/*  209 */           addConstantLongOrDouble(itemIndex, itemTag, classReader.readLong(itemOffset));
/*      */           break;
/*      */         case 1:
/*  212 */           addConstantUtf8(itemIndex, classReader.readUtf(itemIndex, charBuffer));
/*      */           break;
/*      */         
/*      */         case 15:
/*  216 */           memberRefItemOffset = classReader.getItem(classReader.readUnsignedShort(itemOffset + 1));
/*      */           
/*  218 */           nameAndTypeItemOffset = classReader.getItem(classReader.readUnsignedShort(memberRefItemOffset + 2));
/*  219 */           addConstantMethodHandle(itemIndex, classReader
/*      */               
/*  221 */               .readByte(itemOffset), classReader
/*  222 */               .readClass(memberRefItemOffset, charBuffer), classReader
/*  223 */               .readUTF8(nameAndTypeItemOffset, charBuffer), classReader
/*  224 */               .readUTF8(nameAndTypeItemOffset + 2, charBuffer), 
/*  225 */               (classReader.readByte(memberRefItemOffset - 1) == 11));
/*      */           break;
/*      */         
/*      */         case 17:
/*      */         case 18:
/*  230 */           hasBootstrapMethods = true;
/*      */           
/*  232 */           nameAndTypeItemOffset = classReader.getItem(classReader.readUnsignedShort(itemOffset + 2));
/*  233 */           addConstantDynamicOrInvokeDynamicReference(itemTag, itemIndex, classReader
/*      */ 
/*      */               
/*  236 */               .readUTF8(nameAndTypeItemOffset, charBuffer), classReader
/*  237 */               .readUTF8(nameAndTypeItemOffset + 2, charBuffer), classReader
/*  238 */               .readUnsignedShort(itemOffset));
/*      */           break;
/*      */         case 7:
/*      */         case 8:
/*      */         case 16:
/*      */         case 19:
/*      */         case 20:
/*  245 */           addConstantUtf8Reference(itemIndex, itemTag, classReader
/*  246 */               .readUTF8(itemOffset, charBuffer));
/*      */           break;
/*      */         default:
/*  249 */           throw new IllegalArgumentException();
/*      */       } 
/*  251 */       itemIndex += (
/*  252 */         itemTag == 5 || itemTag == 6) ? 2 : 1;
/*      */     } 
/*      */ 
/*      */     
/*  256 */     if (hasBootstrapMethods) {
/*  257 */       copyBootstrapMethods(classReader, charBuffer);
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
/*      */   private void copyBootstrapMethods(ClassReader classReader, char[] charBuffer) {
/*  271 */     byte[] inputBytes = classReader.classFileBuffer;
/*  272 */     int currentAttributeOffset = classReader.getFirstAttributeOffset();
/*  273 */     for (int i = classReader.readUnsignedShort(currentAttributeOffset - 2); i > 0; i--) {
/*  274 */       String attributeName = classReader.readUTF8(currentAttributeOffset, charBuffer);
/*  275 */       if ("BootstrapMethods".equals(attributeName)) {
/*  276 */         this.bootstrapMethodCount = classReader.readUnsignedShort(currentAttributeOffset + 6);
/*      */         break;
/*      */       } 
/*  279 */       currentAttributeOffset += 6 + classReader.readInt(currentAttributeOffset + 2);
/*      */     } 
/*  281 */     if (this.bootstrapMethodCount > 0) {
/*      */       
/*  283 */       int bootstrapMethodsOffset = currentAttributeOffset + 8;
/*  284 */       int bootstrapMethodsLength = classReader.readInt(currentAttributeOffset + 2) - 2;
/*  285 */       this.bootstrapMethods = new ByteVector(bootstrapMethodsLength);
/*  286 */       this.bootstrapMethods.putByteArray(inputBytes, bootstrapMethodsOffset, bootstrapMethodsLength);
/*      */ 
/*      */       
/*  289 */       int currentOffset = bootstrapMethodsOffset;
/*  290 */       for (int j = 0; j < this.bootstrapMethodCount; j++) {
/*  291 */         int offset = currentOffset - bootstrapMethodsOffset;
/*  292 */         int bootstrapMethodRef = classReader.readUnsignedShort(currentOffset);
/*  293 */         currentOffset += 2;
/*  294 */         int numBootstrapArguments = classReader.readUnsignedShort(currentOffset);
/*  295 */         currentOffset += 2;
/*  296 */         int hashCode = classReader.readConst(bootstrapMethodRef, charBuffer).hashCode();
/*  297 */         while (numBootstrapArguments-- > 0) {
/*  298 */           int bootstrapArgument = classReader.readUnsignedShort(currentOffset);
/*  299 */           currentOffset += 2;
/*  300 */           hashCode ^= classReader.readConst(bootstrapArgument, charBuffer).hashCode();
/*      */         } 
/*  302 */         add(new Entry(j, 64, offset, hashCode & Integer.MAX_VALUE));
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
/*      */   ClassReader getSource() {
/*  314 */     return this.sourceClassReader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getMajorVersion() {
/*  323 */     return this.majorVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getClassName() {
/*  332 */     return this.className;
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
/*      */   int setMajorVersionAndClassName(int majorVersion, String className) {
/*  344 */     this.majorVersion = majorVersion;
/*  345 */     this.className = className;
/*  346 */     return (addConstantClass(className)).index;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getConstantPoolCount() {
/*  355 */     return this.constantPoolCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getConstantPoolLength() {
/*  364 */     return this.constantPool.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void putConstantPool(ByteVector output) {
/*  374 */     output.putShort(this.constantPoolCount).putByteArray(this.constantPool.data, 0, this.constantPool.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int computeBootstrapMethodsSize() {
/*  384 */     if (this.bootstrapMethods != null) {
/*  385 */       addConstantUtf8("BootstrapMethods");
/*  386 */       return 8 + this.bootstrapMethods.length;
/*      */     } 
/*  388 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void putBootstrapMethods(ByteVector output) {
/*  399 */     if (this.bootstrapMethods != null) {
/*  400 */       output
/*  401 */         .putShort(addConstantUtf8("BootstrapMethods"))
/*  402 */         .putInt(this.bootstrapMethods.length + 2)
/*  403 */         .putShort(this.bootstrapMethodCount)
/*  404 */         .putByteArray(this.bootstrapMethods.data, 0, this.bootstrapMethods.length);
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
/*      */   private Entry get(int hashCode) {
/*  420 */     return this.entries[hashCode % this.entries.length];
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
/*      */   private Entry put(Entry entry) {
/*  433 */     if (this.entryCount > this.entries.length * 3 / 4) {
/*  434 */       int currentCapacity = this.entries.length;
/*  435 */       int newCapacity = currentCapacity * 2 + 1;
/*  436 */       Entry[] newEntries = new Entry[newCapacity];
/*  437 */       for (int i = currentCapacity - 1; i >= 0; i--) {
/*  438 */         Entry currentEntry = this.entries[i];
/*  439 */         while (currentEntry != null) {
/*  440 */           int newCurrentEntryIndex = currentEntry.hashCode % newCapacity;
/*  441 */           Entry nextEntry = currentEntry.next;
/*  442 */           currentEntry.next = newEntries[newCurrentEntryIndex];
/*  443 */           newEntries[newCurrentEntryIndex] = currentEntry;
/*  444 */           currentEntry = nextEntry;
/*      */         } 
/*      */       } 
/*  447 */       this.entries = newEntries;
/*      */     } 
/*  449 */     this.entryCount++;
/*  450 */     int index = entry.hashCode % this.entries.length;
/*  451 */     entry.next = this.entries[index];
/*  452 */     this.entries[index] = entry; return entry;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void add(Entry entry) {
/*  463 */     this.entryCount++;
/*  464 */     int index = entry.hashCode % this.entries.length;
/*  465 */     entry.next = this.entries[index];
/*  466 */     this.entries[index] = entry;
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
/*      */   Symbol addConstant(Object value) {
/*  483 */     if (value instanceof Integer)
/*  484 */       return addConstantInteger(((Integer)value).intValue()); 
/*  485 */     if (value instanceof Byte)
/*  486 */       return addConstantInteger(((Byte)value).intValue()); 
/*  487 */     if (value instanceof Character)
/*  488 */       return addConstantInteger(((Character)value).charValue()); 
/*  489 */     if (value instanceof Short)
/*  490 */       return addConstantInteger(((Short)value).intValue()); 
/*  491 */     if (value instanceof Boolean)
/*  492 */       return addConstantInteger(((Boolean)value).booleanValue() ? 1 : 0); 
/*  493 */     if (value instanceof Float)
/*  494 */       return addConstantFloat(((Float)value).floatValue()); 
/*  495 */     if (value instanceof Long)
/*  496 */       return addConstantLong(((Long)value).longValue()); 
/*  497 */     if (value instanceof Double)
/*  498 */       return addConstantDouble(((Double)value).doubleValue()); 
/*  499 */     if (value instanceof String)
/*  500 */       return addConstantString((String)value); 
/*  501 */     if (value instanceof Type) {
/*  502 */       Type type = (Type)value;
/*  503 */       int typeSort = type.getSort();
/*  504 */       if (typeSort == 10)
/*  505 */         return addConstantClass(type.getInternalName()); 
/*  506 */       if (typeSort == 11) {
/*  507 */         return addConstantMethodType(type.getDescriptor());
/*      */       }
/*  509 */       return addConstantClass(type.getDescriptor());
/*      */     } 
/*  511 */     if (value instanceof Handle) {
/*  512 */       Handle handle = (Handle)value;
/*  513 */       return addConstantMethodHandle(handle
/*  514 */           .getTag(), handle
/*  515 */           .getOwner(), handle
/*  516 */           .getName(), handle
/*  517 */           .getDesc(), handle
/*  518 */           .isInterface());
/*  519 */     }  if (value instanceof ConstantDynamic) {
/*  520 */       ConstantDynamic constantDynamic = (ConstantDynamic)value;
/*  521 */       return addConstantDynamic(constantDynamic
/*  522 */           .getName(), constantDynamic
/*  523 */           .getDescriptor(), constantDynamic
/*  524 */           .getBootstrapMethod(), constantDynamic
/*  525 */           .getBootstrapMethodArgumentsUnsafe());
/*      */     } 
/*  527 */     throw new IllegalArgumentException(stringConcat$0(String.valueOf(value)));
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
/*      */   Symbol addConstantClass(String value) {
/*  539 */     return addConstantUtf8Reference(7, value);
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
/*      */   Symbol addConstantFieldref(String owner, String name, String descriptor) {
/*  552 */     return addConstantMemberReference(9, owner, name, descriptor);
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
/*      */   Symbol addConstantMethodref(String owner, String name, String descriptor, boolean isInterface) {
/*  567 */     int tag = isInterface ? 11 : 10;
/*  568 */     return addConstantMemberReference(tag, owner, name, descriptor);
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
/*      */   private Entry addConstantMemberReference(int tag, String owner, String name, String descriptor) {
/*  585 */     int hashCode = hash(tag, owner, name, descriptor);
/*  586 */     Entry entry = get(hashCode);
/*  587 */     while (entry != null) {
/*  588 */       if (entry.tag == tag && entry.hashCode == hashCode && entry.owner
/*      */         
/*  590 */         .equals(owner) && entry.name
/*  591 */         .equals(name) && entry.value
/*  592 */         .equals(descriptor)) {
/*  593 */         return entry;
/*      */       }
/*  595 */       entry = entry.next;
/*      */     } 
/*  597 */     this.constantPool.put122(tag, 
/*  598 */         (addConstantClass(owner)).index, addConstantNameAndType(name, descriptor));
/*  599 */     return put(new Entry(this.constantPoolCount++, tag, owner, name, descriptor, 0L, hashCode));
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
/*      */   private void addConstantMemberReference(int index, int tag, String owner, String name, String descriptor) {
/*  619 */     add(new Entry(index, tag, owner, name, descriptor, 0L, hash(tag, owner, name, descriptor)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantString(String value) {
/*  630 */     return addConstantUtf8Reference(8, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantInteger(int value) {
/*  641 */     return addConstantIntegerOrFloat(3, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantFloat(float value) {
/*  652 */     return addConstantIntegerOrFloat(4, Float.floatToRawIntBits(value));
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
/*      */   private Symbol addConstantIntegerOrFloat(int tag, int value) {
/*  664 */     int hashCode = hash(tag, value);
/*  665 */     Entry entry = get(hashCode);
/*  666 */     while (entry != null) {
/*  667 */       if (entry.tag == tag && entry.hashCode == hashCode && entry.data == value) {
/*  668 */         return entry;
/*      */       }
/*  670 */       entry = entry.next;
/*      */     } 
/*  672 */     this.constantPool.putByte(tag).putInt(value);
/*  673 */     return put(new Entry(this.constantPoolCount++, tag, value, hashCode));
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
/*      */   private void addConstantIntegerOrFloat(int index, int tag, int value) {
/*  685 */     add(new Entry(index, tag, value, hash(tag, value)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantLong(long value) {
/*  696 */     return addConstantLongOrDouble(5, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantDouble(double value) {
/*  707 */     return addConstantLongOrDouble(6, Double.doubleToRawLongBits(value));
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
/*      */   private Symbol addConstantLongOrDouble(int tag, long value) {
/*  719 */     int hashCode = hash(tag, value);
/*  720 */     Entry entry = get(hashCode);
/*  721 */     while (entry != null) {
/*  722 */       if (entry.tag == tag && entry.hashCode == hashCode && entry.data == value) {
/*  723 */         return entry;
/*      */       }
/*  725 */       entry = entry.next;
/*      */     } 
/*  727 */     int index = this.constantPoolCount;
/*  728 */     this.constantPool.putByte(tag).putLong(value);
/*  729 */     this.constantPoolCount += 2;
/*  730 */     return put(new Entry(index, tag, value, hashCode));
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
/*      */   private void addConstantLongOrDouble(int index, int tag, long value) {
/*  742 */     add(new Entry(index, tag, value, hash(tag, value)));
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
/*      */   int addConstantNameAndType(String name, String descriptor) {
/*  754 */     int tag = 12;
/*  755 */     int hashCode = hash(12, name, descriptor);
/*  756 */     Entry entry = get(hashCode);
/*  757 */     while (entry != null) {
/*  758 */       if (entry.tag == 12 && entry.hashCode == hashCode && entry.name
/*      */         
/*  760 */         .equals(name) && entry.value
/*  761 */         .equals(descriptor)) {
/*  762 */         return entry.index;
/*      */       }
/*  764 */       entry = entry.next;
/*      */     } 
/*  766 */     this.constantPool.put122(12, addConstantUtf8(name), addConstantUtf8(descriptor));
/*  767 */     return (put(new Entry(this.constantPoolCount++, 12, name, descriptor, hashCode))).index;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addConstantNameAndType(int index, String name, String descriptor) {
/*  778 */     int tag = 12;
/*  779 */     add(new Entry(index, 12, name, descriptor, hash(12, name, descriptor)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int addConstantUtf8(String value) {
/*  790 */     int hashCode = hash(1, value);
/*  791 */     Entry entry = get(hashCode);
/*  792 */     while (entry != null) {
/*  793 */       if (entry.tag == 1 && entry.hashCode == hashCode && entry.value
/*      */         
/*  795 */         .equals(value)) {
/*  796 */         return entry.index;
/*      */       }
/*  798 */       entry = entry.next;
/*      */     } 
/*  800 */     this.constantPool.putByte(1).putUTF8(value);
/*  801 */     return (put(new Entry(this.constantPoolCount++, 1, value, hashCode))).index;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addConstantUtf8(int index, String value) {
/*  811 */     add(new Entry(index, 1, value, hash(1, value)));
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
/*      */   Symbol addConstantMethodHandle(int referenceKind, String owner, String name, String descriptor, boolean isInterface) {
/*  834 */     int tag = 15;
/*  835 */     int data = getConstantMethodHandleSymbolData(referenceKind, isInterface);
/*      */ 
/*      */     
/*  838 */     int hashCode = hash(15, owner, name, descriptor, data);
/*  839 */     Entry entry = get(hashCode);
/*  840 */     while (entry != null) {
/*  841 */       if (entry.tag == 15 && entry.hashCode == hashCode && entry.data == data && entry.owner
/*      */ 
/*      */         
/*  844 */         .equals(owner) && entry.name
/*  845 */         .equals(name) && entry.value
/*  846 */         .equals(descriptor)) {
/*  847 */         return entry;
/*      */       }
/*  849 */       entry = entry.next;
/*      */     } 
/*  851 */     if (referenceKind <= 4) {
/*  852 */       this.constantPool.put112(15, referenceKind, (addConstantFieldref(owner, name, descriptor)).index);
/*      */     } else {
/*  854 */       this.constantPool.put112(15, referenceKind, 
/*  855 */           (addConstantMethodref(owner, name, descriptor, isInterface)).index);
/*      */     } 
/*  857 */     return put(new Entry(this.constantPoolCount++, 15, owner, name, descriptor, data, hashCode));
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
/*      */   private void addConstantMethodHandle(int index, int referenceKind, String owner, String name, String descriptor, boolean isInterface) {
/*  880 */     int tag = 15;
/*  881 */     int data = getConstantMethodHandleSymbolData(referenceKind, isInterface);
/*  882 */     int hashCode = hash(15, owner, name, descriptor, data);
/*  883 */     add(new Entry(index, 15, owner, name, descriptor, data, hashCode));
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
/*      */   private static int getConstantMethodHandleSymbolData(int referenceKind, boolean isInterface) {
/*  897 */     if (referenceKind > 4 && isInterface) {
/*  898 */       return referenceKind << 8;
/*      */     }
/*  900 */     return referenceKind;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantMethodType(String methodDescriptor) {
/*  911 */     return addConstantUtf8Reference(16, methodDescriptor);
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
/*      */   Symbol addConstantDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/*  930 */     Symbol bootstrapMethod = addBootstrapMethod(bootstrapMethodHandle, bootstrapMethodArguments);
/*  931 */     return addConstantDynamicOrInvokeDynamicReference(17, name, descriptor, bootstrapMethod.index);
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
/*      */   Symbol addConstantInvokeDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/*  951 */     Symbol bootstrapMethod = addBootstrapMethod(bootstrapMethodHandle, bootstrapMethodArguments);
/*  952 */     return addConstantDynamicOrInvokeDynamicReference(18, name, descriptor, bootstrapMethod.index);
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
/*      */   private Symbol addConstantDynamicOrInvokeDynamicReference(int tag, String name, String descriptor, int bootstrapMethodIndex) {
/*  970 */     int hashCode = hash(tag, name, descriptor, bootstrapMethodIndex);
/*  971 */     Entry entry = get(hashCode);
/*  972 */     while (entry != null) {
/*  973 */       if (entry.tag == tag && entry.hashCode == hashCode && entry.data == bootstrapMethodIndex && entry.name
/*      */ 
/*      */         
/*  976 */         .equals(name) && entry.value
/*  977 */         .equals(descriptor)) {
/*  978 */         return entry;
/*      */       }
/*  980 */       entry = entry.next;
/*      */     } 
/*  982 */     this.constantPool.put122(tag, bootstrapMethodIndex, addConstantNameAndType(name, descriptor));
/*  983 */     return put(new Entry(this.constantPoolCount++, tag, null, name, descriptor, bootstrapMethodIndex, hashCode));
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
/*      */   private void addConstantDynamicOrInvokeDynamicReference(int tag, int index, String name, String descriptor, int bootstrapMethodIndex) {
/* 1006 */     int hashCode = hash(tag, name, descriptor, bootstrapMethodIndex);
/* 1007 */     add(new Entry(index, tag, null, name, descriptor, bootstrapMethodIndex, hashCode));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantModule(String moduleName) {
/* 1018 */     return addConstantUtf8Reference(19, moduleName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Symbol addConstantPackage(String packageName) {
/* 1029 */     return addConstantUtf8Reference(20, packageName);
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
/*      */   private Symbol addConstantUtf8Reference(int tag, String value) {
/* 1045 */     int hashCode = hash(tag, value);
/* 1046 */     Entry entry = get(hashCode);
/* 1047 */     while (entry != null) {
/* 1048 */       if (entry.tag == tag && entry.hashCode == hashCode && entry.value.equals(value)) {
/* 1049 */         return entry;
/*      */       }
/* 1051 */       entry = entry.next;
/*      */     } 
/* 1053 */     this.constantPool.put12(tag, addConstantUtf8(value));
/* 1054 */     return put(new Entry(this.constantPoolCount++, tag, value, hashCode));
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
/*      */   private void addConstantUtf8Reference(int index, int tag, String value) {
/* 1069 */     add(new Entry(index, tag, value, hash(tag, value)));
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
/*      */   Symbol addBootstrapMethod(Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 1086 */     ByteVector bootstrapMethodsAttribute = this.bootstrapMethods;
/* 1087 */     if (bootstrapMethodsAttribute == null) {
/* 1088 */       bootstrapMethodsAttribute = this.bootstrapMethods = new ByteVector();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1095 */     int numBootstrapArguments = bootstrapMethodArguments.length;
/* 1096 */     int[] bootstrapMethodArgumentIndexes = new int[numBootstrapArguments];
/* 1097 */     for (int i = 0; i < numBootstrapArguments; i++) {
/* 1098 */       bootstrapMethodArgumentIndexes[i] = (addConstant(bootstrapMethodArguments[i])).index;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1104 */     int bootstrapMethodOffset = bootstrapMethodsAttribute.length;
/* 1105 */     bootstrapMethodsAttribute.putShort(
/* 1106 */         (addConstantMethodHandle(bootstrapMethodHandle
/* 1107 */           .getTag(), bootstrapMethodHandle
/* 1108 */           .getOwner(), bootstrapMethodHandle
/* 1109 */           .getName(), bootstrapMethodHandle
/* 1110 */           .getDesc(), bootstrapMethodHandle
/* 1111 */           .isInterface())).index);
/*      */ 
/*      */     
/* 1114 */     bootstrapMethodsAttribute.putShort(numBootstrapArguments);
/* 1115 */     for (int j = 0; j < numBootstrapArguments; j++) {
/* 1116 */       bootstrapMethodsAttribute.putShort(bootstrapMethodArgumentIndexes[j]);
/*      */     }
/*      */ 
/*      */     
/* 1120 */     int bootstrapMethodlength = bootstrapMethodsAttribute.length - bootstrapMethodOffset;
/* 1121 */     int hashCode = bootstrapMethodHandle.hashCode();
/* 1122 */     for (Object bootstrapMethodArgument : bootstrapMethodArguments) {
/* 1123 */       hashCode ^= bootstrapMethodArgument.hashCode();
/*      */     }
/* 1125 */     hashCode &= Integer.MAX_VALUE;
/*      */ 
/*      */     
/* 1128 */     return addBootstrapMethod(bootstrapMethodOffset, bootstrapMethodlength, hashCode);
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
/*      */   private Symbol addBootstrapMethod(int offset, int length, int hashCode) {
/* 1142 */     byte[] bootstrapMethodsData = this.bootstrapMethods.data;
/* 1143 */     Entry entry = get(hashCode);
/* 1144 */     while (entry != null) {
/* 1145 */       if (entry.tag == 64 && entry.hashCode == hashCode) {
/* 1146 */         int otherOffset = (int)entry.data;
/* 1147 */         boolean isSameBootstrapMethod = true;
/* 1148 */         for (int i = 0; i < length; i++) {
/* 1149 */           if (bootstrapMethodsData[offset + i] != bootstrapMethodsData[otherOffset + i]) {
/* 1150 */             isSameBootstrapMethod = false;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1154 */         if (isSameBootstrapMethod) {
/* 1155 */           this.bootstrapMethods.length = offset;
/* 1156 */           return entry;
/*      */         } 
/*      */       } 
/* 1159 */       entry = entry.next;
/*      */     } 
/* 1161 */     return put(new Entry(this.bootstrapMethodCount++, 64, offset, hashCode));
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
/*      */   Symbol getType(int typeIndex) {
/* 1175 */     return this.typeTable[typeIndex];
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
/*      */   Label getForwardUninitializedLabel(int typeIndex) {
/* 1187 */     return (this.labelTable[(int)(this.typeTable[typeIndex]).data]).label;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int addType(String value) {
/* 1198 */     int hashCode = hash(128, value);
/* 1199 */     Entry entry = get(hashCode);
/* 1200 */     while (entry != null) {
/* 1201 */       if (entry.tag == 128 && entry.hashCode == hashCode && entry.value.equals(value)) {
/* 1202 */         return entry.index;
/*      */       }
/* 1204 */       entry = entry.next;
/*      */     } 
/* 1206 */     return addTypeInternal(new Entry(this.typeCount, 128, value, hashCode));
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
/*      */   int addUninitializedType(String value, int bytecodeOffset) {
/* 1219 */     int hashCode = hash(129, value, bytecodeOffset);
/* 1220 */     Entry entry = get(hashCode);
/* 1221 */     while (entry != null) {
/* 1222 */       if (entry.tag == 129 && entry.hashCode == hashCode && entry.data == bytecodeOffset && entry.value
/*      */ 
/*      */         
/* 1225 */         .equals(value)) {
/* 1226 */         return entry.index;
/*      */       }
/* 1228 */       entry = entry.next;
/*      */     } 
/* 1230 */     return addTypeInternal(new Entry(this.typeCount, 129, value, bytecodeOffset, hashCode));
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
/*      */   int addForwardUninitializedType(String value, Label label) {
/* 1244 */     int labelIndex = (getOrAddLabelEntry(label)).index;
/* 1245 */     int hashCode = hash(130, value, labelIndex);
/* 1246 */     Entry entry = get(hashCode);
/* 1247 */     while (entry != null) {
/* 1248 */       if (entry.tag == 130 && entry.hashCode == hashCode && entry.data == labelIndex && entry.value
/*      */ 
/*      */         
/* 1251 */         .equals(value)) {
/* 1252 */         return entry.index;
/*      */       }
/* 1254 */       entry = entry.next;
/*      */     } 
/* 1256 */     return addTypeInternal(new Entry(this.typeCount, 130, value, labelIndex, hashCode));
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
/*      */   int addMergedType(int typeTableIndex1, int typeTableIndex2) {
/* 1275 */     long data = (typeTableIndex1 < typeTableIndex2) ? (typeTableIndex1 | typeTableIndex2 << 32L) : (typeTableIndex2 | typeTableIndex1 << 32L);
/* 1276 */     int hashCode = hash(131, typeTableIndex1 + typeTableIndex2);
/* 1277 */     Entry entry = get(hashCode);
/* 1278 */     while (entry != null) {
/* 1279 */       if (entry.tag == 131 && entry.hashCode == hashCode && entry.data == data) {
/* 1280 */         return entry.info;
/*      */       }
/* 1282 */       entry = entry.next;
/*      */     } 
/* 1284 */     String type1 = (this.typeTable[typeTableIndex1]).value;
/* 1285 */     String type2 = (this.typeTable[typeTableIndex2]).value;
/* 1286 */     int commonSuperTypeIndex = addType(this.classWriter.getCommonSuperClass(type1, type2));
/* 1287 */     (put(new Entry(this.typeCount, 131, data, hashCode))).info = commonSuperTypeIndex;
/* 1288 */     return commonSuperTypeIndex;
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
/*      */   private int addTypeInternal(Entry entry) {
/* 1300 */     if (this.typeTable == null) {
/* 1301 */       this.typeTable = new Entry[16];
/*      */     }
/* 1303 */     if (this.typeCount == this.typeTable.length) {
/* 1304 */       Entry[] newTypeTable = new Entry[2 * this.typeTable.length];
/* 1305 */       System.arraycopy(this.typeTable, 0, newTypeTable, 0, this.typeTable.length);
/* 1306 */       this.typeTable = newTypeTable;
/*      */     } 
/* 1308 */     this.typeTable[this.typeCount++] = entry;
/* 1309 */     return (put(entry)).index;
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
/*      */   private LabelEntry getOrAddLabelEntry(Label label) {
/* 1322 */     if (this.labelEntries == null) {
/* 1323 */       this.labelEntries = new LabelEntry[16];
/* 1324 */       this.labelTable = new LabelEntry[16];
/*      */     } 
/* 1326 */     int hashCode = System.identityHashCode(label);
/* 1327 */     LabelEntry labelEntry = this.labelEntries[hashCode % this.labelEntries.length];
/* 1328 */     while (labelEntry != null && labelEntry.label != label) {
/* 1329 */       labelEntry = labelEntry.next;
/*      */     }
/* 1331 */     if (labelEntry != null) {
/* 1332 */       return labelEntry;
/*      */     }
/*      */     
/* 1335 */     if (this.labelCount > this.labelEntries.length * 3 / 4) {
/* 1336 */       int currentCapacity = this.labelEntries.length;
/* 1337 */       int newCapacity = currentCapacity * 2 + 1;
/* 1338 */       LabelEntry[] newLabelEntries = new LabelEntry[newCapacity];
/* 1339 */       for (int i = currentCapacity - 1; i >= 0; i--) {
/* 1340 */         LabelEntry currentEntry = this.labelEntries[i];
/* 1341 */         while (currentEntry != null) {
/* 1342 */           int newCurrentEntryIndex = System.identityHashCode(currentEntry.label) % newCapacity;
/* 1343 */           LabelEntry nextEntry = currentEntry.next;
/* 1344 */           currentEntry.next = newLabelEntries[newCurrentEntryIndex];
/* 1345 */           newLabelEntries[newCurrentEntryIndex] = currentEntry;
/* 1346 */           currentEntry = nextEntry;
/*      */         } 
/*      */       } 
/* 1349 */       this.labelEntries = newLabelEntries;
/*      */     } 
/* 1351 */     if (this.labelCount == this.labelTable.length) {
/* 1352 */       LabelEntry[] newLabelTable = new LabelEntry[2 * this.labelTable.length];
/* 1353 */       System.arraycopy(this.labelTable, 0, newLabelTable, 0, this.labelTable.length);
/* 1354 */       this.labelTable = newLabelTable;
/*      */     } 
/*      */     
/* 1357 */     labelEntry = new LabelEntry(this.labelCount, label);
/* 1358 */     int index = hashCode % this.labelEntries.length;
/* 1359 */     labelEntry.next = this.labelEntries[index];
/* 1360 */     this.labelEntries[index] = labelEntry;
/* 1361 */     this.labelTable[this.labelCount++] = labelEntry;
/* 1362 */     return labelEntry;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int hash(int tag, int value) {
/* 1370 */     return Integer.MAX_VALUE & tag + value;
/*      */   }
/*      */   
/*      */   private static int hash(int tag, long value) {
/* 1374 */     return Integer.MAX_VALUE & tag + (int)value + (int)(value >>> 32L);
/*      */   }
/*      */   
/*      */   private static int hash(int tag, String value) {
/* 1378 */     return Integer.MAX_VALUE & tag + value.hashCode();
/*      */   }
/*      */   
/*      */   private static int hash(int tag, String value1, int value2) {
/* 1382 */     return Integer.MAX_VALUE & tag + value1.hashCode() + value2;
/*      */   }
/*      */   
/*      */   private static int hash(int tag, String value1, String value2) {
/* 1386 */     return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode();
/*      */   }
/*      */ 
/*      */   
/*      */   private static int hash(int tag, String value1, String value2, int value3) {
/* 1391 */     return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode() * (value3 + 1);
/*      */   }
/*      */ 
/*      */   
/*      */   private static int hash(int tag, String value1, String value2, String value3) {
/* 1396 */     return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode() * value3.hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int hash(int tag, String value1, String value2, String value3, int value4) {
/* 1405 */     return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode() * value3.hashCode() * value4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class Entry
/*      */     extends Symbol
/*      */   {
/*      */     final int hashCode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Entry next;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Entry(int index, int tag, String owner, String name, String value, long data, int hashCode) {
/* 1434 */       super(index, tag, owner, name, value, data);
/* 1435 */       this.hashCode = hashCode;
/*      */     }
/*      */     
/*      */     Entry(int index, int tag, String value, int hashCode) {
/* 1439 */       super(index, tag, null, null, value, 0L);
/* 1440 */       this.hashCode = hashCode;
/*      */     }
/*      */     
/*      */     Entry(int index, int tag, String value, long data, int hashCode) {
/* 1444 */       super(index, tag, null, null, value, data);
/* 1445 */       this.hashCode = hashCode;
/*      */     }
/*      */ 
/*      */     
/*      */     Entry(int index, int tag, String name, String value, int hashCode) {
/* 1450 */       super(index, tag, null, name, value, 0L);
/* 1451 */       this.hashCode = hashCode;
/*      */     }
/*      */     
/*      */     Entry(int index, int tag, long data, int hashCode) {
/* 1455 */       super(index, tag, null, null, null, data);
/* 1456 */       this.hashCode = hashCode;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class LabelEntry
/*      */   {
/*      */     final int index;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final Label label;
/*      */ 
/*      */ 
/*      */     
/*      */     LabelEntry next;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     LabelEntry(int index, Label label) {
/* 1481 */       this.index = index;
/* 1482 */       this.label = label;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/SymbolTable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */