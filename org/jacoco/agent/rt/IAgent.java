package org.jacoco.agent.rt;

import java.io.IOException;

public interface IAgent {
  String getVersion();
  
  String getSessionId();
  
  void setSessionId(String paramString);
  
  void reset();
  
  byte[] getExecutionData(boolean paramBoolean);
  
  void dump(boolean paramBoolean) throws IOException;
}


/* Location:              /Users/mac/Downloads/jacocoagent.jar!/org/jacoco/agent/rt/IAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */