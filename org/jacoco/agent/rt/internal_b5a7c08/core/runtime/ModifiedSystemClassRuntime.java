/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.runtime;
/*     */ 
/*     */ import java.lang.instrument.ClassFileTransformer;
/*     */ import java.lang.instrument.IllegalClassFormatException;
/*     */ import java.lang.instrument.Instrumentation;
/*     */ import java.lang.reflect.Field;
/*     */ import java.security.ProtectionDomain;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassReader;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ClassWriter;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr.InstrSupport;
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
/*     */ public class ModifiedSystemClassRuntime
/*     */   extends AbstractRuntime
/*     */ {
/*     */   private static final String ACCESS_FIELD_TYPE = "Ljava/lang/Object;";
/*     */   private final Class<?> systemClass;
/*     */   private final String systemClassName;
/*     */   private final String accessFieldName;
/*     */   
/*     */   public ModifiedSystemClassRuntime(Class<?> systemClass, String accessFieldName) {
/*  58 */     this.systemClass = systemClass;
/*  59 */     this.systemClassName = systemClass.getName().replace('.', '/');
/*  60 */     this.accessFieldName = accessFieldName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startup(RuntimeData data) throws Exception {
/*  65 */     super.startup(data);
/*  66 */     Field field = this.systemClass.getField(this.accessFieldName);
/*  67 */     field.set(null, data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int generateDataAccessor(long classid, String classname, int probecount, MethodVisitor mv) {
/*  77 */     mv.visitFieldInsn(178, this.systemClassName, this.accessFieldName, "Ljava/lang/Object;");
/*     */ 
/*     */     
/*  80 */     RuntimeData.generateAccessCall(classid, classname, probecount, mv);
/*     */     
/*  82 */     return 6;
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
/*     */   
/*     */   public static IRuntime createFor(Instrumentation inst, String className) throws ClassNotFoundException {
/* 101 */     return createFor(inst, className, "$jacocoAccess");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IRuntime createFor(Instrumentation inst, final String className, final String accessFieldName) throws ClassNotFoundException {
/* 123 */     ClassFileTransformer transformer = new ClassFileTransformer()
/*     */       {
/*     */         
/*     */         public byte[] transform(ClassLoader loader, String name, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] source) throws IllegalClassFormatException
/*     */         {
/* 128 */           if (name.equals(className)) {
/* 129 */             return ModifiedSystemClassRuntime.instrument(source, accessFieldName);
/*     */           }
/* 131 */           return null;
/*     */         }
/*     */       };
/* 134 */     inst.addTransformer(transformer);
/* 135 */     Class<?> clazz = Class.forName(className.replace('/', '.'));
/* 136 */     inst.removeTransformer(transformer);
/*     */     try {
/* 138 */       clazz.getField(accessFieldName);
/* 139 */     } catch (NoSuchFieldException e) {
/* 140 */       throw new RuntimeException(
/* 141 */           String.format("Class %s could not be instrumented.", new Object[] { className }), e);
/*     */     } 
/*     */     
/* 144 */     return new ModifiedSystemClassRuntime(clazz, accessFieldName);
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
/*     */   public static byte[] instrument(byte[] source, final String accessFieldName) {
/* 158 */     ClassReader reader = InstrSupport.classReaderFor(source);
/* 159 */     ClassWriter writer = new ClassWriter(reader, 0);
/* 160 */     reader.accept(new ClassVisitor(589824, (ClassVisitor)writer)
/*     */         {
/*     */           public void visitEnd()
/*     */           {
/* 164 */             ModifiedSystemClassRuntime.createDataField(this.cv, accessFieldName);
/* 165 */             super.visitEnd();
/*     */           }
/*     */         }8);
/*     */     
/* 169 */     return writer.toByteArray();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void createDataField(ClassVisitor visitor, String dataField) {
/* 174 */     visitor.visitField(4233, dataField, "Ljava/lang/Object;", null, null);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/runtime/ModifiedSystemClassRuntime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */