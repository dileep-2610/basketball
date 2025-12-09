/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.commons;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Opcodes;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.AbstractInsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.InsnList;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.InsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.JumpInsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.LabelNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.LocalVariableNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.LookupSwitchInsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.MethodNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.TableSwitchInsnNode;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.tree.TryCatchBlockNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSRInlinerAdapter
/*     */   extends MethodNode
/*     */   implements Opcodes
/*     */ {
/*  65 */   private final BitSet mainSubroutineInsns = new BitSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private final Map<LabelNode, BitSet> subroutinesInsns = new HashMap<LabelNode, BitSet>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   final BitSet sharedSubroutineInsns = new BitSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSRInlinerAdapter(MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
/* 102 */     this(589824, methodVisitor, access, name, descriptor, signature, exceptions);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (getClass() != JSRInlinerAdapter.class) {
/* 111 */       throw new IllegalStateException();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JSRInlinerAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
/* 138 */     super(api, access, name, descriptor, signature, exceptions);
/* 139 */     this.mv = methodVisitor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 144 */     super.visitJumpInsn(opcode, label);
/* 145 */     LabelNode labelNode = ((JumpInsnNode)this.instructions.getLast()).label;
/* 146 */     if (opcode == 168 && !this.subroutinesInsns.containsKey(labelNode)) {
/* 147 */       this.subroutinesInsns.put(labelNode, new BitSet());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 153 */     if (!this.subroutinesInsns.isEmpty()) {
/*     */       
/* 155 */       findSubroutinesInsns();
/* 156 */       emitCode();
/*     */     } 
/* 158 */     if (this.mv != null) {
/* 159 */       accept(this.mv);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void findSubroutinesInsns() {
/* 166 */     BitSet visitedInsns = new BitSet();
/* 167 */     findSubroutineInsns(0, this.mainSubroutineInsns, visitedInsns);
/*     */     
/* 169 */     for (Map.Entry<LabelNode, BitSet> entry : this.subroutinesInsns.entrySet()) {
/* 170 */       LabelNode jsrLabelNode = entry.getKey();
/* 171 */       BitSet subroutineInsns = entry.getValue();
/* 172 */       findSubroutineInsns(this.instructions.indexOf((AbstractInsnNode)jsrLabelNode), subroutineInsns, visitedInsns);
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
/*     */   private void findSubroutineInsns(int startInsnIndex, BitSet subroutineInsns, BitSet visitedInsns) {
/*     */     boolean applicableHandlerFound;
/* 190 */     findReachableInsns(startInsnIndex, subroutineInsns, visitedInsns);
/*     */ 
/*     */     
/*     */     do {
/* 194 */       applicableHandlerFound = false;
/* 195 */       for (TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks)
/*     */       {
/* 197 */         int handlerIndex = this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.handler);
/* 198 */         if (subroutineInsns.get(handlerIndex)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 204 */         int startIndex = this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.start);
/* 205 */         int endIndex = this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.end);
/* 206 */         int firstSubroutineInsnAfterTryCatchStart = subroutineInsns.nextSetBit(startIndex);
/* 207 */         if (firstSubroutineInsnAfterTryCatchStart >= startIndex && firstSubroutineInsnAfterTryCatchStart < endIndex)
/*     */         {
/* 209 */           findReachableInsns(handlerIndex, subroutineInsns, visitedInsns);
/* 210 */           applicableHandlerFound = true;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 215 */     } while (applicableHandlerFound);
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
/*     */   private void findReachableInsns(int insnIndex, BitSet subroutineInsns, BitSet visitedInsns) {
/* 234 */     int currentInsnIndex = insnIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     while (currentInsnIndex < this.instructions.size()) {
/*     */       
/* 242 */       if (subroutineInsns.get(currentInsnIndex)) {
/*     */         return;
/*     */       }
/* 245 */       subroutineInsns.set(currentInsnIndex);
/*     */ 
/*     */       
/* 248 */       if (visitedInsns.get(currentInsnIndex)) {
/* 249 */         this.sharedSubroutineInsns.set(currentInsnIndex);
/*     */       }
/* 251 */       visitedInsns.set(currentInsnIndex);
/*     */       
/* 253 */       AbstractInsnNode currentInsnNode = this.instructions.get(currentInsnIndex);
/* 254 */       if (currentInsnNode.getType() == 7 && currentInsnNode
/* 255 */         .getOpcode() != 168) {
/*     */         
/* 257 */         JumpInsnNode jumpInsnNode = (JumpInsnNode)currentInsnNode;
/* 258 */         findReachableInsns(this.instructions.indexOf((AbstractInsnNode)jumpInsnNode.label), subroutineInsns, visitedInsns);
/* 259 */       } else if (currentInsnNode.getType() == 11) {
/* 260 */         TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode)currentInsnNode;
/* 261 */         findReachableInsns(this.instructions
/* 262 */             .indexOf((AbstractInsnNode)tableSwitchInsnNode.dflt), subroutineInsns, visitedInsns);
/* 263 */         for (LabelNode labelNode : tableSwitchInsnNode.labels) {
/* 264 */           findReachableInsns(this.instructions.indexOf((AbstractInsnNode)labelNode), subroutineInsns, visitedInsns);
/*     */         }
/* 266 */       } else if (currentInsnNode.getType() == 12) {
/* 267 */         LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode)currentInsnNode;
/* 268 */         findReachableInsns(this.instructions
/* 269 */             .indexOf((AbstractInsnNode)lookupSwitchInsnNode.dflt), subroutineInsns, visitedInsns);
/* 270 */         for (LabelNode labelNode : lookupSwitchInsnNode.labels) {
/* 271 */           findReachableInsns(this.instructions.indexOf((AbstractInsnNode)labelNode), subroutineInsns, visitedInsns);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 276 */       switch (this.instructions.get(currentInsnIndex).getOpcode()) {
/*     */         case 167:
/*     */         case 169:
/*     */         case 170:
/*     */         case 171:
/*     */         case 172:
/*     */         case 173:
/*     */         case 174:
/*     */         case 175:
/*     */         case 176:
/*     */         case 177:
/*     */         case 191:
/*     */           return;
/*     */       } 
/*     */ 
/*     */       
/* 292 */       currentInsnIndex++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void emitCode() {
/* 303 */     LinkedList<Instantiation> worklist = new LinkedList<Instantiation>();
/*     */     
/* 305 */     worklist.add(new Instantiation(null, this.mainSubroutineInsns));
/*     */ 
/*     */     
/* 308 */     InsnList newInstructions = new InsnList();
/* 309 */     List<TryCatchBlockNode> newTryCatchBlocks = new ArrayList<TryCatchBlockNode>();
/* 310 */     List<LocalVariableNode> newLocalVariables = new ArrayList<LocalVariableNode>();
/* 311 */     while (!worklist.isEmpty()) {
/* 312 */       Instantiation instantiation = worklist.removeFirst();
/* 313 */       emitInstantiation(instantiation, worklist, newInstructions, newTryCatchBlocks, newLocalVariables);
/*     */     } 
/*     */     
/* 316 */     this.instructions = newInstructions;
/* 317 */     this.tryCatchBlocks = newTryCatchBlocks;
/* 318 */     this.localVariables = newLocalVariables;
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
/*     */   private void emitInstantiation(Instantiation instantiation, List<Instantiation> worklist, InsnList newInstructions, List<TryCatchBlockNode> newTryCatchBlocks, List<LocalVariableNode> newLocalVariables) {
/* 340 */     LabelNode previousLabelNode = null;
/* 341 */     for (int i = 0; i < this.instructions.size(); i++) {
/* 342 */       AbstractInsnNode insnNode = this.instructions.get(i);
/* 343 */       if (insnNode.getType() == 8) {
/*     */         
/* 345 */         LabelNode labelNode = (LabelNode)insnNode;
/* 346 */         LabelNode clonedLabelNode = instantiation.getClonedLabel(labelNode);
/* 347 */         if (clonedLabelNode != previousLabelNode) {
/* 348 */           newInstructions.add((AbstractInsnNode)clonedLabelNode);
/* 349 */           previousLabelNode = clonedLabelNode;
/*     */         } 
/* 351 */       } else if (instantiation.findOwner(i) == instantiation) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 356 */         if (insnNode.getOpcode() == 169) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 361 */           LabelNode retLabel = null;
/* 362 */           Instantiation retLabelOwner = instantiation;
/* 363 */           for (; retLabelOwner != null; 
/* 364 */             retLabelOwner = retLabelOwner.parent) {
/* 365 */             if (retLabelOwner.subroutineInsns.get(i)) {
/* 366 */               retLabel = retLabelOwner.returnLabel;
/*     */             }
/*     */           } 
/* 369 */           if (retLabel == null)
/*     */           {
/*     */             
/* 372 */             throw new IllegalArgumentException(stringConcat$0(i));
/*     */           }
/*     */           
/* 375 */           newInstructions.add((AbstractInsnNode)new JumpInsnNode(167, retLabel));
/* 376 */         } else if (insnNode.getOpcode() == 168) {
/* 377 */           LabelNode jsrLabelNode = ((JumpInsnNode)insnNode).label;
/* 378 */           BitSet subroutineInsns = this.subroutinesInsns.get(jsrLabelNode);
/* 379 */           Instantiation newInstantiation = new Instantiation(instantiation, subroutineInsns);
/* 380 */           LabelNode clonedJsrLabelNode = newInstantiation.getClonedLabelForJumpInsn(jsrLabelNode);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 385 */           newInstructions.add((AbstractInsnNode)new InsnNode(1));
/* 386 */           newInstructions.add((AbstractInsnNode)new JumpInsnNode(167, clonedJsrLabelNode));
/* 387 */           newInstructions.add((AbstractInsnNode)newInstantiation.returnLabel);
/*     */           
/* 389 */           worklist.add(newInstantiation);
/*     */         } else {
/* 391 */           newInstructions.add(insnNode.clone(instantiation));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 397 */     for (TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks) {
/* 398 */       LabelNode start = instantiation.getClonedLabel(tryCatchBlockNode.start);
/* 399 */       LabelNode end = instantiation.getClonedLabel(tryCatchBlockNode.end);
/* 400 */       if (start != end) {
/*     */         
/* 402 */         LabelNode handler = instantiation.getClonedLabelForJumpInsn(tryCatchBlockNode.handler);
/* 403 */         if (start == null || end == null || handler == null) {
/* 404 */           throw new AssertionError("Internal error!");
/*     */         }
/* 406 */         newTryCatchBlocks.add(new TryCatchBlockNode(start, end, handler, tryCatchBlockNode.type));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 411 */     for (LocalVariableNode localVariableNode : this.localVariables) {
/* 412 */       LabelNode start = instantiation.getClonedLabel(localVariableNode.start);
/* 413 */       LabelNode end = instantiation.getClonedLabel(localVariableNode.end);
/* 414 */       if (start != end) {
/* 415 */         newLocalVariables.add(new LocalVariableNode(localVariableNode.name, localVariableNode.desc, localVariableNode.signature, start, end, localVariableNode.index));
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
/*     */   private final class Instantiation
/*     */     extends AbstractMap<LabelNode, LabelNode>
/*     */   {
/*     */     final Instantiation parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final BitSet subroutineInsns;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final Map<LabelNode, LabelNode> clonedLabels;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final LabelNode returnLabel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Instantiation(Instantiation parent, BitSet subroutineInsns) {
/* 456 */       Instantiation instantiation = parent;
/* 457 */       for (; instantiation != null; 
/* 458 */         instantiation = instantiation.parent) {
/* 459 */         if (instantiation.subroutineInsns == subroutineInsns) {
/* 460 */           throw new IllegalArgumentException(stringConcat$0(String.valueOf(subroutineInsns)));
/*     */         }
/*     */       } 
/*     */       
/* 464 */       this.parent = parent;
/* 465 */       this.subroutineInsns = subroutineInsns;
/* 466 */       this.returnLabel = (parent == null) ? null : new LabelNode();
/* 467 */       this.clonedLabels = new HashMap<LabelNode, LabelNode>();
/*     */ 
/*     */ 
/*     */       
/* 471 */       LabelNode clonedLabelNode = null;
/* 472 */       for (int insnIndex = 0; insnIndex < JSRInlinerAdapter.this.instructions.size(); insnIndex++) {
/* 473 */         AbstractInsnNode insnNode = JSRInlinerAdapter.this.instructions.get(insnIndex);
/* 474 */         if (insnNode.getType() == 8) {
/* 475 */           LabelNode labelNode = (LabelNode)insnNode;
/*     */           
/* 477 */           if (clonedLabelNode == null) {
/* 478 */             clonedLabelNode = new LabelNode();
/*     */           }
/* 480 */           this.clonedLabels.put(labelNode, clonedLabelNode);
/* 481 */         } else if (findOwner(insnIndex) == this) {
/*     */ 
/*     */           
/* 484 */           clonedLabelNode = null;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Instantiation findOwner(int insnIndex) {
/* 508 */       if (!this.subroutineInsns.get(insnIndex)) {
/* 509 */         return null;
/*     */       }
/* 511 */       if (!JSRInlinerAdapter.this.sharedSubroutineInsns.get(insnIndex)) {
/* 512 */         return this;
/*     */       }
/* 514 */       Instantiation owner = this;
/* 515 */       Instantiation instantiation = this.parent;
/* 516 */       for (; instantiation != null; 
/* 517 */         instantiation = instantiation.parent) {
/* 518 */         if (instantiation.subroutineInsns.get(insnIndex)) {
/* 519 */           owner = instantiation;
/*     */         }
/*     */       } 
/* 522 */       return owner;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     LabelNode getClonedLabelForJumpInsn(LabelNode labelNode) {
/* 535 */       return (findOwner(JSRInlinerAdapter.this.instructions.indexOf((AbstractInsnNode)labelNode))).clonedLabels.get(labelNode);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     LabelNode getClonedLabel(LabelNode labelNode) {
/* 547 */       return this.clonedLabels.get(labelNode);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Set<Map.Entry<LabelNode, LabelNode>> entrySet() {
/* 554 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public LabelNode get(Object key) {
/* 559 */       return getClonedLabelForJumpInsn((LabelNode)key);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object other) {
/* 564 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 569 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/commons/JSRInlinerAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */