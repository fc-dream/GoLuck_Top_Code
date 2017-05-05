LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := goluck
LOCAL_SRC_FILES := KeyUtil.cpp
include $(BUILD_SHARED_LIBRARY)