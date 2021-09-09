package com.zalocoders.payoneer;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxImmediateSchedulerRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());

        try {
            base.evaluate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            RxJavaPlugins.reset();
            RxAndroidPlugins.reset();
        }
        return base;
    }
}