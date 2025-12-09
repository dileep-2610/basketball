/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ProbeUpdationEventEmitter;
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
/*    */ public final class JaCoCo
/*    */ {
/*    */   public static final String VERSION;
/*    */   public static final String COMMITID;
/*    */   
/*    */   static {
/* 43 */     ResourceBundle bundle = ResourceBundle.getBundle("org.jacoco.agent.rt.internal_b5a7c08.core.jacoco");
/* 44 */     VERSION = bundle.getString("VERSION");
/* 45 */     COMMITID = bundle.getString("COMMITID");
/* 46 */   } public static final String COMMITID_SHORT = COMMITID.substring(0, 7); static {
/* 47 */     HOMEURL = bundle.getString("HOMEURL");
/* 48 */     RUNTIMEPACKAGE = bundle.getString("RUNTIMEPACKAGE");
/*    */ 
/*    */     
/* 51 */     ProbeUpdationEventEmitter.noopFn();
/*    */   }
/*    */   
/*    */   public static final String HOMEURL;
/*    */   public static final String RUNTIMEPACKAGE;
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/JaCoCo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */