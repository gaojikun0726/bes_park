package org.ace.socketserver.handler;

import org.ace.socketserver.dto.result.CmdExeResult;

public interface ResultSendable
{
    void send(CmdExeResult result);
}
