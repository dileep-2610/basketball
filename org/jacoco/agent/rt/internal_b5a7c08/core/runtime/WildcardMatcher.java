/*    */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WildcardMatcher
/*    */ {
/*    */   private final Pattern pattern;
/*    */   
/*    */   public WildcardMatcher(String expression) {
/* 34 */     String[] parts = expression.split("\\:");
/* 35 */     StringBuilder regex = new StringBuilder(expression.length() * 2);
/* 36 */     boolean next = false;
/* 37 */     for (String part : parts) {
/* 38 */       if (next) {
/* 39 */         regex.append('|');
/*    */       }
/* 41 */       regex.append('(').append(toRegex(part)).append(')');
/* 42 */       next = true;
/*    */     } 
/* 44 */     this.pattern = Pattern.compile(regex.toString());
/*    */   }
/*    */   
/*    */   private static CharSequence toRegex(String expression) {
/* 48 */     StringBuilder regex = new StringBuilder(expression.length() * 2);
/* 49 */     for (char c : expression.toCharArray()) {
/* 50 */       switch (c) {
/*    */         case '?':
/* 52 */           regex.append(".");
/*    */           break;
/*    */         case '*':
/* 55 */           regex.append(".*");
/*    */           break;
/*    */         default:
/* 58 */           regex.append(Pattern.quote(String.valueOf(c)));
/*    */           break;
/*    */       } 
/*    */     } 
/* 62 */     return regex;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matches(String s) {
/* 73 */     return this.pattern.matcher(s).matches();
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/WildcardMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */