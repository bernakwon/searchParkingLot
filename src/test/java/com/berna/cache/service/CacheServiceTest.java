package com.berna.cache.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private SchedulerService schedulerService;



    @Test
    public void changeCron() {

    }

   /* @TestConfiguration
    static class SchedulingTestConfig implements SchedulingConfigurer {
        @Autowired
        private SchedulerService schedulerService;

        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
            List<IntervalTask> taskList = taskRegistrar.getFixedDelayTaskList();
            List<IntervalTask> newTaskList = new ArrayList<>();

            for (IntervalTask task : taskList) {
                ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task.getRunnable();
                if (runnable.getTarget() instanceof MyService) {
                    newTaskList.add(new IntervalTask(runnable, CUSTOM_DELAY, CUSTOM_DELAY));
                }
            }

            taskRegistrar.setFixedDelayTasksList(newTaskList);
        }
    }
}*/
}