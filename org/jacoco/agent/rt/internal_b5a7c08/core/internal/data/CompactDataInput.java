/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.data;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ public class CompactDataInput
/*    */   extends DataInputStream
/*    */ {
/*    */   public CompactDataInput(InputStream in) {
/* 34 */     super(in);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readVarInt() throws IOException {
/* 45 */     int value = 0xFF & readByte();
/* 46 */     if ((value & 0x80) == 0) {
/* 47 */       return value;
/*    */     }
/* 49 */     return value & 0x7F | readVarInt() << 7;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean[] readBooleanArray() throws IOException {
/* 60 */     boolean[] value = new boolean[readVarInt()];
/* 61 */     int buffer = 0;
/* 62 */     for (int i = 0; i < value.length; i++) {
/* 63 */       if (i % 8 == 0) {
/* 64 */         buffer = readByte();
/*    */       }
/* 66 */       value[i] = ((buffer & 0x1) != 0);
/* 67 */       buffer >>>= 1;
/*    */     } 
/* 69 */     return value;
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/data/CompactDataInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */