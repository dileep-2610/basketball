/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Type
/*     */ {
/*     */   public static final int VOID = 0;
/*     */   public static final int BOOLEAN = 1;
/*     */   public static final int CHAR = 2;
/*     */   public static final int BYTE = 3;
/*     */   public static final int SHORT = 4;
/*     */   public static final int INT = 5;
/*     */   public static final int FLOAT = 6;
/*     */   public static final int LONG = 7;
/*     */   public static final int DOUBLE = 8;
/*     */   public static final int ARRAY = 9;
/*     */   public static final int OBJECT = 10;
/*     */   public static final int METHOD = 11;
/*     */   private static final int INTERNAL = 12;
/*     */   private static final String PRIMITIVE_DESCRIPTORS = "VZCBSIFJD";
/*  85 */   public static final Type VOID_TYPE = new Type(0, "VZCBSIFJD", 0, 1);
/*     */ 
/*     */   
/*  88 */   public static final Type BOOLEAN_TYPE = new Type(1, "VZCBSIFJD", 1, 2);
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static final Type CHAR_TYPE = new Type(2, "VZCBSIFJD", 2, 3);
/*     */ 
/*     */   
/*  95 */   public static final Type BYTE_TYPE = new Type(3, "VZCBSIFJD", 3, 4);
/*     */ 
/*     */   
/*  98 */   public static final Type SHORT_TYPE = new Type(4, "VZCBSIFJD", 4, 5);
/*     */ 
/*     */   
/* 101 */   public static final Type INT_TYPE = new Type(5, "VZCBSIFJD", 5, 6);
/*     */ 
/*     */   
/* 104 */   public static final Type FLOAT_TYPE = new Type(6, "VZCBSIFJD", 6, 7);
/*     */ 
/*     */   
/* 107 */   public static final Type LONG_TYPE = new Type(7, "VZCBSIFJD", 7, 8);
/*     */ 
/*     */   
/* 110 */   public static final Type DOUBLE_TYPE = new Type(8, "VZCBSIFJD", 8, 9);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int sort;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String valueBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int valueBegin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int valueEnd;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Type(int sort, String valueBuffer, int valueBegin, int valueEnd) {
/* 160 */     this.sort = sort;
/* 161 */     this.valueBuffer = valueBuffer;
/* 162 */     this.valueBegin = valueBegin;
/* 163 */     this.valueEnd = valueEnd;
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
/*     */   public static Type getType(String typeDescriptor) {
/* 177 */     return getTypeInternal(typeDescriptor, 0, typeDescriptor.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getType(Class<?> clazz) {
/* 187 */     if (clazz.isPrimitive()) {
/* 188 */       if (clazz == int.class)
/* 189 */         return INT_TYPE; 
/* 190 */       if (clazz == void.class)
/* 191 */         return VOID_TYPE; 
/* 192 */       if (clazz == boolean.class)
/* 193 */         return BOOLEAN_TYPE; 
/* 194 */       if (clazz == byte.class)
/* 195 */         return BYTE_TYPE; 
/* 196 */       if (clazz == char.class)
/* 197 */         return CHAR_TYPE; 
/* 198 */       if (clazz == short.class)
/* 199 */         return SHORT_TYPE; 
/* 200 */       if (clazz == double.class)
/* 201 */         return DOUBLE_TYPE; 
/* 202 */       if (clazz == float.class)
/* 203 */         return FLOAT_TYPE; 
/* 204 */       if (clazz == long.class) {
/* 205 */         return LONG_TYPE;
/*     */       }
/* 207 */       throw new AssertionError();
/*     */     } 
/*     */     
/* 210 */     return getType(getDescriptor(clazz));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getType(Constructor<?> constructor) {
/* 221 */     return getType(getConstructorDescriptor(constructor));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getType(Method method) {
/* 231 */     return getType(getMethodDescriptor(method));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getElementType() {
/* 241 */     int numDimensions = getDimensions();
/* 242 */     return getTypeInternal(this.valueBuffer, this.valueBegin + numDimensions, this.valueEnd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getObjectType(String internalName) {
/* 252 */     return new Type(
/* 253 */         (internalName.charAt(0) == '[') ? 9 : 12, internalName, 0, internalName.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getMethodType(String methodDescriptor) {
/* 264 */     return new Type(11, methodDescriptor, 0, methodDescriptor.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getMethodType(Type returnType, Type... argumentTypes) {
/* 275 */     return getType(getMethodDescriptor(returnType, argumentTypes));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type[] getArgumentTypes() {
/* 285 */     return getArgumentTypes(getDescriptor());
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
/*     */   public static Type[] getArgumentTypes(String methodDescriptor) {
/* 298 */     int numArgumentTypes = getArgumentCount(methodDescriptor);
/*     */ 
/*     */     
/* 301 */     Type[] argumentTypes = new Type[numArgumentTypes];
/*     */     
/* 303 */     int currentOffset = 1;
/*     */     
/* 305 */     int currentArgumentTypeIndex = 0;
/* 306 */     while (methodDescriptor.charAt(currentOffset) != ')') {
/* 307 */       int currentArgumentTypeOffset = currentOffset;
/* 308 */       while (methodDescriptor.charAt(currentOffset) == '[') {
/* 309 */         currentOffset++;
/*     */       }
/* 311 */       if (methodDescriptor.charAt(currentOffset++) == 'L') {
/*     */         
/* 313 */         int semiColumnOffset = methodDescriptor.indexOf(';', currentOffset);
/* 314 */         currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
/*     */       } 
/* 316 */       argumentTypes[currentArgumentTypeIndex++] = 
/* 317 */         getTypeInternal(methodDescriptor, currentArgumentTypeOffset, currentOffset);
/*     */     } 
/* 319 */     return argumentTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type[] getArgumentTypes(Method method) {
/* 329 */     Class<?>[] classes = method.getParameterTypes();
/* 330 */     Type[] types = new Type[classes.length];
/* 331 */     for (int i = classes.length - 1; i >= 0; i--) {
/* 332 */       types[i] = getType(classes[i]);
/*     */     }
/* 334 */     return types;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getReturnType() {
/* 344 */     return getReturnType(getDescriptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getReturnType(String methodDescriptor) {
/* 354 */     return getTypeInternal(methodDescriptor, 
/* 355 */         getReturnTypeOffset(methodDescriptor), methodDescriptor.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type getReturnType(Method method) {
/* 365 */     return getType(method.getReturnType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getReturnTypeOffset(String methodDescriptor) {
/* 376 */     int currentOffset = 1;
/*     */     
/* 378 */     while (methodDescriptor.charAt(currentOffset) != ')') {
/* 379 */       while (methodDescriptor.charAt(currentOffset) == '[') {
/* 380 */         currentOffset++;
/*     */       }
/* 382 */       if (methodDescriptor.charAt(currentOffset++) == 'L') {
/*     */         
/* 384 */         int semiColumnOffset = methodDescriptor.indexOf(';', currentOffset);
/* 385 */         currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
/*     */       } 
/*     */     } 
/* 388 */     return currentOffset + 1;
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
/*     */   private static Type getTypeInternal(String descriptorBuffer, int descriptorBegin, int descriptorEnd) {
/* 403 */     switch (descriptorBuffer.charAt(descriptorBegin)) {
/*     */       case 'V':
/* 405 */         return VOID_TYPE;
/*     */       case 'Z':
/* 407 */         return BOOLEAN_TYPE;
/*     */       case 'C':
/* 409 */         return CHAR_TYPE;
/*     */       case 'B':
/* 411 */         return BYTE_TYPE;
/*     */       case 'S':
/* 413 */         return SHORT_TYPE;
/*     */       case 'I':
/* 415 */         return INT_TYPE;
/*     */       case 'F':
/* 417 */         return FLOAT_TYPE;
/*     */       case 'J':
/* 419 */         return LONG_TYPE;
/*     */       case 'D':
/* 421 */         return DOUBLE_TYPE;
/*     */       case '[':
/* 423 */         return new Type(9, descriptorBuffer, descriptorBegin, descriptorEnd);
/*     */       case 'L':
/* 425 */         return new Type(10, descriptorBuffer, descriptorBegin + 1, descriptorEnd - 1);
/*     */       case '(':
/* 427 */         return new Type(11, descriptorBuffer, descriptorBegin, descriptorEnd);
/*     */     } 
/* 429 */     throw new IllegalArgumentException(stringConcat$0(descriptorBuffer));
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
/*     */   public String getClassName() {
/*     */     StringBuilder stringBuilder;
/*     */     int i;
/* 444 */     switch (this.sort) {
/*     */       case 0:
/* 446 */         return "void";
/*     */       case 1:
/* 448 */         return "boolean";
/*     */       case 2:
/* 450 */         return "char";
/*     */       case 3:
/* 452 */         return "byte";
/*     */       case 4:
/* 454 */         return "short";
/*     */       case 5:
/* 456 */         return "int";
/*     */       case 6:
/* 458 */         return "float";
/*     */       case 7:
/* 460 */         return "long";
/*     */       case 8:
/* 462 */         return "double";
/*     */       case 9:
/* 464 */         stringBuilder = new StringBuilder(getElementType().getClassName());
/* 465 */         for (i = getDimensions(); i > 0; i--) {
/* 466 */           stringBuilder.append("[]");
/*     */         }
/* 468 */         return stringBuilder.toString();
/*     */       case 10:
/*     */       case 12:
/* 471 */         return this.valueBuffer.substring(this.valueBegin, this.valueEnd).replace('/', '.');
/*     */     } 
/* 473 */     throw new AssertionError();
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
/*     */   public String getInternalName() {
/* 485 */     return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getInternalName(Class<?> clazz) {
/* 496 */     return clazz.getName().replace('.', '/');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescriptor() {
/* 505 */     if (this.sort == 10)
/* 506 */       return this.valueBuffer.substring(this.valueBegin - 1, this.valueEnd + 1); 
/* 507 */     if (this.sort == 12) {
/* 508 */       return stringConcat$1(this.valueBuffer.substring(this.valueBegin, this.valueEnd));
/*     */     }
/* 510 */     return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDescriptor(Class<?> clazz) {
/* 521 */     StringBuilder stringBuilder = new StringBuilder();
/* 522 */     appendDescriptor(clazz, stringBuilder);
/* 523 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getConstructorDescriptor(Constructor<?> constructor) {
/* 533 */     StringBuilder stringBuilder = new StringBuilder();
/* 534 */     stringBuilder.append('(');
/* 535 */     Class<?>[] parameters = constructor.getParameterTypes();
/* 536 */     for (Class<?> parameter : parameters) {
/* 537 */       appendDescriptor(parameter, stringBuilder);
/*     */     }
/* 539 */     return stringBuilder.append(")V").toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMethodDescriptor(Type returnType, Type... argumentTypes) {
/* 550 */     StringBuilder stringBuilder = new StringBuilder();
/* 551 */     stringBuilder.append('(');
/* 552 */     for (Type argumentType : argumentTypes) {
/* 553 */       argumentType.appendDescriptor(stringBuilder);
/*     */     }
/* 555 */     stringBuilder.append(')');
/* 556 */     returnType.appendDescriptor(stringBuilder);
/* 557 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMethodDescriptor(Method method) {
/* 567 */     StringBuilder stringBuilder = new StringBuilder();
/* 568 */     stringBuilder.append('(');
/* 569 */     Class<?>[] parameters = method.getParameterTypes();
/* 570 */     for (Class<?> parameter : parameters) {
/* 571 */       appendDescriptor(parameter, stringBuilder);
/*     */     }
/* 573 */     stringBuilder.append(')');
/* 574 */     appendDescriptor(method.getReturnType(), stringBuilder);
/* 575 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendDescriptor(StringBuilder stringBuilder) {
/* 584 */     if (this.sort == 10) {
/* 585 */       stringBuilder.append(this.valueBuffer, this.valueBegin - 1, this.valueEnd + 1);
/* 586 */     } else if (this.sort == 12) {
/* 587 */       stringBuilder.append('L').append(this.valueBuffer, this.valueBegin, this.valueEnd).append(';');
/*     */     } else {
/* 589 */       stringBuilder.append(this.valueBuffer, this.valueBegin, this.valueEnd);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void appendDescriptor(Class<?> clazz, StringBuilder stringBuilder) {
/* 600 */     Class<?> currentClass = clazz;
/* 601 */     while (currentClass.isArray()) {
/* 602 */       stringBuilder.append('[');
/* 603 */       currentClass = currentClass.getComponentType();
/*     */     } 
/* 605 */     if (currentClass.isPrimitive()) {
/*     */       char descriptor;
/* 607 */       if (currentClass == int.class) {
/* 608 */         descriptor = 'I';
/* 609 */       } else if (currentClass == void.class) {
/* 610 */         descriptor = 'V';
/* 611 */       } else if (currentClass == boolean.class) {
/* 612 */         descriptor = 'Z';
/* 613 */       } else if (currentClass == byte.class) {
/* 614 */         descriptor = 'B';
/* 615 */       } else if (currentClass == char.class) {
/* 616 */         descriptor = 'C';
/* 617 */       } else if (currentClass == short.class) {
/* 618 */         descriptor = 'S';
/* 619 */       } else if (currentClass == double.class) {
/* 620 */         descriptor = 'D';
/* 621 */       } else if (currentClass == float.class) {
/* 622 */         descriptor = 'F';
/* 623 */       } else if (currentClass == long.class) {
/* 624 */         descriptor = 'J';
/*     */       } else {
/* 626 */         throw new AssertionError();
/*     */       } 
/* 628 */       stringBuilder.append(descriptor);
/*     */     } else {
/* 630 */       stringBuilder.append('L').append(getInternalName(currentClass)).append(';');
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
/*     */   public int getSort() {
/* 646 */     return (this.sort == 12) ? 10 : this.sort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDimensions() {
/* 656 */     int numDimensions = 1;
/* 657 */     while (this.valueBuffer.charAt(this.valueBegin + numDimensions) == '[') {
/* 658 */       numDimensions++;
/*     */     }
/* 660 */     return numDimensions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 670 */     switch (this.sort) {
/*     */       case 0:
/* 672 */         return 0;
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 9:
/*     */       case 10:
/*     */       case 12:
/* 682 */         return 1;
/*     */       case 7:
/*     */       case 8:
/* 685 */         return 2;
/*     */     } 
/* 687 */     throw new AssertionError();
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
/*     */   public int getArgumentCount() {
/* 699 */     return getArgumentCount(getDescriptor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getArgumentCount(String methodDescriptor) {
/* 710 */     int argumentCount = 0;
/*     */     
/* 712 */     int currentOffset = 1;
/*     */     
/* 714 */     while (methodDescriptor.charAt(currentOffset) != ')') {
/* 715 */       while (methodDescriptor.charAt(currentOffset) == '[') {
/* 716 */         currentOffset++;
/*     */       }
/* 718 */       if (methodDescriptor.charAt(currentOffset++) == 'L') {
/*     */         
/* 720 */         int semiColumnOffset = methodDescriptor.indexOf(';', currentOffset);
/* 721 */         currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
/*     */       } 
/* 723 */       argumentCount++;
/*     */     } 
/* 725 */     return argumentCount;
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
/*     */   public int getArgumentsAndReturnSizes() {
/* 739 */     return getArgumentsAndReturnSizes(getDescriptor());
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
/*     */   public static int getArgumentsAndReturnSizes(String methodDescriptor) {
/* 753 */     int argumentsSize = 1;
/*     */     
/* 755 */     int currentOffset = 1;
/* 756 */     int currentChar = methodDescriptor.charAt(currentOffset);
/*     */     
/* 758 */     while (currentChar != 41) {
/* 759 */       if (currentChar == 74 || currentChar == 68) {
/* 760 */         currentOffset++;
/* 761 */         argumentsSize += 2;
/*     */       } else {
/* 763 */         while (methodDescriptor.charAt(currentOffset) == '[') {
/* 764 */           currentOffset++;
/*     */         }
/* 766 */         if (methodDescriptor.charAt(currentOffset++) == 'L') {
/*     */           
/* 768 */           int semiColumnOffset = methodDescriptor.indexOf(';', currentOffset);
/* 769 */           currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
/*     */         } 
/* 771 */         argumentsSize++;
/*     */       } 
/* 773 */       currentChar = methodDescriptor.charAt(currentOffset);
/*     */     } 
/* 775 */     currentChar = methodDescriptor.charAt(currentOffset + 1);
/* 776 */     if (currentChar == 86) {
/* 777 */       return argumentsSize << 2;
/*     */     }
/* 779 */     int returnSize = (currentChar == 74 || currentChar == 68) ? 2 : 1;
/* 780 */     return argumentsSize << 2 | returnSize;
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
/*     */   public int getOpcode(int opcode) {
/* 796 */     if (opcode == 46 || opcode == 79) {
/* 797 */       switch (this.sort) {
/*     */         case 1:
/*     */         case 3:
/* 800 */           return opcode + 5;
/*     */         case 2:
/* 802 */           return opcode + 6;
/*     */         case 4:
/* 804 */           return opcode + 7;
/*     */         case 5:
/* 806 */           return opcode;
/*     */         case 6:
/* 808 */           return opcode + 2;
/*     */         case 7:
/* 810 */           return opcode + 1;
/*     */         case 8:
/* 812 */           return opcode + 3;
/*     */         case 9:
/*     */         case 10:
/*     */         case 12:
/* 816 */           return opcode + 4;
/*     */         case 0:
/*     */         case 11:
/* 819 */           throw new UnsupportedOperationException();
/*     */       } 
/* 821 */       throw new AssertionError();
/*     */     } 
/*     */     
/* 824 */     switch (this.sort) {
/*     */       case 0:
/* 826 */         if (opcode != 172) {
/* 827 */           throw new UnsupportedOperationException();
/*     */         }
/* 829 */         return 177;
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 835 */         return opcode;
/*     */       case 6:
/* 837 */         return opcode + 2;
/*     */       case 7:
/* 839 */         return opcode + 1;
/*     */       case 8:
/* 841 */         return opcode + 3;
/*     */       case 9:
/*     */       case 10:
/*     */       case 12:
/* 845 */         if (opcode != 21 && opcode != 54 && opcode != 172) {
/* 846 */           throw new UnsupportedOperationException();
/*     */         }
/* 848 */         return opcode + 4;
/*     */       case 11:
/* 850 */         throw new UnsupportedOperationException();
/*     */     } 
/* 852 */     throw new AssertionError();
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
/*     */   public boolean equals(Object object) {
/* 869 */     if (this == object) {
/* 870 */       return true;
/*     */     }
/* 872 */     if (!(object instanceof Type)) {
/* 873 */       return false;
/*     */     }
/* 875 */     Type other = (Type)object;
/* 876 */     if (((this.sort == 12) ? true : this.sort) != ((other.sort == 12) ? true : other.sort)) {
/* 877 */       return false;
/*     */     }
/* 879 */     int begin = this.valueBegin;
/* 880 */     int end = this.valueEnd;
/* 881 */     int otherBegin = other.valueBegin;
/* 882 */     int otherEnd = other.valueEnd;
/*     */     
/* 884 */     if (end - begin != otherEnd - otherBegin) {
/* 885 */       return false;
/*     */     }
/* 887 */     for (int i = begin, j = otherBegin; i < end; i++, j++) {
/* 888 */       if (this.valueBuffer.charAt(i) != other.valueBuffer.charAt(j)) {
/* 889 */         return false;
/*     */       }
/*     */     } 
/* 892 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 902 */     int hashCode = 13 * ((this.sort == 12) ? 10 : this.sort);
/* 903 */     if (this.sort >= 9) {
/* 904 */       for (int i = this.valueBegin, end = this.valueEnd; i < end; i++) {
/* 905 */         hashCode = 17 * (hashCode + this.valueBuffer.charAt(i));
/*     */       }
/*     */     }
/* 908 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 918 */     return getDescriptor();
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/Type.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */