/*     */ package org.jacoco.agent.rt.internal_b5a7c08.asm.commons;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.ConstantDynamic;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Handle;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Label;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.MethodVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Opcodes;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.asm.Type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnalyzerAdapter
/*     */   extends MethodVisitor
/*     */ {
/*     */   public List<Object> locals;
/*     */   public List<Object> stack;
/*     */   private List<Label> labels;
/*     */   public Map<Object, Object> uninitializedTypes;
/*     */   private int maxStack;
/*     */   private int maxLocals;
/*     */   private String owner;
/*     */   
/*     */   public AnalyzerAdapter(String owner, int access, String name, String descriptor, MethodVisitor methodVisitor) {
/* 121 */     this(589824, owner, access, name, descriptor, methodVisitor);
/* 122 */     if (getClass() != AnalyzerAdapter.class) {
/* 123 */       throw new IllegalStateException();
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
/*     */   protected AnalyzerAdapter(int api, String owner, int access, String name, String descriptor, MethodVisitor methodVisitor) {
/* 146 */     super(api, methodVisitor);
/* 147 */     this.owner = owner;
/* 148 */     this.locals = new ArrayList();
/* 149 */     this.stack = new ArrayList();
/* 150 */     this.uninitializedTypes = new HashMap<Object, Object>();
/*     */     
/* 152 */     if ((access & 0x8) == 0) {
/* 153 */       if ("<init>".equals(name)) {
/* 154 */         this.locals.add(Opcodes.UNINITIALIZED_THIS);
/*     */       } else {
/* 156 */         this.locals.add(owner);
/*     */       } 
/*     */     }
/* 159 */     for (Type argumentType : Type.getArgumentTypes(descriptor)) {
/* 160 */       switch (argumentType.getSort()) {
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/*     */         case 5:
/* 166 */           this.locals.add(Opcodes.INTEGER);
/*     */           break;
/*     */         case 6:
/* 169 */           this.locals.add(Opcodes.FLOAT);
/*     */           break;
/*     */         case 7:
/* 172 */           this.locals.add(Opcodes.LONG);
/* 173 */           this.locals.add(Opcodes.TOP);
/*     */           break;
/*     */         case 8:
/* 176 */           this.locals.add(Opcodes.DOUBLE);
/* 177 */           this.locals.add(Opcodes.TOP);
/*     */           break;
/*     */         case 9:
/* 180 */           this.locals.add(argumentType.getDescriptor());
/*     */           break;
/*     */         case 10:
/* 183 */           this.locals.add(argumentType.getInternalName());
/*     */           break;
/*     */         default:
/* 186 */           throw new AssertionError();
/*     */       } 
/*     */     } 
/* 189 */     this.maxLocals = this.locals.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 199 */     if (type != -1) {
/* 200 */       throw new IllegalArgumentException("AnalyzerAdapter only accepts expanded frames (see ClassReader.EXPAND_FRAMES)");
/*     */     }
/*     */ 
/*     */     
/* 204 */     super.visitFrame(type, numLocal, local, numStack, stack);
/*     */     
/* 206 */     if (this.locals != null) {
/* 207 */       this.locals.clear();
/* 208 */       this.stack.clear();
/*     */     } else {
/* 210 */       this.locals = new ArrayList();
/* 211 */       this.stack = new ArrayList();
/*     */     } 
/* 213 */     visitFrameTypes(numLocal, local, this.locals);
/* 214 */     visitFrameTypes(numStack, stack, this.stack);
/* 215 */     this.maxLocals = Math.max(this.maxLocals, this.locals.size());
/* 216 */     this.maxStack = Math.max(this.maxStack, this.stack.size());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void visitFrameTypes(int numTypes, Object[] frameTypes, List<Object> result) {
/* 221 */     for (int i = 0; i < numTypes; i++) {
/* 222 */       Object frameType = frameTypes[i];
/* 223 */       result.add(frameType);
/* 224 */       if (frameType == Opcodes.LONG || frameType == Opcodes.DOUBLE) {
/* 225 */         result.add(Opcodes.TOP);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/* 232 */     super.visitInsn(opcode);
/* 233 */     execute(opcode, 0, (String)null);
/* 234 */     if ((opcode >= 172 && opcode <= 177) || opcode == 191) {
/* 235 */       this.locals = null;
/* 236 */       this.stack = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 242 */     super.visitIntInsn(opcode, operand);
/* 243 */     execute(opcode, operand, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int varIndex) {
/* 248 */     super.visitVarInsn(opcode, varIndex);
/* 249 */     boolean isLongOrDouble = (opcode == 22 || opcode == 24 || opcode == 55 || opcode == 57);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     this.maxLocals = Math.max(this.maxLocals, varIndex + (isLongOrDouble ? 2 : 1));
/* 255 */     execute(opcode, varIndex, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 260 */     if (opcode == 187) {
/* 261 */       if (this.labels == null) {
/* 262 */         Label label = new Label();
/* 263 */         this.labels = new ArrayList<Label>(3);
/* 264 */         this.labels.add(label);
/* 265 */         if (this.mv != null) {
/* 266 */           this.mv.visitLabel(label);
/*     */         }
/*     */       } 
/* 269 */       for (Label label : this.labels) {
/* 270 */         this.uninitializedTypes.put(label, type);
/*     */       }
/*     */     } 
/* 273 */     super.visitTypeInsn(opcode, type);
/* 274 */     execute(opcode, 0, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 280 */     super.visitFieldInsn(opcode, owner, name, descriptor);
/* 281 */     execute(opcode, 0, descriptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/* 291 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*     */       
/* 293 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */       return;
/*     */     } 
/* 296 */     super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/* 297 */     int opcode = opcodeAndSource & 0xFFFFFEFF;
/*     */     
/* 299 */     if (this.locals == null) {
/* 300 */       this.labels = null;
/*     */       return;
/*     */     } 
/* 303 */     pop(descriptor);
/* 304 */     if (opcode != 184) {
/* 305 */       Object value = pop();
/* 306 */       if (opcode == 183 && name.equals("<init>")) {
/*     */         Object initializedValue;
/* 308 */         if (value == Opcodes.UNINITIALIZED_THIS) {
/* 309 */           initializedValue = this.owner;
/*     */         } else {
/* 311 */           initializedValue = owner;
/*     */         }  int i;
/* 313 */         for (i = 0; i < this.locals.size(); i++) {
/* 314 */           if (this.locals.get(i) == value) {
/* 315 */             this.locals.set(i, initializedValue);
/*     */           }
/*     */         } 
/* 318 */         for (i = 0; i < this.stack.size(); i++) {
/* 319 */           if (this.stack.get(i) == value) {
/* 320 */             this.stack.set(i, initializedValue);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 325 */     pushDescriptor(descriptor);
/* 326 */     this.labels = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 335 */     super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
/* 336 */     if (this.locals == null) {
/* 337 */       this.labels = null;
/*     */       return;
/*     */     } 
/* 340 */     pop(descriptor);
/* 341 */     pushDescriptor(descriptor);
/* 342 */     this.labels = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 347 */     super.visitJumpInsn(opcode, label);
/* 348 */     execute(opcode, 0, (String)null);
/* 349 */     if (opcode == 167) {
/* 350 */       this.locals = null;
/* 351 */       this.stack = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/* 357 */     super.visitLabel(label);
/* 358 */     if (this.labels == null) {
/* 359 */       this.labels = new ArrayList<Label>(3);
/*     */     }
/* 361 */     this.labels.add(label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 366 */     super.visitLdcInsn(value);
/* 367 */     if (this.locals == null) {
/* 368 */       this.labels = null;
/*     */       return;
/*     */     } 
/* 371 */     if (value instanceof Integer) {
/* 372 */       push(Opcodes.INTEGER);
/* 373 */     } else if (value instanceof Long) {
/* 374 */       push(Opcodes.LONG);
/* 375 */       push(Opcodes.TOP);
/* 376 */     } else if (value instanceof Float) {
/* 377 */       push(Opcodes.FLOAT);
/* 378 */     } else if (value instanceof Double) {
/* 379 */       push(Opcodes.DOUBLE);
/* 380 */       push(Opcodes.TOP);
/* 381 */     } else if (value instanceof String) {
/* 382 */       push("java/lang/String");
/* 383 */     } else if (value instanceof Type) {
/* 384 */       int sort = ((Type)value).getSort();
/* 385 */       if (sort == 10 || sort == 9) {
/* 386 */         push("java/lang/Class");
/* 387 */       } else if (sort == 11) {
/* 388 */         push("java/lang/invoke/MethodType");
/*     */       } else {
/* 390 */         throw new IllegalArgumentException();
/*     */       } 
/* 392 */     } else if (value instanceof Handle) {
/* 393 */       push("java/lang/invoke/MethodHandle");
/* 394 */     } else if (value instanceof ConstantDynamic) {
/* 395 */       pushDescriptor(((ConstantDynamic)value).getDescriptor());
/*     */     } else {
/* 397 */       throw new IllegalArgumentException();
/*     */     } 
/* 399 */     this.labels = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int varIndex, int increment) {
/* 404 */     super.visitIincInsn(varIndex, increment);
/* 405 */     this.maxLocals = Math.max(this.maxLocals, varIndex + 1);
/* 406 */     execute(132, varIndex, (String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 412 */     super.visitTableSwitchInsn(min, max, dflt, labels);
/* 413 */     execute(170, 0, (String)null);
/* 414 */     this.locals = null;
/* 415 */     this.stack = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 420 */     super.visitLookupSwitchInsn(dflt, keys, labels);
/* 421 */     execute(171, 0, (String)null);
/* 422 */     this.locals = null;
/* 423 */     this.stack = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 428 */     super.visitMultiANewArrayInsn(descriptor, numDimensions);
/* 429 */     execute(197, numDimensions, descriptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 440 */     char firstDescriptorChar = descriptor.charAt(0);
/* 441 */     this
/* 442 */       .maxLocals = Math.max(this.maxLocals, index + ((
/* 443 */         firstDescriptorChar == 'J' || firstDescriptorChar == 'D') ? 2 : 1));
/* 444 */     super.visitLocalVariable(name, descriptor, signature, start, end, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 449 */     if (this.mv != null) {
/* 450 */       this.maxStack = Math.max(this.maxStack, maxStack);
/* 451 */       this.maxLocals = Math.max(this.maxLocals, maxLocals);
/* 452 */       this.mv.visitMaxs(this.maxStack, this.maxLocals);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Object get(int local) {
/* 459 */     this.maxLocals = Math.max(this.maxLocals, local + 1);
/* 460 */     return (local < this.locals.size()) ? this.locals.get(local) : Opcodes.TOP;
/*     */   }
/*     */   
/*     */   private void set(int local, Object type) {
/* 464 */     this.maxLocals = Math.max(this.maxLocals, local + 1);
/* 465 */     while (local >= this.locals.size()) {
/* 466 */       this.locals.add(Opcodes.TOP);
/*     */     }
/* 468 */     this.locals.set(local, type);
/*     */   }
/*     */   
/*     */   private void push(Object type) {
/* 472 */     this.stack.add(type);
/* 473 */     this.maxStack = Math.max(this.maxStack, this.stack.size());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void pushDescriptor(String fieldOrMethodDescriptor) {
/* 480 */     String descriptor = (fieldOrMethodDescriptor.charAt(0) == '(') ? Type.getReturnType(fieldOrMethodDescriptor).getDescriptor() : fieldOrMethodDescriptor;
/* 481 */     switch (descriptor.charAt(0)) {
/*     */       case 'V':
/*     */         return;
/*     */       case 'B':
/*     */       case 'C':
/*     */       case 'I':
/*     */       case 'S':
/*     */       case 'Z':
/* 489 */         push(Opcodes.INTEGER);
/*     */         return;
/*     */       case 'F':
/* 492 */         push(Opcodes.FLOAT);
/*     */         return;
/*     */       case 'J':
/* 495 */         push(Opcodes.LONG);
/* 496 */         push(Opcodes.TOP);
/*     */         return;
/*     */       case 'D':
/* 499 */         push(Opcodes.DOUBLE);
/* 500 */         push(Opcodes.TOP);
/*     */         return;
/*     */       case '[':
/* 503 */         push(descriptor);
/*     */         return;
/*     */       case 'L':
/* 506 */         push(descriptor.substring(1, descriptor.length() - 1));
/*     */         return;
/*     */     } 
/* 509 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */   
/*     */   private Object pop() {
/* 514 */     return this.stack.remove(this.stack.size() - 1);
/*     */   }
/*     */   
/*     */   private void pop(int numSlots) {
/* 518 */     int size = this.stack.size();
/* 519 */     int end = size - numSlots;
/* 520 */     for (int i = size - 1; i >= end; i--) {
/* 521 */       this.stack.remove(i);
/*     */     }
/*     */   }
/*     */   
/*     */   private void pop(String descriptor) {
/* 526 */     char firstDescriptorChar = descriptor.charAt(0);
/* 527 */     if (firstDescriptorChar == '(') {
/* 528 */       int numSlots = 0;
/* 529 */       Type[] types = Type.getArgumentTypes(descriptor);
/* 530 */       for (Type type : types) {
/* 531 */         numSlots += type.getSize();
/*     */       }
/* 533 */       pop(numSlots);
/* 534 */     } else if (firstDescriptorChar == 'J' || firstDescriptorChar == 'D') {
/* 535 */       pop(2);
/*     */     } else {
/* 537 */       pop(1);
/*     */     } 
/*     */   }
/*     */   private void execute(int opcode, int intArg, String stringArg) {
/*     */     Object value1, value2, value3, t4;
/* 542 */     if (opcode == 168 || opcode == 169) {
/* 543 */       throw new IllegalArgumentException("JSR/RET are not supported");
/*     */     }
/* 545 */     if (this.locals == null) {
/* 546 */       this.labels = null;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 553 */     switch (opcode) {
/*     */       case 0:
/*     */       case 116:
/*     */       case 117:
/*     */       case 118:
/*     */       case 119:
/*     */       case 145:
/*     */       case 146:
/*     */       case 147:
/*     */       case 167:
/*     */       case 177:
/*     */         break;
/*     */       case 1:
/* 566 */         push(Opcodes.NULL);
/*     */         break;
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 16:
/*     */       case 17:
/* 577 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 9:
/*     */       case 10:
/* 581 */         push(Opcodes.LONG);
/* 582 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/* 587 */         push(Opcodes.FLOAT);
/*     */         break;
/*     */       case 14:
/*     */       case 15:
/* 591 */         push(Opcodes.DOUBLE);
/* 592 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 21:
/*     */       case 23:
/*     */       case 25:
/* 597 */         push(get(intArg));
/*     */         break;
/*     */       case 22:
/*     */       case 24:
/* 601 */         push(get(intArg));
/* 602 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 47:
/*     */       case 143:
/* 606 */         pop(2);
/* 607 */         push(Opcodes.LONG);
/* 608 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 49:
/*     */       case 138:
/* 612 */         pop(2);
/* 613 */         push(Opcodes.DOUBLE);
/* 614 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 50:
/* 617 */         pop(1);
/* 618 */         value1 = pop();
/* 619 */         if (value1 instanceof String) {
/* 620 */           pushDescriptor(((String)value1).substring(1)); break;
/* 621 */         }  if (value1 == Opcodes.NULL) {
/* 622 */           push(value1); break;
/*     */         } 
/* 624 */         push("java/lang/Object");
/*     */         break;
/*     */       
/*     */       case 54:
/*     */       case 56:
/*     */       case 58:
/* 630 */         value1 = pop();
/* 631 */         set(intArg, value1);
/* 632 */         if (intArg > 0) {
/* 633 */           Object object = get(intArg - 1);
/* 634 */           if (object == Opcodes.LONG || object == Opcodes.DOUBLE) {
/* 635 */             set(intArg - 1, Opcodes.TOP);
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 55:
/*     */       case 57:
/* 641 */         pop(1);
/* 642 */         value1 = pop();
/* 643 */         set(intArg, value1);
/* 644 */         set(intArg + 1, Opcodes.TOP);
/* 645 */         if (intArg > 0) {
/* 646 */           Object object = get(intArg - 1);
/* 647 */           if (object == Opcodes.LONG || object == Opcodes.DOUBLE) {
/* 648 */             set(intArg - 1, Opcodes.TOP);
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 79:
/*     */       case 81:
/*     */       case 83:
/*     */       case 84:
/*     */       case 85:
/*     */       case 86:
/* 658 */         pop(3);
/*     */         break;
/*     */       case 80:
/*     */       case 82:
/* 662 */         pop(4);
/*     */         break;
/*     */       case 87:
/*     */       case 153:
/*     */       case 154:
/*     */       case 155:
/*     */       case 156:
/*     */       case 157:
/*     */       case 158:
/*     */       case 170:
/*     */       case 171:
/*     */       case 172:
/*     */       case 174:
/*     */       case 176:
/*     */       case 191:
/*     */       case 194:
/*     */       case 195:
/*     */       case 198:
/*     */       case 199:
/* 681 */         pop(1);
/*     */         break;
/*     */       case 88:
/*     */       case 159:
/*     */       case 160:
/*     */       case 161:
/*     */       case 162:
/*     */       case 163:
/*     */       case 164:
/*     */       case 165:
/*     */       case 166:
/*     */       case 173:
/*     */       case 175:
/* 694 */         pop(2);
/*     */         break;
/*     */       case 89:
/* 697 */         value1 = pop();
/* 698 */         push(value1);
/* 699 */         push(value1);
/*     */         break;
/*     */       case 90:
/* 702 */         value1 = pop();
/* 703 */         value2 = pop();
/* 704 */         push(value1);
/* 705 */         push(value2);
/* 706 */         push(value1);
/*     */         break;
/*     */       case 91:
/* 709 */         value1 = pop();
/* 710 */         value2 = pop();
/* 711 */         value3 = pop();
/* 712 */         push(value1);
/* 713 */         push(value3);
/* 714 */         push(value2);
/* 715 */         push(value1);
/*     */         break;
/*     */       case 92:
/* 718 */         value1 = pop();
/* 719 */         value2 = pop();
/* 720 */         push(value2);
/* 721 */         push(value1);
/* 722 */         push(value2);
/* 723 */         push(value1);
/*     */         break;
/*     */       case 93:
/* 726 */         value1 = pop();
/* 727 */         value2 = pop();
/* 728 */         value3 = pop();
/* 729 */         push(value2);
/* 730 */         push(value1);
/* 731 */         push(value3);
/* 732 */         push(value2);
/* 733 */         push(value1);
/*     */         break;
/*     */       case 94:
/* 736 */         value1 = pop();
/* 737 */         value2 = pop();
/* 738 */         value3 = pop();
/* 739 */         t4 = pop();
/* 740 */         push(value2);
/* 741 */         push(value1);
/* 742 */         push(t4);
/* 743 */         push(value3);
/* 744 */         push(value2);
/* 745 */         push(value1);
/*     */         break;
/*     */       case 95:
/* 748 */         value1 = pop();
/* 749 */         value2 = pop();
/* 750 */         push(value1);
/* 751 */         push(value2);
/*     */         break;
/*     */       case 46:
/*     */       case 51:
/*     */       case 52:
/*     */       case 53:
/*     */       case 96:
/*     */       case 100:
/*     */       case 104:
/*     */       case 108:
/*     */       case 112:
/*     */       case 120:
/*     */       case 122:
/*     */       case 124:
/*     */       case 126:
/*     */       case 128:
/*     */       case 130:
/*     */       case 136:
/*     */       case 142:
/*     */       case 149:
/*     */       case 150:
/* 772 */         pop(2);
/* 773 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 97:
/*     */       case 101:
/*     */       case 105:
/*     */       case 109:
/*     */       case 113:
/*     */       case 127:
/*     */       case 129:
/*     */       case 131:
/* 783 */         pop(4);
/* 784 */         push(Opcodes.LONG);
/* 785 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 48:
/*     */       case 98:
/*     */       case 102:
/*     */       case 106:
/*     */       case 110:
/*     */       case 114:
/*     */       case 137:
/*     */       case 144:
/* 795 */         pop(2);
/* 796 */         push(Opcodes.FLOAT);
/*     */         break;
/*     */       case 99:
/*     */       case 103:
/*     */       case 107:
/*     */       case 111:
/*     */       case 115:
/* 803 */         pop(4);
/* 804 */         push(Opcodes.DOUBLE);
/* 805 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 121:
/*     */       case 123:
/*     */       case 125:
/* 810 */         pop(3);
/* 811 */         push(Opcodes.LONG);
/* 812 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 132:
/* 815 */         set(intArg, Opcodes.INTEGER);
/*     */         break;
/*     */       case 133:
/*     */       case 140:
/* 819 */         pop(1);
/* 820 */         push(Opcodes.LONG);
/* 821 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 134:
/* 824 */         pop(1);
/* 825 */         push(Opcodes.FLOAT);
/*     */         break;
/*     */       case 135:
/*     */       case 141:
/* 829 */         pop(1);
/* 830 */         push(Opcodes.DOUBLE);
/* 831 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 139:
/*     */       case 190:
/*     */       case 193:
/* 836 */         pop(1);
/* 837 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 148:
/*     */       case 151:
/*     */       case 152:
/* 842 */         pop(4);
/* 843 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 178:
/* 846 */         pushDescriptor(stringArg);
/*     */         break;
/*     */       case 179:
/* 849 */         pop(stringArg);
/*     */         break;
/*     */       case 180:
/* 852 */         pop(1);
/* 853 */         pushDescriptor(stringArg);
/*     */         break;
/*     */       case 181:
/* 856 */         pop(stringArg);
/* 857 */         pop();
/*     */         break;
/*     */       case 187:
/* 860 */         push(this.labels.get(0));
/*     */         break;
/*     */       case 188:
/* 863 */         pop();
/* 864 */         switch (intArg) {
/*     */           case 4:
/* 866 */             pushDescriptor("[Z");
/*     */             break;
/*     */           case 5:
/* 869 */             pushDescriptor("[C");
/*     */             break;
/*     */           case 8:
/* 872 */             pushDescriptor("[B");
/*     */             break;
/*     */           case 9:
/* 875 */             pushDescriptor("[S");
/*     */             break;
/*     */           case 10:
/* 878 */             pushDescriptor("[I");
/*     */             break;
/*     */           case 6:
/* 881 */             pushDescriptor("[F");
/*     */             break;
/*     */           case 7:
/* 884 */             pushDescriptor("[D");
/*     */             break;
/*     */           case 11:
/* 887 */             pushDescriptor("[J");
/*     */             break;
/*     */         } 
/* 890 */         throw new IllegalArgumentException(stringConcat$0(intArg));
/*     */ 
/*     */       
/*     */       case 189:
/* 894 */         pop();
/* 895 */         pushDescriptor(stringConcat$1(String.valueOf(Type.getObjectType(stringArg))));
/*     */         break;
/*     */       case 192:
/* 898 */         pop();
/* 899 */         pushDescriptor(Type.getObjectType(stringArg).getDescriptor());
/*     */         break;
/*     */       case 197:
/* 902 */         pop(intArg);
/* 903 */         pushDescriptor(stringArg);
/*     */         break;
/*     */       default:
/* 906 */         throw new IllegalArgumentException(stringConcat$2(opcode));
/*     */     } 
/* 908 */     this.labels = null;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/commons/AnalyzerAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */