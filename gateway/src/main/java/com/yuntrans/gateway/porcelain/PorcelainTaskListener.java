/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:35
 */
package com.yuntrans.gateway.porcelain;

import com.yuntrans.common.task.YuntransTaskListener;

public interface PorcelainTaskListener extends YuntransTaskListener<PorcelainEvent> {
    void onEvent(PorcelainEvent paramPorcelainEvent);

    void onStream(byte[] paramArrayOfbyte);
}
