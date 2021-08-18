package vn.mog.framework.cache.dynacache.impl;

import vn.mog.framework.cache.dynacache.DynaCacheItemFeed;
import vn.mog.framework.cache.dynacache.DynaCacheItemFeedException;

public class HttpCacheItemAsyncFeedImpl extends HttpCacheItemFeedImpl implements DynaCacheItemFeed {

    int timeOut = 5000;

    public void setTimeOut(int timeOut) {
	this.timeOut = timeOut;
    }

    // -----------------
    @Override
    public Object feedItem(Object key) throws DynaCacheItemFeedException {

	return ParalellCacheItemFeedHelper.feedItem(key, this, timeOut);
    }
}
