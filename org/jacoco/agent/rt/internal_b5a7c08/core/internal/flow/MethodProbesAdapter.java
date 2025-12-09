/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.commons.AnalyzerAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MethodProbesAdapter
/*     */   extends MethodVisitor
/*     */ {
/*     */   private final MethodProbesVisitor probesVisitor;
/*     */   private final IProbeIdGenerator idGenerator;
/*     */   private AnalyzerAdapter analyzer;
/*     */   private final Map<Label, Label> tryCatchProbeLabels;
/*     */   
/*     */   public MethodProbesAdapter(MethodProbesVisitor probesVisitor, IProbeIdGenerator idGenerator) {
/*  48 */     super(589824, probesVisitor);
/*  49 */     this.probesVisitor = probesVisitor;
/*  50 */     this.idGenerator = idGenerator;
/*  51 */     this.tryCatchProbeLabels = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnalyzer(AnalyzerAdapter analyzer) {
/*  62 */     this.analyzer = analyzer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/*  68 */     this.probesVisitor.visitTryCatchBlock(getTryCatchLabel(start), 
/*  69 */         getTryCatchLabel(end), handler, type);
/*     */   }
/*     */   
/*     */   private Label getTryCatchLabel(Label label) {
/*  73 */     if (this.tryCatchProbeLabels.containsKey(label)) {
/*  74 */       label = this.tryCatchProbeLabels.get(label);
/*  75 */     } else if (LabelInfo.needsProbe(label)) {
/*     */ 
/*     */       
/*  78 */       Label probeLabel = new Label();
/*  79 */       LabelInfo.setSuccessor(probeLabel);
/*  80 */       this.tryCatchProbeLabels.put(label, probeLabel);
/*  81 */       label = probeLabel;
/*     */     } 
/*  83 */     return label;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/*  88 */     if (LabelInfo.needsProbe(label)) {
/*  89 */       if (this.tryCatchProbeLabels.containsKey(label)) {
/*  90 */         this.probesVisitor.visitLabel(this.tryCatchProbeLabels.get(label));
/*     */       }
/*  92 */       this.probesVisitor.visitProbe(this.idGenerator.nextId());
/*     */     } 
/*  94 */     this.probesVisitor.visitLabel(label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/*  99 */     switch (opcode) {
/*     */       case 172:
/*     */       case 173:
/*     */       case 174:
/*     */       case 175:
/*     */       case 176:
/*     */       case 177:
/*     */       case 191:
/* 107 */         this.probesVisitor.visitInsnWithProbe(opcode, this.idGenerator.nextId());
/*     */         return;
/*     */     } 
/* 110 */     this.probesVisitor.visitInsn(opcode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 117 */     if (LabelInfo.isMultiTarget(label)) {
/* 118 */       this.probesVisitor.visitJumpInsnWithProbe(opcode, label, this.idGenerator
/* 119 */           .nextId(), frame(jumpPopCount(opcode)));
/*     */     } else {
/* 121 */       this.probesVisitor.visitJumpInsn(opcode, label);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int jumpPopCount(int opcode) {
/* 126 */     switch (opcode) {
/*     */       case 167:
/* 128 */         return 0;
/*     */       case 153:
/*     */       case 154:
/*     */       case 155:
/*     */       case 156:
/*     */       case 157:
/*     */       case 158:
/*     */       case 198:
/*     */       case 199:
/* 137 */         return 1;
/*     */     } 
/* 139 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 146 */     if (markLabels(dflt, labels)) {
/* 147 */       this.probesVisitor.visitLookupSwitchInsnWithProbes(dflt, keys, labels, 
/* 148 */           frame(1));
/*     */     } else {
/* 150 */       this.probesVisitor.visitLookupSwitchInsn(dflt, keys, labels);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 157 */     if (markLabels(dflt, labels)) {
/* 158 */       this.probesVisitor.visitTableSwitchInsnWithProbes(min, max, dflt, labels, 
/* 159 */           frame(1));
/*     */     } else {
/* 161 */       this.probesVisitor.visitTableSwitchInsn(min, max, dflt, labels);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean markLabels(Label dflt, Label[] labels) {
/* 166 */     boolean probe = false;
/* 167 */     LabelInfo.resetDone(labels);
/* 168 */     if (LabelInfo.isMultiTarget(dflt)) {
/* 169 */       LabelInfo.setProbeId(dflt, this.idGenerator.nextId());
/* 170 */       probe = true;
/*     */     } 
/* 172 */     LabelInfo.setDone(dflt);
/* 173 */     for (Label l : labels) {
/* 174 */       if (LabelInfo.isMultiTarget(l) && !LabelInfo.isDone(l)) {
/* 175 */         LabelInfo.setProbeId(l, this.idGenerator.nextId());
/* 176 */         probe = true;
/*     */       } 
/* 178 */       LabelInfo.setDone(l);
/*     */     } 
/* 180 */     return probe;
/*     */   }
/*     */   
/*     */   private IFrame frame(int popCount) {
/* 184 */     return FrameSnapshot.create(this.analyzer, popCount);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/flow/MethodProbesAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */