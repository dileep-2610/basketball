/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.analysis;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.AbstractInsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.analysis.ICounter;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.analysis.filter.Replacements;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Instruction
/*     */ {
/*     */   private final int line;
/*     */   private int branches;
/*     */   private final BitSet coveredBranches;
/*     */   private Instruction predecessor;
/*     */   private int predecessorBranch;
/*     */   private int probeId;
/*     */   
/*     */   public Instruction(int line) {
/*  82 */     this.line = line;
/*  83 */     this.branches = 0;
/*  84 */     this.coveredBranches = new BitSet();
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
/*     */   public void addBranch(Instruction target, int branch) {
/* 102 */     this.branches++;
/* 103 */     target.predecessor = this;
/* 104 */     target.predecessorBranch = branch;
/* 105 */     if (!target.coveredBranches.isEmpty()) {
/* 106 */       propagateExecutedBranch(this, branch);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBranch(Instruction target, int branch, int probeId, HashMap<Integer, Set<Integer>> probesToLineNumbers) {
/* 112 */     this.branches++;
/* 113 */     target.predecessor = this;
/* 114 */     target.predecessorBranch = branch;
/* 115 */     propagateProbeId(this, probeId, probesToLineNumbers);
/* 116 */     if (!target.coveredBranches.isEmpty()) {
/* 117 */       propagateExecutedBranch(this, branch);
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
/*     */   public void addBranch(boolean executed, int branch) {
/* 135 */     this.branches++;
/* 136 */     if (executed) {
/* 137 */       propagateExecutedBranch(this, branch);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBranch(boolean executed, int branch, int probeId, HashMap<Integer, Set<Integer>> probesToLineNumbers) {
/* 143 */     this.branches++;
/* 144 */     propagateProbeId(this, probeId, probesToLineNumbers);
/* 145 */     if (executed) {
/* 146 */       propagateExecutedBranch(this, branch);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void propagateProbeId(Instruction insn, int probeId, HashMap<Integer, Set<Integer>> probesToLineNumbers) {
/* 153 */     Set<Integer> lineNumbers = probesToLineNumbers.get(Integer.valueOf(probeId));
/* 154 */     if (lineNumbers == null) {
/* 155 */       lineNumbers = new TreeSet<>();
/* 156 */       probesToLineNumbers.put(Integer.valueOf(probeId), lineNumbers);
/*     */     } 
/*     */     
/* 159 */     while (insn != null && 
/* 160 */       insn.coveredBranches.isEmpty()) {
/*     */ 
/*     */ 
/*     */       
/* 164 */       lineNumbers.add(Integer.valueOf(insn.getLine()));
/* 165 */       insn.setProbeId(probeId);
/* 166 */       insn = insn.predecessor;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void propagateExecutedBranch(Instruction insn, int branch) {
/* 172 */     while (insn != null) {
/* 173 */       if (!insn.coveredBranches.isEmpty()) {
/* 174 */         insn.coveredBranches.set(branch);
/*     */         break;
/*     */       } 
/* 177 */       insn.coveredBranches.set(branch);
/* 178 */       branch = insn.predecessorBranch;
/* 179 */       insn = insn.predecessor;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLine() {
/* 189 */     return this.line;
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
/*     */   public Instruction merge(Instruction other) {
/* 201 */     Instruction result = new Instruction(this.line);
/* 202 */     result.branches = this.branches;
/* 203 */     result.coveredBranches.or(this.coveredBranches);
/* 204 */     result.coveredBranches.or(other.coveredBranches);
/* 205 */     return result;
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
/*     */   public Instruction replaceBranches(Replacements replacements, Mapper mapper) {
/* 223 */     Instruction result = new Instruction(this.line);
/* 224 */     int branchIndex = 0;
/* 225 */     for (Collection<Replacements.InstructionBranch> newBranch : (Iterable<Collection<Replacements.InstructionBranch>>)replacements
/* 226 */       .values()) {
/* 227 */       for (Replacements.InstructionBranch from : newBranch) {
/* 228 */         if ((mapper.apply(from.instruction)).coveredBranches
/* 229 */           .get(from.branch)) {
/* 230 */           result.coveredBranches.set(branchIndex);
/*     */         }
/*     */       } 
/* 233 */       branchIndex++;
/*     */     } 
/* 235 */     result.branches = branchIndex;
/* 236 */     return result;
/*     */   }
/*     */   
/*     */   public int getProbeId() {
/* 240 */     return this.probeId;
/*     */   }
/*     */   
/*     */   public void setProbeId(int probeId) {
/* 244 */     this.probeId = probeId;
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
/*     */   public ICounter getInstructionCounter() {
/* 261 */     return this.coveredBranches.isEmpty() ? CounterImpl.COUNTER_1_0 : 
/* 262 */       CounterImpl.COUNTER_0_1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ICounter getBranchCounter() {
/* 272 */     if (this.branches < 2) {
/* 273 */       return CounterImpl.COUNTER_0_0;
/*     */     }
/* 275 */     int covered = this.coveredBranches.cardinality();
/* 276 */     return CounterImpl.getInstance(this.branches - covered, covered);
/*     */   }
/*     */   
/*     */   static interface Mapper {
/*     */     Instruction apply(AbstractInsnNode param1AbstractInsnNode);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/analysis/Instruction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */