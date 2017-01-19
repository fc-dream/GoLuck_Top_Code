/**
 * 作者：luck on 2017/1/19 09:26
 * 邮箱：fc_dream@163.com
 * Ndk_2017_1_19
 */
#include <jni.h>
#include <string.h>
#include <stdio.h>
#include "top_goluck_util_KeyUtil.h"

//该签名是我的debug包，注：不匹配该签名的apk都不能得到正常的数据
// 即调用以下文件只会返回【该so库可能不适用你用！！！】
const char *RELEASE_SIGN = "08201dd30820146020101300d06092a864886f70d010105050030373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b3009060355040613025553301e170d3136303432393038343931365a170d3436303432323038343931365a30373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b300906035504061302555330819f300d06092a864886f70d010101050003818d00308189028181008d7724d953fac08229e38147379ce329d1054d413eea9ebec5771f0d3e9cf1b6433a6574d25d950277da8b16b43f41167122fac1d372c1abdb0db0cc59f9fc06191a89847e05757afedd33aba94eeecf96044ac12effb562c16d9caf4bbeb42912250138fda5d95e30c99dec6cbd380c1ee297a7eeb97a33af4b657f584844e50203010001300d06092a864886f70d01010505000381810058ad2255854a130850df01b60b9d21bd88c1ab5decdb9bb7940696c74d7f758035ab9d906f9e981aa9214dc6d9bb1cef80483ad735424f2c82915d4eca0cad4a85b984a0dc474b8a9e7c606a401e3d043f1e5ce5fec99d8e0aab72ecb22eaa3494b84f166e3e4aa382d2bda87a44c7f38d5aa2354e5f299f72c85665cb9707a6";

JNIEXPORT jstring JNICALL Java_top_goluck_util_KeyUtil_getKey(JNIEnv *env, jobject object, jobject contextObject,jint type) {

    jclass native_class = env->GetObjectClass(contextObject);
    jmethodID pm_id = env->GetMethodID(native_class, "getPackageManager","()Landroid/content/pm/PackageManager;");
    jobject pm_obj = env->CallObjectMethod(contextObject, pm_id);
    jclass pm_clazz = env->GetObjectClass(pm_obj);
    // 得到 getPackageInfo 方法的 ID
    jmethodID package_info_id = env->GetMethodID(pm_clazz, "getPackageInfo", "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    jclass native_classs = env->GetObjectClass(contextObject);
    jmethodID mId = env->GetMethodID(native_classs, "getPackageName","()Ljava/lang/String;");
    jstring pkg_str = static_cast<jstring>(env->CallObjectMethod(contextObject, mId));
    // 获得应用包的信息
    jobject pi_obj = env->CallObjectMethod(pm_obj, package_info_id, pkg_str, 64);
    // 获得 PackageInfo 类
    jclass pi_clazz = env->GetObjectClass(pi_obj);
    // 获得签名数组属性的 ID
    jfieldID signatures_fieldId = env->GetFieldID(pi_clazz, "signatures","[Landroid/content/pm/Signature;");
    jobject signatures_obj = env->GetObjectField(pi_obj, signatures_fieldId);
    jobjectArray signaturesArray = (jobjectArray) signatures_obj;
    jsize size = env->GetArrayLength(signaturesArray);
    jobject signature_obj = env->GetObjectArrayElement(signaturesArray, 0);
    jclass signature_clazz = env->GetObjectClass(signature_obj);
    jmethodID string_id = env->GetMethodID(signature_clazz, "toCharsString","()Ljava/lang/String;");
    jstring str = static_cast<jstring>(env->CallObjectMethod(signature_obj, string_id));
    char *c_msg = (char *) env->GetStringUTFChars(str, 0);
    if (strcmp(c_msg, RELEASE_SIGN) == 0)//如果当前apk的签名一致返回需要数据
    {
        if (type == 1) {
            return env->NewStringUTF("我是通过native方法返回的QQKey");
        } else if (type == 2) {
            return env->NewStringUTF("我是通过native方法返回的WeiXinKey");
        } else {
            return env->NewStringUTF("error，没有该类型的Key");
        }
    } else {
        return env->NewStringUTF("该so库可能不适合你用！！！");
    }

}