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
/*     */ public class Attribute
/*     */ {
/*     */   public final String type;
/*     */   private ByteVector cachedContent;
/*     */   Attribute nextAttribute;
/*     */   
/*     */   protected Attribute(String type) {
/*  65 */     this.type = type;
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
/*     */   public boolean isUnknown() {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCodeAttribute() {
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected Label[] getLabels() {
/* 100 */     return new Label[0];
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
/*     */   protected Attribute read(ClassReader classReader, int offset, int length, char[] charBuffer, int codeAttributeOffset, Label[] labels) {
/* 132 */     Attribute attribute = new Attribute(this.type);
/* 133 */     attribute.cachedContent = new ByteVector(classReader.readBytes(offset, length));
/* 134 */     return attribute;
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
/*     */   public static Attribute read(Attribute attribute, ClassReader classReader, int offset, int length, char[] charBuffer, int codeAttributeOffset, Label[] labels) {
/* 167 */     return attribute.read(classReader, offset, length, charBuffer, codeAttributeOffset, labels);
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
/*     */   public static Label readLabel(ClassReader classReader, int bytecodeOffset, Label[] labels) {
/* 185 */     return classReader.readLabel(bytecodeOffset, labels);
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
/*     */   private ByteVector maybeWrite(ClassWriter classWriter, byte[] code, int codeLength, int maxStack, int maxLocals) {
/* 212 */     if (this.cachedContent == null) {
/* 213 */       this.cachedContent = write(classWriter, code, codeLength, maxStack, maxLocals);
/*     */     }
/* 215 */     return this.cachedContent;
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
/*     */   protected ByteVector write(ClassWriter classWriter, byte[] code, int codeLength, int maxStack, int maxLocals) {
/* 246 */     return this.cachedContent;
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
/*     */   public static byte[] write(Attribute attribute, ClassWriter classWriter, byte[] code, int codeLength, int maxStack, int maxLocals) {
/* 275 */     ByteVector content = attribute.maybeWrite(classWriter, code, codeLength, maxStack, maxLocals);
/* 276 */     byte[] result = new byte[content.length];
/* 277 */     System.arraycopy(content.data, 0, result, 0, content.length);
/* 278 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final int getAttributeCount() {
/* 287 */     int count = 0;
/* 288 */     Attribute attribute = this;
/* 289 */     while (attribute != null) {
/* 290 */       count++;
/* 291 */       attribute = attribute.nextAttribute;
/*     */     } 
/* 293 */     return count;
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
/*     */   final int computeAttributesSize(SymbolTable symbolTable) {
/* 306 */     byte[] code = null;
/* 307 */     int codeLength = 0;
/* 308 */     int maxStack = -1;
/* 309 */     int maxLocals = -1;
/* 310 */     return computeAttributesSize(symbolTable, code, 0, -1, -1);
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
/*     */   final int computeAttributesSize(SymbolTable symbolTable, byte[] code, int codeLength, int maxStack, int maxLocals) {
/* 338 */     ClassWriter classWriter = symbolTable.classWriter;
/* 339 */     int size = 0;
/* 340 */     Attribute attribute = this;
/* 341 */     while (attribute != null) {
/* 342 */       symbolTable.addConstantUtf8(attribute.type);
/* 343 */       size += 6 + (attribute.maybeWrite(classWriter, code, codeLength, maxStack, maxLocals)).length;
/* 344 */       attribute = attribute.nextAttribute;
/*     */     } 
/* 346 */     return size;
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
/*     */   static int computeAttributesSize(SymbolTable symbolTable, int accessFlags, int signatureIndex) {
/* 363 */     int size = 0;
/*     */     
/* 365 */     if ((accessFlags & 0x1000) != 0 && symbolTable
/* 366 */       .getMajorVersion() < 49) {
/*     */       
/* 368 */       symbolTable.addConstantUtf8("Synthetic");
/* 369 */       size += 6;
/*     */     } 
/* 371 */     if (signatureIndex != 0) {
/*     */       
/* 373 */       symbolTable.addConstantUtf8("Signature");
/* 374 */       size += 8;
/*     */     } 
/*     */     
/* 377 */     if ((accessFlags & 0x20000) != 0) {
/*     */       
/* 379 */       symbolTable.addConstantUtf8("Deprecated");
/* 380 */       size += 6;
/*     */     } 
/* 382 */     return size;
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
/*     */   final void putAttributes(SymbolTable symbolTable, ByteVector output) {
/* 394 */     byte[] code = null;
/* 395 */     int codeLength = 0;
/* 396 */     int maxStack = -1;
/* 397 */     int maxLocals = -1;
/* 398 */     putAttributes(symbolTable, code, 0, -1, -1, output);
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
/*     */   final void putAttributes(SymbolTable symbolTable, byte[] code, int codeLength, int maxStack, int maxLocals, ByteVector output) {
/* 426 */     ClassWriter classWriter = symbolTable.classWriter;
/* 427 */     Attribute attribute = this;
/* 428 */     while (attribute != null) {
/*     */       
/* 430 */       ByteVector attributeContent = attribute.maybeWrite(classWriter, code, codeLength, maxStack, maxLocals);
/*     */       
/* 432 */       output.putShort(symbolTable.addConstantUtf8(attribute.type)).putInt(attributeContent.length);
/* 433 */       output.putByteArray(attributeContent.data, 0, attributeContent.length);
/* 434 */       attribute = attribute.nextAttribute;
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
/*     */   static void putAttributes(SymbolTable symbolTable, int accessFlags, int signatureIndex, ByteVector output) {
/* 454 */     if ((accessFlags & 0x1000) != 0 && symbolTable
/* 455 */       .getMajorVersion() < 49) {
/* 456 */       output.putShort(symbolTable.addConstantUtf8("Synthetic")).putInt(0);
/*     */     }
/* 458 */     if (signatureIndex != 0) {
/* 459 */       output
/* 460 */         .putShort(symbolTable.addConstantUtf8("Signature"))
/* 461 */         .putInt(2)
/* 462 */         .putShort(signatureIndex);
/*     */     }
/* 464 */     if ((accessFlags & 0x20000) != 0) {
/* 465 */       output.putShort(symbolTable.addConstantUtf8("Deprecated")).putInt(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static final class Set
/*     */   {
/*     */     private static final int SIZE_INCREMENT = 6;
/*     */     
/*     */     private int size;
/* 475 */     private Attribute[] data = new Attribute[6];
/*     */     
/*     */     void addAttributes(Attribute attributeList) {
/* 478 */       Attribute attribute = attributeList;
/* 479 */       while (attribute != null) {
/* 480 */         if (!contains(attribute)) {
/* 481 */           add(attribute);
/*     */         }
/* 483 */         attribute = attribute.nextAttribute;
/*     */       } 
/*     */     }
/*     */     
/*     */     Attribute[] toArray() {
/* 488 */       Attribute[] result = new Attribute[this.size];
/* 489 */       System.arraycopy(this.data, 0, result, 0, this.size);
/* 490 */       return result;
/*     */     }
/*     */     
/*     */     private boolean contains(Attribute attribute) {
/* 494 */       for (int i = 0; i < this.size; i++) {
/* 495 */         if ((this.data[i]).type.equals(attribute.type)) {
/* 496 */           return true;
/*     */         }
/*     */       } 
/* 499 */       return false;
/*     */     }
/*     */     
/*     */     private void add(Attribute attribute) {
/* 503 */       if (this.size >= this.data.length) {
/* 504 */         Attribute[] newData = new Attribute[this.data.length + 6];
/* 505 */         System.arraycopy(this.data, 0, newData, 0, this.size);
/* 506 */         this.data = newData;
/*     */       } 
/* 508 */       this.data[this.size++] = attribute;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/Attribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */