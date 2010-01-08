// created by mlrain on 2007-10-8 下午04:04:17
package net.oliver.olframework.commu.sock;

/**
 * 通讯所使用的常量。
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-8 下午04:04:20
 */
public interface CommucationConstants
{
    /** *********************上下文KEY（开始）****************************** */
    /**
     * 上下文信息 - 服务器信息 - IP地址
     */
    public static final String CONTEXT_SERVER_IP = "server.ip";

    /**
     * 上下文信息 - 服务器信息 - 端口
     */
    public static final String CONTEXT_SERVER_PORT = "server.port";

    /**
     * 上下文信息 - 服务器信息 - 通讯超时时间
     */
    public static final String CONTEXT_SERVER_TIMEOUT = "server.timeout";

    /**
     * 上下文信息 - 服务器动作 - 接口调用 - 请求报文体
     */
    public static final String CONTEXT_WORKER_REQUEST_BODY = "worker.request.body";

    /**
     * 上下文信息 - 服务器动作 - 接口调用 - 完整的请求报文
     */
    public static final String CONTEXT_WORKER_REQUEST_MESSAGE = "worker.request.message";

    /**
     * 上下文信息 - 服务器动作 - 接口调用 - 交易代码
     */
    public static final String CONTEXT_WORKER_TRADECODE = "worker.tradecode";

    /**
     * 上下文信息 - 服务器动作 - 接口调用 - 额外参数（除必须参数外的其他参数）
     */
    public static final String CONTEXT_WORKER_HEADER_PARAM_EX = "worker.paramex";

    /**
     * 上下文信息 - 服务器返回信息 - 成功标志
     */
    public static final String CONTEXT_RET_SUCCESS = "return.success";

    /**
     * 上下文信息 - 服务器返回信息 - 描述信息
     */
    public static final String CONTEXT_RET_DESC = "return.description";

    /**
     * 上下文信息 - 服务器动作 - 接口调用 - 响应报文体
     */
    public static final String CONTEXT_RET_BODY = "worker.response.body";

    /**
     * 上下文信息 - 服务器动作 - 接口调用 - 响应报文
     */
    public static final String CONTEXT_RET_MESSAGE = "worker.response.message";

    /**
     * 上下文信息 - 服务器返回信息 - 错误代码
     */
    public static final String CONTEXT_RET_ERROR_CODE = "return.error.code";

    /**
     * 上下文信息 - 服务器返回信息 - 错误信息
     */
    public static final String CONTEXT_RET_ERROR_DESC = "return.error.description";

    /** *********************上下文KEY（结束）****************************** */

    /** *********************常量（开始）****************************** */
    /**
     * Socket通讯超时时间（毫秒）
     */
    public static final int COMMUCATION_TIMEOUT_DEFAULT = 30000;

    /** *********************常量（结束）****************************** */

    /** *********************服务器动作名称常量（开始）****************************** */
    /**
     * 服务器动作名称 - 交易调用
     */
    public static final String WORKER_NAME_TRADE_INVOKER = "TradeInvoker";
    /** *********************服务器动作名称常量（结束）****************************** */
}
