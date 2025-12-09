/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.data;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ExecutionDataStore
/*     */   implements IExecutionDataVisitor
/*     */ {
/*  32 */   private final Map<Long, ExecutionData> entries = new HashMap<>();
/*     */   
/*  34 */   private final Set<String> names = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(ExecutionData data) throws IllegalStateException {
/*  49 */     Long id = Long.valueOf(data.getId());
/*  50 */     ExecutionData entry = this.entries.get(id);
/*  51 */     if (entry == null) {
/*  52 */       this.entries.put(id, data);
/*  53 */       this.names.add(data.getName());
/*     */     } else {
/*  55 */       entry.merge(data);
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
/*     */   public void subtract(ExecutionData data) throws IllegalStateException {
/*  74 */     Long id = Long.valueOf(data.getId());
/*  75 */     ExecutionData entry = this.entries.get(id);
/*  76 */     if (entry != null) {
/*  77 */       entry.merge(data, false);
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
/*     */   public void subtract(ExecutionDataStore store) {
/*  89 */     for (ExecutionData data : store.getContents()) {
/*  90 */       subtract(data);
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
/*     */   public ExecutionData get(long id) {
/* 103 */     return this.entries.get(Long.valueOf(id));
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
/*     */   public boolean contains(String name) {
/* 116 */     return this.names.contains(name);
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
/*     */   public ExecutionData get(Long id, String name, int probecount) {
/* 133 */     ExecutionData entry = this.entries.get(id);
/* 134 */     if (entry == null) {
/* 135 */       entry = new ExecutionData(id.longValue(), name, probecount);
/* 136 */       this.entries.put(id, entry);
/* 137 */       this.names.add(name);
/*     */     } else {
/* 139 */       entry.assertCompatibility(id.longValue(), name, probecount);
/*     */     } 
/* 141 */     return entry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 149 */     for (ExecutionData executionData : this.entries.values()) {
/* 150 */       executionData.reset();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ExecutionData> getContents() {
/* 160 */     return new ArrayList<>(this.entries.values());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(IExecutionDataVisitor visitor) {
/* 170 */     for (ExecutionData data : getContents()) {
/* 171 */       visitor.visitClassExecution(data);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitClassExecution(ExecutionData data) {
/* 178 */     put(data);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/data/ExecutionDataStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */