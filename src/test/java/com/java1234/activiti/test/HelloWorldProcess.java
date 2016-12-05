package com.java1234.activiti.test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorldProcess {
	/** 
	 * 获取默认的流程引擎实例 会自动读取activiti.cfg.xml文件  
	 */  
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();  
	
	
	/** 
	 * 部署流程定义 
	 */  
	//@Test  
	public void deploy(){  
	    // 获取部署对象  
	    Deployment deployment=processEngine.getRepositoryService() // 部署Service  
	                 .createDeployment()  // 创建部署  
	                 .addClasspathResource("diagrams/helloWorld.bpmn")  // 从classpath的资源中加载，一次只能加载一个文件  
	                 .addClasspathResource("diagrams/helloWorld.png")   // 从classpath的资源中加载，一次只能加载一个文件  
	                 .name("HelloWorld流程")  // 流程名称  
	                 .deploy(); // 完成部署  
	    System.out.println("流程部署ID:"+deployment.getId());  
	    System.out.println("流程部署Name:"+deployment.getName());  
	}  
	
	/** 
	 * 启动流程实例 
	 */  
	//@Test  
	public void start(){  
	    // 启动并获取流程实例  
	    ProcessInstance processInstance=processEngine.getRuntimeService() // 运行时流程实例Service  
	        .startProcessInstanceByKey("myFirstProcess"); // 数据库中流程定义表(act_re_prcdef)的KEY字段值；key对应对应 流程图里的process id的名字，使用Key值 启动，默认是按照最新版本的流程定义启动的    
	    System.out.println("流程实例ID:-->"+processInstance.getId());  
	    System.out.println("流程定义ID:-->"+processInstance.getProcessDefinitionId());  
	}  
	
	
	
	/** 
	 * 查看任务 
	 */  
	@Test  
	public void findTask(){  
	    // 查询并且返回任务即可  
	    List<Task> taskList=processEngine.getTaskService() // 任务相关Service  
	            .createTaskQuery()  // 创建任务查询  
	            .taskAssignee("java1234_小锋") // 指定某个人  
	            .list();   
	    for(Task task:taskList){  
	        System.out.println("任务ID:"+task.getId());  
	        System.out.println("任务名称："+task.getName());  
	        System.out.println("任务创建时间："+task.getCreateTime());  
	        System.out.println("任务委派人："+task.getAssignee());  
	        System.out.println("流程实例ID:"+task.getProcessInstanceId());  
	    }  
	}  
	
	
	/** 
	 * 完成任务 
	 */  
	//@Test  
	public void completeTask(){  
	    processEngine.getTaskService() // 任务相关Service  
	            .complete("2504"); // 指定要完成的任务ID  
	}  
	
	
	
	
	
	
}
