package com.st.dbutil.android.process;

/**
 * Created by Daniel Costa at 8/29/16.
 * Using user computer xdata
 */
public interface OnProcessResult<PR extends  ProcessResult>
{
    void processResult(PR processResult);
}
