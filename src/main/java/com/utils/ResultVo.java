package com.utils;

import lombok.Getter;
import lombok.Setter;

public class ResultVo {
	
	@Setter
	@Getter
	private int count = 0;
	
	@Setter
	@Getter
	private Object data = null;
	
	@Setter
	@Getter
	private boolean flag = false;
	
	@Setter
	@Getter
	private String msg = null;
	
}
