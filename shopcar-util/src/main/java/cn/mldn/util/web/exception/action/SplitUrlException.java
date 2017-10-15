package cn.mldn.util.web.exception.action;

import cn.mldn.util.web.exception.DispatcherException;

@SuppressWarnings("serial")
public class SplitUrlException extends DispatcherException {

	public SplitUrlException(String msg) {
		super(msg);
	}

}
