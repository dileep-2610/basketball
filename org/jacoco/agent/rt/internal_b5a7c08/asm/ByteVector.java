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
/*     */ public class ByteVector
/*     */ {
/*     */   byte[] data;
/*     */   int length;
/*     */   
/*     */   public ByteVector() {
/*  46 */     this.data = new byte[64];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteVector(int initialCapacity) {
/*  55 */     this.data = new byte[initialCapacity];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ByteVector(byte[] data) {
/*  64 */     this.data = data;
/*  65 */     this.length = data.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  74 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteVector putByte(int byteValue) {
/*  84 */     int currentLength = this.length;
/*  85 */     if (currentLength + 1 > this.data.length) {
/*  86 */       enlarge(1);
/*     */     }
/*  88 */     this.data[currentLength++] = (byte)byteValue;
/*  89 */     this.length = currentLength;
/*  90 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final ByteVector put11(int byteValue1, int byteValue2) {
/* 101 */     int currentLength = this.length;
/* 102 */     if (currentLength + 2 > this.data.length) {
/* 103 */       enlarge(2);
/*     */     }
/* 105 */     byte[] currentData = this.data;
/* 106 */     currentData[currentLength++] = (byte)byteValue1;
/* 107 */     currentData[currentLength++] = (byte)byteValue2;
/* 108 */     this.length = currentLength;
/* 109 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteVector putShort(int shortValue) {
/* 119 */     int currentLength = this.length;
/* 120 */     if (currentLength + 2 > this.data.length) {
/* 121 */       enlarge(2);
/*     */     }
/* 123 */     byte[] currentData = this.data;
/* 124 */     currentData[currentLength++] = (byte)(shortValue >>> 8);
/* 125 */     currentData[currentLength++] = (byte)shortValue;
/* 126 */     this.length = currentLength;
/* 127 */     return this;
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
/*     */   final ByteVector put12(int byteValue, int shortValue) {
/* 139 */     int currentLength = this.length;
/* 140 */     if (currentLength + 3 > this.data.length) {
/* 141 */       enlarge(3);
/*     */     }
/* 143 */     byte[] currentData = this.data;
/* 144 */     currentData[currentLength++] = (byte)byteValue;
/* 145 */     currentData[currentLength++] = (byte)(shortValue >>> 8);
/* 146 */     currentData[currentLength++] = (byte)shortValue;
/* 147 */     this.length = currentLength;
/* 148 */     return this;
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
/*     */   final ByteVector put112(int byteValue1, int byteValue2, int shortValue) {
/* 161 */     int currentLength = this.length;
/* 162 */     if (currentLength + 4 > this.data.length) {
/* 163 */       enlarge(4);
/*     */     }
/* 165 */     byte[] currentData = this.data;
/* 166 */     currentData[currentLength++] = (byte)byteValue1;
/* 167 */     currentData[currentLength++] = (byte)byteValue2;
/* 168 */     currentData[currentLength++] = (byte)(shortValue >>> 8);
/* 169 */     currentData[currentLength++] = (byte)shortValue;
/* 170 */     this.length = currentLength;
/* 171 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteVector putInt(int intValue) {
/* 181 */     int currentLength = this.length;
/* 182 */     if (currentLength + 4 > this.data.length) {
/* 183 */       enlarge(4);
/*     */     }
/* 185 */     byte[] currentData = this.data;
/* 186 */     currentData[currentLength++] = (byte)(intValue >>> 24);
/* 187 */     currentData[currentLength++] = (byte)(intValue >>> 16);
/* 188 */     currentData[currentLength++] = (byte)(intValue >>> 8);
/* 189 */     currentData[currentLength++] = (byte)intValue;
/* 190 */     this.length = currentLength;
/* 191 */     return this;
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
/*     */   final ByteVector put122(int byteValue, int shortValue1, int shortValue2) {
/* 204 */     int currentLength = this.length;
/* 205 */     if (currentLength + 5 > this.data.length) {
/* 206 */       enlarge(5);
/*     */     }
/* 208 */     byte[] currentData = this.data;
/* 209 */     currentData[currentLength++] = (byte)byteValue;
/* 210 */     currentData[currentLength++] = (byte)(shortValue1 >>> 8);
/* 211 */     currentData[currentLength++] = (byte)shortValue1;
/* 212 */     currentData[currentLength++] = (byte)(shortValue2 >>> 8);
/* 213 */     currentData[currentLength++] = (byte)shortValue2;
/* 214 */     this.length = currentLength;
/* 215 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteVector putLong(long longValue) {
/* 225 */     int currentLength = this.length;
/* 226 */     if (currentLength + 8 > this.data.length) {
/* 227 */       enlarge(8);
/*     */     }
/* 229 */     byte[] currentData = this.data;
/* 230 */     int intValue = (int)(longValue >>> 32L);
/* 231 */     currentData[currentLength++] = (byte)(intValue >>> 24);
/* 232 */     currentData[currentLength++] = (byte)(intValue >>> 16);
/* 233 */     currentData[currentLength++] = (byte)(intValue >>> 8);
/* 234 */     currentData[currentLength++] = (byte)intValue;
/* 235 */     intValue = (int)longValue;
/* 236 */     currentData[currentLength++] = (byte)(intValue >>> 24);
/* 237 */     currentData[currentLength++] = (byte)(intValue >>> 16);
/* 238 */     currentData[currentLength++] = (byte)(intValue >>> 8);
/* 239 */     currentData[currentLength++] = (byte)intValue;
/* 240 */     this.length = currentLength;
/* 241 */     return this;
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
/*     */   public ByteVector putUTF8(String stringValue) {
/* 253 */     int charLength = stringValue.length();
/* 254 */     if (charLength > 65535) {
/* 255 */       throw new IllegalArgumentException("UTF8 string too large");
/*     */     }
/* 257 */     int currentLength = this.length;
/* 258 */     if (currentLength + 2 + charLength > this.data.length) {
/* 259 */       enlarge(2 + charLength);
/*     */     }
/* 261 */     byte[] currentData = this.data;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     currentData[currentLength++] = (byte)(charLength >>> 8);
/* 267 */     currentData[currentLength++] = (byte)charLength;
/* 268 */     for (int i = 0; i < charLength; i++) {
/* 269 */       char charValue = stringValue.charAt(i);
/* 270 */       if (charValue >= '\001' && charValue <= '') {
/* 271 */         currentData[currentLength++] = (byte)charValue;
/*     */       } else {
/* 273 */         this.length = currentLength;
/* 274 */         return encodeUtf8(stringValue, i, 65535);
/*     */       } 
/*     */     } 
/* 277 */     this.length = currentLength;
/* 278 */     return this;
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
/*     */   final ByteVector encodeUtf8(String stringValue, int offset, int maxByteLength) {
/* 294 */     int charLength = stringValue.length();
/* 295 */     int byteLength = offset;
/* 296 */     for (int i = offset; i < charLength; i++) {
/* 297 */       char charValue = stringValue.charAt(i);
/* 298 */       if (charValue >= '\001' && charValue <= '') {
/* 299 */         byteLength++;
/* 300 */       } else if (charValue <= '߿') {
/* 301 */         byteLength += 2;
/*     */       } else {
/* 303 */         byteLength += 3;
/*     */       } 
/*     */     } 
/* 306 */     if (byteLength > maxByteLength) {
/* 307 */       throw new IllegalArgumentException("UTF8 string too large");
/*     */     }
/*     */     
/* 310 */     int byteLengthOffset = this.length - offset - 2;
/* 311 */     if (byteLengthOffset >= 0) {
/* 312 */       this.data[byteLengthOffset] = (byte)(byteLength >>> 8);
/* 313 */       this.data[byteLengthOffset + 1] = (byte)byteLength;
/*     */     } 
/* 315 */     if (this.length + byteLength - offset > this.data.length) {
/* 316 */       enlarge(byteLength - offset);
/*     */     }
/* 318 */     int currentLength = this.length;
/* 319 */     for (int j = offset; j < charLength; j++) {
/* 320 */       char charValue = stringValue.charAt(j);
/* 321 */       if (charValue >= '\001' && charValue <= '') {
/* 322 */         this.data[currentLength++] = (byte)charValue;
/* 323 */       } else if (charValue <= '߿') {
/* 324 */         this.data[currentLength++] = (byte)(0xC0 | charValue >> 6 & 0x1F);
/* 325 */         this.data[currentLength++] = (byte)(0x80 | charValue & 0x3F);
/*     */       } else {
/* 327 */         this.data[currentLength++] = (byte)(0xE0 | charValue >> 12 & 0xF);
/* 328 */         this.data[currentLength++] = (byte)(0x80 | charValue >> 6 & 0x3F);
/* 329 */         this.data[currentLength++] = (byte)(0x80 | charValue & 0x3F);
/*     */       } 
/*     */     } 
/* 332 */     this.length = currentLength;
/* 333 */     return this;
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
/*     */   public ByteVector putByteArray(byte[] byteArrayValue, int byteOffset, int byteLength) {
/* 348 */     if (this.length + byteLength > this.data.length) {
/* 349 */       enlarge(byteLength);
/*     */     }
/* 351 */     if (byteArrayValue != null) {
/* 352 */       System.arraycopy(byteArrayValue, byteOffset, this.data, this.length, byteLength);
/*     */     }
/* 354 */     this.length += byteLength;
/* 355 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void enlarge(int size) {
/* 364 */     if (this.length > this.data.length) {
/* 365 */       throw new AssertionError("Internal error");
/*     */     }
/* 367 */     int doubleCapacity = 2 * this.data.length;
/* 368 */     int minimalCapacity = this.length + size;
/* 369 */     byte[] newData = new byte[(doubleCapacity > minimalCapacity) ? doubleCapacity : minimalCapacity];
/* 370 */     System.arraycopy(this.data, 0, newData, 0, this.length);
/* 371 */     this.data = newData;
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/asm/ByteVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */