package com.easyservice.exception;

import com.easyservice.support.ServiceResponse.ExceptionType;

public class ProtocolParseException extends SuperException{
		public ProtocolParseException()
		{
			super();
		}
		public ProtocolParseException(String message)
		{
			super(message);
		}
		public ProtocolParseException(Throwable e,ExceptionType type)
		{
			super(e, type);
		}
}
