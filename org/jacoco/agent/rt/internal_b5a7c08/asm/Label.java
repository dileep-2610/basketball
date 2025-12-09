/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Label
/*     */ {
/*     */   static final int FLAG_DEBUG_ONLY = 1;
/*     */   static final int FLAG_JUMP_TARGET = 2;
/*     */   static final int FLAG_RESOLVED = 4;
/*     */   static final int FLAG_REACHABLE = 8;
/*     */   static final int FLAG_SUBROUTINE_CALLER = 16;
/*     */   static final int FLAG_SUBROUTINE_START = 32;
/*     */   static final int FLAG_SUBROUTINE_END = 64;
/*     */   static final int FLAG_LINE_NUMBER = 128;
/*     */   static final int LINE_NUMBERS_CAPACITY_INCREMENT = 4;
/*     */   static final int FORWARD_REFERENCES_CAPACITY_INCREMENT = 6;
/*     */   static final int FORWARD_REFERENCE_TYPE_MASK = -268435456;
/*     */   static final int FORWARD_REFERENCE_TYPE_SHORT = 268435456;
/*     */   static final int FORWARD_REFERENCE_TYPE_WIDE = 536870912;
/*     */   static final int FORWARD_REFERENCE_TYPE_STACK_MAP = 805306368;
/*     */   static final int FORWARD_REFERENCE_HANDLE_MASK = 268435455;
/* 140 */   static final Label EMPTY_LIST = new Label();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object info;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   short flags;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short lineNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] otherLineNumbers;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int bytecodeOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] forwardReferences;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   short inputStackSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   short outputStackSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   short outputStackMax;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   short subroutineId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Frame frame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Label nextBasicBlock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Edge outgoingEdges;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Label nextListElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffset() {
/* 312 */     if ((this.flags & 0x4) == 0) {
/* 313 */       throw new IllegalStateException("Label offset position has not been resolved yet");
/*     */     }
/* 315 */     return this.bytecodeOffset;
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
/*     */   final Label getCanonicalInstance() {
/* 332 */     return (this.frame == null) ? this : this.frame.owner;
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
/*     */   final void addLineNumber(int lineNumber) {
/* 345 */     if ((this.flags & 0x80) == 0) {
/* 346 */       this.flags = (short)(this.flags | 0x80);
/* 347 */       this.lineNumber = (short)lineNumber;
/*     */     } else {
/* 349 */       if (this.otherLineNumbers == null) {
/* 350 */         this.otherLineNumbers = new int[4];
/*     */       }
/* 352 */       int otherLineNumberIndex = this.otherLineNumbers[0] = this.otherLineNumbers[0] + 1;
/* 353 */       if (otherLineNumberIndex >= this.otherLineNumbers.length) {
/* 354 */         int[] newLineNumbers = new int[this.otherLineNumbers.length + 4];
/* 355 */         System.arraycopy(this.otherLineNumbers, 0, newLineNumbers, 0, this.otherLineNumbers.length);
/* 356 */         this.otherLineNumbers = newLineNumbers;
/*     */       } 
/* 358 */       this.otherLineNumbers[otherLineNumberIndex] = lineNumber;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void accept(MethodVisitor methodVisitor, boolean visitLineNumbers) {
/* 369 */     methodVisitor.visitLabel(this);
/* 370 */     if (visitLineNumbers && (this.flags & 0x80) != 0) {
/* 371 */       methodVisitor.visitLineNumber(this.lineNumber & 0xFFFF, this);
/* 372 */       if (this.otherLineNumbers != null) {
/* 373 */         for (int i = 1; i <= this.otherLineNumbers[0]; i++) {
/* 374 */           methodVisitor.visitLineNumber(this.otherLineNumbers[i], this);
/*     */         }
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
/*     */   
/*     */   final void put(ByteVector code, int sourceInsnBytecodeOffset, boolean wideReference) {
/* 397 */     if ((this.flags & 0x4) == 0) {
/* 398 */       if (wideReference) {
/* 399 */         addForwardReference(sourceInsnBytecodeOffset, 536870912, code.length);
/* 400 */         code.putInt(-1);
/*     */       } else {
/* 402 */         addForwardReference(sourceInsnBytecodeOffset, 268435456, code.length);
/* 403 */         code.putShort(-1);
/*     */       }
/*     */     
/* 406 */     } else if (wideReference) {
/* 407 */       code.putInt(this.bytecodeOffset - sourceInsnBytecodeOffset);
/*     */     } else {
/* 409 */       code.putShort(this.bytecodeOffset - sourceInsnBytecodeOffset);
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
/*     */   final void put(ByteVector stackMapTableEntries) {
/* 422 */     if ((this.flags & 0x4) == 0) {
/* 423 */       addForwardReference(0, 805306368, stackMapTableEntries.length);
/*     */     }
/* 425 */     stackMapTableEntries.putShort(this.bytecodeOffset);
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
/*     */   private void addForwardReference(int sourceInsnBytecodeOffset, int referenceType, int referenceHandle) {
/* 442 */     if (this.forwardReferences == null) {
/* 443 */       this.forwardReferences = new int[6];
/*     */     }
/* 445 */     int lastElementIndex = this.forwardReferences[0];
/* 446 */     if (lastElementIndex + 2 >= this.forwardReferences.length) {
/* 447 */       int[] newValues = new int[this.forwardReferences.length + 6];
/* 448 */       System.arraycopy(this.forwardReferences, 0, newValues, 0, this.forwardReferences.length);
/* 449 */       this.forwardReferences = newValues;
/*     */     } 
/* 451 */     this.forwardReferences[++lastElementIndex] = sourceInsnBytecodeOffset;
/* 452 */     this.forwardReferences[++lastElementIndex] = referenceType | referenceHandle;
/* 453 */     this.forwardReferences[0] = lastElementIndex;
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
/*     */   final boolean resolve(byte[] code, ByteVector stackMapTableEntries, int bytecodeOffset) {
/* 475 */     this.flags = (short)(this.flags | 0x4);
/* 476 */     this.bytecodeOffset = bytecodeOffset;
/* 477 */     if (this.forwardReferences == null) {
/* 478 */       return false;
/*     */     }
/* 480 */     boolean hasAsmInstructions = false;
/* 481 */     for (int i = this.forwardReferences[0]; i > 0; i -= 2) {
/* 482 */       int sourceInsnBytecodeOffset = this.forwardReferences[i - 1];
/* 483 */       int reference = this.forwardReferences[i];
/* 484 */       int relativeOffset = bytecodeOffset - sourceInsnBytecodeOffset;
/* 485 */       int handle = reference & 0xFFFFFFF;
/* 486 */       if ((reference & 0xF0000000) == 268435456) {
/* 487 */         if (relativeOffset < -32768 || relativeOffset > 32767) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 492 */           int opcode = code[sourceInsnBytecodeOffset] & 0xFF;
/* 493 */           if (opcode < 198) {
/*     */             
/* 495 */             code[sourceInsnBytecodeOffset] = (byte)(opcode + 49);
/*     */           } else {
/*     */             
/* 498 */             code[sourceInsnBytecodeOffset] = (byte)(opcode + 20);
/*     */           } 
/* 500 */           hasAsmInstructions = true;
/*     */         } 
/* 502 */         code[handle++] = (byte)(relativeOffset >>> 8);
/* 503 */         code[handle] = (byte)relativeOffset;
/* 504 */       } else if ((reference & 0xF0000000) == 536870912) {
/* 505 */         code[handle++] = (byte)(relativeOffset >>> 24);
/* 506 */         code[handle++] = (byte)(relativeOffset >>> 16);
/* 507 */         code[handle++] = (byte)(relativeOffset >>> 8);
/* 508 */         code[handle] = (byte)relativeOffset;
/*     */       } else {
/* 510 */         stackMapTableEntries.data[handle++] = (byte)(bytecodeOffset >>> 8);
/* 511 */         stackMapTableEntries.data[handle] = (byte)bytecodeOffset;
/*     */       } 
/*     */     } 
/* 514 */     return hasAsmInstructions;
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
/*     */   final void markSubroutine(short subroutineId) {
/* 538 */     Label listOfBlocksToProcess = this;
/* 539 */     listOfBlocksToProcess.nextListElement = EMPTY_LIST;
/* 540 */     while (listOfBlocksToProcess != EMPTY_LIST) {
/*     */       
/* 542 */       Label basicBlock = listOfBlocksToProcess;
/* 543 */       listOfBlocksToProcess = listOfBlocksToProcess.nextListElement;
/* 544 */       basicBlock.nextListElement = null;
/*     */ 
/*     */ 
/*     */       
/* 548 */       if (basicBlock.subroutineId == 0) {
/* 549 */         basicBlock.subroutineId = subroutineId;
/* 550 */         listOfBlocksToProcess = basicBlock.pushSuccessors(listOfBlocksToProcess);
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
/*     */ 
/*     */ 
/*     */   
/*     */   final void addSubroutineRetSuccessors(Label subroutineCaller) {
/* 574 */     Label listOfProcessedBlocks = EMPTY_LIST;
/* 575 */     Label listOfBlocksToProcess = this;
/* 576 */     listOfBlocksToProcess.nextListElement = EMPTY_LIST;
/* 577 */     while (listOfBlocksToProcess != EMPTY_LIST) {
/*     */       
/* 579 */       Label basicBlock = listOfBlocksToProcess;
/* 580 */       listOfBlocksToProcess = basicBlock.nextListElement;
/* 581 */       basicBlock.nextListElement = listOfProcessedBlocks;
/* 582 */       listOfProcessedBlocks = basicBlock;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 587 */       if ((basicBlock.flags & 0x40) != 0 && basicBlock.subroutineId != subroutineCaller.subroutineId)
/*     */       {
/* 589 */         basicBlock.outgoingEdges = new Edge(basicBlock.outputStackSize, subroutineCaller.outgoingEdges.successor, basicBlock.outgoingEdges);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 602 */       listOfBlocksToProcess = basicBlock.pushSuccessors(listOfBlocksToProcess);
/*     */     } 
/*     */ 
/*     */     
/* 606 */     while (listOfProcessedBlocks != EMPTY_LIST) {
/* 607 */       Label newListOfProcessedBlocks = listOfProcessedBlocks.nextListElement;
/* 608 */       listOfProcessedBlocks.nextListElement = null;
/* 609 */       listOfProcessedBlocks = newListOfProcessedBlocks;
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
/*     */   private Label pushSuccessors(Label listOfLabelsToProcess) {
/* 623 */     Label newListOfLabelsToProcess = listOfLabelsToProcess;
/* 624 */     Edge outgoingEdge = this.outgoingEdges;
/* 625 */     while (outgoingEdge != null) {
/*     */ 
/*     */       
/* 628 */       boolean isJsrTarget = ((this.flags & 0x10) != 0 && outgoingEdge == this.outgoingEdges.nextEdge);
/*     */       
/* 630 */       if (!isJsrTarget && outgoingEdge.successor.nextListElement == null) {
/*     */ 
/*     */         
/* 633 */         outgoingEdge.successor.nextListElement = newListOfLabelsToProcess;
/* 634 */         newListOfLabelsToProcess = outgoingEdge.successor;
/*     */       } 
/* 636 */       outgoingEdge = outgoingEdge.nextEdge;
/*     */     } 
/* 638 */     return newListOfLabelsToProcess;
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
/*     */   public String toString() {
/* 652 */     return stringConcat$0(System.identityHashCode(this));
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/Label.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */