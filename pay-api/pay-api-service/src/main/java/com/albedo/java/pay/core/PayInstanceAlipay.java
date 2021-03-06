package com.albedo.java.pay.core;

import com.albedo.java.pay.util.PayUtil;
import com.albedo.java.pay.vo.PayCreate;
import com.albedo.java.common.pay.core.PayInstance;
import com.albedo.java.common.pay.core.param.PayAlipayParam;
import com.albedo.java.common.pay.core.vo.Alipay;

/**
 * Created by lijie on 2017/5/3.
 */
public class PayInstanceAlipay implements PayInstance {

	public static final PayInstanceAlipay I = new PayInstanceAlipay();

	@Override
	public String genParams(PayCreate payCreate, String domain) {
		PayAlipayParam payAlipayParam = PayUtil.findParamsByClass(payCreate.getBizType(), payCreate.getPayType(),
				PayAlipayParam.class);
		Alipay alipay = new Alipay(payAlipayParam, domain);
		alipay.setOut_trade_no(payCreate.getBizCode());
		alipay.setSubject(payCreate.getSubject());
		alipay.setTotal_fee(payCreate.getAmount());
		alipay.setBody(payCreate.getSubject());
		return alipay.buildRequestPara();
	}
}
