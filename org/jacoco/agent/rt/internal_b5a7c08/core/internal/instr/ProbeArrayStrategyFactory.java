/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*    */ 
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassReader;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassVisitor;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow.ClassProbesAdapter;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.IExecutionDataAccessorGenerator;
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
/*    */ public final class ProbeArrayStrategyFactory
/*    */ {
/*    */   public static IProbeArrayStrategy createFor(long classId, ClassReader reader, IExecutionDataAccessorGenerator accessorGenerator) {
/* 46 */     String className = reader.getClassName();
/* 47 */     int version = InstrSupport.getMajorVersion(reader);
/*    */     
/* 49 */     if (isInterfaceOrModule(reader)) {
/* 50 */       ProbeCounter counter = getProbeCounter(reader);
/* 51 */       if (counter.getCount() == 0) {
/* 52 */         return new NoneProbeArrayStrategy();
/*    */       }
/* 54 */       if (version >= 55 && counter.hasMethods()) {
/* 55 */         return new CondyProbeArrayStrategy(className, true, classId, accessorGenerator);
/*    */       }
/*    */       
/* 58 */       if (version >= 52 && counter.hasMethods()) {
/* 59 */         return new InterfaceFieldProbeArrayStrategy(className, classId, counter
/* 60 */             .getCount(), accessorGenerator);
/*    */       }
/* 62 */       return new LocalProbeArrayStrategy(className, classId, counter
/* 63 */           .getCount(), accessorGenerator);
/*    */     } 
/*    */     
/* 66 */     if (version >= 55) {
/* 67 */       return new CondyProbeArrayStrategy(className, false, classId, accessorGenerator);
/*    */     }
/*    */     
/* 70 */     return new ClassFieldProbeArrayStrategy(className, classId, 
/* 71 */         InstrSupport.needsFrames(version), accessorGenerator);
/*    */   }
/*    */ 
/*    */   
/*    */   private static boolean isInterfaceOrModule(ClassReader reader) {
/* 76 */     return ((reader.getAccess() & 0x8200) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   private static ProbeCounter getProbeCounter(ClassReader reader) {
/* 81 */     ProbeCounter counter = new ProbeCounter();
/* 82 */     reader.accept((ClassVisitor)new ClassProbesAdapter(counter, false), 0);
/* 83 */     return counter;
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/ProbeArrayStrategyFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */