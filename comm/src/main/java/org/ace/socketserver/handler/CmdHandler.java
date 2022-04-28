package org.ace.socketserver.handler;


import org.ace.socketserver.dto.result.CmdExeResult;
import org.ace.socketserver.dto.result.JsonMsg;

public interface CmdHandler
{
    CmdExeResult execute(JsonMsg jsonMsg);
}
