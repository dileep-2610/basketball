/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassReader;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class InstrSupport
/*     */ {
/*     */   public static final int ASM_API_VERSION = 589824;
/*     */   public static final String DATAFIELD_NAME = "$jacocoData";
/*     */   public static final int DATAFIELD_ACC = 4234;
/*     */   public static final int DATAFIELD_INTF_ACC = 4121;
/*     */   public static final String DATAFIELD_DESC = "[Z";
/*     */   public static final String INITMETHOD_NAME = "$jacocoInit";
/*     */   public static final String INITMETHOD_DESC = "()[Z";
/*     */   public static final int INITMETHOD_ACC = 4106;
/*     */   static final String CLINIT_NAME = "<clinit>";
/*     */   static final String CLINIT_DESC = "()V";
/*     */   static final int CLINIT_ACC = 4104;
/*     */   
/*     */   public static int getMajorVersion(byte[] b) {
/* 176 */     return (b[6] & 0xFF) << 8 | b[7] & 0xFF;
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
/*     */   public static void setMajorVersion(int majorVersion, byte[] b) {
/* 190 */     b[6] = (byte)(majorVersion >>> 8);
/* 191 */     b[7] = (byte)majorVersion;
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
/*     */   public static int getMajorVersion(ClassReader reader) {
/* 206 */     int firstConstantPoolEntryOffset = reader.getItem(1) - 1;
/* 207 */     return reader.readUnsignedShort(firstConstantPoolEntryOffset - 4);
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
/*     */   public static boolean needsFrames(int version) {
/* 219 */     return ((version & 0xFFFF) >= 50);
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
/*     */   public static void assertNotInstrumented(String member, String owner) throws IllegalStateException {
/* 237 */     if (member.equals("$jacocoData") || member.equals("$jacocoInit")) {
/* 238 */       throw new IllegalStateException(String.format("Cannot process instrumented class %s. Please supply original non-instrumented classes.", new Object[] { owner }));
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
/*     */   public static void push(MethodVisitor mv, int value) {
/* 255 */     if (value >= -1 && value <= 5) {
/* 256 */       mv.visitInsn(3 + value);
/* 257 */     } else if (value >= -128 && value <= 127) {
/* 258 */       mv.visitIntInsn(16, value);
/* 259 */     } else if (value >= -32768 && value <= 32767) {
/* 260 */       mv.visitIntInsn(17, value);
/*     */     } else {
/* 262 */       mv.visitLdcInsn(Integer.valueOf(value));
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
/*     */   public static ClassReader classReaderFor(byte[] b) {
/* 275 */     int originalVersion = getMajorVersion(b);
/* 276 */     if (originalVersion == 69)
/*     */     {
/* 278 */       setMajorVersion(68, b);
/*     */     }
/* 280 */     ClassReader classReader = new ClassReader(b);
/* 281 */     setMajorVersion(originalVersion, b);
/* 282 */     return classReader;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/InstrSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */