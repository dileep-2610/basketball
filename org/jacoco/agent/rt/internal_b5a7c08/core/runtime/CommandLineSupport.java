/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class CommandLineSupport
/*     */ {
/*     */   private static final char BLANK = ' ';
/*     */   private static final char QUOTE = '"';
/*     */   private static final char SLASH = '\\';
/*     */   private static final int M_STRIP_WHITESPACE = 0;
/*     */   private static final int M_PARSE_ARGUMENT = 1;
/*     */   private static final int M_ESCAPED = 2;
/*     */   
/*     */   static String quote(String arg) {
/*  35 */     StringBuilder escaped = new StringBuilder();
/*  36 */     for (char c : arg.toCharArray()) {
/*  37 */       if (c == '"' || c == '\\') {
/*  38 */         escaped.append('\\');
/*     */       }
/*  40 */       escaped.append(c);
/*     */     } 
/*  42 */     if (arg.indexOf(' ') != -1 || arg.indexOf('"') != -1) {
/*  43 */       escaped.insert(0, '"').append('"');
/*     */     }
/*  45 */     return escaped.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String quote(List<String> args) {
/*  57 */     StringBuilder result = new StringBuilder();
/*  58 */     boolean separate = false;
/*  59 */     for (String arg : args) {
/*  60 */       if (separate) {
/*  61 */         result.append(' ');
/*     */       }
/*  63 */       result.append(quote(arg));
/*  64 */       separate = true;
/*     */     } 
/*  66 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List<String> split(String commandline) {
/*  78 */     if (commandline == null || commandline.length() == 0) {
/*  79 */       return new ArrayList<>();
/*     */     }
/*  81 */     List<String> args = new ArrayList<>();
/*  82 */     StringBuilder current = new StringBuilder();
/*  83 */     int mode = 0;
/*  84 */     int endChar = 32;
/*  85 */     for (char c : commandline.toCharArray()) {
/*  86 */       switch (mode) {
/*     */         case 0:
/*  88 */           if (!Character.isWhitespace(c)) {
/*  89 */             if (c == '"') {
/*  90 */               endChar = 34;
/*     */             } else {
/*  92 */               current.append(c);
/*  93 */               endChar = 32;
/*     */             } 
/*  95 */             mode = 1;
/*     */           } 
/*     */           break;
/*     */         case 1:
/*  99 */           if (c == endChar) {
/* 100 */             addArgument(args, current);
/* 101 */             mode = 0; break;
/* 102 */           }  if (c == '\\') {
/* 103 */             current.append('\\');
/* 104 */             mode = 2; break;
/*     */           } 
/* 106 */           current.append(c);
/*     */           break;
/*     */         
/*     */         case 2:
/* 110 */           if (c == '"' || c == '\\') {
/* 111 */             current.setCharAt(current.length() - 1, c);
/* 112 */           } else if (c == endChar) {
/* 113 */             addArgument(args, current);
/* 114 */             mode = 0;
/*     */           } else {
/* 116 */             current.append(c);
/*     */           } 
/* 118 */           mode = 1;
/*     */           break;
/*     */       } 
/*     */     } 
/* 122 */     addArgument(args, current);
/* 123 */     return args;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addArgument(List<String> args, StringBuilder current) {
/* 128 */     if (current.length() > 0) {
/* 129 */       args.add(current.toString());
/* 130 */       current.setLength(0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/CommandLineSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */