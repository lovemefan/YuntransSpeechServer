package com.yuntrans.common.auth;

import com.yuntrans.common.task.YuntransService;
import com.yuntrans.common.task.YuntransTaskContext;

public interface AuthService  extends YuntransService<YuntransTaskContext, AuthParams, AuthListener, AuthTask> {}
