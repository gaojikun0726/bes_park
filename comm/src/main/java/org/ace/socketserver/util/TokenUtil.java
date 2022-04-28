package org.ace.socketserver.util;


import org.ace.socketserver.handler.TokenSupport;

import java.util.ServiceLoader;
import java.util.logging.Logger;

public class TokenUtil
{
    private static final Logger log = Logger.getLogger(TokenUtil.class.getName());

    private static TokenSupport tokenSupport;

    /**
     * 判断 tokenSN 是否存在
     *
     * @param tokenSN
     * @return
     */
    public static boolean isTokenExist(String tokenSN)
    {
        if (tokenSN == null || tokenSN.isEmpty())
        {
            log.warning("Parameter Error. tokenSN id is null.");
            return false;
        }

        if (null != tokenSupport)
        {
            return tokenSupport.isTokenExist(tokenSN);
        }

        // 判断tokenSN是否存在
        ServiceLoader<TokenSupport> load = ServiceLoader.load(TokenSupport.class);

        for (TokenSupport item : load)
        {
            tokenSupport = item;
            return item.isTokenExist(tokenSN);
        }

        return false;
    }

}
