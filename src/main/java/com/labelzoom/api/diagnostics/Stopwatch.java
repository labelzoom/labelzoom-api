package com.labelzoom.api.diagnostics;

/* Copyright (C) 2021, LabelZoom, a subsidiary of RJF Technology Solutions, Inc.
 * All rights reserved.  Proprietary and confidential.
 *
 * This file is subject to the license terms found at
 * https://www.labelzoom.net/end-user-license-agreement/
 *
 * The methods and techniques described herein are considered
 * confidential and/or trade secrets.
 * No part of this file may be copied, modified, propagated,
 * or distributed except as authorized by the license.
 */

public class Stopwatch
{
    private long startTime = 0L;
    private long stopTime = 0L;
    private long elapsedTime = 0L;
    private boolean isRunning = false;

    public Stopwatch() { }

    public void start()
    {
        if (!isRunning)
        {
            if (stopTime > 0)
            {
                elapsedTime += (stopTime - startTime);
            }
            startTime = System.nanoTime();
            isRunning = true;
        }
    }

    public void stop()
    {
        if (isRunning)
        {
            stopTime = System.nanoTime();
            isRunning = false;
        }
    }

    public long getElapsedMilliseconds()
    {
        return getElapsedNanoseconds() / 1000 / 1000;
    }

    public long getElapsedNanoseconds()
    {
        if (isRunning)
        {
            return elapsedTime + (System.nanoTime() - startTime);
        }
        else
        {
            return elapsedTime + (stopTime - startTime);
        }
    }

    public static Stopwatch startNew()
    {
        final Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        return stopwatch;
    }

    @Override
    public String toString()
    {
        return String.format("%4d ms / %8d us", getElapsedMilliseconds(), getElapsedNanoseconds() / 1000);
    }
}
