package com.alipay.demo.trade.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.demo.trade.model.builder.RequestBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuyangkly on 15/10/22.
 */
abstract class AbsAlipayService {
    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected AlipayClient client ;

    /**
     * 验证bizContent对象
     * @param builder
     */
    protected void validateBuilder(RequestBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder should not be NULL!");
        }

        if (!builder.validate()) {
            throw new IllegalStateException("builder validate failed! " + builder.toString());
        }
    }

    /**
     * 调用AlipayClient的execute方法，进行远程调用
     * @param request
     * @return
     */
    protected AlipayResponse getResponse(AlipayRequest request) {
        try {
            AlipayResponse response = client.execute(request);
            if (response != null) {
                log.info(response.getBody());
            }
            return response;

        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}