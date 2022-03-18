package sun.zhao.guo.guide.aqs.diylock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.aqs.diylock
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/18/下午3:31
 * @Description:
 */
public class DiyLock {

    private static class Sync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1 );
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private Sync sync = new Sync();

    public void lock(){
        sync.acquire(1);
    }

    public void unlock(){
        sync.release(0);
    }

}
