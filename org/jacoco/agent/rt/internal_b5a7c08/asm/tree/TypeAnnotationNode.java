/*    */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*    */ 
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.TypePath;
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
/*    */ public class TypeAnnotationNode
/*    */   extends AnnotationNode
/*    */ {
/*    */   public int typeRef;
/*    */   public TypePath typePath;
/*    */   
/*    */   public TypeAnnotationNode(int typeRef, TypePath typePath, String descriptor) {
/* 62 */     this(589824, typeRef, typePath, descriptor);
/* 63 */     if (getClass() != TypeAnnotationNode.class) {
/* 64 */       throw new IllegalStateException();
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
/*    */ 
/*    */ 
/*    */   
/*    */   public TypeAnnotationNode(int api, int typeRef, TypePath typePath, String descriptor) {
/* 81 */     super(api, descriptor);
/* 82 */     this.typeRef = typeRef;
/* 83 */     this.typePath = typePath;
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/TypeAnnotationNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */