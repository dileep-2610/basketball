package org.jacoco.agent.rt.internal_b5a7c08.output;

import java.io.IOException;
import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_b5a7c08.core.runtime.RuntimeData;

public interface IAgentOutput {
  void startup(AgentOptions paramAgentOptions, RuntimeData paramRuntimeData) throws Exception;
  
  void shutdown() throws Exception;
  
  void writeExecutionData(boolean paramBoolean) throws IOException;
}


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/internal_b5a7c08/output/IAgentOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */