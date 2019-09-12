/*
 *    Copyright 2019 HotLava03
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.hotlava03.baclava.components;

import io.github.hotlava03.baclava.Baclava;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;

import javax.script.ScriptEngine;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.*;


/**
 * Based on https://github.com/Almighty-Alpaca/JDA-Butler/blob/master/bot/src/main/java/com/almightyalpaca/discord/jdabutler/eval/Engine.java
 * <p>
 * Thank you JDA-Butler. The code in this class does NOT belong to me (HotLava03).
 * <p>
 * Changed the class to a normal class, removed the JavaScript eval and overall simplified the code to Baclava's needs.
 */
public class EvalComponent {

    // Imports used
    public static final List<String> DEFAULT_IMPORTS = Arrays.asList(
            "io.github.hotlava03.baclava.commands",
            "io.github.hotlava03.baclava.events",
            "io.github.hotlava03.baclava.listeners",
            "io.github.hotlava03.baclava.util",
            "io.github.hotlava03.baclava",
            "net.dv8tion.jda.core.entities.impl",
            "net.dv8tion.jda.core.managers",
            "net.dv8tion.jda.core.entities",
            "net.dv8tion.jda.core",
            "net.dv8tion.jda.api",
            "java.lang",
            "java.io",
            "java.math",
            "java.eval",
            "java.eval.concurrent",
            "java.time",
            "java.awt",
            "java.awt.image",
            "javax.imageio",
            "java.time.format"
    );

    private final static ScheduledExecutorService service = Executors.newScheduledThreadPool(1, newThreadFactory("eval-thread", false));

    /**
     * @param fields All shortcut variables to be used in the code
     * @param classImports All class imports
     * @param packageImports All package imports
     * @param timeout Timeout if there's any freezing
     * @param script The script to run
     * @return The result in a Triple container. The first parameter is the result, the second is the output and the third an error/exception.
     */
    public Triple<Object, String, String> eval(final Map<String, Object> fields, final Collection<String> classImports, final Collection<String> packageImports, final int timeout, final String script) {
        StringBuilder importString = new StringBuilder();
        for (final String s : classImports)
            importString.append("import ").append(s).append(";");
        for (final String s : packageImports)
            importString.append("import ").append(s).append(".*;");
        return this.eval(fields, timeout, importString + script, new GroovyScriptEngineImpl());
    }

    private Triple<Object, String, String> eval(final Map<String, Object> fields, final int timeout, final String script, final ScriptEngine engine) {

        for (final Entry<String, Object> shortcut : fields.entrySet())
            engine.put(shortcut.getKey(), shortcut.getValue());

        final StringWriter outString = new StringWriter();
        final PrintWriter outWriter = new PrintWriter(outString);
        engine.getContext().setWriter(outWriter);

        final StringWriter errorString = new StringWriter();
        final PrintWriter errorWriter = new PrintWriter(errorString);
        engine.getContext().setErrorWriter(errorWriter);
        Object result = null;
        final ScheduledFuture<Object> future = EvalComponent.service.schedule(() -> engine.eval(script), 0, TimeUnit.MILLISECONDS);


        try {
            result = future.get(timeout, TimeUnit.SECONDS);
        } catch (final ExecutionException e) {
            errorWriter.println(e.getCause().toString());
        } catch (TimeoutException | InterruptedException e) {
            future.cancel(true);
            errorWriter.println(e.toString());
        }


        return new ImmutableTriple<>(result, outString.toString(), errorString.toString());
    }

    private static ThreadFactory newThreadFactory(String threadName, boolean isdaemon) {
        return (r) ->
        {
            Thread t = new Thread(r, threadName);
            t.setDaemon(isdaemon);
            t.setUncaughtExceptionHandler((final Thread thread, final Throwable throwable) ->
                    Baclava.getLogger().throwing("There was a uncaught exception in the {} threadpool", thread.getName(), throwable));
            return t;
        };
    }
}
