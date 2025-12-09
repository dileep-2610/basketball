/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class InputStreams
/*    */ {
/*    */   public static byte[] readFully(InputStream is) throws IOException {
/* 38 */     byte[] buf = new byte[1024];
/* 39 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*    */     while (true) {
/* 41 */       int r = is.read(buf);
/* 42 */       if (r == -1) {
/*    */         break;
/*    */       }
/* 45 */       out.write(buf, 0, r);
/*    */     } 
/* 47 */     return out.toByteArray();
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/InputStreams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */