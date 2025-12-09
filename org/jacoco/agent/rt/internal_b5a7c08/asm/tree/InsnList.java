/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.tree;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InsnList
/*     */   implements Iterable<AbstractInsnNode>
/*     */ {
/*     */   private int size;
/*     */   private AbstractInsnNode firstInsn;
/*     */   private AbstractInsnNode lastInsn;
/*     */   AbstractInsnNode[] cache;
/*     */   
/*     */   public int size() {
/*  61 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode getFirst() {
/*  70 */     return this.firstInsn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode getLast() {
/*  79 */     return this.lastInsn;
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
/*     */   public AbstractInsnNode get(int index) {
/*  93 */     if (index < 0 || index >= this.size) {
/*  94 */       throw new IndexOutOfBoundsException();
/*     */     }
/*  96 */     if (this.cache == null) {
/*  97 */       this.cache = toArray();
/*     */     }
/*  99 */     return this.cache[index];
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
/*     */   public boolean contains(AbstractInsnNode insnNode) {
/* 111 */     AbstractInsnNode currentInsn = this.firstInsn;
/* 112 */     while (currentInsn != null && currentInsn != insnNode) {
/* 113 */       currentInsn = currentInsn.nextInsn;
/*     */     }
/* 115 */     return (currentInsn != null);
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
/*     */   public int indexOf(AbstractInsnNode insnNode) {
/* 130 */     if (this.cache == null) {
/* 131 */       this.cache = toArray();
/*     */     }
/* 133 */     return insnNode.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 142 */     AbstractInsnNode currentInsn = this.firstInsn;
/* 143 */     while (currentInsn != null) {
/* 144 */       currentInsn.accept(methodVisitor);
/* 145 */       currentInsn = currentInsn.nextInsn;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<AbstractInsnNode> iterator() {
/* 156 */     return iterator(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<AbstractInsnNode> iterator(int index) {
/* 167 */     return new InsnListIterator(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode[] toArray() {
/* 176 */     int currentInsnIndex = 0;
/* 177 */     AbstractInsnNode currentInsn = this.firstInsn;
/* 178 */     AbstractInsnNode[] insnNodeArray = new AbstractInsnNode[this.size];
/* 179 */     while (currentInsn != null) {
/* 180 */       insnNodeArray[currentInsnIndex] = currentInsn;
/* 181 */       currentInsn.index = currentInsnIndex++;
/* 182 */       currentInsn = currentInsn.nextInsn;
/*     */     } 
/* 184 */     return insnNodeArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(AbstractInsnNode oldInsnNode, AbstractInsnNode newInsnNode) {
/* 194 */     AbstractInsnNode nextInsn = oldInsnNode.nextInsn;
/* 195 */     newInsnNode.nextInsn = nextInsn;
/* 196 */     if (nextInsn != null) {
/* 197 */       nextInsn.previousInsn = newInsnNode;
/*     */     } else {
/* 199 */       this.lastInsn = newInsnNode;
/*     */     } 
/* 201 */     AbstractInsnNode previousInsn = oldInsnNode.previousInsn;
/* 202 */     newInsnNode.previousInsn = previousInsn;
/* 203 */     if (previousInsn != null) {
/* 204 */       previousInsn.nextInsn = newInsnNode;
/*     */     } else {
/* 206 */       this.firstInsn = newInsnNode;
/*     */     } 
/* 208 */     if (this.cache != null) {
/* 209 */       int index = oldInsnNode.index;
/* 210 */       this.cache[index] = newInsnNode;
/* 211 */       newInsnNode.index = index;
/*     */     } else {
/* 213 */       newInsnNode.index = 0;
/*     */     } 
/* 215 */     oldInsnNode.index = -1;
/* 216 */     oldInsnNode.previousInsn = null;
/* 217 */     oldInsnNode.nextInsn = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(AbstractInsnNode insnNode) {
/* 226 */     this.size++;
/* 227 */     if (this.lastInsn == null) {
/* 228 */       this.firstInsn = insnNode;
/* 229 */       this.lastInsn = insnNode;
/*     */     } else {
/* 231 */       this.lastInsn.nextInsn = insnNode;
/* 232 */       insnNode.previousInsn = this.lastInsn;
/*     */     } 
/* 234 */     this.lastInsn = insnNode;
/* 235 */     this.cache = null;
/* 236 */     insnNode.index = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(InsnList insnList) {
/* 246 */     if (insnList.size == 0) {
/*     */       return;
/*     */     }
/* 249 */     this.size += insnList.size;
/* 250 */     if (this.lastInsn == null) {
/* 251 */       this.firstInsn = insnList.firstInsn;
/* 252 */       this.lastInsn = insnList.lastInsn;
/*     */     } else {
/* 254 */       AbstractInsnNode firstInsnListElement = insnList.firstInsn;
/* 255 */       this.lastInsn.nextInsn = firstInsnListElement;
/* 256 */       firstInsnListElement.previousInsn = this.lastInsn;
/* 257 */       this.lastInsn = insnList.lastInsn;
/*     */     } 
/* 259 */     this.cache = null;
/* 260 */     insnList.removeAll(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(AbstractInsnNode insnNode) {
/* 269 */     this.size++;
/* 270 */     if (this.firstInsn == null) {
/* 271 */       this.firstInsn = insnNode;
/* 272 */       this.lastInsn = insnNode;
/*     */     } else {
/* 274 */       this.firstInsn.previousInsn = insnNode;
/* 275 */       insnNode.nextInsn = this.firstInsn;
/*     */     } 
/* 277 */     this.firstInsn = insnNode;
/* 278 */     this.cache = null;
/* 279 */     insnNode.index = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(InsnList insnList) {
/* 289 */     if (insnList.size == 0) {
/*     */       return;
/*     */     }
/* 292 */     this.size += insnList.size;
/* 293 */     if (this.firstInsn == null) {
/* 294 */       this.firstInsn = insnList.firstInsn;
/* 295 */       this.lastInsn = insnList.lastInsn;
/*     */     } else {
/* 297 */       AbstractInsnNode lastInsnListElement = insnList.lastInsn;
/* 298 */       this.firstInsn.previousInsn = lastInsnListElement;
/* 299 */       lastInsnListElement.nextInsn = this.firstInsn;
/* 300 */       this.firstInsn = insnList.firstInsn;
/*     */     } 
/* 302 */     this.cache = null;
/* 303 */     insnList.removeAll(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(AbstractInsnNode previousInsn, AbstractInsnNode insnNode) {
/* 314 */     this.size++;
/* 315 */     AbstractInsnNode nextInsn = previousInsn.nextInsn;
/* 316 */     if (nextInsn == null) {
/* 317 */       this.lastInsn = insnNode;
/*     */     } else {
/* 319 */       nextInsn.previousInsn = insnNode;
/*     */     } 
/* 321 */     previousInsn.nextInsn = insnNode;
/* 322 */     insnNode.nextInsn = nextInsn;
/* 323 */     insnNode.previousInsn = previousInsn;
/* 324 */     this.cache = null;
/* 325 */     insnNode.index = 0;
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
/*     */   public void insert(AbstractInsnNode previousInsn, InsnList insnList) {
/* 337 */     if (insnList.size == 0) {
/*     */       return;
/*     */     }
/* 340 */     this.size += insnList.size;
/* 341 */     AbstractInsnNode firstInsnListElement = insnList.firstInsn;
/* 342 */     AbstractInsnNode lastInsnListElement = insnList.lastInsn;
/* 343 */     AbstractInsnNode nextInsn = previousInsn.nextInsn;
/* 344 */     if (nextInsn == null) {
/* 345 */       this.lastInsn = lastInsnListElement;
/*     */     } else {
/* 347 */       nextInsn.previousInsn = lastInsnListElement;
/*     */     } 
/* 349 */     previousInsn.nextInsn = firstInsnListElement;
/* 350 */     lastInsnListElement.nextInsn = nextInsn;
/* 351 */     firstInsnListElement.previousInsn = previousInsn;
/* 352 */     this.cache = null;
/* 353 */     insnList.removeAll(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertBefore(AbstractInsnNode nextInsn, AbstractInsnNode insnNode) {
/* 364 */     this.size++;
/* 365 */     AbstractInsnNode previousInsn = nextInsn.previousInsn;
/* 366 */     if (previousInsn == null) {
/* 367 */       this.firstInsn = insnNode;
/*     */     } else {
/* 369 */       previousInsn.nextInsn = insnNode;
/*     */     } 
/* 371 */     nextInsn.previousInsn = insnNode;
/* 372 */     insnNode.nextInsn = nextInsn;
/* 373 */     insnNode.previousInsn = previousInsn;
/* 374 */     this.cache = null;
/* 375 */     insnNode.index = 0;
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
/*     */   public void insertBefore(AbstractInsnNode nextInsn, InsnList insnList) {
/* 387 */     if (insnList.size == 0) {
/*     */       return;
/*     */     }
/* 390 */     this.size += insnList.size;
/* 391 */     AbstractInsnNode firstInsnListElement = insnList.firstInsn;
/* 392 */     AbstractInsnNode lastInsnListElement = insnList.lastInsn;
/* 393 */     AbstractInsnNode previousInsn = nextInsn.previousInsn;
/* 394 */     if (previousInsn == null) {
/* 395 */       this.firstInsn = firstInsnListElement;
/*     */     } else {
/* 397 */       previousInsn.nextInsn = firstInsnListElement;
/*     */     } 
/* 399 */     nextInsn.previousInsn = lastInsnListElement;
/* 400 */     lastInsnListElement.nextInsn = nextInsn;
/* 401 */     firstInsnListElement.previousInsn = previousInsn;
/* 402 */     this.cache = null;
/* 403 */     insnList.removeAll(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(AbstractInsnNode insnNode) {
/* 412 */     this.size--;
/* 413 */     AbstractInsnNode nextInsn = insnNode.nextInsn;
/* 414 */     AbstractInsnNode previousInsn = insnNode.previousInsn;
/* 415 */     if (nextInsn == null) {
/* 416 */       if (previousInsn == null) {
/* 417 */         this.firstInsn = null;
/* 418 */         this.lastInsn = null;
/*     */       } else {
/* 420 */         previousInsn.nextInsn = null;
/* 421 */         this.lastInsn = previousInsn;
/*     */       }
/*     */     
/* 424 */     } else if (previousInsn == null) {
/* 425 */       this.firstInsn = nextInsn;
/* 426 */       nextInsn.previousInsn = null;
/*     */     } else {
/* 428 */       previousInsn.nextInsn = nextInsn;
/* 429 */       nextInsn.previousInsn = previousInsn;
/*     */     } 
/*     */     
/* 432 */     this.cache = null;
/* 433 */     insnNode.index = -1;
/* 434 */     insnNode.previousInsn = null;
/* 435 */     insnNode.nextInsn = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeAll(boolean mark) {
/* 444 */     if (mark) {
/* 445 */       AbstractInsnNode currentInsn = this.firstInsn;
/* 446 */       while (currentInsn != null) {
/* 447 */         AbstractInsnNode next = currentInsn.nextInsn;
/* 448 */         currentInsn.index = -1;
/* 449 */         currentInsn.previousInsn = null;
/* 450 */         currentInsn.nextInsn = null;
/* 451 */         currentInsn = next;
/*     */       } 
/*     */     } 
/* 454 */     this.size = 0;
/* 455 */     this.firstInsn = null;
/* 456 */     this.lastInsn = null;
/* 457 */     this.cache = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 462 */     removeAll(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetLabels() {
/* 470 */     AbstractInsnNode currentInsn = this.firstInsn;
/* 471 */     while (currentInsn != null) {
/* 472 */       if (currentInsn instanceof LabelNode) {
/* 473 */         ((LabelNode)currentInsn).resetLabel();
/*     */       }
/* 475 */       currentInsn = currentInsn.nextInsn;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final class InsnListIterator
/*     */     implements ListIterator
/*     */   {
/*     */     AbstractInsnNode nextInsn;
/*     */     
/*     */     AbstractInsnNode previousInsn;
/*     */     
/*     */     AbstractInsnNode remove;
/*     */     
/*     */     InsnListIterator(int index) {
/* 490 */       if (index < 0 || index > InsnList.this.size())
/* 491 */         throw new IndexOutOfBoundsException(); 
/* 492 */       if (index == InsnList.this.size()) {
/* 493 */         this.nextInsn = null;
/* 494 */         this.previousInsn = InsnList.this.getLast();
/*     */       } else {
/* 496 */         AbstractInsnNode currentInsn = InsnList.this.getFirst();
/* 497 */         for (int i = 0; i < index; i++) {
/* 498 */           currentInsn = currentInsn.nextInsn;
/*     */         }
/*     */         
/* 501 */         this.nextInsn = currentInsn;
/* 502 */         this.previousInsn = currentInsn.previousInsn;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 508 */       return (this.nextInsn != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object next() {
/* 513 */       if (this.nextInsn == null) {
/* 514 */         throw new NoSuchElementException();
/*     */       }
/* 516 */       AbstractInsnNode result = this.nextInsn;
/* 517 */       this.previousInsn = result;
/* 518 */       this.nextInsn = result.nextInsn;
/* 519 */       this.remove = result;
/* 520 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 525 */       if (this.remove != null) {
/* 526 */         if (this.remove == this.nextInsn) {
/* 527 */           this.nextInsn = this.nextInsn.nextInsn;
/*     */         } else {
/* 529 */           this.previousInsn = this.previousInsn.previousInsn;
/*     */         } 
/* 531 */         InsnList.this.remove(this.remove);
/* 532 */         this.remove = null;
/*     */       } else {
/* 534 */         throw new IllegalStateException();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasPrevious() {
/* 540 */       return (this.previousInsn != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object previous() {
/* 545 */       if (this.previousInsn == null) {
/* 546 */         throw new NoSuchElementException();
/*     */       }
/* 548 */       AbstractInsnNode result = this.previousInsn;
/* 549 */       this.nextInsn = result;
/* 550 */       this.previousInsn = result.previousInsn;
/* 551 */       this.remove = result;
/* 552 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public int nextIndex() {
/* 557 */       if (this.nextInsn == null) {
/* 558 */         return InsnList.this.size();
/*     */       }
/* 560 */       if (InsnList.this.cache == null) {
/* 561 */         InsnList.this.cache = InsnList.this.toArray();
/*     */       }
/* 563 */       return this.nextInsn.index;
/*     */     }
/*     */ 
/*     */     
/*     */     public int previousIndex() {
/* 568 */       if (this.previousInsn == null) {
/* 569 */         return -1;
/*     */       }
/* 571 */       if (InsnList.this.cache == null) {
/* 572 */         InsnList.this.cache = InsnList.this.toArray();
/*     */       }
/* 574 */       return this.previousInsn.index;
/*     */     }
/*     */ 
/*     */     
/*     */     public void add(Object o) {
/* 579 */       if (this.nextInsn != null) {
/* 580 */         InsnList.this.insertBefore(this.nextInsn, (AbstractInsnNode)o);
/* 581 */       } else if (this.previousInsn != null) {
/* 582 */         InsnList.this.insert(this.previousInsn, (AbstractInsnNode)o);
/*     */       } else {
/* 584 */         InsnList.this.add((AbstractInsnNode)o);
/*     */       } 
/* 586 */       this.previousInsn = (AbstractInsnNode)o;
/* 587 */       this.remove = null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void set(Object o) {
/* 592 */       if (this.remove != null) {
/* 593 */         InsnList.this.set(this.remove, (AbstractInsnNode)o);
/* 594 */         if (this.remove == this.previousInsn) {
/* 595 */           this.previousInsn = (AbstractInsnNode)o;
/*     */         } else {
/* 597 */           this.nextInsn = (AbstractInsnNode)o;
/*     */         } 
/*     */       } else {
/* 600 */         throw new IllegalStateException();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/tree/InsnList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */