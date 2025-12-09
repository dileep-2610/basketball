/*     */ package org.jacoco.agent.rt.internal_b5a7c08;
/*     */ 
/*     */ import java.lang.instrument.ClassFileTransformer;
/*     */ import java.lang.instrument.IllegalClassFormatException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.CodeSource;
/*     */ import java.security.ProtectionDomain;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.instr.Instrumenter;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.AgentOptions;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.IExecutionDataAccessorGenerator;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.IRuntime;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.WildcardMatcher;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CoverageTransformer
/*     */   implements ClassFileTransformer
/*     */ {
/*     */   private static final String AGENT_PREFIX;
/*     */   private final Instrumenter instrumenter;
/*     */   private final IExceptionLogger logger;
/*     */   private final WildcardMatcher includes;
/*     */   private final WildcardMatcher excludes;
/*     */   private final WildcardMatcher exclClassloader;
/*     */   private final ClassFileDumper classFileDumper;
/*     */   private final boolean inclBootstrapClasses;
/*     */   private final boolean inclNoLocationClasses;
/*     */   
/*     */   static {
/*  34 */     String name = CoverageTransformer.class.getName();
/*  35 */     AGENT_PREFIX = toVMName(name.substring(0, name.lastIndexOf('.')));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CoverageTransformer(IRuntime runtime, AgentOptions options, IExceptionLogger logger) {
/*  66 */     this.instrumenter = new Instrumenter((IExecutionDataAccessorGenerator)runtime);
/*  67 */     this.logger = logger;
/*     */     
/*  69 */     this.includes = new WildcardMatcher(toVMName(options.getIncludes()));
/*  70 */     this.excludes = new WildcardMatcher(toVMName(options.getExcludes()));
/*  71 */     this.exclClassloader = new WildcardMatcher(options.getExclClassloader());
/*  72 */     this.classFileDumper = new ClassFileDumper(options.getClassDumpDir());
/*  73 */     this.inclBootstrapClasses = options.getInclBootstrapClasses();
/*  74 */     this.inclNoLocationClasses = options.getInclNoLocationClasses();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] transform(ClassLoader loader, String classname, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
/*  82 */     ClassLoader platformClassLoader = null;
/*     */     try {
/*  84 */       Class<?> classLoaderClass = Class.forName("java.lang.ClassLoader");
/*     */       
/*  86 */       Method getPlatformClassLoaderMethod = classLoaderClass.getMethod("getPlatformClassLoader", new Class[0]);
/*     */ 
/*     */ 
/*     */       
/*  90 */       platformClassLoader = (ClassLoader)getPlatformClassLoaderMethod.invoke(null, new Object[0]);
/*  91 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*  94 */     if (loader == platformClassLoader) {
/*  95 */       return null;
/*     */     }
/*     */     
/*  98 */     if (classname.contains("HypertestAgent") || classname
/*  99 */       .contains("hypertest/javaagent")) {
/* 100 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 104 */     if (classBeingRedefined != null) {
/* 105 */       return null;
/*     */     }
/*     */     
/* 108 */     if (!filter(loader, classname, protectionDomain)) {
/* 109 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 113 */       this.classFileDumper.dump(classname, classfileBuffer);
/* 114 */       return this.instrumenter.instrument(classfileBuffer, classname);
/* 115 */     } catch (Exception ex) {
/*     */       
/* 117 */       IllegalClassFormatException wrapper = new IllegalClassFormatException(ex.getMessage());
/* 118 */       wrapper.initCause(ex);
/*     */       
/* 120 */       this.logger.logExeption(wrapper);
/* 121 */       throw wrapper;
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
/*     */ 
/*     */ 
/*     */   
/*     */   boolean filter(ClassLoader loader, String classname, ProtectionDomain protectionDomain) {
/* 138 */     if (loader == null) {
/* 139 */       if (!this.inclBootstrapClasses) {
/* 140 */         return false;
/*     */       }
/*     */     } else {
/* 143 */       if (!this.inclNoLocationClasses && 
/* 144 */         !hasSourceLocation(protectionDomain)) {
/* 145 */         return false;
/*     */       }
/* 147 */       if (this.exclClassloader.matches(loader.getClass().getName())) {
/* 148 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 152 */     return (!classname.startsWith(AGENT_PREFIX) && this.includes
/*     */       
/* 154 */       .matches(classname) && 
/*     */       
/* 156 */       !this.excludes.matches(classname));
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
/*     */   private boolean hasSourceLocation(ProtectionDomain protectionDomain) {
/* 168 */     if (protectionDomain == null) {
/* 169 */       return false;
/*     */     }
/* 171 */     CodeSource codeSource = protectionDomain.getCodeSource();
/* 172 */     if (codeSource == null) {
/* 173 */       return false;
/*     */     }
/* 175 */     return (codeSource.getLocation() != null);
/*     */   }
/*     */   
/*     */   private static String toVMName(String srcName) {
/* 179 */     return srcName.replace('.', '/');
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/CoverageTransformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */