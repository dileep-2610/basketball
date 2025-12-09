/*    */ package org.jacoco.agent.rt.internal_b5a7c08;
/*    */ 
/*    */ import java.lang.instrument.Instrumentation;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.AgentOptions;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.IRuntime;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.InjectedClassRuntime;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.ModifiedSystemClassRuntime;
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
/*    */ public final class PreMain
/*    */ {
/*    */   public static void premain(String options, Instrumentation inst) throws Exception {
/* 45 */     AgentOptions agentOptions = new AgentOptions(options);
/*    */     
/* 47 */     Agent agent = Agent.getInstance(agentOptions);
/*    */     
/* 49 */     IRuntime runtime = createRuntime(inst);
/* 50 */     runtime.startup(agent.getData());
/* 51 */     inst.addTransformer(new CoverageTransformer(runtime, agentOptions, IExceptionLogger.SYSTEM_ERR));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static IRuntime createRuntime(Instrumentation inst) throws Exception {
/* 58 */     if (AgentModule.isSupported()) {
/* 59 */       AgentModule module = new AgentModule();
/* 60 */       module.openPackage(inst, Object.class);
/*    */       
/* 62 */       Class<InjectedClassRuntime> clazz = module.loadClassInModule(InjectedClassRuntime.class);
/* 63 */       return clazz.getConstructor(new Class[] { Class.class, String.class
/* 64 */           }).newInstance(new Object[] { Object.class, "$JaCoCo" });
/*    */     } 
/*    */     
/* 67 */     return ModifiedSystemClassRuntime.createFor(inst, "java/lang/UnknownError");
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/PreMain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */