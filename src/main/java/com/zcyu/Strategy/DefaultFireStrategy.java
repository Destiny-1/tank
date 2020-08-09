package com.zcyu.Strategy;

public class DefaultFireStrategy implements FireStrategy {
    private DefaultFireStrategy(){}

    private static class defaultFireStrategyHandler {
        private static final DefaultFireStrategy defaultFireStrategy = new DefaultFireStrategy();
    }

    public static DefaultFireStrategy getDefaultFireStrategy(){
        return DefaultFireStrategy.defaultFireStrategyHandler.defaultFireStrategy;
    }

}
