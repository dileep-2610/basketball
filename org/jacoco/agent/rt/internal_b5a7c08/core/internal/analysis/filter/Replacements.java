/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.analysis.filter;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.AbstractInsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.LabelNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.LookupSwitchInsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.TableSwitchInsnNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Replacements
/*     */ {
/*  34 */   private final LinkedHashMap<AbstractInsnNode, Collection<InstructionBranch>> newBranches = new LinkedHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(AbstractInsnNode target, AbstractInsnNode instruction, int branchIndex) {
/*  93 */     Collection<InstructionBranch> from = this.newBranches.get(target);
/*  94 */     if (from == null) {
/*  95 */       from = new ArrayList<>();
/*  96 */       this.newBranches.put(target, from);
/*     */     } 
/*  98 */     from.add(new InstructionBranch(instruction, branchIndex));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<Collection<InstructionBranch>> values() {
/* 106 */     return this.newBranches.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Replacements ignoreDefaultBranch(AbstractInsnNode switchNode) {
/*     */     List<LabelNode> labels;
/*     */     LabelNode defaultLabel;
/* 118 */     if (switchNode.getOpcode() == 171) {
/* 119 */       LookupSwitchInsnNode s = (LookupSwitchInsnNode)switchNode;
/* 120 */       labels = s.labels;
/* 121 */       defaultLabel = s.dflt;
/*     */     } else {
/* 123 */       TableSwitchInsnNode s = (TableSwitchInsnNode)switchNode;
/* 124 */       labels = s.labels;
/* 125 */       defaultLabel = s.dflt;
/*     */     } 
/* 127 */     Replacements replacements = new Replacements();
/* 128 */     int branchIndex = 0;
/* 129 */     for (LabelNode label : labels) {
/* 130 */       if (label != defaultLabel && replacements.newBranches
/* 131 */         .get(label) == null) {
/* 132 */         branchIndex++;
/* 133 */         replacements.add((AbstractInsnNode)label, switchNode, branchIndex);
/*     */       } 
/*     */     } 
/* 136 */     return replacements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class InstructionBranch
/*     */   {
/*     */     public final AbstractInsnNode instruction;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final int branch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InstructionBranch(AbstractInsnNode instruction, int branch) {
/* 158 */       this.instruction = instruction;
/* 159 */       this.branch = branch;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 164 */       if (o == null || getClass() != o.getClass()) {
/* 165 */         return false;
/*     */       }
/* 167 */       InstructionBranch other = (InstructionBranch)o;
/* 168 */       return (this.instruction.equals(other.instruction) && this.branch == other.branch);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 174 */       return this.instruction.hashCode() * 31 + this.branch;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/analysis/filter/Replacements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */