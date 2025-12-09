/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.analysis;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.analysis.ICounter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CounterImpl
/*     */   implements ICounter
/*     */ {
/*     */   private static final int SINGLETON_LIMIT = 30;
/*  26 */   private static final CounterImpl[][] SINGLETONS = new CounterImpl[31][];
/*     */ 
/*     */   
/*     */   static {
/*  30 */     for (int i = 0; i <= 30; i++) {
/*  31 */       SINGLETONS[i] = new CounterImpl[31];
/*  32 */       for (int j = 0; j <= 30; j++) {
/*  33 */         SINGLETONS[i][j] = new Fix(i, j);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  39 */   public static final CounterImpl COUNTER_0_0 = SINGLETONS[0][0];
/*     */ 
/*     */   
/*  42 */   public static final CounterImpl COUNTER_1_0 = SINGLETONS[1][0];
/*     */ 
/*     */   
/*  45 */   public static final CounterImpl COUNTER_0_1 = SINGLETONS[0][1];
/*     */   protected int missed;
/*     */   protected int covered;
/*     */   
/*     */   private static class Var
/*     */     extends CounterImpl {
/*     */     public Var(int missed, int covered) {
/*  52 */       super(missed, covered);
/*     */     }
/*     */ 
/*     */     
/*     */     public CounterImpl increment(int missed, int covered) {
/*  57 */       this.missed += missed;
/*  58 */       this.covered += covered;
/*  59 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class Fix
/*     */     extends CounterImpl
/*     */   {
/*     */     public Fix(int missed, int covered) {
/*  68 */       super(missed, covered);
/*     */     }
/*     */ 
/*     */     
/*     */     public CounterImpl increment(int missed, int covered) {
/*  73 */       return getInstance(this.missed + missed, this.covered + covered);
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
/*     */   public static CounterImpl getInstance(int missed, int covered) {
/*  87 */     if (missed <= 30 && covered <= 30) {
/*  88 */       return SINGLETONS[missed][covered];
/*     */     }
/*  90 */     return new Var(missed, covered);
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
/*     */   public static CounterImpl getInstance(ICounter counter) {
/* 102 */     return getInstance(counter.getMissedCount(), counter.getCoveredCount());
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
/*     */   protected CounterImpl(int missed, int covered) {
/* 120 */     this.missed = missed;
/* 121 */     this.covered = covered;
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
/*     */   public CounterImpl increment(ICounter counter) {
/* 134 */     return increment(counter.getMissedCount(), counter.getCoveredCount());
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
/*     */   public double getValue(ICounter.CounterValue value) {
/* 153 */     switch (value) {
/*     */       case TOTALCOUNT:
/* 155 */         return getTotalCount();
/*     */       case MISSEDCOUNT:
/* 157 */         return getMissedCount();
/*     */       case COVEREDCOUNT:
/* 159 */         return getCoveredCount();
/*     */       case MISSEDRATIO:
/* 161 */         return getMissedRatio();
/*     */       case COVEREDRATIO:
/* 163 */         return getCoveredRatio();
/*     */     } 
/* 165 */     throw new AssertionError(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTotalCount() {
/* 170 */     return this.missed + this.covered;
/*     */   }
/*     */   
/*     */   public int getCoveredCount() {
/* 174 */     return this.covered;
/*     */   }
/*     */   
/*     */   public int getMissedCount() {
/* 178 */     return this.missed;
/*     */   }
/*     */   
/*     */   public double getCoveredRatio() {
/* 182 */     return this.covered / (this.missed + this.covered);
/*     */   }
/*     */   
/*     */   public double getMissedRatio() {
/* 186 */     return this.missed / (this.missed + this.covered);
/*     */   }
/*     */   
/*     */   public int getStatus() {
/* 190 */     int status = (this.covered > 0) ? 2 : 0;
/* 191 */     if (this.missed > 0) {
/* 192 */       status |= 0x1;
/*     */     }
/* 194 */     return status;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 199 */     if (obj instanceof ICounter) {
/* 200 */       ICounter that = (ICounter)obj;
/* 201 */       return (this.missed == that.getMissedCount() && this.covered == that
/* 202 */         .getCoveredCount());
/*     */     } 
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 210 */     return this.missed ^ this.covered * 17;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 215 */     StringBuilder b = new StringBuilder("Counter[");
/* 216 */     b.append(getMissedCount());
/* 217 */     b.append('/').append(getCoveredCount());
/* 218 */     b.append(']');
/* 219 */     return b.toString();
/*     */   }
/*     */   
/*     */   public abstract CounterImpl increment(int paramInt1, int paramInt2);
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/analysis/CounterImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */