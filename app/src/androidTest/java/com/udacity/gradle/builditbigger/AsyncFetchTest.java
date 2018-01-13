package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;
import android.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Executor;

/**
 * Created by Vinnie on 1/11/2018.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncFetchTest {
    private static final String[] jokes =new String[]
            {   "Q: How do you know when the moon has enough to eat? \rA: When it's full.",
                    "Q: What lies at the bottom of the ocean and shakes? \rA: A nervous wreck.",
                    "Q: Did you hear about the picket thieves? \rA: They took offence."
            };

    Context mContext = new MockContext();

    @Before
    public void setUp() throws Exception{
        mContext = InstrumentationRegistry.getContext();
    }

    @Test
    public void testAsyncFetch() throws Throwable{
        new CallEndpointTask(new TaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                boolean isPresent = false;
                for (int i=0;!isPresent&&i<jokes.length;i++) {
                    isPresent=jokes[i].equals(result);
                }
                junit.framework.Assert.assertTrue("Test", isPresent);
            }
        }).executeOnExecutor(new CurrentThreadExecutor(),new Pair<Context, String>(mContext,"BOB"));
    }

    public class CurrentThreadExecutor implements Executor{
        @Override
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    }

}




