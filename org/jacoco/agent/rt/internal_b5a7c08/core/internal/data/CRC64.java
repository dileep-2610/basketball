/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.data;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CRC64
/*    */ {
/*    */   private static final long POLY64REV = -2882303761517117440L;
/* 33 */   private static final long[] LOOKUPTABLE = new long[256]; static {
/* 34 */     for (int i = 0; i < 256; i++) {
/* 35 */       long v = i;
/* 36 */       for (int j = 0; j < 8; j++) {
/* 37 */         if ((v & 0x1L) == 1L) {
/* 38 */           v = v >>> 1L ^ 0xD800000000000000L;
/*    */         } else {
/* 40 */           v >>>= 1L;
/*    */         } 
/*    */       } 
/* 43 */       LOOKUPTABLE[i] = v;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static long update(long sum, byte b) {
/* 57 */     int lookupidx = ((int)sum ^ b) & 0xFF;
/* 58 */     return sum >>> 8L ^ LOOKUPTABLE[lookupidx];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static long update(long sum, byte[] bytes, int fromIndexInclusive, int toIndexExclusive) {
/* 76 */     for (int i = fromIndexInclusive; i < toIndexExclusive; i++) {
/* 77 */       sum = update(sum, bytes[i]);
/*    */     }
/* 79 */     return sum;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long classId(byte[] bytes) {
/* 90 */     if (bytes.length > 7 && bytes[6] == 0 && bytes[7] == 53) {
/*    */ 
/*    */ 
/*    */       
/* 94 */       long sum = update(0L, bytes, 0, 7);
/* 95 */       sum = update(sum, (byte)52);
/* 96 */       return update(sum, bytes, 8, bytes.length);
/*    */     } 
/* 98 */     return update(0L, bytes, 0, bytes.length);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/data/CRC64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */