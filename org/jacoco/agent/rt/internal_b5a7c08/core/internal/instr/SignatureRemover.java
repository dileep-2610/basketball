/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.Manifest;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class SignatureRemover
/*     */ {
/*  30 */   private static final Pattern SIGNATURE_FILES = Pattern.compile("META-INF/[^/]*\\.SF|META-INF/[^/]*\\.DSA|META-INF/[^/]*\\.RSA|META-INF/SIG-[^/]*");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String MANIFEST_MF = "META-INF/MANIFEST.MF";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String DIGEST_SUFFIX = "-Digest";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean active = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActive(boolean active) {
/*  57 */     this.active = active;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeEntry(String name) {
/*  68 */     return (this.active && SIGNATURE_FILES.matcher(name).matches());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean filterEntry(String name, InputStream in, OutputStream out) throws IOException {
/*  86 */     if (!this.active || !"META-INF/MANIFEST.MF".equals(name)) {
/*  87 */       return false;
/*     */     }
/*  89 */     Manifest mf = new Manifest(in);
/*  90 */     filterManifestEntry(mf.getEntries().values());
/*  91 */     mf.write(out);
/*  92 */     return true;
/*     */   }
/*     */   
/*     */   private void filterManifestEntry(Collection<Attributes> entry) {
/*  96 */     for (Iterator<Attributes> i = entry.iterator(); i.hasNext(); ) {
/*  97 */       Attributes attributes = i.next();
/*  98 */       filterManifestEntryAttributes(attributes);
/*  99 */       if (attributes.isEmpty()) {
/* 100 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void filterManifestEntryAttributes(Attributes attrs) {
/* 106 */     Iterator<Object> i = attrs.keySet().iterator();
/* 107 */     while (i.hasNext()) {
/* 108 */       if (String.valueOf(i.next()).endsWith("-Digest"))
/* 109 */         i.remove(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/SignatureRemover.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */