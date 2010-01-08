package net.oliver.olframework.jni.reparent;

public class ReparentUtil {
	static{
		System.loadLibrary("reparent");
	}
	
	/**
	 * 1. 启动 Windows 应用程序；
	 * 2. 获取 Windows 应用程序的主窗口句柄；
	 * 3. 将 Windows 应用程序的主窗口设置成指定窗口的子窗口。 
	 * 
	 * @param parentWnd 父窗口句柄
	 * @param command 程序启动命令，就是应用程序的路径
	 * @param wndClass 程序主窗口类型
	 * @return
	 */
	public static native int startAndReparent(int parentWnd,String command,String wndClass);
	
	/*
	// 启动应用程序的实现
	
	JNIEXPORT jint JNICALL Java_net_oliver_olframework_jni_reparent_ReparentUtil_startAndReparent
     (JNIEnv *env, jclass classobj, jint parent, jstring command,jstring wndClass){
     
	jboolean isCopy=FALSE;
	PROCESS_INFORMATION   pInfo;   
	STARTUPINFO                   sInfo; 

	int hParentWnd;
	// 将命令转化为c
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
	// 使用Windows API CreateProcess方法 启动程序 
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
    
	// 使用一个 Windows 的系统钩子来截获窗口创建的事件
	// 我们使用的钩子类型是 WH_SHELL。这种钩子可以截获所有顶级窗口创建或者激活的事件。
	// 第二个参数是事件处理函数。我们的处理函数叫 ShellProc
	hHook = SetWindowsHookEx(WH_SHELL, ShellProc,(HINSTANCE)hDllHandle,NULL);
	
    if(!CreateProcess(NULL,commandcstr,NULL,NULL, TRUE,0,NULL,NULL,&sInfo,&pInfo))
   {   
            printf("ERROR:   Cannot   launch   child   process\n");   
			release();
            return 0;
    }   
    
    // 启动应用程序之后，我们需要获取应用程序的主窗口之后才能继续运行。这里需要实现进程间的同步。
    // 在我们的主进程中，我们需要等待，当应用程序的主窗口创建之后，我们发一个消息，通知我们的主进程继续执行。
    // 我们这里使用 Windows的Event来实现同步。我们首先调用 CreateEvent 来创建一个事件，然后调用 WaitForSingleObject（）等待事件的状态改变。
    
    // 在我们的 ShellProc 处理函数中，我们一旦获取应用程序主窗口句柄
    // 我们会改变事件的状态以通知主进程继续执行。
    // 以下是创建事件的代码，我们创建了一个名为 Global\WaitWindowCreatedEvent 的事件：

	SECURITY_ATTRIBUTES secuAtt;
	secuAtt.bInheritHandle = TRUE;
	secuAtt.lpSecurityDescriptor = NULL;
	secuAtt.nLength = sizeof(SECURITY_ATTRIBUTES);
	
	hEvent = CreateEvent(&secuAtt,FALSE,FALSE,TEXT("Global\WaitWindowCreatedEvent"));
    
          等待事件状态变化可以调用以下代码：

	WaitForSingleObject(hEvent,1000*60);
	
	下面我们再来看 ShellProc 的处理代码。这个函数中，我们主要是要获取应用程序的主窗口。

	// 根据 Windows 系统 WH_SHELL 钩子的定义，
    // 钩子的处理函数的第一个参数是事件类型，第二个参数是窗口句柄。

	
	LRESULT CALLBACK ShellProc(int nCode,WPARAM wParam,LPARAM lParam){
	
	// 我们首先判断窗口的类型是否是 HSHELL_WINDOWCREATED
	if(nCode==HSHELL_WINDOWCREATED && childInstanceId!=0){
		HWND hwnd=HWND(wParam);
		DWORD pid;
		HANDLE childEvent;
		char classname[100];
		GetWindowThreadProcessId(hwnd,&pid);
		// 然后判断对应窗口所属的进程号是否等于我们所启动的应用程序
		if(pid==childInstanceId){
			// 如果需要还要判断窗口类型。
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
			// 一旦我们找到了应用程序主窗口，我们通过调用 SetEvent 来通知主进程继续执行。
			if(childEvent!=0){
				SetEvent(childEvent);
			}
		}
	}
	
	return CallNextHookEx(hHook, nCode, wParam, lParam);
}

	// 将 Windows 应用主窗口设置成指定窗口的子窗口

	// 获取应用程序的主窗口句柄之后，在 Java_com_reparent_ReparentUtil_startAndReparent 函数的最后，

	// 我们通过调用 Windows 的 SetParent 函数将其设置成我们的子窗口，
	 
	// 同时调整一下应用程序窗口的大小以使其能刚好显示在我们的窗口中。
	 
	// 为了避免窗口的闪烁，我们先将窗口隐藏，reparent 之后再显示。
	 
	 // 为了去掉应用程序的窗口栏，我们需要将应用程序的窗口类型改为 WS_POPUP。

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
