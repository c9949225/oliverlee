// created by mlrain on 2007-10-8 ����04:04:17
package net.oliver.olframework.commu.sock;

/**
 * ͨѶ��ʹ�õĳ�����
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-8 ����04:04:20
 */
public interface CommucationConstants
{
    /** *********************������KEY����ʼ��****************************** */
    /**
     * ��������Ϣ - ��������Ϣ - IP��ַ
     */
    public static final String CONTEXT_SERVER_IP = "server.ip";

    /**
     * ��������Ϣ - ��������Ϣ - �˿�
     */
    public static final String CONTEXT_SERVER_PORT = "server.port";

    /**
     * ��������Ϣ - ��������Ϣ - ͨѶ��ʱʱ��
     */
    public static final String CONTEXT_SERVER_TIMEOUT = "server.timeout";

    /**
     * ��������Ϣ - ���������� - �ӿڵ��� - ��������
     */
    public static final String CONTEXT_WORKER_REQUEST_BODY = "worker.request.body";

    /**
     * ��������Ϣ - ���������� - �ӿڵ��� - ������������
     */
    public static final String CONTEXT_WORKER_REQUEST_MESSAGE = "worker.request.message";

    /**
     * ��������Ϣ - ���������� - �ӿڵ��� - ���״���
     */
    public static final String CONTEXT_WORKER_TRADECODE = "worker.tradecode";

    /**
     * ��������Ϣ - ���������� - �ӿڵ��� - �������������������������������
     */
    public static final String CONTEXT_WORKER_HEADER_PARAM_EX = "worker.paramex";

    /**
     * ��������Ϣ - ������������Ϣ - �ɹ���־
     */
    public static final String CONTEXT_RET_SUCCESS = "return.success";

    /**
     * ��������Ϣ - ������������Ϣ - ������Ϣ
     */
    public static final String CONTEXT_RET_DESC = "return.description";

    /**
     * ��������Ϣ - ���������� - �ӿڵ��� - ��Ӧ������
     */
    public static final String CONTEXT_RET_BODY = "worker.response.body";

    /**
     * ��������Ϣ - ���������� - �ӿڵ��� - ��Ӧ����
     */
    public static final String CONTEXT_RET_MESSAGE = "worker.response.message";

    /**
     * ��������Ϣ - ������������Ϣ - �������
     */
    public static final String CONTEXT_RET_ERROR_CODE = "return.error.code";

    /**
     * ��������Ϣ - ������������Ϣ - ������Ϣ
     */
    public static final String CONTEXT_RET_ERROR_DESC = "return.error.description";

    /** *********************������KEY��������****************************** */

    /** *********************��������ʼ��****************************** */
    /**
     * SocketͨѶ��ʱʱ�䣨���룩
     */
    public static final int COMMUCATION_TIMEOUT_DEFAULT = 30000;

    /** *********************������������****************************** */

    /** *********************�������������Ƴ�������ʼ��****************************** */
    /**
     * �������������� - ���׵���
     */
    public static final String WORKER_NAME_TRADE_INVOKER = "TradeInvoker";
    /** *********************�������������Ƴ�����������****************************** */
}
