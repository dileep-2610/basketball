/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassWriter;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
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
/*     */ public class InjectedClassRuntime
/*     */   extends AbstractRuntime
/*     */ {
/*     */   private static final String FIELD_NAME = "data";
/*     */   private static final String FIELD_TYPE = "Ljava/lang/Object;";
/*     */   private final Class<?> locator;
/*     */   private final String injectedClassName;
/*     */   
/*     */   public InjectedClassRuntime(Class<?> locator, String simpleClassName) {
/*  46 */     this.locator = locator;
/*  47 */     this.injectedClassName = locator.getPackage().getName().replace('.', '/') + '/' + simpleClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startup(RuntimeData data) throws Exception {
/*  53 */     super.startup(data);
/*     */     
/*  55 */     Lookup.privateLookupIn(this.locator, Lookup.lookup())
/*  56 */       .defineClass(createClass(this.injectedClassName))
/*  57 */       .getField("data")
/*  58 */       .set(null, data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() {}
/*     */ 
/*     */   
/*     */   public int generateDataAccessor(long classid, String classname, int probecount, MethodVisitor mv) {
/*  67 */     mv.visitFieldInsn(178, this.injectedClassName, "data", "Ljava/lang/Object;");
/*     */ 
/*     */     
/*  70 */     RuntimeData.generateAccessCall(classid, classname, probecount, mv);
/*     */     
/*  72 */     return 6;
/*     */   }
/*     */   
/*     */   private static byte[] createClass(String name) {
/*  76 */     ClassWriter cw = new ClassWriter(0);
/*  77 */     cw.visit(53, 4097, name
/*  78 */         .replace('.', '/'), null, "java/lang/Object", null);
/*  79 */     cw.visitField(9, "data", "Ljava/lang/Object;", null, null);
/*     */     
/*  81 */     cw.visitEnd();
/*  82 */     return cw.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class Lookup
/*     */   {
/*     */     private final Object instance;
/*     */ 
/*     */ 
/*     */     
/*     */     private Lookup(Object instance) {
/*  94 */       this.instance = instance;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static Lookup lookup() throws Exception {
/* 101 */       return new Lookup(
/* 102 */           Class.forName("java.lang.invoke.MethodHandles")
/* 103 */           .getMethod("lookup", new Class[0])
/* 104 */           .invoke(null, new Object[0]));
/*     */     }
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
/*     */     static Lookup privateLookupIn(Class<?> targetClass, Lookup lookup) throws Exception {
/* 118 */       return new Lookup(
/* 119 */           Class.forName("java.lang.invoke.MethodHandles")
/* 120 */           .getMethod("privateLookupIn", new Class[] {
/* 121 */               Class.class, Class.forName("java.lang.invoke.MethodHandles$Lookup")
/*     */             
/* 123 */             }).invoke(null, new Object[] { targetClass, lookup.instance }));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Class<?> defineClass(byte[] bytes) throws Exception {
/* 134 */       return 
/* 135 */         (Class)Class.forName("java.lang.invoke.MethodHandles$Lookup")
/* 136 */         .getMethod("defineClass", new Class[] { byte[].class
/* 137 */           }).invoke(this.instance, new Object[] { bytes });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/InjectedClassRuntime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */