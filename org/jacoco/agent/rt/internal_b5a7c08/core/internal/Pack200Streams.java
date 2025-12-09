/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.jar.JarInputStream;
/*     */ import java.util.jar.JarOutputStream;
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
/*     */ public final class Pack200Streams
/*     */ {
/*     */   public static InputStream unpack(InputStream input) throws IOException {
/*  42 */     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/*  43 */     JarOutputStream jar = new JarOutputStream(buffer);
/*     */     
/*     */     try {
/*  46 */       Object unpacker = Class.forName("java.util.jar.Pack200").getMethod("newUnpacker", new Class[0]).invoke(null, new Object[0]);
/*  47 */       Class.forName("java.util.jar.Pack200$Unpacker")
/*  48 */         .getMethod("unpack", new Class[] { InputStream.class, JarOutputStream.class
/*     */           
/*  50 */           }).invoke(unpacker, new Object[] { new NoCloseInput(input), jar });
/*  51 */     } catch (ClassNotFoundException e) {
/*  52 */       throw newIOException(e);
/*  53 */     } catch (NoSuchMethodException e) {
/*  54 */       throw newIOException(e);
/*  55 */     } catch (IllegalAccessException e) {
/*  56 */       throw newIOException(e);
/*  57 */     } catch (InvocationTargetException e) {
/*  58 */       throw newIOException(e.getCause());
/*     */     } 
/*  60 */     jar.finish();
/*  61 */     return new ByteArrayInputStream(buffer.toByteArray());
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
/*     */   public static void pack(byte[] source, OutputStream output) throws IOException {
/*  77 */     JarInputStream jar = new JarInputStream(new ByteArrayInputStream(source));
/*     */ 
/*     */     
/*     */     try {
/*  81 */       Object packer = Class.forName("java.util.jar.Pack200").getMethod("newPacker", new Class[0]).invoke(null, new Object[0]);
/*  82 */       Class.forName("java.util.jar.Pack200$Packer")
/*  83 */         .getMethod("pack", new Class[] { JarInputStream.class, OutputStream.class
/*  84 */           }).invoke(packer, new Object[] { jar, output });
/*  85 */     } catch (ClassNotFoundException e) {
/*  86 */       throw newIOException(e);
/*  87 */     } catch (NoSuchMethodException e) {
/*  88 */       throw newIOException(e);
/*  89 */     } catch (IllegalAccessException e) {
/*  90 */       throw newIOException(e);
/*  91 */     } catch (InvocationTargetException e) {
/*  92 */       throw newIOException(e.getCause());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static IOException newIOException(Throwable cause) {
/*  97 */     IOException exception = new IOException();
/*  98 */     exception.initCause(cause);
/*  99 */     return exception;
/*     */   }
/*     */   
/*     */   private static class NoCloseInput extends FilterInputStream {
/*     */     protected NoCloseInput(InputStream in) {
/* 104 */       super(in);
/*     */     }
/*     */     
/*     */     public void close() throws IOException {}
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/Pack200Streams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */