/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Opcodes;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.commons.AnalyzerAdapter;
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
/*    */ class FrameSnapshot
/*    */   implements IFrame
/*    */ {
/* 28 */   private static final FrameSnapshot NOP = new FrameSnapshot(null, null);
/*    */   
/*    */   private final Object[] locals;
/*    */   private final Object[] stack;
/*    */   
/*    */   private FrameSnapshot(Object[] locals, Object[] stack) {
/* 34 */     this.locals = locals;
/* 35 */     this.stack = stack;
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
/*    */   static IFrame create(AnalyzerAdapter analyzer, int popCount) {
/* 49 */     if (analyzer == null || analyzer.locals == null) {
/* 50 */       return NOP;
/*    */     }
/* 52 */     Object[] locals = reduce(analyzer.locals, 0);
/* 53 */     Object[] stack = reduce(analyzer.stack, popCount);
/* 54 */     return new FrameSnapshot(locals, stack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Object[] reduce(List<Object> source, int popCount) {
/* 64 */     List<Object> copy = new ArrayList(source);
/* 65 */     int size = source.size() - popCount;
/* 66 */     copy.subList(size, source.size()).clear();
/* 67 */     for (int i = size; --i >= 0; ) {
/* 68 */       Object type = source.get(i);
/* 69 */       if (type == Opcodes.LONG || type == Opcodes.DOUBLE) {
/* 70 */         copy.remove(i + 1);
/*    */       }
/*    */     } 
/* 73 */     return copy.toArray();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor mv) {
/* 79 */     if (this.locals != null)
/* 80 */       mv.visitFrame(-1, this.locals.length, this.locals, this.stack.length, this.stack); 
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/flow/FrameSnapshot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */