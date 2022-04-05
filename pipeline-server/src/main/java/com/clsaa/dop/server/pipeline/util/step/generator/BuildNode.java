package com.clsaa.dop.server.pipeline.util.step.generator;

import com.clsaa.dop.server.pipeline.util.StepUtil;
import com.clsaa.dop.server.pipeline.util.step.StepGenerationOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildNode {

    private static final List<String> steps = new ArrayList<>(Arrays.asList(
            "sh 'npm --version'",
            "sh 'node --version'"
    ));

    public static String generate(StepGenerationOption option) {
        return StepUtil.generate(option.getValuesMap(), steps);
    }
}
