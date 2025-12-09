/*    */ package org.jacoco.agent.rt.internal_b5a7c08;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.data.CRC64;
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
/*    */ class ClassFileDumper
/*    */ {
/*    */   private final File location;
/*    */   
/*    */   ClassFileDumper(String location) {
/* 37 */     if (location == null) {
/* 38 */       this.location = null;
/*    */     } else {
/* 40 */       this.location = new File(location);
/*    */     } 
/*    */   }
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
/*    */   void dump(String name, byte[] contents) throws IOException {
/* 56 */     if (this.location != null) {
/*    */       File outputdir;
/*    */       String localname;
/* 59 */       int pkgpos = name.lastIndexOf('/');
/* 60 */       if (pkgpos != -1) {
/* 61 */         outputdir = new File(this.location, name.substring(0, pkgpos));
/* 62 */         localname = name.substring(pkgpos + 1);
/*    */       } else {
/* 64 */         outputdir = this.location;
/* 65 */         localname = name;
/*    */       } 
/* 67 */       outputdir.mkdirs();
/* 68 */       Long id = Long.valueOf(CRC64.classId(contents));
/*    */       
/* 70 */       File file = new File(outputdir, String.format("%s.%016x.class", new Object[] { localname, id }));
/* 71 */       OutputStream out = new FileOutputStream(file);
/* 72 */       out.write(contents);
/* 73 */       out.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/ClassFileDumper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */