/*    */ package org.jacoco.agent.rt.internal_b5a7c08;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
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
/*    */ final class ConfigLoader
/*    */ {
/*    */   private static final String SYS_PREFIX = "jacoco-agent.";
/* 33 */   private static final Pattern SUBST_PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");
/*    */   
/*    */   static Properties load(String resource, Properties system) {
/* 36 */     Properties result = new Properties();
/* 37 */     loadResource(resource, result);
/* 38 */     loadSystemProperties(system, result);
/* 39 */     substSystemProperties(result, system);
/* 40 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void loadResource(String resource, Properties result) {
/* 46 */     InputStream file = ConfigLoader.class.getResourceAsStream(resource);
/* 47 */     if (file != null) {
/*    */       try {
/* 49 */         result.load(file);
/* 50 */       } catch (IOException e) {
/* 51 */         throw new RuntimeException(e);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static void loadSystemProperties(Properties system, Properties result) {
/* 58 */     for (Map.Entry<Object, Object> entry : system.entrySet()) {
/* 59 */       String keystr = entry.getKey().toString();
/* 60 */       if (keystr.startsWith("jacoco-agent.")) {
/* 61 */         result.put(keystr.substring("jacoco-agent.".length()), entry
/* 62 */             .getValue());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static void substSystemProperties(Properties result, Properties system) {
/* 69 */     for (Map.Entry<Object, Object> entry : result.entrySet()) {
/* 70 */       String oldValue = (String)entry.getValue();
/* 71 */       StringBuilder newValue = new StringBuilder();
/* 72 */       Matcher m = SUBST_PATTERN.matcher(oldValue);
/* 73 */       int pos = 0;
/* 74 */       while (m.find()) {
/* 75 */         newValue.append(oldValue.substring(pos, m.start()));
/* 76 */         String sub = system.getProperty(m.group(1));
/* 77 */         newValue.append((sub == null) ? m.group(0) : sub);
/* 78 */         pos = m.end();
/*    */       } 
/* 80 */       newValue.append(oldValue.substring(pos));
/* 81 */       entry.setValue(newValue.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/ConfigLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */