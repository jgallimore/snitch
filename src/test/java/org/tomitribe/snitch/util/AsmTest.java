/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.util;

import org.tomitribe.snitch.Asmifier;
import org.tomitribe.snitch.Bytecode;
import org.tomitribe.snitch.Clazz;
import org.tomitribe.snitch.TimingEnhancer;
import org.tomitribe.snitch.TrackEnhancer;
import org.tomitribe.snitch.Tracker;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;

import static org.tomitribe.snitch.Method.fromToString;

/**
 * @version $Revision$ $Date$
 */
public class AsmTest {

    @org.junit.Test
    public void testIsPublic() throws Exception {
        Asmifier.asmify(Orange.class, "unmodified");
        Asmifier.asmify(Orange2.class, "modified");
        Asmifier.asmify(Green.class, "unmodified");
        Asmifier.asmify(Green2.class, "modified");

        final URLClassLoader loader = new URLClassLoader(new URL[]{});

        final Clazz greenInfo = new Clazz(Green.class.getName());
        greenInfo.put(fromToString(Green.class.getName() + ".run(java.lang.String[])"), "start");

        final Clazz orangeInfo = new Clazz(Orange.class.getName());
        orangeInfo.put(fromToString(Orange.class.getName() + ".dowithreturn()"), "doWithReturn");
        orangeInfo.put(fromToString(Orange.class.getName() + ".dowithvoid()"), "doWithVoid");


        Bytecode.modifyAndDefine(loader, orangeInfo, TimingEnhancer.class);
        Bytecode.modifyAndDefine(loader, greenInfo, TrackEnhancer.class);

        final Class<?> clazz = loader.loadClass(Green.class.getName());
        final Object green = clazz.newInstance();
        final Method run = clazz.getMethod("run", String[].class);

        run.invoke(green, new Object[]{new String[]{}});
    }

    public static class Green {

        public Date run(String[] doSomething) throws IllegalStateException {
            final Orange<Object> orange = new Orange<Object>();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithvoid();
            orange.dowithvoid();
            return null;
        }
    }

    public static class Green2 {

        public Date run(String[] doSomething) throws IllegalStateException {
            Tracker.start();
            final long doit$start = System.nanoTime();
            try {
                return track$run(doSomething);
            } finally {
                Tracker.track("theTag", doit$start);
                Tracker.stop();
            }
        }

        public Date track$run(String[] doSomething) throws IllegalStateException {
            final Orange2<Object> orange = new Orange2<Object>();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithvoid();
            orange.dowithvoid();
            return null;
        }

        public void run2(String[] doSomething) throws IllegalStateException {
            Tracker.start();
            final long doit$start = System.nanoTime();
            try {
                track$run2(doSomething);
            } finally {
                Tracker.track("theTag", doit$start);
                Tracker.stop();
            }
        }

        public void track$run2(String[] doSomething) throws IllegalStateException {
            final Orange2<Object> orange = new Orange2<Object>();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithreturn();
            orange.dowithvoid();
            orange.dowithvoid();
            orange.dowithvoid();
        }
    }

    public static class Orange<T> {
        public synchronized T dowithreturn() {
            return null;
        }

        public synchronized void dowithvoid() {
        }
    }

    public static class Orange2<T> {

        public synchronized T $dowithreturn() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }

            return null;
        }

        public T dowithreturn() {
            final long doit$start = System.nanoTime();
            try {
                return $dowithreturn();
            } finally {
                Tracker.track("dowithreturn", doit$start);
            }
        }

        public void $dowithvoid() {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }

        public void dowithvoid() {
            final long doit$start = System.nanoTime();
            try {
                $dowithvoid();
            } finally {
                Tracker.track("dowithvoid", doit$start);
            }
        }
    }
}
