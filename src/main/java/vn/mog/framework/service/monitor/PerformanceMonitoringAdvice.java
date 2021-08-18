package vn.mog.framework.service.monitor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Service
public class PerformanceMonitoringAdvice implements MethodInterceptor {
	private static Logger logger = LogManager.getLogger(PerformanceMonitoringAdvice.class);

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		long startTime = System.currentTimeMillis();
		try {
			Object result = methodInvocation.proceed();
			return result;
		} finally {
			@SuppressWarnings("rawtypes")
			Class returnType = methodInvocation.getMethod().getReturnType();
			if (ClassUtils.isAssignable(MobiliserResponseType.class, returnType)) {
				long endTime = System.currentTimeMillis();
				logger.info("Total time taken in ms : " + (endTime - startTime));
			}
		}
	}
}
