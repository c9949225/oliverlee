#include "HelloWorld.h"
#include <stdio.h>

/* ʵ��ͷ�ļ���C++Դ�ļ� */
JNIEXPORT void JNICALL Java_net_oliver_olframework_jni_HelloWorld_print(JNIEnv * env, jobject obj) 
{
	printf("Hello World");
}