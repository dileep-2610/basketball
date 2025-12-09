/*     */ package org.jacoco.agent.rt.internal_b5a7c08;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.util.concurrent.Callable;
/*     */ import org.jacoco.agent.rt.IAgent;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.JaCoCo;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ExecutionDataWriter;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.IExecutionDataVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.data.ISessionInfoVisitor;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.AbstractRuntime;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.AgentOptions;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.RuntimeData;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.output.FileOutput;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.output.IAgentOutput;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.output.NoneOutput;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.output.TcpClientOutput;
/*     */ import org.jacoco.agent.rt.internal_b5a7c08.output.TcpServerOutput;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Agent
/*     */   implements IAgent
/*     */ {
/*     */   private static Agent singleton;
/*     */   private final AgentOptions options;
/*     */   private final IExceptionLogger logger;
/*     */   private final RuntimeData data;
/*     */   private IAgentOutput output;
/*     */   private Callable<Void> jmxRegistration;
/*     */   
/*     */   public static synchronized Agent getInstance(AgentOptions options) throws Exception {
/*  52 */     if (singleton == null) {
/*  53 */       final Agent agent = new Agent(options, IExceptionLogger.SYSTEM_ERR);
/*  54 */       agent.startup();
/*  55 */       Runtime.getRuntime().addShutdownHook(new Thread()
/*     */           {
/*     */             public void run() {
/*  58 */               agent.shutdown();
/*     */             }
/*     */           });
/*  61 */       singleton = agent;
/*     */     } 
/*  63 */     return singleton;
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
/*     */   public static synchronized Agent getInstance() throws IllegalStateException {
/*  76 */     if (singleton == null) {
/*  77 */       throw new IllegalStateException("JaCoCo agent not started.");
/*     */     }
/*  79 */     return singleton;
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
/*     */   Agent(AgentOptions options, IExceptionLogger logger) {
/* 101 */     this.options = options;
/* 102 */     this.logger = logger;
/* 103 */     this.data = new RuntimeData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuntimeData getData() {
/* 112 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startup() throws Exception {
/*     */     try {
/* 123 */       String sessionId = this.options.getSessionId();
/* 124 */       if (sessionId == null) {
/* 125 */         sessionId = createSessionId();
/*     */       }
/* 127 */       this.data.setSessionId(sessionId);
/* 128 */       this.output = createAgentOutput();
/* 129 */       this.output.startup(this.options, this.data);
/* 130 */       if (this.options.getJmx()) {
/* 131 */         this.jmxRegistration = new JmxRegistration(this);
/*     */       }
/* 133 */     } catch (Exception e) {
/* 134 */       this.logger.logExeption(e);
/* 135 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() {
/*     */     try {
/* 144 */       if (this.options.getDumpOnExit()) {
/* 145 */         this.output.writeExecutionData(false);
/*     */       }
/* 147 */       this.output.shutdown();
/* 148 */       if (this.jmxRegistration != null) {
/* 149 */         this.jmxRegistration.call();
/*     */       }
/* 151 */     } catch (Exception e) {
/* 152 */       this.logger.logExeption(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   IAgentOutput createAgentOutput() {
/* 162 */     AgentOptions.OutputMode controllerType = this.options.getOutput();
/* 163 */     switch (controllerType) {
/*     */       case file:
/* 165 */         return (IAgentOutput)new FileOutput();
/*     */       case tcpserver:
/* 167 */         return (IAgentOutput)new TcpServerOutput(this.logger);
/*     */       case tcpclient:
/* 169 */         return (IAgentOutput)new TcpClientOutput(this.logger);
/*     */       case none:
/* 171 */         return (IAgentOutput)new NoneOutput();
/*     */     } 
/* 173 */     throw new AssertionError(controllerType);
/*     */   }
/*     */ 
/*     */   
/*     */   private String createSessionId() {
/*     */     String host;
/*     */     try {
/* 180 */       host = InetAddress.getLocalHost().getHostName();
/* 181 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 184 */       host = "unknownhost";
/*     */     } 
/* 186 */     return host + "-" + AbstractRuntime.createRandomId();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 192 */     return JaCoCo.VERSION;
/*     */   }
/*     */   
/*     */   public String getSessionId() {
/* 196 */     return this.data.getSessionId();
/*     */   }
/*     */   
/*     */   public void setSessionId(String id) {
/* 200 */     this.data.setSessionId(id);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 204 */     this.data.reset();
/*     */   }
/*     */   
/*     */   public byte[] getExecutionData(boolean reset) {
/* 208 */     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/*     */     try {
/* 210 */       ExecutionDataWriter writer = new ExecutionDataWriter(buffer);
/* 211 */       this.data.collect((IExecutionDataVisitor)writer, (ISessionInfoVisitor)writer, reset);
/* 212 */     } catch (IOException e) {
/*     */       
/* 214 */       throw new AssertionError(e);
/*     */     } 
/* 216 */     return buffer.toByteArray();
/*     */   }
/*     */   
/*     */   public void dump(boolean reset) throws IOException {
/* 220 */     this.output.writeExecutionData(reset);
/*     */   }
/*     */ }


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */