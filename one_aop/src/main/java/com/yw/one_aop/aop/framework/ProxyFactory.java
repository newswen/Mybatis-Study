package com.yw.one_aop.aop.framework;

import com.yw.one_aop.aop.AdviceSupport;

/**
 * 代理类工厂
 *
 * @author: yuanwen
 * @since: 2024/10/22
 */
public class ProxyFactory {

    private final AdviceSupport adviceSupport;

    public ProxyFactory(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

    public AopProxy getProxy() {
        return adviceSupport.isProxyType() ? new CGLibProxy(adviceSupport) : new JDKProxy(adviceSupport);
    }

    public void setProxyType(boolean proxyType) {
        adviceSupport.setProxyType(proxyType);
    }

}
