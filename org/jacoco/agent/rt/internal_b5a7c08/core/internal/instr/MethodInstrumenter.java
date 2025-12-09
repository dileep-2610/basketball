/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow.IFrame;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow.LabelInfo;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.internal.flow.MethodProbesVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MethodInstrumenter
/*     */   extends MethodProbesVisitor
/*     */ {
/*     */   private final IProbeInserter probeInserter;
/*     */   
/*     */   public MethodInstrumenter(MethodVisitor mv, IProbeInserter probeInserter) {
/*  40 */     super(mv);
/*  41 */     this.probeInserter = probeInserter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitProbe(int probeId) {
/*  48 */     this.probeInserter.insertProbe(probeId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsnWithProbe(int opcode, int probeId) {
/*  53 */     this.probeInserter.insertProbe(probeId);
/*  54 */     this.mv.visitInsn(opcode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitJumpInsnWithProbe(int opcode, Label label, int probeId, IFrame frame) {
/*  60 */     if (opcode == 167) {
/*  61 */       this.probeInserter.insertProbe(probeId);
/*  62 */       this.mv.visitJumpInsn(167, label);
/*     */     } else {
/*  64 */       Label intermediate = new Label();
/*  65 */       this.mv.visitJumpInsn(getInverted(opcode), intermediate);
/*  66 */       this.probeInserter.insertProbe(probeId);
/*  67 */       this.mv.visitJumpInsn(167, label);
/*  68 */       this.mv.visitLabel(intermediate);
/*  69 */       frame.accept(this.mv);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getInverted(int opcode) {
/*  74 */     switch (opcode) {
/*     */       case 153:
/*  76 */         return 154;
/*     */       case 154:
/*  78 */         return 153;
/*     */       case 155:
/*  80 */         return 156;
/*     */       case 156:
/*  82 */         return 155;
/*     */       case 157:
/*  84 */         return 158;
/*     */       case 158:
/*  86 */         return 157;
/*     */       case 159:
/*  88 */         return 160;
/*     */       case 160:
/*  90 */         return 159;
/*     */       case 161:
/*  92 */         return 162;
/*     */       case 162:
/*  94 */         return 161;
/*     */       case 163:
/*  96 */         return 164;
/*     */       case 164:
/*  98 */         return 163;
/*     */       case 165:
/* 100 */         return 166;
/*     */       case 166:
/* 102 */         return 165;
/*     */       case 198:
/* 104 */         return 199;
/*     */       case 199:
/* 106 */         return 198;
/*     */     } 
/* 108 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsnWithProbes(int min, int max, Label dflt, Label[] labels, IFrame frame) {
/* 115 */     LabelInfo.resetDone(dflt);
/* 116 */     LabelInfo.resetDone(labels);
/* 117 */     Label newDflt = createIntermediate(dflt);
/* 118 */     Label[] newLabels = createIntermediates(labels);
/* 119 */     this.mv.visitTableSwitchInsn(min, max, newDflt, newLabels);
/*     */ 
/*     */     
/* 122 */     insertIntermediateProbes(dflt, labels, frame);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsnWithProbes(Label dflt, int[] keys, Label[] labels, IFrame frame) {
/* 129 */     LabelInfo.resetDone(dflt);
/* 130 */     LabelInfo.resetDone(labels);
/* 131 */     Label newDflt = createIntermediate(dflt);
/* 132 */     Label[] newLabels = createIntermediates(labels);
/* 133 */     this.mv.visitLookupSwitchInsn(newDflt, keys, newLabels);
/*     */ 
/*     */     
/* 136 */     insertIntermediateProbes(dflt, labels, frame);
/*     */   }
/*     */   
/*     */   private Label[] createIntermediates(Label[] labels) {
/* 140 */     Label[] intermediates = new Label[labels.length];
/* 141 */     for (int i = 0; i < labels.length; i++) {
/* 142 */       intermediates[i] = createIntermediate(labels[i]);
/*     */     }
/* 144 */     return intermediates;
/*     */   }
/*     */   
/*     */   private Label createIntermediate(Label label) {
/*     */     Label intermediate;
/* 149 */     if (LabelInfo.getProbeId(label) == -1) {
/* 150 */       intermediate = label;
/*     */     }
/* 152 */     else if (LabelInfo.isDone(label)) {
/* 153 */       intermediate = LabelInfo.getIntermediateLabel(label);
/*     */     } else {
/* 155 */       intermediate = new Label();
/* 156 */       LabelInfo.setIntermediateLabel(label, intermediate);
/* 157 */       LabelInfo.setDone(label);
/*     */     } 
/*     */     
/* 160 */     return intermediate;
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertIntermediateProbe(Label label, IFrame frame) {
/* 165 */     int probeId = LabelInfo.getProbeId(label);
/* 166 */     if (probeId != -1 && !LabelInfo.isDone(label)) {
/* 167 */       this.mv.visitLabel(LabelInfo.getIntermediateLabel(label));
/* 168 */       frame.accept(this.mv);
/* 169 */       this.probeInserter.insertProbe(probeId);
/* 170 */       this.mv.visitJumpInsn(167, label);
/* 171 */       LabelInfo.setDone(label);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertIntermediateProbes(Label dflt, Label[] labels, IFrame frame) {
/* 177 */     LabelInfo.resetDone(dflt);
/* 178 */     LabelInfo.resetDone(labels);
/* 179 */     insertIntermediateProbe(dflt, frame);
/* 180 */     for (Label l : labels)
/* 181 */       insertIntermediateProbe(l, frame); 
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/MethodInstrumenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */