/*    */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*    */ 
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocalVariableNode
/*    */ {
/*    */   public String name;
/*    */   public String desc;
/*    */   public String signature;
/*    */   public LabelNode start;
/*    */   public LabelNode end;
/*    */   public int index;
/*    */   
/*    */   public LocalVariableNode(String name, String descriptor, String signature, LabelNode start, LabelNode end, int index) {
/* 75 */     this.name = name;
/* 76 */     this.desc = descriptor;
/* 77 */     this.signature = signature;
/* 78 */     this.start = start;
/* 79 */     this.end = end;
/* 80 */     this.index = index;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 89 */     methodVisitor.visitLocalVariable(this.name, this.desc, this.signature, this.start
/* 90 */         .getLabel(), this.end.getLabel(), this.index);
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/LocalVariableNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */