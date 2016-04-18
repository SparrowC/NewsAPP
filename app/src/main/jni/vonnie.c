#include <jni.h>


JNIEXPORT jstring JNICALL
Java_com_vonnie_mynewsapp_global_Config_getIPURL(JNIEnv *env, jclass type) {
    return (*env)->NewStringUTF(env, "http://121.42.208.111:8080/NewsApp/");
    //"http://172.16.9.246:8080/NewsApp/"
}