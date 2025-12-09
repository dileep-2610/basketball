/*    */ package org.jacoco.agent.rt.internal_b5a7c08;
/*    */ 
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.util.concurrent.Callable;
/*    */ import javax.management.MBeanServer;
/*    */ import javax.management.ObjectName;
/*    */ import javax.management.StandardMBean;
/*    */ import org.jacoco.agent.rt.IAgent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class JmxRegistration
/*    */   implements Callable<Void>
/*    */ {
/*    */   private static final String JMX_NAME = "org.jacoco:type=Runtime";
/*    */   private final MBeanServer server;
/*    */   private final ObjectName name;
/*    */   
/*    */   JmxRegistration(IAgent agent) throws Exception {
/* 36 */     this.server = ManagementFactory.getPlatformMBeanServer();
/* 37 */     this.name = new ObjectName("org.jacoco:type=Runtime");
/* 38 */     this.server.registerMBean(new StandardMBean((T)agent, (Class)IAgent.class), this.name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Void call() throws Exception {
/* 45 */     this.server.unregisterMBean(this.name);
/* 46 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/JmxRegistration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */