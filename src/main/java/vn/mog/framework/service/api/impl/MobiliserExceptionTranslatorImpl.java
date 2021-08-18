package vn.mog.framework.service.api.impl;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.mog.framework.service.api.IMobiliserExceptionTranslator;
import vn.mog.framework.service.api.MobiliserServiceException;

@Service
public final class MobiliserExceptionTranslatorImpl implements IMobiliserExceptionTranslator, InitializingBean {
	private static final Logger logger = LogManager.getLogger(MobiliserExceptionTranslatorImpl.class);
	private AtomicInteger defaultErrorCode = new AtomicInteger(9999);
	private AtomicReference<Map<String, ?>> exceptionMapping = new AtomicReference<Map<String, ?>>();

	@Override
	public void afterPropertiesSet() {
		if (this.defaultErrorCode.get() == 0)
			throw new IllegalStateException("returnCode is required");
	}

	@Override
	public int getDefaultErrorCode() {
		return this.defaultErrorCode.get();
	}

	@Override
	public IMobiliserExceptionTranslator.TranslationResult translate(Throwable e, Long callerId) {

		@SuppressWarnings("rawtypes")
		Class exceptionClass = e.getClass();

		if (logger.isTraceEnabled()) {
			logger.trace("Trying to find error code for exception of type [" + exceptionClass.getName() + "]");
		}

		if (e instanceof MobiliserServiceException) {
			if (logger.isTraceEnabled()) {
				logger.trace("exception of type [" + exceptionClass.getName()
						+ "] is a MobiliserServiceException, using its error code");
			}

			MobiliserServiceException mobServException = (MobiliserServiceException) e;

			return new IMobiliserExceptionTranslator.TranslationResult(mobServException.getMessage(),
					mobServException.getErrorCode());
		}

		Map<String, ?> mappings = this.exceptionMapping.get();

		if (mappings == null) {
			if (logger.isTraceEnabled()) {
				logger.trace("No mappings registered, returning default error code");
			}

			return new IMobiliserExceptionTranslator.TranslationResult(null, this.defaultErrorCode.get());
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Trying to find error code for exception of type [" + exceptionClass.getName() + "]");
		}

		Object mapping = mappings.get(exceptionClass.getName());
		while ((mapping == null) && (!(exceptionClass.equals(Throwable.class)))) {
			exceptionClass = exceptionClass.getSuperclass();
			mapping = mappings.get(exceptionClass.getName());
		}

		if (mapping == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("No error code for exception of type [" + exceptionClass.getName()
						+ "]: found, returning default.");
			}

			return new IMobiliserExceptionTranslator.TranslationResult(null, this.defaultErrorCode.get());
		}

		if ((mapping instanceof String) && (!(StringUtils.hasText((String) mapping)))) {
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Found error code for exception of type [" + exceptionClass.getName() + "]: " + mapping);
		}

		return new IMobiliserExceptionTranslator.TranslationResult(null, Integer.parseInt((String) mapping));
	}

	public void updateCallback(Map<String, ?> properties) {
		this.exceptionMapping.set(properties);
	}
}
