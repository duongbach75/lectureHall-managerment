package vn.savis.lhm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.dto.ServiceResponse;


@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	private Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	/* Handler error 405 - method not found */

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody
	ServiceResponse<?> handlerExceptionNotFound(NoHandlerFoundException ex) {

		LOGGER.error(ex.getMessage());

		ServiceResponse<?> response = new ServiceResponse<>();
		response.setData(null);
		response.setCode(Constants.ERR_CODE_NOT_FOUND);
		response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_NOT_FOUND);

		return response;
	}

	/* Handler error 405 - method not allowed */

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody
	ServiceResponse<?> handlerExceptionNotAllowed(HttpRequestMethodNotSupportedException ex) {

		LOGGER.error(ex.getMessage());

		ServiceResponse<?> response = new ServiceResponse<>();
		response.setData(null);
		response.setCode(Constants.ERR_CODE_METHOD_NOT_ALLOW);
		response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_METHOD_NOT_ALLOW);

		return response;
	}

	/* Handler error 400 - bad request when parameters are not correct */
	@ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
	ServiceResponse<?> handleMissingParameter(MissingServletRequestParameterException ex) {
		
		LOGGER.error(ex.getMessage());

		ServiceResponse<?> response = new ServiceResponse<>();
		response.setData(null);
		response.setCode(Constants.ERR_CODE_BAD_REQUEST);
		response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);

		return response;
    }
}
