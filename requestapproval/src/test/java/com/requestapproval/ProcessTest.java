package com.requestapproval;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.jbpm.workflow.instance.WorkflowRuntimeException;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;

public class ProcessTest extends JbpmJUnitBaseTestCase {

	public void customerProductList() {
		testProcess("com/requestapproval/RequestApproval.bpmn2", "com.requestapproval.RequestApproval");
	}

	private void testProcess(String bpmnResource, String processId) {
		RuntimeManager manager = createRuntimeManager(bpmnResource);
		RuntimeEngine engine = getRuntimeEngine(null);

		try {
			KieSession ksession = engine.getKieSession();
			ksession.startProcess(processId);
		} finally {
			manager.disposeRuntimeEngine(engine);
			manager.close();
		}
	}

}