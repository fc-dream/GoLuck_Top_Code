//
// Created by luck on 2017/1/15.
//
#include "top_goluck_ndk_2017_1_15_HelloNDKUtil.h"
JNIEXPORT jstring JNICALL Java_top_goluck_ndk_12017_11_115_HelloNDKUtil_getHelloNDK
        (JNIEnv *env, jobject obj,jstring str) {
    return str;//这里是将java层的参数直接返回到java层
}