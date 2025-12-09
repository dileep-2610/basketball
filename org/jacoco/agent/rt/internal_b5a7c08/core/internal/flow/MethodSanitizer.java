/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow;
/*    */ 
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.commons.JSRInlinerAdapter;
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
/*    */ class MethodSanitizer
/*    */   extends JSRInlinerAdapter
/*    */ {
/*    */   MethodSanitizer(MethodVisitor mv, int access, String name, String desc, String signature, String[] exceptions) {
/* 37 */     super(589824, mv, access, name, desc, signature, exceptions);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
/* 48 */     if (start.info != null && end.info != null) {
/* 49 */       super.visitLocalVariable(name, desc, signature, start, end, index);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void visitLineNumber(int line, Label start) {
/* 58 */     if (start.info != null)
/* 59 */       super.visitLineNumber(line, start); 
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/flow/MethodSanitizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */