#include "HelloWorld.h"
#include <stdio.h>

/* 实现头文件的C++源文件 */
JNIEXPORT void JNICALL Java_net_oliver_olframework_jni_HelloWorld_print(JNIEnv * env, jobject obj) 
{
	printf("Hello World");
}