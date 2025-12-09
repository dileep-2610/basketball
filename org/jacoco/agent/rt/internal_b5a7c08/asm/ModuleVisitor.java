/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm;
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
/*     */ public abstract class ModuleVisitor
/*     */ {
/*     */   protected final int api;
/*     */   protected ModuleVisitor mv;
/*     */   
/*     */   protected ModuleVisitor(int api) {
/*  57 */     this(api, null);
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
/*     */   protected ModuleVisitor(int api, ModuleVisitor moduleVisitor) {
/*  69 */     if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       throw new IllegalArgumentException(stringConcat$0(api));
/*     */     }
/*  78 */     if (api == 17432576) {
/*  79 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  81 */     this.api = api;
/*  82 */     this.mv = moduleVisitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleVisitor getDelegate() {
/*  92 */     return this.mv;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMainClass(String mainClass) {
/* 102 */     if (this.mv != null) {
/* 103 */       this.mv.visitMainClass(mainClass);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitPackage(String packaze) {
/* 113 */     if (this.mv != null) {
/* 114 */       this.mv.visitPackage(packaze);
/*     */     }
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
/*     */   public void visitRequire(String module, int access, String version) {
/* 127 */     if (this.mv != null) {
/* 128 */       this.mv.visitRequire(module, access, version);
/*     */     }
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
/*     */   public void visitExport(String packaze, int access, String... modules) {
/* 142 */     if (this.mv != null) {
/* 143 */       this.mv.visitExport(packaze, access, modules);
/*     */     }
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
/*     */   public void visitOpen(String packaze, int access, String... modules) {
/* 157 */     if (this.mv != null) {
/* 158 */       this.mv.visitOpen(packaze, access, modules);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitUse(String service) {
/* 169 */     if (this.mv != null) {
/* 170 */       this.mv.visitUse(service);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitProvide(String service, String... providers) {
/* 182 */     if (this.mv != null) {
/* 183 */       this.mv.visitProvide(service, providers);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 192 */     if (this.mv != null)
/* 193 */       this.mv.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/ModuleVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */