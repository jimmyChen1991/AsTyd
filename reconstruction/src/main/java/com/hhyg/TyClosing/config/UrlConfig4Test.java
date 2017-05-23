package com.hhyg.TyClosing.config;

public class UrlConfig4Test implements NetrulConfig{
	@Override
	public String getIndexUrl() {
		return "http://v5commonapi.mianshui365.net/index.php";
	}

	@Override
	public String getServiceUrl() {
		return "http://v5.mianshui365.net/api/MSService.php";
		
	}
}
