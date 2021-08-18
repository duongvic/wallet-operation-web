package vn.mog.framework.util.tools.random.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.mog.framework.util.tools.random.IRandomRepository;

@Service
public final class RandomRepositoryImpl implements IRandomRepository {
  protected static final Logger LOG = LoggerFactory.getLogger(RandomRepositoryImpl.class);
  private final AtomicReference<Future<SecureRandom>> holder;

  public RandomRepositoryImpl() {
    this.holder = new AtomicReference<Future<SecureRandom>>();
  }

  @Override
  public void clear() {
    Future<SecureRandom> ft = this.holder.getAndSet(null);

    if (ft != null)
      ft.cancel(true);
  }

  @Override
  public SecureRandom getSecureRandom() {
    while (true) {
      Future<SecureRandom> future = this.holder.get();
      Future<SecureRandom> check;
      if (future == null) {
        Callable<SecureRandom> callable = new Callable<SecureRandom>() {
          public SecureRandom call() throws Exception {
            SecureRandom newInstance;
            try {
              newInstance = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
              RandomRepositoryImpl.LOG.info("SHA1PRNG not available on this JVM", e);
              newInstance = new SecureRandom();
            }

            newInstance.nextBytes(new byte[1]);

            return newInstance;
          }
        };
        FutureTask<SecureRandom> ft = new FutureTask<SecureRandom>(callable);
        if (this.holder.compareAndSet(null, ft)) {
          LOG.debug("Initialised hold to future {}", ft);

          ft.run();

          check = ft;
        } else {
          check = this.holder.get();
        }
      } else {
        check = future;
      }

      if (check == null) {
        continue;
      }

      try {
        return ((SecureRandom) check.get());
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch (ExecutionException e) {
        this.holder.compareAndSet(check, null);

        if (e.getCause() instanceof RuntimeException)
          throw ((RuntimeException) e.getCause());
        if (e.getCause() instanceof Error) {
          throw ((Error) e.getCause());
        }
        throw new IllegalStateException("Unexpected exception thrown", e.getCause());
      }
    }
  }
}
