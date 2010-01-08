package net.oliver.olframework.jni.reparent;

public class ReparentUtil {
	static{
		System.loadLibrary("reparent");
	}
	
	/**
	 * 1. ���� Windows Ӧ�ó���
	 * 2. ��ȡ Windows Ӧ�ó���������ھ����
	 * 3. �� Windows Ӧ�ó�������������ó�ָ�����ڵ��Ӵ��ڡ� 
	 * 
	 * @param parentWnd �����ھ��
	 * @param command ���������������Ӧ�ó����·��
	 * @param wndClass ��������������
	 * @return
	 */
	public static native int startAndReparent(int parentWnd,String command,String wndClass);
	
	/*
	// ����Ӧ�ó����ʵ��
	
	JNIEXPORT jint JNICALL Java_net_oliver_olframework_jni_reparent_ReparentUtil_startAndReparent
     (JNIEnv *env, jclass classobj, jint parent, jstring command,jstring wndClass){
     
	jboolean isCopy=FALSE;
	PROCESS_INFORMATION   pInfo;   
	STARTUPINFO                   sInfo; 

	int hParentWnd;
	// ������ת��Ϊc
	jsize len = ( *env ).GetStringLength(command);
	const jchar *commandstr = (*env).GetStringChars(command,&isCopy);
	const jchar *wndClassStr = NULL;
	char commandcstr[200];
	int size = 0;
	size = WideCharToMultiByte( CP_ACP, 0, (LPCWSTR)commandstr, 
			len, commandcstr,(len*2+1), NULL, NULL );
	(*env).ReleaseStringChars(command, commandstr);
	if(size==0){
		return 0;
	}
	commandcstr[size] = 0;

	if(wndClass!=NULL){
		wndClassStr = (*env).GetStringChars(wndClass,&isCopy);
		if(wndClassStr!=NULL){
			len = (*env).GetStringLength(wndClass);
			size = WideCharToMultiByte( CP_ACP, 0, (LPCWSTR)wndClassStr, 
					len, wndClassName,(len*2+1), NULL, NULL );
			wndClassName[size] = 0;
			(*env).ReleaseStringChars(wndClass, wndClassStr);
		}
	}
	// ʹ��Windows API CreateProcess���� �������� 
	sInfo.cb                     =   sizeof(STARTUPINFO);   
    sInfo.lpReserved             =   NULL;   
    sInfo.lpReserved2           =   NULL;   
    sInfo.cbReserved2           =   0;   
    sInfo.lpDesktop               =   NULL;   
    sInfo.lpTitle                   =   NULL;   
    sInfo.dwFlags                   =   0;   
    sInfo.dwX                           =   0;   
    sInfo.dwY                           =   0;   
    sInfo.dwFillAttribute   =   0;   
    sInfo.wShowWindow           =   SW_HIDE;   
    
	// ʹ��һ�� Windows ��ϵͳ�������ػ񴰿ڴ������¼�
	// ����ʹ�õĹ��������� WH_SHELL�����ֹ��ӿ��Խػ����ж������ڴ������߼�����¼���
	// �ڶ����������¼������������ǵĴ������� ShellProc
	hHook = SetWindowsHookEx(WH_SHELL, ShellProc,(HINSTANCE)hDllHandle,NULL);
	
    if(!CreateProcess(NULL,commandcstr,NULL,NULL, TRUE,0,NULL,NULL,&sInfo,&pInfo))
   {   
            printf("ERROR:   Cannot   launch   child   process\n");   
			release();
            return 0;
    }   
    
    // ����Ӧ�ó���֮��������Ҫ��ȡӦ�ó����������֮����ܼ������С�������Ҫʵ�ֽ��̼��ͬ����
    // �����ǵ��������У�������Ҫ�ȴ�����Ӧ�ó���������ڴ���֮�����Ƿ�һ����Ϣ��֪ͨ���ǵ������̼���ִ�С�
    // ��������ʹ�� Windows��Event��ʵ��ͬ�����������ȵ��� CreateEvent ������һ���¼���Ȼ����� WaitForSingleObject�����ȴ��¼���״̬�ı䡣
    
    // �����ǵ� ShellProc �������У�����һ����ȡӦ�ó��������ھ��
    // ���ǻ�ı��¼���״̬��֪ͨ�����̼���ִ�С�
    // �����Ǵ����¼��Ĵ��룬���Ǵ�����һ����Ϊ Global\WaitWindowCreatedEvent ���¼���

	SECURITY_ATTRIBUTES secuAtt;
	secuAtt.bInheritHandle = TRUE;
	secuAtt.lpSecurityDescriptor = NULL;
	secuAtt.nLength = sizeof(SECURITY_ATTRIBUTES);
	
	hEvent = CreateEvent(&secuAtt,FALSE,FALSE,TEXT("Global\WaitWindowCreatedEvent"));
    
          �ȴ��¼�״̬�仯���Ե������´��룺

	WaitForSingleObject(hEvent,1000*60);
	
	�������������� ShellProc �Ĵ�����롣��������У�������Ҫ��Ҫ��ȡӦ�ó���������ڡ�

	// ���� Windows ϵͳ WH_SHELL ���ӵĶ��壬
    // ���ӵĴ������ĵ�һ���������¼����ͣ��ڶ��������Ǵ��ھ����

	
	LRESULT CALLBACK ShellProc(int nCode,WPARAM wParam,LPARAM lParam){
	
	// ���������жϴ��ڵ������Ƿ��� HSHELL_WINDOWCREATED
	if(nCode==HSHELL_WINDOWCREATED && childInstanceId!=0){
		HWND hwnd=HWND(wParam);
		DWORD pid;
		HANDLE childEvent;
		char classname[100];
		GetWindowThreadProcessId(hwnd,&pid);
		// Ȼ���ж϶�Ӧ���������Ľ��̺��Ƿ����������������Ӧ�ó���
		if(pid==childInstanceId){
			// �����Ҫ��Ҫ�жϴ������͡�
			if(wndClassName[0]!=0){
				int count = GetClassName(hwnd,classname,100);
				classname[count] = 0;
				if(strcmp(classname,wndClassName)!=0){
				  return CallNextHookEx(hHook, nCode,
				                        wParam, lParam);
				}
			}
			hChildWnd = hwnd;
			ShowWindow(hChildWnd,SW_HIDE);
			childEvent = OpenEvent(EVENT_ALL_ACCESS,
			            TRUE,TEXT("Global\WaitWindowCreatedEvent"));
			// һ�������ҵ���Ӧ�ó��������ڣ�����ͨ������ SetEvent ��֪ͨ�����̼���ִ�С�
			if(childEvent!=0){
				SetEvent(childEvent);
			}
		}
	}
	
	return CallNextHookEx(hHook, nCode, wParam, lParam);
}

	// �� Windows Ӧ�����������ó�ָ�����ڵ��Ӵ���

	// ��ȡӦ�ó���������ھ��֮���� Java_com_reparent_ReparentUtil_startAndReparent ���������

	// ����ͨ������ Windows �� SetParent �����������ó����ǵ��Ӵ��ڣ�
	 
	// ͬʱ����һ��Ӧ�ó��򴰿ڵĴ�С��ʹ���ܸպ���ʾ�����ǵĴ����С�
	 
	// Ϊ�˱��ⴰ�ڵ���˸�������Ƚ��������أ�reparent ֮������ʾ��
	 
	 // Ϊ��ȥ��Ӧ�ó���Ĵ�������������Ҫ��Ӧ�ó���Ĵ������͸�Ϊ WS_POPUP��

	if(hChildWnd!=0){
			RECT rect;
			GetWindowRect((HWND)hParentWnd,&rect);
			ShowWindow(hChildWnd,SW_HIDE);
			SetParent(hChildWnd,(HWND)hParentWnd);
			SetWindowPos(hChildWnd,(HWND)0,0,0,
			              rect.right-rect.left,rect.bottom-rect.top,
				SWP_NOZORDER | SWP_NOACTIVATE | SWP_ASYNCWINDOWPOS | 
				SWP_SHOWWINDOW | SWP_NOSENDCHANGING | SWP_DEFERERASE);
			SetWindowLong(hChildWnd,GWL_STYLE,WS_POPUP);
			ShowWindow(hChildWnd,SW_SHOW);
	}

	 */
	
}
