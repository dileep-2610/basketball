/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.data;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public final class ExecutionData
/*     */ {
/*     */   private final long id;
/*     */   private final String name;
/*     */   private final boolean[] probes;
/*     */   
/*     */   public ExecutionData(long id, String name, boolean[] probes) {
/*  44 */     this.id = id;
/*  45 */     this.name = name;
/*  46 */     this.probes = probes;
/*  47 */     ProbeArrRefToExecutionDataMap.set(probes, this);
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
/*     */   public ExecutionData(long id, String name, int probeCount) {
/*  63 */     this.id = id;
/*  64 */     this.name = name;
/*  65 */     this.probes = new boolean[probeCount];
/*  66 */     ProbeArrRefToExecutionDataMap.set(this.probes, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getId() {
/*  76 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  85 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] getProbes() {
/*  95 */     return this.probes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 102 */     Arrays.fill(this.probes, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasHits() {
/* 111 */     for (boolean p : this.probes) {
/* 112 */       if (p) {
/* 113 */         return true;
/*     */       }
/*     */     } 
/* 116 */     return false;
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
/*     */   public void merge(ExecutionData other) {
/* 135 */     merge(other, true);
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
/*     */   public void merge(ExecutionData other, boolean flag) {
/* 162 */     assertCompatibility(other.getId(), other.getName(), (other
/* 163 */         .getProbes()).length);
/* 164 */     boolean[] otherData = other.getProbes();
/* 165 */     for (int i = 0; i < this.probes.length; i++) {
/* 166 */       if (otherData[i]) {
/* 167 */         this.probes[i] = flag;
/*     */       }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void assertCompatibility(long id, String name, int probecount) throws IllegalStateException {
/* 188 */     if (this.id != id)
/* 189 */       throw new IllegalStateException(
/* 190 */           String.format("Different ids (%016x and %016x).", new Object[] {
/* 191 */               Long.valueOf(this.id), Long.valueOf(id)
/*     */             })); 
/* 193 */     if (!this.name.equals(name))
/* 194 */       throw new IllegalStateException(
/* 195 */           String.format("Different class names %s and %s for id %016x.", new Object[] {
/* 196 */               this.name, name, Long.valueOf(id)
/*     */             })); 
/* 198 */     if (this.probes.length != probecount) {
/* 199 */       throw new IllegalStateException(String.format("Incompatible execution data for class %s with id %016x.", new Object[] { name, 
/*     */               
/* 201 */               Long.valueOf(id) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 207 */     return String.format("ExecutionData[name=%s, id=%016x]", new Object[] { this.name, 
/* 208 */           Long.valueOf(this.id) });
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/data/ExecutionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */