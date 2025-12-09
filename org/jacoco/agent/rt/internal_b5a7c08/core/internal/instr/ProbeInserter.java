/*     */ package org.jacoco.agent.rt.internal_b5a7c08.core.internal.instr;
/*     */ 
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.AnnotationVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Opcodes;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Type;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.TypePath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ProbeInserter
/*     */   extends MethodVisitor
/*     */   implements IProbeInserter
/*     */ {
/*     */   private final IProbeArrayStrategy arrayStrategy;
/*     */   private final boolean clinit;
/*     */   private final int variable;
/*     */   private final Label beginLabel;
/*     */   private int accessorStackSize;
/*     */   
/*     */   ProbeInserter(int access, String name, String desc, MethodVisitor mv, IProbeArrayStrategy arrayStrategy) {
/*  70 */     super(589824, mv);
/*  71 */     this.clinit = "<clinit>".equals(name);
/*  72 */     this.arrayStrategy = arrayStrategy;
/*  73 */     int pos = ((0x8 & access) == 0) ? 1 : 0;
/*  74 */     for (Type t : Type.getArgumentTypes(desc)) {
/*  75 */       pos += t.getSize();
/*     */     }
/*  77 */     this.variable = pos + 1;
/*  78 */     this.beginLabel = new Label();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertProbe(int id) {
/*  86 */     this.mv.visitVarInsn(25, this.variable);
/*     */ 
/*     */ 
/*     */     
/*  90 */     InstrSupport.push(this.mv, id);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     this.mv.visitInsn(4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     this.mv.visitInsn(84);
/*     */     
/* 103 */     this.mv.visitVarInsn(25, this.variable);
/*     */ 
/*     */ 
/*     */     
/* 107 */     InstrSupport.push(this.mv, id);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     this.mv.visitMethodInsn(184, "org/jacoco/agent/rt/internal_b5a7c08/core/data/ProbeUpdationEventEmitter", "updateProbe", "([ZI)V", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitCode() {
/* 123 */     this.mv.visitLabel(this.beginLabel);
/* 124 */     this.accessorStackSize = this.arrayStrategy.storeInstance(this.mv, this.clinit, this.variable);
/* 125 */     this.mv.visitCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void visitVarInsn(int opcode, int var) {
/* 130 */     this.mv.visitVarInsn(opcode, map(var));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void visitIincInsn(int var, int increment) {
/* 135 */     this.mv.visitIincInsn(map(var), increment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
/* 142 */     if (index < this.variable - 1) {
/*     */       
/* 144 */       this.mv.visitLocalVariable(name, desc, signature, this.beginLabel, end, index);
/*     */     } else {
/*     */       
/* 147 */       this.mv.visitLocalVariable(name, desc, signature, start, end, 
/* 148 */           map(index));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 156 */     int[] newIndex = new int[index.length];
/* 157 */     for (int i = 0; i < newIndex.length; i++) {
/* 158 */       newIndex[i] = map(index[i]);
/*     */     }
/* 160 */     return this.mv.visitLocalVariableAnnotation(typeRef, typePath, start, end, newIndex, descriptor, visible);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 170 */     int increasedStack = Math.max(maxStack + 3, this.accessorStackSize);
/* 171 */     this.mv.visitMaxs(increasedStack, maxLocals + 2);
/*     */   }
/*     */   
/*     */   private int map(int var) {
/* 175 */     if (var < this.variable - 1) {
/* 176 */       return var;
/*     */     }
/* 178 */     return var + 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
/* 186 */     if (type != -1) {
/* 187 */       throw new IllegalArgumentException("ClassReader.accept() should be called with EXPAND_FRAMES flag");
/*     */     }
/*     */ 
/*     */     
/* 191 */     Object[] newLocal = new Object[Math.max(nLocal + 2, this.variable + 1)];
/*     */     
/* 193 */     int idx = 0;
/* 194 */     int newIdx = 0;
/* 195 */     int pos = 0;
/* 196 */     while (idx < nLocal && pos < this.variable - 1) {
/* 197 */       Object t = local[idx++];
/* 198 */       newLocal[newIdx++] = t;
/* 199 */       pos += (t == Opcodes.LONG || t == Opcodes.DOUBLE) ? 2 : 1;
/*     */     } 
/* 201 */     boolean safetySlotOccupied = (pos == this.variable);
/* 202 */     while (pos < this.variable) {
/* 203 */       newLocal[newIdx++] = Opcodes.TOP;
/* 204 */       pos++;
/*     */     } 
/* 206 */     newLocal[newIdx++] = "[Z";
/* 207 */     if (idx < nLocal && safetySlotOccupied) {
/* 208 */       newLocal[newIdx++] = Opcodes.TOP;
/*     */     }
/* 210 */     while (idx < nLocal) {
/* 211 */       newLocal[newIdx++] = local[idx++];
/*     */     }
/*     */     
/* 214 */     this.mv.visitFrame(type, newIdx, newLocal, nStack, stack);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/core/internal/instr/ProbeInserter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */