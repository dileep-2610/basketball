/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public class ContentTypeDetector
/*     */ {
/*     */   public static final int UNKNOWN = -1;
/*     */   public static final int CLASSFILE = -889275714;
/*     */   public static final int ZIPFILE = 1347093252;
/*     */   public static final int GZFILE = 529203200;
/*     */   public static final int PACK200FILE = -889270259;
/*     */   private static final int BUFFER_SIZE = 8;
/*     */   private final InputStream in;
/*     */   private final int type;
/*     */   
/*     */   public ContentTypeDetector(InputStream in) throws IOException {
/*  56 */     if (in.markSupported()) {
/*  57 */       this.in = in;
/*     */     } else {
/*  59 */       this.in = new BufferedInputStream(in, 8);
/*     */     } 
/*  61 */     this.in.mark(8);
/*  62 */     this.type = determineType(this.in);
/*  63 */     this.in.reset();
/*     */   }
/*     */   
/*     */   private static int determineType(InputStream in) throws IOException {
/*  67 */     int majorVersion, header = readInt(in);
/*  68 */     switch (header) {
/*     */       case 1347093252:
/*  70 */         return 1347093252;
/*     */       case -889270259:
/*  72 */         return -889270259;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case -889275714:
/*  80 */         majorVersion = readInt(in) & 0xFFFF;
/*  81 */         if (majorVersion >= 45)
/*  82 */           return -889275714; 
/*     */         break;
/*     */     } 
/*  85 */     if ((header & 0xFFFF0000) == 529203200) {
/*  86 */       return 529203200;
/*     */     }
/*  88 */     return -1;
/*     */   }
/*     */   
/*     */   private static int readInt(InputStream in) throws IOException {
/*  92 */     return in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() {
/* 102 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 111 */     return this.type;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/ContentTypeDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */