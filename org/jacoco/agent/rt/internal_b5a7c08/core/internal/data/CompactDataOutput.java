/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.data;
/*    */ 
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ public class CompactDataOutput
/*    */   extends DataOutputStream
/*    */ {
/*    */   public CompactDataOutput(OutputStream out) {
/* 34 */     super(out);
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
/*    */   public void writeVarInt(int value) throws IOException {
/* 48 */     if ((value & 0xFFFFFF80) == 0) {
/* 49 */       writeByte(value);
/*    */     } else {
/* 51 */       writeByte(0x80 | value & 0x7F);
/* 52 */       writeVarInt(value >>> 7);
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
/*    */   public void writeBooleanArray(boolean[] value) throws IOException {
/* 66 */     writeVarInt(value.length);
/* 67 */     int buffer = 0;
/* 68 */     int bufferSize = 0;
/* 69 */     for (boolean b : value) {
/* 70 */       if (b) {
/* 71 */         buffer |= 1 << bufferSize;
/*    */       }
/* 73 */       if (++bufferSize == 8) {
/* 74 */         writeByte(buffer);
/* 75 */         buffer = 0;
/* 76 */         bufferSize = 0;
/*    */       } 
/*    */     } 
/* 79 */     if (bufferSize > 0)
/* 80 */       writeByte(buffer); 
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/data/CompactDataOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */